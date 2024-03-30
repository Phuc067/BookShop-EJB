/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package bean;

import java.time.Instant;
import java.util.List;
import javax.ejb.Local;
import model.Order;

/**
 *
 * @author phucd
 */
@Local
public interface OrderBean {
    int insert(String name, String address, String phone, Instant createDate, String status);
    List<Order> findAll();
    void updateStatus(int orderId, String status);
}
