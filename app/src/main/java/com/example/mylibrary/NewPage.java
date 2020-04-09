package com.example.mylibrary;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewPage extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);
    }

    public void show(View view)
    {

    editText=(EditText) findViewById(R.id.editText);

    editText2=(EditText) findViewById(R.id.editText2);

    editText3=(EditText) findViewById(R.id.editText3);


    LibraryDatabase libraryDatabase = new LibraryDatabase(this);
    SQLiteDatabase db = libraryDatabase.getWritableDatabase();
    ContentValues cv = new ContentValues();
        cv.put("book_name",editText.getText().toString().toUpperCase());
        cv.put("author",editText2.getText().toString());
        cv.put("book_id",editText3.getText().toString());
    long res = db.insert("Library", null, cv);

        if(res ==-1)

    {
        Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();

    }
        else

    {
        Toast.makeText(this, "Successfully", Toast.LENGTH_LONG).show();
    }

}




}
