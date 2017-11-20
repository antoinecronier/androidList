package com.tactfactory.mynotes.dao.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tactfactory.mynotes.dao.EnregistrementDAO;
import com.tactfactory.mynotes.dao.NoteDAO;
import com.tactfactory.mynotes.entities.Enregistrement;
import com.tactfactory.mynotes.entities.contracts.EnregistrementContract;
import com.tactfactory.mynotes.entities.contracts.NoteContract;

/**
 * Created by tactfactory on 20/11/17.
 */

public class ApplicationSQLiteOpenHelper extends SQLiteOpenHelper {
    protected static final String TAG = "DatabaseHelper";
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "MyNotes";

    public ApplicationSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NoteContract.scheme);
        sqLiteDatabase.execSQL(EnregistrementContract.scheme);
        android.util.Log.i(TAG, "Create database..");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}