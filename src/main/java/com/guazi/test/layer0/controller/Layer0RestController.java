package com.guazi.test.layer0.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.guazi.test.layer0.vo.Student;
import com.guazi.test.layer1.api.Layer1Api;
import com.guazi.test.layer1.vo.Layer1Req;
import com.guazi.test.layer1.vo.Layer1Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Using IntelliJ IDEA.
 *
 * @author XIANYINGDA at 2018-12-29 14:41
 */
@RestController
@Slf4j
public class Layer0RestController {

    @Reference(check = false)
    private Layer1Api layer1Api;

    @RequestMapping("/info")
    public Object getInfo(Student student) {
        log.info("传入参数：{}", JSON.toJSONString(student));
        Layer1Req req = new Layer1Req();
        req.setName(student.getName());
        req.setAge(String.valueOf(student.getAge()));
        log.info("请求参数：{}", JSON.toJSONString(req));
        Layer1Response layer1Response = layer1Api.getLayer1Response(req);
        log.info("layer1响应结果：{}", JSON.toJSONString(layer1Response));
        return layer1Response;
    }
}
