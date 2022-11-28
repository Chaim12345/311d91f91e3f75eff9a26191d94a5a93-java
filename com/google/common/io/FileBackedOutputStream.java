package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class FileBackedOutputStream extends OutputStream {
    @NullableDecl
    @GuardedBy("this")
    private File file;
    private final int fileThreshold;
    @GuardedBy("this")
    private MemoryOutput memory;
    @GuardedBy("this")
    private OutputStream out;
    @NullableDecl
    private final File parentDirectory;
    private final boolean resetOnFinalize;
    private final ByteSource source;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MemoryOutput extends ByteArrayOutputStream {
        private MemoryOutput() {
        }

        byte[] a() {
            return ((ByteArrayOutputStream) this).buf;
        }

        int getCount() {
            return ((ByteArrayOutputStream) this).count;
        }
    }

    public FileBackedOutputStream(int i2) {
        this(i2, false);
    }

    public FileBackedOutputStream(int i2, boolean z) {
        this(i2, z, null);
    }

    private FileBackedOutputStream(int i2, boolean z, @NullableDecl File file) {
        this.fileThreshold = i2;
        this.resetOnFinalize = z;
        this.parentDirectory = file;
        MemoryOutput memoryOutput = new MemoryOutput();
        this.memory = memoryOutput;
        this.out = memoryOutput;
        this.source = z ? new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.1
            protected void finalize() {
                try {
                    FileBackedOutputStream.this.reset();
                } catch (Throwable th) {
                    th.printStackTrace(System.err);
                }
            }

            @Override // com.google.common.io.ByteSource
            public InputStream openStream() {
                return FileBackedOutputStream.this.openInputStream();
            }
        } : new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.2
            @Override // com.google.common.io.ByteSource
            public InputStream openStream() {
                return FileBackedOutputStream.this.openInputStream();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized InputStream openInputStream() {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        return new ByteArrayInputStream(this.memory.a(), 0, this.memory.getCount());
    }

    @GuardedBy("this")
    private void update(int i2) {
        if (this.file != null || this.memory.getCount() + i2 <= this.fileThreshold) {
            return;
        }
        File createTempFile = File.createTempFile("FileBackedOutputStream", null, this.parentDirectory);
        if (this.resetOnFinalize) {
            createTempFile.deleteOnExit();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        fileOutputStream.write(this.memory.a(), 0, this.memory.getCount());
        fileOutputStream.flush();
        this.out = fileOutputStream;
        this.file = createTempFile;
        this.memory = null;
    }

    public ByteSource asByteSource() {
        return this.source;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.out.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public synchronized void flush() {
        this.out.flush();
    }

    public synchronized void reset() {
        close();
        MemoryOutput memoryOutput = this.memory;
        if (memoryOutput == null) {
            this.memory = new MemoryOutput();
        } else {
            memoryOutput.reset();
        }
        this.out = this.memory;
        File file = this.file;
        if (file != null) {
            this.file = null;
            if (!file.delete()) {
                throw new IOException("Could not delete: " + file);
            }
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i2) {
        update(1);
        this.out.write(i2);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr, int i2, int i3) {
        update(i3);
        this.out.write(bArr, i2, i3);
    }
}
