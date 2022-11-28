package com.google.firebase.crashlytics.internal.metadata;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class QueueFile implements Closeable {
    private static final int INITIAL_LENGTH = 4096;
    private static final Logger LOGGER = Logger.getLogger(QueueFile.class.getName());

    /* renamed from: a  reason: collision with root package name */
    int f9983a;
    private final byte[] buffer = new byte[16];
    private int elementCount;
    private Element first;
    private Element last;
    private final RandomAccessFile raf;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Element {

        /* renamed from: c  reason: collision with root package name */
        static final Element f9986c = new Element(0, 0);

        /* renamed from: a  reason: collision with root package name */
        final int f9987a;

        /* renamed from: b  reason: collision with root package name */
        final int f9988b;

        Element(int i2, int i3) {
            this.f9987a = i2;
            this.f9988b = i3;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position = " + this.f9987a + ", length = " + this.f9988b + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class ElementInputStream extends InputStream {
        private int position;
        private int remaining;

        private ElementInputStream(Element element) {
            this.position = QueueFile.this.wrapPosition(element.f9987a + 4);
            this.remaining = element.f9988b;
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.remaining == 0) {
                return -1;
            }
            QueueFile.this.raf.seek(this.position);
            int read = QueueFile.this.raf.read();
            this.position = QueueFile.this.wrapPosition(this.position + 1);
            this.remaining--;
            return read;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            QueueFile.nonNull(bArr, "buffer");
            if ((i2 | i3) < 0 || i3 > bArr.length - i2) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i4 = this.remaining;
            if (i4 > 0) {
                if (i3 > i4) {
                    i3 = i4;
                }
                QueueFile.this.ringRead(this.position, bArr, i2, i3);
                this.position = QueueFile.this.wrapPosition(this.position + i3);
                this.remaining -= i3;
                return i3;
            }
            return -1;
        }
    }

    /* loaded from: classes2.dex */
    public interface ElementReader {
        void read(InputStream inputStream, int i2);
    }

    public QueueFile(File file) {
        if (!file.exists()) {
            initialize(file);
        }
        this.raf = open(file);
        readHeader();
    }

    private void expandIfNecessary(int i2) {
        int i3 = i2 + 4;
        int remainingBytes = remainingBytes();
        if (remainingBytes >= i3) {
            return;
        }
        int i4 = this.f9983a;
        do {
            remainingBytes += i4;
            i4 <<= 1;
        } while (remainingBytes < i3);
        setLength(i4);
        Element element = this.last;
        int wrapPosition = wrapPosition(element.f9987a + 4 + element.f9988b);
        if (wrapPosition < this.first.f9987a) {
            FileChannel channel = this.raf.getChannel();
            channel.position(this.f9983a);
            long j2 = wrapPosition - 4;
            if (channel.transferTo(16L, j2, channel) != j2) {
                throw new AssertionError("Copied insufficient number of bytes!");
            }
        }
        int i5 = this.last.f9987a;
        int i6 = this.first.f9987a;
        if (i5 < i6) {
            int i7 = (this.f9983a + i5) - 16;
            writeHeader(i4, this.elementCount, i6, i7);
            this.last = new Element(i7, this.last.f9988b);
        } else {
            writeHeader(i4, this.elementCount, i6, i5);
        }
        this.f9983a = i4;
    }

    private static void initialize(File file) {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile open = open(file2);
        try {
            open.setLength(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
            open.seek(0L);
            byte[] bArr = new byte[16];
            writeInts(bArr, 4096, 0, 0, 0);
            open.write(bArr);
            open.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            open.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T nonNull(T t2, String str) {
        Objects.requireNonNull(t2, str);
        return t2;
    }

    private static RandomAccessFile open(File file) {
        return new RandomAccessFile(file, "rwd");
    }

    private Element readElement(int i2) {
        if (i2 == 0) {
            return Element.f9986c;
        }
        this.raf.seek(i2);
        return new Element(i2, this.raf.readInt());
    }

    private void readHeader() {
        this.raf.seek(0L);
        this.raf.readFully(this.buffer);
        int readInt = readInt(this.buffer, 0);
        this.f9983a = readInt;
        if (readInt <= this.raf.length()) {
            this.elementCount = readInt(this.buffer, 4);
            int readInt2 = readInt(this.buffer, 8);
            int readInt3 = readInt(this.buffer, 12);
            this.first = readElement(readInt2);
            this.last = readElement(readInt3);
            return;
        }
        throw new IOException("File is truncated. Expected length: " + this.f9983a + ", Actual length: " + this.raf.length());
    }

    private static int readInt(byte[] bArr, int i2) {
        return ((bArr[i2] & 255) << 24) + ((bArr[i2 + 1] & 255) << 16) + ((bArr[i2 + 2] & 255) << 8) + (bArr[i2 + 3] & 255);
    }

    private int remainingBytes() {
        return this.f9983a - usedBytes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ringRead(int i2, byte[] bArr, int i3, int i4) {
        RandomAccessFile randomAccessFile;
        int wrapPosition = wrapPosition(i2);
        int i5 = wrapPosition + i4;
        int i6 = this.f9983a;
        if (i5 <= i6) {
            this.raf.seek(wrapPosition);
            randomAccessFile = this.raf;
        } else {
            int i7 = i6 - wrapPosition;
            this.raf.seek(wrapPosition);
            this.raf.readFully(bArr, i3, i7);
            this.raf.seek(16L);
            randomAccessFile = this.raf;
            i3 += i7;
            i4 -= i7;
        }
        randomAccessFile.readFully(bArr, i3, i4);
    }

    private void ringWrite(int i2, byte[] bArr, int i3, int i4) {
        RandomAccessFile randomAccessFile;
        int wrapPosition = wrapPosition(i2);
        int i5 = wrapPosition + i4;
        int i6 = this.f9983a;
        if (i5 <= i6) {
            this.raf.seek(wrapPosition);
            randomAccessFile = this.raf;
        } else {
            int i7 = i6 - wrapPosition;
            this.raf.seek(wrapPosition);
            this.raf.write(bArr, i3, i7);
            this.raf.seek(16L);
            randomAccessFile = this.raf;
            i3 += i7;
            i4 -= i7;
        }
        randomAccessFile.write(bArr, i3, i4);
    }

    private void setLength(int i2) {
        this.raf.setLength(i2);
        this.raf.getChannel().force(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int wrapPosition(int i2) {
        int i3 = this.f9983a;
        return i2 < i3 ? i2 : (i2 + 16) - i3;
    }

    private void writeHeader(int i2, int i3, int i4, int i5) {
        writeInts(this.buffer, i2, i3, i4, i5);
        this.raf.seek(0L);
        this.raf.write(this.buffer);
    }

    private static void writeInt(byte[] bArr, int i2, int i3) {
        bArr[i2] = (byte) (i3 >> 24);
        bArr[i2 + 1] = (byte) (i3 >> 16);
        bArr[i2 + 2] = (byte) (i3 >> 8);
        bArr[i2 + 3] = (byte) i3;
    }

    private static void writeInts(byte[] bArr, int... iArr) {
        int i2 = 0;
        for (int i3 : iArr) {
            writeInt(bArr, i2, i3);
            i2 += 4;
        }
    }

    public void add(byte[] bArr) {
        add(bArr, 0, bArr.length);
    }

    public synchronized void add(byte[] bArr, int i2, int i3) {
        int wrapPosition;
        nonNull(bArr, "buffer");
        if ((i2 | i3) < 0 || i3 > bArr.length - i2) {
            throw new IndexOutOfBoundsException();
        }
        expandIfNecessary(i3);
        boolean isEmpty = isEmpty();
        if (isEmpty) {
            wrapPosition = 16;
        } else {
            Element element = this.last;
            wrapPosition = wrapPosition(element.f9987a + 4 + element.f9988b);
        }
        Element element2 = new Element(wrapPosition, i3);
        writeInt(this.buffer, 0, i3);
        ringWrite(element2.f9987a, this.buffer, 0, 4);
        ringWrite(element2.f9987a + 4, bArr, i2, i3);
        writeHeader(this.f9983a, this.elementCount + 1, isEmpty ? element2.f9987a : this.first.f9987a, element2.f9987a);
        this.last = element2;
        this.elementCount++;
        if (isEmpty) {
            this.first = element2;
        }
    }

    public synchronized void clear() {
        writeHeader(4096, 0, 0, 0);
        this.elementCount = 0;
        Element element = Element.f9986c;
        this.first = element;
        this.last = element;
        if (this.f9983a > 4096) {
            setLength(4096);
        }
        this.f9983a = 4096;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.raf.close();
    }

    public synchronized void forEach(ElementReader elementReader) {
        int i2 = this.first.f9987a;
        for (int i3 = 0; i3 < this.elementCount; i3++) {
            Element readElement = readElement(i2);
            elementReader.read(new ElementInputStream(readElement), readElement.f9988b);
            i2 = wrapPosition(readElement.f9987a + 4 + readElement.f9988b);
        }
    }

    public boolean hasSpaceFor(int i2, int i3) {
        return (usedBytes() + 4) + i2 <= i3;
    }

    public synchronized boolean isEmpty() {
        return this.elementCount == 0;
    }

    public synchronized void peek(ElementReader elementReader) {
        if (this.elementCount > 0) {
            elementReader.read(new ElementInputStream(this.first), this.first.f9988b);
        }
    }

    public synchronized byte[] peek() {
        if (isEmpty()) {
            return null;
        }
        Element element = this.first;
        int i2 = element.f9988b;
        byte[] bArr = new byte[i2];
        ringRead(element.f9987a + 4, bArr, 0, i2);
        return bArr;
    }

    public synchronized void remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.elementCount == 1) {
            clear();
        } else {
            Element element = this.first;
            int wrapPosition = wrapPosition(element.f9987a + 4 + element.f9988b);
            ringRead(wrapPosition, this.buffer, 0, 4);
            int readInt = readInt(this.buffer, 0);
            writeHeader(this.f9983a, this.elementCount - 1, wrapPosition, this.last.f9987a);
            this.elementCount--;
            this.first = new Element(wrapPosition, readInt);
        }
    }

    public synchronized int size() {
        return this.elementCount;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        sb.append("fileLength=");
        sb.append(this.f9983a);
        sb.append(", size=");
        sb.append(this.elementCount);
        sb.append(", first=");
        sb.append(this.first);
        sb.append(", last=");
        sb.append(this.last);
        sb.append(", element lengths=[");
        try {
            forEach(new ElementReader(this) { // from class: com.google.firebase.crashlytics.internal.metadata.QueueFile.1

                /* renamed from: a  reason: collision with root package name */
                boolean f9984a = true;

                @Override // com.google.firebase.crashlytics.internal.metadata.QueueFile.ElementReader
                public void read(InputStream inputStream, int i2) {
                    if (this.f9984a) {
                        this.f9984a = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(i2);
                }
            });
        } catch (IOException e2) {
            LOGGER.log(Level.WARNING, "read error", (Throwable) e2);
        }
        sb.append("]]");
        return sb.toString();
    }

    public int usedBytes() {
        if (this.elementCount == 0) {
            return 16;
        }
        Element element = this.last;
        int i2 = element.f9987a;
        int i3 = this.first.f9987a;
        return i2 >= i3 ? (i2 - i3) + 4 + element.f9988b + 16 : (((i2 + 4) + element.f9988b) + this.f9983a) - i3;
    }
}
