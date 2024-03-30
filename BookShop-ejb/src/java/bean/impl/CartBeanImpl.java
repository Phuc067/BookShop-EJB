/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package bean.impl;

import bean.GlobalBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.CartDetail;
import bean.CartBean;
import dto.CartDetailDto;
import java.util.ArrayList;
import java.util.Map;
import mapper.CartDetailMapper;
import model.Book;

@Stateless
public class CartBeanImpl implements CartBean {

    @EJB
    private GlobalBean globalBean;

    @EJB
    private CartDetailMapper cartDetailMapper;

    @Override
    public void insert(String bookId, int quantity) {
            int id = getMaxId() + 1;
            CartDetail cartDetail = new CartDetail(id, bookId, quantity);
            globalBean.cartDetails.add(cartDetail);
    }
    

    @Override

    public int update(String bookId, int newQuantity) {
        try {
            int index = existBookById(bookId);
            globalBean.cartDetails.get(index).setQuantity(newQuantity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public int existBookById(String id) {
        List<CartDetail> cartDetails = globalBean.cartDetails;
        for (int i = 0; i < cartDetails.size(); i++) {
            if (cartDetails.get(i).getBookId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private int getMaxId() {
        int maxId = 0;
        int size = globalBean.cartDetails.size();
        if (size != 0) {
            maxId = globalBean.cartDetails.get(size - 1).getId();
        }
        return maxId;

    }

    @Override
    public List<CartDetail> findAll() {
        List<CartDetail> cartDetails = globalBean.cartDetails;
        return cartDetails;
    }

    @Override
    public List<CartDetailDto> getALlDetail() {
        List<CartDetail> cartDetails = globalBean.cartDetails;
        List<CartDetailDto> cartDetailDtos = new ArrayList<>();
        if (cartDetails != null) {
            cartDetailDtos = cartDetailMapper.modelToDtos(cartDetails);
        }
        return cartDetailDtos;
    }

    @Override
    public CartDetail findById(int id) {
        for (CartDetail cartDetail : globalBean.cartDetails) {
            if (cartDetail.getId() == id) {
                return cartDetail;
            }
        }
        return null;
    }

    @Override
    public int delete(int id) {
        try {
            CartDetail cartDetail = findById(id);
            globalBean.cartDetails.remove(cartDetail);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
