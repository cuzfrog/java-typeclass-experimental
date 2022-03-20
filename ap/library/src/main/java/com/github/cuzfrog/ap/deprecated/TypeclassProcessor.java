package com.github.cuzfrog.ap.deprecated;

import com.github.cuzfrog.ap.utils.Logger;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

final class TypeclassProcessor {
    private final Logger log;
    private final Types types;
    private final Elements elements;

    TypeclassProcessor(ProcessingEnvironment processingEnv) {
        this.log = new Logger(processingEnv.getMessager());
        this.types = processingEnv.getTypeUtils();
        this.elements = processingEnv.getElementUtils();
    }

    Set<ImplInfo> deriveImplInfo(TypeElement typeclass) {
        if (typeclass.getKind() != ElementKind.INTERFACE) {
            log.error("Typeclass can only be interface.", typeclass);
            return Collections.emptySet();
        }
        if (((DeclaredType)typeclass.asType()).getTypeArguments().isEmpty()) {
            log.error("Typeclass must have at least 1 type argument.", typeclass);
        }
        Set<TypeElement> implClasses = getImplementations(typeclass);
        return implClasses.stream().map(implClass -> {
            try {
                return new ImplInfo(typeclass, implClass, getTypeArgument(implClass, typeclass));
            } catch (Exception e) {
                log.error(e.getMessage(), implClass);
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());
    }

    private Set<TypeElement> getImplementations(TypeElement typeclass) {
        PackageElement packageElement = elements.getPackageOf(typeclass);
        Set<TypeElement> implClasses = packageElement.getEnclosedElements().stream()
                .filter(classElem -> classElem.getKind() == ElementKind.CLASS && classElem.getModifiers().contains(Modifier.FINAL))
                .map(TypeElement.class::cast)
                .filter(classElem -> types.isSubtype(classElem.asType(), types.erasure(typeclass.asType())))
                .collect(Collectors.toSet());
        if (implClasses.isEmpty()) {
            log.warn("No impl found for the typeclass, the searching scope is top-level final class in the same package:" + packageElement, typeclass);
        }
        return implClasses;
    }

    private DeclaredType getTypeArgument(TypeElement implClass, TypeElement typeclass) {
        log.note("Impl discovered for typeclass " + typeclass, implClass);
        DeclaredType typeclassType = findInterfaceType(implClass.asType(), types.erasure(typeclass.asType()));
        if (typeclassType == null) {
            throw new AssertionError(String.format("%s does not impl %s, it could be an error with this compilation.", implClass, typeclass));
        }

        List<? extends TypeMirror> typeArguments = typeclassType.getTypeArguments();
        if (typeArguments.isEmpty()) {
            throw new IllegalArgumentException("Impl of a typeclass must have type argument(s) provided.");
        }
        TypeMirror typeArgument = typeArguments.get(0);
        if (typeArgument.getKind() != TypeKind.DECLARED) {
            throw new IllegalArgumentException("First type arugment is the target type and must be declared type, but it's " + typeArgument);
        }
        return (DeclaredType) typeArgument;
    }

    private DeclaredType findInterfaceType(TypeMirror clazz, TypeMirror typeclassType) {
        for (TypeMirror directSuperType : types.directSupertypes(clazz)) {
            if (types.isSameType(types.erasure(directSuperType), typeclassType)) {
                return (DeclaredType) directSuperType;
            }
            if (directSuperType.getKind() == TypeKind.DECLARED) {
                DeclaredType found = findInterfaceType(directSuperType, typeclassType);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
