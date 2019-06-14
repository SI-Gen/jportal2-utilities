package bbd.jportal2.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import bbd.jportal2.util.IInterceptorFactory;
import bbd.jportal2.util.InterceptedPostgresConnector;
import bbd.jportal2.util.Interceptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.junit.Test;

public class ProxyTest {

    @Test
    public void testProxied() throws Exception {
        TestCounter testCounter = new TestCounter();

        Connection connection = mock(Connection.class);
        doReturn(mock(PreparedStatement.class)).when(connection).prepareStatement(any());
        InterceptedPostgresConnector connector = new InterceptedPostgresConnector(connection,
                new IInterceptorFactoryImpl(testCounter));
        PreparedStatement statement = connector.prepareStatement("test");
        statement.executeQuery();
        assertEquals(1, testCounter.pre());
        assertEquals(1, testCounter.post());
    }

    private static class TestCounter {

        private int pre;
        private int post;

        private TestCounter() {
            this.pre = 0;
            this.post = 0;
        }

        private int pre() {
            return this.pre++;
        }

        private int post() {
            return this.post++;
        }
    }

    private static class IInterceptorFactoryImpl implements IInterceptorFactory<PreparedStatement> {

        private final TestCounter testCounter;

        private IInterceptorFactoryImpl(TestCounter testCounter) {
            this.testCounter = testCounter;
        }

        @Override
        public Interceptor<PreparedStatement> newInterceptor(PreparedStatement preparedStatement) {
            return new Interceptor<PreparedStatement>(preparedStatement) {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    testCounter.pre();
                    Object res = method.invoke(getTarget(), args);
                    testCounter.post();
                    return res;
                }
            };
        }
    }
}
