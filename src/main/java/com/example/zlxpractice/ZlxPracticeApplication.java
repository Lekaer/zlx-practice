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

    // 输入一个字符串 192.168.0.0
    // 判断这个字符串是不是一个有效的ip地址
    public boolean isValidIp(String ip) {
        if (ip == null || ip.length() == 0) {
            return false;
        }
        String[] parts = ip.split("\\.", -1);
        System.out.println(parts.length);
        // 必须4段
        if (parts.length != 4) {
            return false;
        }
        for (String part : parts) {
            // 空字符串
            if (part.length() == 0) {
                return false;
            }
            // 前导0
            if (part.length() > 1 && part.charAt(0) == '0') {
                return false;
            }
            // 必须是数字
            for (char c : part.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            int num = Integer.parseInt(part);
            // 范围
            if (num < 0 || num > 255) {
                return false;
            }
        }

        return true;
    }

    @RequestMapping("/")
    public String home() {
        return "Hello, Spring Boot!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ZlxPracticeApplication.class, args);
    }

}
