package service;

import java.util.List;

import entitiy.Agent;
import entitiy.Issue;
import observer.AgentAvailabilityObserver;

public interface IssueAssignService extends AgentAvailabilityObserver{

    void assignIssueToAvailableAgent(Issue issue, List<Agent> agents);

    void markIssueResolved(Issue issue);
}
