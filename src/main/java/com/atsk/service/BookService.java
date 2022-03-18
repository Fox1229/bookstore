package com.atsk.service;

import com.atsk.pojo.Book;
import com.atsk.pojo.Page;

import java.util.List;

/**
 * @author Ly
 * @date 2021-07-08 16:31
 */
public interface BookService {
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
     * 根据图书名分页查询图书信息
     */
    Page<Book> getBookByBookName(String bookName, int pageNo, int pageSize);

    /**
     * 查询所有的图书信息
     */
    List<Book> getAllBooks();

    /**
     * 查询每一页显示的数据
     */
    Page<Book> page(int pageNo, int pageSize);

    /**
     * 根据价格查询图书信息
     */
    Page<Book> pageByPriceScope(int pageNo, int pageSize, double minPrice, double maxPrice);

    /**
     * 查询最高价格的图书
     */
    int getBookByMaxPrice();

}
