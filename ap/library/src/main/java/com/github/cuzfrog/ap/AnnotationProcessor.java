package com.github.cuzfrog.ap;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/** @noinspection unused*/
@AutoService(Processor.class)
public final class AnnotationProcessor extends AbstractProcessor {
    private static final Set<String> SUPPORTED_ANNOTATIONS = new HashSet<>(Collections.singleton(Typeclass.class.getName()));

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        TypeclassProcessor typeclassProcessor = new TypeclassProcessor(processingEnv);
        roundEnv.getElementsAnnotatedWith(Typeclass.class).forEach(typeclassProcessor::process);
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return SUPPORTED_ANNOTATIONS;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
