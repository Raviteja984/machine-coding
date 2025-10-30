package controller;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import model.Problem;
import model.Submission;
import model.TestCase;
import model.User;

import enums.SubmissionStatus;
import service.CodeExecutorService;
import service.LeaderBoardService;
import service.ModelService;

public class LeetCodeController {

    private final ModelService<User> userService;
    private final ModelService<Problem> problemService;
    private final ModelService<TestCase> testCaseService;
    private final ModelService<Submission> submissionService;
    private final CodeExecutorService codeExecutorService;
    private final LeaderBoardService leaderBoardService;

    public LeetCodeController(
        ModelService<User> userService,
        ModelService<Problem> problemService,
        ModelService<TestCase> testCaseService,
        ModelService<Submission> submissionService,
        Supplier<CodeExecutorService> codeExecutorSupplier,
        LeaderBoardService leaderBoardService
    ) {
        this.userService = userService;
        this.problemService = problemService;
        this.codeExecutorService = codeExecutorSupplier.get();
        this.submissionService = submissionService;
        this.testCaseService = testCaseService;
        this.leaderBoardService = leaderBoardService;
    }

    public User registerUser(User user){
        return userService.add(user);
    }

    public Problem registerProblem(Problem problem) {
        return problemService.add(problem);
    }

    public Submission registerSubmission(Submission submission) {
        return submissionService.add(submission);
    }

    public TestCase registerTestCase(TestCase testCase) {
        return testCaseService.add(testCase);
    }

    public void submitSolution(Submission submission) {
        Problem problem = problemService.getById(submission.getProblem().getId()).orElseThrow(() -> new RuntimeException("Problem not found !"));
        List<TestCase> testCases = problem.getTestCases();
        
        SubmissionStatus status = codeExecutorService.execute(submission, testCases);

        
        User user = userService.getById(submission.getSubmittedBy().getId()).orElseThrow(() -> new RuntimeException(" User not found"));
        Set<Problem> solvedProblems = submissionService.getAll().stream()
                            .filter(s -> s.getSubmittedBy().getId() == user.getId() && SubmissionStatus.ACCEPTED.equals(s.getSubmissionStatus()))
                            .map(s -> s.getProblem())
                            .collect(Collectors.toSet());
        boolean alreadySolved = solvedProblems.contains(problem);
        
        submission.setSubmissionStatus(status);
        leaderBoardService.removeUser(user);
        if(SubmissionStatus.ACCEPTED.equals(status) && !alreadySolved){
            user.setPoints(user.getPoints() + problem.getPoints());
        }
        leaderBoardService.addUser(user);
    }

    public void showLeaderBoard() {
        leaderBoardService.printLeaderBoard();
    }

    public void showTopUsers(int n) {
        leaderBoardService.printTopUsers(n);
    }
}
