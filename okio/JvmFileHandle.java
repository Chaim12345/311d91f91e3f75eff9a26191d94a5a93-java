package okio;

import java.io.RandomAccessFile;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\t"}, d2 = {"Lokio/JvmFileHandle;", "Lokio/FileHandle;", "Ljava/io/RandomAccessFile;", "randomAccessFile", "Ljava/io/RandomAccessFile;", "", "readWrite", "<init>", "(ZLjava/io/RandomAccessFile;)V", "okio"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JvmFileHandle extends FileHandle {
    @NotNull
    private final RandomAccessFile randomAccessFile;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmFileHandle(boolean z, @NotNull RandomAccessFile randomAccessFile) {
        super(z);
        Intrinsics.checkNotNullParameter(randomAccessFile, "randomAccessFile");
        this.randomAccessFile = randomAccessFile;
    }

    @Override // okio.FileHandle
    protected synchronized void a() {
        this.randomAccessFile.close();
    }

    @Override // okio.FileHandle
    protected synchronized void b() {
        this.randomAccessFile.getFD().sync();
    }

    @Override // okio.FileHandle
    protected synchronized int c(long j2, @NotNull byte[] array, int i2, int i3) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.randomAccessFile.seek(j2);
        int i4 = 0;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            int read = this.randomAccessFile.read(array, i2, i3 - i4);
            if (read != -1) {
                i4 += read;
            } else if (i4 == 0) {
                return -1;
            }
        }
        return i4;
    }

    @Override // okio.FileHandle
    protected synchronized void d(long j2) {
        long size = size();
        long j3 = j2 - size;
        if (j3 > 0) {
            int i2 = (int) j3;
            f(size, new byte[i2], 0, i2);
        } else {
            this.randomAccessFile.setLength(j2);
        }
    }

    @Override // okio.FileHandle
    protected synchronized long e() {
        return this.randomAccessFile.length();
    }

    @Override // okio.FileHandle
    protected synchronized void f(long j2, @NotNull byte[] array, int i2, int i3) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.randomAccessFile.seek(j2);
        this.randomAccessFile.write(array, i2, i3);
    }
}
