package org.stepwiselabs.persnickety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stepwiselabs.flair.exceptions.NotFoundException;
import org.stepwiselabs.persnickety.annotation.DAO;
import org.stepwiselabs.persnickety.processing.AnnotatedTypeElement;
import org.stepwiselabs.persnickety.processing.AnnotationInstance;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.stepwiselabs.persnickety.util.ProcessorUtil.getAnnotatedTypeElementsWithAnnotation;

@SupportedAnnotationTypes("org.stepwiselabs.persnickety.annotation.*")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class DBTableProcessor extends AbstractProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBTableProcessor.class);

    private Messager messager;
    private Elements elements;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.elements = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "@@@ Warn Can you see this???");

        /**
         * Get DAO Elements
         */
        Set<AnnotatedTypeElement> daoElements = getAnnotatedTypeElementsWithAnnotation(annotations, roundEnvironment,
                DAO.class, processingEnv);
        if (daoElements.isEmpty()) {
            LOGGER.info("No DAO Elements to process");
            return false;
        }

        // process each DAO element
        for (AnnotatedTypeElement daoEle : daoElements) {

            AnnotationInstance<DAO> daoAnnotation = daoEle.getRequiredAnnotationInstance(DAO.class);
            LOGGER.info(String.format("Class '%s' @DAO(table='%s', dto='%s')", daoEle.getClassName(),
                    daoAnnotation.getStringParam("table", "null"),
                    daoAnnotation.getStringParam("dto", "null")));


//            DAO da = daoEle.getAnnotation(DAO.class);
//            String table = da.table();
//            Class dto = da.dto();


        }

//        LOGGER.info("@@@ called process");
//        for (TypeElement annotation : annotations) {
//
//            Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(annotation);
//
//            LOGGER.info("for annotation " + annotation.getSimpleName() + " kind " + annotation.getKind().name());
//
//            for (Element ele : annotatedElements) {
//                LOGGER.info("  :element " + ele.getSimpleName());
//                LOGGER.info("       :kind " + ele.getKind().name());
//                LOGGER.info("       :enclosing element " + ele.getEnclosingElement().getSimpleName() +
//                        " (" + ele.getEnclosingElement().getKind().name() + ")");
//            }
//        }

        return false;
    }


}
