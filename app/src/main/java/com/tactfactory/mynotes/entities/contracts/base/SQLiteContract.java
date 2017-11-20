package com.tactfactory.mynotes.entities.contracts.base;

/**
 * Created by tactfactory on 20/11/17.
 */

public class SQLiteContract {

    public static final String CREATE_TABLE = "CREATE TABLE ";

    public static String createTable(String tableName, String[] cols, String[] types, String[] constraints){
        String request = CREATE_TABLE + tableName + "(";

        for (int i = 0; i < cols.length; i++) {
            request += cols[i] + " " + types[i];
        }

        for (int i = 0; i < constraints.length; i++){
            request += constraints[i];
        }

        request += ");";

        return request;
    }
}