package com.appmattus.certificaterevocation;

import java.io.IOException;
import java.security.cert.X509Certificate;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public abstract class RevocationResult {

    /* loaded from: classes.dex */
    public static abstract class Failure extends RevocationResult {

        /* loaded from: classes.dex */
        public static final class CertificateRevoked extends Failure {
            @NotNull
            private final X509Certificate certificate;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public CertificateRevoked(@NotNull X509Certificate certificate) {
                super(null);
                Intrinsics.checkNotNullParameter(certificate, "certificate");
                this.certificate = certificate;
            }

            public static /* synthetic */ CertificateRevoked copy$default(CertificateRevoked certificateRevoked, X509Certificate x509Certificate, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    x509Certificate = certificateRevoked.certificate;
                }
                return certificateRevoked.copy(x509Certificate);
            }

            @NotNull
            public final X509Certificate component1() {
                return this.certificate;
            }

            @NotNull
            public final CertificateRevoked copy(@NotNull X509Certificate certificate) {
                Intrinsics.checkNotNullParameter(certificate, "certificate");
                return new CertificateRevoked(certificate);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof CertificateRevoked) && Intrinsics.areEqual(this.certificate, ((CertificateRevoked) obj).certificate);
            }

            @NotNull
            public final X509Certificate getCertificate() {
                return this.certificate;
            }

            public int hashCode() {
                return this.certificate.hashCode();
            }

            @NotNull
            public String toString() {
                return "Failure: Certificate is revoked";
            }
        }

        /* loaded from: classes.dex */
        public static final class NoCertificates extends Failure {
            @NotNull
            public static final NoCertificates INSTANCE = new NoCertificates();

            private NoCertificates() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "Failure: No certificates";
            }
        }

        /* loaded from: classes.dex */
        public static final class UnknownIoException extends Failure {
            @NotNull
            private final IOException ioException;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public UnknownIoException(@NotNull IOException ioException) {
                super(null);
                Intrinsics.checkNotNullParameter(ioException, "ioException");
                this.ioException = ioException;
            }

            public static /* synthetic */ UnknownIoException copy$default(UnknownIoException unknownIoException, IOException iOException, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    iOException = unknownIoException.ioException;
                }
                return unknownIoException.copy(iOException);
            }

            @NotNull
            public final IOException component1() {
                return this.ioException;
            }

            @NotNull
            public final UnknownIoException copy(@NotNull IOException ioException) {
                Intrinsics.checkNotNullParameter(ioException, "ioException");
                return new UnknownIoException(ioException);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UnknownIoException) && Intrinsics.areEqual(this.ioException, ((UnknownIoException) obj).ioException);
            }

            @NotNull
            public final IOException getIoException() {
                return this.ioException;
            }

            public int hashCode() {
                return this.ioException.hashCode();
            }

            @NotNull
            public String toString() {
                return "Failure: IOException " + this.ioException;
            }
        }

        private Failure() {
            super(null);
        }

        public /* synthetic */ Failure(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Success extends RevocationResult {

        /* loaded from: classes.dex */
        public static final class InsecureConnection extends Success {
            @NotNull
            public static final InsecureConnection INSTANCE = new InsecureConnection();

            private InsecureConnection() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "Success: Revocation not enabled for insecure connection";
            }
        }

        /* loaded from: classes.dex */
        public static final class Trusted extends Success {
            @NotNull
            public static final Trusted INSTANCE = new Trusted();

            private Trusted() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "Success: Certificates not in revocation list";
            }
        }

        private Success() {
            super(null);
        }

        public /* synthetic */ Success(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private RevocationResult() {
    }

    public /* synthetic */ RevocationResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
