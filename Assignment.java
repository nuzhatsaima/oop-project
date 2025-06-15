package models;

import java.time.LocalDate;

public class Assignment {
    private String id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String studentId;
    private String submissionFilePath;
    private String grade;
    private String feedback;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getSubmissionFilePath() { return submissionFilePath; }
    public void setSubmissionFilePath(String submissionFilePath) { this.submissionFilePath = submissionFilePath; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}