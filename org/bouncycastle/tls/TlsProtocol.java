package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.SessionParameters;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* loaded from: classes4.dex */
public abstract class TlsProtocol implements TlsCloseable {

    /* renamed from: s  reason: collision with root package name */
    protected static final Integer f14862s = Integers.valueOf(65281);

    /* renamed from: t  reason: collision with root package name */
    protected static final Integer f14863t = Integers.valueOf(35);

    /* renamed from: a  reason: collision with root package name */
    final RecordStream f14864a;
    private ByteQueue alertQueue;
    private volatile boolean appDataReady;
    private volatile boolean appDataSplitEnabled;
    private volatile int appDataSplitMode;
    private ByteQueue applicationDataQueue;

    /* renamed from: b  reason: collision with root package name */
    final Object f14865b;

    /* renamed from: c  reason: collision with root package name */
    TlsHandshakeHash f14866c;
    private volatile boolean closed;

    /* renamed from: d  reason: collision with root package name */
    protected TlsSession f14867d;

    /* renamed from: e  reason: collision with root package name */
    protected SessionParameters f14868e;

    /* renamed from: f  reason: collision with root package name */
    protected TlsSecret f14869f;
    private volatile boolean failedWithError;

    /* renamed from: g  reason: collision with root package name */
    protected byte[] f14870g;

    /* renamed from: h  reason: collision with root package name */
    protected int f14871h;
    private ByteQueue handshakeQueue;

    /* renamed from: i  reason: collision with root package name */
    protected Hashtable f14872i;

    /* renamed from: j  reason: collision with root package name */
    protected Hashtable f14873j;

    /* renamed from: k  reason: collision with root package name */
    protected short f14874k;
    private volatile boolean keyUpdateEnabled;
    private volatile boolean keyUpdatePendingSend;

    /* renamed from: l  reason: collision with root package name */
    protected boolean f14875l;

    /* renamed from: m  reason: collision with root package name */
    protected boolean f14876m;
    private int maxHandshakeMessageSize;

    /* renamed from: n  reason: collision with root package name */
    protected boolean f14877n;

    /* renamed from: o  reason: collision with root package name */
    protected boolean f14878o;

    /* renamed from: p  reason: collision with root package name */
    protected boolean f14879p;

    /* renamed from: q  reason: collision with root package name */
    protected ByteQueueInputStream f14880q;

