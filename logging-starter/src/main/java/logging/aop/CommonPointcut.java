package logging.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
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
    @Pointcut("within(*.service.*Service)")
    public void isServiceLayer() {
    }
}
