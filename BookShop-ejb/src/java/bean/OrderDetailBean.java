/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package bean;

import java.util.List;
import javax.ejb.Local;
import model.OrderDetail;

/**
 *
 * @author phucd
 */
@Local
public interface OrderDetailBean {

    List<OrderDetail> findAll();
    List<OrderDetail> findByOrderId(int orderId);
    void insert(int orderId, String bookId, int quantity, Double price);

}
