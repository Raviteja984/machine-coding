import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import controller.IncidentManager;
import entitiy.Agent;
import entitiy.Constants;
import entitiy.Issue;
import service.IssueAssignService;
import service.IssueAssignServiceImpl;

public class IncidentManagementDemoMain {

    private static String getEntityId() {
        return UUID.randomUUID().toString();
    }
    public static void main(String[] args) throws Exception {
        
        IssueAssignService issueAssignService = new IssueAssignServiceImpl();
        IncidentManager incidentManager = new IncidentManager(issueAssignService);

        Set<String> a1_specs = new HashSet<>();
        a1_specs.add(Constants.PAYMENT_RELATED);
        a1_specs.add(Constants.SPF);
        Agent a1 = incidentManager.addAgent(new Agent(getEntityId(), "A1", a1_specs));
        
        // adding the issue assigners to agent as a observer
        a1.getObservers().add(issueAssignService);

        Issue IN1 = incidentManager.addIssue(new Issue(getEntityId(),  "user_1@ex.com", "I made payment but it was not processed", Constants.PAYMENT_RELATED));
        Issue IN2 = incidentManager.addIssue(new Issue(getEntityId(),  "user_1@ex.com", "payment service down", Constants.PAYMENT_RELATED));

        incidentManager.assignIssueToAvailableAgent(IN1);
        incidentManager.assignIssueToAvailableAgent(IN2);

        incidentManager.markIssueResolved(IN1);
        incidentManager.markIssueResolved(IN2);

        incidentManager.fetchIssuesByStatus(Constants.RESOLVED);

        incidentManager.timeToResolveAllIssues();

        incidentManager.getAverageResolutionTime(getEntityId());
    }
}
