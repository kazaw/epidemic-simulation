package kacper.example.epidemicsimulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EpidemicSimulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpidemicSimulationApplication.class, args);
    }

    @Bean(name = "webExecutor")
    public Executor asyncWebExecutor(){
        ThreadPoolTaskExecutor webExecutor = new ThreadPoolTaskExecutor();
        webExecutor.setCorePoolSize(5);
        webExecutor.setMaxPoolSize(5);
        webExecutor.setQueueCapacity(600);
        webExecutor.setThreadNamePrefix("webService-");
        webExecutor.initialize();
        return webExecutor;
    }

}

