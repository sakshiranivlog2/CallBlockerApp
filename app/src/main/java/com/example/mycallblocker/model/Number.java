package com.example.mycallblocker.model;

import android.content.ContentValues;

public class Number {

    public static final String
            _TABLE = "numbers",
            NUMBER = "number",
            NAME = "name",
            LAST_CALL = "lastCall",
            TIMES_CALLED = "timesCalled",
            ALLOW = "allow",
            ID = "id";

    public String number;
    public String name;

    public Long lastCall;
    public int timesCalled;

    public Integer allow;

    public Integer id;

    public static Number fromValues(ContentValues values) {
        Number number = new Number();
        number.number = values.getAsString(NUMBER);
        number.name = values.getAsString(NAME);
        number.lastCall = values.getAsLong(LAST_CALL);
        number.timesCalled = values.getAsInteger(TIMES_CALLED);
        number.allow = values.getAsInteger(ALLOW);
        number.id = values.getAsInteger(ID);
        return number;
    }

    public static String wildcardsDbToView(String number) {
        return number
                .replace('%','*')
                .replace('_','#');
    }

    public static String wildcardsViewToDb(String number) {
        return number
                .replaceAll("[^+#*%_0-9]", "")
                .replace('*','%')
                .replace('#','_');
    }

}