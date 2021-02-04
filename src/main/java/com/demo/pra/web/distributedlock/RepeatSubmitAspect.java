package com.demo.pra.web.distributedlock;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

/**
 * 分布式锁切面-防止重复提交
 *
 * @date: 2021/1/29 16:26
 */
@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {

    @Resource
    private RedisDistributedLockTool redisDistributedLockTool;

    /**
     * 设置切点，标注了重复提交注解的方法
     *
     * @date: 2021/1/29 16:35
     */
    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointCut(NoRepeatSubmit noRepeatSubmit) {
    }


    @Around(value = "pointCut(noRepeatSubmit)", argNames = "pjp,noRepeatSubmit")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {
        int lockSeconds = noRepeatSubmit.lockTime();

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        Assert.notNull(request, "request can not null");

        // 此处可以用token或者JSessionId
        /*String token = request.getHeader("Authorization");*/
        String token = "token_test_code";
        String path = request.getServletPath();
        String key = getKey(token, path);
        String clientId = getClientId();

        boolean isSuccess = redisDistributedLockTool.tryGetDistributedLock(key, clientId, lockSeconds);
        log.info("tryLock key = [{}], clientId = [{}]", key, clientId);

        if (isSuccess) {
            log.info("tryLock success, key = [{}], clientId = [{}]", key, clientId);
            // 获取锁成功
            Object result;

            try {
                // 执行进程
                result = pjp.proceed();
            } finally {
                // 解锁
                boolean flag = redisDistributedLockTool.releaseDistributedLock(key, clientId);
                log.info("releaseLock success, key = [{}], clientId = [{}],result = [{}]", key, clientId, flag);
            }

            return result;

        } else {
            // 获取锁失败，认为是重复提交的请求
            log.info("tryLock fail, key = [{}]", key);
            return JSON.toJSONString("重复请求，请稍后再试！");
        }

    }

    private String getKey(String token, String path) {
        return token.concat(path);
    }

    private String getClientId() {
        return UUID.randomUUID().toString();
    }
}
