package com.atsk.dao.impl;

import com.atsk.dao.BaseDAO;
import com.atsk.dao.BookDAO;
import com.atsk.pojo.Book;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ly
 * @date 2021-07-08 16:37
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {

    @Override
    public void addBook(Book book) {
        String sql = "insert into t_book(name,price,author,sales,stock,img_name) values(?,?,?,?,?,?)";
        update(sql, book.getName(), book.getPrice(), book.getAuthor()
                , book.getSales(), book.getStock(), book.getImgName());
    }

    @Override
    public void delBook(Integer id) {
        String sql = "delete from t_book where id = ?";
        update(sql, id);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "update t_book set name = ?,price = ?,author = ?,sales = ?,stock = ?,img_name = ? where id = ?";
        update(sql, book.getName(), book.getPrice(), book.getAuthor()
                , book.getSales(), book.getStock(), book.getImgName(), book.getId());
    }

    @Override
    public Book getBookById(Integer id) {
        String sql = "select id,name,price,author,sales,stock,img_name imgName from t_book where id = ?";
        return getInstance(sql, id);
    }

    @Override
    public Long getBookByBookName(String bookName) {
        String sql = "select count(*) from t_book where name like \"%\"?\"%\"";
        return getValue(sql, bookName);
    }

    @Override
    public List<Book> getBookByBookName(String bookName, int begin, int pageSize) {
        String sql = "select id,name,price,author,sales,stock,img_name imgName from t_book where name like \"%\"?\"%\" limit ?, ?";
        return getForList(sql, bookName, begin, pageSize);
    }

    @Override
    public List<Book> getAllBooks() {
        String sql = "select id,name,price,author,sales,stock,img_name imgName from t_book";
        return getForList(sql);
    }

    @Override
    public Long getCount() {
        String sql = "select count(*) from t_book";
        return getValue(sql);
    }

    @Override
    public List<Book> getItems(int begin, int pageSize) {
        String sql = "select id,name,price,author,sales,stock,img_name imgName from t_book limit ?,?";
        return getForList(sql, begin, pageSize);
    }

    @Override
    public Long getForPageTotalCount(double minPrice, double maxPrice) {
        String sql = "select count(*) from t_book where price >= ? && price <= ?";
        return getValue(sql, minPrice, maxPrice);
    }

    @Override
    public List<Book> getForPageItems(double minPrice, double maxPrice, int begin, int pageSize) {

        String sql = "SELECT id,name,price,author,sales,stock,img_name imgName " +
                "FROM t_book WHERE price BETWEEN ? AND ? ORDER BY price LIMIT ?,?";
        return getForList(sql, minPrice, maxPrice, begin, pageSize);
    }

    @Override
    public int getBookByMaxPrice() {
        String sql = "SELECT MAX(price) FROM t_book";
        Object value = getValue(sql);
        BigDecimal bigDecimal = (BigDecimal) value;
        return bigDecimal.intValue();
    }
}
