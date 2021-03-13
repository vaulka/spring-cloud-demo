package com.pongsky.cloud.controller;

import com.pongsky.cloud.response.annotation.ResponseResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

    /**
     * 随机 UUID
     */
    private static final String UID = UUID.randomUUID().toString();

    /**
     * 获取 随机 UUID
     *
     * @return 获取 随机 UUID
     */
//    @SentinelResource("GET - uid")
    @GetMapping("/uid")
    public Object getUid() {
        return UID;
    }

    /**
     * 获取 随机 UUID
     *
     * @return 获取 随机 UUID
     */
//    @SentinelResource("POST - uid")
    @PostMapping("/uid")
    public Object postUid() {
        return UID;
    }

}
