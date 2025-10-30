package model;

import java.time.LocalDateTime;

import enums.Language;
import enums.SubmissionStatus;

public class Submission implements Identifyable{

    private final int id;
    private final Language language;
    private final User submittedBy;
    private final Problem problem;
    private final String code;
    private SubmissionStatus submissionStatus;
    private final LocalDateTime submittedAt;
    private long executionTime;
    private String output;

    public Submission(int id, Language language, User submittedBy, Problem problem, String code) {
        this.id = id;
        this.language = language;
        this.submittedBy = submittedBy;
        this.problem = problem;
        this.code = code;
        this.submittedAt = LocalDateTime.now();
        this.submissionStatus = SubmissionStatus.PENDING;
    }

    @Override
    public int getId() {
        return id;
    }

    public Language getLanguage() {
        return language;
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public Problem getProblem() {
        return problem;
    }

    public String getCode() {
        return code;
    }

    public SubmissionStatus getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(SubmissionStatus submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Submission [language=" + language + ", submittedBy=" + submittedBy + ", problem=" + problem
                + ", submissionStatus=" + submissionStatus + "]";
    }
}
