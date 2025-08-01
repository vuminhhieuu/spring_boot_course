package com.example.productmanagement.controller;

import com.example.productmanagement.entity.Product;
import com.example.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService; // Tiêm vào interface, không phải implementation

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listProducts", productService.findAll());
        return "index";
    }

    // ... các method thêm, sửa, xóa sản phẩm đơn lẻ không đổi ...

    @PostMapping("/deleteAllZeroQuantity")
    public String deleteAllZeroQuantity(
            @RequestParam(name = "useTransaction", required = false) boolean useTransaction,
            RedirectAttributes ra) {
        try {
            long count = productService.deleteAllZeroQuantityProducts(useTransaction);
            ra.addFlashAttribute("message",
                    "Đã xóa thành công " + count + " sản phẩm." +
                            " (Chế độ: " + (useTransaction ? "Có Transaction" : "Không có Transaction") + ")");
        } catch (Exception e) {
            // Bắt lỗi được ném ra từ service để hiển thị cho người dùng
            ra.addFlashAttribute("errorMessage",
                    "Xóa thất bại! Lỗi: " + e.getMessage() +
                            " (Chế độ: " + (useTransaction ? "Có Transaction" : "Không có Transaction") + ")");
        }
        return "redirect:/";
    }

    // ... các file khác như login, form ...
    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Thêm Sản Phẩm Mới");
        return "product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product, RedirectAttributes ra) {
        productService.save(product);
        ra.addFlashAttribute("message", "Sản phẩm đã được lưu thành công.");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Chỉnh Sửa Sản Phẩm (ID: " + id + ")");
        return "product-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            productService.deleteById(id);
            ra.addFlashAttribute("message", "Sản phẩm có ID " + id + " đã được xóa.");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}