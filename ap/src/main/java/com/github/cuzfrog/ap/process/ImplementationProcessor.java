package com.github.cuzfrog.ap.process;

import javax.annotation.processing.ProcessingEnvironment;

public interface ImplementationProcessor {
    void process(ProcessingContext ctx);

    static ImplementationProcessor create(ProcessingEnvironment processingEnv) {
        return new CompositeImplementationProcessor(
                new AnnotationValueExtractProcessor(processingEnv),
                new ValidationProcessor(processingEnv),
                new MethodImplementationProcessor(processingEnv)
        );
    }
}
