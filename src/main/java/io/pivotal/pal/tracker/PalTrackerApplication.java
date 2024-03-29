package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PalTrackerApplication {


    @Bean
    public TimeEntryRepository getTimeEntryRepository(DataSource dataSource){
//
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setUrl(System.getenv("SPRING_DATASOURCE_URL"));

        return  new JdbcTimeEntryRepository(dataSource);
    }

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }


}
