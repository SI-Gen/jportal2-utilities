package bbd.jportal2.util;

import bbd.jportal2.util.ConnectorPostgre;
import java.sql.Connection;

public class TestingConnector extends ConnectorPostgre {

    public TestingConnector(Connection connection) throws Exception {
        super(connection);
    }
}
