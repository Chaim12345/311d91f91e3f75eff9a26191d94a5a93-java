package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.util.Integers;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class DTLSReliableHandshake {
    private static final int MAX_RECEIVE_AHEAD = 16;
    private static final int MAX_RESEND_MILLIS = 60000;
    private static final int MESSAGE_HEADER_LENGTH = 12;
    private TlsHandshakeHash handshakeHash;
    private Timeout handshakeTimeout;
    private int next_receive_seq;
    private int next_send_seq;
    private DTLSRecordLayer recordLayer;
    private int resendMillis;
    private Timeout resendTimeout;
    private Hashtable currentInboundFlight = new Hashtable();
    private Hashtable previousInboundFlight = null;
    private Vector outboundFlight = new Vector();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class Message {
        private final byte[] body;
        private final int message_seq;
        private final short msg_type;

        private Message(int i2, short s2, byte[] bArr) {
            this.message_seq = i2;
            this.msg_type = s2;
            this.body = bArr;
        }

        public byte[] getBody() {
            return this.body;
        }

        public int getSeq() {
            return this.message_seq;
        }

        public short getType() {
            return this.msg_type;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class RecordLayerBuffer extends ByteArrayOutputStream {
        RecordLayerBuffer(int i2) {
            super(i2);
        }

        void a(DTLSRecordLayer dTLSRecordLayer) {
            dTLSRecordLayer.send(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
            ((ByteArrayOutputStream) this).buf = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSReliableHandshake(TlsContext tlsContext, DTLSRecordLayer dTLSRecordLayer, int i2, DTLSRequest dTLSRequest) {
        this.resendMillis = -1;
        this.resendTimeout = null;
        this.next_send_seq = 0;
        this.next_receive_seq = 0;
        this.recordLayer = dTLSRecordLayer;
        this.handshakeHash = new DeferredHash(tlsContext);
        this.handshakeTimeout = Timeout.b(i2);
        if (dTLSRequest != null) {
            this.resendMillis = 1000;
            this.resendTimeout = new Timeout(1000);
            long d2 = dTLSRequest.d();
            int c2 = dTLSRequest.c();
            byte[] b2 = dTLSRequest.b();
            this.recordLayer.j(d2);
            this.currentInboundFlight.put(Integers.valueOf(c2), new DTLSReassembler((short) 1, b2.length - 12));
            this.next_send_seq = 1;
            this.next_receive_seq = c2 + 1;
            this.handshakeHash.update(b2, 0, b2.length);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(int i2) {
        return Math.min(i2 * 2, 60000);
    }

    private static boolean checkAll(Hashtable hashtable) {
        Enumeration elements = hashtable.elements();
        while (elements.hasMoreElements()) {
            if (((DTLSReassembler) elements.nextElement()).b() == null) {
                return false;
            }
        }
        return true;
    }

    private void checkInboundFlight() {
        Enumeration keys = this.currentInboundFlight.keys();
        while (keys.hasMoreElements()) {
            ((Integer) keys.nextElement()).intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DTLSRequest f(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        byte[] i4 = DTLSRecordLayer.i(bArr, i2, i3);
        if (i4 == null || i4.length < 12) {
            return null;
        }
        long readUint48 = TlsUtils.readUint48(bArr, i2 + 5);
        if (1 != TlsUtils.readUint8(i4, 0)) {
            return null;
        }
        int readUint24 = TlsUtils.readUint24(i4, 1);
        if (i4.length == readUint24 + 12 && TlsUtils.readUint24(i4, 6) == 0 && readUint24 == TlsUtils.readUint24(i4, 9)) {
            return new DTLSRequest(readUint48, i4, ClientHello.parse(new ByteArrayInputStream(i4, 12, readUint24), outputStream));
        }
        return null;
    }

    private Message getPendingMessage() {
        byte[] b2;
        DTLSReassembler dTLSReassembler = (DTLSReassembler) this.currentInboundFlight.get(Integers.valueOf(this.next_receive_seq));
        if (dTLSReassembler == null || (b2 = dTLSReassembler.b()) == null) {
            return null;
        }
        this.previousInboundFlight = null;
        int i2 = this.next_receive_seq;
        this.next_receive_seq = i2 + 1;
        return updateHandshakeMessagesDigest(new Message(i2, dTLSReassembler.c(), b2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void j(DatagramSender datagramSender, long j2, byte[] bArr) {
        TlsUtils.checkUint8(bArr.length);
        int length = bArr.length + 3;
        byte[] bArr2 = new byte[length + 12];
        TlsUtils.writeUint8((short) 3, bArr2, 0);
        TlsUtils.writeUint24(length, bArr2, 1);
        TlsUtils.writeUint24(length, bArr2, 9);
        TlsUtils.writeVersion(ProtocolVersion.DTLSv10, bArr2, 12);
        TlsUtils.writeOpaque8(bArr, bArr2, 14);
        DTLSRecordLayer.l(datagramSender, j2, bArr2);
    }

    private void prepareInboundFlight(Hashtable hashtable) {
        resetAll(this.currentInboundFlight);
        this.previousInboundFlight = this.currentInboundFlight;
        this.currentInboundFlight = hashtable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processRecord(int i2, int i3, byte[] bArr, int i4, int i5) {
        int readUint24;
        int readUint242;
        DTLSReassembler dTLSReassembler;
        int i6 = i4;
        int i7 = i5;
        boolean z = false;
        while (i7 >= 12 && i7 >= (readUint242 = (readUint24 = TlsUtils.readUint24(bArr, i6 + 9)) + 12)) {
            int readUint243 = TlsUtils.readUint24(bArr, i6 + 1);
            int readUint244 = TlsUtils.readUint24(bArr, i6 + 6);
            if (readUint244 + readUint24 > readUint243) {
                break;
            }
            short readUint8 = TlsUtils.readUint8(bArr, i6 + 0);
            if (i3 != (readUint8 == 20 ? 1 : 0)) {
                break;
            }
            int readUint16 = TlsUtils.readUint16(bArr, i6 + 4);
            int i8 = this.next_receive_seq;
            if (readUint16 < i8 + i2) {
                if (readUint16 >= i8) {
                    DTLSReassembler dTLSReassembler2 = (DTLSReassembler) this.currentInboundFlight.get(Integers.valueOf(readUint16));
                    if (dTLSReassembler2 == null) {
                        dTLSReassembler2 = new DTLSReassembler(readUint8, readUint243);
                        this.currentInboundFlight.put(Integers.valueOf(readUint16), dTLSReassembler2);
                    }
                    dTLSReassembler2.a(readUint8, readUint243, bArr, i6 + 12, readUint244, readUint24);
                } else {
                    Hashtable hashtable = this.previousInboundFlight;
                    if (hashtable != null && (dTLSReassembler = (DTLSReassembler) hashtable.get(Integers.valueOf(readUint16))) != null) {
                        dTLSReassembler.a(readUint8, readUint243, bArr, i6 + 12, readUint244, readUint24);
                        z = true;
                    }
                }
            }
            i6 += readUint242;
            i7 -= readUint242;
        }
        if (z && checkAll(this.previousInboundFlight)) {
            resendOutboundFlight();
            resetAll(this.previousInboundFlight);
        }
    }

    private void resendOutboundFlight() {
        this.recordLayer.k();
        for (int i2 = 0; i2 < this.outboundFlight.size(); i2++) {
            writeMessage((Message) this.outboundFlight.elementAt(i2));
        }
        int b2 = b(this.resendMillis);
        this.resendMillis = b2;
        this.resendTimeout = new Timeout(b2);
    }

    private static void resetAll(Hashtable hashtable) {
        Enumeration elements = hashtable.elements();
        while (elements.hasMoreElements()) {
            ((DTLSReassembler) elements.nextElement()).d();
        }
    }

    private Message updateHandshakeMessagesDigest(Message message) {
        short type = message.getType();
        if (type != 0 && type != 3 && type != 24) {
            byte[] body = message.getBody();
            byte[] bArr = new byte[12];
            TlsUtils.writeUint8(type, bArr, 0);
            TlsUtils.writeUint24(body.length, bArr, 1);
            TlsUtils.writeUint16(message.getSeq(), bArr, 4);
            TlsUtils.writeUint24(0, bArr, 6);
            TlsUtils.writeUint24(body.length, bArr, 9);
            this.handshakeHash.update(bArr, 0, 12);
            this.handshakeHash.update(body, 0, body.length);
        }
        return message;
    }

    private void writeHandshakeFragment(Message message, int i2, int i3) {
        RecordLayerBuffer recordLayerBuffer = new RecordLayerBuffer(i3 + 12);
        TlsUtils.writeUint8(message.getType(), (OutputStream) recordLayerBuffer);
        TlsUtils.writeUint24(message.getBody().length, recordLayerBuffer);
        TlsUtils.writeUint16(message.getSeq(), recordLayerBuffer);
        TlsUtils.writeUint24(i2, recordLayerBuffer);
        TlsUtils.writeUint24(i3, recordLayerBuffer);
        recordLayerBuffer.write(message.getBody(), i2, i3);
        recordLayerBuffer.a(this.recordLayer);
    }

    private void writeMessage(Message message) {
        int sendLimit = this.recordLayer.getSendLimit() - 12;
        if (sendLimit < 1) {
            throw new TlsFatalAlert((short) 80);
        }
        int length = message.getBody().length;
        int i2 = 0;
        do {
            int min = Math.min(length - i2, sendLimit);
            writeHandshakeFragment(message, i2, min);
            i2 += min;
        } while (i2 < length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        DTLSHandshakeRetransmit dTLSHandshakeRetransmit = null;
        if (this.resendTimeout != null) {
            checkInboundFlight();
        } else {
            prepareInboundFlight(null);
            if (this.previousInboundFlight != null) {
                dTLSHandshakeRetransmit = new DTLSHandshakeRetransmit() { // from class: org.bouncycastle.tls.DTLSReliableHandshake.1
                    @Override // org.bouncycastle.tls.DTLSHandshakeRetransmit
                    public void receivedHandshakeRecord(int i2, byte[] bArr, int i3, int i4) {
                        DTLSReliableHandshake.this.processRecord(0, i2, bArr, i3, i4);
                    }
                };
            }
        }
        this.recordLayer.e(dTLSHandshakeRetransmit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsHandshakeHash d() {
        return this.handshakeHash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsHandshakeHash e() {
        TlsHandshakeHash tlsHandshakeHash = this.handshakeHash;
        this.handshakeHash = tlsHandshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Message g() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.resendTimeout == null) {
            this.resendMillis = 1000;
            this.resendTimeout = new Timeout(1000, currentTimeMillis);
            prepareInboundFlight(new Hashtable());
        }
        byte[] bArr = null;
        while (!this.recordLayer.h()) {
            Message pendingMessage = getPendingMessage();
            if (pendingMessage != null) {
                return pendingMessage;
            }
            if (Timeout.e(this.handshakeTimeout, currentTimeMillis)) {
                throw new TlsTimeoutException("Handshake timed out");
            }
            int a2 = Timeout.a(Timeout.d(this.handshakeTimeout, currentTimeMillis), this.resendTimeout, currentTimeMillis);
            if (a2 < 1) {
                a2 = 1;
            }
            int receiveLimit = this.recordLayer.getReceiveLimit();
            if (bArr == null || bArr.length < receiveLimit) {
                bArr = new byte[receiveLimit];
            }
            int receive = this.recordLayer.receive(bArr, 0, receiveLimit, a2);
            if (receive < 0) {
                resendOutboundFlight();
            } else {
                processRecord(16, this.recordLayer.c(), bArr, 0, receive);
            }
            currentTimeMillis = System.currentTimeMillis();
        }
        throw new TlsFatalAlert((short) 90);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] h(short s2) {
        Message g2 = g();
        if (g2.getType() == s2) {
            return g2.getBody();
        }
        throw new TlsFatalAlert((short) 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i() {
        this.currentInboundFlight = new Hashtable();
        this.previousInboundFlight = null;
        this.outboundFlight = new Vector();
        this.resendMillis = -1;
        this.resendTimeout = null;
        this.next_receive_seq = 1;
        this.handshakeHash.reset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(short s2, byte[] bArr) {
        TlsUtils.checkUint24(bArr.length);
        if (this.resendTimeout != null) {
            checkInboundFlight();
            this.resendMillis = -1;
            this.resendTimeout = null;
            this.outboundFlight.removeAllElements();
        }
        int i2 = this.next_send_seq;
        this.next_send_seq = i2 + 1;
        Message message = new Message(i2, s2, bArr);
        this.outboundFlight.addElement(message);
        writeMessage(message);
        updateHandshakeMessagesDigest(message);
    }
}
