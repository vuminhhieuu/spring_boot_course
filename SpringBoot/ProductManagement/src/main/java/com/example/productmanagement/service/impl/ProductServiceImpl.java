package com.example.productmanagement.service.impl;

import com.example.productmanagement.entity.Product;
import com.example.productmanagement.exception.ProductNotFoundException;
import com.example.productmanagement.repository.ProductRepository;
import com.example.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // Đánh dấu đây là một Bean, Spring sẽ quản lý nó
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Không tìm thấy sản phẩm với ID: " + id));
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Không thể xóa. Không tìm thấy sản phẩm ID: " + id);
        }
        productRepository.deleteById(id);
    }

    // --- LOGIC XỬ LÝ TRANSACTION ---

    @Override
    public long deleteAllZeroQuantityProducts(boolean useTransaction) {
        if (useTransaction) {
            System.out.println("Executing WITH transaction...");
            return deleteAllZeroQuantityTransactional();
        } else {
            System.out.println("Executing WITHOUT transaction...");
            return deleteAllZeroQuantityNonTransactional();
        }
    }

    /**
     * Method này được bao bọc bởi một transaction.
     * Nếu có lỗi RuntimeException, tất cả các thay đổi sẽ được rollback.
     */
    @Transactional(rollbackFor = Exception.class)
    public long deleteAllZeroQuantityTransactional() {
        long deletedCount = productRepository.deleteByQuantity(0);
        // Giả lập lỗi để kiểm tra rollback
        if (deletedCount > 0) {
            throw new RuntimeException("CỐ TÌNH GÂY LỖI để kiểm tra rollback!");
        }
        return deletedCount;
    }

    /**
     * Method này KHÔNG có @Transactional.
     * Mỗi thao tác là một transaction riêng lẻ. Nếu có lỗi, những gì đã làm sẽ không được hoàn tác.
     */
    public long deleteAllZeroQuantityNonTransactional() {
        long deletedCount = productRepository.deleteByQuantity(0);
        // Giả lập lỗi để kiểm tra
        if (deletedCount > 0) {
            throw new RuntimeException("CỐ TÌNH GÂY LỖI trong môi trường không transaction!");
        }
        return deletedCount;
    }
}