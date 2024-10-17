package org.iclass.board.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Advice 는 `스프링 AOP` 기능 중에 처리할 내용(로직-what)을 담당하는 메소드
//              when - exception 발생할 때 동작.
//              where -정식으로는 리턴타입 패키지명.클래스명.메소드명(인자) 형식 지정
//                     이 클래스는 exception 이 발생한 코드.
@RestControllerAdvice
// @ControllerAdvice + @ResponseBody
//   ResponseBody 는 리턴하는 응답 타입이 application/json(데이터)
//           ResponseEntity 자바클래스에 저장.-> json으로 변환해서 전달
//   *비교 : 일반적인 mvc 는 응답이 웹페이지
@Slf4j
public class CustomRestAdvice {

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
