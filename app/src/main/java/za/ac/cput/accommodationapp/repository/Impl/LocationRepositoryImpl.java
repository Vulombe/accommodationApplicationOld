package za.ac.cput.accommodationapp.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import za.ac.cput.accommodationapp.conf.databases.DBConstants;
import za.ac.cput.accommodationapp.conf.util.AppUtil;
import za.ac.cput.accommodationapp.domain.Address;
import za.ac.cput.accommodationapp.domain.Location;
import za.ac.cput.accommodationapp.repository.LocationRepository;

/**
 * Created by 212068075 on 4/21/2016.
 */
public class LocationRepositoryImpl extends SQLiteOpenHelper implements LocationRepository
{
    private Address address =  null;
    public static final String TABLE_NAME = "location";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "LocationID";
    public static final String COLUMN_BUILDING = "building_Name";
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITYCODE = "cityCode";


    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BUILDING + " TEXT NOT NULL , "
            + COLUMN_PROVINCE + " TEXT NOT NULL , "
            + COLUMN_CITY + " TEXT  NOT NULL , "
            + COLUMN_STREET + " TEXT  NOT NULL , "
            + COLUMN_CITYCODE + " TEXT  NOT NULL , ";


    public LocationRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    public Location findById(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_BUILDING,
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

            address  = new Address.Builder()
                    .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .cityCode(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)))
                    .build();

            final Location location = new Location.Builder()
                    .locationID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .buildingName(cursor.getString(cursor.getColumnIndex(COLUMN_BUILDING)))
                    .address(address)
                    .build();

            return location;
        } else {
            return null;
        }
    }

    public Location save(Location entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getLocationID());
        values.put(COLUMN_BUILDING, entity.getBuildingName());
        values.put(COLUMN_PROVINCE, address.getProvince());
        values.put(COLUMN_STREET, address.getStreet());
        values.put(COLUMN_CITY, address.getCity());
        values.put(COLUMN_CITYCODE, address.getCityCode());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Location insertedEntity = new Location.Builder()
                .copy(entity)
                .locationID(new Long(id))
                .build();
        return insertedEntity;
    }

    public Location update(Location entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getLocationID());
        values.put(COLUMN_BUILDING, entity.getBuildingName());
        values.put(COLUMN_PROVINCE, address.getProvince());
        values.put(COLUMN_STREET, address.getStreet());
        values.put(COLUMN_CITY, address.getCity());
        values.put(COLUMN_CITYCODE, address.getCityCode());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getLocationID())}
        );
        return entity;
    }

    public Location delete(Location entity){
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getLocationID())});
        return entity;
    }

    public Set<Location> findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Location> locations = new HashSet<>();
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

                final Location location = new Location.Builder()
                        .locationID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .buildingName(cursor.getString(cursor.getColumnIndex(COLUMN_BUILDING)))
                        .build();


                locations.add(location);
            } while (cursor.moveToNext());
        }
        return locations;
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
