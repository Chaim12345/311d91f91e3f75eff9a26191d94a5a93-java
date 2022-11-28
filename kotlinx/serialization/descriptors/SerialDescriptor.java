package kotlinx.serialization.descriptors;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface SerialDescriptor {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @NotNull
        public static List<Annotation> getAnnotations(@NotNull SerialDescriptor serialDescriptor) {
            Intrinsics.checkNotNullParameter(serialDescriptor, "this");
            return CollectionsKt.emptyList();
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void getAnnotations$annotations() {
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void getElementsCount$annotations() {
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void getKind$annotations() {
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void getSerialName$annotations() {
        }

        public static boolean isInline(@NotNull SerialDescriptor serialDescriptor) {
            Intrinsics.checkNotNullParameter(serialDescriptor, "this");
            return false;
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void isInline$annotations() {
        }

        public static boolean isNullable(@NotNull SerialDescriptor serialDescriptor) {
            Intrinsics.checkNotNullParameter(serialDescriptor, "this");
            return false;
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void isNullable$annotations() {
        }
    }

    @NotNull
    List<Annotation> getAnnotations();

    @ExperimentalSerializationApi
    @NotNull
    List<Annotation> getElementAnnotations(int i2);

    @ExperimentalSerializationApi
    @NotNull
    SerialDescriptor getElementDescriptor(int i2);

    @ExperimentalSerializationApi
    int getElementIndex(@NotNull String str);

    @ExperimentalSerializationApi
    @NotNull
    String getElementName(int i2);

    int getElementsCount();

    @NotNull
    SerialKind getKind();

    @NotNull
    String getSerialName();

    @ExperimentalSerializationApi
    boolean isElementOptional(int i2);

    boolean isInline();

    boolean isNullable();
}
