package kotlinx.serialization;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationTarget;
@Target({})
@ExperimentalSerializationApi
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.PROPERTY})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes3.dex */
public @interface EncodeDefault {

    @ExperimentalSerializationApi
    /* loaded from: classes3.dex */
    public enum Mode {
        ALWAYS,
        NEVER
    }

    Mode mode() default Mode.ALWAYS;
}
