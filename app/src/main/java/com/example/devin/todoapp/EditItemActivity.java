package com.example.devin.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.devin.todoapp.lib.Priorities;
import com.example.devin.todoapp.models.TodoItem;

public class EditItemActivity extends AppCompatActivity {

    EditText etTodoItem;
    Spinner spinnerPriority;

    TodoItem item;
    Intent returnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        returnIntent = new Intent(EditItemActivity.this, MainActivity.class);

        long id = getIntent().getLongExtra("id", -1);
        item = TodoItem.load(TodoItem.class, id);
        if (item == null) {
            returnIntent.putExtra("message", "Could not load item with id: " + id);
            setResult(RESULT_CANCELED, returnIntent);
            finish();
        }

        // populate activity
        etTodoItem = (EditText) findViewById(R.id.etTodoItem);
        spinnerPriority = (Spinner) findViewById(R.id.spinnerPriority);

        etTodoItem.setText(item.body);
        etTodoItem.requestFocus();

        ArrayAdapter<String> priorityAdaptor = new ArrayAdapter<>(this,  android.R.layout.simple_spinner_item, Priorities.textValues);
        priorityAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriority.setAdapter(priorityAdaptor);
        spinnerPriority.setSelection(item.priority);

        spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPriority.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

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

    private boolean valuesHaveChanged() {
        String newBody = etTodoItem.getText().toString();
        int newPriority = spinnerPriority.getSelectedItemPosition();


        if (!newBody.equals(item.body)) return true;
        if (newPriority != item.priority) return true;
        return false;
    }

    private void saveItem() {
        String newBody = etTodoItem.getText().toString();
        int newPriority = spinnerPriority.getSelectedItemPosition();
        item.body = newBody;
        item.priority = newPriority;
        item.save();
    }

    public void ocSaveItem(View view) {
        if (valuesHaveChanged()) {
            saveItem();
            returnIntent.putExtra("message", "Updated " + item.body);
            setResult(RESULT_OK, returnIntent);
        } else {
            returnIntent.putExtra("message", "No Change");
            setResult(RESULT_CANCELED, returnIntent);
        }
        finish();
    }
}
