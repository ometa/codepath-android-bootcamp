package com.example.devin.todoapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.devin.todoapp.R;
import com.example.devin.todoapp.lib.Priorities;

/**
 * Created by devin on 10/2/15.
 */
public class TodoCursorAdapter extends CursorAdapter {

    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
        TextView tvPriority = (TextView) view.findViewById(R.id.tvPriority);

        String body = cursor.getString(cursor.getColumnIndexOrThrow("Body"));
        int priority = cursor.getInt(cursor.getColumnIndexOrThrow("Priority"));

        tvBody.setText(body);
        tvPriority.setText(Priorities.getText(priority));

        // color switching
        switch (priority) {
            case Priorities.LOW:
                view.setBackgroundColor(Color.parseColor("#A2BC13"));
                break;
            case Priorities.MEDIUM:
                view.setBackgroundColor(Color.parseColor("#FFD700"));
                break;
            case Priorities.HIGH:
                view.setBackgroundColor(Color.parseColor("#C80000"));
                break;
        }
    }
}