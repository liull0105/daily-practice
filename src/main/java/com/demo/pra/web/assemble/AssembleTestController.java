package com.demo.pra.web.assemble;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("assembleTest")
public class AssembleTestController {

    @Resource
    private AssembleTestClass assembleTestClass;

    @GetMapping("getInfo")
    public String getInfo() {
        return assembleTestClass.getInfo();
    }

}
