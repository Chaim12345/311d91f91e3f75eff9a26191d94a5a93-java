package kotlin.collections.builders;

import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class SerializedCollection implements Externalizable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = 0;
    public static final int tagList = 0;
    public static final int tagSet = 1;
    @NotNull
    private Collection<?> collection;
    private final int tag;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SerializedCollection() {
        this(r0, 0);
        List emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
    }

    public SerializedCollection(@NotNull Collection<?> collection, int i2) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        this.collection = collection;
        this.tag = i2;
    }

    private final Object readResolve() {
        return this.collection;
    }

    @Override // java.io.Externalizable
    public void readExternal(@NotNull ObjectInput input) {
        List createListBuilder;
        Collection<?> build;
        Set createSetBuilder;
        Intrinsics.checkNotNullParameter(input, "input");
        byte readByte = input.readByte();
        int i2 = readByte & 1;
        if ((readByte & (-2)) != 0) {
            throw new InvalidObjectException("Unsupported flags value: " + ((int) readByte) + '.');
        }
        int readInt = input.readInt();
        if (readInt < 0) {
            throw new InvalidObjectException("Illegal size value: " + readInt + '.');
        }
        int i3 = 0;
        if (i2 == 0) {
            createListBuilder = CollectionsKt__CollectionsJVMKt.createListBuilder(readInt);
            while (i3 < readInt) {
                createListBuilder.add(input.readObject());
                i3++;
            }
            build = CollectionsKt__CollectionsJVMKt.build(createListBuilder);
        } else if (i2 != 1) {
            throw new InvalidObjectException("Unsupported collection type tag: " + i2 + '.');
        } else {
            createSetBuilder = SetsKt__SetsJVMKt.createSetBuilder(readInt);
            while (i3 < readInt) {
                createSetBuilder.add(input.readObject());
                i3++;
            }
            build = SetsKt__SetsJVMKt.build(createSetBuilder);
        }
        this.collection = build;
    }

    @Override // java.io.Externalizable
    public void writeExternal(@NotNull ObjectOutput output) {
        Intrinsics.checkNotNullParameter(output, "output");
        output.writeByte(this.tag);
        output.writeInt(this.collection.size());
        Iterator<?> it = this.collection.iterator();
        while (it.hasNext()) {
            output.writeObject(it.next());
        }
    }
}
