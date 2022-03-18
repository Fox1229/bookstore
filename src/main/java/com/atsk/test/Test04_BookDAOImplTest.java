package com.atsk.test;

import com.atsk.dao.impl.BookDAOImpl;
import com.atsk.pojo.Book;
import com.atsk.pojo.Page;
import com.atsk.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.List;

/**
 * @author Ly
 * @date 2021-07-08 16:44
 */
public class Test04_BookDAOImplTest {
    private BookDAOImpl dao = new BookDAOImpl();

    @Test
    public void testGetBookByName1() {

        Long size = dao.getBookByBookName("王子");
        System.out.println(size);
    }

    @Test
    public void testGetBookByName() {

        List<Book> list = dao.getBookByBookName("王子", 0, 5);
        list.forEach(System.out::println);
    }

    @Test
    public void testParseInt() {
        String str = "200.9";
        double strDouble = Double.parseDouble(str);
        int i = (int) strDouble;
        System.out.println(i);
    }

    @Test
    public void testGetBookByMaxPrice() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            int price = dao.getBookByMaxPrice();
            System.out.println(price);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }

    @Test
    public void testGetForPageItems() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<Book> list = dao.getForPageItems(50, 20, 0, 4);
            if (list.size() == 0) {
                System.out.println("没有用户信息");
            } else {
                list.forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }

    @Test
    public void testGetItemsByPriceScope() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Long count = dao.getForPageTotalCount(50, 100);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }

    @Test
    public void testGetItems() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<Book> list = dao.getItems(1, 4);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }

    @Test
    public void testGetCount() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Long count = dao.getCount();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }


    @Test
    public void testGetBooks() {
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
    public void testGetBookById() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            int id = 5;
            Book book = dao.getBookById(id);
            System.out.println(book);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }


    @Test
    public void testUpdateBook() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Book book = new Book(21, "abc", 250.0, "赵来运", 1000, 5, "bbb");
            dao.updateBook(book);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }


    @Test
    public void testDelBook() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            int id = 6;
            dao.delBook(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }


    @Test
    public void testAddBook() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Book book = new Book("abc", 250.0, "赵来运", 1000, 5, "aa");
            dao.addBook(book);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }
}
