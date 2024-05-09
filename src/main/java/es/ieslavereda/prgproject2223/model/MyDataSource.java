package es.ieslavereda.prgproject2223.model;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyDataSource {

    @Bean
    public static DataSource getMyDataSource() {
        MysqlDataSource mySQL = new MysqlDataSource();
        mySQL.setURL("jdbc:mysql://localhost:3306/java");
        mySQL.setUser("xavier");
        mySQL.setPassword("1234");
        return mySQL;
    }

}
