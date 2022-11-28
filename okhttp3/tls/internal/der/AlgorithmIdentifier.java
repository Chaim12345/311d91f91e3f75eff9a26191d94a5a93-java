package okhttp3.tls.internal.der;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AlgorithmIdentifier {
    @NotNull
    private final String algorithm;
    @Nullable
    private final Object parameters;

    public AlgorithmIdentifier(@NotNull String algorithm, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.algorithm = algorithm;
        this.parameters = obj;
    }

    public static /* synthetic */ AlgorithmIdentifier copy$default(AlgorithmIdentifier algorithmIdentifier, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            str = algorithmIdentifier.algorithm;
        }
        if ((i2 & 2) != 0) {
            obj = algorithmIdentifier.parameters;
        }
        return algorithmIdentifier.copy(str, obj);
    }

    @NotNull
    public final String component1() {
        return this.algorithm;
    }

    @Nullable
    public final Object component2() {
        return this.parameters;
    }

    @NotNull
    public final AlgorithmIdentifier copy(@NotNull String algorithm, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        return new AlgorithmIdentifier(algorithm, obj);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AlgorithmIdentifier) {
            AlgorithmIdentifier algorithmIdentifier = (AlgorithmIdentifier) obj;
            return Intrinsics.areEqual(this.algorithm, algorithmIdentifier.algorithm) && Intrinsics.areEqual(this.parameters, algorithmIdentifier.parameters);
        }
        return false;
    }

    @NotNull
    public final String getAlgorithm() {
        return this.algorithm;
    }

    @Nullable
    public final Object getParameters() {
        return this.parameters;
    }

    public int hashCode() {
        int hashCode = this.algorithm.hashCode() * 31;
        Object obj = this.parameters;
        return hashCode + (obj == null ? 0 : obj.hashCode());
    }

    @NotNull
    public String toString() {
        return "AlgorithmIdentifier(algorithm=" + this.algorithm + ", parameters=" + this.parameters + ')';
    }
}
