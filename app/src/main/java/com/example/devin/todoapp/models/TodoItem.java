package com.example.devin.todoapp.models;

import android.database.Cursor;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.example.devin.todoapp.lib.Priorities;

import java.util.List;

/**
 * Created by devin on 10/1/15.
 */
@Table(name = "TodoItems")
public class TodoItem extends Model {

    @Column(name = "Body")
    public String body;

    @Column(name = "Priority")
    public int priority;

    public TodoItem() {
        super();
    }

    public TodoItem(String body) {
        super();
        this.body = body;
        this.priority = Priorities.DEFAULT;
    }

    public TodoItem(String body, int priority) {
        super();
        this.body = body;
        this.priority = priority;
    }

    public static List<TodoItem> getAll() {
        return all().execute();
    }

    private static From all() {
        return new Select().from(TodoItem.class);
    }

    // Return cursor for result set for all todo items
    public static Cursor getAllCursor() {
        String tableName = Cache.getTableInfo(TodoItem.class).getTableName();

        // Query all items without any conditions
        String sql = new Select(tableName + ".*, " + tableName + ".Id as _id").
                from(TodoItem.class).toSql();

        return Cache.openDatabase().rawQuery(sql, null);
    }
}
