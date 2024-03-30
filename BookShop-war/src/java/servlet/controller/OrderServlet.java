/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.controller;

import bean.BookBean;
import bean.CartBean;
import bean.OrderBean;
import bean.OrderDetailBean;
import dto.CartDetailDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mapper.CartDetailMapper;
import model.Book;
import model.CartDetail;
import model.OrderDetail;

/**
 *
 * @author phucd
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    @EJB
    private BookBean bookBean;

    @EJB
    private CartBean cartBean;

    @EJB
    private OrderBean orderBean;

    @EJB
    private OrderDetailBean orderDetailBean;

    @EJB
    private CartDetailMapper cartDetailMapper;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int orderId = -1;
            Double total = 0.0;
            String paymentMethod = request.getParameter("paymentMethod");
            if (request.getParameter("orderId")!=null) {
                orderId = Integer.parseInt(request.getParameter("orderId"));
                List<OrderDetail> orderDetails = orderDetailBean.findByOrderId(orderId);
                for (OrderDetail orderDetail : orderDetails) {
                    total += orderDetail.getPrice() * orderDetail.getQuantity();
                }
            } else {
                List<Integer> cartDetailsId = getListId(request);
                String recipientName = request.getParameter("recipientName");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                Instant now = Instant.now();
                try {
                    if (paymentMethod.equals("cod")) {
                        orderId = orderBean.insert(recipientName, address, phone, now, "Thanh toan khi nhan hang");
                    } else if (paymentMethod.equals("bank_transfer")) {
                        orderId = orderBean.insert(recipientName, address, phone, now, "Chua chuyen khoan");
                    }
                    if (orderId == -1) {
                        return;
                    }
                    // Lấy thông tin sách đã chọn(price)
                    List<CartDetailDto> cartDetailDtos = new ArrayList<>();
                    if (cartDetailsId != null) {
                        List<CartDetail> cartDetails = new ArrayList<>();
                        for (int i : cartDetailsId) {
                            CartDetail cartDetail = cartBean.findById(i);
                            if (cartDetail != null) {
                                cartDetails.add(cartDetail);
                            }
                        }
                        cartDetailDtos = cartDetailMapper.modelToDtos(cartDetails);
                    }

                    for (CartDetailDto cartDetailDto : cartDetailDtos) {
                        orderDetailBean.insert(orderId, cartDetailDto.getBookId(), cartDetailDto.getQuantity(), cartDetailDto.getPrice());
                        total += cartDetailDto.getPrice() * cartDetailDto.getQuantity();
                    }
                    for (CartDetailDto cartDetailDto : cartDetailDtos) {
                        Book book = bookBean.findById(cartDetailDto.getBookId());
                        int newQuantity = book.getQuantity() - cartDetailDto.getQuantity();
                        bookBean.updateBookQuantity(cartDetailDto.getBookId(), newQuantity);
                    }
                    for (int i : cartDetailsId) {
                        cartBean.delete(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            request.setAttribute("paymentMethod", paymentMethod);
            if (paymentMethod.equals("bank_transfer")) {
                request.setAttribute("total", total);
            }
            request.setAttribute("orderId", orderId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/orderSuccess.jsp");
            dispatcher.forward(request, response);
        }
    }

    private List<Integer> getListId(HttpServletRequest request) {
        String selectedProductsString = request.getParameter("selectedProducts");
        if (selectedProductsString == null) {
            return null;
        }
        String[] idStrings = selectedProductsString.split(",");
        List<Integer> cartDetailsId = new ArrayList<>();
        for (String idString : idStrings) {
            cartDetailsId.add(Integer.parseInt(idString));
        }
        return cartDetailsId;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
