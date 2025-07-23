package com.example.springcore;

import com.example.springcore.config.AppConfig;
import com.example.springcore.service.TransferService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        TransferService service = ctx.getBean(TransferService.class);

        // TH: Khong du so du
        try {
            service.transfer(1, 2, 1000.0); // Chuyển từ Alice → Bob
            System.out.println("Chuyển tiền thành công.");
        } catch (Exception e) {
            System.out.println("Giao dịch lỗi: " + e.getMessage());
        }

        // TH: Du so du
        try {
            service.transfer(1, 2, 200.0); // Chuyển từ Alice → Bob
            System.out.println("Chuyển tiền thành công.");
        } catch (Exception e) {
            System.out.println("Giao dịch lỗi: " + e.getMessage());
        }

        ctx.close();
    }
}
