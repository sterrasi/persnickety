package org.stepwiselabs.persnickety.processing.impl;

import org.stepwiselabs.flair.Strings;
import org.stepwiselabs.persnickety.processing.AnnotationInstance;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Optional;

public class AnnotationInstanceImpl<T extends Annotation> implements AnnotationInstance<T> {

    private final Class<T> clazz;
    private final Map<String, Object> params;

    public AnnotationInstanceImpl(Class<T> clazz, Map<String, Object> params) {
        this.clazz = clazz;
        this.params = params;
    }

    @Override
    public Class<T> getAnnotationClass() {
        return clazz;
    }

    @Override
    public Optional<String> getStringParam(String key) {

        Object val = params.get(key);
        if (val == null) {
            return Optional.empty();
        }
        String sVal = val.toString();

        if (Strings.isBlank(sVal)) {
            return Optional.empty();
        }
        return Optional.of(sVal);
    }

    @Override
    public String getStringParam(String key, String defaultValue) {
        return getStringParam(key).orElse(defaultValue);
    }

    @Override
    public Optional<Class> getClassParam(String key) {
        Object val = params.get(key);
        if (val == null) {
            return Optional.empty();
        }
        if (!(val instanceof Class)) {
            throw new IllegalStateException(String.format("%s attribute on %s annotation is not of type Class",
                    key, clazz.getSimpleName()));
        }
        return Optional.of((Class) val);
    }

    @Override
    public Class getClassParam(String key, Class defaultValue) {
        return getClassParam(key).orElse(defaultValue);
    }

}
