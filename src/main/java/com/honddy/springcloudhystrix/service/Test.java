package com.honddy.springcloudhystrix.service;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.hystrix.contrib.javanica.command.GenericCommand;
import com.netflix.hystrix.contrib.javanica.command.HystrixCommandBuilder;
import com.netflix.hystrix.contrib.javanica.command.HystrixCommandFactory;

/**
 * @program: spring-cloud-hystrix
 * @description:
 * @author: lipeng
 * @create: 2018-04-18 13:28
 **/
public class Test
{
    static class SayHelloCommand extends HystrixCommand<String>
    {
        private final String _name;

        public SayHelloCommand(String name)
        {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloService"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
            _name = new String(name);//unmutable

        }

        @Override
        protected String run() throws InterruptedException
        {

            Thread.sleep(600);

            return String.format("Hello %s!", _name);
        }

        @Override
        protected String getFallback()
        {
            return String.format("[FallBack]Hello %s!", _name);
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        System.err.println(new SayHelloCommand("lipeng").execute());
    }

}
