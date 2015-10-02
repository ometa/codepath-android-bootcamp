package com.example.devin.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.devin.todoapp.models.TodoItem;

public class EditItemActivity extends AppCompatActivity {

    EditText etTodoItem;
    TodoItem item;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        intent = new Intent(EditItemActivity.this, MainActivity.class);

        long id = getIntent().getLongExtra("id", -1);

        item = TodoItem.load(TodoItem.class, id);
        if (item == null) {
            intent.putExtra("message", "Could not load item with id: " + id);
            setResult(RESULT_CANCELED, intent);
            finish();
        }

        // populate activity
        etTodoItem = (EditText) findViewById(R.id.etTodoItem);
        etTodoItem.setText(item.body);
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
        etTodoItem = (EditText) findViewById(R.id.etTodoItem);
        String newBody = etTodoItem.getText().toString();

        if (! newBody.equals(item.body)) {
            item.body = newBody;
            item.save();
            intent.putExtra("message", "Updated " + item.body);
            setResult(RESULT_OK, intent);
        } else {
            intent.putExtra("message", "No Change");
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }
}
