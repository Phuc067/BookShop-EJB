/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package bean;

import dto.CartDetailDto;
import java.util.List;
import javax.ejb.Local;
import model.Book;
import model.CartDetail;

/**
 *
 * @author phucd
 */
@Local
public interface CartBean {
    List<CartDetail> findAll();
    void insert(String bookId, int quantity);
    int update(String bookId, int quantity);
    int delete(int id);
    int existBookById(String id);
    List<CartDetailDto> getALlDetail();
    CartDetail findById(int id);
}
