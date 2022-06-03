package com.github.cuzfrog.ap.deprecated;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;

public final class ImplInfo {
    private final TypeElement typeclass;
    private final TypeElement implClass;
    private final DeclaredType targetType;

    ImplInfo(TypeElement typeclass, TypeElement implClass, DeclaredType targetType) {
        this.typeclass = typeclass;
        this.implClass = implClass;
        this.targetType = targetType;
    }

    public TypeElement getTypeclass() {
        return typeclass;
    }

    public TypeElement getImplClass() {
        return implClass;
    }

    public DeclaredType getTargetType() {
        return targetType;
    }
}
