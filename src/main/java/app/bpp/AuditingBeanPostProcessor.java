package app.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuditingBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> auditedBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Auditing.class))
            auditedBeans.put(beanName, bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = auditedBeans.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("Auditing method: " + method.getName());
                            var startTime = System.nanoTime();
                            try {
                                return method.invoke(bean, args);
                            } finally {
                                System.out.println("Execution time is " + (System.nanoTime() - startTime));
                            }
                        }
                    });
        }
        return bean;
    }
}
















