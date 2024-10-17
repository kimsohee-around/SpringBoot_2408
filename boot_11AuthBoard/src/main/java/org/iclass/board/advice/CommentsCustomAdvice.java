package org.iclass.board.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class CommentsCustomAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //예외 발생하면 예외를 처리하는 핸들러(메소드)- 예외 타입 BindException
    public ResponseEntity<?> handleBindResult(MethodArgumentNotValidException e){
        log.error(e.getMessage());
        Map<String,String> errorMap = new HashMap<>();
        if(e.hasErrors()){
            BindingResult bindingResult = e.getBindingResult();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
        }

        return ResponseEntity.badRequest().body(Map.of("errors", errorMap));
//        return ResponseEntity.badRequest().body(e.getMessage());  //e 정보 전체
    }
}
