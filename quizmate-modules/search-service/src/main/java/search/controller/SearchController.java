package search.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>quizmate-backend</h3>
 * <p>搜索服务控制器接口</p>
 *
 * @author moru
 * @since 2025-09-20 18:57
 */
@RestController
@RequestMapping("/quizmate-search")
@Slf4j
public class SearchController {

    @RequestMapping("/test")
    public String test() {
        log.info("Search service is working.");
        return "Search service is working.";
    }
}
