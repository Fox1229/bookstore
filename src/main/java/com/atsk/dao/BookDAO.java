package com.atsk.dao;

import com.atsk.pojo.Book;

import java.util.List;

/**
 * @author Ly
 * @date 2021-07-08 16:31
 */
public interface BookDAO {

    /**
     * 添加图书
     */
    void addBook(Book book);

    /**
     * 删除图书
     */
    void delBook(Integer id);

    /**
     * 修改图书信息
     */
    void updateBook(Book book);

    /**
     * 根据 id 查询一条图书信息
     */
    Book getBookById(Integer id);

    /**
     * 根据图书名查询图书信息
     */
    Long getBookByBookName(String bookName);

    /**
     * 根据图书名分页查询图书信息
     */
    List<Book> getBookByBookName(String bookName, int begin, int pageSize);

    /**
     * 查询所有的图书信息
     */
    List<Book> getAllBooks();

    /**
     * 返回Book中指定目录的个数
     */
    Long getCount();

    /**
     * 返回分页后每一页显示的数据
     */
    List<Book> getItems(int begin, int pageSize);

    /**
     * 查询价格区间内的书本数量
     *
     * @param minPrice 最低价格
     * @param maxPrice 最高价格
     */
    Long getForPageTotalCount(double minPrice, double maxPrice);

    /**
     * 查询价格区间内当前页数据
     */
    List<Book> getForPageItems(double minPrice, double maxPrice, int begin, int pageSize);

    /**
     * 查询最高价格的图书
     */
    int getBookByMaxPrice();
}
