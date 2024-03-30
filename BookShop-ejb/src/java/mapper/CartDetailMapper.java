/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import dto.CartDetailDto;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import model.CartDetail;

/**
 *
 * @author phucd
 */
@Local
public interface CartDetailMapper {
    CartDetailDto modelToDto(CartDetail cartDetail);
    List<CartDetailDto> modelToDtos(List<CartDetail> cartDetails);
}
