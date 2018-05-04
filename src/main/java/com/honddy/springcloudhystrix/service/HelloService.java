package com.honddy.springcloudhystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: spring-cloud-hystrix
 * @description:
 * @author: lipeng
 * @create: 2018-04-18 10:32
 **/
@Service
public class HelloService
{
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultFallBack")
    public String helloService(String name)
    {
        return restTemplate.getForEntity("http://eureka-client/name?name={0}",String.class,name).getBody();
    }

    public String defaultFallBack(String name)
    {
        return "get by eureka client error!";
    }
}
