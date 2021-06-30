package com.github.noodzhan.main.pattern.event;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @author noodzhan
 * @since 2021/6/2 6:48 下午
 */
public class CrudOpRegister implements OpRegister {
    private Operate operate;

    public CrudOpRegister(Operate operate) {
        this.operate = operate;
    }

    @Override
    public void register(Class clazz) {
        List<OperateListener> listeners = scan(clazz);
        operate.addListener(listeners);
    }

    private List<OperateListener> scan(Class clazz) {
        List<OperateListener> operateListeners = new LinkedList<>();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Object instance = null;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Method method : declaredMethods) {
            if (Objects.nonNull(method.getAnnotation(CrudOpListener.class))) {
                OperateListener factory = OperateListenerFactory.factory(method,instance);
                operateListeners.add(factory);
            }
        }
        return operateListeners;
    }

    static class OperateListenerFactory {
        public static OperateListener factory(Method method,Object instance) {
            final Method markedAnnotationMethod = method;
            return (OperateListener) Proxy.newProxyInstance(OperateListenerFactory.class.getClassLoader(), new Class[]{OperateListener.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    markedAnnotationMethod.invoke(instance, markedAnnotationMethod.getParameters());

                    return null;
                }

            });
        }

    }
}

interface OpRegister {

    default void register() {
        return;
    }

    default void register(Class clazz) {
        return;
    }
}

