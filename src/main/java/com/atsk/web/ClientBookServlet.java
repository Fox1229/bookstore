package com.atsk.web;

import com.atsk.pojo.Book;
import com.atsk.pojo.Page;
import com.atsk.service.BookService;
import com.atsk.service.impl.BookServiceImpl;
import com.atsk.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ly
 * @date 2021-07-10 15:02
 */
public class ClientBookServlet extends BaseServlet {

    private BookService dao = new BookServiceImpl();
    private String tmpBookName = null;

    /**
     * 处理分页
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int pageNo = WebUtil.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = Page.PAGE_SIZE;

        // 调用 dao.page(pageNo，pageSize)：Page 对象
        Page<Book> page = dao.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");

        // 将对象保存到request域中
        request.setAttribute("page", page);
        request.setAttribute("location", "page");

        // 请求转发到pages/client/index.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    /**
     * 根据图书名查询图书
     */
    protected void getBookByBookName(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String bookName = request.getParameter("bookName");

        // 判断是否能够获取到书名，若书名为null则使用上次获取的书名
        if (bookName != null) {
            tmpBookName = bookName;
        } else {
            bookName = tmpBookName;
        }

        int pageNo = WebUtil.parseInt(request.getParameter("pageNo"), 1);
        Page<Book> page = dao.getBookByBookName(bookName, pageNo, Page.PAGE_SIZE);

        // 没有找到图书信息，跳转到emptyBook.jsp，提示用户
        if (page == null || page.getPageTotal() == 0) {
            request.setAttribute("bookName", bookName);
            request.getRequestDispatcher("/pages/client/emptyBook.jsp").forward(request, response);
            return;
        }

        // 解决查询后分页跳转进入page的问题
        StringBuilder sb = new StringBuilder("client/bookServlet?action=getBookByBookName");
        sb.append("&bookName=").append(bookName);
        page.setUrl(sb.toString());

        StringBuilder location = new StringBuilder("getBookByBookName");
        if (bookName != null) {
            sb.append("&bookName=").append(bookName);
            request.setAttribute("location", location);
        }

        request.setAttribute("bookName", bookName);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    /**
     * 处理价格区间的分页
     */
    protected void getItemsByPriceScope(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 若不输入价格区间，跳转到首页
        if ("".equals(request.getParameter("min")) && "".equals(request.getParameter("max"))) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        int pageNo = WebUtil.parseInt(request.getParameter("pageNo"), 1);

        double minPriceDouble = WebUtil.parseDouble(request.getParameter("min"), 0);
        int minPrice = (int) minPriceDouble;
        if (minPrice < 0) {
            minPrice = 0;
        }

        // 查询价格最高的图书。若用户不输入或输入错误，则maxPrice默认查询价格最高的图书
        int priceInt = dao.getBookByMaxPrice();
        double maxPriceDouble = WebUtil.parseDouble(request.getParameter("max"), priceInt + 1);
        int maxPrice = (int) maxPriceDouble;

        // 若用户输入的最小查询价格大于最大查询价格，
        if (minPrice > maxPrice) {
            request.getRequestDispatcher("/pages/client/emptyBook.jsp").forward(request, response);
            return;
        }

        // 查询价格区间内的图书
        Page<Book> page = dao.pageByPriceScope(pageNo, Page.PAGE_SIZE, minPrice, maxPrice);

        // 没有找到图书信息，跳转到emptyBook.jsp，提示用户
        if (page == null) {
            request.getRequestDispatcher("/pages/client/emptyBook.jsp").forward(request, response);
            return;
        }

        // 解决查询后分页跳转进入page的问题
        StringBuilder sb = new StringBuilder("client/bookServlet?action=getItemsByPriceScope");
        // 如果有最小、最大价格的参数，添加到分页条的地址参数中
        if (!(request.getParameter("min") == null && request.getParameter("max") == null)) {
            sb.append("&min=").append(minPrice);
            sb.append("&max=").append(maxPrice);
            page.setUrl(sb.toString());
        }
        StringBuilder location = new StringBuilder("getItemsByPriceScope");
        if (!(request.getParameter("min") == null && request.getParameter("max") == null)) {
            location.append("&min=").append(minPrice);
            location.append("&max=").append(maxPrice);
            request.setAttribute("location", location);
        }

        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }
}