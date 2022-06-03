package com.github.cuzfrog.ap;

import com.github.cuzfrog.ap.process.AnnotationProcessingException;
import com.github.cuzfrog.ap.process.ImplementationProcessor;
import com.github.cuzfrog.ap.process.ProcessingContext;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/** @noinspection unused*/
public final class AnnotationProcessor extends AbstractProcessor {
    private static final Set<String> SUPPORTED_ANNOTATIONS = new HashSet<>(Collections.singleton(Implementation.class.getName()));

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        ImplementationProcessor processor = ImplementationProcessor.create(processingEnv);
        for (Element element : roundEnv.getElementsAnnotatedWith(Implementation.class)) {
            ProcessingContext processingContext = new ProcessingContext(element);
            try {
                processor.process(processingContext);
            } catch (AnnotationProcessingException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage(), e.getElement());
            }
        }
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
