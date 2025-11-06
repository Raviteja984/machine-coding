package observer;

import entitiy.Issue;

public interface IssueObserver {

    void onIssueCreated(Issue issue);
}
