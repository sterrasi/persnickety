package org.stepwiselabs.persnickety.processing.impl;

import org.stepwiselabs.persnickety.processing.AnnotatedTypeElement;
import org.stepwiselabs.persnickety.processing.AnnotationInstance;
import org.stepwiselabs.persnickety.util.ProcessorUtil;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.Set;

import static org.stepwiselabs.persnickety.util.ProcessorUtil.getAnnotationParams;

public class AnnotatedTypeElementImpl implements AnnotatedTypeElement {

    private final TypeElement typeElement;
    private final ProcessingEnvironment processingEnv;

    public AnnotatedTypeElementImpl(TypeElement typeElement, ProcessingEnvironment processingEnv) {
        this.typeElement = typeElement;
        this.processingEnv = processingEnv;
    }

    @Override
    public String getClassName() {
        return ProcessorUtil.getClassName(typeElement);
    }

    //TODO: I am here
    public Set<Element> getMethodElements() {
        for (Element member : processingEnv.getElementUtils().getAllMembers(typeElement)) {

            // member is potentially an ExecutableElement (method)
            processingEnv.getElementUtils().getAllAnnotationMirrors(member); // to collect all annotations

        }

        return null;
    }

    @Override
    public boolean hasAnnotation(Class annotationClass) {
        return ProcessorUtil.hasAnnotation(typeElement, annotationClass);
    }

    @Override
    public <T extends Annotation> Optional<AnnotationInstance<T>> getAnnotationInstance(Class<T> annotationClass) {

        if (!hasAnnotation(annotationClass)) {
            return Optional.empty();
        }
        return Optional.of(new AnnotationInstanceImpl<T>(annotationClass,
                getAnnotationParams(typeElement, annotationClass)));
    }

    @Override
    public <T extends Annotation> AnnotationInstance<T> getRequiredAnnotationInstance(Class<T> annotationClass) {

        return getAnnotationInstance(annotationClass).orElseThrow(() -> new IllegalStateException(String.format(
                "Cannot find required annotation '%s' on Class '%s'", annotationClass.getSimpleName(),
                typeElement.getQualifiedName())));
    }
}
