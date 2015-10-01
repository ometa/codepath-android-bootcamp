package com.example.devin.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText etTodoItem;
    int todoListPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        // pull values from intent
        String text = getIntent().getStringExtra("text");
        todoListPosition = getIntent().getIntExtra("position", -1);

        // populate activity
        etTodoItem = (EditText) findViewById(R.id.etTodoItem);
        etTodoItem.setText(text);
        etTodoItem.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ocSaveItem(View view) {
        Intent i = new Intent(EditItemActivity.this, MainActivity.class);

        etTodoItem = (EditText) findViewById(R.id.etTodoItem);
        i.putExtra("text", etTodoItem.getText().toString());
        i.putExtra("position", todoListPosition);
        setResult(RESULT_OK, i);
        finish();
    }
}
