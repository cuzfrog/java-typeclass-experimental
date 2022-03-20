package com.github.cuzfrog.ap.utils;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

public class Logger {
    private final Messager messager;

    public Logger(Messager messager) {
        this.messager = messager;
    }

    public void error(CharSequence msg, Element element) {
        messager.printMessage(Diagnostic.Kind.ERROR, msg, element);
    }
    public void warn(CharSequence msg, Element element) {
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, msg, element);
    }
    public void note(CharSequence msg, Element element) {
        messager.printMessage(Diagnostic.Kind.NOTE, msg, element);
    }
}
