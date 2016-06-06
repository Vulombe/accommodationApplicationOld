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
import za.ac.cput.accommodationapp.domain.Booking;
import za.ac.cput.accommodationapp.repository.BookingRepository;

/**
 * Created by mphuser on 5/8/2016.
 */
public class BookingRepositoryImpl extends SQLiteOpenHelper implements BookingRepository
{
    public static final String TABLE_NAME = "booking";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "bookingID";
    public static final String COLUMN_DATE= "dateBooked";
    public static final String COLUMN_DAYS= "days";




    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DATE + " TEXT  NOT NULL , "
            + COLUMN_DAYS + " TEXT  NOT NULL , ";


    public BookingRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    public Booking findById(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_DATE,
                        COLUMN_DAYS,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Booking booking  = new Booking.Builder()
                    .ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                    .numOfDays(cursor.getString(cursor.getColumnIndex(COLUMN_DAYS)))
                    .build();

            return booking;
        } else {
            return null;
        }
    }

    public Booking save(Booking entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_DATE, entity.getDate().toString());
        values.put(COLUMN_DAYS, entity.getNumOfDays());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Booking insertedEntity = new Booking.Builder()
                .copy(entity)
                .ID(new Long(id))
                .build();
        return insertedEntity;
    }

    public Booking update(Booking entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_DATE, entity.getDate().toString());
        values.put(COLUMN_DAYS, entity.getNumOfDays());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())}
        );
        return entity;
    }

    public Booking delete(Booking entity){
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())});
        return entity;
    }

    public Set<Booking> findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Booking> bookings = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
              Booking  booking  = new Booking.Builder()
                        .ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                        .numOfDays(cursor.getString(cursor.getColumnIndex(COLUMN_DAYS)))
                        .build();

                       bookings.add(booking);
            } while (cursor.moveToNext());
        }
        return bookings;
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
