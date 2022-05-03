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
        JCTree.JCMethodDecl method = createMethod(typeclassMethod);
        objTree.defs = objTree.defs.append(method);
        System.out.println(objTree);
    }

    private JCTree.JCMethodDecl createMethod(ExecutableElement typeclassMethod) {
        JCTree.JCExpression returnType = treeMaker.Type((Type) typeclassMethod.getReturnType());

        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
        statements.append(treeMaker.Return(treeMaker.Literal("abc")));

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
