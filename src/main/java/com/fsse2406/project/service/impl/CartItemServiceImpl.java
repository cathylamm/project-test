package com.fsse2406.project.service.impl;

import com.fsse2406.project.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2406.project.data.cartItem.entity.CartItemEntity;
import com.fsse2406.project.data.product.entity.ProductEntity;
import com.fsse2406.project.data.user.domainObject.request.FirebaseUserData;
import com.fsse2406.project.data.user.entity.UserEntity;
import com.fsse2406.project.exception.cartItem.CartItemException;
import com.fsse2406.project.repository.CartIemRepository;
import com.fsse2406.project.service.CartItemService;
import com.fsse2406.project.service.ProductService;
import com.fsse2406.project.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private static final Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);
    private final UserService userService;
    private final ProductService productService;
    private final CartIemRepository cartIemRepository;

    public CartItemServiceImpl
            (UserService userService,
             ProductService productService,
             CartIemRepository cartIemRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartIemRepository = cartIemRepository;
    }

    @Override
    @Transactional
    public boolean putCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) {
        try {
            UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            ProductEntity productEntity = productService.getEntityByPid(pid);

            if (quantity <= 0) {
                throw new CartItemException("Quantity must be greater than zero");
            }
            Optional<CartItemEntity> optionalCartItemEntity = cartIemRepository.findByProductAndUser(productEntity, userEntity);

            if (optionalCartItemEntity.isEmpty()) {

                validateQuantity(quantity, productEntity.getStock());
                //replaced by above code
                // if (quantity > productEntity.getStock()) {
                //    throw new CartItemException("Quantity must be smaller than stock");
                //}

                CartItemEntity cartItemEntity = new CartItemEntity(productEntity, userEntity, quantity);
                cartIemRepository.save(cartItemEntity);
                //lv3 cartIemRepository.save(new CartItemEntity(productEntity, userEntity, quantity));
            } else {
                CartItemEntity cartItemEntity = optionalCartItemEntity.get();
                cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);

                validateQuantity(cartItemEntity.getQuantity(), productEntity.getStock());
                //replaced by above code
                //if (cartItemEntity.getQuantity() > productEntity.getStock()) {
                //throw new CartItemException("Quantity must be smaller than stock");
            }
            return true;

        } catch (Exception ex) {
            logger.warn("Put CartItem failed: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<CartItemResponseData> getUserCartByfirebaseUserData(FirebaseUserData firebaseUserData) {
        UserEntity loginUser = userService.getEntityByFirebaseUserData(firebaseUserData);

        List<CartItemResponseData> cartItemResponseDataList = new ArrayList<>();

        //Lv2
        //  List<CartItemEntity> cartItemEntityList = cartIemRepository.findAllByUser(loginUser);
        //  for(CartItemEntity cartItemEntity : cartItemEntityList){
        //Lv3
        for (CartItemEntity cartItemEntity : cartIemRepository.findAllByUser(loginUser)) {
            cartItemResponseDataList.add(new CartItemResponseData(cartItemEntity));
        }
        return cartItemResponseDataList;
    }

    @Override
    @Transactional
    public CartItemResponseData updateQuantity(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) {
        try {
            UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            ProductEntity productEntity = productService.getEntityByPid(pid);

            CartItemEntity cartItemEntity = getEntityByProductAndUser(productEntity, userEntity);

            validateQuantity(quantity, productEntity.getStock());
            cartItemEntity.setQuantity(quantity);

            return new CartItemResponseData(cartItemEntity);

        } catch (Exception ex) {
            logger.warn("Update Cart Quantity failed: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    @Transactional
    public boolean removeCartItem(Integer pid, FirebaseUserData firebaseUserData) {
        Integer deleteCount = cartIemRepository.removeByProductPidAndUserUid(pid, firebaseUserData.getFirebaseUid());
        if (deleteCount <= 0) {
            throw new CartItemException("Delete CartItem failed: pid = " + pid);
        }
        return true;
    }

    public CartItemEntity getEntityByProductAndUser(ProductEntity productEntity, UserEntity userEntity) {
        return cartIemRepository.findByProductAndUser(productEntity, userEntity).orElseThrow(
                () -> new CartItemException(
                        String.format
                                ("Cart Item Not Found: pid-%d, uid-%d", productEntity.getPid(), userEntity.getUid())
                )
        );
    }


    public void validateQuantity(Integer quantity, Integer stock) {
        if (quantity > stock) {
            throw new CartItemException("Quantity must be smaller than stock");
        }
    }


}


