package es.ieslavereda.prgproject2223.model;
import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;

public class MyDataSource {

    public static DataSource getMyDataSource() {
        MysqlDataSource mySQL = new MysqlDataSource();
        mySQL.setURL("jdbc:mysql://localhost:3306/java");
        mySQL.setUser("xrosillo");
        mySQL.setPassword("Bl@nca2023");
        return mySQL;
    }

}
