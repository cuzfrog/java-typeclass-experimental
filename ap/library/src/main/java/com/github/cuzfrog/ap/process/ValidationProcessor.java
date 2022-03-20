package com.github.cuzfrog.ap.process;

import com.github.cuzfrog.ap.Implementation;

import javax.lang.model.element.Element;
import java.util.stream.Stream;

final class ValidationProcessor implements ImplementationProcessor {
    @Override
    public void process(Element element) {
        Implementation annotation = element.getAnnotation(Implementation.class);
        for (Class<?> typeclass : annotation.value()) {
            if (!typeclass.isInterface()) {
                throw new AnnotationProcessingException("Typeclass must be interface, but it's " + typeclass, element);
            }
        }
    }
}
