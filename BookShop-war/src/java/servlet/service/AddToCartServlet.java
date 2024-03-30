/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import bean.BookBean;
import bean.CartBean;
import java.util.List;
import model.CartDetail;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    @EJB
    private CartBean cartBean;

    @EJB
    private BookBean bookBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Book book = bookBean.findById(bookId);

        int existQuantity = 0;
        List<CartDetail> cartDetails = cartBean.findAll();
        int index = cartBean.existBookById(bookId);
        if (index != -1) {
            existQuantity = cartDetails.get(index).getQuantity();
        }
        if (quantity + existQuantity > book.getQuantity()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int cartItemCount = cartDetails.size();
        if (index == -1) {
            cartBean.insert(bookId, quantity);
            cartItemCount +=1;
            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            cartBean.update(bookId, quantity + existQuantity);
        }

        String responseMessage = Integer.toString(cartItemCount);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(responseMessage);
        }
    }
}
