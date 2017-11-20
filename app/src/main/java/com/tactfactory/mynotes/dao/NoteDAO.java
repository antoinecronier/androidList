package com.tactfactory.mynotes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tactfactory.mynotes.dao.base.BaseDAO;
import com.tactfactory.mynotes.entities.Enregistrement;
import com.tactfactory.mynotes.entities.Note;
import com.tactfactory.mynotes.entities.base.BaseEntity;
import com.tactfactory.mynotes.entities.contracts.NoteContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tactfactory on 20/11/17.
 */

public class NoteDAO extends BaseDAO<Note>{

    public NoteDAO(Context context) {
        super(context,NoteContract.TABLE_NAME,NoteContract.COL_ID,NoteContract.COLS,Note.class);
    }

    @Override
    public Note insert(Note item) {
        super.insert(item);
        EnregistrementDAO enregistrementDAO = new EnregistrementDAO(this.context);
        for (Enregistrement enregistrement: item.getEnregistrementList()) {
            enregistrementDAO.insert(enregistrement);
        }
        return item;
    }

    @Override
    protected ContentValues getContentValues(Note item) {
        ContentValues values = new ContentValues();
        values.put(NoteContract.COL_ID,item.getId());
        values.put(NoteContract.COL_NAME,item.getName());
        return values;
    }

    @Override
    protected void itemContentUpdate(Note item, Cursor cursor) {
        int index;
        index = cursor.getColumnIndex(NoteContract.COL_ID);
        item.setId(cursor.getLong(index));
        index = cursor.getColumnIndex(NoteContract.COL_NAME);
        item.setName(cursor.getString(index));
    }
}
