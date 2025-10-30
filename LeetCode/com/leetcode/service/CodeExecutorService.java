package service;

import java.util.List;

import enums.SubmissionStatus;
import model.Submission;
import model.TestCase;

public interface CodeExecutorService {

    SubmissionStatus execute(Submission submission, List<TestCase> testCases);
}
