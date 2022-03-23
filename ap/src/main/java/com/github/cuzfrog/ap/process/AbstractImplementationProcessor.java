package com.github.cuzfrog.ap.process;

import com.github.cuzfrog.ap.utils.Logger;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

abstract class AbstractImplementationProcessor implements ImplementationProcessor {
    final Logger log;
    final Types types;
    final Elements elements;

    AbstractImplementationProcessor(ProcessingEnvironment processingEnv) {
        this.log = new Logger(processingEnv.getMessager());
        this.types = processingEnv.getTypeUtils();
        this.elements = processingEnv.getElementUtils();
    }
}
