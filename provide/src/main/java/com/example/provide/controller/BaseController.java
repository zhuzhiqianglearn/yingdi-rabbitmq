package com.example.provide.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


public class BaseController {
    @Autowired
    public RabbitTemplate rabbitTemplate;
}
