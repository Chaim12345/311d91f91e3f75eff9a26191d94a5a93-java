package com.chuckerteam.chucker.internal.support;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/FileFactory;", "", "Ljava/io/File;", "directory", "create", "", "fileName", "Ljava/util/concurrent/atomic/AtomicLong;", "uniqueIdGenerator", "Ljava/util/concurrent/atomic/AtomicLong;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class FileFactory {
    public static final FileFactory INSTANCE = new FileFactory();
    private static final AtomicLong uniqueIdGenerator = new AtomicLong();

    private FileFactory() {
    }

    @Nullable
    public final File create(@NotNull File directory) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        return create(directory, "chucker-" + uniqueIdGenerator.getAndIncrement());
    }

    @Nullable
    public final File create(@NotNull File directory, @NotNull String fileName) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        try {
            File file = new File(directory, fileName);
            if (file.exists() && !file.delete()) {
                throw new IOException("Failed to delete file " + file);
            }
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            if (file.createNewFile()) {
                return file;
            }
            throw new IOException("File " + file + " already exists");
        } catch (IOException e2) {
            new IOException("An error occurred while creating a Chucker file", e2).printStackTrace();
            return null;
        }
    }
}