    /* renamed from: r  reason: collision with root package name */
    protected ByteQueueOutputStream f14881r;
    private volatile boolean resumableHandshake;
    private TlsInputStream tlsInputStream;
    private TlsOutputStream tlsOutputStream;

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsProtocol() {
        this.applicationDataQueue = new ByteQueue(0);
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue(0);
        this.f14865b = new Object();
        this.maxHandshakeMessageSize = -1;
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.keyUpdateEnabled = false;
        this.keyUpdatePendingSend = false;
        this.resumableHandshake = false;
        this.appDataSplitMode = 0;
        this.f14867d = null;
        this.f14868e = null;
        this.f14869f = null;
        this.f14870g = null;
        this.f14871h = -1;
        this.f14872i = null;
        this.f14873j = null;
        this.f14874k = (short) 0;
        this.f14875l = false;
        this.f14876m = false;
        this.f14877n = false;
        this.f14878o = false;
        this.f14879p = false;
        this.f14880q = new ByteQueueInputStream();
        this.f14881r = new ByteQueueOutputStream();
        this.f14864a = new RecordStream(this, this.f14880q, this.f14881r);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsProtocol(InputStream inputStream, OutputStream outputStream) {
        this.applicationDataQueue = new ByteQueue(0);
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue(0);
        this.f14865b = new Object();
        this.maxHandshakeMessageSize = -1;
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.appDataSplitEnabled = true;
        this.keyUpdateEnabled = false;
        this.keyUpdatePendingSend = false;
        this.resumableHandshake = false;
        this.appDataSplitMode = 0;
        this.f14867d = null;
        this.f14868e = null;
        this.f14869f = null;
        this.f14870g = null;
        this.f14871h = -1;
        this.f14872i = null;
        this.f14873j = null;
        this.f14874k = (short) 0;
        this.f14875l = false;
        this.f14876m = false;
        this.f14877n = false;
        this.f14878o = false;
        this.f14879p = true;
        this.f14864a = new RecordStream(this, inputStream, outputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Hashtable I(ByteArrayInputStream byteArrayInputStream) {
        if (byteArrayInputStream.available() < 1) {
            return null;
        }
        byte[] readOpaque16 = TlsUtils.readOpaque16(byteArrayInputStream);
        b(byteArrayInputStream);
        return J(readOpaque16);
    }

    protected static Hashtable J(byte[] bArr) {
        Hashtable hashtable = new Hashtable();
        if (bArr.length > 0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            do {
                int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
                if (hashtable.put(Integers.valueOf(readUint16), TlsUtils.readOpaque16(byteArrayInputStream)) != null) {
                    throw new TlsFatalAlert((short) 47, "Repeated extension: " + ExtensionType.getText(readUint16));
                }
            } while (byteArrayInputStream.available() > 0);
            return hashtable;
        }
        return hashtable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Hashtable K(int i2, byte[] bArr) {
        Hashtable hashtable = new Hashtable();
        if (bArr.length > 0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            do {
                int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
                if (!TlsUtils.Y(i2, readUint16)) {
                    throw new TlsFatalAlert((short) 47, "Invalid extension: " + ExtensionType.getText(readUint16));
                }
                if (hashtable.put(Integers.valueOf(readUint16), TlsUtils.readOpaque16(byteArrayInputStream)) != null) {
                    throw new TlsFatalAlert((short) 47, "Repeated extension: " + ExtensionType.getText(readUint16));
                }
            } while (byteArrayInputStream.available() > 0);
            return hashtable;
        }
        return hashtable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Hashtable L(byte[] bArr) {
        int readUint16;
        Hashtable hashtable = new Hashtable();
        if (bArr.length > 0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            boolean z = false;
            do {
                readUint16 = TlsUtils.readUint16(byteArrayInputStream);
                if (hashtable.put(Integers.valueOf(readUint16), TlsUtils.readOpaque16(byteArrayInputStream)) != null) {
                    throw new TlsFatalAlert((short) 47, "Repeated extension: " + ExtensionType.getText(readUint16));
                }
                z |= 41 == readUint16;
            } while (byteArrayInputStream.available() > 0);
            if (z && 41 != readUint16) {
                throw new TlsFatalAlert((short) 47, "'pre_shared_key' MUST be last in ClientHello");
            }
        }
        return hashtable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Vector M(ByteArrayInputStream byteArrayInputStream) {
        byte[] readOpaque24 = TlsUtils.readOpaque24(byteArrayInputStream, 1);
        b(byteArrayInputStream);
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readOpaque24);
        Vector vector = new Vector();
        while (byteArrayInputStream2.available() > 0) {
            vector.addElement(new SupplementalDataEntry(TlsUtils.readUint16(byteArrayInputStream2), TlsUtils.readOpaque16(byteArrayInputStream2)));
        }
        return vector;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void b(ByteArrayInputStream byteArrayInputStream) {
        if (byteArrayInputStream.available() > 0) {
            throw new TlsFatalAlert((short) 50);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void c0(OutputStream outputStream, Hashtable hashtable) {
        d0(outputStream, hashtable, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void d0(OutputStream outputStream, Hashtable hashtable, int i2) {
        if (hashtable == null || hashtable.isEmpty()) {
            return;
        }
        byte[] g0 = g0(hashtable, i2);
        int length = g0.length + i2;
        TlsUtils.checkUint16(length);
        TlsUtils.writeUint16(length, outputStream);
        outputStream.write(g0);
    }

    protected static void e0(Hashtable hashtable, int i2, ByteArrayOutputStream byteArrayOutputStream) {
        j0(byteArrayOutputStream, hashtable, true);
        j0(byteArrayOutputStream, hashtable, false);
        i0(byteArrayOutputStream, hashtable, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] f0(Hashtable hashtable) {
        return g0(hashtable, 0);
    }

    protected static byte[] g0(Hashtable hashtable, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        e0(hashtable, i2, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] i(boolean z, TlsContext tlsContext) {
        byte[] generateNonce = tlsContext.getNonceGenerator().generateNonce(32);
        if (z) {
            TlsUtils.writeGMTUnixTime(generateNonce, 0);
        }
        return generateNonce;
    }

    protected static void i0(OutputStream outputStream, Hashtable hashtable, int i2) {
        byte[] bArr = (byte[]) hashtable.get(TlsExtensionsUtils.EXT_pre_shared_key);
        if (bArr != null) {
            TlsUtils.checkUint16(41);
            TlsUtils.writeUint16(41, outputStream);
            int length = bArr.length + i2;
            TlsUtils.checkUint16(length);
            TlsUtils.writeUint16(length, outputStream);
            outputStream.write(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] j(byte[] bArr) {
        return TlsUtils.encodeOpaque8(bArr);
    }

    protected static void j0(OutputStream outputStream, Hashtable hashtable, boolean z) {
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            int intValue = num.intValue();
            if (41 != intValue) {
                byte[] bArr = (byte[]) hashtable.get(num);
                if (z == (bArr.length == 0)) {
                    TlsUtils.checkUint16(intValue);
                    TlsUtils.writeUint16(intValue, outputStream);
                    TlsUtils.writeOpaque16(bArr, outputStream);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void k(TlsContext tlsContext, TlsKeyExchange tlsKeyExchange) {
        TlsSecret generatePreMasterSecret = tlsKeyExchange.generatePreMasterSecret();
        if (generatePreMasterSecret == null) {
            throw new TlsFatalAlert((short) 80);
        }
        try {
            tlsContext.getSecurityParametersHandshake().f14814q = TlsUtils.i(tlsContext, generatePreMasterSecret);
        } finally {
            generatePreMasterSecret.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void k0(OutputStream outputStream, Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 0; i2 < vector.size(); i2++) {
            SupplementalDataEntry supplementalDataEntry = (SupplementalDataEntry) vector.elementAt(i2);
            int dataType = supplementalDataEntry.getDataType();
            TlsUtils.checkUint16(dataType);
            TlsUtils.writeUint16(dataType, byteArrayOutputStream);
            TlsUtils.writeOpaque16(supplementalDataEntry.getData(), byteArrayOutputStream);
        }
        TlsUtils.writeOpaque24(byteArrayOutputStream.toByteArray(), outputStream);
    }

    private void processAlertQueue() {
        while (this.alertQueue.available() >= 2) {
            byte[] removeData = this.alertQueue.removeData(2, 0);
            q(removeData[0], removeData[1]);
        }
    }

    private void processApplicationDataQueue() {
    }

    private void processChangeCipherSpec(byte[] bArr, int i2, int i3) {
        ProtocolVersion serverVersion = m().getServerVersion();
        if (serverVersion == null || TlsUtils.isTLSv13(serverVersion)) {
            throw new TlsFatalAlert((short) 10);
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (TlsUtils.readUint8(bArr, i2 + i4) != 1) {
                throw new TlsFatalAlert((short) 50);
            }
            if (this.f14877n || this.alertQueue.available() > 0 || this.handshakeQueue.available() > 0) {
                throw new TlsFatalAlert((short) 10);
            }
            this.f14864a.h();
            this.f14877n = true;
            s();
        }
    }

    private void processHandshakeQueue(ByteQueue byteQueue) {
        ProtocolVersion serverVersion;
        ProtocolVersion serverVersion2;
        while (byteQueue.available() >= 4) {
            int readInt32 = byteQueue.readInt32();
            short s2 = (short) (readInt32 >>> 24);
            if (!HandshakeType.isRecognized(s2)) {
                throw new TlsFatalAlert((short) 10, "Handshake message of unrecognized type: " + ((int) s2));
            }
            int i2 = readInt32 & 16777215;
            if (i2 > this.maxHandshakeMessageSize) {
                throw new TlsFatalAlert((short) 80, "Handshake message length exceeds the maximum: " + HandshakeType.getText(s2) + ", " + i2 + " > " + this.maxHandshakeMessageSize);
            }
            int i3 = i2 + 4;
            if (byteQueue.available() < i3) {
                return;
            }
            if (s2 != 0 && ((serverVersion2 = m().getServerVersion()) == null || !TlsUtils.isTLSv13(serverVersion2))) {
                e(20 == s2);
            }
            HandshakeMessageInput a2 = byteQueue.a(i3);
            if (s2 != 0 && s2 != 1 && s2 != 2 && (s2 == 4 ? !((serverVersion = m().getServerVersion()) == null || TlsUtils.isTLSv13(serverVersion)) : !(s2 == 15 || s2 == 20 || s2 == 24))) {
                a2.updateHash(this.f14866c);
            }
            a2.skip(4L);
            w(s2, a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean A() {
        switch (this.f14874k) {
            case 0:
            case 1:
            case 4:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return true;
            case 2:
            case 3:
            case 5:
            case 9:
            case 13:
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean B() {
        switch (this.f14874k) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 20:
            case 21:
                return true;
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
            case 16:
            case 19:
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void C(ByteArrayInputStream byteArrayInputStream) {
        TlsContext m2 = m();
        SecurityParameters securityParametersHandshake = m2.getSecurityParametersHandshake();
        boolean isServer = m2.isServer();
        byte[] readFully = TlsUtils.readFully(securityParametersHandshake.getVerifyDataLength(), byteArrayInputStream);
        b(byteArrayInputStream);
        byte[] l2 = TlsUtils.l(m2, this.f14866c, !isServer);
        if (!Arrays.constantTimeAreEqual(l2, readFully)) {
            throw new TlsFatalAlert((short) 51);
        }
        securityParametersHandshake.V = l2;
        securityParametersHandshake.A = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void D(ByteArrayInputStream byteArrayInputStream) {
        TlsContext m2 = m();
        SecurityParameters securityParametersHandshake = m2.getSecurityParametersHandshake();
        boolean isServer = m2.isServer();
        byte[] readFully = TlsUtils.readFully(securityParametersHandshake.getVerifyDataLength(), byteArrayInputStream);
        b(byteArrayInputStream);
        byte[] l2 = TlsUtils.l(m2, this.f14866c, !isServer);
        if (!Arrays.constantTimeAreEqual(l2, readFully)) {
            throw new TlsFatalAlert((short) 51);
        }
        securityParametersHandshake.V = l2;
        if ((!this.f14875l || securityParametersHandshake.isExtendedMasterSecret()) && securityParametersHandshake.getLocalVerifyData() == null) {
            securityParametersHandshake.A = l2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public short E(Hashtable hashtable, Hashtable hashtable2, short s2) {
        short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable2);
        if (maxFragmentLengthExtension < 0 || (MaxFragmentLength.isValid(maxFragmentLengthExtension) && (this.f14875l || maxFragmentLengthExtension == TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable)))) {
            return maxFragmentLengthExtension;
        }
        throw new TlsFatalAlert(s2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void F(short s2, byte[] bArr, int i2, int i3) {
        switch (s2) {
            case 20:
                processChangeCipherSpec(bArr, i2, i3);
                return;
            case 21:
                this.alertQueue.addData(bArr, i2, i3);
                processAlertQueue();
                return;
            case 22:
                if (this.handshakeQueue.available() > 0) {
                    this.handshakeQueue.addData(bArr, i2, i3);
                    processHandshakeQueue(this.handshakeQueue);
                    return;
                }
                ByteQueue byteQueue = new ByteQueue(bArr, i2, i3);
                processHandshakeQueue(byteQueue);
                int available = byteQueue.available();
                if (available > 0) {
                    this.handshakeQueue.addData(bArr, (i2 + i3) - available, available);
                    return;
                }
                return;
            case 23:
                if (!this.appDataReady) {
                    throw new TlsFatalAlert((short) 10);
                }
                this.applicationDataQueue.addData(bArr, i2, i3);
                processApplicationDataQueue();
                return;
            default:
                throw new TlsFatalAlert((short) 10);
        }
    }

    protected void G(short s2, String str, Throwable th) {
        o().notifyAlertRaised((short) 2, s2, str, th);
        try {
            this.f14864a.t((short) 21, new byte[]{2, (byte) s2}, 0, 2);
        } catch (Exception unused) {
        }
    }

    protected void H(short s2, String str) {
        o().notifyAlertRaised((short) 1, s2, str, null);
        S((short) 21, new byte[]{1, (byte) s2}, 0, 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void N(ByteArrayInputStream byteArrayInputStream) {
        if (!this.appDataReady || !this.keyUpdateEnabled) {
            throw new TlsFatalAlert((short) 10);
        }
        short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
        b(byteArrayInputStream);
        if (!KeyUpdateRequest.isValid(readUint8)) {
            throw new TlsFatalAlert((short) 47);
        }
        boolean z = 1 == readUint8;
        TlsUtils.E0(m());
        this.f14864a.i();
        this.keyUpdatePendingSend = z | this.keyUpdatePendingSend;
    }

    protected void O() {
        if (TlsUtils.isSSL(m())) {
            throw new TlsFatalAlert((short) 40);
        }
        H((short) 100, "Renegotiation not supported");
    }

    protected RecordPreview P(byte[] bArr) {
        try {
            return this.f14864a.m(bArr);
        } catch (RuntimeException e2) {
            u((short) 80, "Failed to read record", e2);
            throw new TlsFatalAlert((short) 80, e2);
        } catch (TlsFatalAlert e3) {
            u(e3.getAlertDescription(), "Failed to read record", e3);
            throw e3;
        } catch (IOException e4) {
            u((short) 80, "Failed to read record", e4);
            throw e4;
        }
    }

    protected boolean Q(byte[] bArr, int i2, int i3) {
        try {
            return this.f14864a.n(bArr, i2, i3);
        } catch (RuntimeException e2) {
            u((short) 80, "Failed to process record", e2);
            throw new TlsFatalAlert((short) 80, e2);
        } catch (TlsFatalAlert e3) {
            u(e3.getAlertDescription(), "Failed to process record", e3);
            throw e3;
        } catch (IOException e4) {
            u((short) 80, "Failed to process record", e4);
            throw e4;
        }
    }

    protected void R() {
        try {
            if (this.f14864a.o()) {
                return;
            }
            if (!this.appDataReady) {
                throw new TlsFatalAlert((short) 40);
            }
            if (o().requiresCloseNotify()) {
                v();
                throw new TlsNoCloseNotifyException();
            } else {
                t(false);
            }
        } catch (RuntimeException e2) {
            u((short) 80, "Failed to read record", e2);
            throw new TlsFatalAlert((short) 80, e2);
        } catch (TlsFatalAlert e3) {
            u(e3.getAlertDescription(), "Failed to read record", e3);
            throw e3;
        } catch (TlsFatalAlertReceived e4) {
            throw e4;
        } catch (IOException e5) {
            u((short) 80, "Failed to read record", e5);
            throw e5;
        }
    }

    protected void S(short s2, byte[] bArr, int i2, int i3) {
        try {
            this.f14864a.t(s2, bArr, i2, i3);
        } catch (RuntimeException e2) {
            u((short) 80, "Failed to write record", e2);
            throw new TlsFatalAlert((short) 80, e2);
        } catch (TlsFatalAlert e3) {
            u(e3.getAlertDescription(), "Failed to write record", e3);
            throw e3;
        } catch (IOException e4) {
            u((short) 80, "Failed to write record", e4);
            throw e4;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void T(Certificate certificate) {
        if (certificate == null) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsContext m2 = m();
        SecurityParameters securityParametersHandshake = m2.getSecurityParametersHandshake();
        if (securityParametersHandshake.getLocalCertificate() != null) {
            throw new TlsFatalAlert((short) 80);
        }
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 11);
        certificate.encode(m2, handshakeMessageOutput, null);
        handshakeMessageOutput.c(this);
        securityParametersHandshake.Q = certificate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void U(DigitallySigned digitallySigned) {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 15);
        digitallySigned.encode(handshakeMessageOutput);
        handshakeMessageOutput.c(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void V() {
        TlsContext m2 = m();
        SecurityParameters securityParametersHandshake = m2.getSecurityParametersHandshake();
        byte[] l2 = TlsUtils.l(m2, this.f14866c, m2.isServer());
        securityParametersHandshake.U = l2;
        securityParametersHandshake.A = null;
        HandshakeMessageOutput.d(this, (short) 20, l2);
    }

    protected void W(boolean z) {
        if (!this.appDataReady || !this.keyUpdateEnabled) {
            throw new TlsFatalAlert((short) 80);
        }
        HandshakeMessageOutput.d(this, (short) 24, TlsUtils.encodeUint8(z ? (short) 1 : (short) 0));
        TlsUtils.D0(m());
        this.f14864a.j();
        this.keyUpdatePendingSend = (z ? 1 : 0) & (this.keyUpdatePendingSend ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void X(Certificate certificate, OutputStream outputStream) {
        TlsContext m2 = m();
        SecurityParameters securityParametersHandshake = m2.getSecurityParametersHandshake();
        if (securityParametersHandshake.getLocalCertificate() != null) {
            throw new TlsFatalAlert((short) 80);
        }
        if (certificate == null) {
            certificate = Certificate.EMPTY_CHAIN;
        }
        if (certificate.isEmpty() && !m2.isServer() && securityParametersHandshake.getNegotiatedVersion().isSSL()) {
            H((short) 41, "SSLv3 client didn't provide credentials");
        } else {
            HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 11);
            certificate.encode(m2, handshakeMessageOutput, outputStream);
            handshakeMessageOutput.c(this);
        }
        securityParametersHandshake.Q = certificate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void Y() {
        Z();
        this.f14864a.d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void Z() {
        S((short) 20, new byte[]{1}, 0, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(short s2) {
        if (s2 >= 0) {
            if (!MaxFragmentLength.isValid(s2)) {
                throw new TlsFatalAlert((short) 80);
            }
            this.f14864a.r(1 << (s2 + 8));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a0() {
        TlsContext m2 = m();
        SecurityParameters securityParametersHandshake = m2.getSecurityParametersHandshake();
        byte[] l2 = TlsUtils.l(m2, this.f14866c, m2.isServer());
        securityParametersHandshake.U = l2;
        if ((!this.f14875l || securityParametersHandshake.isExtendedMasterSecret()) && securityParametersHandshake.getPeerVerifyData() == null) {
            securityParametersHandshake.A = l2;
        }
        HandshakeMessageOutput.d(this, (short) 20, l2);
    }

    public int applicationDataAvailable() {
        return this.applicationDataQueue.available();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b0(Vector vector) {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 23);
        k0(handshakeMessageOutput, vector);
        handshakeMessageOutput.c(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(boolean z) {
        AbstractTlsContext n2 = n();
        TlsPeer o2 = o();
        this.maxHandshakeMessageSize = Math.max(1024, o2.getMaxHandshakeMessageSize());
        this.f14866c = new DeferredHash(n2);
        this.f14874k = (short) 0;
        this.f14875l = false;
        this.f14876m = false;
        n2.d(o2);
        SecurityParameters securityParametersHandshake = n2.getSecurityParametersHandshake();
        if (z != securityParametersHandshake.isRenegotiating()) {
            throw new TlsFatalAlert((short) 80);
        }
        securityParametersHandshake.D = o2.shouldUseExtendedPadding();
    }

    @Override // org.bouncycastle.tls.TlsCloseable
    public void close() {
        t(true);
    }

    public void closeInput() {
        if (this.f14879p) {
            throw new IllegalStateException("Cannot use closeInput() in blocking mode!");
        }
        if (this.closed) {
            return;
        }
        if (this.f14880q.available() > 0) {
            throw new EOFException();
        }
        if (!this.appDataReady) {
            throw new TlsFatalAlert((short) 40);
        }
        if (o().requiresCloseNotify()) {
            v();
            throw new TlsNoCloseNotifyException();
        } else {
            t(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        while (this.f14874k != 21) {
            if (isClosed()) {
                throw new TlsFatalAlert((short) 80);
            }
            R();
        }
    }

    protected void e(boolean z) {
        if (z != this.f14877n) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f() {
        SecurityParameters securityParameters;
        TlsContext m2 = m();
        if (m2 != null && (securityParameters = m2.getSecurityParameters()) != null) {
            securityParameters.a();
        }
        this.f14867d = null;
        this.f14868e = null;
        this.f14869f = null;
        this.f14870g = null;
        this.f14871h = -1;
        this.f14872i = null;
        this.f14873j = null;
        this.f14875l = false;
        this.f14876m = false;
        this.f14877n = false;
        this.f14878o = false;
    }

    public void flush() {
    }

    protected void g() {
        this.f14864a.a();
    }

    public int getAppDataSplitMode() {
        return this.appDataSplitMode;
    }

    public int getApplicationDataLimit() {
        return this.f14864a.f();
    }

    public int getAvailableInputBytes() {
        if (this.f14879p) {
            throw new IllegalStateException("Cannot use getAvailableInputBytes() in blocking mode! Use getInputStream().available() instead.");
        }
        return applicationDataAvailable();
    }

    public int getAvailableOutputBytes() {
        if (this.f14879p) {
            throw new IllegalStateException("Cannot use getAvailableOutputBytes() in blocking mode! Use getOutputStream() instead.");
        }
        return this.f14881r.getBuffer().available();
    }

    public InputStream getInputStream() {
        if (this.f14879p) {
            return this.tlsInputStream;
        }
        throw new IllegalStateException("Cannot use InputStream in non-blocking mode! Use offerInput() instead.");
    }

    public OutputStream getOutputStream() {
        if (this.f14879p) {
            return this.tlsOutputStream;
        }
        throw new IllegalStateException("Cannot use OutputStream in non-blocking mode! Use offerOutput() instead.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h() {
        try {
            AbstractTlsContext n2 = n();
            SecurityParameters securityParametersHandshake = n2.getSecurityParametersHandshake();
            if (!n2.g() || securityParametersHandshake.getLocalVerifyData() == null || securityParametersHandshake.getPeerVerifyData() == null) {
                throw new TlsFatalAlert((short) 80);
            }
            this.f14864a.e();
            this.f14874k = (short) 21;
            this.f14866c = new DeferredHash(n2);
            this.alertQueue.shrink();
            this.handshakeQueue.shrink();
            ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
            this.appDataSplitEnabled = !TlsUtils.isTLSv11(negotiatedVersion);
            this.appDataReady = true;
            this.keyUpdateEnabled = TlsUtils.isTLSv13(negotiatedVersion);
            if (this.f14879p) {
                this.tlsInputStream = new TlsInputStream(this);
                this.tlsOutputStream = new TlsOutputStream(this);
            }
            SessionParameters sessionParameters = this.f14868e;
            if (sessionParameters == null) {
                this.f14869f = securityParametersHandshake.getMasterSecret();
                this.f14868e = new SessionParameters.Builder().setCipherSuite(securityParametersHandshake.getCipherSuite()).setCompressionAlgorithm(securityParametersHandshake.getCompressionAlgorithm()).setExtendedMasterSecret(securityParametersHandshake.isExtendedMasterSecret()).setLocalCertificate(securityParametersHandshake.getLocalCertificate()).setMasterSecret(n2.getCrypto().adoptSecret(this.f14869f)).setNegotiatedVersion(securityParametersHandshake.getNegotiatedVersion()).setPeerCertificate(securityParametersHandshake.getPeerCertificate()).setPSKIdentity(securityParametersHandshake.getPSKIdentity()).setSRPIdentity(securityParametersHandshake.getSRPIdentity()).setServerExtensions(this.f14873j).build();
                this.f14867d = TlsUtils.importSession(securityParametersHandshake.getSessionID(), this.f14868e);
            } else {
                securityParametersHandshake.Q = sessionParameters.getLocalCertificate();
                securityParametersHandshake.R = this.f14868e.getPeerCertificate();
                securityParametersHandshake.x = this.f14868e.getPSKIdentity();
                securityParametersHandshake.y = this.f14868e.getSRPIdentity();
            }
            n2.e(o(), this.f14867d);
        } finally {
            f();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h0(byte[] bArr, int i2, int i3) {
        ProtocolVersion serverVersion;
        if (i3 < 4) {
            throw new TlsFatalAlert((short) 80);
        }
        short readUint8 = TlsUtils.readUint8(bArr, i2);
        if (readUint8 != 0 && readUint8 != 1 && (readUint8 == 4 ? !((serverVersion = m().getServerVersion()) == null || TlsUtils.isTLSv13(serverVersion)) : readUint8 != 24)) {
            this.f14866c.update(bArr, i2, i3);
        }
        int i4 = 0;
        do {
            int min = Math.min(i3 - i4, this.f14864a.f());
            S((short) 22, bArr, i2 + i4, min);
            i4 += min;
        } while (i4 < i3);
    }

    public boolean isClosed() {
        return this.closed;
    }

    public boolean isConnected() {
        AbstractTlsContext n2;
        return (this.closed || (n2 = n()) == null || !n2.f()) ? false : true;
    }

    public boolean isHandshaking() {
        AbstractTlsContext n2;
        return (this.closed || (n2 = n()) == null || !n2.g()) ? false : true;
    }

    public boolean isResumableHandshake() {
        return this.resumableHandshake;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean l(TlsSession tlsSession) {
        SessionParameters exportSessionParameters;
        this.f14867d = null;
        this.f14868e = null;
        this.f14869f = null;
        if (tlsSession == null || !tlsSession.isResumable() || (exportSessionParameters = tlsSession.exportSessionParameters()) == null) {
            return false;
        }
        if (!exportSessionParameters.isExtendedMasterSecret()) {
            TlsPeer o2 = o();
            if (!o2.allowLegacyResumption() || o2.requiresExtendedMasterSecret()) {
                return false;
            }
        }
        TlsSecret Q = TlsUtils.Q(m().getCrypto(), exportSessionParameters.getMasterSecret());
        if (Q == null) {
            return false;
        }
        this.f14867d = tlsSession;
        this.f14868e = exportSessionParameters;
        this.f14869f = Q;
        return true;
    }

    protected abstract TlsContext m();

    abstract AbstractTlsContext n();

    protected abstract TlsPeer o();

    public void offerInput(byte[] bArr) {
        offerInput(bArr, 0, bArr.length);
    }

    public void offerInput(byte[] bArr, int i2, int i3) {
        if (this.f14879p) {
            throw new IllegalStateException("Cannot use offerInput() in blocking mode! Use getInputStream() instead.");
        }
        if (this.closed) {
            throw new IOException("Connection is closed, cannot accept any more input");
        }
        if (this.f14880q.available() == 0 && Q(bArr, i2, i3)) {
            if (this.closed && !this.appDataReady) {
                throw new TlsFatalAlert((short) 80);
            }
            return;
        }
        this.f14880q.addBytes(bArr, i2, i3);
        while (this.f14880q.available() >= 5) {
            byte[] bArr2 = new byte[5];
            if (5 != this.f14880q.peek(bArr2)) {
                throw new TlsFatalAlert((short) 80);
            }
            if (this.f14880q.available() < P(bArr2).getRecordSize()) {
                return;
            }
            R();
            if (this.closed) {
                if (!this.appDataReady) {
                    throw new TlsFatalAlert((short) 80);
                }
                return;
            }
        }
    }

    protected int p() {
        return 0;
    }

    public RecordPreview previewInputRecord(byte[] bArr) {
        if (this.f14879p) {
            throw new IllegalStateException("Cannot use previewInputRecord() in blocking mode!");
        }
        if (this.f14880q.available() == 0) {
            if (this.closed) {
                throw new IOException("Connection is closed, cannot accept any more input");
            }
            return P(bArr);
        }
        throw new IllegalStateException("Can only use previewInputRecord() for record-aligned input.");
    }

    public RecordPreview previewOutputRecord(int i2) {
        if (this.appDataReady) {
            if (this.f14879p) {
                throw new IllegalStateException("Cannot use previewOutputRecord() in blocking mode!");
            }
            if (this.f14881r.getBuffer().available() == 0) {
                if (this.closed) {
                    throw new IOException("Connection is closed, cannot produce any more output");
                }
                if (i2 < 1) {
                    return new RecordPreview(0, 0);
                }
                if (this.appDataSplitEnabled) {
                    int i3 = this.appDataSplitMode;
                    if (i3 == 1 || i3 == 2) {
                        return RecordPreview.a(this.f14864a.k(0), this.f14864a.k(i2));
                    }
                    RecordPreview k2 = this.f14864a.k(1);
                    return i2 > 1 ? RecordPreview.a(k2, this.f14864a.k(i2 - 1)) : k2;
                }
                RecordPreview k3 = this.f14864a.k(i2);
                if (this.keyUpdateEnabled) {
                    if (this.keyUpdatePendingSend || this.f14864a.g()) {
                        return RecordPreview.b(k3, this.f14864a.l(HandshakeMessageOutput.a(1)));
                    }
                    return k3;
                }
                return k3;
            }
            throw new IllegalStateException("Can only use previewOutputRecord() for record-aligned output.");
        }
        throw new IllegalStateException("Cannot use previewOutputRecord() until initial handshake completed.");
    }

    protected void q(short s2, short s3) {
        o().notifyAlertReceived(s2, s3);
        if (s2 == 1) {
            r(s3);
        } else {
            v();
            throw new TlsFatalAlertReceived(s3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void r(short s2) {
        if (s2 == 0) {
            if (!this.appDataReady) {
                throw new TlsFatalAlert((short) 40);
            }
            t(false);
        } else if (s2 == 41) {
            throw new TlsFatalAlert((short) 10);
        } else {
            if (s2 == 100) {
                throw new TlsFatalAlert((short) 40);
            }
        }
    }

    public int readApplicationData(byte[] bArr, int i2, int i3) {
        if (i3 < 1) {
            return 0;
        }
        while (this.applicationDataQueue.available() == 0) {
            if (this.closed) {
                if (this.failedWithError) {
                    throw new IOException("Cannot read application data on failed TLS connection");
                }
                return -1;
            } else if (!this.appDataReady) {
                throw new IllegalStateException("Cannot read application data until initial handshake completed.");
            } else {
                R();
            }
        }
        int min = Math.min(i3, this.applicationDataQueue.available());
        this.applicationDataQueue.removeData(bArr, i2, min, 0);
        return min;
    }

    public int readInput(byte[] bArr, int i2, int i3) {
        if (this.f14879p) {
            throw new IllegalStateException("Cannot use readInput() in blocking mode! Use getInputStream() instead.");
        }
        int min = Math.min(i3, this.applicationDataQueue.available());
        if (min < 1) {
            return 0;
        }
        this.applicationDataQueue.removeData(bArr, i2, min, 0);
        return min;
    }

    public int readOutput(byte[] bArr, int i2, int i3) {
        if (this.f14879p) {
            throw new IllegalStateException("Cannot use readOutput() in blocking mode! Use getOutputStream() instead.");
        }
        int min = Math.min(getAvailableOutputBytes(), i3);
        this.f14881r.getBuffer().removeData(bArr, i2, min, 0);
        return min;
    }

    public void resumeHandshake() {
        if (!this.f14879p) {
            throw new IllegalStateException("Cannot use resumeHandshake() in non-blocking mode!");
        }
        if (!isHandshaking()) {
            throw new IllegalStateException("No handshake in progress");
        }
        d();
    }

    protected void s() {
    }

    public void setAppDataSplitMode(int i2) {
        if (i2 >= 0 && i2 <= 2) {
            this.appDataSplitMode = i2;
            return;
        }
        throw new IllegalArgumentException("Illegal appDataSplitMode mode: " + i2);
    }

    public void setResumableHandshake(boolean z) {
        this.resumableHandshake = z;
    }

    protected void t(boolean z) {
        if (this.closed) {
            return;
        }
        this.closed = true;
        if (!this.appDataReady) {
            f();
            if (z) {
                H((short) 90, "User canceled handshake");
            }
        }
        H((short) 0, "Connection closed");
        g();
    }

    protected void u(short s2, String str, Throwable th) {
        if (((this.appDataReady || isResumableHandshake()) && (th instanceof InterruptedIOException)) || this.closed) {
            return;
        }
        G(s2, str, th);
        v();
    }

    protected void v() {
        this.closed = true;
        this.failedWithError = true;
        y();
        if (!this.appDataReady) {
            f();
        }
        g();
    }

    protected abstract void w(short s2, HandshakeMessageInput handshakeMessageInput);

    public void writeApplicationData(byte[] bArr, int i2, int i3) {
        if (!this.appDataReady) {
            throw new IllegalStateException("Cannot write application data until initial handshake completed.");
        }
        synchronized (this.f14865b) {
            while (i3 > 0) {
                if (this.closed) {
                    throw new IOException("Cannot write application data on closed/failed TLS connection");
                }
                if (this.appDataSplitEnabled) {
                    int i4 = this.appDataSplitMode;
                    if (i4 != 1) {
                        if (i4 == 2) {
                            this.appDataSplitEnabled = false;
                        } else if (i3 > 1) {
                            S((short) 23, bArr, i2, 1);
                            i2++;
                            i3--;
                        }
                    }
                    S((short) 23, TlsUtils.EMPTY_BYTES, 0, 0);
                } else if (this.keyUpdateEnabled) {
                    if (this.keyUpdatePendingSend) {
                        W(false);
                    } else if (this.f14864a.g()) {
                        W(true);
                    }
                }
                int min = Math.min(i3, this.f14864a.f());
                S((short) 23, bArr, i2, min);
                i2 += min;
                i3 -= min;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003c A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean x() {
        int i2;
        SecurityParameters securityParametersConnection = m().getSecurityParametersConnection();
        if (securityParametersConnection != null && securityParametersConnection.isSecureRenegotiation()) {
            Certificate localCertificate = securityParametersConnection.getEntity() == 0 ? securityParametersConnection.getLocalCertificate() : securityParametersConnection.getPeerCertificate();
            if (localCertificate != null && !localCertificate.isEmpty()) {
                i2 = p();
                if (i2 == 1) {
                    if (i2 != 2) {
                        O();
                        return false;
                    }
                    c(true);
                    return true;
                }
                return false;
            }
        }
        i2 = 0;
        if (i2 == 1) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void y() {
        TlsSecret tlsSecret = this.f14869f;
        if (tlsSecret != null) {
            tlsSecret.destroy();
            this.f14869f = null;
        }
        SessionParameters sessionParameters = this.f14868e;
        if (sessionParameters != null) {
            sessionParameters.clear();
            this.f14868e = null;
        }
        TlsSession tlsSession = this.f14867d;
        if (tlsSession != null) {
            tlsSession.invalidate();
            this.f14867d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean z() {
        return this.appDataReady;
    }
}
