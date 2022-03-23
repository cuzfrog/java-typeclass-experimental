package com.github.cuzfrog.ap.process;

import javax.lang.model.element.Element;
import javax.lang.model.type.DeclaredType;
import java.util.Collections;
import java.util.List;

public final class ProcessingContext {
    private final Element element;
    private List<DeclaredType> typeclassTypes;

    public ProcessingContext(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public List<DeclaredType> getTypeclassTypes() {
        return typeclassTypes == null ? Collections.emptyList() : typeclassTypes;
    }

    public void setTypeclassTypes(List<DeclaredType> typeclassTypes) {
        this.typeclassTypes = typeclassTypes;
    }
}
