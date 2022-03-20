package com.github.cuzfrog.ap;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/** @noinspection unused*/
@AutoService(Processor.class)
public final class AnnotationProcessor extends AbstractProcessor {
    private static final Set<String> SUPPORTED_ANNOTATIONS = ImmutableSet.of(Implementation.class.getName());

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Implementation.class)) {

        }

        return false;
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
