package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
import org.bouncycastle.tls.crypto.TlsHash;
import org.bouncycastle.tls.crypto.TlsHashOutputStream;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class OfferedPsks {

    /* renamed from: a  reason: collision with root package name */
    protected final Vector f14770a;

    /* renamed from: b  reason: collision with root package name */
    protected final Vector f14771b;

    /* renamed from: c  reason: collision with root package name */
    protected final int f14772c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class BindersConfig {

        /* renamed from: a  reason: collision with root package name */
        final TlsPSK[] f14773a;

        /* renamed from: b  reason: collision with root package name */
        final short[] f14774b;

        /* renamed from: c  reason: collision with root package name */
        final TlsSecret[] f14775c;

        /* renamed from: d  reason: collision with root package name */
        final int f14776d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public BindersConfig(TlsPSK[] tlsPSKArr, short[] sArr, TlsSecret[] tlsSecretArr, int i2) {
            this.f14773a = tlsPSKArr;
            this.f14774b = sArr;
            this.f14775c = tlsSecretArr;
            this.f14776d = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class SelectedConfig {

        /* renamed from: a  reason: collision with root package name */
        final int f14777a;

        /* renamed from: b  reason: collision with root package name */
        final TlsPSK f14778b;

        /* renamed from: c  reason: collision with root package name */
        final TlsSecret f14779c;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SelectedConfig(int i2, TlsPSK tlsPSK, short[] sArr, TlsSecret tlsSecret) {
            this.f14777a = i2;
            this.f14778b = tlsPSK;
            this.f14779c = tlsSecret;
        }
    }

    public OfferedPsks(Vector vector) {
        this(vector, null, -1);
    }

    private OfferedPsks(Vector vector, Vector vector2, int i2) {
        if (vector == null || vector.isEmpty()) {
            throw new IllegalArgumentException("'identities' cannot be null or empty");
        }
        if (vector2 != null && vector.size() != vector2.size()) {
            throw new IllegalArgumentException("'binders' must be the same length as 'identities' (or null)");
        }
        if ((vector2 != null) != (i2 >= 0)) {
            throw new IllegalArgumentException("'bindersSize' must be >= 0 iff 'binders' are present");
        }
        this.f14770a = vector;
        this.f14771b = vector2;
        this.f14772c = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(OutputStream outputStream, TlsCrypto tlsCrypto, TlsHandshakeHash tlsHandshakeHash, BindersConfig bindersConfig) {
        TlsPSK[] tlsPSKArr = bindersConfig.f14773a;
        TlsSecret[] tlsSecretArr = bindersConfig.f14775c;
        int i2 = bindersConfig.f14776d - 2;
        TlsUtils.checkUint16(i2);
        TlsUtils.writeUint16(i2, outputStream);
        int i3 = 0;
        for (int i4 = 0; i4 < tlsPSKArr.length; i4++) {
            TlsPSK tlsPSK = tlsPSKArr[i4];
            TlsSecret tlsSecret = tlsSecretArr[i4];
            int hashForPRF = TlsCryptoUtils.getHashForPRF(tlsPSK.getPRFAlgorithm());
            TlsHash createHash = tlsCrypto.createHash(hashForPRF);
            tlsHandshakeHash.copyBufferTo(new TlsHashOutputStream(createHash));
            byte[] j2 = TlsUtils.j(tlsCrypto, true, hashForPRF, tlsSecret, createHash.calculateHash());
            i3 += j2.length + 1;
            TlsUtils.writeOpaque8(j2, outputStream);
        }
        if (i2 != i3) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(TlsPSK[] tlsPSKArr) {
        int i2 = 0;
        for (TlsPSK tlsPSK : tlsPSKArr) {
            i2 += TlsCryptoUtils.getHashOutputSize(TlsCryptoUtils.getHashForPRF(tlsPSK.getPRFAlgorithm())) + 1;
        }
        TlsUtils.checkUint16(i2);
        return i2 + 2;
    }

    public static OfferedPsks parse(InputStream inputStream) {
        Vector vector = new Vector();
        int readUint16 = TlsUtils.readUint16(inputStream);
        if (readUint16 >= 7) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(readUint16, inputStream));
            do {
                vector.add(PskIdentity.parse(byteArrayInputStream));
            } while (byteArrayInputStream.available() > 0);
            Vector vector2 = new Vector();
            int readUint162 = TlsUtils.readUint16(inputStream);
            if (readUint162 >= 33) {
                ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(TlsUtils.readFully(readUint162, inputStream));
                do {
                    vector2.add(TlsUtils.readOpaque8(byteArrayInputStream2, 32));
                } while (byteArrayInputStream2.available() > 0);
                return new OfferedPsks(vector, vector2, readUint162 + 2);
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public void encode(OutputStream outputStream) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.f14770a.size(); i3++) {
            i2 += ((PskIdentity) this.f14770a.elementAt(i3)).getEncodedLength();
        }
        TlsUtils.checkUint16(i2);
        TlsUtils.writeUint16(i2, outputStream);
        for (int i4 = 0; i4 < this.f14770a.size(); i4++) {
            ((PskIdentity) this.f14770a.elementAt(i4)).encode(outputStream);
        }
        if (this.f14771b != null) {
            int i5 = 0;
            for (int i6 = 0; i6 < this.f14771b.size(); i6++) {
                i5 += ((byte[]) this.f14771b.elementAt(i6)).length + 1;
            }
            TlsUtils.checkUint16(i5);
            TlsUtils.writeUint16(i5, outputStream);
            for (int i7 = 0; i7 < this.f14771b.size(); i7++) {
                TlsUtils.writeOpaque8((byte[]) this.f14771b.elementAt(i7), outputStream);
            }
        }
    }

    public Vector getBinders() {
        return this.f14771b;
    }

    public int getBindersSize() {
        return this.f14772c;
    }

    public Vector getIdentities() {
        return this.f14770a;
    }

    public int getIndexOfIdentity(PskIdentity pskIdentity) {
        int size = this.f14770a.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (pskIdentity.equals(this.f14770a.elementAt(i2))) {
                return i2;
            }
        }
        return -1;
    }
}
