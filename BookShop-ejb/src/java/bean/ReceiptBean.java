/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package bean;

import java.time.Instant;
import java.util.List;
import javax.ejb.Local;
import model.Receipt;

/**
 *
 * @author phucd
 */
@Local
public interface ReceiptBean {
    List<Receipt> findAll();
    void insert(int orderId, Instant createDate, double price, String paymentMethod);
    Receipt findById(int id);
    Boolean existByOrderId(int orderId);
}
