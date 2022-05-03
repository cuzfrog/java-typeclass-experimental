package com.github.cuzfrog.ap.process.compile;

import com.sun.tools.javac.processing.JavacProcessingEnvironment;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;

public interface SourceModifier {
    void insertImpl(DeclaredType targetType, Element implElem, ExecutableElement typeclassMethod);

    static SourceModifier create(ProcessingEnvironment processingEnv) {
        if (processingEnv instanceof JavacProcessingEnvironment) {
            return  new JavacSourceModifier((JavacProcessingEnvironment)processingEnv);
        }  else {
            throw new UnsupportedOperationException("only support javac for now");
        }
    }
}
