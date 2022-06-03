package com.github.cuzfrog.ap.process;

import com.github.cuzfrog.ap.process.compile.SourceModifier;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import java.util.List;
import java.util.stream.Collectors;

final class MethodImplementationProcessor implements ImplementationProcessor {
    private final SourceModifier sourceModifier;

    MethodImplementationProcessor(ProcessingEnvironment processingEnv) {
        this.sourceModifier = SourceModifier.create(processingEnv);
    }

    @Override
    public void process(ProcessingContext ctx) {
        ExecutableElement method = (ExecutableElement) ctx.getElement();

        List<? extends VariableElement> params = method.getParameters();
        if (params.isEmpty()) {
            throw new AnnotationProcessingException("Method implementation must have the 1st param as the self reference", method);
        }
        VariableElement selfParam = params.get(0);

        TypeElement typeclassElement = (TypeElement) ctx.getTypeclassType().asElement();
        List<ExecutableElement> typeclassMethods = typeclassElement.getEnclosedElements().stream()
                .filter(elem -> elem.getKind() == ElementKind.METHOD && !elem.getModifiers().contains(Modifier.STATIC))
                .map(elem -> (ExecutableElement)elem)
                .collect(Collectors.toList());

        sourceModifier.insertImpl((DeclaredType) (selfParam.asType()), method, typeclassMethods.get(0));
    }
}
