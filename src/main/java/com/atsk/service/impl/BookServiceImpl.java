package com.atsk.service.impl;

import com.atsk.dao.BookDAO;
import com.atsk.dao.impl.BookDAOImpl;
import com.atsk.pojo.Book;
import com.atsk.pojo.Page;
import com.atsk.service.BookService;

import java.util.List;

/**
 * @author Ly
 * @date 2021-07-08 17:31
 */
public class BookServiceImpl implements BookService {

    private BookDAO dao = new BookDAOImpl();

    @Override
    public void addBook(Book book) {
        dao.addBook(book);
    }

    @Override
    public void delBook(Integer id) {
        dao.delBook(id);
    }

    @Override
    public void updateBook(Book book) {
        dao.updateBook(book);
    }

    @Override
    public Book getBookById(Integer id) {
        return dao.getBookById(id);
    }

    @Override
    public Page<Book> getBookByBookName(String bookName, int pageNo, int pageSize) {

        Page<Book> page = new Page<>();

        // 设置显示数量
        page.setPageSize(pageSize);

        // 查询所有包含该关键字的图书的个数
        Long countLong = dao.getBookByBookName(bookName);
        int count = Math.toIntExact(countLong);
        if (count == 0) {
            return null;
        }

        // 设置总记录数
        page.setPageTotalCount(count);

        // 设置总页码
        int pageTotal = count / pageSize;
        if (count % pageSize > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        //设置数据的有效边界
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }

        // 设置当前页码
        page.setPageNo(pageNo);

        // 设置当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;

        // 设置当前页数据
        List<Book> items = dao.getBookByBookName(bookName, begin, pageSize);
        page.setItems(items);

        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {

        Page<Book> page = new Page<>();
        // 设置显示数量
        page.setPageSize(pageSize);

        // 设置总记录数
        Long countLong = dao.getCount();
        int count = Math.toIntExact(countLong);
        page.setPageTotalCount(count);

        // 设置总页码
        int pageTotal = Math.toIntExact(count / pageSize);
        if (count % pageSize > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        //设置数据的有效边界
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        // 设置当前页码
        page.setPageNo(pageNo);

        // 设置当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;

        // 设置当前页数据
        List<Book> items = dao.getItems(begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPriceScope(int pageNo, int pageSize, double minPrice, double maxPrice) {

        Page<Book> page = new Page<>();

        // 设置显示数量
        page.setPageSize(pageSize);

        //查询价格区间内的总记录数
        Long countLong = dao.getForPageTotalCount(minPrice, maxPrice);
        int count = Math.toIntExact(countLong);

        // 设置总记录数
        page.setPageTotalCount(count);

        // 设置总页码
        int pageTotal = count / pageSize;
        if (count % pageSize > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        //设置数据的有效边界
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }

        // 设置当前页码
        page.setPageNo(pageNo);

        // 设置当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;

        List<Book> list = null;
        try {
            list = dao.getForPageItems(minPrice, maxPrice, begin, pageSize);
        } catch (Exception e) {
            return null;
        }

        // 设置当前页数据
        page.setItems(list);
        return page;
    }

    @Override
    public int getBookByMaxPrice() {
        return dao.getBookByMaxPrice();
    }
}
