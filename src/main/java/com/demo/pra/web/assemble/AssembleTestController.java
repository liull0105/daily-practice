package com.demo.pra.web.assemble;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 测试@resource 与 @Autowired的区别
 *
 * @date: 2021/1/29 16:02
 */
@Controller
@RequestMapping("assembleTest")
public class AssembleTestController {

    @Resource
    private AssembleTestClass assembleTestClass;

    /*@Resource(type = AssembleTestClass.class)
    private AssembleTestBClass assembleTestBClass;*/

    @RequestMapping("getInfo")
    @ResponseBody
    public String getInfo() {
        return assembleTestClass.getInfo();
    }

}
