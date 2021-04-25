package org.stepwiselabs.persnickety.processing;

import java.lang.annotation.Annotation;
import java.util.Optional;

public interface AnnotatedTypeElement {

    String getClassName();
    boolean hasAnnotation(Class annotationClass);
    <T extends Annotation> Optional<AnnotationInstance<T>> getAnnotationInstance(Class<T> annotationClass);
    <T extends Annotation> AnnotationInstance<T> getRequiredAnnotationInstance(Class<T> annotationClass);

}
