package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.ExperimentalSerializationApi;
import org.jetbrains.annotations.NotNull;
@ExperimentalSerializationApi
/* loaded from: classes3.dex */
public abstract class StructureKind extends SerialKind {

    /* loaded from: classes3.dex */
    public static final class CLASS extends StructureKind {
        @NotNull
        public static final CLASS INSTANCE = new CLASS();

        private CLASS() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class LIST extends StructureKind {
        @NotNull
        public static final LIST INSTANCE = new LIST();

        private LIST() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class MAP extends StructureKind {
        @NotNull
        public static final MAP INSTANCE = new MAP();

        private MAP() {
            super(null);
        }
    }

    /* loaded from: classes3.dex */
    public static final class OBJECT extends StructureKind {
        @NotNull
        public static final OBJECT INSTANCE = new OBJECT();

        private OBJECT() {
            super(null);
        }
    }

    private StructureKind() {
        super(null);
    }

    public /* synthetic */ StructureKind(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
