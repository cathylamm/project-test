package com.fsse2406.project.service;

import com.fsse2406.project.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2406.project.data.user.domainObject.request.FirebaseUserData;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartItemService {
    boolean putCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData);

    List<CartItemResponseData> getUserCartByfirebaseUserData(FirebaseUserData firebaseUserData);

    CartItemResponseData updateQuantity(Integer pid, Integer quantity, FirebaseUserData firebaseUserData);

    boolean removeCartItem(Integer pid, FirebaseUserData firebaseUserData);
}
