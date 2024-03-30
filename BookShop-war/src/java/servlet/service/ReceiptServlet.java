/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.service;

import bean.OrderBean;
import bean.ReceiptBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ReceiptServlet", urlPatterns = {"/ReceiptServlet"})
public class ReceiptServlet extends HttpServlet {

    @EJB
    private ReceiptBean receiptBean;
    
    @EJB
    private OrderBean orderBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String orderIdString = request.getParameter("orderId");
            int orderId = -1;
            if (orderIdString != null) {
                orderId = Integer.parseInt(orderIdString);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            if (receiptBean.existByOrderId(orderId)) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
            } else {
                String totalString = request.getParameter("total");
                double total = 0;
                if (totalString != null) {
                    total = Double.parseDouble(totalString);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
                Instant now = Instant.now();
                receiptBean.insert(orderId, now, total, "Banking");
                orderBean.updateStatus(orderId, "Da thanh toan");
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
