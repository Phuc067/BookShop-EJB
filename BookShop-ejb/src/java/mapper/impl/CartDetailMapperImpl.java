/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper.impl;

import bean.BookBean;
import dto.CartDetailDto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mapper.CartDetailMapper;
import model.Book;
import model.CartDetail;

@Stateless
public class CartDetailMapperImpl implements CartDetailMapper{

  
    @EJB
    private BookBean bookBean;
    
    @Override
    public CartDetailDto modelToDto(CartDetail cartDetail) {
        Book book = bookBean.findById(cartDetail.getBookId());
        CartDetailDto cartDetailDto = new CartDetailDto();
        cartDetailDto.setId(cartDetail.getId());
        cartDetailDto.setBookId(book.getId());
        cartDetailDto.setName(book.getName());
        cartDetailDto.setPrice(book.getPrice());
        cartDetailDto.setQuantity(cartDetail.getQuantity());
        cartDetailDto.setType(book.getType());
        cartDetailDto.setImg(book.getImg());
        return cartDetailDto;
    }

    @Override
    public List<CartDetailDto> modelToDtos(List<CartDetail> list) {
        List<CartDetailDto> cartDetailDtos = new ArrayList<>();
        for(int i = 0 ;i< list.size(); i++)
        {
            cartDetailDtos.add(modelToDto(list.get(i)));
        }
        return cartDetailDtos;
    }
    
}
