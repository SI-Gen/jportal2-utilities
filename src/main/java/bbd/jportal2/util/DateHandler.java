/// ------------------------------------------------------------------
/// Copyright (c) 1996, 2004 Vincent Risi in Association
///                          with Barone Budge and Dominick
/// All rights reserved.
/// This program and the accompanying materials are made available
/// under the terms of the Common Public License v1.0
/// which accompanies this distribution and is available at
/// http://www.eclipse.org/legal/cpl-v10.html
/// Contributors:
///    Vincent Risi
/// ------------------------------------------------------------------
/// System : JPortal
/// $Date: 2004/10/18 13:45:46 $
/// $Revision: 411.1 $ // YMM.Revision
/// ------------------------------------------------------------------
package bbd.jportal2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateHandler {

    public SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    public SimpleDateFormat timeFormat = new SimpleDateFormat("hhmmss");

    public String timeStamp(java.sql.Timestamp value) {
        return dateTimeFormat.format(value);
    }

    public java.sql.Timestamp timeStamp(String value) {
        try {
            java.util.Date date = dateTimeFormat.parse(value);
            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            return new java.sql.Timestamp(0);
        }
    }

    public String dateTime(java.sql.Timestamp value) {
        return dateTimeFormat.format(value);
    }

    public java.sql.Timestamp dateTime(String value) {
        try {
            java.util.Date date = dateTimeFormat.parse(value);
            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            return new java.sql.Timestamp(0);
        }
    }

    public String date(java.sql.Date value) {
        return dateFormat.format(value);
    }

    public java.sql.Date date(String value) {
        try {
            java.util.Date date = dateFormat.parse(value);
            return new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            return new java.sql.Date(0);
        }
    }

    public String time(java.sql.Time value) {
        return timeFormat.format(value);
    }

    public java.sql.Time time(String value) {
        try {
            java.util.Date date = timeFormat.parse(value);
            return new java.sql.Time(date.getTime());
        } catch (ParseException e) {
            return new java.sql.Time(0);
        }
    }
}
