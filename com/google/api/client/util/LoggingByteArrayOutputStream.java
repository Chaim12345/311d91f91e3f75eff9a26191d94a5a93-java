package com.google.api.client.util;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes2.dex */
public class LoggingByteArrayOutputStream extends ByteArrayOutputStream {
    private int bytesWritten;
    private boolean closed;
    private final Logger logger;
    private final Level loggingLevel;
    private final int maximumBytesToLog;

    public LoggingByteArrayOutputStream(Logger logger, Level level, int i2) {
        this.logger = (Logger) Preconditions.checkNotNull(logger);
        this.loggingLevel = (Level) Preconditions.checkNotNull(level);
        Preconditions.checkArgument(i2 >= 0);
        this.maximumBytesToLog = i2;
    }

    private static void appendBytes(StringBuilder sb, int i2) {
        String str;
        if (i2 == 1) {
            str = "1 byte";
        } else {
            sb.append(NumberFormat.getInstance().format(i2));
            str = " bytes";
        }
        sb.append(str);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.closed) {
            if (this.bytesWritten != 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Total: ");
                appendBytes(sb, this.bytesWritten);
                int i2 = ((ByteArrayOutputStream) this).count;
                if (i2 != 0 && i2 < this.bytesWritten) {
                    sb.append(" (logging first ");
                    appendBytes(sb, ((ByteArrayOutputStream) this).count);
                    sb.append(")");
                }
                this.logger.config(sb.toString());
                if (((ByteArrayOutputStream) this).count != 0) {
                    this.logger.log(this.loggingLevel, toString("UTF-8").replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", " "));
                }
            }
            this.closed = true;
        }
    }

    public final synchronized int getBytesWritten() {
        return this.bytesWritten;
    }

    public final int getMaximumBytesToLog() {
        return this.maximumBytesToLog;
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(int i2) {
        Preconditions.checkArgument(!this.closed);
        this.bytesWritten++;
        if (((ByteArrayOutputStream) this).count < this.maximumBytesToLog) {
            super.write(i2);
        }
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i2, int i3) {
        Preconditions.checkArgument(!this.closed);
        this.bytesWritten += i3;
        int i4 = ((ByteArrayOutputStream) this).count;
        int i5 = this.maximumBytesToLog;
        if (i4 < i5) {
            int i6 = i4 + i3;
            if (i6 > i5) {
                i3 += i5 - i6;
            }
            super.write(bArr, i2, i3);
        }
    }
}
