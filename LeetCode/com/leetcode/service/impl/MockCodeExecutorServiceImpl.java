package service.impl;

import java.util.List;

import enums.SubmissionStatus;
import model.Submission;
import model.TestCase;
import service.CodeExecutorService;

public class MockCodeExecutorServiceImpl implements CodeExecutorService{

    @Override
    public SubmissionStatus execute(Submission submission, List<TestCase> testCases) {
        
        if(submission.getCode().contains("error")) {
            return SubmissionStatus.COMPILATION_ERROR;
        } else if (submission.getCode().contains("timeout")) {
            return SubmissionStatus.TIME_LIMIT_EXCEEDED;
        } else if(submission.getCode().contains("runtime")) {
            return SubmissionStatus.RUNTIME_ERROR;
        }

        for(TestCase testCase : testCases) {
            if(!submission.getCode().contains(testCase.getExpectedOutput())){
                return SubmissionStatus.WRONG_ANSWER;
            }
        }
        return SubmissionStatus.ACCEPTED;
    }

}
