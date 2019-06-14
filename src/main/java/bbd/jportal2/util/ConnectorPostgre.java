package bbd.jportal2.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectorPostgre extends Connector {

    private final String user = "USER";

    public ConnectorPostgre(Connection connection) throws Exception {
        super(connection);
    }

    @Override
    public String getUserstamp() throws SQLException {
        return user;
    }

    @Override
    public int getSequence(String table) throws SQLException {
        return getSequence(table.toLowerCase(), "id");
    }

    @Override
    public long getBigSequence(String table) throws SQLException {
        return getBigSequence(table.toLowerCase(), "id");
    }

    @Override
    public int getSequence(String table, String field) throws SQLException {
        int nextNo;
        PreparedStatement prep = prepareStatement("select nextval(\'" + table + "_" + field
                + "_seq\')");
        ResultSet result = prep.executeQuery();
        result.next();
        nextNo = result.getInt(1);
        result.close();
        prep.close();
        return nextNo;
    }

    @Override
    public long getBigSequence(String table, String field) throws SQLException {
        long nextNo;
        PreparedStatement prep = prepareStatement("select nextval(\'" + table + "_" + field
                + "_seq\')");
        ResultSet result = prep.executeQuery();
        result.next();
        nextNo = result.getLong(1);
        result.close();
        prep.close();
        return nextNo;
    }

    @Override
    public Returning getReturning(String table, String field) throws SQLException {
        Returning result = new Returning();
        result.head = "";
        result.output = "";
        result.sequence = "DEFAULT,";
        result.tail = "RETURNING " + field;
        result.dropField = field;
        result.doesGeneratedKeys = false;
        return result;
    }
}
