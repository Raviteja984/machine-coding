package demo;

import java.util.Arrays;
import java.util.function.Supplier;

import controller.LeetCodeController;
import enums.Difficulty;
import enums.Language;
import factory.CodeExecutorServiceFactory;
import model.Problem;
import model.Submission;
import model.TestCase;
import model.User;
import service.CodeExecutorService;
import service.LeaderBoardService;
import service.ModelService;
import service.impl.LeaderBoardServiceImpl;
import service.impl.ModelServiceImpl;

public class LeetCodeDemoMain {

    String requirements = """
    
        - Functional Requirements
            - Users should be able to 
                - add a problem
                - view a problem
                - submit solution
                - track the leaderboard
        
        - model
            - User
            - Problem
            - Submission
            - TestCase
            - Identifyable
        
        - enums
            - Difficulty
            - Language
            - SubmissionStatus
        
        - service
            - ModelService<T>
            - LeaderboardService
            - CodeExecutorService
        
        - service/impl
            - ModelServiceImpl<T>
            - LeaderboardServiceImpl
            - MockCodeExecutorServiceImpl
        
        - factory
            - CodeExecutorFactory
        
        - controller
            - LeetCodeController
        
        - LeetCodeDemoMain

    """;

    private static int counter=1;

    public static int getEntityCounter() {
        return counter++;
    }

    public static void main(String[] args) {
        
        ModelService<User> userService = new ModelServiceImpl<>();
        ModelService<Problem> problemService = new ModelServiceImpl<>();
        ModelService<TestCase> testCaseService = new ModelServiceImpl<>();
        ModelService<Submission> submissionService = new ModelServiceImpl<>();
        LeaderBoardService leaderBoardService = new LeaderBoardServiceImpl();
        Supplier<CodeExecutorService> codeExecutoSupplier = () -> CodeExecutorServiceFactory.getCodeExecutor(Language.JAVA);

        LeetCodeController leetcode = new LeetCodeController(
            userService, 
            problemService, 
            testCaseService,
            submissionService, 
            codeExecutoSupplier, 
            leaderBoardService);
        
        // register users
        User suman = leetcode.registerUser(new User(getEntityCounter() , "Suman"));
        User eshwar = leetcode.registerUser(new User(getEntityCounter() , "Eshwar"));
        User raviteja = leetcode.registerUser(new User(getEntityCounter() , "Raviteja"));
        User priyanka = leetcode.registerUser(new User(getEntityCounter() , "Priyanka"));

        // register the testcases
        TestCase t1 = leetcode.registerTestCase(new TestCase(getEntityCounter(), "[2,7,11,15], target=9", "[0,1]"));
        TestCase t2 = leetcode.registerTestCase(new TestCase(getEntityCounter(), "[3,2,4], target=6", "[1,2]"));

        // register the problem
        Problem two_sum_problem = leetcode.registerProblem(
            new Problem(getEntityCounter(),
            "Two Sum", 
            "Given and array, find the indices that add up to the given target", 
            Arrays.asList(t1, t2),
            Difficulty.EASY, 
            50));

        // register submissions and submit solutions
        Submission suman_s1 = leetcode.registerSubmission(new Submission(
            getEntityCounter(),
            Language.PYTHON,
            suman, 
            two_sum_problem,
            "error")); 
        
        leetcode.submitSolution(suman_s1);

        Submission eshwar_s1 = leetcode.registerSubmission(new Submission(
            getEntityCounter(),
            Language.JAVASCRIPT,
            eshwar, 
            two_sum_problem,
            "timeout")); 
        
        leetcode.submitSolution(eshwar_s1);

        Submission priyanka_s1 = leetcode.registerSubmission(new Submission(
            getEntityCounter(),
            Language.PYTHON,
            priyanka, 
            two_sum_problem,
            "runtime")); 
        
        leetcode.submitSolution(priyanka_s1);

        Submission raviteja_s1 = leetcode.registerSubmission(new Submission(
            getEntityCounter(),
            Language.JAVA,
            raviteja, 
            two_sum_problem,
            "[0,1] , [1,2]")); 
        
        leetcode.submitSolution(raviteja_s1);

        Submission priyanka_s2 = leetcode.registerSubmission(new Submission(
            getEntityCounter(),
            Language.JAVA,
            priyanka, 
            two_sum_problem,
            "[0,1] , [1,2]")); 
        
        leetcode.submitSolution(priyanka_s2);

        Submission raviteja_s2 = leetcode.registerSubmission(new Submission(
            getEntityCounter(),
            Language.JAVA,
            raviteja, 
            two_sum_problem,
            "[0,1] , [1,2]")); 
        
        leetcode.submitSolution(raviteja_s2);

        leetcode.showLeaderBoard();
    }
}
