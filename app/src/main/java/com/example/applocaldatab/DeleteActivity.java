package com.example.applocaldatab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends Dialog {

    Context context;
    static String dbName="stddb.db";  // database nu name static j hovu joye atle a j apvu
    String dbPath="/data/data/com.example.applocaldatab/databases/"; // database no path == (/data/data/) +  domain par right click-->copy path-->copy reference + (/databases/)
    SQLiteDatabase db = null; // reference of databases
    EditText etId;
    Button btDel,btCan;

    public DeleteActivity(@NonNull Context context) {
        super(context);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        etId = findViewById(R.id.etid);
        btDel = findViewById(R.id.btDel);
        btCan = findViewById(R.id.btcan);

        btDel.setOnClickListener(new View.OnClickListener() {
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

                db.execSQL("delete from mytab where sid=("+id+")");


                db.close();

                etId.setText("");
                Toast.makeText(context,"Record Deleted Successfully",Toast.LENGTH_LONG).show();


            }
        });
        btCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etId.setText("");

                Toast.makeText(context,"Record Cancelled Successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
}