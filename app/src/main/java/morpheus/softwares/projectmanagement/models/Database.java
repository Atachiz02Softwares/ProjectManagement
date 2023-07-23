package morpheus.softwares.projectmanagement.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "undergraduate_projects.db",
            TABLE_USERS = "users", TABLE_STUDENTS = "students", TABLE_SUPERVISORS = "supervisors",
            TABLE_COORDINATOR = "coordinator", TABLE_PROJECTS = "projects";
    public static final int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id integer PRIMARY KEY AUTOINCREMENT, identifier " +
                "text, pin text, name text, role text)");
        db.execSQL("CREATE TABLE students (id integer PRIMARY KEY AUTOINCREMENT, id_number " +
                "text, email text, first_project text, second_project text, " +
                "third_project text, first_area text, second_area text, third_area text, " +
                "grouping text)");
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
     * Adds a row to Users Table
     */
    public void insertUser(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_USERS;
        sqlInsert += " values( null, '" + users.getIdentifier() + "', '" + users.getPin() +
                "', '" + users.getName() + "', '" + users.getRole() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    /**
     * Adds a row to Coordinator Table
     */
    public void insertCoordinator(Coordinator coordinator) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_COORDINATOR;
        sqlInsert += " values( null, '" + coordinator.getName() + "', '" + coordinator.getPhoneNumber() + "', '" +
                coordinator.getEmail() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    /**
     * Adds a row to Supervisors Table
     */
    public void insertSupervisor(Supervisors supervisors) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_SUPERVISORS;
        sqlInsert += " values( null, '" + supervisors.getName() + "', '" + supervisors.getPhoneNumber() +
                "', '" + supervisors.getEmail() + "', '" + supervisors.getArea() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    /**
     * Adds a row to Projects Table
     */
    public void insertProject(Projects projects) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_PROJECTS;
        sqlInsert += " values( null, '" + projects.getIdNumber() + "', '" + projects.getFirstTopic() +
                "', '" + projects.getSecondTopic() + "', '" + projects.getThirdTopic() + "', '" +
                projects.getDate() + "', '" + projects.getStatus() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    /**
     * Adds a row to Students Table
     */
    public void insertStudent(Students students) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_STUDENTS;
        sqlInsert += " values( null, '" + students.getIdNumber() + "', '" + students.getEmail() + "', '" +
                students.getFirstProject() + "', '" + students.getSecondProject() + "', '" +
                students.getThirdProject() + "', '" + students.getFirstArea() + "', '" +
                students.getSecondArea() + "', '" + students.getThirdArea() + "', '" + students.getGrouping() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    /**
     * Selects and returns all the rows in Users Table
     */
    public ArrayList<Users> selectAllUsers() {
        String sqlQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Users> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            Users currentUser = new Users(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4));
            users.add(currentUser);
        }

        db.close();
        return users;
    }

    /**
     * Selects and returns all the rows in Coordinator Table
     */
    public ArrayList<Coordinator> selectAllCoordinators() {
        String sqlQuery = "SELECT * FROM " + TABLE_COORDINATOR;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Coordinator> coordinators = new ArrayList<>();
        while (cursor.moveToNext()) {
            Coordinator currentCoordinator = new Coordinator(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));
            coordinators.add(currentCoordinator);
        }

        db.close();
        return coordinators;
    }

    /**
     * Selects and returns all the rows in Supervisors Table
     */
    public ArrayList<Supervisors> selectAllSupervisors() {
        String sqlQuery = "SELECT * FROM " + TABLE_SUPERVISORS;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Supervisors> supervisors = new ArrayList<>();
        while (cursor.moveToNext()) {
            Supervisors currentSupervisor = new Supervisors(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4));
            supervisors.add(currentSupervisor);
        }

        db.close();
        return supervisors;
    }

    /**
     * Selects and returns all the rows in Projects Table
     */
    public ArrayList<Projects> selectAllProjects() {
        String sqlQuery = "SELECT * FROM " + TABLE_PROJECTS;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Projects> projects = new ArrayList<>();
        while (cursor.moveToNext()) {
            Projects currentProject = new Projects(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6));
            projects.add(currentProject);
        }

        db.close();
        return projects;
    }

    /**
     * Selects and returns all the rows in Students Table
     */
    public ArrayList<Students> selectAllStudents() {
        String sqlQuery = "SELECT * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Students> students = new ArrayList<>();
        while (cursor.moveToNext()) {
            Students currentStudent = new Students(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6),
                    cursor.getString(7), cursor.getString(8), cursor.getString(9));
            students.add(currentStudent);
        }

        db.close();
        return students;
    }

    /**
     * Removes the row with the selected item from Users Table
     */
    public void deleteUser(String identifier) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, "identifier" + " = ?", new String[]{identifier});
        db.close();
    }

    /**
     * Removes the row with the selected item from Students Table
     */
    public void deleteStudent(String idNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, "id_number" + " = ?", new String[]{idNumber});
        db.close();
    }

    /**
     * Removes the row with the selected item from Projects Table
     */
    public void deleteProject(String idNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROJECTS, "id_number" + " = ?", new String[]{idNumber});
        db.close();
    }

    /**
     * Removes the row with the selected item from Supervisors Table
     */
    public void deleteSupervisor(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUPERVISORS, "email" + " = ?", new String[]{email});
        db.close();
    }

    /**
     * Removes the row with the selected item from Coordinator Table
     */
    public void deleteCoordinator(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COORDINATOR, "email" + " = ?", new String[]{email});
        db.close();
    }

    /**
     * Returns the total number of rows in Users Table
     */
    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    /**
     * Returns the total number of rows in Coordinator Table
     */
    public int getCoordinatorCount() {
        String countQuery = "SELECT * FROM " + TABLE_COORDINATOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    /**
     * Returns the total number of rows in Projects Table
     */
    public int getProjectCount() {
        String countQuery = "SELECT * FROM " + TABLE_PROJECTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    /**
     * Returns the total number of rows in Students Table
     */
    public int getStudentCount() {
        String countQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    /**
     * Returns the total number of rows in Supervisors Table
     */
    public int getSupervisorCount() {
        String countQuery = "SELECT * FROM " + TABLE_SUPERVISORS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    /**
     * Clears all rows in Users Table and returns the number of rows remaining
     */
    public Integer clearUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_USERS, null, null);
    }

    /**
     * Clears all rows in Coordinator Table and returns the number of rows remaining
     */
    public Integer clearCoordinators() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_COORDINATOR, null, null);
    }

    /**
     * Clears all rows in Projects Table and returns the number of rows remaining
     */
    public Integer clearProjects() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_PROJECTS, null, null);
    }

    /**
     * Clears all rows in Students Table and returns the number of rows remaining
     */
    public Integer clearStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_STUDENTS, null, null);
    }

    /**
     * Clears all rows in Projects Table and returns the number of rows remaining
     */
    public Integer clearSupervisors() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_SUPERVISORS, null, null);
    }
}