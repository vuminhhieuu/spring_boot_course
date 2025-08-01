package com.example.productmanagement.service;

import com.example.productmanagement.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    void save(Product product);
    void deleteById(Long id);

    /**
     * Xóa các sản phẩm có số lượng bằng 0.
     * @param useTransaction cờ để quyết định có sử dụng transaction hay không.
     * @return số lượng sản phẩm đã xóa.
     */
    long deleteAllZeroQuantityProducts(boolean useTransaction);
}