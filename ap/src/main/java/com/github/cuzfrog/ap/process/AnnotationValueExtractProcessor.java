package com.github.cuzfrog.ap.process;

import com.github.cuzfrog.ap.Implementation;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class AnnotationValueExtractProcessor extends AbstractImplementationProcessor {
    AnnotationValueExtractProcessor(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }

    @Override @SuppressWarnings("unchecked")
    public void process(ProcessingContext ctx) {
        AnnotationValue annotationValue = getAnnotationMirror(ctx.getElement());
        List<DeclaredType> types = annotationValue == null ? Collections.emptyList() : (List<DeclaredType>)annotationValue.getValue();
        ctx.setTypeclassTypes(types);
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
