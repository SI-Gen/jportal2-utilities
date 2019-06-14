package bbd.jportal2.util;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import bbd.jportal2.util.Connector;
import bbd.jportal2.util.ConnectorPostgre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import junit.framework.TestCase;

public class TestPostgresConnector extends TestCase {

    private Connection connection;
    private Connector connector;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        // Mock out the JDBC connection:
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockStatement.executeQuery()).thenReturn(mock(ResultSet.class));

        this.connection = mock(Connection.class);
        when(connection.prepareStatement(any())).thenReturn(mockStatement);

        // Initialize the connector being tested.
        this.connector = new ConnectorPostgre(connection);
    }

    public void testGetReturning() throws SQLException {
        Connector.Returning result = new Connector.Returning();
        result.sequence = "DEFAULT,";
        result.tail = "RETURNING " + "X";
        result.dropField = "X";
        result.doesGeneratedKeys = false;

        Connector.Returning testRet = connector.getReturning("", "X");
        assertEquals(result.tail, testRet.tail);
        assertEquals(result.dropField, testRet.dropField);
        assertEquals(result.sequence, testRet.sequence);
        assertFalse(testRet.doesGeneratedKeys);
    }

    public void testGetBigSequence() throws SQLException {
        assertEquals(connector.getBigSequence(""), 0);
    }

    public void testSequence() throws SQLException {
        assertEquals(connector.getSequence(""), 0);
    }
}
