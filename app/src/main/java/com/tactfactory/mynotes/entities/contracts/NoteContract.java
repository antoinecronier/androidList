package com.tactfactory.mynotes.entities.contracts;

import com.tactfactory.mynotes.entities.contracts.base.SQLiteContract;

/**
 * Created by tactfactory on 20/11/17.
 */

public class NoteContract {
    public static final String TABLE_NAME = "note";

    public static final String COL_ID = "id";
    public static final String COL_ID_TYPE = "INTEGER PRIMARY KEY AUTOINCREMENT,";
    public static final String COL_NAME = "name";
    public static final String COL_NAME_TYPE = "VARCHAR(200) NOT NULL";

    public static final String[] COLS = {
            COL_ID,
            COL_NAME
    };

    public static final String[] COL_TYPES ={
            COL_ID_TYPE,
            COL_NAME_TYPE
    };

    public static final String[] COL_CONSTRAINTS= {};

    public static final String scheme =
            SQLiteContract.createTable(TABLE_NAME,COLS,COL_TYPES,COL_CONSTRAINTS);
}
