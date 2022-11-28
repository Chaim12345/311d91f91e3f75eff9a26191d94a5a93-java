package okhttp3.internal.cache2;

import java.nio.channels.FileChannel;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FileOperator {
    @NotNull
    private final FileChannel fileChannel;

    public FileOperator(@NotNull FileChannel fileChannel) {
        Intrinsics.checkNotNullParameter(fileChannel, "fileChannel");
        this.fileChannel = fileChannel;
    }

    public final void read(long j2, @NotNull Buffer sink, long j3) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (j3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j3 > 0) {
            long transferTo = this.fileChannel.transferTo(j2, j3, sink);
            j2 += transferTo;
            j3 -= transferTo;
        }
    }

    public final void write(long j2, @NotNull Buffer source, long j3) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (j3 < 0 || j3 > source.size()) {
            throw new IndexOutOfBoundsException();
        }
        while (j3 > 0) {
            long transferFrom = this.fileChannel.transferFrom(source, j2, j3);
            j2 += transferFrom;
            j3 -= transferFrom;
        }
    }
}
