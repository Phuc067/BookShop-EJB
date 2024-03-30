/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package bean.impl;

import bean.GlobalBean;
import javax.ejb.Stateless;
import bean.ReceiptBean;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import model.Receipt;

/**
 *
 * @author phucd
 */
@Stateless
public class ReceiptBeanImpl implements ReceiptBean {

    @EJB
    private GlobalBean globalBean;
    
    @Override
    public List<Receipt> findAll() {
        return  globalBean.receipts;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void insert(int orderId, Instant createDate, double price, String paymentMethod) {
        int id = getMaxId() + 1;
        Receipt receipt = new Receipt(id, orderId, createDate, price, paymentMethod);
        globalBean.receipts.add(receipt);
    }
    
    private int getMaxId()
    {
         int maxId = 0;
        int size = globalBean.receipts.size();
        if (size != 0) {
            maxId = globalBean.receipts.get(size - 1).getId();
        }
        return maxId;
    }

    @Override
    public Receipt findById(int i) {
        for(Receipt receipt: globalBean.receipts)
        {
            if(receipt.getId() == i)
            {
                return receipt;
            }
        }
        return null;
    }

    @Override
    public Boolean existByOrderId(int orderId) {
         for(Receipt receipt: globalBean.receipts)
        {
            if(receipt.getOrderId()== orderId)
            {
                return true;
            }
        }
        return false;
    }
}
