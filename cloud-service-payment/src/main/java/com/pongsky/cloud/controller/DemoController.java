package com.pongsky.cloud.controller;

import com.pongsky.cloud.response.annotation.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Demo 模块
 *
 * @author pengsenhao
 * @create 2021-02-14
 */
@ResponseResult
@RestController
@RequestMapping("/demo")
public class DemoController {

    /**
     * 随机 UUID
     */
    private static final String UID = UUID.randomUUID().toString();

    /**
     * 获取 随机 UUID
     */
    @RequestMapping("/uid")
    public String uid() {
        return UID;
    }

}
