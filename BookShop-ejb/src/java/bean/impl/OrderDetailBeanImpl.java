/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package bean.impl;

import bean.GlobalBean;
import javax.ejb.Stateless;
import model.OrderDetail;
import bean.OrderDetailBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author phucd
 */
@Stateless
public class OrderDetailBeanImpl implements OrderDetailBean {

    @EJB
    private GlobalBean globalBean;

    
    @Override
    public List<OrderDetail> findAll()
    {
        return globalBean.orderDetails;
    }
    
    @Override
    public List<OrderDetail> findByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        if (globalBean.orderDetails != null) {
            for (OrderDetail orderDetail : globalBean.orderDetails) {
                if (orderDetail.getOrderId() == orderDetail.getOrderId()) {
                    orderDetails.add(orderDetail);
                }
            }
        }
        return orderDetails;
    }

    @Override
    public void insert(int orderId, String bookId, int quantity, Double price) {
        int id = getMaxId() +1;
        OrderDetail orderDetail = new OrderDetail(id ,orderId, bookId, quantity, price);
        globalBean.orderDetails.add(orderDetail);
    }
    
    private int getMaxId()
    {
         int maxId = 0;
        int size = globalBean.orderDetails.size();
        if (size != 0) {
            maxId = globalBean.orderDetails.get(size - 1).getId();
        }
        return maxId;
    }
}
