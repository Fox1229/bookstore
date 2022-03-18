package com.atsk.dao;

import com.atsk.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

/**
 * @author Ly
 * @date 2021-06-03 12:37
 * 针对于数据表的通用的操作
 */
public abstract class BaseDAO<T> {

    private Class<T> clazz;
    private QueryRunner runner;

    {
        runner = new QueryRunner();
        //获取当前BaseDAO的子类继承的父类中的泛型
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;

        //获取了父类的泛型参数
        Type[] typeArguments = paramType.getActualTypeArguments();
        //泛型的第一个参数
        clazz = (Class<T>) typeArguments[0];
    }

    /**
     * 通用的增删改操作
     *
     * @param args:填充占位符的可变参数
     */
    public void update(String sql, Object... args) {

        try {
            Connection conn = JdbcUtil.getConnection();
            runner.update(conn, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 通用的查询操作，用于返回数据表中的一条记录
     */
    public T getInstance(String sql, Object... args) {

        try {
            Connection conn = JdbcUtil.getConnection();
            return runner.query(conn, sql, new BeanHandler<>(clazz), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 通用的查询操作，用于返回数据表中的多条记录构成的集合
     */
    public List<T> getForList(String sql, Object... args) {

        try {
            Connection conn = JdbcUtil.getConnection();
            return runner.query(conn, sql, new BeanListHandler<>(clazz), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 用于查询特殊值的通用的方法
     */
    public <E> E getValue(String sql, Object... args) {

        try {
            Connection conn = JdbcUtil.getConnection();
            return (E) runner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
