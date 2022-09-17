package com.example.applocaldatab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.jetbrains.annotations.TestOnly;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ConcurrentModificationException;

// 1st step --soliteopen helper class is used to create and copy database in clients device
//sauthi pela a check karvanu k database client na device ma che k nai atle a sauthi pela alag java class ma akrvanu

public class mySqliteHelper extends SQLiteOpenHelper {

    static String dbName="stddb.db";  // database nu name static j hovu joye atle a j apvu
    String dbPath="/data/data/com.example.applocaldatab/databases/"; // database no path == (/data/data/) +  domain par right click-->copy path-->copy reference + (/databases/)
    SQLiteDatabase db = null; // reference of database
    Context context; //non activity class che atle
    //constructor
    mySqliteHelper(Context context)
    {
        super(context,dbName,null,1);
        this.context = context;
    }

    public void checkDatabase() //database che k nai device ma ana mate
    {
      String myPath = dbPath+dbName;
      try
      {
          db= SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);//database che k nai device ma ana mate
      }
      catch (Exception e) {
          Toast.makeText(context,"database doesn't exist",Toast.LENGTH_SHORT).show();
      }

      if(db==null){
          this.getReadableDatabase();//it will create new dataBase (na hoy to navo banavo pade )
          this.close();

          try{
              //datat base mathi data ne byte to byte lava mate ip stream nd op stream use karvani
              InputStream is  = context.getAssets().open("stddb.db");
              OutputStream os = new FileOutputStream(myPath);

              byte b[] = new byte[1024];
              int length =0 ;
              while ((length= is.read(b))>0){ // database mathi byte read karse ne pela ma write kase 1 byte b data hase tya sudhi karse
                  os.write(b);
              }

              is.close();
              os.close();

              Toast.makeText(context,"database created Successfully...",Toast.LENGTH_SHORT).show();
          }
          catch (Exception e){
              Toast.makeText(context,"database Creation error",Toast.LENGTH_SHORT).show();
          }
      }
      else {
          Toast.makeText(context,"database already exist",Toast.LENGTH_SHORT).show();
      }

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

