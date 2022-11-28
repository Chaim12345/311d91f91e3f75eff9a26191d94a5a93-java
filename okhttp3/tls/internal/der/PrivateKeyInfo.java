package okhttp3.tls.internal.der;

import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class PrivateKeyInfo {
    @NotNull
    private final AlgorithmIdentifier algorithmIdentifier;
    @NotNull
    private final ByteString privateKey;
    private final long version;

    public PrivateKeyInfo(long j2, @NotNull AlgorithmIdentifier algorithmIdentifier, @NotNull ByteString privateKey) {
        Intrinsics.checkNotNullParameter(algorithmIdentifier, "algorithmIdentifier");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        this.version = j2;
        this.algorithmIdentifier = algorithmIdentifier;
        this.privateKey = privateKey;
    }

    public static /* synthetic */ PrivateKeyInfo copy$default(PrivateKeyInfo privateKeyInfo, long j2, AlgorithmIdentifier algorithmIdentifier, ByteString byteString, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = privateKeyInfo.version;
        }
        if ((i2 & 2) != 0) {
            algorithmIdentifier = privateKeyInfo.algorithmIdentifier;
        }
        if ((i2 & 4) != 0) {
            byteString = privateKeyInfo.privateKey;
        }
        return privateKeyInfo.copy(j2, algorithmIdentifier, byteString);
    }

    public final long component1() {
        return this.version;
    }

    @NotNull
    public final AlgorithmIdentifier component2() {
        return this.algorithmIdentifier;
    }

    @NotNull
    public final ByteString component3() {
        return this.privateKey;
    }

    @NotNull
    public final PrivateKeyInfo copy(long j2, @NotNull AlgorithmIdentifier algorithmIdentifier, @NotNull ByteString privateKey) {
        Intrinsics.checkNotNullParameter(algorithmIdentifier, "algorithmIdentifier");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        return new PrivateKeyInfo(j2, algorithmIdentifier, privateKey);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PrivateKeyInfo) {
            PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) obj;
            return this.version == privateKeyInfo.version && Intrinsics.areEqual(this.algorithmIdentifier, privateKeyInfo.algorithmIdentifier) && Intrinsics.areEqual(this.privateKey, privateKeyInfo.privateKey);
        }
        return false;
    }

    @NotNull
    public final AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.algorithmIdentifier;
    }

    @NotNull
    public final ByteString getPrivateKey() {
        return this.privateKey;
    }

    public final long getVersion() {
        return this.version;
    }

    public int hashCode() {
        return ((((0 + ((int) this.version)) * 31) + this.algorithmIdentifier.hashCode()) * 31) + this.privateKey.hashCode();
    }

    @NotNull
    public String toString() {
        return "PrivateKeyInfo(version=" + this.version + ", algorithmIdentifier=" + this.algorithmIdentifier + ", privateKey=" + this.privateKey + ')';
    }
}
