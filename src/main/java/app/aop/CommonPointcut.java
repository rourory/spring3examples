package app.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonPointcut {
    /*
    check annotation on the class
 */
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }

    /*
        check classes at certain package
     */
    @Pointcut("within(app.service.*Service)")
    public void isServiceLayer() {
    }
}
