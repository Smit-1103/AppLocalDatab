package com.example.applocaldatab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowActivity extends Activity {

    static String dbName="stddb.db";  // database nu name static j hovu joye atle a j apvu
    String dbPath="/data/data/com.example.applocaldatab/databases/"; // database no path == (/data/data/) +  domain par right click-->copy path-->copy reference + (/databases/)
    SQLiteDatabase db = null; // reference of database
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lv = findViewById(R.id.lv);
        String myPath = dbPath+dbName;
        try{
            db = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);  //read
        }
        catch (Exception e)
        {}

        Cursor cur = db.rawQuery("select * from mytab",null); // ketla records che ana mate
        Toast.makeText(getApplicationContext(),"Number of records : "+cur.getCount(),Toast.LENGTH_LONG).show(); // badha cursor ma same thase
        String data[] = new String[cur.getCount()]; // jetla record avse atla no array banava nd save karase string ma
        int i = 0;

        while (cur.moveToNext()){
            @SuppressLint("Range") int id = cur.getInt(cur.getColumnIndex("sid"));
            @SuppressLint("Range") String name  = cur.getString(cur.getColumnIndex("sna"));
            @SuppressLint("Range") int age = cur.getInt(cur.getColumnIndex("sag"));

            data[i] = ""+id+" , "+name+" , "+age+"";
            i++;

        }
        db.close();

        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,data);

        lv.setAdapter(ad);
    }

}
