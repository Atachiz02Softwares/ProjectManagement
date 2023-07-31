package morpheus.softwares.projectmanagement.models;

public class Student {
    private int id;
    private String idNumber, email, firstProject, secondProject, thirdProject, firstArea,
            secondArea, thirdArea, grouping, date, firstStatus, secondStatus, thirdStatus, firstReport,
            secondReport,
            thirdReport;

    public Student(int id, String idNumber, String email, String firstProject,
                   String secondProject, String thirdProject, String firstArea,
                   String secondArea, String thirdArea, String grouping, String date,
                   String firstStatus, String secondStatus, String thirdStatus, String firstReport,
                   String secondReport, String thirdReport) {
        setId(id);
        setIdNumber(idNumber);
        setEmail(email);
        setFirstProject(firstProject);
        setSecondProject(secondProject);
        setThirdProject(thirdProject);
        setFirstArea(firstArea);
        setSecondArea(secondArea);
        setThirdArea(thirdArea);
        setGrouping(grouping);
        setDate(date);
        setFirstStatus(firstStatus);
        setSecondStatus(secondStatus);
        setThirdStatus(thirdStatus);
        setFirstReport(firstReport);
        setSecondReport(secondReport);
        setThirdReport(thirdReport);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstProject() {
        return firstProject;
    }

    public void setFirstProject(String firstProject) {
        this.firstProject = firstProject;
    }

    public String getSecondProject() {
        return secondProject;
    }

    public void setSecondProject(String secondProject) {
        this.secondProject = secondProject;
    }

    public String getThirdProject() {
        return thirdProject;
    }

    public void setThirdProject(String thirdProject) {
        this.thirdProject = thirdProject;
    }

    public String getFirstArea() {
        return firstArea;
    }

    public void setFirstArea(String firstArea) {
        this.firstArea = firstArea;
    }

    public String getSecondArea() {
        return secondArea;
    }

    public void setSecondArea(String secondArea) {
        this.secondArea = secondArea;
    }

    public String getThirdArea() {
        return thirdArea;
    }

    public void setThirdArea(String thirdArea) {
        this.thirdArea = thirdArea;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstStatus() {
        return firstStatus;
    }

    public void setFirstStatus(String firstStatus) {
        this.firstStatus = firstStatus;
    }

    public String getSecondStatus() {
        return secondStatus;
    }

    public void setSecondStatus(String secondStatus) {
        this.secondStatus = secondStatus;
    }

    public String getThirdStatus() {
        return thirdStatus;
    }

    public void setThirdStatus(String thirdStatus) {
        this.thirdStatus = thirdStatus;
    }

    public String getFirstReport() {
        return firstReport;
    }

    public void setFirstReport(String firstReport) {
        this.firstReport = firstReport;
    }

    public String getSecondReport() {
        return secondReport;
    }

    public void setSecondReport(String secondReport) {
        this.secondReport = secondReport;
    }

    public String getThirdReport() {
        return thirdReport;
    }

    public void setThirdReport(String thirdReport) {
        this.thirdReport = thirdReport;
    }
}