package com.atsk.web;

import com.atsk.pojo.Book;
import com.atsk.pojo.Page;
import com.atsk.service.BookService;
import com.atsk.service.impl.BookServiceImpl;
import com.atsk.util.WebUtil;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Ly
 * @date 2021-07-08 18:06
 */
public class BookServlet extends BaseServlet {

    private BookService dao = new BookServiceImpl();
    // 输入图片的路径
    private static final String IMG_INPUT_PATH = "F:\\bookcityData\\imgInput\\";
    // 图片库路径
    private static final String IMG_PATH = "F:\\bookcityData\\img\\";

    /**
     * 添加图书
     */
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int pageNo = WebUtil.parseInt(request.getParameter("pageNo"), 0);
        pageNo++;
        Book book = WebUtil.copyParamToBean(request.getParameterMap(), new Book());

        // 获取图片的名称
        String imgName = book.getImgName();

        // 保存图片到磁盘中
        writePictureToDisk(imgName);

        dao.addBook(book);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 删除图书
     */
    protected void delBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取要删除图书的编号
        int id = WebUtil.parseInt(request.getParameter("id"), 0);

        // 获取要删除图书对应的图片名
        Book book = dao.getBookById(id);
        String imgName = book.getImgName();

        // 删除操作
        dao.delBook(id);

        // 从图片库中删除图片
        Files.deleteIfExists(Paths.get(IMG_PATH + imgName));

        // 重定向到图书管理列表界面
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="
                + request.getParameter("pageNo"));
    }

    /**
     * 修改图书
     */
    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Book book = WebUtil.copyParamToBean(request.getParameterMap(), new Book());

        String strBookId = request.getParameter("bookId");
        int bookId = Integer.parseInt(strBookId);
        book.setId(bookId);

        String newImg = book.getImgName();
        String oldImg = request.getParameter("oldImg");
        if (newImg != null && newImg != "" && !newImg.equals(oldImg)) {
            // 修改图片
            // 移除原图片
            delImg(oldImg);
            // 保存新图片到磁盘中
            writePictureToDisk(newImg);
        } else {
            // 不修改，保留原图片
            book.setImgName(oldImg);
        }

        dao.updateBook(book);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="
                + request.getParameter("pageNo"));
    }

    /**
     * 根据id查询图书
     */
    protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int id = WebUtil.parseInt(request.getParameter("id"), 0);
        Book book = dao.getBookById(id);
        request.setAttribute("book", book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    /**
     * 查询全部图书
     */
    protected void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 通过BookService 查询所有图书
        List<Book> books = dao.getAllBooks();
        // 把全部图书保存到request 域中
        request.setAttribute("books", books);
        // 请求转发到/pages/manager/book_manager.jsp 页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

    /**
     * 处理分页
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取请求的参数pageNo、pageSize
        int pageNo = WebUtil.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = Page.PAGE_SIZE;
        // 调用 dao.page(pageNo，pageSize)：Page 对象
        Page<Book> page = dao.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

    /**
     * 根据输入图片的路径，将图片保存到书城的图片库中
     */
    protected void writePictureToDisk(String imgName) throws Exception {

        FileInputStream fis = new FileInputStream(IMG_INPUT_PATH + imgName);
        FileOutputStream fos = new FileOutputStream(IMG_PATH + imgName);
        IOUtils.copy(fis, fos);
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(fos);
    }

    /**
     * 根据输入图片的路径，将图片保存到书城的图片库中
     */
    protected void delImg(String imgName) throws Exception {
        // 从图片库中删除图片
        Files.deleteIfExists(Paths.get(IMG_PATH + imgName));
    }
}