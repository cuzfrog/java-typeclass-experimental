package com.github.cuzfrog.ap.process.compile;

import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import java.util.Collections;

final class JavacSourceModifier implements SourceModifier {
    private final JavacProcessingEnvironment javacEnv;
    private final JavacElements elements;
    private final TreeMaker treeMaker;
    private final Trees trees;

    JavacSourceModifier(JavacProcessingEnvironment javacEnv) {
        this.javacEnv = javacEnv;
        this.elements = javacEnv.getElementUtils();
        this.treeMaker = TreeMaker.instance(javacEnv.getContext());
        this.trees = Trees.instance(javacEnv);
    }

    @Override
    public void insertImpl(DeclaredType targetType, Element implElem, ExecutableElement typeclassMethod) {
        JCTree.JCClassDecl objTree = (JCTree.JCClassDecl) elements.getTree(targetType.asElement());
        JCTree.JCMethodDecl method = createMethod(typeclassMethod, implElem);
        objTree.defs = objTree.defs.append(method);
        System.out.println(objTree);
    }

    private JCTree.JCMethodDecl createMethod(ExecutableElement typeclassMethod, Element implElem) {
        JCTree.JCExpression returnType = treeMaker.Type((Type) typeclassMethod.getReturnType());

        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
        TypeElement implClass = (TypeElement) implElem.getEnclosingElement();
        JCTree.JCExpression implClassExpr = treeMaker.Type((Type)implClass.asType());
        final JCTree.JCStatement call;
        if (implElem.getKind() == ElementKind.METHOD) {
            ExecutableElement calleeMethod = (ExecutableElement) implElem;
            JCTree.JCExpression calleeClassId = treeMaker.Ident(elements.getName(implClass.getQualifiedName()))
                    .setType((Type)implClass.asType());
            JCTree.JCExpression callee = treeMaker.Select(implClassExpr, elements.getName(calleeMethod.getSimpleName()))
                    .setType((Type) typeclassMethod.getReturnType());
            JCTree.JCExpression arg = treeMaker.This((Type.ClassType)calleeMethod.getParameters().get(0).asType());
            JCTree.JCMethodInvocation methodInvoc = treeMaker.App(callee, List.of(arg));
            methodInvoc.setType((Type) typeclassMethod.getReturnType());
            call = treeMaker.Call(methodInvoc);
        } else if (implElem.getKind() == ElementKind.FIELD) {
            throw new AssertionError("Not implemented!");
        } else {
            throw new UnsupportedOperationException("Not support elem kind:" + implElem.getKind());
        }

        statements.append(call);

        JCTree.JCBlock methodBody = treeMaker.Block(0, statements.toList());
        return treeMaker.MethodDef(
                treeMaker.Modifiers(Flags.PUBLIC),
                elements.getName(typeclassMethod.getSimpleName()) ,
                returnType,
                List.nil(),
                List.nil(),
                List.nil(),
                methodBody,
                null
        );
    }
}
