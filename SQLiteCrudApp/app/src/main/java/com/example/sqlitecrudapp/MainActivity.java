package com.example.sqlitecrudapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insertButton, updateButton, deleteButton, viewButton;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insertButton = findViewById(R.id.insertButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        viewButton = findViewById(R.id.viewButton);

        db = new DBHelper(this);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String contactText = contact.getText().toString();
                String dobText = dob.getText().toString();

                Boolean checkUserDataInserted = db.insertUserData(nameText, contactText, dobText);
                if (checkUserDataInserted == true) {
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String contactText = contact.getText().toString();
                String dobText = dob.getText().toString();

                Boolean checkUserDataUpdated = db.updateUserData(nameText, contactText, dobText);
                if (checkUserDataUpdated == true) {
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Entry is Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String contactText = contact.getText().toString();
                String dobText = dob.getText().toString();

                Boolean checkUserDataDeleted = db.deleteUserData(nameText);
                if (checkUserDataDeleted == true) {
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Entry was Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = db.getUserData();
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Found", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()) {
                    buffer.append("Name: " + result.getString(0) + "\n");
                    buffer.append("Contact: " + result.getString(1) + "\n");
                    buffer.append("Date of Birth: " + result.getString(2) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
            }
        });



    }
}