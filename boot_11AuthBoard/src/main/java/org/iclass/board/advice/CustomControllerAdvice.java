package org.iclass.board.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler
    public String handleException(Exception e, Model model){
        log.error("exception : {}",e.getMessage());
        model.addAttribute("errorMessage",e.getMessage());

        return "error";
    }
}
