package org.rhydo.superecom.service;

import jakarta.transaction.Transactional;
import org.rhydo.superecom.dto.CartDTO;
import org.rhydo.superecom.dto.CartItemDTO;

import java.util.List;

public interface CartService {
    String createOrUpdateCartWithItems(List<CartItemDTO> cartItems);

    CartDTO addProductToCart(Long productId, Integer quantity);

    List<CartDTO> getAllCarts();

    CartDTO getCart(String emailId, Long cartId);

    @Transactional
    CartDTO updateProductQuantityInCart(Long productId, Integer quantity);

    String deleteProductFromCart(Long cartId, Long productId);
}
