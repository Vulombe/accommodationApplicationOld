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
import za.ac.cput.accommodationapp.domain.Contacts;
import za.ac.cput.accommodationapp.repository.ContactsRepository;

/**
 * Created by mphuser on 5/8/2016.
 */
public class ContactsRepositoryImpl extends SQLiteOpenHelper implements ContactsRepository
{
    public static final String TABLE_NAME = "Contacts";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "contactsID";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CELLNUMBER = "cellNumber";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EMAIL + " TEXT  NOT NULL , "
            + COLUMN_CELLNUMBER + " TEXT NOT NULL );";

    public ContactsRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Contacts findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_EMAIL,
                        COLUMN_CELLNUMBER,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

          final Contacts  contacts = new Contacts.Builder()
                    .ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .cellNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CELLNUMBER)))
                    .build();

            return contacts;
        } else {
            return null;
        }
    }

    @Override
    public Contacts save(Contacts entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_CELLNUMBER, entity.getCellNumber());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Contacts insertedEntity = new Contacts.Builder()
                .copy(entity)
                .ID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Contacts update(Contacts entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
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
    public Contacts delete(Contacts entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())});
        return entity;
    }

    @Override
    public Set<Contacts> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Contacts> contactses = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Contacts contacts = new Contacts.Builder()
                        .ID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .cellNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CELLNUMBER)))
                        .build();

                contactses.add(contacts);
            } while (cursor.moveToNext());
        }
        return contactses;
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
