package com.example.mobileapp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContactDataSource {
    private SQLiteDatabase database;
    private ContactDBHelper dbHelper;

    public ContactDataSource(Context context){
        dbHelper = new ContactDBHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public boolean insertContact(Contact c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("contactname", c.getContactName());
            initialValues.put("streetaddress", c.getStreetAddress());
            initialValues.put("city", c.getCity());
            initialValues.put("state", c.getState());
            initialValues.put("zipcode", c.getZipCode());
            initialValues.put("phonenumber", c.getPhoneNumber());
            initialValues.put("cellnumber", c.getCellNumber());
            initialValues.put("email", c.geteMail());
            initialValues.put("birthday", String.valueOf(c.getBirthday().getTimeInMillis()));

            didSucceed = database.insert("contact", null, initialValues) > 0;
        }
        catch (Exception e) {
            // Do nothing - will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean updateContact(Contact c) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) c.getContactID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("contactname", c.getContactName());
            updateValues.put("streetaddress", c.getStreetAddress());
            updateValues.put("city", c.getCity());
            updateValues.put("state", c.getState());
            updateValues.put("zipcode", c.getZipCode());
            updateValues.put("phonenumber", c.getPhoneNumber());
            updateValues.put("cellnumber", c.getCellNumber());
            updateValues.put("email", c.geteMail());
            updateValues.put("birthday", String.valueOf(c.getBirthday().getTimeInMillis()));

            didSucceed = database.update("contact", updateValues, "_id=" + rowId, null) > 0;
        }
        catch (Exception e) {
        }
        return didSucceed;
    }
    public int getLastContactId(){
        int lastId = -1;
        try{
            String query = "Select MAX(_id) from contact";
            Cursor cursor = database.rawQuery(query, null);
            cursor.close();
        }
        catch (Exception e){
            lastId = -1;
        }
        return lastId;

    }

    public ArrayList<String> getContactName(){
        ArrayList<String> contactNames = new ArrayList<>();
        try{
            String query = "Select contactname from contact"; // assigning our query
            Cursor cursor = database.rawQuery(query,null); //assigning the result of our query to cursor

            cursor.moveToFirst();        // puts cursor on the first row
            while(!cursor.isAfterLast()){    // while there is still rows left
                contactNames.add(cursor.getString(0));     // add string values of column with index zero
                cursor.moveToNext();   // on to the next
            }
            cursor.close();     // close the cursor(release the result set)

        }
        catch (Exception e){
            contactNames = new ArrayList<>();
        }
        return contactNames;
    }




}
