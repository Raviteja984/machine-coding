package service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import entitiy.Agent;
import entitiy.Constants;
import entitiy.Issue;

public class IssueAssignServiceImpl implements IssueAssignService{

    private final Queue<Issue> issueQueue = new LinkedList<>();

    @Override
    public void onAgentAvailable(Agent agent) {
        
        while(!issueQueue.isEmpty()) {
            Issue issue = issueQueue.poll();
            if(agent.getSpecializations().contains(issue.getSpecialization())) {
                System.out.println("Assigning the issue " + issue.getIssueText() + " from queue to the agent " + agent.getName());
                issue.setAssignedTo(agent);
                issue.setIssueStatus(Constants.IN_PROGRESS);
                agent.setAvailable(false);
            } else {
                issueQueue.offer(issue);
            }
        }
    }

    @Override
    public void assignIssueToAvailableAgent(Issue issue, List<Agent> availableAgents) {
        
        Optional<Agent> matchingAgentOptional = availableAgents
                                    .stream()
                                    .filter(a -> a.getSpecializations().contains(issue.getSpecialization()) &&  a.isAvailable())
                                    .findAny();
        if(matchingAgentOptional.isEmpty()) {
            issueQueue.offer(issue);
            System.out.println("Pushing the issue to queue");
            return;
        }

        Agent matchedAgent = matchingAgentOptional.get();
        System.out.println("Assigning the issue " + issue.getIssueText() + " to the agent " + matchedAgent.getName());
        issue.setAssignedTo(matchedAgent);
        issue.setIssueStatus(Constants.IN_PROGRESS);
        matchedAgent.setAvailable(false);
    }

    public void markIssueResolved(Issue issue) {
        
        try {
            System.out.println("Issue resolution is in progress");
            Thread.currentThread().sleep(2000);
            System.out.println("Issue is resolved");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        issue.setIssueStatus(Constants.RESOLVED);
        issue.setResolvedAt(LocalDateTime.now());
        issue.setResolutionTime(Duration.between(issue.getCreatedAt(), issue.getResolvedAt()).toMillis());
        issue.getAssignedTo().getWorkHistory().add(issue);
        issue.getAssignedTo().setAvailable(true);
    }

}
