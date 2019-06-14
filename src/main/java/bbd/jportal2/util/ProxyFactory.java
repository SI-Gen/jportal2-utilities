package bbd.jportal2.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.stream.Stream;

public class ProxyFactory {

    @SuppressWarnings("unchecked")
    private static <T> T simpleProxy(Class<? extends T> iface, InvocationHandler handler,
            Class<?>... otherIfaces) {
        Class<?>[] allInterfaces = Stream.concat(
                Stream.of(iface),
                Stream.of(otherIfaces))
                .distinct()
                .toArray(Class<?>[]::new);

        return (T) Proxy.newProxyInstance(
                iface.getClassLoader(),
                allInterfaces,
                handler);
    }

    public static <T> T interceptProxy(Class<? extends T> iface, Interceptor<T> interceptor) {
        return simpleProxy(iface, interceptor);
    }

    /** private constructor avoids instantiation of utility class **/
    private ProxyFactory() {}
}
