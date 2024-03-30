/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package bean.impl;

import bean.GlobalBean;
import static bean.GlobalBean.books;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Book;
import model.CartDetail;
import bean.BookBean;

/**
 *
 * @author phucd
 */
@Stateless
public class BookBeanImpl implements BookBean {
    
    @EJB
    private GlobalBean globalBean;
    
    @Override
    public List<Book> findAll() {
        List<Book> books = globalBean.books;
        return books;
    }
    @Override
    public Book findById(String id) {
         for (Book book : globalBean.books) {
            if (book.getId().equals(id)) {
                return book; 
            }
        }
        return null; 
    }

    @Override
    public void updateBookQuantity(String id, int quantity) {
        int index = getIndexById(id);
       globalBean.books.get(index).setQuantity(quantity);
    }
    
    private int getIndexById(String id)
    {
        Book book = findById(id);
        return globalBean.books.indexOf(book);
    }

  

    
   
    
}
