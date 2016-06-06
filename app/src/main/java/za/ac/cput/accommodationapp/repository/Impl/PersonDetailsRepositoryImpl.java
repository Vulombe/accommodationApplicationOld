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
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.repository.PersonDetailsRepository;

/**
 * Created by mphuser on 5/8/2016.
 */
public class PersonDetailsRepositoryImpl extends SQLiteOpenHelper implements PersonDetailsRepository
{
    public static final String TABLE_NAME = "person_details";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "personID";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_lASTNAME = "lastName";
    public static final String COLUMN_GENDER= "gender";
    public static final String COLUMN_DATE = "DOB";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CELLNUMBER = "cellNumber";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_lASTNAME + " TEXT NOT NULL , "
            + COLUMN_GENDER + " TEXT  NOT NULL , "
            + COLUMN_DATE + " TEXT  NOT NULL , "
            + COLUMN_EMAIL + " TEXT  NOT NULL , "
            + COLUMN_CELLNUMBER + " TEXT NOT NULL );";

    public PersonDetailsRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public PersonDetails findById(Long id) {

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
                        COLUMN_CELLNUMBER,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final PersonDetails personDetails = new PersonDetails.Builder()
                    .ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .fName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lName(cursor.getString(cursor.getColumnIndex(COLUMN_lASTNAME)))
                    .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                    .dob(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .cellNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CELLNUMBER)))
                    .build();

            return personDetails;
        } else {
            return null;
        }
    }

    @Override
    public PersonDetails save(PersonDetails entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_FIRSTNAME, entity.getfName());
        values.put(COLUMN_lASTNAME, entity.getlName());
        values.put(COLUMN_GENDER, entity.getGender());
        values.put(COLUMN_DATE, entity.getDob().toString());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_CELLNUMBER, entity.getCellNumber());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        PersonDetails insertedEntity = new PersonDetails.Builder()
                .copy(entity)
                .ID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public PersonDetails update(PersonDetails entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_FIRSTNAME, entity.getfName());
        values.put(COLUMN_lASTNAME, entity.getlName());
        values.put(COLUMN_GENDER, entity.getGender());
        values.put(COLUMN_DATE, entity.getDob().toString());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_CELLNUMBER, entity.getCellNumber());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())}
        );
        return entity;
    }

    @Override
    public PersonDetails delete(PersonDetails entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())});
        return entity;
    }

    @Override
    public Set<PersonDetails> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PersonDetails> personDetails = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                PersonDetails  personDetail = new PersonDetails.Builder()
                        .ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .fName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lName(cursor.getString(cursor.getColumnIndex(COLUMN_lASTNAME)))
                        .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                        .dob(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .cellNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CELLNUMBER)))
                        .build();


                personDetails.add(personDetail);
            } while (cursor.moveToNext());
        }
        return personDetails;
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
