package com.demo.pra.web.distributedlock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试分布式锁控制层
 *
 * @date: 2021/1/29 16:12
 */
@Controller
@RequestMapping("commit")
public class TestLockController {

    @NoRepeatSubmit
    @RequestMapping("req")
    @ResponseBody
    public String req() {
        return "hello";
    }

}
