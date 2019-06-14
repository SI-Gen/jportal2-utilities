package bbd.jportal2.util;

public interface IInterceptorFactory<T> {

    Interceptor<T> newInterceptor(T t);
}
