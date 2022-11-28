package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.TruncatedChunkException;
import org.apache.http.config.MessageConstraints;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
/* loaded from: classes3.dex */
public class ChunkedInputStream extends InputStream {
    private static final int BUFFER_SIZE = 2048;
    private static final int CHUNK_CRLF = 3;
    private static final int CHUNK_DATA = 2;
    private static final int CHUNK_INVALID = Integer.MAX_VALUE;
    private static final int CHUNK_LEN = 1;
    private final CharArrayBuffer buffer;
    private long chunkSize;
    private boolean closed;
    private final MessageConstraints constraints;
    private boolean eof;
    private Header[] footers;
    private final SessionInputBuffer in;
    private long pos;
    private int state;

    public ChunkedInputStream(SessionInputBuffer sessionInputBuffer) {
        this(sessionInputBuffer, null);
    }

    public ChunkedInputStream(SessionInputBuffer sessionInputBuffer, MessageConstraints messageConstraints) {
        this.eof = false;
        this.closed = false;
        this.footers = new Header[0];
        this.in = (SessionInputBuffer) Args.notNull(sessionInputBuffer, "Session input buffer");
        this.pos = 0L;
        this.buffer = new CharArrayBuffer(16);
        this.constraints = messageConstraints == null ? MessageConstraints.DEFAULT : messageConstraints;
        this.state = 1;
    }

    private long getChunkSize() {
        int i2 = this.state;
        if (i2 != 1) {
            if (i2 != 3) {
                throw new IllegalStateException("Inconsistent codec state");
            }
            this.buffer.clear();
            if (this.in.readLine(this.buffer) == -1) {
                throw new MalformedChunkCodingException("CRLF expected at end of chunk");
            }
            if (!this.buffer.isEmpty()) {
                throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
            }
            this.state = 1;
        }
        this.buffer.clear();
        if (this.in.readLine(this.buffer) != -1) {
            int indexOf = this.buffer.indexOf(59);
            if (indexOf < 0) {
                indexOf = this.buffer.length();
            }
            String substringTrimmed = this.buffer.substringTrimmed(0, indexOf);
            try {
                return Long.parseLong(substringTrimmed, 16);
            } catch (NumberFormatException unused) {
                throw new MalformedChunkCodingException("Bad chunk header: " + substringTrimmed);
            }
        }
        throw new ConnectionClosedException("Premature end of chunk coded message body: closing chunk expected");
    }

    private void nextChunk() {
        if (this.state == Integer.MAX_VALUE) {
            throw new MalformedChunkCodingException("Corrupt data stream");
        }
        try {
            long chunkSize = getChunkSize();
            this.chunkSize = chunkSize;
            if (chunkSize < 0) {
                throw new MalformedChunkCodingException("Negative chunk size");
            }
            this.state = 2;
            this.pos = 0L;
            if (chunkSize == 0) {
                this.eof = true;
                parseTrailerHeaders();
            }
        } catch (MalformedChunkCodingException e2) {
            this.state = Integer.MAX_VALUE;
            throw e2;
        }
    }

    private void parseTrailerHeaders() {
        try {
            this.footers = AbstractMessageParser.parseHeaders(this.in, this.constraints.getMaxHeaderCount(), this.constraints.getMaxLineLength(), null);
        } catch (HttpException e2) {
            MalformedChunkCodingException malformedChunkCodingException = new MalformedChunkCodingException("Invalid footer: " + e2.getMessage());
            malformedChunkCodingException.initCause(e2);
            throw malformedChunkCodingException;
        }
    }

    @Override // java.io.InputStream
    public int available() {
        SessionInputBuffer sessionInputBuffer = this.in;
        if (sessionInputBuffer instanceof BufferInfo) {
            return (int) Math.min(((BufferInfo) sessionInputBuffer).length(), this.chunkSize - this.pos);
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        try {
            if (this.eof || this.state == Integer.MAX_VALUE) {
            }
            do {
            } while (read(new byte[2048]) >= 0);
        } finally {
            this.eof = true;
            this.closed = true;
        }
    }

    public Header[] getFooters() {
        return (Header[]) this.footers.clone();
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.eof) {
            return -1;
        }
        if (this.state != 2) {
            nextChunk();
            if (this.eof) {
                return -1;
            }
        }
        int read = this.in.read();
        if (read != -1) {
            long j2 = this.pos + 1;
            this.pos = j2;
            if (j2 >= this.chunkSize) {
                this.state = 3;
            }
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.eof) {
            return -1;
        }
        if (this.state != 2) {
            nextChunk();
            if (this.eof) {
                return -1;
            }
        }
        int read = this.in.read(bArr, i2, (int) Math.min(i3, this.chunkSize - this.pos));
        if (read == -1) {
            this.eof = true;
            throw new TruncatedChunkException("Truncated chunk (expected size: %,d; actual size: %,d)", Long.valueOf(this.chunkSize), Long.valueOf(this.pos));
        }
        long j2 = this.pos + read;
        this.pos = j2;
        if (j2 >= this.chunkSize) {
            this.state = 3;
        }
        return read;
    }
}
