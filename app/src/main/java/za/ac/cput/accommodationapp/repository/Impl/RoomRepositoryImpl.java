package za.ac.cput.accommodationapp.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.databases.DBConstants;
import za.ac.cput.accommodationapp.conf.util.AppUtil;
import za.ac.cput.accommodationapp.domain.Booking;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.RoomType;
import za.ac.cput.accommodationapp.repository.RoomRepository;

/**
 * Created by 212068075 on 4/21/2016.
 */
public class RoomRepositoryImpl extends SQLiteOpenHelper implements RoomRepository
{

    public static final String TABLE_NAME = "room";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "roomID";
    public static final String COLUMN_FLOORNUMBER = "floorNumber";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DATE= "dateBooked";
    public static final String COLUMN_DAYS= "days";

    private Booking booking = null;
    private RoomType roomType = null;


    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FLOORNUMBER + " TEXT NOT NULL , "
            + COLUMN_STATUS + " TEXT NOT NULL , "
            + COLUMN_TYPE + " TEXT  NOT NULL , "
            + COLUMN_DATE + " TEXT  NOT NULL , "
            + COLUMN_DAYS + " TEXT  NOT NULL , ";


    public RoomRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    public Room findById(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_FLOORNUMBER,
                        COLUMN_STATUS,
                        COLUMN_TYPE,
                        COLUMN_DATE,
                        COLUMN_DAYS,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            booking  = new Booking.Builder()
                    .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                    .numOfDays(cursor.getString(cursor.getColumnIndex(COLUMN_DAYS)))
                    .build();

            roomType = new RoomType.Builder()
                    .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                    .status(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)))
                    .build();

            final Room room = new Room.Builder()
                    .roomNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .floorNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_FLOORNUMBER)))
                    .build();

            return room;
        } else {
            return null;
        }
    }

    public Room save(Room entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getRoomNumber());
        values.put(COLUMN_FLOORNUMBER, entity.getFloorNumber());
        values.put(COLUMN_STATUS, roomType.getStatus());
        values.put(COLUMN_TYPE, roomType.getType());
        values.put(COLUMN_DATE, booking.getDate().toString());
        values.put(COLUMN_DAYS, booking.getNumOfDays());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Room insertedEntity = new Room.Builder()
                .copy(entity)
                .roomNumber(new Long(id))
                .build();
        return insertedEntity;
    }

    public Room update(Room entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getRoomNumber());
        values.put(COLUMN_FLOORNUMBER, entity.getFloorNumber());
        values.put(COLUMN_STATUS, roomType.getStatus());
        values.put(COLUMN_TYPE, roomType.getType());
        values.put(COLUMN_DATE, booking.getDate().toString());
        values.put(COLUMN_DAYS, booking.getNumOfDays());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getRoomNumber())}
        );
        return entity;
    }

    public Room delete(Room entity){
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getRoomNumber())});
        return entity;
    }

    public Set<Room> findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Room> rooms = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                booking  = new Booking.Builder()
                        .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                        .numOfDays(cursor.getString(cursor.getColumnIndex(COLUMN_DAYS)))
                        .build();

                roomType = new RoomType.Builder()
                        .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                        .status(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)))
                        .build();

                final Room room = new Room.Builder()
                        .roomNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .floorNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_FLOORNUMBER)))
                        .build();


                rooms.add(room);
            } while (cursor.moveToNext());
        }
        return rooms;
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
