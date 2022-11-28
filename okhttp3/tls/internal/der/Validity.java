package okhttp3.tls.internal.der;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Validity {
    private final long notAfter;
    private final long notBefore;

    public Validity(long j2, long j3) {
        this.notBefore = j2;
        this.notAfter = j3;
    }

    public static /* synthetic */ Validity copy$default(Validity validity, long j2, long j3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = validity.notBefore;
        }
        if ((i2 & 2) != 0) {
            j3 = validity.notAfter;
        }
        return validity.copy(j2, j3);
    }

    public final long component1() {
        return this.notBefore;
    }

    public final long component2() {
        return this.notAfter;
    }

    @NotNull
    public final Validity copy(long j2, long j3) {
        return new Validity(j2, j3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Validity) {
            Validity validity = (Validity) obj;
            return this.notBefore == validity.notBefore && this.notAfter == validity.notAfter;
        }
        return false;
    }

    public final long getNotAfter() {
        return this.notAfter;
    }

    public final long getNotBefore() {
        return this.notBefore;
    }

    public int hashCode() {
        return ((0 + ((int) this.notBefore)) * 31) + ((int) this.notAfter);
    }

    @NotNull
    public String toString() {
        return "Validity(notBefore=" + this.notBefore + ", notAfter=" + this.notAfter + ')';
    }
}
