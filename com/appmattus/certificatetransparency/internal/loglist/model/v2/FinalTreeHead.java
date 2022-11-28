package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Serializable
/* loaded from: classes.dex */
public final class FinalTreeHead {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String sha256RootHash;
    private final int treeSize;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<FinalTreeHead> serializer() {
            return FinalTreeHead$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ FinalTreeHead(int i2, @SerialName("tree_size") int i3, @SerialName("sha256_root_hash") String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i2 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i2, 3, FinalTreeHead$$serializer.INSTANCE.getDescriptor());
        }
        this.treeSize = i3;
        this.sha256RootHash = str;
        if (!(i3 >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(str.length() == 44)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public FinalTreeHead(int i2, @NotNull String sha256RootHash) {
        Intrinsics.checkNotNullParameter(sha256RootHash, "sha256RootHash");
        this.treeSize = i2;
        this.sha256RootHash = sha256RootHash;
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(sha256RootHash.length() == 44)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public static /* synthetic */ FinalTreeHead copy$default(FinalTreeHead finalTreeHead, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = finalTreeHead.treeSize;
        }
        if ((i3 & 2) != 0) {
            str = finalTreeHead.sha256RootHash;
        }
        return finalTreeHead.copy(i2, str);
    }

    @SerialName("sha256_root_hash")
    public static /* synthetic */ void getSha256RootHash$annotations() {
    }

    @SerialName("tree_size")
    public static /* synthetic */ void getTreeSize$annotations() {
    }

    @JvmStatic
    public static final void write$Self(@NotNull FinalTreeHead self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeIntElement(serialDesc, 0, self.treeSize);
        output.encodeStringElement(serialDesc, 1, self.sha256RootHash);
    }

    public final int component1() {
        return this.treeSize;
    }

    @NotNull
    public final String component2() {
        return this.sha256RootHash;
    }

    @NotNull
    public final FinalTreeHead copy(int i2, @NotNull String sha256RootHash) {
        Intrinsics.checkNotNullParameter(sha256RootHash, "sha256RootHash");
        return new FinalTreeHead(i2, sha256RootHash);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FinalTreeHead) {
            FinalTreeHead finalTreeHead = (FinalTreeHead) obj;
            return this.treeSize == finalTreeHead.treeSize && Intrinsics.areEqual(this.sha256RootHash, finalTreeHead.sha256RootHash);
        }
        return false;
    }

    @NotNull
    public final String getSha256RootHash() {
        return this.sha256RootHash;
    }

    public final int getTreeSize() {
        return this.treeSize;
    }

    public int hashCode() {
        return (Integer.hashCode(this.treeSize) * 31) + this.sha256RootHash.hashCode();
    }

    @NotNull
    public String toString() {
        return "FinalTreeHead(treeSize=" + this.treeSize + ", sha256RootHash=" + this.sha256RootHash + ')';
    }
}
