package com.example.productmanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleProductNotFoundException(ProductNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage());
        ModelAndView modelAndView = new ModelAndView("error/404");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex) {
        logger.error("An unexpected error occurred: ", ex);
        ModelAndView modelAndView = new ModelAndView("error/generic-error");
        modelAndView.addObject("errorMessage", "Đã có lỗi không mong muốn xảy ra. Vui lòng thử lại sau.");
        return modelAndView;
    }
}