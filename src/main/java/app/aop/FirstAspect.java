package app.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
public class FirstAspect {

    /*
    ______________________________________________________
    ______________________POINTCUTS_______________________
    ______________________________________________________
     */

    /*
        this - check AOP proxy class type
        this - check target object class type
     */
    @Pointcut("this(org.springframework.data.repository.Repository)")
//    @Pointcut("target(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {
    }

    /*
        check annotation on methods of Controller layer
        we can use operands like logic and, logic or and logic not
     */
    @Pointcut("CommonPointcut.isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {

    }


    /*
        check method param type
        Model, .. - model class and more
        Model, * - model class and one more
        Model, *, * - model class and two more
     */
    @Pointcut("CommonPointcut.isControllerLayer() && args(org.springframework.ui.Model, ..)")
    public void hasModelParam() {

    }

    /*
        check annotation on the param type
     */
    @Pointcut("CommonPointcut.isControllerLayer() && @args(app.validation.UserInfo, ..)")
    public void hasUserInfoParamAnnotation() {

    }

    /*
        check bean name
     */
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {

    }


    /*
        execution(modifiers-pattern? return-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws)
     */
    @Pointcut("execution(public * app.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {

    }


    /*
    ______________________________________________________
    _______________________ADVICES________________________
    ______________________________________________________
     */


    @Before("anyFindByIdServiceMethod() " +
            "&& args(id) " +
            "&& target(service) " +
            "&& this(serviceProxy) " +
            "&& @within(transactional)")
    public void addLoggingBeforeFindByIdMethod(JoinPoint joinPoint,
                                               Object id,
                                               Object service,
                                               Object serviceProxy,
                                               Transactional transactional) {
        log.info("invoked findById method in class {}, with id {}", service, id);
    }


    @AfterReturning(value = "anyFindByIdServiceMethod() " +
            "&& target(service)", returning = "result")
    public void addLoggingAfterReturningFindByIdMethod(Object result, Object service) {
        log.info("after returning - invoked findById method in class {}, result {}", service, result);
    }


    @AfterThrowing(value = "anyFindByIdServiceMethod() " +
            "&& target(service)", throwing = "ex")
    public void addLoggingAfterThrowingFindByIdMethod(Throwable ex, Object service) {
        log.info("after throwing - invoked findById method in class {}, exception {}: {}", service, ex.getClass(), ex.getMessage());
    }


    @After("anyFindByIdServiceMethod() && target(service)")
    public void addLoggingAfterFindByIdMethod(Object service) {
        log.info("after (finally) - invoked findById method in class {}", service);
    }

    @Around("anyFindByIdServiceMethod() && target(service) && args(id) ")
    public Object addLoggingAroundFindByIdMethod(ProceedingJoinPoint joinPoint,
                                                 Object id,
                                                 Object service) {
        log.info("AROUND - invoked findById method in class {}, with id {}", service, id);
        Object result = null;

        try {
            result = joinPoint.proceed();
            log.info("AROUND after returning - invoked findById method in class {}, result {}", service, result);
        } catch (Throwable ex) {
            log.info("AROUND after throwing - invoked findById method in class {}, exception {}: {}", service, ex.getClass(), ex.getMessage());
            throw new RuntimeException(ex);
        } finally {
            log.info("AROUND after (finally) - invoked findById method in class {}", service);
        }
        return result;


    }

}















