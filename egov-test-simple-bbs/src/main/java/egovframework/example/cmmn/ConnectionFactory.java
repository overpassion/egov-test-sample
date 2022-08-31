package egovframework.example.cmmn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ConnectionFactory {

    private static interface Singleton {
       ///final ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    private final DataSource dataSource;

    private ConnectionFactory() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/example");
        dataSource.setUsername("example");
        dataSource.setPassword("example01");

        this.dataSource = dataSource;

        /*GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                "jdbc:mysql://example.org:3306/exampleDb", properties
        );
        new PoolableConnectionFactory(connectionFactory, pool, null, "SELECT 1", 3, false, false, Connection.TRANSACTION_READ_COMMITTED);
        this.dataSource = new PoolingDataSource(pool);*/
        
        
        
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return null; ////Singleton.INSTANCE.dataSource.getConnection();
    }
}
