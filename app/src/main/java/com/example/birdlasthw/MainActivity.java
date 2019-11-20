package com.example.birdlasthw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonCreate;
    EditText editTextCreateBird, editTextCreateCode, editTextCreateName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCreate = findViewById(R.id.buttonCreate);

        editTextCreateBird = findViewById(R.id.editTextCreateBird);
        editTextCreateCode = findViewById(R.id.editTextCreateCode);
        editTextCreateName = findViewById(R.id.editTextCreateName);

        buttonCreate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        if(v == buttonCreate) {

            String createBird = editTextCreateBird.getText().toString();
            String createCode = editTextCreateCode.getText().toString();
            String createPerson = editTextCreateName.getText().toString();

            Birds createBirds = new Birds(createBird, createCode, createPerson);
            myRef.push().setValue(createBirds);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionsMenuInflater = getMenuInflater();
        optionsMenuInflater.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.enterBirdItem:
                return true;
            case R.id.searchBirdItem:
                Intent searchActivityintent = new Intent(MainActivity.this, Search.class);
                startActivity(searchActivityintent);
                return true;

            default:
                return false;
        }

    }
}
