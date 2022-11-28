package okio;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.internal._PathKt;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u000b\u0018\u0000 32\b\u0012\u0004\u0012\u00020\u00000\u0001:\u00013B\u0011\b\u0000\u0012\u0006\u0010\u0019\u001a\u00020\u0007¢\u0006\u0004\b1\u00102J\u0018\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b\u0004\u0010\bJ\u0018\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0004\b\u0004\u0010\tJ\u0018\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\nJ\u0018\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\nJ\u0018\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00002\b\b\u0002\u0010\u000b\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000J\u0006\u0010\u000e\u001a\u00020\u0000J\u0006\u0010\u0010\u001a\u00020\u000fJ\b\u0010\u0012\u001a\u00020\u0011H\u0007J\u0011\u0010\u0014\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010\u0016\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\b\u0010\u0018\u001a\u00020\u0002H\u0016R\u001c\u0010\u0019\u001a\u00020\u00078\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\u001f\u001a\u0004\u0018\u00010\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00020 8F@\u0006¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0019\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070 8F@\u0006¢\u0006\u0006\u001a\u0004\b$\u0010\"R\u0013\u0010&\u001a\u00020\n8F@\u0006¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0013\u0010(\u001a\u00020\n8F@\u0006¢\u0006\u0006\u001a\u0004\b(\u0010'R\u0015\u0010*\u001a\u0004\u0018\u00010)8G@\u0006¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0013\u0010,\u001a\u00020\u00078G@\u0006¢\u0006\u0006\u001a\u0004\b,\u0010\u001cR\u0013\u0010-\u001a\u00020\u00028G@\u0006¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0015\u0010/\u001a\u0004\u0018\u00010\u00008G@\u0006¢\u0006\u0006\u001a\u0004\b/\u0010\u001eR\u0013\u00100\u001a\u00020\n8F@\u0006¢\u0006\u0006\u001a\u0004\b0\u0010'¨\u00064"}, d2 = {"Lokio/Path;", "", "", "child", "resolve", "(Ljava/lang/String;)Lokio/Path;", "div", "Lokio/ByteString;", "(Lokio/ByteString;)Lokio/Path;", "(Lokio/Path;)Lokio/Path;", "", "normalize", "other", "relativeTo", "normalized", "Ljava/io/File;", "toFile", "Ljava/nio/file/Path;", "toNioPath", "", "compareTo", "", "equals", "hashCode", "toString", "bytes", "Lokio/ByteString;", "getBytes$okio", "()Lokio/ByteString;", "getRoot", "()Lokio/Path;", "root", "", "getSegments", "()Ljava/util/List;", "segments", "getSegmentsBytes", "segmentsBytes", "isAbsolute", "()Z", "isRelative", "", "volumeLetter", "()Ljava/lang/Character;", "nameBytes", AppMeasurementSdk.ConditionalUserProperty.NAME, "()Ljava/lang/String;", "parent", "isRoot", "<init>", "(Lokio/ByteString;)V", "Companion", "okio"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class Path implements Comparable<Path> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final String DIRECTORY_SEPARATOR;
    @NotNull
    private final ByteString bytes;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\b\u001a\u00020\u0005*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\u000b\u001a\u00020\u0005*\u00020\t2\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\nJ\u001d\u0010\u000b\u001a\u00020\u0005*\u00020\f2\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\rR\u0016\u0010\u000e\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lokio/Path$Companion;", "", "", "", "normalize", "Lokio/Path;", "get", "(Ljava/lang/String;Z)Lokio/Path;", "toPath", "Ljava/io/File;", "(Ljava/io/File;Z)Lokio/Path;", "toOkioPath", "Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Z)Lokio/Path;", "DIRECTORY_SEPARATOR", "Ljava/lang/String;", "<init>", "()V", "okio"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ Path get$default(Companion companion, File file, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = false;
            }
            return companion.get(file, z);
        }

        public static /* synthetic */ Path get$default(Companion companion, String str, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = false;
            }
            return companion.get(str, z);
        }

        public static /* synthetic */ Path get$default(Companion companion, java.nio.file.Path path, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = false;
            }
            return companion.get(path, z);
        }

        @JvmStatic
        @JvmName(name = "get")
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull File file) {
            Intrinsics.checkNotNullParameter(file, "<this>");
            return get$default(this, file, false, 1, (Object) null);
        }

        @JvmStatic
        @JvmName(name = "get")
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull File file, boolean z) {
            Intrinsics.checkNotNullParameter(file, "<this>");
            String file2 = file.toString();
            Intrinsics.checkNotNullExpressionValue(file2, "toString()");
            return get(file2, z);
        }

        @JvmStatic
        @JvmName(name = "get")
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return get$default(this, str, false, 1, (Object) null);
        }

        @JvmStatic
        @JvmName(name = "get")
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull String str, boolean z) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return _PathKt.commonToPath(str, z);
        }

        @JvmStatic
        @JvmName(name = "get")
        @IgnoreJRERequirement
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull java.nio.file.Path path) {
            Intrinsics.checkNotNullParameter(path, "<this>");
            return get$default(this, path, false, 1, (Object) null);
        }

        @JvmStatic
        @JvmName(name = "get")
        @IgnoreJRERequirement
        @NotNull
        @JvmOverloads
        public final Path get(@NotNull java.nio.file.Path path, boolean z) {
            Intrinsics.checkNotNullParameter(path, "<this>");
            return get(path.toString(), z);
        }
    }

    static {
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        DIRECTORY_SEPARATOR = separator;
    }

    public Path(@NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.bytes = bytes;
    }

    @JvmStatic
    @JvmName(name = "get")
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull File file) {
        return Companion.get(file);
    }

    @JvmStatic
    @JvmName(name = "get")
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull File file, boolean z) {
        return Companion.get(file, z);
    }

    @JvmStatic
    @JvmName(name = "get")
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull String str) {
        return Companion.get(str);
    }

    @JvmStatic
    @JvmName(name = "get")
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull String str, boolean z) {
        return Companion.get(str, z);
    }

    @JvmStatic
    @JvmName(name = "get")
    @IgnoreJRERequirement
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull java.nio.file.Path path) {
        return Companion.get(path);
    }

    @JvmStatic
    @JvmName(name = "get")
    @IgnoreJRERequirement
    @NotNull
    @JvmOverloads
    public static final Path get(@NotNull java.nio.file.Path path, boolean z) {
        return Companion.get(path, z);
    }

    public static /* synthetic */ Path resolve$default(Path path, String str, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return path.resolve(str, z);
    }

    public static /* synthetic */ Path resolve$default(Path path, ByteString byteString, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return path.resolve(byteString, z);
    }

    public static /* synthetic */ Path resolve$default(Path path, Path path2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return path.resolve(path2, z);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull Path other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return getBytes$okio().compareTo(other.getBytes$okio());
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof Path) && Intrinsics.areEqual(((Path) obj).getBytes$okio(), getBytes$okio());
    }

    @NotNull
    public final ByteString getBytes$okio() {
        return this.bytes;
    }

    @Nullable
    public final Path getRoot() {
        int access$rootLength = _PathKt.access$rootLength(this);
        if (access$rootLength == -1) {
            return null;
        }
        return new Path(getBytes$okio().substring(0, access$rootLength));
    }

    @NotNull
    public final List<String> getSegments() {
        int collectionSizeOrDefault;
        ArrayList<ByteString> arrayList = new ArrayList();
        int access$rootLength = _PathKt.access$rootLength(this);
        if (access$rootLength == -1) {
            access$rootLength = 0;
        } else if (access$rootLength < getBytes$okio().size() && getBytes$okio().getByte(access$rootLength) == ((byte) 92)) {
            access$rootLength++;
        }
        int size = getBytes$okio().size();
        if (access$rootLength < size) {
            int i2 = access$rootLength;
            while (true) {
                int i3 = access$rootLength + 1;
                if (getBytes$okio().getByte(access$rootLength) == ((byte) 47) || getBytes$okio().getByte(access$rootLength) == ((byte) 92)) {
                    arrayList.add(getBytes$okio().substring(i2, access$rootLength));
                    i2 = i3;
                }
                if (i3 >= size) {
                    break;
                }
                access$rootLength = i3;
            }
            access$rootLength = i2;
        }
        if (access$rootLength < getBytes$okio().size()) {
            arrayList.add(getBytes$okio().substring(access$rootLength, getBytes$okio().size()));
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (ByteString byteString : arrayList) {
            arrayList2.add(byteString.utf8());
        }
        return arrayList2;
    }

    @NotNull
    public final List<ByteString> getSegmentsBytes() {
        ArrayList arrayList = new ArrayList();
        int access$rootLength = _PathKt.access$rootLength(this);
        if (access$rootLength == -1) {
            access$rootLength = 0;
        } else if (access$rootLength < getBytes$okio().size() && getBytes$okio().getByte(access$rootLength) == ((byte) 92)) {
            access$rootLength++;
        }
        int size = getBytes$okio().size();
        if (access$rootLength < size) {
            int i2 = access$rootLength;
            while (true) {
                int i3 = access$rootLength + 1;
                if (getBytes$okio().getByte(access$rootLength) == ((byte) 47) || getBytes$okio().getByte(access$rootLength) == ((byte) 92)) {
                    arrayList.add(getBytes$okio().substring(i2, access$rootLength));
                    i2 = i3;
                }
                if (i3 >= size) {
                    break;
                }
                access$rootLength = i3;
            }
            access$rootLength = i2;
        }
        if (access$rootLength < getBytes$okio().size()) {
            arrayList.add(getBytes$okio().substring(access$rootLength, getBytes$okio().size()));
        }
        return arrayList;
    }

    public int hashCode() {
        return getBytes$okio().hashCode();
    }

    public final boolean isAbsolute() {
        return _PathKt.access$rootLength(this) != -1;
    }

    public final boolean isRelative() {
        return _PathKt.access$rootLength(this) == -1;
    }

    public final boolean isRoot() {
        return _PathKt.access$rootLength(this) == getBytes$okio().size();
    }

    @JvmName(name = AppMeasurementSdk.ConditionalUserProperty.NAME)
    @NotNull
    public final String name() {
        return nameBytes().utf8();
    }

    @JvmName(name = "nameBytes")
    @NotNull
    public final ByteString nameBytes() {
        int access$getIndexOfLastSlash = _PathKt.access$getIndexOfLastSlash(this);
        return access$getIndexOfLastSlash != -1 ? ByteString.substring$default(getBytes$okio(), access$getIndexOfLastSlash + 1, 0, 2, null) : (volumeLetter() == null || getBytes$okio().size() != 2) ? getBytes$okio() : ByteString.EMPTY;
    }

    @NotNull
    public final Path normalized() {
        return Companion.get(toString(), true);
    }

    @JvmName(name = "parent")
    @Nullable
    public final Path parent() {
        Path path;
        if (Intrinsics.areEqual(getBytes$okio(), _PathKt.access$getDOT$p()) || Intrinsics.areEqual(getBytes$okio(), _PathKt.access$getSLASH$p()) || Intrinsics.areEqual(getBytes$okio(), _PathKt.access$getBACKSLASH$p()) || _PathKt.access$lastSegmentIsDotDot(this)) {
            return null;
        }
        int access$getIndexOfLastSlash = _PathKt.access$getIndexOfLastSlash(this);
        if (access$getIndexOfLastSlash != 2 || volumeLetter() == null) {
            if (access$getIndexOfLastSlash == 1 && getBytes$okio().startsWith(_PathKt.access$getBACKSLASH$p())) {
                return null;
            }
            if (access$getIndexOfLastSlash != -1 || volumeLetter() == null) {
                if (access$getIndexOfLastSlash == -1) {
                    return new Path(_PathKt.access$getDOT$p());
                }
                if (access$getIndexOfLastSlash != 0) {
                    return new Path(ByteString.substring$default(getBytes$okio(), 0, access$getIndexOfLastSlash, 1, null));
                }
                path = new Path(ByteString.substring$default(getBytes$okio(), 0, 1, 1, null));
            } else if (getBytes$okio().size() == 2) {
                return null;
            } else {
                path = new Path(ByteString.substring$default(getBytes$okio(), 0, 2, 1, null));
            }
        } else if (getBytes$okio().size() == 3) {
            return null;
        } else {
            path = new Path(ByteString.substring$default(getBytes$okio(), 0, 3, 1, null));
        }
        return path;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00a6 A[LOOP:2: B:31:0x00a6->B:34:0x00b7, LOOP_START, PHI: r5 
      PHI: (r5v2 int) = (r5v1 int), (r5v5 int) binds: [B:30:0x00a4, B:34:0x00b7] A[DONT_GENERATE, DONT_INLINE]] */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Path relativeTo(@NotNull Path other) {
        int size;
        Intrinsics.checkNotNullParameter(other, "other");
        if (!Intrinsics.areEqual(getRoot(), other.getRoot())) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + other).toString());
        }
        List<ByteString> segmentsBytes = getSegmentsBytes();
        List<ByteString> segmentsBytes2 = other.getSegmentsBytes();
        int min = Math.min(segmentsBytes.size(), segmentsBytes2.size());
        int i2 = 0;
        while (i2 < min && Intrinsics.areEqual(segmentsBytes.get(i2), segmentsBytes2.get(i2))) {
            i2++;
        }
        if (i2 == min && getBytes$okio().size() == other.getBytes$okio().size()) {
            return Companion.get$default(Companion, ".", false, 1, (Object) null);
        }
        if (!(segmentsBytes2.subList(i2, segmentsBytes2.size()).indexOf(_PathKt.access$getDOT_DOT$p()) == -1)) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + other).toString());
        }
        Buffer buffer = new Buffer();
        ByteString access$getSlash = _PathKt.access$getSlash(other);
        if (access$getSlash == null && (access$getSlash = _PathKt.access$getSlash(this)) == null) {
            access$getSlash = _PathKt.access$toSlash(DIRECTORY_SEPARATOR);
        }
        int size2 = segmentsBytes2.size();
        if (i2 >= size2) {
            size = segmentsBytes.size();
            if (i2 < size) {
            }
            return _PathKt.toPath(buffer, false);
        }
        int i3 = i2;
        do {
            i3++;
            buffer.write(_PathKt.access$getDOT_DOT$p());
            buffer.write(access$getSlash);
        } while (i3 < size2);
        size = segmentsBytes.size();
        if (i2 < size) {
            while (true) {
                int i4 = i2 + 1;
                buffer.write(segmentsBytes.get(i2));
                buffer.write(access$getSlash);
                if (i4 >= size) {
                    break;
                }
                i2 = i4;
            }
        }
        return _PathKt.toPath(buffer, false);
    }

    @JvmName(name = "resolve")
    @NotNull
    public final Path resolve(@NotNull String child) {
        Intrinsics.checkNotNullParameter(child, "child");
        return _PathKt.commonResolve(this, _PathKt.toPath(new Buffer().writeUtf8(child), false), false);
    }

    @NotNull
    public final Path resolve(@NotNull String child, boolean z) {
        Intrinsics.checkNotNullParameter(child, "child");
        return _PathKt.commonResolve(this, _PathKt.toPath(new Buffer().writeUtf8(child), false), z);
    }

    @JvmName(name = "resolve")
    @NotNull
    public final Path resolve(@NotNull ByteString child) {
        Intrinsics.checkNotNullParameter(child, "child");
        return _PathKt.commonResolve(this, _PathKt.toPath(new Buffer().write(child), false), false);
    }

    @NotNull
    public final Path resolve(@NotNull ByteString child, boolean z) {
        Intrinsics.checkNotNullParameter(child, "child");
        return _PathKt.commonResolve(this, _PathKt.toPath(new Buffer().write(child), false), z);
    }

    @JvmName(name = "resolve")
    @NotNull
    public final Path resolve(@NotNull Path child) {
        Intrinsics.checkNotNullParameter(child, "child");
        return _PathKt.commonResolve(this, child, false);
    }

    @NotNull
    public final Path resolve(@NotNull Path child, boolean z) {
        Intrinsics.checkNotNullParameter(child, "child");
        return _PathKt.commonResolve(this, child, z);
    }

    @NotNull
    public final File toFile() {
        return new File(toString());
    }

    @IgnoreJRERequirement
    @NotNull
    public final java.nio.file.Path toNioPath() {
        java.nio.file.Path path = Paths.get(toString(), new String[0]);
        Intrinsics.checkNotNullExpressionValue(path, "get(toString())");
        return path;
    }

    @NotNull
    public String toString() {
        return getBytes$okio().utf8();
    }

    @JvmName(name = "volumeLetter")
    @Nullable
    public final Character volumeLetter() {
        boolean z = false;
        if (ByteString.indexOf$default(getBytes$okio(), _PathKt.access$getSLASH$p(), 0, 2, (Object) null) == -1 && getBytes$okio().size() >= 2 && getBytes$okio().getByte(1) == ((byte) 58)) {
            char c2 = (char) getBytes$okio().getByte(0);
            if (!('a' <= c2 && c2 <= 'z')) {
                if ('A' <= c2 && c2 <= 'Z') {
                    z = true;
                }
                if (!z) {
                    return null;
                }
            }
            return Character.valueOf(c2);
        }
        return null;
    }
}
