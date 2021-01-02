package org.stepwiselabs.persnickety.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Creates a join between the primary key of the host model and the table column specified in the 'target' param.
 *
 * The {@code target} is a foreign key column given in {@code (table name).(column name)} syntax.
 * The {@code constraint} is an optional additive constraint applied to the join
 * The {@code keyColumn} is the optional key of the model object to return. If the model corresponds to the
 *      target table this parameter does not have to be specified.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Join {

    String target();
    String constraint() default ""; // optional;
    String keyColumn() default ""; // optional
}
