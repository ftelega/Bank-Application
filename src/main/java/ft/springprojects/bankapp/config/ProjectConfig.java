package ft.springprojects.bankapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.format.DateTimeFormatter;

@Configuration
public class ProjectConfig {

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager();
    }

    @Bean
    public DateTimeFormatter dateTimeFormatter(){
        String pattern = "yyyy-MM-dd";
        return DateTimeFormatter.ofPattern(pattern);
    }
}
