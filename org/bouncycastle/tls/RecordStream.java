package org.bouncycastle.tls;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsDecodeResult;
import org.bouncycastle.tls.crypto.TlsEncodeResult;
import org.bouncycastle.tls.crypto.TlsNullNullCipher;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class RecordStream {
    private static int DEFAULT_PLAINTEXT_LIMIT = 16384;
    private int ciphertextLimit;
    private TlsProtocol handler;
    private boolean ignoreChangeCipherSpec;
    private InputStream input;
    private OutputStream output;
    private int plaintextLimit;
    private TlsCipher readCipher;
    private TlsCipher readCipherDeferred;
    private TlsCipher writeCipher;
    private ProtocolVersion writeVersion;
    private final Record inputRecord = new Record();
    private final SequenceNumber readSeqNo = new SequenceNumber();
    private final SequenceNumber writeSeqNo = new SequenceNumber();
    private TlsCipher pendingCipher = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class Record {

        /* renamed from: a  reason: collision with root package name */
        volatile byte[] f14792a;

        /* renamed from: b  reason: collision with root package name */
        volatile int f14793b;
        private final byte[] header;

        private Record() {
            byte[] bArr = new byte[5];
            this.header = bArr;
            this.f14792a = bArr;
            this.f14793b = 0;
        }

        private void resize(int i2) {
            if (this.f14792a.length < i2) {
                byte[] bArr = new byte[i2];
                System.arraycopy(this.f14792a, 0, bArr, 0, this.f14793b);
                this.f14792a = bArr;
            }
        }

        void a(InputStream inputStream, int i2) {
            while (this.f14793b < i2) {
                try {
                    int read = inputStream.read(this.f14792a, this.f14793b, i2 - this.f14793b);
                    if (read < 0) {
                        return;
                    }
                    this.f14793b += read;
                } catch (InterruptedIOException e2) {
                    this.f14793b += e2.bytesTransferred;
                    e2.bytesTransferred = 0;
                    throw e2;
                }
            }
        }

        void b(InputStream inputStream, int i2) {
            int i3 = i2 + 5;
            resize(i3);
            a(inputStream, i3);
            if (this.f14793b < i3) {
                throw new EOFException();
            }
        }

        boolean c(InputStream inputStream) {
            a(inputStream, 5);
            if (this.f14793b == 0) {
                return false;
            }
            if (this.f14793b >= 5) {
                return true;
            }
            throw new EOFException();
        }

        void d() {
            this.f14792a = this.header;
            this.f14793b = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class SequenceNumber {
        private boolean exhausted;
        private long value;

        private SequenceNumber() {
            this.value = 0L;
            this.exhausted = false;
        }

        synchronized long a() {
            return this.value;
        }

        synchronized long b(short s2) {
            long j2;
            if (this.exhausted) {
                throw new TlsFatalAlert(s2, "Sequence numbers exhausted");
            }
            j2 = this.value;
            long j3 = 1 + j2;
            this.value = j3;
            if (j3 == 0) {
                this.exhausted = true;
            }
            return j2;
        }

        synchronized void c() {
            this.value = 0L;
            this.exhausted = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecordStream(TlsProtocol tlsProtocol, InputStream inputStream, OutputStream outputStream) {
        TlsNullNullCipher tlsNullNullCipher = TlsNullNullCipher.INSTANCE;
        this.readCipher = tlsNullNullCipher;
        this.readCipherDeferred = null;
        this.writeCipher = tlsNullNullCipher;
        this.writeVersion = null;
        int i2 = DEFAULT_PLAINTEXT_LIMIT;
        this.plaintextLimit = i2;
        this.ciphertextLimit = i2;
        this.ignoreChangeCipherSpec = false;
        this.handler = tlsProtocol;
        this.input = inputStream;
        this.output = outputStream;
    }

    private void checkChangeCipherSpec(byte[] bArr, int i2, int i3) {
        if (1 == i3 && 1 == bArr[i2]) {
            return;
        }
        throw new TlsFatalAlert((short) 10, "Malformed " + ContentType.getText((short) 20));
    }

    private static void checkLength(int i2, int i3, short s2) {
        if (i2 > i3) {
            throw new TlsFatalAlert(s2);
        }
    }

    private short checkRecordType(byte[] bArr, int i2) {
        short readUint8 = TlsUtils.readUint8(bArr, i2);
        TlsCipher tlsCipher = this.readCipherDeferred;
        if (tlsCipher != null && readUint8 == 23) {
            this.readCipher = tlsCipher;
            this.readCipherDeferred = null;
            this.ciphertextLimit = tlsCipher.getCiphertextDecodeLimit(this.plaintextLimit);
            this.readSeqNo.c();
        } else if (!this.readCipher.usesOpaqueRecordType()) {
            switch (readUint8) {
                case 20:
                case 21:
                case 22:
                    break;
                default:
                    throw new TlsFatalAlert((short) 10, "Unsupported " + ContentType.getText(readUint8));
                case 23:
                    if (!this.handler.z()) {
                        throw new TlsFatalAlert((short) 10, "Not ready for " + ContentType.getText((short) 23));
                    }
                    break;
            }
        } else if (23 != readUint8 && (!this.ignoreChangeCipherSpec || 20 != readUint8)) {
            throw new TlsFatalAlert((short) 10, "Opaque " + ContentType.getText(readUint8));
        }
        return readUint8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.inputRecord.d();
        try {
            this.input.close();
            e = null;
        } catch (IOException e2) {
            e = e2;
        }
        try {
            this.output.close();
        } catch (IOException e3) {
            if (e == null) {
                e = e3;
            }
        }
        if (e != null) {
            throw e;
        }
    }

    TlsDecodeResult b(short s2, ProtocolVersion protocolVersion, byte[] bArr, int i2, int i3) {
        TlsDecodeResult decodeCiphertext = this.readCipher.decodeCiphertext(this.readSeqNo.b((short) 10), s2, protocolVersion, bArr, i2, i3);
        checkLength(decodeCiphertext.len, this.plaintextLimit, (short) 22);
        if (decodeCiphertext.len >= 1 || decodeCiphertext.contentType == 23) {
            return decodeCiphertext;
        }
        throw new TlsFatalAlert((short) 47);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(boolean z) {
        TlsCipher tlsCipher = this.pendingCipher;
        if (tlsCipher == null) {
            throw new TlsFatalAlert((short) 80);
        }
        if (this.readCipherDeferred != null) {
            throw new TlsFatalAlert((short) 80);
        }
        if (z) {
            this.readCipherDeferred = tlsCipher;
            return;
        }
        this.readCipher = tlsCipher;
        this.ciphertextLimit = tlsCipher.getCiphertextDecodeLimit(this.plaintextLimit);
        this.readSeqNo.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        TlsCipher tlsCipher = this.pendingCipher;
        if (tlsCipher == null) {
            throw new TlsFatalAlert((short) 80);
        }
        this.writeCipher = tlsCipher;
        this.writeSeqNo.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        TlsCipher tlsCipher = this.readCipher;
        TlsCipher tlsCipher2 = this.pendingCipher;
        if (tlsCipher != tlsCipher2 || this.writeCipher != tlsCipher2) {
            throw new TlsFatalAlert((short) 40);
        }
        this.pendingCipher = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.plaintextLimit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g() {
        return this.writeSeqNo.a() >= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        if (this.pendingCipher == null) {
            throw new TlsFatalAlert((short) 10, "No pending cipher");
        }
        c(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i() {
        this.readCipher.rekeyDecoder();
        this.readSeqNo.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        this.writeCipher.rekeyEncoder();
        this.writeSeqNo.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecordPreview k(int i2) {
        int max = Math.max(0, Math.min(this.plaintextLimit, i2));
        return new RecordPreview(l(max), max);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int l(int i2) {
        return this.writeCipher.getCiphertextEncodeLimit(i2, this.plaintextLimit) + 5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecordPreview m(byte[] bArr) {
        int i2 = 0;
        short checkRecordType = checkRecordType(bArr, 0);
        int readUint16 = TlsUtils.readUint16(bArr, 3);
        checkLength(readUint16, this.ciphertextLimit, (short) 22);
        int i3 = readUint16 + 5;
        if (23 == checkRecordType && this.handler.z()) {
            i2 = Math.max(0, Math.min(this.plaintextLimit, this.readCipher.getPlaintextLimit(readUint16)));
        }
        return new RecordPreview(i3, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean n(byte[] bArr, int i2, int i3) {
        if (i3 < 5) {
            return false;
        }
        int readUint16 = TlsUtils.readUint16(bArr, i2 + 3);
        if (i3 != readUint16 + 5) {
            return false;
        }
        short checkRecordType = checkRecordType(bArr, i2 + 0);
        ProtocolVersion readVersion = TlsUtils.readVersion(bArr, i2 + 1);
        checkLength(readUint16, this.ciphertextLimit, (short) 22);
        if (this.ignoreChangeCipherSpec && 20 == checkRecordType) {
            checkChangeCipherSpec(bArr, i2 + 5, readUint16);
            return true;
        }
        TlsDecodeResult b2 = b(checkRecordType, readVersion, bArr, i2 + 5, readUint16);
        this.handler.F(b2.contentType, b2.buf, b2.off, b2.len);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean o() {
        if (this.inputRecord.c(this.input)) {
            short checkRecordType = checkRecordType(this.inputRecord.f14792a, 0);
            ProtocolVersion readVersion = TlsUtils.readVersion(this.inputRecord.f14792a, 1);
            int readUint16 = TlsUtils.readUint16(this.inputRecord.f14792a, 3);
            checkLength(readUint16, this.ciphertextLimit, (short) 22);
            this.inputRecord.b(this.input, readUint16);
            try {
                if (this.ignoreChangeCipherSpec && 20 == checkRecordType) {
                    checkChangeCipherSpec(this.inputRecord.f14792a, 5, readUint16);
                    return true;
                }
                TlsDecodeResult b2 = b(checkRecordType, readVersion, this.inputRecord.f14792a, 5, readUint16);
                this.inputRecord.d();
                this.handler.F(b2.contentType, b2.buf, b2.off, b2.len);
                return true;
            } finally {
                this.inputRecord.d();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(boolean z) {
        this.ignoreChangeCipherSpec = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(TlsCipher tlsCipher) {
        this.pendingCipher = tlsCipher;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(int i2) {
        this.plaintextLimit = i2;
        this.ciphertextLimit = this.readCipher.getCiphertextDecodeLimit(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(ProtocolVersion protocolVersion) {
        this.writeVersion = protocolVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(short s2, byte[] bArr, int i2, int i3) {
        if (this.writeVersion == null) {
            return;
        }
        checkLength(i3, this.plaintextLimit, (short) 80);
        if (i3 < 1 && s2 != 23) {
            throw new TlsFatalAlert((short) 80);
        }
        long b2 = this.writeSeqNo.b((short) 80);
        ProtocolVersion protocolVersion = this.writeVersion;
        TlsEncodeResult encodePlaintext = this.writeCipher.encodePlaintext(b2, s2, protocolVersion, 5, bArr, i2, i3);
        int i4 = encodePlaintext.len - 5;
        TlsUtils.checkUint16(i4);
        TlsUtils.writeUint8(encodePlaintext.recordType, encodePlaintext.buf, encodePlaintext.off + 0);
        TlsUtils.writeVersion(protocolVersion, encodePlaintext.buf, encodePlaintext.off + 1);
        TlsUtils.writeUint16(i4, encodePlaintext.buf, encodePlaintext.off + 3);
        try {
            this.output.write(encodePlaintext.buf, encodePlaintext.off, encodePlaintext.len);
            this.output.flush();
        } catch (InterruptedIOException e2) {
            throw new TlsFatalAlert((short) 80, (Throwable) e2);
        }
    }
}
