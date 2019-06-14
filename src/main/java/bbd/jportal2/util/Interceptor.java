package bbd.jportal2.util;

import java.lang.reflect.InvocationHandler;
import java.util.Objects;

public abstract class Interceptor<T> implements InvocationHandler {

    private T target;

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public Interceptor(T target) {
        Objects.requireNonNull(target, "Can not proxy a null object");
        this.target = target;
    }
}
