package com.atsk.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Ly
 * @date 2021-07-08 10:46
 */
public class JdbcUtil {

    private static DataSource source;
    private static ThreadLocal<Connection> connections = new ThreadLocal<>();

    static {
        Properties pros = new Properties();
        try {
            // 加载配置文件
            pros.load(JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            // 创建线程池
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {

        Connection conn = connections.get();
        if (conn == null) {
            try {
                //从数据库连接池中获取连接
                conn = source.getConnection();
                // 保存到ThreadLocal对象中，供后面的jdbc操作使用
                connections.set(conn);
                // 设置为手动管理事务
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose() {

        Connection conn = connections.get();
        // 如果不等于null，说明之前使用过连接，操作过数据库
        if (conn != null) {
            try {
                conn.commit(); // 提交 事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DbUtils.closeQuietly(conn);
            }
        }
        // 将连接从当前线程中移除
        connections.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose() {

        // 获取当前线程中的连接
        Connection conn = connections.get();

        // 如果不等于null，说明之前使用过连接，操作过数据库
        if (conn != null) {
            try {
                // 提交事务
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DbUtils.closeQuietly(conn);
            }
        }
        // 将连接从当前线程中移除
        connections.remove();
    }

    /**
     * 关闭数据库连接
     */
    public static void closeResource(Connection conn) {
        DbUtils.closeQuietly(conn);
    }
}
