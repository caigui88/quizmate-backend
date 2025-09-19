# quizmate
面试伙伴系统，对鱼皮编程导航中多个项目进行服务集成与服务拆分，开发的一个微服务项目

## 平台简介

基于 ruoyi-cloud 进行二次开发，以若依原有的后台系统作为微服务的管理端，面试鸭作为核心业务模块，提供了一个完整的分布式微服务解决方案。

* 后端采用Spring Boot、Spring Cloud & Alibaba。
* 注册中心、配置中心选型Nacos，权限认证使用Redis。
* 流量控制框架选型Sentinel，分布式事务选型Seata。

## 系统模块

~~~
com.quizmate/
    ├── sql/    数据库初始化脚本
    ├── docker/ docker 部署脚本
    │   ├── mysql/
    │   ├── nacos/
    │   ├── nginx/
    │   ├── redis/
    │   ├── quizmate/
    │   └── docker-compose.yml
    ├── pom.xml 项目公共依赖统一管理 root pom
    ├── README.md   项目介绍
    ├── quizmate-ui/    前端 ui 模块
    │   └── package.json
    ├── quizmate-api/   微服务 API 模块
    │   ├── quizmate-api-custom/    业务端 api 接口模块
    │   └── quizmate-api-system/    后台端 api 接口模块
    ├── quizmate-auth/  统一鉴权模块：9200
    ├── quizmate-common/    公共基础服务模块
    │   ├── quizmate-common-core/   核心工具类
    │   ├── quizmate-common-datascope/  数据权限校验
    │   ├── quizmate-common-datasource/ 数据源操作
    │   ├── quizmate-common-log/    日志记录
    │   ├── quizmate-common-redis/  redis 调用
    │   ├── quizmate-common-seata/  seata 分布式事务    
    │   ├── quizmate-common-security/   spring security 权限框架
    │   ├── quizmate-common-sensitive/  数据脱敏过滤
    │   └── quizmate-common-swagger/    项目 API 文档
    ├── quizmate-visual/
    │   └── quizmate-monitor/   系统服务监控模块：9201
    ├── quizmate-gateway/   网关模块：8080
    └── quizmate-modules/   实际业务模块
        ├── quizmate-system/    后台系统模块：9201
        ├── quizmate-gen/   代码生成模块：9202
        ├── quizmate-job/   定时任务模块：9203
        ├── user-service/   用户服务：9204
        ├── search-service/ 搜索服务：9205
        ├── question-service/   题目服务：9206
        ├── questionBank-service/   题库服务：9207
        ├── interaction-service/    互动服务：9208
        ├── mockInterview-service/  AI 面试服务：9209
        └── file-service/   文件服务：9300

~~~

## 架构图
本质上还是基于若依框架的一个项目，所以架构图和若依差不多，只是把原有的单体应用拆分成了多个微服务。

<img src="https://oscimg.oschina.net/oscnet/up-82e9722ecb846786405a904bafcf19f73f3.png"/>

## 内置功能

## 在线体验

## 演示图

