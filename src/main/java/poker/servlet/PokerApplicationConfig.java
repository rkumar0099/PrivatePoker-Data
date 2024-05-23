package poker.servlet;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class PokerApplicationConfig {

    @Bean
    public DataSource mysqlDataSource() {
        String hostname = null;
        String username = null;
        String password = null;
        try(InputStream is = PokerApplicationConfig.class.getResourceAsStream("/private.txt")) {
            BufferedReader reader= new BufferedReader(new InputStreamReader(is));
            String line = null;
            while((line = reader.readLine()) != null) {
                String[] tokens = line.split("=");
                System.out.printf("%s, %s\n", tokens[0], tokens[1]);
                switch (tokens[0]) {
                    case "hostname":
                        hostname = tokens[1];
                        break;
                    
                    case "username":
                        username = tokens[1];
                        break;
                    
                    case "password":
                        password = tokens[1];
                        break;
                    
                    default:
                        break;
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("%s, %s, %s", hostname, username, password);
        if (hostname == null || username == null || password == null) {
            return null;
        }
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://%s:3306/poker", hostname));
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    
    }

    @Bean
    public JdbcTemplate mysqlConnection(){
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqlDataSource());
            return jdbcTemplate;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }    


}