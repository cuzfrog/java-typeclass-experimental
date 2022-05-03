package com.github.cuzfrog.ap.process;

import javax.lang.model.element.Element;
import javax.lang.model.type.DeclaredType;

public final class ProcessingContext {
    private final Element element;
    private DeclaredType typeclassType;

    public ProcessingContext(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public DeclaredType getTypeclassType() {
        return typeclassType;
    }

    public void setTypeclassType(DeclaredType typeclassType) {
        this.typeclassType = typeclassType;
    }
}
