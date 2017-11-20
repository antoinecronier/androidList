package com.tactfactory.mynotes.dao.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tactfactory.mynotes.entities.Note;
import com.tactfactory.mynotes.entities.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tactfactory on 20/11/17.
 */

public abstract class BaseDAO<T extends BaseEntity> {

    protected String tableName;
    protected  SQLiteDatabase db;
    protected Context context;
    protected String idCol;
    protected String whereId;
    protected String[] cols;
    protected Class<T> clazz;

    public BaseDAO(Context context, String tableName, String idCol, String[] cols, Class<T> clazz){
    ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(
            context , ApplicationSQLiteOpenHelper.DB_NAME, null, ApplicationSQLiteOpenHelper.DB_VERSION);
        this.db = helper.getWritableDatabase() ;
        this.context = context;
        this.tableName = tableName;
        this.idCol = idCol;
        this.whereId = idCol + " = ?";
        this.cols = cols;
        this.clazz = clazz;
    }

    public T insert(T item){
        long idResult = this.db.insert(
                this.tableName,
                null,
                getContentValues(item));

        item.setId(idResult);
        return item;
    }

    public T update(T item){
        String whereClause =
                whereId;
        String[] whereArgs =
                new String[] {String.valueOf(item.getId()) };

        this.db.update(
                this.tableName,
                getContentValues(item),
                whereClause,
                whereArgs);

        return item;
    }
    public void delete(T item){
        String whereClause =
                whereId;
        String[] whereArgs = new String[] {
                String.valueOf(item.getId())};

        this.db.delete(
                this.tableName,
                whereClause,
                whereArgs
        );
    }

    public T get(T item){
        String selection = whereId;
        String[] selectionArgs = {String.valueOf(item.getId())};

        Cursor cursor = this.db.query(
                this.tableName,
                cols,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            itemContentUpdate(item, cursor);
        }

        return item;
    }

    public List<T> get(){
        Cursor cursor = this.db.query(
                this.tableName,
                cols,
                null,
                null,
                null,
                null,
                null);

        ArrayList<T> result = new ArrayList<T>(cursor.getCount());

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            int index;
            do {
                T item = null;
                try {
                    item = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                
                itemContentUpdate(item,cursor);
                result.add(item);
            } while (cursor.moveToNext());
        }

        return result;
    }

    protected abstract ContentValues getContentValues(T item);
    protected abstract void itemContentUpdate(T item, Cursor cursor);
}
