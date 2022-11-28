package okhttp3.tls.internal.der;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class BasicConstraints {
    private final boolean ca;
    @Nullable
    private final Long maxIntermediateCas;

    public BasicConstraints(boolean z, @Nullable Long l2) {
        this.ca = z;
        this.maxIntermediateCas = l2;
    }

    public static /* synthetic */ BasicConstraints copy$default(BasicConstraints basicConstraints, boolean z, Long l2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = basicConstraints.ca;
        }
        if ((i2 & 2) != 0) {
            l2 = basicConstraints.maxIntermediateCas;
        }
        return basicConstraints.copy(z, l2);
    }

    public final boolean component1() {
        return this.ca;
    }

    @Nullable
    public final Long component2() {
        return this.maxIntermediateCas;
    }

    @NotNull
    public final BasicConstraints copy(boolean z, @Nullable Long l2) {
        return new BasicConstraints(z, l2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BasicConstraints) {
            BasicConstraints basicConstraints = (BasicConstraints) obj;
            return this.ca == basicConstraints.ca && Intrinsics.areEqual(this.maxIntermediateCas, basicConstraints.maxIntermediateCas);
        }
        return false;
    }

    public final boolean getCa() {
        return this.ca;
    }

    @Nullable
    public final Long getMaxIntermediateCas() {
        return this.maxIntermediateCas;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.ca;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i2 = r0 * 31;
        Long l2 = this.maxIntermediateCas;
        return i2 + (l2 == null ? 0 : l2.hashCode());
    }

    @NotNull
    public String toString() {
        return "BasicConstraints(ca=" + this.ca + ", maxIntermediateCas=" + this.maxIntermediateCas + ')';
    }
}
