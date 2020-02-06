package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Cart;
import com.mmall.vo.CartVO;

import java.net.Inet4Address;

public interface ICartService {
    ServerResponse<CartVO> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVO> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVO> delete(Integer userId, String productIds);

    ServerResponse<CartVO> list(Integer userId);

    ServerResponse<CartVO> selectOrSelect(Integer userId, Integer productId, Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
