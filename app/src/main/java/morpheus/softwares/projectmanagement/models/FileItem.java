package morpheus.softwares.projectmanagement.models;

public class FileItem {
    private String fileName;
    private String filePath;

    public FileItem(String fileName, String filePath) {
        setFileName(fileName);
        setFilePath(filePath);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}