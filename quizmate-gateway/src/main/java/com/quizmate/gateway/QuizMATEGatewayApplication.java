package com.quizmate.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <h3>quizmate-backend</h3>
 * <p>网关启动类</p>
 *
 * @author moru
 * @since 2025-09-15 23:58
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class QuizMATEGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuizMATEGatewayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  面试伙伴网关启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "   ___    _   _   _____  ___   __  __      _      _____   _____ \n" +
                "  / _ \\  | | | | |__  / |_ _| |  \\/  |    / \\    |_   _| | ____|\n" +
                " | | | | | | | |   / /   | |  | |\\/| |   / _ \\     | |   |  _|  \n" +
                " | |_| | | |_| |  / /_   | |  | |  | |  / ___ \\    | |   | |___ \n" +
                "  \\__\\_\\  \\___/  /____| |___| |_|  |_| /_/   \\_\\   |_|   |_____|\n" +
                "                                 ");
    }
}
