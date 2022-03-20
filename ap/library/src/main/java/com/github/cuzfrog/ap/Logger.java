package com.github.cuzfrog.ap;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

final class Logger {
    private final Messager messager;

    Logger(Messager messager) {
        this.messager = messager;
    }

    void error(CharSequence msg, Element element) {
        messager.printMessage(Diagnostic.Kind.ERROR, msg, element);
    }
    void warn(CharSequence msg, Element element) {
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, msg, element);
    }
    void note(CharSequence msg, Element element) {
        messager.printMessage(Diagnostic.Kind.NOTE, msg, element);
    }
}
