package morpheus.softwares.projectmanagement.models;

public class Links {
    String[] areas = {"Artificial Intelligence", "Software Engineering", "Game Design", "Networking",
            "Multimedia Technology", "Cyber Security", "Data Science", "Programming Languages",
            "Soft Computing", "Machine Learning", "Data structures & Algorithms", "Computer Hardware",
            "Medical Informatics", "Cloud Computing", "Game Design", "Data Mining", "Information " +
            "& Communication Technology", "Computer Vision", "Natural Language Processing"};

    public Links() {
    }

    /**
     * Returns the list of available project areas of interests
     */
    public String[] getAreas() {
        return areas;
    }
}