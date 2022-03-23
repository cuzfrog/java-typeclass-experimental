package com.github.cuzfrog.ap.process;

import com.github.cuzfrog.ap.Implementation;
import com.github.cuzfrog.ap.utils.Logger;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

final class MethodImplementationProcessor implements ImplementationProcessor {
    private final Logger log;
    private final Types types;
    private final Elements elements;

    MethodImplementationProcessor(ProcessingEnvironment processingEnv) {
        this.log = new Logger(processingEnv.getMessager());
        this.types = processingEnv.getTypeUtils();
        this.elements = processingEnv.getElementUtils();
//        if (processingEnv instanceof JavacProcessingEnvironment)
    }

    @Override
    public void process(ProcessingContext ctx) {
        ExecutableElement method = (ExecutableElement) ctx.getElement();
        if (!ctx.getTypeclassTypes().isEmpty()) {
            throw new AnnotationProcessingException("Method implementation does not support specifying typeclass", method);
        }


    }
}
