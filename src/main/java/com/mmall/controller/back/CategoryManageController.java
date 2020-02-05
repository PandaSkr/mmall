package com.mmall.controller.back;

import com.mmall.common.Constants;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 添加商品种类
     * @param httpSession
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping(value = "add_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession httpSession, String categoryName,
                                      @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        User user = (User)httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        // 开始校验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 是管理员，添加处理分类逻辑
            return iCategoryService.addCategory(categoryName, parentId);
        } else {
            return ServerResponse.createByErrorMsg("无权限操作");
        }
    }

    /**
     * 设置商品种类名称
     * @param httpSession
     * @param categoryId
     * @param categoryName
     * @return
     */
    @RequestMapping(value = "set_category_name.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession httpSession, Integer categoryId, String categoryName) {
        User user = (User)httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        // 开始校验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 是管理员，添加处理分类逻辑
            return iCategoryService.updateCategoryName(categoryId, categoryName);
        } else {
            return ServerResponse.createByErrorMsg("无权限操作");
        }
    }

    /**
     * 查询节点
     * @param httpSession
     * @param parentId
     * @return
     */
    @RequestMapping(value = "get_children_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession httpSession, @RequestParam(
            value = "parentId", defaultValue = "0") Integer parentId) {
        User user = (User)httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        // 开始校验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 是管理员，查询子节点的category信息，并且不递归，保持平级
            return iCategoryService.getChildrenParallelCategory(parentId);
        } else {
            return ServerResponse.createByErrorMsg("无权限操作");
        }
    }

    @RequestMapping(value = "get_all_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getAllCategory(HttpSession httpSession, @RequestParam(
            value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User)httpSession.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        // 开始校验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 是管理员，查询当前节点的id和递归子节点id
            return iCategoryService.getCategoryAndChildrenById(categoryId);
        } else {
            return ServerResponse.createByErrorMsg("无权限操作");
        }
    }
}
