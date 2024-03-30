/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package bean;

import java.time.Instant;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import model.Book;

/**
 *
 * @author phucd
 */
@Local
public interface BookBean {
    List<Book> findAll(); 
    Book findById(String id);
    
    void updateBookQuantity(String id,int quantity);
    
    
}
