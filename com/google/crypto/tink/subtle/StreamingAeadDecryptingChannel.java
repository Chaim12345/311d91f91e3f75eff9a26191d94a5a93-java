package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class StreamingAeadDecryptingChannel implements ReadableByteChannel {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private byte[] aad;
    private ReadableByteChannel ciphertextChannel;
    private ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private final StreamSegmentDecrypter decrypter;
    private boolean definedState;
    private boolean endOfCiphertext;
    private boolean endOfPlaintext;
    private final int firstCiphertextSegmentSize;
    private ByteBuffer header;
    private boolean headerRead;
    private ByteBuffer plaintextSegment;
    private int segmentNr;

    public StreamingAeadDecryptingChannel(NonceBasedStreamingAead nonceBasedStreamingAead, ReadableByteChannel readableByteChannel, byte[] bArr) {
        this.decrypter = nonceBasedStreamingAead.newStreamSegmentDecrypter();
        this.ciphertextChannel = readableByteChannel;
        this.header = ByteBuffer.allocate(nonceBasedStreamingAead.getHeaderLength());
        this.aad = Arrays.copyOf(bArr, bArr.length);
        int ciphertextSegmentSize = nonceBasedStreamingAead.getCiphertextSegmentSize();
        this.ciphertextSegmentSize = ciphertextSegmentSize;
        ByteBuffer allocate = ByteBuffer.allocate(ciphertextSegmentSize + 1);
        this.ciphertextSegment = allocate;
        allocate.limit(0);
        this.firstCiphertextSegmentSize = ciphertextSegmentSize - nonceBasedStreamingAead.getCiphertextOffset();
        ByteBuffer allocate2 = ByteBuffer.allocate(nonceBasedStreamingAead.getPlaintextSegmentSize() + 16);
        this.plaintextSegment = allocate2;
        allocate2.limit(0);
        this.headerRead = false;
        this.endOfCiphertext = false;
        this.endOfPlaintext = false;
        this.segmentNr = 0;
        this.definedState = true;
    }

    private void readSomeCiphertext(ByteBuffer byteBuffer) {
        int read;
        do {
            read = this.ciphertextChannel.read(byteBuffer);
            if (read <= 0) {
                break;
            }
        } while (byteBuffer.remaining() > 0);
        if (read == -1) {
            this.endOfCiphertext = true;
        }
    }

    private void setUndefinedState() {
        this.definedState = false;
        this.plaintextSegment.limit(0);
    }

    private boolean tryLoadSegment() {
        if (!this.endOfCiphertext) {
            readSomeCiphertext(this.ciphertextSegment);
        }
        byte b2 = 0;
        if (this.ciphertextSegment.remaining() <= 0 || this.endOfCiphertext) {
            if (!this.endOfCiphertext) {
                ByteBuffer byteBuffer = this.ciphertextSegment;
                b2 = byteBuffer.get(byteBuffer.position() - 1);
                ByteBuffer byteBuffer2 = this.ciphertextSegment;
                byteBuffer2.position(byteBuffer2.position() - 1);
            }
            this.ciphertextSegment.flip();
            this.plaintextSegment.clear();
            try {
                this.decrypter.decryptSegment(this.ciphertextSegment, this.segmentNr, this.endOfCiphertext, this.plaintextSegment);
                this.segmentNr++;
                this.plaintextSegment.flip();
                this.ciphertextSegment.clear();
                if (!this.endOfCiphertext) {
                    this.ciphertextSegment.clear();
                    this.ciphertextSegment.limit(this.ciphertextSegmentSize + 1);
                    this.ciphertextSegment.put(b2);
                }
                return true;
            } catch (GeneralSecurityException e2) {
                setUndefinedState();
                throw new IOException(e2.getMessage() + "\n" + toString() + "\nsegmentNr:" + this.segmentNr + " endOfCiphertext:" + this.endOfCiphertext, e2);
            }
        }
        return false;
    }

    private boolean tryReadHeader() {
        if (this.endOfCiphertext) {
            throw new IOException("Ciphertext is too short");
        }
        readSomeCiphertext(this.header);
        if (this.header.remaining() > 0) {
            return false;
        }
        this.header.flip();
        try {
            this.decrypter.init(this.header, this.aad);
            this.headerRead = true;
            return true;
        } catch (GeneralSecurityException e2) {
            setUndefinedState();
            throw new IOException(e2);
        }
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.ciphertextChannel.close();
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.ciphertextChannel.isOpen();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) {
        if (this.definedState) {
            if (!this.headerRead) {
                if (!tryReadHeader()) {
                    return 0;
                }
                this.ciphertextSegment.clear();
                this.ciphertextSegment.limit(this.firstCiphertextSegmentSize + 1);
            }
            if (this.endOfPlaintext) {
                return -1;
            }
            int position = byteBuffer.position();
            while (true) {
                if (byteBuffer.remaining() <= 0) {
                    break;
                }
                if (this.plaintextSegment.remaining() == 0) {
                    if (!this.endOfCiphertext) {
                        if (!tryLoadSegment()) {
                            break;
                        }
                    } else {
                        this.endOfPlaintext = true;
                        break;
                    }
                }
                if (this.plaintextSegment.remaining() <= byteBuffer.remaining()) {
                    this.plaintextSegment.remaining();
                    byteBuffer.put(this.plaintextSegment);
                } else {
                    int remaining = byteBuffer.remaining();
                    ByteBuffer duplicate = this.plaintextSegment.duplicate();
                    duplicate.limit(duplicate.position() + remaining);
                    byteBuffer.put(duplicate);
                    ByteBuffer byteBuffer2 = this.plaintextSegment;
                    byteBuffer2.position(byteBuffer2.position() + remaining);
                }
            }
            int position2 = byteBuffer.position() - position;
            if (position2 == 0 && this.endOfPlaintext) {
                return -1;
            }
            return position2;
        }
        throw new IOException("This StreamingAeadDecryptingChannel is in an undefined state");
    }

    public synchronized String toString() {
        return "StreamingAeadDecryptingChannel\nsegmentNr:" + this.segmentNr + "\nciphertextSegmentSize:" + this.ciphertextSegmentSize + "\nheaderRead:" + this.headerRead + "\nendOfCiphertext:" + this.endOfCiphertext + "\nendOfPlaintext:" + this.endOfPlaintext + "\ndefinedState:" + this.definedState + "\nHeader position:" + this.header.position() + " limit:" + this.header.position() + "\nciphertextSgement position:" + this.ciphertextSegment.position() + " limit:" + this.ciphertextSegment.limit() + "\nplaintextSegment position:" + this.plaintextSegment.position() + " limit:" + this.plaintextSegment.limit();
    }
}
