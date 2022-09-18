package com.example.applocaldatab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;


//Local database -- client to client database changes and that's why we use Local database(in assets folder)
public class MainActivity extends Activity {
    //for animation
    TextView tv;
    String text = "Let's get into the Local Datatbase with Compact !"; // for display the string
    int i = 0; // for count the letter

Button bsh,bin,bdel,bup;
private long pressedTime; // for back pressed twice to exit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        bsh = findViewById(R.id.bsh);
        bin = findViewById(R.id.bin);
        bdel = findViewById(R.id.bdel);
        bup = findViewById(R.id.bup);

        initialize(text);   //for animation of the text


        //app run thay to pela ma pelu a run thase nd db check thase
        mySqliteHelper ms = new mySqliteHelper(this); // database che k nai ana mate call karayo class
        ms.checkDatabase();

        bsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ShowActivity.class);
                startActivity(i);
            }
        });
        bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InsertScreen dialog = new InsertScreen(MainActivity.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // for corner and hide other part
                dialog.show();
            }
        });
        bup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet(MainActivity.this);
                bottomSheet.show();
                bottomSheet.getWindow().getAttributes().windowAnimations = R.style.BottomSheet; // for call the animation file from style in theme.xml
            }
        });
        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteActivity dialog = new DeleteActivity(MainActivity.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // for corner and hide other part
                dialog.show();
            }
        });


    }

    private void initialize(String passed_text) {
        if(i<= passed_text.length()){
            String outputSting = ""+passed_text.substring(0,i);
            tv.setText(Html.fromHtml(outputSting));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    i++;
                    initialize(passed_text);
                }
            },100);
        }
    }

    //for back pressed twice to exit
    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }



}