package bbd.jportal2.util;

import junit.framework.TestCase;

public class TestJDBCDriver extends TestCase {

    public void testDriverAvailability() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }
}
