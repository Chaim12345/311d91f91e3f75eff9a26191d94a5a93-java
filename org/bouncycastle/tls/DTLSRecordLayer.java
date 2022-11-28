package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsDecodeResult;
import org.bouncycastle.tls.crypto.TlsEncodeResult;
import org.bouncycastle.tls.crypto.TlsNullNullCipher;
import org.bouncycastle.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class DTLSRecordLayer implements DatagramTransport {
    private static final int MAX_FRAGMENT_LENGTH = 16384;
    private static final int RECORD_HEADER_LENGTH = 13;
    private static final long RETRANSMIT_TIMEOUT = 240000;
    private static final long TCP_MSL = 120000;
    private final TlsContext context;
    private DTLSEpoch currentEpoch;
    private volatile boolean inConnection;
    private final TlsPeer peer;
    private DTLSEpoch pendingEpoch;
    private volatile int plaintextLimit;
    private DTLSEpoch readEpoch;
    private final DatagramTransport transport;
    private DTLSEpoch writeEpoch;
    private final ByteQueue recordQueue = new ByteQueue();
    private final Object writeLock = new Object();
    private volatile boolean closed = false;
    private volatile boolean failed = false;
    private volatile ProtocolVersion readVersion = null;
    private volatile ProtocolVersion writeVersion = null;
    private DTLSHandshakeRetransmit retransmit = null;
    private DTLSEpoch retransmitEpoch = null;
    private Timeout retransmitTimeout = null;
    private TlsHeartbeat heartbeat = null;
    private boolean heartBeatResponder = false;
    private HeartbeatMessage heartbeatInFlight = null;
    private Timeout heartbeatTimeout = null;
    private int heartbeatResendMillis = -1;
    private Timeout heartbeatResendTimeout = null;
    private volatile boolean inHandshake = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSRecordLayer(TlsContext tlsContext, TlsPeer tlsPeer, DatagramTransport datagramTransport) {
        this.context = tlsContext;
        this.peer = tlsPeer;
        this.transport = datagramTransport;
        DTLSEpoch dTLSEpoch = new DTLSEpoch(0, TlsNullNullCipher.INSTANCE);
        this.currentEpoch = dTLSEpoch;
        this.pendingEpoch = null;
        this.readEpoch = dTLSEpoch;
        this.writeEpoch = dTLSEpoch;
        m(16384);
    }

    private void closeTransport() {
        if (this.closed) {
            return;
        }
        try {
            if (!this.failed) {
                p((short) 0, null);
            }
            this.transport.close();
        } catch (Exception unused) {
        }
        this.closed = true;
    }

    private static long getMacSequenceNumber(int i2, long j2) {
        return ((i2 & BodyPartID.bodyIdMax) << 48) | j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] i(byte[] bArr, int i2, int i3) {
        if (i3 >= 13 && 22 == TlsUtils.readUint8(bArr, i2 + 0)) {
            if (ProtocolVersion.DTLSv10.isEqualOrEarlierVersionOf(TlsUtils.readVersion(bArr, i2 + 1)) && TlsUtils.readUint16(bArr, i2 + 3) == 0) {
                int readUint16 = TlsUtils.readUint16(bArr, i2 + 11);
                if (i3 >= readUint16 + 13 && readUint16 <= 16384) {
                    int i4 = i2 + 13;
                    return TlsUtils.copyOfRangeExact(bArr, i4, readUint16 + i4);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void l(DatagramSender datagramSender, long j2, byte[] bArr) {
        TlsUtils.checkUint16(bArr.length);
        int length = bArr.length + 13;
        byte[] bArr2 = new byte[length];
        TlsUtils.writeUint8((short) 22, bArr2, 0);
        TlsUtils.writeVersion(ProtocolVersion.DTLSv10, bArr2, 1);
        TlsUtils.writeUint16(0, bArr2, 3);
        TlsUtils.writeUint48(j2, bArr2, 5);
        TlsUtils.writeUint16(bArr.length, bArr2, 11);
        System.arraycopy(bArr, 0, bArr2, 13, bArr.length);
        sendDatagram(datagramSender, bArr2, 0, length);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0049 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int processRecord(int i2, byte[] bArr, byte[] bArr2, int i3) {
        DTLSEpoch dTLSEpoch;
        DTLSEpoch dTLSEpoch2;
        DTLSEpoch dTLSEpoch3;
        DTLSEpoch dTLSEpoch4;
        if (i2 < 13) {
            return -1;
        }
        int readUint16 = TlsUtils.readUint16(bArr, 11);
        if (i2 != readUint16 + 13) {
            return -1;
        }
        short readUint8 = TlsUtils.readUint8(bArr, 0);
        switch (readUint8) {
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                int readUint162 = TlsUtils.readUint16(bArr, 3);
                if (readUint162 == this.readEpoch.c()) {
                    dTLSEpoch3 = this.readEpoch;
                } else if (readUint8 != 22 || (dTLSEpoch2 = this.retransmitEpoch) == null || readUint162 != dTLSEpoch2.c()) {
                    dTLSEpoch = null;
                    if (dTLSEpoch != null) {
                        return -1;
                    }
                    long readUint48 = TlsUtils.readUint48(bArr, 5);
                    if (dTLSEpoch.d().c(readUint48)) {
                        return -1;
                    }
                    ProtocolVersion readVersion = TlsUtils.readVersion(bArr, 1);
                    if (readVersion.isDTLS()) {
                        if (this.readVersion != null && !this.readVersion.equals(readVersion)) {
                            if (!(c() == 0 && readUint16 > 0 && 22 == readUint8 && 1 == TlsUtils.readUint8(bArr, 13))) {
                                return -1;
                            }
                        }
                        TlsDecodeResult decodeCiphertext = dTLSEpoch.b().decodeCiphertext(getMacSequenceNumber(dTLSEpoch.c(), readUint48), readUint8, readVersion, bArr, 13, readUint16);
                        dTLSEpoch.d().a(readUint48);
                        if (decodeCiphertext.len > this.plaintextLimit) {
                            return -1;
                        }
                        if (decodeCiphertext.len >= 1 || decodeCiphertext.contentType == 23) {
                            if (this.readVersion == null) {
                                if (!(c() == 0 && readUint16 > 0 && 22 == readUint8 && 3 == TlsUtils.readUint8(bArr, 13))) {
                                    this.readVersion = readVersion;
                                } else if (!ProtocolVersion.DTLSv12.isEqualOrLaterVersionOf(readVersion)) {
                                    return -1;
                                }
                            }
                            switch (decodeCiphertext.contentType) {
                                case 20:
                                    for (int i4 = 0; i4 < decodeCiphertext.len; i4++) {
                                        if (TlsUtils.readUint8(decodeCiphertext.buf, decodeCiphertext.off + i4) == 1 && (dTLSEpoch4 = this.pendingEpoch) != null) {
                                            this.readEpoch = dTLSEpoch4;
                                        }
                                    }
                                    return -1;
                                case 21:
                                    if (decodeCiphertext.len == 2) {
                                        short readUint82 = TlsUtils.readUint8(decodeCiphertext.buf, decodeCiphertext.off);
                                        short readUint83 = TlsUtils.readUint8(decodeCiphertext.buf, decodeCiphertext.off + 1);
                                        this.peer.notifyAlertReceived(readUint82, readUint83);
                                        if (readUint82 == 2) {
                                            b();
                                            throw new TlsFatalAlert(readUint83);
                                        } else if (readUint83 == 0) {
                                            closeTransport();
                                            return -1;
                                        } else {
                                            return -1;
                                        }
                                    }
                                    return -1;
                                case 22:
                                    if (!this.inHandshake) {
                                        DTLSHandshakeRetransmit dTLSHandshakeRetransmit = this.retransmit;
                                        if (dTLSHandshakeRetransmit != null) {
                                            dTLSHandshakeRetransmit.receivedHandshakeRecord(readUint162, decodeCiphertext.buf, decodeCiphertext.off, decodeCiphertext.len);
                                            return -1;
                                        }
                                        return -1;
                                    }
                                    break;
                                case 23:
                                    if (this.inHandshake) {
                                        return -1;
                                    }
                                    break;
                                case 24:
                                    if (this.heartbeatInFlight != null || this.heartBeatResponder) {
                                        try {
                                            HeartbeatMessage parse = HeartbeatMessage.parse(new ByteArrayInputStream(decodeCiphertext.buf, decodeCiphertext.off, decodeCiphertext.len));
                                            if (parse != null) {
                                                short type = parse.getType();
                                                if (type != 1) {
                                                    if (type == 2 && this.heartbeatInFlight != null && Arrays.areEqual(parse.getPayload(), this.heartbeatInFlight.getPayload())) {
                                                        resetHeartbeat();
                                                    }
                                                } else if (this.heartBeatResponder) {
                                                    sendHeartbeatMessage(HeartbeatMessage.create(this.context, (short) 2, parse.getPayload()));
                                                }
                                            }
                                            return -1;
                                        } catch (Exception unused) {
                                            return -1;
                                        }
                                    }
                                    return -1;
                                default:
                                    return -1;
                            }
                            if (!this.inHandshake && this.retransmit != null) {
                                this.retransmit = null;
                                this.retransmitEpoch = null;
                                this.retransmitTimeout = null;
                            }
                            System.arraycopy(decodeCiphertext.buf, decodeCiphertext.off, bArr2, i3, decodeCiphertext.len);
                            return decodeCiphertext.len;
                        }
                        return -1;
                    }
                    return -1;
                } else {
                    dTLSEpoch3 = this.retransmitEpoch;
                }
                dTLSEpoch = dTLSEpoch3;
                if (dTLSEpoch != null) {
                }
                break;
            default:
                return -1;
        }
    }

    private void raiseAlert(short s2, short s3, String str, Throwable th) {
        this.peer.notifyAlertRaised(s2, s3, str, th);
        sendRecord((short) 21, new byte[]{(byte) s2, (byte) s3}, 0, 2);
    }

    private int receiveDatagram(byte[] bArr, int i2, int i3, int i4) {
        try {
            return this.transport.receive(bArr, i2, i3, i4);
        } catch (SocketTimeoutException unused) {
            return -1;
        } catch (InterruptedIOException e2) {
            e2.bytesTransferred = 0;
            throw e2;
        }
    }

    private int receiveRecord(byte[] bArr, int i2, int i3, int i4) {
        int i5;
        if (this.recordQueue.available() > 0) {
            if (this.recordQueue.available() >= 13) {
                byte[] bArr2 = new byte[2];
                this.recordQueue.read(bArr2, 0, 2, 11);
                i5 = TlsUtils.readUint16(bArr2, 0);
            } else {
                i5 = 0;
            }
            int min = Math.min(this.recordQueue.available(), i5 + 13);
            this.recordQueue.removeData(bArr, i2, min, 0);
            return min;
        }
        int receiveDatagram = receiveDatagram(bArr, i2, i3, i4);
        if (receiveDatagram >= 13) {
            this.inConnection = true;
            int readUint16 = TlsUtils.readUint16(bArr, i2 + 11) + 13;
            if (receiveDatagram > readUint16) {
                this.recordQueue.addData(bArr, i2 + readUint16, receiveDatagram - readUint16);
                return readUint16;
            }
            return receiveDatagram;
        }
        return receiveDatagram;
    }

    private void resetHeartbeat() {
        this.heartbeatInFlight = null;
        this.heartbeatResendMillis = -1;
        this.heartbeatResendTimeout = null;
        this.heartbeatTimeout = new Timeout(this.heartbeat.getIdleMillis());
    }

    private static void sendDatagram(DatagramSender datagramSender, byte[] bArr, int i2, int i3) {
        try {
            datagramSender.send(bArr, i2, i3);
        } catch (InterruptedIOException e2) {
            e2.bytesTransferred = 0;
            throw e2;
        }
    }

    private void sendHeartbeatMessage(HeartbeatMessage heartbeatMessage) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        heartbeatMessage.encode(byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        sendRecord((short) 24, byteArray, 0, byteArray.length);
    }

    private void sendRecord(short s2, byte[] bArr, int i2, int i3) {
        if (this.writeVersion == null) {
            return;
        }
        if (i3 > this.plaintextLimit) {
            throw new TlsFatalAlert((short) 80);
        }
        if (i3 < 1 && s2 != 23) {
            throw new TlsFatalAlert((short) 80);
        }
        synchronized (this.writeLock) {
            int c2 = this.writeEpoch.c();
            long a2 = this.writeEpoch.a();
            long macSequenceNumber = getMacSequenceNumber(c2, a2);
            ProtocolVersion protocolVersion = this.writeVersion;
            TlsEncodeResult encodePlaintext = this.writeEpoch.b().encodePlaintext(macSequenceNumber, s2, protocolVersion, 13, bArr, i2, i3);
            int i4 = encodePlaintext.len - 13;
            TlsUtils.checkUint16(i4);
            TlsUtils.writeUint8(encodePlaintext.recordType, encodePlaintext.buf, encodePlaintext.off + 0);
            TlsUtils.writeVersion(protocolVersion, encodePlaintext.buf, encodePlaintext.off + 1);
            TlsUtils.writeUint16(c2, encodePlaintext.buf, encodePlaintext.off + 3);
            TlsUtils.writeUint48(a2, encodePlaintext.buf, encodePlaintext.off + 5);
            TlsUtils.writeUint16(i4, encodePlaintext.buf, encodePlaintext.off + 11);
            sendDatagram(this.transport, encodePlaintext.buf, encodePlaintext.off, encodePlaintext.len);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(short s2) {
        if (this.closed) {
            return;
        }
        if (this.inConnection) {
            try {
                raiseAlert((short) 2, s2, null, null);
            } catch (Exception unused) {
            }
        }
        this.failed = true;
        closeTransport();
    }

    void b() {
        if (this.closed) {
            return;
        }
        this.failed = true;
        closeTransport();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.readEpoch.c();
    }

    @Override // org.bouncycastle.tls.TlsCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        if (this.inHandshake && this.inConnection) {
            p((short) 90, "User canceled handshake");
        }
        closeTransport();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProtocolVersion d() {
        return this.readVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(DTLSHandshakeRetransmit dTLSHandshakeRetransmit) {
        DTLSEpoch dTLSEpoch = this.readEpoch;
        DTLSEpoch dTLSEpoch2 = this.currentEpoch;
        if (dTLSEpoch == dTLSEpoch2 || this.writeEpoch == dTLSEpoch2) {
            throw new IllegalStateException();
        }
        if (dTLSHandshakeRetransmit != null) {
            this.retransmit = dTLSHandshakeRetransmit;
            this.retransmitEpoch = dTLSEpoch2;
            this.retransmitTimeout = new Timeout(RETRANSMIT_TIMEOUT);
        }
        this.inHandshake = false;
        this.currentEpoch = this.pendingEpoch;
        this.pendingEpoch = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(TlsHeartbeat tlsHeartbeat, boolean z) {
        if (this.inHandshake) {
            throw new IllegalStateException();
        }
        this.heartbeat = tlsHeartbeat;
        this.heartBeatResponder = z;
        if (tlsHeartbeat != null) {
            resetHeartbeat();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(TlsCipher tlsCipher) {
        if (this.pendingEpoch != null) {
            throw new IllegalStateException();
        }
        this.pendingEpoch = new DTLSEpoch(this.writeEpoch.c() + 1, tlsCipher);
    }

    @Override // org.bouncycastle.tls.DatagramReceiver
    public int getReceiveLimit() {
        return Math.min(this.plaintextLimit, this.readEpoch.b().getPlaintextLimit(this.transport.getReceiveLimit() - 13));
    }

    @Override // org.bouncycastle.tls.DatagramSender
    public int getSendLimit() {
        return Math.min(this.plaintextLimit, this.writeEpoch.b().getPlaintextLimit(this.transport.getSendLimit() - 13));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h() {
        return this.closed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(long j2) {
        this.inConnection = true;
        this.currentEpoch.e(j2);
        this.currentEpoch.d().b(j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        DTLSEpoch dTLSEpoch = this.retransmitEpoch;
        if (dTLSEpoch == null) {
            dTLSEpoch = this.currentEpoch;
        }
        this.writeEpoch = dTLSEpoch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(int i2) {
        this.plaintextLimit = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(ProtocolVersion protocolVersion) {
        this.readVersion = protocolVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(ProtocolVersion protocolVersion) {
        this.writeVersion = protocolVersion;
    }

    void p(short s2, String str) {
        raiseAlert((short) 1, s2, str, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a6 A[LOOP:0: B:3:0x000a->B:32:0x00a6, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a5 A[SYNTHETIC] */
    @Override // org.bouncycastle.tls.DatagramReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int receive(byte[] bArr, int i2, int i3, int i4) {
        Timeout timeout;
        int min;
        int processRecord;
        long currentTimeMillis = System.currentTimeMillis();
        Timeout c2 = Timeout.c(i4, currentTimeMillis);
        byte[] bArr2 = null;
        while (i4 >= 0) {
            Timeout timeout2 = this.retransmitTimeout;
            if (timeout2 != null && timeout2.f(currentTimeMillis) < 1) {
                this.retransmit = null;
                this.retransmitEpoch = null;
                this.retransmitTimeout = null;
            }
            if (!Timeout.e(this.heartbeatTimeout, currentTimeMillis)) {
                if (Timeout.e(this.heartbeatResendTimeout, currentTimeMillis)) {
                    int b2 = DTLSReliableHandshake.b(this.heartbeatResendMillis);
                    this.heartbeatResendMillis = b2;
                    timeout = new Timeout(b2, currentTimeMillis);
                }
                int a2 = Timeout.a(Timeout.a(i4, this.heartbeatTimeout, currentTimeMillis), this.heartbeatResendTimeout, currentTimeMillis);
                int i5 = a2 >= 0 ? a2 : 1;
                min = Math.min(i3, getReceiveLimit()) + 13;
                if (bArr2 != null || bArr2.length < min) {
                    bArr2 = new byte[min];
                }
                processRecord = processRecord(receiveRecord(bArr2, 0, min, i5), bArr2, bArr, i2);
                if (processRecord < 0) {
                    return processRecord;
                }
                currentTimeMillis = System.currentTimeMillis();
                i4 = Timeout.d(c2, currentTimeMillis);
            } else if (this.heartbeatInFlight != null) {
                throw new TlsTimeoutException("Heartbeat timed out");
            } else {
                this.heartbeatInFlight = HeartbeatMessage.create(this.context, (short) 1, this.heartbeat.generatePayload());
                this.heartbeatTimeout = new Timeout(this.heartbeat.getTimeoutMillis(), currentTimeMillis);
                this.heartbeatResendMillis = 1000;
                timeout = new Timeout(1000, currentTimeMillis);
            }
            this.heartbeatResendTimeout = timeout;
            sendHeartbeatMessage(this.heartbeatInFlight);
            int a22 = Timeout.a(Timeout.a(i4, this.heartbeatTimeout, currentTimeMillis), this.heartbeatResendTimeout, currentTimeMillis);
            if (a22 >= 0) {
            }
            min = Math.min(i3, getReceiveLimit()) + 13;
            if (bArr2 != null) {
            }
            bArr2 = new byte[min];
            processRecord = processRecord(receiveRecord(bArr2, 0, min, i5), bArr2, bArr, i2);
            if (processRecord < 0) {
            }
        }
        return -1;
    }

    @Override // org.bouncycastle.tls.DatagramSender
    public void send(byte[] bArr, int i2, int i3) {
        short s2;
        if (this.inHandshake || this.writeEpoch == this.retransmitEpoch) {
            s2 = 22;
            if (TlsUtils.readUint8(bArr, i2) == 20) {
                DTLSEpoch dTLSEpoch = null;
                if (this.inHandshake) {
                    dTLSEpoch = this.pendingEpoch;
                } else if (this.writeEpoch == this.retransmitEpoch) {
                    dTLSEpoch = this.currentEpoch;
                }
                if (dTLSEpoch == null) {
                    throw new IllegalStateException();
                }
                sendRecord((short) 20, new byte[]{1}, 0, 1);
                this.writeEpoch = dTLSEpoch;
            }
        } else {
            s2 = 23;
        }
        sendRecord(s2, bArr, i2, i3);
    }
}
