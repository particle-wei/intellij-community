package org.jetbrains.annotations;

import java.lang.annotation.*;

/**
 * An element annotated with Nullable claims <code>null</code> value is perfectly <em>valid</em>
 * to return (for methods), pass to (parameters) and hold (local variables and fields).
 * Apart from documentation puropses this annotation intended to be used by static analysis tools
 * to validate against probable run-time errors and element contract violations.
 * @author max
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface Nullable {
  String documentation() default "";
}
