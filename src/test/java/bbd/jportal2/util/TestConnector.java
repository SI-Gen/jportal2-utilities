package bbd.jportal2.util;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import junit.framework.TestCase;

public class TestConnector extends TestCase {

    private Connector connector;
    private Connection connection;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.connection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(any())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mock(ResultSet.class));
        this.connector = spy(new TestingConnector(connection));
    }

    public void testCommit() throws SQLException {
        connector.commit();
        verify(connection, atLeastOnce()).commit();
    }

    public void testRollback() throws SQLException {
        connector.rollback();
        verify(connection, atLeastOnce()).rollback();
    }

    public void testTransaction() throws SQLException {
        connector.startTran();
        connector.startTran();
        verify(connector).endTran();
    }

    public void testFlagRollbackDo() throws SQLException {
        connector.startTran();
        connector.flagRollback();
        connector.endTran();

        verify(connection).rollback();
    }

    public void testFlagCommitDo() throws SQLException {
        connector.startTran();
        connector.flagCommit();
        connector.endTran();

        verify(connection).commit();
    }

    public void testFlagRollbackDoNot() throws SQLException {
        connector.flagRollback();
        verify(connection).rollback();
    }

    public void testFlagCommitDoNot() throws SQLException {
        connector.flagCommit();
        verify(connection).commit();
    }

    public void testAutoCommit() throws SQLException {
        connector.setAutoCommit(true);
        verify(connection).setAutoCommit(true);
    }

    public void testCheckExclude() {
        String ret = connector.checkExclude("This is a test, string,", "test,");
        assertEquals(ret, "This is a test, string,");
    }
}
