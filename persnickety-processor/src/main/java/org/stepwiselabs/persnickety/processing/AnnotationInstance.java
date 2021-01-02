package org.stepwiselabs.persnickety.processing;

import java.util.Optional;

public interface AnnotationInstance<T> {

    Class<T> getAnnotationClass();

    Optional<String> getStringParam(String key);
    String getStringParam(String key, String defaultValue);

    Optional<Class> getClassParam(String key);
    Class getClassParam(String key, Class defaultValue);

}
