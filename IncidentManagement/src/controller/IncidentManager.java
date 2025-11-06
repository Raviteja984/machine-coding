package controller;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

import entitiy.Agent;
import entitiy.Constants;
import entitiy.Issue;
import repository.EntityRepository;
import repository.EntityRepositoryImpl;
import service.IssueAssignService;

public class IncidentManager {

    private final EntityRepository<Agent> agentRepo = new EntityRepositoryImpl<>();
    private final EntityRepository<Issue> issueRepo = new EntityRepositoryImpl<>();
    
    private final IssueAssignService issueAssignService;

    public IncidentManager(IssueAssignService issueAssignService) {
        this.issueAssignService = issueAssignService;
    }

    // add agent
    public Agent addAgent(Agent agent) {
        return agentRepo.save(agent);
    }

    // view agent work history
    public void viewAgentWorkHistory(Agent agent) {
        for(Issue agentIssue : agent.getWorkHistory()) {
            System.out.println("Agent " + agent.getName() + " worked on issue " + agentIssue.getIssueText());
        }
    }

    // add Issue
    public Issue addIssue(Issue issue) {
        return issueRepo.save(issue);
    }

    // mark issue resolved
    public void markIssueResolved(Issue issue) {
        issueAssignService.markIssueResolved(issue);
    }

    // fetchIssuesByStatusOpen
    public void fetchIssuesByStatus(String status) {
        issueRepo.findAll().stream()
                    .filter(i -> i.getIssueStatus().equals(status))
                    .map(i -> i.getId() + " - " +  i.getIssueText() + " - " + i.getIssueStatus())
                    .forEach(System.out :: println);
    }

    // assignIssueToAgent
    public void assignIssueToAvailableAgent(Issue issue) {
        issueAssignService.assignIssueToAvailableAgent(issue, agentRepo.findAll());
    }

    public void timeToResolveAllIssues() {
        Long time = 0L;
        for(Issue issue : issueRepo.findAll()) {
            if(issue.getIssueStatus().equals(Constants.RESOLVED)) {
                // System.out.println(issue.getResolvedAt());
                // System.out.println(issue.getCreatedAt());
                time += Duration.between(issue.getCreatedAt(), issue.getResolvedAt() ).toMillis();
            }
        }
        System.out.println("Time took to resolve the issues " + time + " ms.");
    }

    public void getAverageResolutionTime(String issueType) {
        Map<String, Double> issueTypeResolutionTimeMap = 
                        issueRepo.findAll()
                            .stream()
                            .collect(Collectors.groupingBy(Issue :: getSpecialization, Collectors.averagingLong(Issue :: getResolutionTime)));
        System.out.println("Average times are ");
        System.out.println(issueTypeResolutionTimeMap.toString());
    }
}
