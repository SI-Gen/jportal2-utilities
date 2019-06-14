package bbd.jportal2.util;

import bbd.jportal2.util.DateHandler;
import junit.framework.TestCase;

public class DateHandlerTest extends TestCase {

    private DateHandler dateHandler;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        dateHandler = new DateHandler();
    }

    public void testDataHandler() {
        assertEquals(dateHandler.timeStamp("test"), new java.sql.Timestamp(0));
        assertEquals(dateHandler.dateTime("test"), new java.sql.Timestamp(0));
        assertEquals(dateHandler.date("test"), new java.sql.Date(0));
        assertEquals(dateHandler.time("test"), new java.sql.Time(0));

        assertNotNull(dateHandler.dateTimeFormat);
        assertNotNull(dateHandler.dateFormat);
        assertNotNull(dateHandler.timeFormat);
    }
}
