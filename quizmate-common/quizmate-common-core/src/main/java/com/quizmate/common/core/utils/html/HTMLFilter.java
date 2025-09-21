package com.quizmate.common.core.utils.html;

import com.quizmate.common.core.utils.StringUtils;
import com.quizmate.common.core.utils.ThrowUtils;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h3>quizmate-backend</h3>
 * <p>HTML 过滤器，去除 XSS 隐患</p>
 * <p>
 * 留言板/评论区：攻击者评论里写 <script>alert('XSS')</script>。
 * 个人资料/昵称修改：用户昵称里输入脚本，其他人访问时触发。
 * </p>
 *
 * @author moru
 * @since 2025-09-19 22:55
 */
@Getter
public class HTMLFilter {
    /**
     * Regular expression rules
     * define the regex to match dangerous HTML tags and attributes
     * for example:
     * tags categories:
     *
     **/
    private static final int REGEX_FLAGS_SI = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;
    private static final Pattern P_COMMENTS = Pattern.compile("<!--(.*?)-->", Pattern.DOTALL);
    private static final Pattern P_COMMENT = Pattern.compile("^!--(.*)--$", REGEX_FLAGS_SI);
    private static final Pattern P_TAGS = Pattern.compile("<(.*?)>", Pattern.DOTALL);
    private static final Pattern P_END_TAG = Pattern.compile("^/([a-z0-9]+)", REGEX_FLAGS_SI);
    private static final Pattern P_START_TAG = Pattern.compile("^([a-z0-9]+)(.*?)(/?)$", REGEX_FLAGS_SI);
    private static final Pattern P_QUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)=([\"'])(.*?)\\2", REGEX_FLAGS_SI);
    private static final Pattern P_UNQUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)(=)([^\"\\s']+)", REGEX_FLAGS_SI);
    private static final Pattern P_PROTOCOL = Pattern.compile("^([^:]+):", REGEX_FLAGS_SI);
    private static final Pattern P_ENTITY = Pattern.compile("&#(\\d+);?");
    private static final Pattern P_ENTITY_UNICODE = Pattern.compile("&#x([0-9a-f]+);?");
    private static final Pattern P_ENCODE = Pattern.compile("%([0-9a-f]{2});?");
    private static final Pattern P_VALID_ENTITIES = Pattern.compile("&([^&;]*)(?=(;|&|$))");
    private static final Pattern P_VALID_QUOTES = Pattern.compile("(>|^)([^<]+?)(<|$)", Pattern.DOTALL);
    private static final Pattern P_END_ARROW = Pattern.compile("^>");
    private static final Pattern P_BODY_TO_END = Pattern.compile("<([^>]*?)(?=<|$)");
    private static final Pattern P_XML_CONTENT = Pattern.compile("(^|>)([^<]*?)(?=>)");
    private static final Pattern P_STRAY_LEFT_ARROW = Pattern.compile("<([^>]*?)(?=<|$)");
    private static final Pattern P_STRAY_RIGHT_ARROW = Pattern.compile("(^|>)([^<]*?)(?=>)");
    private static final Pattern P_AMP = Pattern.compile("&");
    private static final Pattern P_QUOTE = Pattern.compile("\"");
    private static final Pattern P_LEFT_ARROW = Pattern.compile("<");
    private static final Pattern P_RIGHT_ARROW = Pattern.compile(">");
    private static final Pattern P_BOTH_ARROWS = Pattern.compile("<>");

    /**
     * cache the compiled patterns for removing blanks
     * key: tag name, value: compiled pattern
     * 相当于一个正则模式的缓存池
     */
    // 存储匹配“成对标签但中间为空”的正则
    private static final ConcurrentMap<String, Pattern> P_REMOVE_PAIR_BLANKS = new ConcurrentHashMap<>();
    // 存储匹配“自闭合标签但中间为空”的正则
    private static final ConcurrentMap<String, Pattern> P_REMOVE_SELF_BLANKS = new ConcurrentHashMap<>();

    /**
     * define the white list of allowed HTML tags and attributes
     * key: tag name, value: list of allowed attributes for the tag
     * e.g.:
     * "a" : ["href", "target"]
     * "img" : ["src", "width", "height", "alt"]
     * "b" : []
     * "strong" : []
     * "i" : []
     * "em" : []
     * 其他标签一律不允许
     */
    // 白名单标签及其允许的属性
    private final Map<String, List<String>> vAllowed;

    // 记录标签出现次数，key: 标签名，value: 出现次数
    private final Map<String, Integer> vTagCounts = new ConcurrentHashMap<>();

    // 自闭合标签
    private final String[] vSelfClosingTags;

    // 必须闭合的标签
    private final String[] vNeedClosingTags;

    // 不允许出现的标签
    private final String[] vDisallowed;

    // 需要校验协议的属性
    private final String[] vProtocolAtts;

    // 允许的协议
    private final String[] vAllowedProtocols;

    // 需要移除空内容的标签
    private final String[] vRemoveBlanks;

    // 允许的实体，如 &amp; &gt; &lt; &quot;
    private final String[] vAllowedEntities;

    // 是否去除注释内容
    private final boolean stripComment;
    // 是否对引号进行编码
    private final boolean encodeQuotes;

    // 是否总是生成标签，false 则不生成 <>
    private final boolean alwaysMakeTags;

    public HTMLFilter() {
        // init the white list
        vAllowed = new HashMap<>();

        final ArrayList<String> a_atts = new ArrayList<>();
        a_atts.add("href");
        a_atts.add("target");
        vAllowed.put("a", a_atts);

        final ArrayList<String> img_atts = new ArrayList<>();
        img_atts.add("src");
        img_atts.add("width");
        img_atts.add("height");
        img_atts.add("alt");
        vAllowed.put("img", img_atts);

        final ArrayList<String> no_atts = new ArrayList<>();
        vAllowed.put("b", no_atts);
        vAllowed.put("strong", no_atts);
        vAllowed.put("i", no_atts);
        vAllowed.put("em", no_atts);

        vSelfClosingTags = new String[]{"img"};
        vNeedClosingTags = new String[]{"a", "b", "strong", "i", "em"};
        vDisallowed = new String[]{};
        vAllowedProtocols = new String[]{"http", "mailto", "https"}; // no ftp.
        vProtocolAtts = new String[]{"src", "href"};
        vRemoveBlanks = new String[]{"a", "b", "strong", "i", "em"};
        vAllowedEntities = new String[]{"amp", "gt", "lt", "quot"};
        stripComment = true;
        encodeQuotes = true;
        alwaysMakeTags = false;
    }

    @SuppressWarnings("unchecked")
    public HTMLFilter(final Map<String, Object> conf) {

        // 使用工具类抛出异常
        ThrowUtils.throwIf(!conf.containsKey("vAllowed"), new RuntimeException("configuration requires vAllowed"));
        ThrowUtils.throwIf(!conf.containsKey("vSelfClosingTags"), new RuntimeException("configuration requires vSelfClosingTags"));
        ThrowUtils.throwIf(!conf.containsKey("vNeedClosingTags"), new RuntimeException("configuration requires vNeedClosingTags"));
        ThrowUtils.throwIf(!conf.containsKey("vDisallowed"), new RuntimeException("configuration requires vDisallowed"));
        ThrowUtils.throwIf(!conf.containsKey("vAllowedProtocols"), new RuntimeException("configuration requires vAllowedProtocols"));
        ThrowUtils.throwIf(!conf.containsKey("vProtocolAtts"), new RuntimeException("configuration requires vProtocolAtts"));
        ThrowUtils.throwIf(!conf.containsKey("vRemoveBlanks"), new RuntimeException("configuration requires vRemoveBlanks"));
        ThrowUtils.throwIf(!conf.containsKey("vAllowedEntities"), new RuntimeException("configuration requires vAllowedEntities"));

        vAllowed = Collections.unmodifiableMap((HashMap<String, List<String>>) conf.get("vAllowed"));
        vSelfClosingTags = (String[]) conf.get("vSelfClosingTags");
        vNeedClosingTags = (String[]) conf.get("vNeedClosingTags");
        vDisallowed = (String[]) conf.get("vDisallowed");
        vAllowedProtocols = (String[]) conf.get("vAllowedProtocols");
        vProtocolAtts = (String[]) conf.get("vProtocolAtts");
        vRemoveBlanks = (String[]) conf.get("vRemoveBlanks");
        vAllowedEntities = (String[]) conf.get("vAllowedEntities");
        stripComment = conf.containsKey("stripComment") ? (Boolean) conf.get("stripComment") : true;
        encodeQuotes = conf.containsKey("encodeQuotes") ? (Boolean) conf.get("encodeQuotes") : true;
        alwaysMakeTags = conf.containsKey("alwaysMakeTags") ? (Boolean) conf.get("alwaysMakeTags") : true;
    }

    public static String chr(final int decimal) {
        return String.valueOf((char) decimal);
    }

    public static String htmlSpecialChars(final String s) {
        String result = s;

        result = regexReplace(P_AMP, "&amp;", result);
        result = regexReplace(P_QUOTE, "&quot;", result);
        result = regexReplace(P_LEFT_ARROW, "&lt;", result);
        result = regexReplace(P_RIGHT_ARROW, "&gt;", result);

        return result;
    }

    private static String regexReplace(final Pattern p, final String r, final String s) {
        return p.matcher(s).replaceAll(r);
    }

    public String filter(final String input) {
        reset();

        String s = input;

        s = escapeComments(s);
        s = balanceHTML(s);
        s = checkTags(s);
        s = processRemoveBlanks(s);
        // s = validateEntities(s);

        return s;
    }

    private String escapeComments(String s) {
        Matcher matcher = P_COMMENTS.matcher(s);
        final StringBuilder buffer = new StringBuilder();
        if (matcher.find()) {
            final String match = matcher.group(1); // match without comment delimiters
            matcher.appendReplacement(buffer, Matcher.quoteReplacement("<!--" + htmlSpecialChars(match) + "-->"));
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private String balanceHTML(String s) {
        if (alwaysMakeTags) {
            //
            // try and form html
            //
            s = regexReplace(P_END_ARROW, "", s);
            // 不追加结束标签
            s = regexReplace(P_BODY_TO_END, "<$1>", s);
            s = regexReplace(P_XML_CONTENT, "$1<$2", s);

        } else {
            //
            // escape stray brackets
            //
            s = regexReplace(P_STRAY_LEFT_ARROW, "&lt;$1", s);
            s = regexReplace(P_STRAY_RIGHT_ARROW, "$1$2&gt;<", s);

            //
            // the last regexp causes '<>' entities to appear
            // (we need to do a lookahead assertion so that the last bracket can
            // be used in the next pass of the regexp)
            //
            s = regexReplace(P_BOTH_ARROWS, "", s);
        }

        return s;
    }

    private String checkTags(String s) {
        Matcher m = P_TAGS.matcher(s);

        final StringBuilder builder = new StringBuilder();
        while (m.find()) {
            String replaceStr = m.group(1);
            replaceStr = processTag(replaceStr);
            m.appendReplacement(builder, Matcher.quoteReplacement(replaceStr));
        }
        m.appendTail(builder);

        // these get tallied in processTag
        // (remember to reset before subsequent calls to filter method)
        final StringBuilder sBuilder = new StringBuilder(builder.toString());
        for (String key : vTagCounts.keySet()) {
            for (int ii = 0; ii < vTagCounts.get(key); ii++) {
                sBuilder.append("</").append(key).append(">");
            }
        }
        s = sBuilder.toString();

        return s;
    }

    private String processTag(final String s) {
        // ending tags
        Matcher m = P_END_TAG.matcher(s);
        if (m.find()) {
            final String name = m.group(1).toLowerCase();
            if (allowed(name)) {
                if (!inArray(name, vSelfClosingTags)) {
                    if (vTagCounts.containsKey(name)) {
                        vTagCounts.put(name, vTagCounts.get(name) - 1);
                        return "</" + name + ">";
                    }
                }
            }
        }

        // starting tags
        m = P_START_TAG.matcher(s);
        if (m.find()) {
            final String name = m.group(1).toLowerCase();
            final String body = m.group(2);
            String ending = m.group(3);

            // debug( "in a starting tag, name='" + name + "'; body='" + body + "'; ending='" + ending + "'" );
            if (allowed(name)) {
                final StringBuilder params = new StringBuilder();

                final Matcher m2 = P_QUOTED_ATTRIBUTES.matcher(body);
                final Matcher m3 = P_UNQUOTED_ATTRIBUTES.matcher(body);
                final List<String> paramNames = new ArrayList<>();
                final List<String> paramValues = new ArrayList<>();
                while (m2.find()) {
                    paramNames.add(m2.group(1)); // ([a-z0-9]+)
                    paramValues.add(m2.group(3)); // (.*?)
                }
                while (m3.find()) {
                    paramNames.add(m3.group(1)); // ([a-z0-9]+)
                    paramValues.add(m3.group(3)); // ([^\"\\s']+)
                }

                String paramName, paramValue;
                for (int ii = 0; ii < paramNames.size(); ii++) {
                    paramName = paramNames.get(ii).toLowerCase();
                    paramValue = paramValues.get(ii);

                    // debug( "paramName='" + paramName + "'" );
                    // debug( "paramValue='" + paramValue + "'" );
                    // debug( "allowed? " + vAllowed.get( name ).contains( paramName ) );

                    if (allowedAttribute(name, paramName)) {
                        if (inArray(paramName, vProtocolAtts)) {
                            paramValue = processParamProtocol(paramValue);
                        }
                        params.append(' ').append(paramName).append("=\\\"").append(paramValue).append("\\\"");
                    }
                }

                if (inArray(name, vSelfClosingTags)) {
                    ending = " /";
                }

                if (inArray(name, vNeedClosingTags)) {
                    ending = "";
                }

                if (ending == null || ending.isEmpty()) {
                    if (vTagCounts.containsKey(name)) {
                        vTagCounts.put(name, vTagCounts.get(name) + 1);
                    } else {
                        vTagCounts.put(name, 1);
                    }
                } else {
                    ending = " /";
                }
                return "<" + name + params + ending + ">";
            } else {
                return "";
            }
        }

        // comments
        m = P_COMMENT.matcher(s);
        if (!stripComment && m.find()) {
            return "<" + m.group() + ">";
        }

        return "";
    }

    private String processParamProtocol(String s) {
        s = decodeEntities(s);
        final Matcher m = P_PROTOCOL.matcher(s);
        if (m.find()) {
            final String protocol = m.group(1);
            if (!inArray(protocol, vAllowedProtocols)) {
                // bad protocol, turn into local anchor link instead
                s = "#" + s.substring(protocol.length() + 1);
                if (s.startsWith("#//")) {
                    s = "#" + s.substring(3);
                }
            }
        }

        return s;
    }

    private String processRemoveBlanks(final String s) {
        String result = s;
        for (String tag : vRemoveBlanks) {
            if (!P_REMOVE_PAIR_BLANKS.containsKey(tag)) {
                P_REMOVE_PAIR_BLANKS.putIfAbsent(tag, Pattern.compile("<" + tag + "(\\s[^>]*)?></" + tag + ">"));
            }
            result = regexReplace(P_REMOVE_PAIR_BLANKS.get(tag), "", result);
            if (!P_REMOVE_SELF_BLANKS.containsKey(tag)) {
                P_REMOVE_SELF_BLANKS.putIfAbsent(tag, Pattern.compile("<" + tag + "(\\s[^>]*)?/>"));
            }
            result = regexReplace(P_REMOVE_SELF_BLANKS.get(tag), "", result);
        }

        return result;
    }

    private String decodeEntities(String s) {
        StringBuffer buf = new StringBuffer();

        Matcher m = P_ENTITY.matcher(s);
        while (m.find()) {
            final String match = m.group(1);
            final int decimal = Integer.decode(match);
            m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
        }
        m.appendTail(buf);
        s = buf.toString();

        buf = new StringBuffer();
        m = P_ENTITY_UNICODE.matcher(s);
        s = getString(buf, m);

        buf = new StringBuffer();
        m = P_ENCODE.matcher(s);
        s = getString(buf, m);

        s = validateEntities(s);
        return s;
    }

    private String getString(StringBuffer buf, Matcher m) {
        while (m.find()) {
            final String match = m.group(1);
            final int decimal = Integer.valueOf(match, 16);
            m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
        }
        m.appendTail(buf);
        return buf.toString();
    }

    private String validateEntities(final String s) {
        StringBuilder builder = new StringBuilder();

        // validate entities throughout the string
        Matcher m = P_VALID_ENTITIES.matcher(s);
        while (m.find()) {
            final String one = m.group(1); // ([^&;]*)
            final String two = m.group(2); // (?=(;|&|$))
            m.appendReplacement(builder, Matcher.quoteReplacement(checkEntity(one, two)));
        }
        m.appendTail(builder);

        return encodeQuotes(builder.toString());
    }

    private String encodeQuotes(final String s) {
        if (encodeQuotes) {
            StringBuilder builder = new StringBuilder();
            Matcher m = P_VALID_QUOTES.matcher(s);
            while (m.find()) {
                final String one = m.group(1); // (>|^)
                final String two = m.group(2); // ([^<]+?)
                final String three = m.group(3); // (<|$)
                // 不替换双引号为&quot;，防止json格式无效 regexReplace(P_QUOTE, "&quot;", two)
                m.appendReplacement(builder, Matcher.quoteReplacement(one + two + three));
            }
            m.appendTail(builder);
            return builder.toString();
        } else {
            return s;
        }
    }

    private String checkEntity(final String preamble, final String term) {

        return ";".equals(term) && isValidEntity(preamble) ? '&' + preamble : "&amp;" + preamble;
    }

    private boolean isValidEntity(final String entity) {
        return inArray(entity, vAllowedEntities);
    }

    private boolean allowedAttribute(final String name, final String paramName) {
        return allowed(name) && (vAllowed.isEmpty() || vAllowed.get(name).contains(paramName));
    }

    private boolean allowed(String name) {
        return (vAllowed.isEmpty() || vAllowed.containsKey(name) && !inArray(name, vDisallowed));
    }

    private boolean inArray(final String name, final String[] vDisallowed) {
        for (String item : vDisallowed) {
            if (StringUtils.isNotBlank(item) && item.equals(name)) return true;
        }
        return false;

    }

    private void reset() {
        vTagCounts.clear();
    }
}
