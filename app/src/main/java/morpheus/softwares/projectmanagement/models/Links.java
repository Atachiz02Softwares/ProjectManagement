package morpheus.softwares.projectmanagement.models;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class Links {
    protected final Context context;
    private final String[] areas = {"Artificial Intelligence", "Software Engineering", "Game " +
            "Design",
            "Networking",
            "Multimedia Technology", "Cyber Security", "Data Science", "Programming Languages",
            "Soft Computing", "Machine Learning", "Data structures & Algorithms", "Computer Hardware",
            "Medical Informatics", "Cloud Computing", "Game Design", "Data Mining", "Information " +
            "& Communication Technology", "Computer Vision", "Natural Language Processing"};
    private final String[] roles = {"student", "supervisor", "coordinator"};

    public Links(Context context) {
        this.context = context;
    }

    /**
     * Returns the list of available project areas of interests
     */
    public String[] getAreas() {
        return areas;
    }

    /**
     * Returns the list of available roles of to be signed up as
     */
    public String[] getRoles() {
        return roles;
    }

    /**
     * Sets the status of a user account creation to 'student', 'supervisor' or 'coordinator'
     */
    public void setStatus(String status) {
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("Status", MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // Storing the key and its value as the data fetched from edittext
        myEdit.putString("status", status);

        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.apply();
    }
}