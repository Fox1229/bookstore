package com.atsk.test;

import com.atsk.pojo.Book;
import com.atsk.pojo.Page;
import com.atsk.service.impl.BookServiceImpl;
import com.atsk.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ly
 * @date 2021-07-08 17:47
 */
public class Test05_BookServiceTest {
    private BookServiceImpl dao = new BookServiceImpl();

    @Test
    public void testGetBookByName() {

        Page<Book> page = dao.getBookByBookName("王子", 1, 4);
        System.out.println(page);
    }

    @Test
    public void test(){

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
//        for (String s : list) {
//
//            System.out.println(s);
//        }
    }

    @Test
    public void testGetMaxPrice(){
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            System.out.println(dao.getBookByMaxPrice());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(connection);
        }
    }

    @Test
    public void testPageByPriceScope(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Page<Book> page = dao.pageByPriceScope(1, 4, 50, 20);
            System.out.println(page);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }

    @Test
    public void testPage(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Page<Book> page = dao.page(1, Page.PAGE_SIZE);
            System.out.println(page);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }

    @Test
    public void testGetBooks(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<Book> list = dao.getAllBooks();
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }


    @Test
    public void testGetBookById(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Book book = dao.getBookById(18);
            System.out.println(book);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }


    @Test
    public void testUpdateBook(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            dao.updateBook(new Book(22,"编程",30.0,"沈从文",10000,200,null));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }


    @Test
    public void testDelBook(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            dao.delBook(21);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }


    @Test
    public void testAddBook(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Book book = new Book("边城",30.0,"沈从文",50000,2000,null);
            dao.addBook(book);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }
}
