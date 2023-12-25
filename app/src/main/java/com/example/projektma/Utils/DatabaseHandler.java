package com.example.projektma.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projektma.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "toDoListedDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";

    private static final String STATUS = "status";

    private static final String CREATE_TODO_TABLE="CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    + TASK + "TEXT, " + STATUS + " INTEGER)";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context){
        super(context, NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TODO_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        // Drop the older tables
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        // create tables again
        onCreate(db);


    }
    public void openDatabase(){
        db = this.getWritableDatabase();
    }
    public void insertTask(ToDoModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, 0);
        db.insert( TODO_TABLE,null,cv);
    }

    public List<ToDoModel> getAllTasks() {
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = null;

        db.beginTransaction();
        try {
            cur = db.query(TODO_TABLE, null, null, null, null, null, null, null);

            if (cur != null) {
                int idColumnIndex = cur.getColumnIndex(ID);
                int taskColumnIndex = cur.getColumnIndex(TASK);
                int statusColumnIndex = cur.getColumnIndex(STATUS);

                if (cur.moveToFirst()) {
                    do {
                        ToDoModel task = new ToDoModel();


                        if (idColumnIndex != -1) {
                            task.setId(cur.getInt(idColumnIndex));
                        }

                        if (taskColumnIndex != -1) {
                            task.setTask(cur.getString(taskColumnIndex));
                        }

                        if (statusColumnIndex != -1) {
                            int statusValue = cur.getInt(statusColumnIndex);


                            if (statusValue >= 0) {
                                task.setStatus(statusValue);
                            } else {

                            }
                        }

                        taskList.add(task);
                    } while (cur.moveToNext());
                }
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
            db.endTransaction();
        }

        return taskList;
    }

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});

    }
    public void updateTask(int id, String task){
        ContentValues cv = new ContentValues();
        cv.put(TASK,task);
        db.update(TODO_TABLE, cv, ID +"+?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id){
        db.delete(TODO_TABLE,ID + "+?", new String[]{String.valueOf(id)});
    }



}
