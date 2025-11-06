package entitiy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import observer.IssueObserver;

public class Issue implements Identifyable{

    private final String id;
    private Agent assignedTo;
    private String issueStatus;
    private String specialization;

    private final LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
    private Long resolutionTime;

    
    public Long getResolutionTime() {
        return resolutionTime;
    }


    public void setResolutionTime(Long resolutionTime) {
        this.resolutionTime = resolutionTime;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    private final String userEmail;
    private final String issueText;
    private final List<IssueObserver> observers;

    public Issue(String id, String userEmail, String issueText, String specialization) {
        this.id = id;
        this.userEmail = userEmail;
        this.issueText = issueText;
        this.issueStatus = Constants.OPEN;
        this.specialization = specialization;
        this.observers = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Agent getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Agent assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getIssueText() {
        return issueText;
    }

    public List<IssueObserver> getObservers() {
        return observers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Issue other = (Issue) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}
