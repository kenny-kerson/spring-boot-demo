package com.kenny.springbootdemo.springjunit5mockitotest.extension;

import com.kenny.springbootdemo.springjunit5mockitotest.annotation.SlowTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private final static long THRESHOLD = 2000L;

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("__KENNY__ EXTENSION : beforeTestExecution");

        final ExtensionContext.Store store = getStore(context);

        store.put("START_TIME", System.currentTimeMillis());

    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("__KENNY__ EXTENSION : afterTestExecution");

        final ExtensionContext.Store store = getStore(context);
        SlowTest slowTest = context.getRequiredTestMethod().getAnnotation(SlowTest.class);

        Long startTime = store.remove("START_TIME", long.class);
        long duration = System.currentTimeMillis() - startTime;
        if ( duration >= THRESHOLD && slowTest == null ) {
            System.out.printf( "[CHECK] THIS TEST IS SLOW!! : class[%s], method[%s] \n", context.getRequiredTestClass().getName(), context.getRequiredTestMethod().getName());
        }
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        final String testClassName = context.getRequiredTestClass().getName();
        final String testMethodName = context.getRequiredTestMethod().getName();
        return context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
    }

}
