package com.example.devin.todoapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.devin.todoapp.adapters.TodoCursorAdapter;
import com.example.devin.todoapp.models.TodoItem;


public class MainActivity extends AppCompatActivity {

    //file-based
    //ArrayList<String> todoItems;
    //ArrayAdapter<String> aTodoAdaptor;

    // sqlite
    TodoCursorAdapter todoAdapter;

    ListView lvItems;
    EditText etItemBody;

    private final int EDIT_ACTIVITY = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateTodoList();

        etItemBody = (EditText) findViewById((R.id.etItemBody));

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TodoItem item = TodoItem.load(TodoItem.class, id);
                item.delete();
                todoAdapter.changeCursor(TodoItem.getAllCursor());

                Toast.makeText(parent.getContext(), "Deleted " + item.body, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("id", id);
                startActivityForResult(i, EDIT_ACTIVITY);
            }
        });
    }

    public void populateTodoList() {
        Cursor todoCursor = TodoItem.getAllCursor();
        todoAdapter = new TodoCursorAdapter(this, todoCursor);
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(todoAdapter);
    }

    public void onAddItem(View view) {
        String newItemBody = etItemBody.getText().toString();
        if (! newItemBody.equals("")) {
            TodoItem item = new TodoItem(newItemBody);
            item.save();
            etItemBody.setText("");
            todoAdapter.changeCursor(TodoItem.getAllCursor());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == EDIT_ACTIVITY) {

            if (resultCode == RESULT_OK) {
                todoAdapter.changeCursor(TodoItem.getAllCursor());
            } else if (resultCode == RESULT_CANCELED) {
                // special stuff if cancelled
            } else {
                // error condition
            }

            String message = data.getExtras().getString("message");
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
