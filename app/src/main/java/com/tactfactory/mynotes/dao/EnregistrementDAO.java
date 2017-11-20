package com.tactfactory.mynotes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tactfactory.mynotes.dao.base.ApplicationSQLiteOpenHelper;
import com.tactfactory.mynotes.dao.base.BaseDAO;
import com.tactfactory.mynotes.entities.Enregistrement;
import com.tactfactory.mynotes.entities.base.BaseEntity;
import com.tactfactory.mynotes.entities.contracts.EnregistrementContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tactfactory on 20/11/17.
 */

public class EnregistrementDAO extends BaseDAO<Enregistrement> {

    public EnregistrementDAO(Context context) {
        super(context, EnregistrementContract.TABLE_NAME,EnregistrementContract.COL_ID,
                EnregistrementContract.COLS,Enregistrement.class);
    }

    @Override
    protected ContentValues getContentValues(Enregistrement item) {
        ContentValues values = new ContentValues();
        values.put(EnregistrementContract.COL_ID,item.getId());
        values.put(EnregistrementContract.COL_CONTENT,item.getContenu());
        values.put(EnregistrementContract.COL_NOTE_ID,item.getNote_id());
        return values;
    }

    @Override
    protected void itemContentUpdate(Enregistrement item, Cursor cursor) {
        int index;
        index = cursor.getColumnIndex(EnregistrementContract.COL_ID);
        item.setId(cursor.getLong(index));
        index = cursor.getColumnIndex(EnregistrementContract.COL_CONTENT);
        item.setContenu(cursor.getString(index));
        index = cursor.getColumnIndex(EnregistrementContract.COL_NOTE_ID);
        item.setNote_id(cursor.getLong(index));
    }

    public List<Enregistrement> get(long note_id) {
        String[] cols = {"id","content","note_id"};
        String selection = "note_id = ?";
        String[] selectionArgs = {String.valueOf(note_id)};

        Cursor cursor = this.db.query(
                this.tableName,
                cols,
                selection,
                selectionArgs,
                null,
                null,
                null);

        ArrayList<Enregistrement> result = new ArrayList<Enregistrement>(cursor.getCount());

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            Enregistrement item = new Enregistrement();
            int index;
            do {
                index = cursor.getColumnIndex("id");
                item.setId(cursor.getLong(index));
                index = cursor.getColumnIndex("content");
                item.setContenu(cursor.getString(index));
                index = cursor.getColumnIndex("note_id");
                item.setNote_id(cursor.getLong(index));

                result.add(item);
            } while (cursor.moveToNext());
        }

        return result;
    }
}
