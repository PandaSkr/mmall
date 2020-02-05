package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Constants {
    public static final String CURRENT_USER = "currentUser";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }
    public interface Role {
        int ROLE_CUSTOMER = 0; // 普通用户
        int ROLE_MANAGER = 1; // 管理员
    }

    public interface Cart {
        int CHECKED = 1;   //购物车选中状态
        int UN_CHECKED = 0; //未选中

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCC = "LIMIT_NUM_SUCC";
    }

    public enum ProductStatusEnum {
        ON_SALE("在线",1);

        private String value;
        private Integer code;
        ProductStatusEnum(String value, Integer code) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }
}
