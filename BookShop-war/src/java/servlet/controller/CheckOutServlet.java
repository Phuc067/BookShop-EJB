/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import bean.impl.BookBeanImpl;
import bean.BookBean;
import bean.CartBean;
import bean.OrderBean;
import bean.OrderDetailBean;
import dto.CartDetailDto;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import mapper.CartDetailMapper;
import model.CartDetail;

@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    @EJB
    private CartBean cartBean;

    @EJB
    private CartDetailMapper cartDetailMapper;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Integer> cartDetailsId = getListId(request);
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
        try {
            double totalValue = 0;
            for (CartDetailDto productDetail : cartDetailDtos) {
                totalValue += productDetail.getQuantity() * productDetail.getPrice();
            }
            request.setAttribute("totalValue", totalValue);
            request.setAttribute("productDetails", cartDetailDtos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/checkOut.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
