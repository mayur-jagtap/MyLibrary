package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.settings:
                Toast.makeText(this,"Settings button is clicked",Toast.LENGTH_LONG).show();
            case R.id.help:
                Toast.makeText(this,"Help button is clicked",Toast.LENGTH_LONG).show();
            case R.id.contact:
                Toast.makeText(this,"contact button is clicked",Toast.LENGTH_LONG).show();
                default:
                    return false;
        }

    }

    private final int REQ_CODE = 100;
     TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView speak = findViewById(R.id.speak);
        textView = (TextView) findViewById(R.id.textView);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),
                            "Sorry your device not supported",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE: {
                if ((resultCode == RESULT_OK) && (null != data)) {
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);


                    LibraryDatabase libraryDatabase = new LibraryDatabase(this);
                    SQLiteDatabase db = libraryDatabase.getReadableDatabase();

                    Cursor c = db.rawQuery("select * from Library ", null);

                        textView.setText("");

                    while (c.moveToNext()) {
                        if(c.getString(0).contains(result.get(0).toString().toUpperCase()))
                        {
                            textView.append("Book Name:"+c.getString(0)+"\n");
                            textView.append("Author:"+c.getString(1)+"\n");
                            textView.append("Book Id:"+c.getString(2)+"\n");

                        }
                    }

                }
            }
        }
    }


    public void newPage(View view)
    {
        startActivity(new Intent(this,NewPage.class));


    }
}
