package com.oop.labs.calculator.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class Controller {
    private Evaluator service = new Evaluator();
    @GetMapping("/{a}/{op}/{b}")
    public String expression(@PathVariable double a, @PathVariable String op, @PathVariable double b) {
        op = op.trim();
        service.setA(a);
        service.setB(b);
        service.setOp(op);
        return service.evaluate();
    }
}