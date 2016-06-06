package za.ac.cput.accommodationapp.repository.Impl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import za.ac.cput.accommodationapp.conf.databases.DBConstants;
import za.ac.cput.accommodationapp.conf.util.AppUtil;
import za.ac.cput.accommodationapp.domain.Administrator;
import za.ac.cput.accommodationapp.domain.Location;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.repository.AdministratorRepository;
import za.ac.cput.accommodationapp.repository.LocationRepository;

/**
 * Created by 212068075 on 4/21/2016.
 */
public class AdministratorRepositoryImpl extends SQLiteOpenHelper implements AdministratorRepository
{
    public static final String TABLE_NAME = "administrator";
    private SQLiteDatabase db;

    PersonDetails personDetails = null;
    List<Location> locations = null;


    public static final String COLUMN_ID = "AdminID";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_lASTNAME = "lastName";
    public static final String COLUMN_GENDER= "gender";
    public static final String COLUMN_DATE = "DOB";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CELLNUMBER = "cellNumber";
    public static final String COLUMN_LOCATION = "location";




    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_lASTNAME + " TEXT NOT NULL , "
            + COLUMN_GENDER + " TEXT  NOT NULL , "
            + COLUMN_DATE + " TEXT  NOT NULL , "
            + COLUMN_EMAIL + " TEXT  NOT NULL , "
            + COLUMN_CELLNUMBER + " TEXT NOT NULL );"
            + COLUMN_LOCATION + " TEXT NOT NULL );";

    public AdministratorRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Administrator findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_FIRSTNAME,
                        COLUMN_lASTNAME,
                        COLUMN_GENDER,
                        COLUMN_DATE,
                        COLUMN_EMAIL,
                        COLUMN_CELLNUMBER,
                        COLUMN_LOCATION,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            personDetails = new PersonDetails.Builder()
                    .fName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lName(cursor.getString(cursor.getColumnIndex(COLUMN_lASTNAME)))
                    .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                    .dob(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .cellNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CELLNUMBER)))
                    .build();

            final Administrator administrator = new Administrator.Builder()
                    .empID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .build();

            return administrator;
        } else {
            return null;
        }
    }

    @Override
    public Administrator save(Administrator entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getEmpID());
        values.put(COLUMN_FIRSTNAME, personDetails.getfName());
        values.put(COLUMN_lASTNAME, personDetails.getlName());
        values.put(COLUMN_GENDER, personDetails.getGender());
        values.put(COLUMN_DATE, personDetails.getDob().toString());
        values.put(COLUMN_EMAIL, personDetails.getEmail());
        values.put(COLUMN_CELLNUMBER, personDetails.getCellNumber());
        values.put(COLUMN_LOCATION, locations.get(0).getLocationID());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Administrator insertedEntity = new Administrator.Builder()
                .copy(entity)
                .empID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Administrator update(Administrator entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getEmpID());
        values.put(COLUMN_FIRSTNAME, personDetails.getfName());
        values.put(COLUMN_lASTNAME, personDetails.getlName());
        values.put(COLUMN_GENDER, personDetails.getGender());
        values.put(COLUMN_DATE, personDetails.getDob().toString());
        values.put(COLUMN_EMAIL, personDetails.getEmail());
        values.put(COLUMN_CELLNUMBER, personDetails.getCellNumber());
        values.put(COLUMN_LOCATION, locations.get(0).getLocationID());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getEmpID())}
        );
        return entity;
    }

    @Override
    public Administrator delete(Administrator entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getEmpID())});
        return entity;
    }

    @Override
    public Set<Administrator> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Administrator> administrators = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                personDetails = new PersonDetails.Builder()
                        .fName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lName(cursor.getString(cursor.getColumnIndex(COLUMN_lASTNAME)))
                        .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                        .dob(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .cellNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CELLNUMBER)))
                        .build();

                final Administrator administrator = new Administrator.Builder()
                        .empID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .build();
                administrators.add(administrator);
            } while (cursor.moveToNext());
        }
        return administrators;
    }

    @Override
    public int deleteAll() {
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
