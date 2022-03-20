package com.github.cuzfrog.ap.process;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;

public interface ImplementationProcessor {
    void process(Element element);

    static ImplementationProcessor create(ProcessingEnvironment processingEnv) {
        return new CompositeImplementationProcessor(
                new ValidationProcessor(),
                new MethodImplementationProcessor(processingEnv)
        );
    }
}
