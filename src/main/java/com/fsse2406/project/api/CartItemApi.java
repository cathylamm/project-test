package com.fsse2406.project.api;

import com.fsse2406.project.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2406.project.data.cartItem.dto.response.CartItemResponseDto;
import com.fsse2406.project.data.cartItem.dto.response.SuccessResponseDto;
import com.fsse2406.project.data.product.dto.response.ProductResponseDto;
import com.fsse2406.project.data.user.domainObject.request.FirebaseUserData;
import com.fsse2406.project.service.CartItemService;
import com.fsse2406.project.util.JwtUtil;
import com.sun.net.httpserver.Authenticator;
import jakarta.validation.constraints.Positive;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemApi {
    private final CartItemService cartItemService;

    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public SuccessResponseDto putCartItem(JwtAuthenticationToken jwt,
                                          @PathVariable Integer pid,
                                          @PathVariable Integer quantity) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwt);
        cartItemService.putCartItem(pid, quantity, firebaseUserData);
        return new SuccessResponseDto();
    }

    @GetMapping
    public List<CartItemResponseDto> getUserCart(JwtAuthenticationToken jwt) {
        List<CartItemResponseDto> dtoList = new ArrayList<>();

        for (CartItemResponseData data :
                cartItemService.getUserCartByfirebaseUserData
                        (JwtUtil.getFirebaseUserData(jwt))) {
            dtoList.add(new CartItemResponseDto(data));

        }
        return dtoList;
    }

    @PatchMapping("{pid}/{quantity}")
    public CartItemResponseDto updateQuantity(JwtAuthenticationToken jwt,
                                              @PathVariable Integer pid,
                                              @PathVariable @Positive Integer quantity){
        return new CartItemResponseDto(
                cartItemService.updateQuantity(pid, quantity, JwtUtil.getFirebaseUserData(jwt))
        );
    }

    @DeleteMapping("/pid")
    public SuccessResponseDto deleteCartItem(JwtAuthenticationToken jwt,
                                             @PathVariable Integer pid){
    cartItemService.removeCartItem(pid, JwtUtil.getFirebaseUserData(jwt));
    return new SuccessResponseDto();
    }
}
