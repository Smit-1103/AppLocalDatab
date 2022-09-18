package com.example.applocaldatab;

import androidx.annotation.NonNull;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomSheet extends BottomSheetDialog {
    Context context;
    static String dbName="stddb.db";  // database nu name static j hovu joye atle a j apvu
    String dbPath="/data/data/com.example.applocaldatab/databases/"; // database no path == (/data/data/) +  domain par right click-->copy path-->copy reference + (/databases/)
    SQLiteDatabase db = null; // reference of databases
    EditText etId,etName,etage;
    Button btUp,btCan;

    public BottomSheet(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        etId = findViewById(R.id.etid);
        etName = findViewById(R.id.etna);
        etage = findViewById(R.id.etag);

        btUp = findViewById(R.id.btUp);
        btCan = findViewById(R.id.btCan);

        btUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myPath = dbPath + dbName;
                try
                {
                    db = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
                }
                catch(Exception e)
                { }

                try
                {
                    int id = Integer.parseInt(etId.getText().toString());
                    String na = etName.getText().toString();
                    int age = Integer.parseInt(etage.getText().toString());

                    db.execSQL("update mytab set sag = "+age+" , sna = '"+na+"' where sid= "+id);

                    db.close();

                    etId.setText("");
                    etage.setText("");
                    etName.setText("");

                    Toast.makeText(context,"Record Updated Successfully",Toast.LENGTH_LONG).show();
                }
                catch(Exception e)
                {
                    Toast.makeText(context,"Fill the details...",Toast.LENGTH_LONG).show();
                }

            }
        });

        btCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etId.setText("");
                etage.setText("");
                etName.setText("");

                Toast.makeText(context,"Cancelled Successfully",Toast.LENGTH_LONG).show();
            }
        });

    }
}