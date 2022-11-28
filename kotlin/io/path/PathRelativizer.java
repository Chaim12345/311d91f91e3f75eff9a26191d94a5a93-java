package kotlin.io.path;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt___StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class PathRelativizer {
    @NotNull
    public static final PathRelativizer INSTANCE = new PathRelativizer();
    private static final Path emptyPath = Paths.get("", new String[0]);
    private static final Path parentPath = Paths.get("..", new String[0]);

    private PathRelativizer() {
    }

    @NotNull
    public final Path tryRelativeTo(@NotNull Path path, @NotNull Path base) {
        boolean endsWith$default;
        String dropLast;
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(base, "base");
        Path normalize = base.normalize();
        Path r2 = path.normalize();
        Path relativize = normalize.relativize(r2);
        int min = Math.min(normalize.getNameCount(), r2.getNameCount());
        for (int i2 = 0; i2 < min; i2++) {
            Path name = normalize.getName(i2);
            Path path2 = parentPath;
            if (!Intrinsics.areEqual(name, path2)) {
                break;
            } else if (!Intrinsics.areEqual(r2.getName(i2), path2)) {
                throw new IllegalArgumentException("Unable to compute relative path");
            }
        }
        if (Intrinsics.areEqual(r2, normalize) || !Intrinsics.areEqual(normalize, emptyPath)) {
            String obj = relativize.toString();
            String separator = relativize.getFileSystem().getSeparator();
            Intrinsics.checkNotNullExpressionValue(separator, "rn.fileSystem.separator");
            endsWith$default = StringsKt__StringsJVMKt.endsWith$default(obj, separator, false, 2, null);
            if (endsWith$default) {
                FileSystem fileSystem = relativize.getFileSystem();
                dropLast = StringsKt___StringsKt.dropLast(obj, relativize.getFileSystem().getSeparator().length());
                r2 = fileSystem.getPath(dropLast, new String[0]);
            } else {
                r2 = relativize;
            }
        }
        Intrinsics.checkNotNullExpressionValue(r2, "r");
        return r2;
    }
}
