package za.ac.cput.accommodationapp.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.databases.DBConstants;
import za.ac.cput.accommodationapp.conf.util.AppUtil;
import za.ac.cput.accommodationapp.domain.RoomType;
import za.ac.cput.accommodationapp.repository.RoomTypeRepository;

/**
 * Created by mphuser on 5/8/2016.
 */
public class RoomTypeRepositoryImpl extends SQLiteOpenHelper implements RoomTypeRepository
{
    public static final String TABLE_NAME = "roomType";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "roomTypeID";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TYPE = "type";


    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STATUS + " TEXT NOT NULL , "
            + COLUMN_TYPE + " TEXT  NOT NULL , ";



    public RoomTypeRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    public RoomType findById(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_STATUS,
                        COLUMN_TYPE,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {



            RoomType  roomType = new RoomType.Builder()
                    .ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                    .status(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)))
                    .build();


            return roomType;
        } else {
            return null;
        }
    }

    public RoomType save(RoomType entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_STATUS, entity.getStatus());
        values.put(COLUMN_TYPE, entity.getType());


        long id = db.insertOrThrow(TABLE_NAME, null, values);
        RoomType insertedEntity = new RoomType.Builder()
                .copy(entity)
                .ID(new Long(id))
                .build();
        return insertedEntity;
    }

    public RoomType update(RoomType entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_STATUS, entity.getStatus());
        values.put(COLUMN_TYPE, entity.getType());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())}
        );
        return entity;
    }

    public RoomType delete(RoomType entity){
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())});
        return entity;
    }

    public Set<RoomType> findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Set<RoomType> roomTypes = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                RoomType roomType = new RoomType.Builder()
                        .ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                        .status(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)))
                        .build();




                roomTypes.add(roomType);
            } while (cursor.moveToNext());
        }
        return roomTypes;
    }

    public int deleteAll(){
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
