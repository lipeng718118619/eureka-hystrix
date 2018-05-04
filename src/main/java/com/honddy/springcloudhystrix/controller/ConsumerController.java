package com.honddy.springcloudhystrix.controller;

import com.honddy.springcloudhystrix.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud-hystrix
 * @description:
 * @author: lipeng
 * @create: 2018-04-18 10:39
 **/
@RestController
public class ConsumerController
{
    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hystrix-hello")
    public String  getHelloName(@RequestParam(value = "name") String name)
    {
       return helloService.helloService(name);
    }
}
