package okhttp3.tls.internal.der;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SubjectPublicKeyInfo {
    @NotNull
    private final AlgorithmIdentifier algorithm;
    @NotNull
    private final BitString subjectPublicKey;

    public SubjectPublicKeyInfo(@NotNull AlgorithmIdentifier algorithm, @NotNull BitString subjectPublicKey) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(subjectPublicKey, "subjectPublicKey");
        this.algorithm = algorithm;
        this.subjectPublicKey = subjectPublicKey;
    }

    public static /* synthetic */ SubjectPublicKeyInfo copy$default(SubjectPublicKeyInfo subjectPublicKeyInfo, AlgorithmIdentifier algorithmIdentifier, BitString bitString, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            algorithmIdentifier = subjectPublicKeyInfo.algorithm;
        }
        if ((i2 & 2) != 0) {
            bitString = subjectPublicKeyInfo.subjectPublicKey;
        }
        return subjectPublicKeyInfo.copy(algorithmIdentifier, bitString);
    }

    @NotNull
    public final AlgorithmIdentifier component1() {
        return this.algorithm;
    }

    @NotNull
    public final BitString component2() {
        return this.subjectPublicKey;
    }

    @NotNull
    public final SubjectPublicKeyInfo copy(@NotNull AlgorithmIdentifier algorithm, @NotNull BitString subjectPublicKey) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(subjectPublicKey, "subjectPublicKey");
        return new SubjectPublicKeyInfo(algorithm, subjectPublicKey);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SubjectPublicKeyInfo) {
            SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo) obj;
            return Intrinsics.areEqual(this.algorithm, subjectPublicKeyInfo.algorithm) && Intrinsics.areEqual(this.subjectPublicKey, subjectPublicKeyInfo.subjectPublicKey);
        }
        return false;
    }

    @NotNull
    public final AlgorithmIdentifier getAlgorithm() {
        return this.algorithm;
    }

    @NotNull
    public final BitString getSubjectPublicKey() {
        return this.subjectPublicKey;
    }

    public int hashCode() {
        return (this.algorithm.hashCode() * 31) + this.subjectPublicKey.hashCode();
    }

    @NotNull
    public String toString() {
        return "SubjectPublicKeyInfo(algorithm=" + this.algorithm + ", subjectPublicKey=" + this.subjectPublicKey + ')';
    }
}
