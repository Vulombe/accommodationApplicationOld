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
import za.ac.cput.accommodationapp.domain.Payment;
import za.ac.cput.accommodationapp.domain.PaymentMethod;
import za.ac.cput.accommodationapp.repository.PaymentRepository;

/**
 * Created by 212068075 on 4/21/2016.
 */
public class PaymentRepositoryImpl extends SQLiteOpenHelper implements PaymentRepository
{
    public static final String TABLE_NAME = "payment";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "paymentID";
    public static final String COLUMN_AMOUNT = "floorNumber";
    public static final String COLUMN_METHOD = "status";



    private Long paymentNumber;
    private double amountPaid;
    private String methodType;
    private PaymentMethod paymentMethod;

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_AMOUNT + " TEXT NOT NULL , "
            + COLUMN_METHOD + " TEXT NOT NULL , ";


    public PaymentRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    public Payment findById(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_AMOUNT,
                        COLUMN_METHOD,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            paymentMethod  = new PaymentMethod.Builder()
                    .methodType(cursor.getString(cursor.getColumnIndex(COLUMN_METHOD)))
                    .build();


            final Payment payment = new Payment.Builder()
                    .paymentNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .amountPaid(cursor.getDouble(cursor.getColumnIndex(COLUMN_AMOUNT)))
                    .build();

            return payment;
        } else {
            return null;
        }
    }

    public Payment save(Payment entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getPaymentNumber());
        values.put(COLUMN_AMOUNT, entity.getAmountPaid());
        values.put(COLUMN_METHOD, paymentMethod.getMethodType());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Payment insertedEntity = new Payment.Builder()
                .copy(entity)
                .paymentNumber(new Long(id))
                .build();
        return insertedEntity;
    }

    public Payment update(Payment entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getPaymentNumber());
        values.put(COLUMN_AMOUNT, entity.getAmountPaid());
        values.put(COLUMN_METHOD, paymentMethod.getMethodType());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getPaymentNumber())}
        );
        return entity;
    }

    public Payment delete(Payment entity){
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getPaymentNumber())});
        return entity;
    }

    public Set<Payment> findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Payment> payments = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                paymentMethod  = new PaymentMethod.Builder()
                        .methodType(cursor.getString(cursor.getColumnIndex(COLUMN_METHOD)))
                        .build();


                final Payment payment = new Payment.Builder()
                        .paymentNumber(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .amountPaid(cursor.getDouble(cursor.getColumnIndex(COLUMN_AMOUNT)))
                        .build();

                payments.add(payment);
            } while (cursor.moveToNext());
        }
        return payments;
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
