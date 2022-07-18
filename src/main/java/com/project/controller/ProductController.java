package com.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entity.ProductEntity;
import com.project.entity.RecommendEntity;
import com.project.model.ProductResponse;
import com.project.service.ProductService;
import com.project.service.RecommendService;
import com.project.utils.PageUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RecommendService recommendService;

    /**
     * 상품 페이지에 표시할 상품 목록을 가져옴
     * 
     * @param page
     * @param unit
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getProduct(@RequestParam(value = "page") String page, @RequestParam(value = "unit") String unit)
            throws IOException {
        if (log.isDebugEnabled()) {
            log.debug("Start getProduct");
        }
        ObjectMapper mapper = new ObjectMapper();
        ProductResponse response = new ProductResponse();
        try {
            if (log.isDebugEnabled()) {
                log.debug("page={}, unit={}", page, unit);
            }
            int intUnit = Integer.parseInt(unit);
            int intPage = Integer.parseInt(page) * intUnit;

            // 등록된 모든 상품의 개수를 가져옴
            int allProduct = productService.countAllProduct();

            // 페이지수 가져옴
            int allPage = PageUtils.getAllPage(allProduct, intUnit);

            // 페이지에 해당하는 상품 목록을 가져옴
            List<ProductEntity> productList = productService.getProductByPage(intPage, intUnit);
            List<Integer> productLNoist = productList.stream().map(ProductEntity::getProductNo)
                    .collect(Collectors.toList());
            Map<Integer, List<RecommendEntity>> recommendMap = recommendService.getRecommendMap(productLNoist);
            for (ProductEntity p : productList) {
                p.setRecommandList(recommendMap.get(p.getProductNo()));
            }
            response.setPage(allPage);
            response.setProductList(productList);
            if (log.isDebugEnabled()) {
                log.debug("allPage={}, productList={}", allPage, productList.size());
            }
            if (log.isDebugEnabled()) {
                log.debug("End getProduct");
            }
            return mapper.writeValueAsString(response);
        } catch (IOException e) {
            throw new IOException();
        }
    }
}