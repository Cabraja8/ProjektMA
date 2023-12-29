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

    private static final int VERSION = 2;
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String DESCRIPTION = "description";
    private static final String DATE = "date";
    private static final String STATUS = "status";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TASK + " TEXT, "
            + DESCRIPTION + " TEXT, "
            + DATE + " TEXT, "
            + STATUS + " INTEGER)";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        db.execSQL(CREATE_TODO_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);

        // Create tables again
        onCreate(db);


    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(ToDoModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(DESCRIPTION, task.getDesc());
        cv.put(DATE, task.getDate());
        cv.put(STATUS, 0);
        db.insert(TODO_TABLE, null, cv);
    }




    public List<ToDoModel> getAllTasks(){
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TODO_TABLE, null, null, null, null, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        ToDoModel task = new ToDoModel();
                        int idIndex = cur.getColumnIndex(ID);
                        int taskIndex = cur.getColumnIndex(TASK);
                        int DescIndex = cur.getColumnIndex(DESCRIPTION);
                        int DateIndex = cur.getColumnIndex(DATE);
                        int statusIndex = cur.getColumnIndex(STATUS);

                        if (idIndex >= 0) {
                            task.setId(cur.getInt(idIndex));
                            task.setId(cur.getInt(idIndex));
                        }
                        if (taskIndex >= 0) {
                            task.setTask(cur.getString(taskIndex));
                            task.setDesc(cur.getString(DescIndex));
                            task.setDate(cur.getString(DateIndex));
                        }
                        if (statusIndex >= 0) {
                            task.setStatus(cur.getInt(statusIndex));
                        }
                        taskList.add(task);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return taskList;
    }

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void updateTask(int id, String task, String desc, String date) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        cv.put(DESCRIPTION, desc);
        cv.put(DATE, date);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void deleteTask(int id){
        db.delete(TODO_TABLE, ID + "= ?", new String[] {String.valueOf(id)});
    }
}
