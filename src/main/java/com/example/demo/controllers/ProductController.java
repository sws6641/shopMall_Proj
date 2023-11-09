package com.example.demo.controllers;

import com.example.demo.application.GetProductDetailService;
import com.example.demo.application.GetProductListService;
import com.example.demo.dtos.ProductDetailDto;
import com.example.demo.dtos.ProductListDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    GetProductListService getProductListService;
    GetProductDetailService getProductDetailService;

    public ProductController(GetProductListService getProductListService,
                             GetProductDetailService getProductDetailService) {
        this.getProductListService = getProductListService;
        this.getProductDetailService = getProductDetailService;
    }

    @GetMapping
    public ProductListDto list(
            @RequestParam(required = false) String categoryId
    ) {
        return getProductListService.getProductListDto(categoryId);
    }

    @GetMapping("/{id}")
    public ProductDetailDto detail(@PathVariable String id) {
        return getProductDetailService.getProductDetailDto(id);
    }
}
