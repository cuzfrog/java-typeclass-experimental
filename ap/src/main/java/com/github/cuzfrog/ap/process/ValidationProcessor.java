package com.github.cuzfrog.ap.process;

import com.github.cuzfrog.ap.Implementation;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.type.DeclaredType;
import java.util.stream.Stream;

final class ValidationProcessor extends AbstractImplementationProcessor {
    ValidationProcessor(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }

    @Override
    public void process(ProcessingContext ctx) {
//
//
//        for (Class<?> typeclass : annotation.value()) {
//            if (!typeclass.isInterface()) {
//                throw new AnnotationProcessingException("Typeclass must be interface, but it's " + typeclass, element);
//            }
//        }
    }
}
