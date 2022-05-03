package com.github.cuzfrog.ap.process;

import com.github.cuzfrog.ap.Implementation;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import java.util.Map;

final class AnnotationValueExtractProcessor extends AbstractImplementationProcessor {
    AnnotationValueExtractProcessor(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }

    @Override
    public void process(ProcessingContext ctx) {
        AnnotationValue annotationValue = getAnnotationMirror(ctx.getElement());
        DeclaredType type = annotationValue == null ? null : (DeclaredType)annotationValue.getValue();
        ctx.setTypeclassType(type);
    }

    private static AnnotationValue getAnnotationMirror(Element element) {
        for(AnnotationMirror m : element.getAnnotationMirrors()) {
            if(m.getAnnotationType().toString().equals(Implementation.class.getName())) {
                for(Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : m.getElementValues().entrySet() ) {
                    if(entry.getKey().getSimpleName().toString().equals("value")) {
                        return entry.getValue();
                    }
                }
            }
        }
        return null;
    }

}
