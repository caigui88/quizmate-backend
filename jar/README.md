本文件夹是作者在开发过程中一些无法从远程仓库下载的 jar 包的存放目录。
针对无法从远程仓库直接下载 jar 包的情况，可以将 jar 包放在本目录下，然后通过 `mvn install:install-file` 命令安装到本地 maven 仓库。
示例指令为：

```bash
<dependency>
    <groupId>cn.dev33</groupId>
    <artifactId>sa-token-reactor-spring-boot-starter</artifactId>
    <version>${sa-token.version}</version>
</dependency>

mvn install:install-file -DgroupId="cn.dev33" -DartifactId="sa-token-reactor-spring-boot-starter" -Dversion="sa-token.version" -Dpackaging="jar" -Dfile="/*/sa-token-reactor-spring-boot-starter-1.39.0.jar"
```