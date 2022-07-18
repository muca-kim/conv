package com.project.controller;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entity.ProductEntity;
import com.project.entity.UserEntity;
import com.project.service.ProductService;
import com.project.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @RequestMapping("/admin-area")
    public String viewAdminArea() throws JsonProcessingException {
        if (log.isDebugEnabled()) {
            log.debug("Start getUserList");
        }
        if (log.isDebugEnabled()) {
            log.debug("End getUserList");
        }
        return "adminPage";
    }

    /**
     * 유저관리
     * 
     * @throws JsonProcessingException
     */
    @ResponseBody
    @GetMapping("/user-list")
    public String getUserList(@RequestParam(required = false) String userName) throws JsonProcessingException {
        if (log.isDebugEnabled()) {
            log.debug("Start getUserList");
        }
        ObjectMapper mapper = new ObjectMapper();
        List<UserEntity> userList;
        if (Objects.isNull(userName)) {
            userList = userService.findAllPersonalUser();
        } else {
            userList = userService.findByUserName(userName);
        }
        if (log.isDebugEnabled()) {
            log.debug("userList={}", userList.size());
        }
        if (log.isDebugEnabled()) {
            log.debug("End getUserList");
        }
        return mapper.writeValueAsString(userList);
    }

    /**
     * 상품관리
     * 
     * @throws JsonProcessingException
     */
    @ResponseBody
    @GetMapping("/all-product")
    public String getProductList(@RequestParam(required = false) String product) throws JsonProcessingException {
        if (log.isDebugEnabled()) {
            log.debug("Start getProductList");
        }
        ObjectMapper mapper = new ObjectMapper();
        List<ProductEntity> productList;
        if (Objects.isNull(product)) {
            productList = productService.findAll();
        } else {
            // if (StringUtils.isNumeric(product)) {
            // productList = productService.findByProductNo(Integer.parseInt(product));
            // } else {
            productList = productService.findByProductName(product);
            // }
        }
        if (log.isDebugEnabled()) {
            log.debug("productList={}", productList.size());
        }
        if (log.isDebugEnabled()) {
            log.debug("End getProductList");
        }
        return mapper.writeValueAsString(productList);
    }

    @ResponseBody
    @DeleteMapping("/delete")
    public String deleteProduct(@RequestParam(required = false) String product) throws JsonProcessingException {
        Objects.requireNonNull(product, "delete product");
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString("OK");
    }

}