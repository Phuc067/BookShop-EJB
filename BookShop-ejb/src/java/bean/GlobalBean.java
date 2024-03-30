/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import model.Book;
import model.CartDetail;
import model.Order;
import model.OrderDetail;
import model.Receipt;

/**
 *
 * @author phucd
 */
@Singleton
@LocalBean
public class GlobalBean {

    public static List<Book> books ;
    public static List<CartDetail> cartDetails;
    public static List<Order> orders;
    public static List< OrderDetail> orderDetails;
    public static List<Receipt> receipts;

    static {
        books = new ArrayList<>();
        books.add(new Book("9780132350", "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", "Prentice Hall", "Programming", 20, 35.5, "image\\cleancode.jpg"));
        books.add(new Book("9780201616", "Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "Addison-Wesley Professional", "Programming", 15, 45.0, "image\\designpatterns.jpg"));
        books.add(new Book("9780135957", "Refactoring: Improving the Design of Existing Code", "Martin Fowler, Kent Beck, John Brant, William Opdyke, Don Roberts", "Addison-Wesley Professional", "Programming", 25, 40.25, "image\\designofexistingcode.jpg"));
        books.add(new Book("9780132764", "The Pragmatic Programmer: Your Journey to Mastery", "Andrew Hunt, David Thomas", "Addison-Wesley Professional", "Programming", 30, 50.75, "image\\pragmaticprogramer.jpg"));
        books.add(new Book("9780596007", "Head First Design Patterns", "Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra", "O'Reilly Media", "Programming", 10, 55.0, "image\\designofexistingcode.jpg"));
        books.add(new Book("9781449316", "Head First Java", "Kathy Sierra, Bert Bates", "O'Reilly Media", "Programming", 35, 60.0, "image\\headfirstjava.jpg"));
        books.add(new Book("9780321125", "Effective Java", "Joshua Bloch", "Addison-Wesley Professional", "Programming", 18, 48.99, "image\\effectivejava.jpg"));
        cartDetails = new ArrayList<>();
        orders = new ArrayList<>();
        orderDetails = new ArrayList<>();
        receipts = new ArrayList<>();
    }

}
