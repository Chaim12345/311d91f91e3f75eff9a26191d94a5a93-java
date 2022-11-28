package okio.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.Path;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0002\b\u0014\u001a\u000f\u0010\u0001\u001a\u0004\u0018\u00010\u0000*\u00020\u0000H\u0080\b\u001a\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002*\u00020\u0000H\u0080\b\u001a\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002*\u00020\u0000H\u0080\b\u001a\f\u0010\b\u001a\u00020\u0007*\u00020\u0000H\u0002\u001a\r\u0010\n\u001a\u00020\t*\u00020\u0000H\u0080\b\u001a\r\u0010\u000b\u001a\u00020\t*\u00020\u0000H\u0080\b\u001a\u0016\u0010\r\u001a\u0004\u0018\u00010\f*\u00020\u0000H\u0080\b¢\u0006\u0004\b\r\u0010\u000e\u001a\r\u0010\u000f\u001a\u00020\u0005*\u00020\u0000H\u0080\b\u001a\r\u0010\u0010\u001a\u00020\u0003*\u00020\u0000H\u0080\b\u001a\u000f\u0010\u0011\u001a\u0004\u0018\u00010\u0000*\u00020\u0000H\u0080\b\u001a\f\u0010\u0012\u001a\u00020\t*\u00020\u0000H\u0002\u001a\r\u0010\u0013\u001a\u00020\t*\u00020\u0000H\u0080\b\u001a\u001d\u0010\u0016\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\tH\u0080\b\u001a\u001d\u0010\u0016\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\tH\u0080\b\u001a\u001d\u0010\u0016\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\tH\u0080\b\u001a\u001c\u0010\u0016\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\tH\u0000\u001a\u0015\u0010\u0019\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0000H\u0080\b\u001a\r\u0010\u001a\u001a\u00020\u0000*\u00020\u0000H\u0080\b\u001a\u0015\u0010\u001b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0000H\u0080\b\u001a\u0017\u0010\u001d\u001a\u00020\t*\u00020\u00002\b\u0010\u0018\u001a\u0004\u0018\u00010\u001cH\u0080\b\u001a\r\u0010\u001e\u001a\u00020\u0007*\u00020\u0000H\u0080\b\u001a\r\u0010\u001f\u001a\u00020\u0003*\u00020\u0000H\u0080\b\u001a\u0012\u0010 \u001a\u00020\u0000*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\t\u001a\u0014\u0010!\u001a\u00020\u0000*\u00020\u00172\u0006\u0010\u0015\u001a\u00020\tH\u0000\u001a\f\u0010\"\u001a\u00020\u0005*\u00020\u0003H\u0002\u001a\f\u0010\"\u001a\u00020\u0005*\u00020#H\u0002\u001a\u0014\u0010%\u001a\u00020\t*\u00020\u00172\u0006\u0010$\u001a\u00020\u0005H\u0002\"\u001c\u0010&\u001a\u00020\u00058\u0002@\u0003X\u0083\u0004¢\u0006\f\n\u0004\b&\u0010'\u0012\u0004\b(\u0010)\"\u001c\u0010*\u001a\u00020\u00058\u0002@\u0003X\u0083\u0004¢\u0006\f\n\u0004\b*\u0010'\u0012\u0004\b+\u0010)\"\u001c\u0010,\u001a\u00020\u00058\u0002@\u0003X\u0083\u0004¢\u0006\f\n\u0004\b,\u0010'\u0012\u0004\b-\u0010)\"\u001c\u0010.\u001a\u00020\u00058\u0002@\u0003X\u0083\u0004¢\u0006\f\n\u0004\b.\u0010'\u0012\u0004\b/\u0010)\"\u001c\u00100\u001a\u00020\u00058\u0002@\u0003X\u0083\u0004¢\u0006\f\n\u0004\b0\u0010'\u0012\u0004\b1\u0010)\"\u001a\u00104\u001a\u00020\u0007*\u00020\u00008B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00103\"\u001c\u0010$\u001a\u0004\u0018\u00010\u0005*\u00020\u00008B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b5\u00106¨\u00067"}, d2 = {"Lokio/Path;", "commonRoot", "", "", "commonSegments", "Lokio/ByteString;", "commonSegmentsBytes", "", "rootLength", "", "commonIsAbsolute", "commonIsRelative", "", "commonVolumeLetter", "(Lokio/Path;)Ljava/lang/Character;", "commonNameBytes", "commonName", "commonParent", "lastSegmentIsDotDot", "commonIsRoot", "child", "normalize", "commonResolve", "Lokio/Buffer;", "other", "commonRelativeTo", "commonNormalized", "commonCompareTo", "", "commonEquals", "commonHashCode", "commonToString", "commonToPath", "toPath", "toSlash", "", "slash", "startsWithVolumeLetterAndColon", "SLASH", "Lokio/ByteString;", "getSLASH$annotations", "()V", "BACKSLASH", "getBACKSLASH$annotations", "ANY_SLASH", "getANY_SLASH$annotations", "DOT", "getDOT$annotations", "DOT_DOT", "getDOT_DOT$annotations", "getIndexOfLastSlash", "(Lokio/Path;)I", "indexOfLastSlash", "getSlash", "(Lokio/Path;)Lokio/ByteString;", "okio"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class _PathKt {
    @NotNull
    private static final ByteString ANY_SLASH;
    @NotNull
    private static final ByteString BACKSLASH;
    @NotNull
    private static final ByteString DOT;
    @NotNull
    private static final ByteString DOT_DOT;
    @NotNull
    private static final ByteString SLASH;

    static {
        ByteString.Companion companion = ByteString.Companion;
        SLASH = companion.encodeUtf8("/");
        BACKSLASH = companion.encodeUtf8("\\");
        ANY_SLASH = companion.encodeUtf8("/\\");
        DOT = companion.encodeUtf8(".");
        DOT_DOT = companion.encodeUtf8("..");
    }

    public static final int commonCompareTo(@NotNull Path path, @NotNull Path other) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return path.getBytes$okio().compareTo(other.getBytes$okio());
    }

    public static final boolean commonEquals(@NotNull Path path, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return (obj instanceof Path) && Intrinsics.areEqual(((Path) obj).getBytes$okio(), path.getBytes$okio());
    }

    public static final int commonHashCode(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return path.getBytes$okio().hashCode();
    }

    public static final boolean commonIsAbsolute(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return rootLength(path) != -1;
    }

    public static final boolean commonIsRelative(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return rootLength(path) == -1;
    }

    public static final boolean commonIsRoot(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return rootLength(path) == path.getBytes$okio().size();
    }

    @NotNull
    public static final String commonName(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return path.nameBytes().utf8();
    }

    @NotNull
    public static final ByteString commonNameBytes(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        int indexOfLastSlash = getIndexOfLastSlash(path);
        return indexOfLastSlash != -1 ? ByteString.substring$default(path.getBytes$okio(), indexOfLastSlash + 1, 0, 2, null) : (path.volumeLetter() == null || path.getBytes$okio().size() != 2) ? path.getBytes$okio() : ByteString.EMPTY;
    }

    @NotNull
    public static final Path commonNormalized(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Path.Companion.get(path.toString(), true);
    }

    @Nullable
    public static final Path commonParent(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        if (Intrinsics.areEqual(path.getBytes$okio(), DOT) || Intrinsics.areEqual(path.getBytes$okio(), SLASH) || Intrinsics.areEqual(path.getBytes$okio(), BACKSLASH) || lastSegmentIsDotDot(path)) {
            return null;
        }
        int indexOfLastSlash = getIndexOfLastSlash(path);
        if (indexOfLastSlash == 2 && path.volumeLetter() != null) {
            if (path.getBytes$okio().size() == 3) {
                return null;
            }
            return new Path(ByteString.substring$default(path.getBytes$okio(), 0, 3, 1, null));
        } else if (indexOfLastSlash == 1 && path.getBytes$okio().startsWith(BACKSLASH)) {
            return null;
        } else {
            if (indexOfLastSlash != -1 || path.volumeLetter() == null) {
                return indexOfLastSlash == -1 ? new Path(DOT) : indexOfLastSlash == 0 ? new Path(ByteString.substring$default(path.getBytes$okio(), 0, 1, 1, null)) : new Path(ByteString.substring$default(path.getBytes$okio(), 0, indexOfLastSlash, 1, null));
            } else if (path.getBytes$okio().size() == 2) {
                return null;
            } else {
                return new Path(ByteString.substring$default(path.getBytes$okio(), 0, 2, 1, null));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:78:0x00ab A[LOOP:2: B:78:0x00ab->B:81:0x00bc, LOOP_START, PHI: r5 
      PHI: (r5v2 int) = (r5v1 int), (r5v3 int) binds: [B:77:0x00a9, B:81:0x00bc] A[DONT_GENERATE, DONT_INLINE]] */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Path commonRelativeTo(@NotNull Path path, @NotNull Path other) {
        int size;
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        if (!Intrinsics.areEqual(path.getRoot(), other.getRoot())) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + path + " and " + other).toString());
        }
        List<ByteString> segmentsBytes = path.getSegmentsBytes();
        List<ByteString> segmentsBytes2 = other.getSegmentsBytes();
        int min = Math.min(segmentsBytes.size(), segmentsBytes2.size());
        int i2 = 0;
        while (i2 < min && Intrinsics.areEqual(segmentsBytes.get(i2), segmentsBytes2.get(i2))) {
            i2++;
        }
        if (i2 == min && path.getBytes$okio().size() == other.getBytes$okio().size()) {
            return Path.Companion.get$default(Path.Companion, ".", false, 1, (Object) null);
        }
        if (!(segmentsBytes2.subList(i2, segmentsBytes2.size()).indexOf(DOT_DOT) == -1)) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + path + " and " + other).toString());
        }
        Buffer buffer = new Buffer();
        ByteString slash = getSlash(other);
        if (slash == null && (slash = getSlash(path)) == null) {
            slash = toSlash(Path.DIRECTORY_SEPARATOR);
        }
        int size2 = segmentsBytes2.size();
        if (i2 >= size2) {
            size = segmentsBytes.size();
            if (i2 < size) {
            }
            return toPath(buffer, false);
        }
        int i3 = i2;
        do {
            i3++;
            buffer.write(DOT_DOT);
            buffer.write(slash);
        } while (i3 < size2);
        size = segmentsBytes.size();
        if (i2 < size) {
            while (true) {
                int i4 = i2 + 1;
                buffer.write(segmentsBytes.get(i2));
                buffer.write(slash);
                if (i4 >= size) {
                    break;
                }
                i2 = i4;
            }
        }
        return toPath(buffer, false);
    }

    @NotNull
    public static final Path commonResolve(@NotNull Path path, @NotNull String child, boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        return commonResolve(path, toPath(new Buffer().writeUtf8(child), false), z);
    }

    @NotNull
    public static final Path commonResolve(@NotNull Path path, @NotNull Buffer child, boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        return commonResolve(path, toPath(child, false), z);
    }

    @NotNull
    public static final Path commonResolve(@NotNull Path path, @NotNull ByteString child, boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        return commonResolve(path, toPath(new Buffer().write(child), false), z);
    }

    @NotNull
    public static final Path commonResolve(@NotNull Path path, @NotNull Path child, boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        if (child.isAbsolute() || child.volumeLetter() != null) {
            return child;
        }
        ByteString slash = getSlash(path);
        if (slash == null && (slash = getSlash(child)) == null) {
            slash = toSlash(Path.DIRECTORY_SEPARATOR);
        }
        Buffer buffer = new Buffer();
        buffer.write(path.getBytes$okio());
        if (buffer.size() > 0) {
            buffer.write(slash);
        }
        buffer.write(child.getBytes$okio());
        return toPath(buffer, z);
    }

    @Nullable
    public static final Path commonRoot(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        int rootLength = rootLength(path);
        if (rootLength == -1) {
            return null;
        }
        return new Path(path.getBytes$okio().substring(0, rootLength));
    }

    @NotNull
    public static final List<String> commonSegments(@NotNull Path path) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(path, "<this>");
        ArrayList<ByteString> arrayList = new ArrayList();
        int rootLength = rootLength(path);
        if (rootLength == -1) {
            rootLength = 0;
        } else if (rootLength < path.getBytes$okio().size() && path.getBytes$okio().getByte(rootLength) == ((byte) 92)) {
            rootLength++;
        }
        int size = path.getBytes$okio().size();
        if (rootLength < size) {
            int i2 = rootLength;
            while (true) {
                int i3 = rootLength + 1;
                if (path.getBytes$okio().getByte(rootLength) == ((byte) 47) || path.getBytes$okio().getByte(rootLength) == ((byte) 92)) {
                    arrayList.add(path.getBytes$okio().substring(i2, rootLength));
                    i2 = i3;
                }
                if (i3 >= size) {
                    break;
                }
                rootLength = i3;
            }
            rootLength = i2;
        }
        if (rootLength < path.getBytes$okio().size()) {
            arrayList.add(path.getBytes$okio().substring(rootLength, path.getBytes$okio().size()));
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (ByteString byteString : arrayList) {
            arrayList2.add(byteString.utf8());
        }
        return arrayList2;
    }

    @NotNull
    public static final List<ByteString> commonSegmentsBytes(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        ArrayList arrayList = new ArrayList();
        int rootLength = rootLength(path);
        if (rootLength == -1) {
            rootLength = 0;
        } else if (rootLength < path.getBytes$okio().size() && path.getBytes$okio().getByte(rootLength) == ((byte) 92)) {
            rootLength++;
        }
        int size = path.getBytes$okio().size();
        if (rootLength < size) {
            int i2 = rootLength;
            while (true) {
                int i3 = rootLength + 1;
                if (path.getBytes$okio().getByte(rootLength) == ((byte) 47) || path.getBytes$okio().getByte(rootLength) == ((byte) 92)) {
                    arrayList.add(path.getBytes$okio().substring(i2, rootLength));
                    i2 = i3;
                }
                if (i3 >= size) {
                    break;
                }
                rootLength = i3;
            }
            rootLength = i2;
        }
        if (rootLength < path.getBytes$okio().size()) {
            arrayList.add(path.getBytes$okio().substring(rootLength, path.getBytes$okio().size()));
        }
        return arrayList;
    }

    @NotNull
    public static final Path commonToPath(@NotNull String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toPath(new Buffer().writeUtf8(str), z);
    }

    @NotNull
    public static final String commonToString(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return path.getBytes$okio().utf8();
    }

    @Nullable
    public static final Character commonVolumeLetter(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        boolean z = false;
        if (ByteString.indexOf$default(path.getBytes$okio(), SLASH, 0, 2, (Object) null) == -1 && path.getBytes$okio().size() >= 2 && path.getBytes$okio().getByte(1) == ((byte) 58)) {
            char c2 = (char) path.getBytes$okio().getByte(0);
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

    private static /* synthetic */ void getANY_SLASH$annotations() {
    }

    private static /* synthetic */ void getBACKSLASH$annotations() {
    }

    private static /* synthetic */ void getDOT$annotations() {
    }

    private static /* synthetic */ void getDOT_DOT$annotations() {
    }

    public static final int getIndexOfLastSlash(Path path) {
        int lastIndexOf$default = ByteString.lastIndexOf$default(path.getBytes$okio(), SLASH, 0, 2, (Object) null);
        return lastIndexOf$default != -1 ? lastIndexOf$default : ByteString.lastIndexOf$default(path.getBytes$okio(), BACKSLASH, 0, 2, (Object) null);
    }

    private static /* synthetic */ void getSLASH$annotations() {
    }

    public static final ByteString getSlash(Path path) {
        ByteString bytes$okio = path.getBytes$okio();
        ByteString byteString = SLASH;
        if (ByteString.indexOf$default(bytes$okio, byteString, 0, 2, (Object) null) != -1) {
            return byteString;
        }
        ByteString bytes$okio2 = path.getBytes$okio();
        ByteString byteString2 = BACKSLASH;
        if (ByteString.indexOf$default(bytes$okio2, byteString2, 0, 2, (Object) null) != -1) {
            return byteString2;
        }
        return null;
    }

    public static final boolean lastSegmentIsDotDot(Path path) {
        return path.getBytes$okio().endsWith(DOT_DOT) && (path.getBytes$okio().size() == 2 || path.getBytes$okio().rangeEquals(path.getBytes$okio().size() + (-3), SLASH, 0, 1) || path.getBytes$okio().rangeEquals(path.getBytes$okio().size() + (-3), BACKSLASH, 0, 1));
    }

    public static final int rootLength(Path path) {
        if (path.getBytes$okio().size() == 0) {
            return -1;
        }
        boolean z = false;
        if (path.getBytes$okio().getByte(0) == ((byte) 47)) {
            return 1;
        }
        byte b2 = (byte) 92;
        if (path.getBytes$okio().getByte(0) == b2) {
            if (path.getBytes$okio().size() <= 2 || path.getBytes$okio().getByte(1) != b2) {
                return 1;
            }
            int indexOf = path.getBytes$okio().indexOf(BACKSLASH, 2);
            return indexOf == -1 ? path.getBytes$okio().size() : indexOf;
        } else if (path.getBytes$okio().size() > 2 && path.getBytes$okio().getByte(1) == ((byte) 58) && path.getBytes$okio().getByte(2) == b2) {
            char c2 = (char) path.getBytes$okio().getByte(0);
            if ('a' <= c2 && c2 <= 'z') {
                return 3;
            }
            if ('A' <= c2 && c2 <= 'Z') {
                z = true;
            }
            return !z ? -1 : 3;
        } else {
            return -1;
        }
    }

    private static final boolean startsWithVolumeLetterAndColon(Buffer buffer, ByteString byteString) {
        if (Intrinsics.areEqual(byteString, BACKSLASH) && buffer.size() >= 2 && buffer.getByte(1L) == ((byte) 58)) {
            char c2 = (char) buffer.getByte(0L);
            if (!('a' <= c2 && c2 <= 'z')) {
                if (!('A' <= c2 && c2 <= 'Z')) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:143:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x00ed A[LOOP:2: B:177:0x00ed->B:183:0x0100, LOOP_START, PHI: r2 
      PHI: (r2v2 int) = (r2v0 int), (r2v5 int) binds: [B:176:0x00eb, B:183:0x0100] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:186:0x010a  */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Path toPath(@NotNull Buffer buffer, boolean z) {
        ByteString byteString;
        int size;
        ByteString readByteString;
        Object last;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer buffer2 = new Buffer();
        int i2 = 0;
        ByteString byteString2 = null;
        int i3 = 0;
        while (true) {
            if (!buffer.rangeEquals(0L, SLASH)) {
                byteString = BACKSLASH;
                if (!buffer.rangeEquals(0L, byteString)) {
                    break;
                }
            }
            byte readByte = buffer.readByte();
            if (byteString2 == null) {
                byteString2 = toSlash(readByte);
            }
            i3++;
        }
        boolean z2 = i3 >= 2 && Intrinsics.areEqual(byteString2, byteString);
        if (z2) {
            Intrinsics.checkNotNull(byteString2);
            buffer2.write(byteString2);
        } else if (i3 <= 0) {
            long indexOfElement = buffer.indexOfElement(ANY_SLASH);
            if (byteString2 == null) {
                byteString2 = indexOfElement == -1 ? toSlash(Path.DIRECTORY_SEPARATOR) : toSlash(buffer.getByte(indexOfElement));
            }
            if (startsWithVolumeLetterAndColon(buffer, byteString2)) {
                if (indexOfElement == 2) {
                    buffer2.write(buffer, 3L);
                } else {
                    buffer2.write(buffer, 2L);
                }
            }
            boolean z3 = buffer2.size() <= 0;
            ArrayList arrayList = new ArrayList();
            while (!buffer.exhausted()) {
                long indexOfElement2 = buffer.indexOfElement(ANY_SLASH);
                if (indexOfElement2 == -1) {
                    readByteString = buffer.readByteString();
                } else {
                    readByteString = buffer.readByteString(indexOfElement2);
                    buffer.readByte();
                }
                ByteString byteString3 = DOT_DOT;
                if (Intrinsics.areEqual(readByteString, byteString3)) {
                    if (!z3 || !arrayList.isEmpty()) {
                        if (z) {
                            if (!z3) {
                                if (!arrayList.isEmpty()) {
                                    last = CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList);
                                    if (Intrinsics.areEqual(last, byteString3)) {
                                    }
                                }
                            }
                            if (!z2 || arrayList.size() != 1) {
                                CollectionsKt__MutableCollectionsKt.removeLastOrNull(arrayList);
                            }
                        }
                        arrayList.add(readByteString);
                    }
                } else if (!Intrinsics.areEqual(readByteString, DOT) && !Intrinsics.areEqual(readByteString, ByteString.EMPTY)) {
                    arrayList.add(readByteString);
                }
            }
            size = arrayList.size();
            if (size > 0) {
                while (true) {
                    int i4 = i2 + 1;
                    if (i2 > 0) {
                        buffer2.write(byteString2);
                    }
                    buffer2.write((ByteString) arrayList.get(i2));
                    if (i4 >= size) {
                        break;
                    }
                    i2 = i4;
                }
            }
            if (buffer2.size() == 0) {
                buffer2.write(DOT);
            }
            return new Path(buffer2.readByteString());
        } else {
            Intrinsics.checkNotNull(byteString2);
        }
        buffer2.write(byteString2);
        if (buffer2.size() <= 0) {
        }
        ArrayList arrayList2 = new ArrayList();
        while (!buffer.exhausted()) {
        }
        size = arrayList2.size();
        if (size > 0) {
        }
        if (buffer2.size() == 0) {
        }
        return new Path(buffer2.readByteString());
    }

    private static final ByteString toSlash(byte b2) {
        if (b2 == 47) {
            return SLASH;
        }
        if (b2 == 92) {
            return BACKSLASH;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("not a directory separator: ", Byte.valueOf(b2)));
    }

    public static final ByteString toSlash(String str) {
        if (Intrinsics.areEqual(str, "/")) {
            return SLASH;
        }
        if (Intrinsics.areEqual(str, "\\")) {
            return BACKSLASH;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("not a directory separator: ", str));
    }
}
