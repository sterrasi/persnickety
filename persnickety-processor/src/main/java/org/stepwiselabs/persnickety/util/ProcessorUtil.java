package org.stepwiselabs.persnickety.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stepwiselabs.flair.Strings;
import org.stepwiselabs.persnickety.processing.AnnotatedTypeElement;
import org.stepwiselabs.persnickety.processing.impl.AnnotatedTypeElementImpl;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProcessorUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorUtil.class);

    public ProcessorUtil() {
        throw new InstantiationError();
    }

    public static Set<AnnotatedTypeElement> getAnnotatedTypeElementsWithAnnotation(Set<? extends TypeElement> annotations,
                                                                                   RoundEnvironment roundEnvironment,
                                                                                   Class<? extends Annotation> annotationClass,
                                                                                   ProcessingEnvironment processingEnv) {
        return annotations.stream()
                .map(e -> (TypeElement) e)
                .filter(e -> e.getSimpleName().contentEquals(annotationClass.getSimpleName()))
                .map(e -> roundEnvironment.getElementsAnnotatedWith(e))
                .findFirst()
                .map(elements -> elements.stream()
                        .map(ee -> (AnnotatedTypeElement) new AnnotatedTypeElementImpl((TypeElement) ee, processingEnv))
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
    }

    public static boolean hasAnnotation(Element ele, Class annotationClass) {

        return ele.getAnnotationMirrors()
                .stream()
                .anyMatch(am -> am.getAnnotationType().asElement().getSimpleName()
                        .contentEquals(annotationClass.getSimpleName()));

    }

    public static Map<String, Object> getAnnotationParams(Element ele, Class annotationClass) {

        return ele.getAnnotationMirrors()
                .stream()
                .filter(am -> am.getAnnotationType().asElement().getSimpleName()
                        .contentEquals(annotationClass.getSimpleName()))
                .flatMap(am -> am.getElementValues().entrySet().stream())
                .collect(Collectors.toMap(entry -> entry.getKey().getSimpleName().toString(), entry -> entry.getValue().getValue()));
    }


    /**
     * TODO: this may no longer be needed
     * @param te
     * @return
     */
    public static String getClassName(TypeElement te) {
        Element finger = te;
        String result = te.getSimpleName().toString();
        while (finger.getEnclosingElement() != null) {
            finger = finger.getEnclosingElement();
            if (finger instanceof TypeElement) {
                result = finger.getSimpleName() + "$" + result;
            } else if (finger instanceof PackageElement) {

                if (Strings.notBlank(finger.getSimpleName())) {
                    result = ((PackageElement) finger)
                            .getQualifiedName() + "." + result;
                }
            }
        }
        return result;
    }
}

