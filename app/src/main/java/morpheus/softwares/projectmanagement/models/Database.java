package morpheus.softwares.projectmanagement.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "undergraduate_projects.db", TABLE_USERS = "users",
            TABLE_STUDENTS =
                    "students", TABLE_SUPERVISORS = "supervisors", TABLE_COORDINATOR =
            "coordinator", TABLE_PROJECTS = "projects";
    public static final int DATABSE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id integer PRIMARY KEY AUTOINCREMENT, id_number text, " +
                "pin integer)");
        db.execSQL("CREATE TABLE students (id integer PRIMARY KEY AUTOINCREMENT, id_number " +
                "text, email text, first_project text, second_project text, third_project text," +
                " first_area text, second_area text, third_area text)");
        db.execSQL("CREATE TABLE supervisors (id integer PRIMARY KEY AUTOINCREMENT, name text, " +
                "phone_number text, email text, area text)");
        db.execSQL("CREATE TABLE coordinator (id integer PRIMARY KEY AUTOINCREMENT, name text, " +
                "phone_number text, email text)");
        db.execSQL("CREATE TABLE projects (id integer PRIMARY KEY AUTOINCREMENT, id_number " +
                "text, topic_one text, topic_two text, topic_three text, date text, status text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPERVISORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COORDINATOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
        onCreate(db);
    }

    /**
     * Adds a row to Login Table
     */
    public void insertFavourite(Favourite favourite) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_USERS;
        sqlInsert += " values( null, '" + favourite.getFavourite() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    /**
     * Adds a row to History Table
     */
    public void insertHistory(History history) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_STUDENTS;
        sqlInsert += " values( null, '" + history.getHistory() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    /**
     * Selects and returns all the rows in Favourite Table
     */
    public ArrayList<Favourite> selectAllFavourites() {
        String sqlQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(sqlQuery, null);


        ArrayList<Favourite> favourites = new ArrayList<>();
        while (cursor.moveToNext()) {
            Favourite currentFavourite = new Favourite(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1));
            favourites.add(currentFavourite);
        }

        db.close();
        return favourites;
    }

    /**
     * Selects and returns all the rows in History Table
     */
    public ArrayList<History> selectAllHistory() {
        String sqlQuery = "SELECT * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(sqlQuery, null);


        ArrayList<History> histories = new ArrayList<>();
        while (cursor.moveToNext()) {
            History currentHistory = new History(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1));
            histories.add(currentHistory);
        }

        db.close();
        return histories;
    }

    /**
     * Removes the row with the selected word from Favourite Table
     */
    public void deleteFavourite(String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, WORD_COLUMN_NAME + " = ?", new String[]{word});
        db.close();
    }

    /**
     * Removes the row with the selected word from History Table
     */
    public void deleteHistory(String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, WORD_COLUMN_NAME + " = ?", new String[]{word});
        db.close();
    }

    /**
     * Returns the total number of rows in Favourite Table
     */
    public int getFavouritesCount() {
        String countQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    /**
     * Returns the total number of rows in History Table
     */
    public int getHistoryCount() {
        String countQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    /**
     * Clears all rows in Favourite Table and returns the number of rows remaining
     */
    public Integer clearFavourite() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_USERS, null, null);
    }

    /**
     * Clears all rows in History Table and returns the number of rows remaining
     */
    public Integer clearHistory() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_STUDENTS, null, null);
    }
}