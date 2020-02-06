package com.mmall.controller.portal;

import com.mmall.common.Constants;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import com.mmall.service.ICategoryService;
import com.mmall.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("/cart")
@Controller
public class CartController {
    @Autowired
    private ICartService iCartService;

    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartVO> listProducts(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.list(user.getId());
    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartVO> add(HttpSession httpSession, Integer count, Integer productId) {
        User user = (User) httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.add(user.getId(), productId, count);
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartVO> update(HttpSession httpSession, Integer count, Integer productId) {
        User user = (User) httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.update(user.getId(), productId, count);
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delete(HttpSession httpSession, String productIds) {
        User user = (User) httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.delete(user.getId(), productIds);
    }

    /**
     * 全选
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "select_all.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartVO> selectAll(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrSelect(user.getId(), null, Constants.Cart.CHECKED);
    }

    /**
     * 全反选
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "un_select_all.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartVO> unSelectAll(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrSelect(user.getId(), null, Constants.Cart.UN_CHECKED);
    }

    /**
     * 单独选
     * @param httpSession
     * @param productId
     * @return
     */
    @RequestMapping(value = "select_one.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartVO> selectOne(HttpSession httpSession, Integer productId) {
        User user = (User) httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrSelect(user.getId(), productId, Constants.Cart.CHECKED);
    }

    /**
     * 单独选
     * @param httpSession
     * @param productId
     * @return
     */
    @RequestMapping(value = "un_select_one.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CartVO> unSelectOne(HttpSession httpSession, Integer productId) {
        User user = (User) httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrSelect(user.getId(), productId, Constants.Cart.UN_CHECKED);
    }

    @RequestMapping(value = "get_cart_product_count.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Integer> getCartProductCount(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createBySuccess(0);
        }
        return iCartService.getCartProductCount(user.getId());
    }


}
