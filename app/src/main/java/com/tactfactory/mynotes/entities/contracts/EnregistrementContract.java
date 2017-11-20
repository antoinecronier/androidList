package com.tactfactory.mynotes.entities.contracts;

import com.tactfactory.mynotes.entities.contracts.base.SQLiteContract;

/**
 * Created by tactfactory on 20/11/17.
 */

public class EnregistrementContract {
    public static final String TABLE_NAME = "enregistrement";

    public static final String COL_ID = "id";
    public static final String COL_ID_TYPE = "INTEGER PRIMARY KEY AUTOINCREMENT,";
    public static final String COL_CONTENT = "content";
    public static final String COL_CONTENT_TYPE = "VARCHAR(2000) NOT NULL,";
    public static final String COL_NOTE_ID = "note_id";
    public static final String COL_NOTE_ID_TYPE = "INTEGER NOT NULL,";
    public static final String COL_NOTE_ID_CONSTRAINT = "FOREIGN KEY(" + COL_NOTE_ID + ") " +
            "REFERENCES " + NoteContract.TABLE_NAME + " (" + NoteContract.COL_ID + ")";

    public static final String[] COLS = {
            COL_ID,
            COL_CONTENT,
            COL_NOTE_ID
    };

    public static final String[] COL_TYPES ={
            COL_ID_TYPE,
            COL_CONTENT_TYPE,
            COL_NOTE_ID_TYPE
    };

    public static final String[] COL_CONSTRAINTS= {
            COL_NOTE_ID_CONSTRAINT
    };

    public static final String scheme =
            SQLiteContract.createTable(TABLE_NAME,COLS,COL_TYPES,COL_CONSTRAINTS);
}
