package com.example.applocaldatab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertScreen extends Dialog
{
    Context context;
    static String dbName="stddb.db";  // database nu name static j hovu joye atle a j apvu
    String dbPath="/data/data/com.example.applocaldatab/databases/"; // database no path == (/data/data/) +  domain par right click-->copy path-->copy reference + (/databases/)
    SQLiteDatabase db = null; // reference of databases
    EditText etId,etName,etage;
    Button btIns,btCan;

    public InsertScreen(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_insert_screen);

        etId = findViewById(R.id.etid);
        etName = findViewById(R.id.etna);
        etage = findViewById(R.id.etag);

        btIns = findViewById(R.id.btins);
        btCan = findViewById(R.id.btcan);

        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myPath = dbPath + dbName;
                try
                {
                    db = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
                }
                catch(Exception e)
                { }

                int id = Integer.parseInt(etId.getText().toString());
                String na = etName.getText().toString();
                int age = Integer.parseInt(etage.getText().toString());

                db.execSQL("insert into mytab values("+id+",'"+na+"',"+age+")");



                db.close();

                etId.setText("");
                etage.setText("");
                etName.setText("");

                Toast.makeText(context,"Record INSERTED Successfully",Toast.LENGTH_LONG).show();


            }
        });
        btCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etId.setText("");
                etage.setText("");
                etName.setText("");

                Toast.makeText(context,"Record Erased Successfully",Toast.LENGTH_LONG).show();
            }
        });


    }
}