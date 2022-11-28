package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public abstract class PrimitiveKind extends SerialKind {

    /* loaded from: classes3.dex */
    public static final class BOOLEAN extends PrimitiveKind {
        @NotNull
        public static final BOOLEAN INSTANCE = new BOOLEAN();

        private BOOLEAN() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class BYTE extends PrimitiveKind {
        @NotNull
        public static final BYTE INSTANCE = new BYTE();

        private BYTE() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class CHAR extends PrimitiveKind {
        @NotNull
        public static final CHAR INSTANCE = new CHAR();

        private CHAR() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class DOUBLE extends PrimitiveKind {
        @NotNull
        public static final DOUBLE INSTANCE = new DOUBLE();

        private DOUBLE() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class FLOAT extends PrimitiveKind {
        @NotNull
        public static final FLOAT INSTANCE = new FLOAT();

        private FLOAT() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class INT extends PrimitiveKind {
        @NotNull
        public static final INT INSTANCE = new INT();

        private INT() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class LONG extends PrimitiveKind {
        @NotNull
        public static final LONG INSTANCE = new LONG();

        private LONG() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class SHORT extends PrimitiveKind {
        @NotNull
        public static final SHORT INSTANCE = new SHORT();

        private SHORT() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class STRING extends PrimitiveKind {
        @NotNull
        public static final STRING INSTANCE = new STRING();

        private STRING() {
            super(null);
        }
    }

    private PrimitiveKind() {
        super(null);
    }

    public /* synthetic */ PrimitiveKind(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
