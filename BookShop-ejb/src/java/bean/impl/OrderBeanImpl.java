/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package bean.impl;

import bean.GlobalBean;
import java.time.Instant;
import javax.ejb.Stateless;
import bean.OrderBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import model.Order;

/**
 *
 * @author phucd
 */
@Stateless
public class OrderBeanImpl implements OrderBean {

    @EJB
    private GlobalBean globalBean;

    @Override
    public int insert(String name, String address, String phone, Instant createDate, String status) {
        try {
            int id = getMaxId() + 1;
            Order order = new Order(id, createDate, name, address, phone, status);
            globalBean.orders.add(order);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;

        }

    }

    @Override
    public List<Order> findAll() {
        return globalBean.orders;
    }

    private int getMaxId() {
        int maxId = 0;
        int size = globalBean.orders.size();
        if (size != 0) {
            maxId = globalBean.orders.get(size - 1).getId();
        }
        return maxId;
    }

    @Override
    public void updateStatus(int orderId, String status) {
        int index = getIndexOf(orderId);
        globalBean.orders.get(index).setStatus(status);
    }

    private int getIndexOf(int orderId) {
        for (int i = 0; i < globalBean.orders.size(); i++) {
            if (globalBean.orders.get(i).getId() == orderId) {
                return i;
            }
        }
        return -1;
    }
}
