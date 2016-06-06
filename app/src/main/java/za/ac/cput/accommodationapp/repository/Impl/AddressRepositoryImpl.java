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
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.repository.AddressRepository;

/**
 * Created by mphuser on 5/8/2016.
 */
public class AddressRepositoryImpl extends SQLiteOpenHelper implements AddressRepository
{
    public static final String TABLE_NAME = "address";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "addressID";
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITYCODE = "cityCode";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PROVINCE + " TEXT NOT NULL , "
            + COLUMN_CITY + " TEXT  NOT NULL , "
            + COLUMN_STREET + " TEXT  NOT NULL , "
            + COLUMN_CITYCODE + " TEXT  NOT NULL , ";

    public AddressRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    public Address findById(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_PROVINCE,
                        COLUMN_CITY,
                        COLUMN_STREET,
                        COLUMN_CITYCODE,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Address address  = new Address.Builder()
                    .ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .cityCode(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)))
                    .build();


            return address;
        } else {
            return null;
        }
    }

    public Address save(Address entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_PROVINCE, entity.getProvince());
        values.put(COLUMN_STREET, entity.getStreet());
        values.put(COLUMN_CITY, entity.getCity());
        values.put(COLUMN_CITYCODE, entity.getCityCode());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Address insertedEntity = new Address.Builder()
                .copy(entity)
                .ID(new Long(id))
                .build();
        return insertedEntity;
    }

    public Address update(Address entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_PROVINCE, entity.getProvince());
        values.put(COLUMN_STREET, entity.getStreet());
        values.put(COLUMN_CITY, entity.getCity());
        values.put(COLUMN_CITYCODE, entity.getCityCode());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())}
        );
        return entity;
    }

    public Address delete(Address entity){
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())});
        return entity;
    }

    public Set<Address> findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Address> addresses = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Address address = new Address.Builder()
                        .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                        .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                        .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                        .cityCode(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)))
                        .build();




                addresses.add(address);
            } while (cursor.moveToNext());
        }
        return addresses;
    }

    public int deleteAll(){
        open();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
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
