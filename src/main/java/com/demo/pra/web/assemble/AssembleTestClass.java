package com.demo.pra.web.assemble;

import org.springframework.stereotype.Service;

/**
 * 装配测试类
 *
 * @date: 2021/1/26 17:09
 */
@Service("assc")
public class AssembleTestClass {

    public String getInfo() {
        return "success";
    }

}
