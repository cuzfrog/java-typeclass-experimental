package com.github.cuzfrog.ap.process;

import javax.lang.model.element.Element;

public final class AnnotationProcessingException extends RuntimeException {
    private final Element element;
    public AnnotationProcessingException(String message, Element element) {
        super(message);
        this.element = element;
    }

    public Element getElement() {
        return element;
    }
}
