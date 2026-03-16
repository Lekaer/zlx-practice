package com.example.zlxpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//题目特征              →  对应思路
//----------------------------------------
//除了自身以外          →  左右拆分（前缀）
//连续子数组            →  滑动窗口
//区间求和              →  前缀和
//下一个更大元素        →  单调栈

@RestController
@SpringBootApplication
public class ZlxPracticeApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello, Spring Boot!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ZlxPracticeApplication.class, args);
    }

}
