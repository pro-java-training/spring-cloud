package com.codve.user.aspect;

import com.codve.user.model.data.object.UserDO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author admin
 * @date 2019/11/21 10:14
 */
@Aspect
public class UserServiceAspect {

    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    @Around("execution(* com.codve.user.service.UserService.save(..))")
    public Object aroundSave(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        UserDO userDO = (UserDO) args[0];
        if (userDO != null && userDO.getPassword() != null) {
            userDO.setPassword(encoder.encode(userDO.getPassword()));
        }
        return joinPoint.proceed();
    }
}
