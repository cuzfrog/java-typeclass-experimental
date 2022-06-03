package com.github.cuzfrog.ap.process;

import javax.lang.model.element.ElementKind;

final class CompositeImplementationProcessor implements ImplementationProcessor {
    private final ImplementationProcessor annotationValueExtractor;
    private final ImplementationProcessor validator;
    private final ImplementationProcessor methodProcessor;

    CompositeImplementationProcessor(ImplementationProcessor annotationValueExtractor,
                                     ImplementationProcessor validator,
                                     ImplementationProcessor methodProcessor) {
        this.annotationValueExtractor = annotationValueExtractor;
        this.validator = validator;
        this.methodProcessor = methodProcessor;
    }

    @Override
    public void process(ProcessingContext ctx) {
        annotationValueExtractor.process(ctx);
        validator.process(ctx);
        if (ctx.getElement().getKind() == ElementKind.METHOD) {
            methodProcessor.process(ctx);
        } else if (ctx.getElement().getKind() == ElementKind.FIELD) {
            // TODO
        } else {
            throw new AnnotationProcessingException("Unsupported element kind:" + ctx.getElement().getKind(), ctx.getElement());
        }
    }
}
