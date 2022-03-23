package com.github.cuzfrog.ap.process;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

final class CompositeImplementationProcessor implements ImplementationProcessor {
    private final ImplementationProcessor validator;
    private final ImplementationProcessor methodProcessor;

    CompositeImplementationProcessor(ImplementationProcessor validator, ImplementationProcessor methodProcessor) {
        this.validator = validator;
        this.methodProcessor = methodProcessor;
    }

    @Override
    public void process(Element element) {
        validator.process(element);
        if (element.getKind() == ElementKind.METHOD) {
            methodProcessor.process(element);
        }
        throw new UnsupportedOperationException("Unsupported element kind:" + element);
    }
}
