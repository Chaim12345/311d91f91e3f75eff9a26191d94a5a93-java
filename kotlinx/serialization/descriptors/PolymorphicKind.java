package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.ExperimentalSerializationApi;
import org.jetbrains.annotations.NotNull;
@ExperimentalSerializationApi
/* loaded from: classes3.dex */
public abstract class PolymorphicKind extends SerialKind {

    /* loaded from: classes3.dex */
    public static final class OPEN extends PolymorphicKind {
        @NotNull
        public static final OPEN INSTANCE = new OPEN();

        private OPEN() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class SEALED extends PolymorphicKind {
        @NotNull
        public static final SEALED INSTANCE = new SEALED();

        private SEALED() {
            super(null);
        }
    }

    private PolymorphicKind() {
        super(null);
    }

    public /* synthetic */ PolymorphicKind(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
