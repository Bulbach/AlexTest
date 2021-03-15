package by.ItAlex.AlexTest.config;

import lombok.SneakyThrows;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @SneakyThrows
    @Bean // jdbc:h2:tcp://localhost/mem:tools
    public Server getServer() {

        return Server.createTcpServer().start();
    }

}
