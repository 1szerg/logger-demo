package com.ericsson.mstesting.logger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

@RestController
public class HomeController
{
    @Autowired
    ApplicationContext ctx;
    @Autowired
    Environment env;

    @RequestMapping("/")
    public String mapRoot()
    {
        StringBuilder sb = new StringBuilder();
        String hostName = "unknown";
        String hostAddress = "unknown";
        try
        {
            InetAddress localHost = InetAddress.getLocalHost();
            hostName = localHost.getHostName();
            hostAddress = localHost.getHostAddress();
        } catch (UnknownHostException e)
        {
            // do nothing
        }
        sb.append("Welcome to ").append(hostName).append("(").append(hostAddress).append(":")
                .append(env.getProperty("local.server.port")).append(")<br>");
        sb.append("Today is ").append(new Date(System.currentTimeMillis()).toString()).append("<br><br>");
        sb.append("Try one of the following commands:<br>");
        sb.append("/list - lists beans provided by Spring Boot<br>");
        return sb.toString();
    }

    @RequestMapping("/list")
    public String listComponents() throws UnknownHostException
    {
        StringBuilder sb = new StringBuilder();
        String[] beanNames = ctx.getBeanDefinitionNames();
        sb.append("We are running with these ").append(beanNames.length).append(" beans provided by Spring Boot:<br><ul>");
        Arrays.sort(beanNames);
        for (String beanName : beanNames)
        {
            sb.append("<li>").append(beanName).append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }
}
