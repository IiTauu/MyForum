package server.repository.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import server.repository.manager.exception.DuplicateException;
import server.repository.manager.exception.NotFoundException;
import server.repository.model.ForumData;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class TableManager<T extends ForumData> {
    protected static final String url = "jdbc:sqlite:./src/main/java/server/repository/db/forum.db";
    // 整个服务端一个连接池，所有数据库操作都放入连接池
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setMaximumPoolSize(10);  // 设置连接池的最大连接数
        dataSource = new HikariDataSource(config);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (dataSource != null) {
                dataSource.close();
            }
        }));
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public abstract int add(T newData) throws DuplicateException;

    public abstract boolean update(T newData)throws DuplicateException, NotFoundException;

    public abstract boolean delete(int id);

    public abstract T get(int id) throws NotFoundException;
}
