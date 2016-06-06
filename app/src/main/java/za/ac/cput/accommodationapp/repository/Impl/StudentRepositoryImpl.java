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
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PersonDetails;
import za.ac.cput.accommodationapp.domain.Room;
import za.ac.cput.accommodationapp.domain.Student;
import za.ac.cput.accommodationapp.repository.StudentRepository;

/**
 * Created by 212068075 on 4/21/2016.
 */
public class StudentRepositoryImpl extends SQLiteOpenHelper implements StudentRepository
{
    public static final String TABLE_NAME = "student";
    private SQLiteDatabase db;

    private PersonDetails personDetails = null;
    private Address address =  null;
    private List<Payment> payments = null;
    private Room room = null;

    public static final String COLUMN_ID = "studentID";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_lASTNAME = "lastName";
    public static final String COLUMN_GENDER= "gender";
    public static final String COLUMN_DATE = "DOB";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CELLNUMBER = "cellNumber";
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITYCODE = "cityCode";
    public static final String COLUMN_VALIDATE = "validate";
    public static final String COLUMN_ROOM= "room";
    public static final String COLUMN_PAYMENT = "payments";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_lASTNAME + " TEXT NOT NULL , "
            + COLUMN_GENDER + " TEXT  NOT NULL , "
            + COLUMN_DATE + " TEXT  NOT NULL , "
            + COLUMN_EMAIL + " TEXT  NOT NULL , "
            + COLUMN_CELLNUMBER + " TEXT NOT NULL );"
            + COLUMN_PROVINCE + " TEXT NOT NULL );"
            + COLUMN_CITY + " TEXT NOT NULL , "
            + COLUMN_STREET + " TEXT NOT NULL , "
            + COLUMN_CITYCODE + " TEXT  NOT NULL , "
            + COLUMN_VALIDATE + " TEXT  NOT NULL , "
            + COLUMN_ROOM + " TEXT  NOT NULL , "
            + COLUMN_PAYMENT + " TEXT NOT NULL );";

   public StudentRepositoryImpl(Context context) {
       super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
   }
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    public Student findById(Long id){
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
                        COLUMN_PROVINCE,
                        COLUMN_CITY,
                        COLUMN_STREET,
                        COLUMN_CITYCODE,
                        COLUMN_VALIDATE,
                        COLUMN_ROOM,
                        COLUMN_PAYMENT,},
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
            address  = new Address.Builder()
                    .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .cityCode(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)))
                    .build();

            final Student administrator = new Student.Builder()
                    .studentID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .validate(cursor.getString(cursor.getColumnIndex(COLUMN_VALIDATE)))
                    .build();

            return administrator;
        } else {
            return null;
        }
    }

    public Student save(Student entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getStudentID());
        values.put(COLUMN_FIRSTNAME, personDetails.getfName());
        values.put(COLUMN_lASTNAME, personDetails.getlName());
        values.put(COLUMN_GENDER, personDetails.getGender());
        values.put(COLUMN_DATE, personDetails.getDob().toString());
        values.put(COLUMN_EMAIL, personDetails.getEmail());
        values.put(COLUMN_CELLNUMBER, personDetails.getCellNumber());
        values.put(COLUMN_PROVINCE, address.getProvince());
        values.put(COLUMN_STREET, address.getStreet());
        values.put(COLUMN_CITY, address.getCity());
        values.put(COLUMN_CITYCODE, address.getCityCode());
        values.put(COLUMN_PAYMENT, payments.get(0).getPaymentNumber());
        values.put(COLUMN_ROOM, room.getRoomNumber());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Student insertedEntity = new Student.Builder()
                .copy(entity)
                .studentID(new Long(id))
                .build();
        return insertedEntity;
    }

    public Student update(Student entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getStudentID());
        values.put(COLUMN_FIRSTNAME, personDetails.getfName());
        values.put(COLUMN_lASTNAME, personDetails.getlName());
        values.put(COLUMN_GENDER, personDetails.getGender());
        values.put(COLUMN_DATE, personDetails.getDob().toString());
        values.put(COLUMN_EMAIL, personDetails.getEmail());
        values.put(COLUMN_CELLNUMBER, personDetails.getCellNumber());
        values.put(COLUMN_PROVINCE, address.getProvince());
        values.put(COLUMN_STREET, address.getStreet());
        values.put(COLUMN_CITY, address.getCity());
        values.put(COLUMN_CITYCODE, address.getCityCode());
        values.put(COLUMN_PAYMENT, payments.get(0).getPaymentNumber());
        values.put(COLUMN_ROOM, room.getRoomNumber());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getStudentID())}
        );
        return entity;
    }

    public Student delete(Student entity){
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getStudentID())});
        return entity;
    }

    public Set<Student> findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Student> students = new HashSet<>();
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

                address  = new Address.Builder()
                        .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                        .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                        .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                        .cityCode(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)))
                        .build();


                final Student student = new Student.Builder()
                        .studentID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .validate(cursor.getString(cursor.getColumnIndex(COLUMN_VALIDATE)))
                        .build();
                students.add(student);
            } while (cursor.moveToNext());
        }
        return students;
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
