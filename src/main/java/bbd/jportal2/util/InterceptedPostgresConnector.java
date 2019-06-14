package bbd.jportal2.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InterceptedPostgresConnector extends ConnectorPostgre {

    private final IInterceptorFactory<PreparedStatement> interceptorFactory;

    public InterceptedPostgresConnector(Connection connection,
            IInterceptorFactory<PreparedStatement> interceptorFactory) throws Exception {
        super(connection);
        this.interceptorFactory = interceptorFactory;
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        PreparedStatement statement = super.prepareStatement(sql);
        return ProxyFactory.interceptProxy(PreparedStatement.class,
                interceptorFactory.newInterceptor(statement));
    }
}
