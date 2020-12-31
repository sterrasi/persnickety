package org.stepwiselabs.persnickety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("org.stepwiselabs.persnickety.annotation.*")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class DBTableProcessor extends AbstractProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBTableProcessor.class);

    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "@@@ Warn Can you see this???");

        for ( Element rootEle: roundEnvironment.getRootElements()){
            LOGGER.info("rootElement " + rootEle.getSimpleName());
        }

        LOGGER.info("@@@ called process");
        for (TypeElement annotation : annotations) {



            Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(annotation);

            LOGGER.info("for annotation " + annotation.getSimpleName());
            for( Element ele: annotatedElements){
                LOGGER.info("  :element " + ele.getSimpleName());
                LOGGER.info("       :kind " + ele.getKind().name());
                LOGGER.info("       :enclosing element " + ele.getEnclosingElement().getSimpleName() +
                        " (" + ele.getEnclosingElement().getKind().name() + ")");
            }



        }

        return false;
    }
}
