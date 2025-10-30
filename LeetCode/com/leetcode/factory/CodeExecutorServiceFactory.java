package factory;

import enums.Language;

import service.CodeExecutorService;
import service.impl.MockCodeExecutorServiceImpl;

public class CodeExecutorServiceFactory {

    public static CodeExecutorService getCodeExecutor(Language language) {
        switch(language) {
            default : return new MockCodeExecutorServiceImpl();
        }
    }
}
