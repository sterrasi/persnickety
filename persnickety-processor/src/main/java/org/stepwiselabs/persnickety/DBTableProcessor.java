package org.stepwiselabs.persnickety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("org.stepwiselabs.persnickety.annotation.*")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
//@AutoService(Processor.class)
public class DBTableProcessor extends AbstractProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBTableProcessor.class);

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "@@@ Can you see this???");
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.OTHER, "@@@ Can you see this???");
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "@@@ Warn Can you see this???");
        LOGGER.info("@@@ called process");
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnvironment
                    .getElementsAnnotatedWith(annotation);


        }

        return true;
    }
}
