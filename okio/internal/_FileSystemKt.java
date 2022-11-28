package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import okio.BufferedSink;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.Source;
import org.apache.http.cookie.ClientCookie;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0014\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u001c\u0010\n\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a\u001c\u0010\r\u001a\u00020\t*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001H\u0000\u001a\u001c\u0010\u0010\u001a\u00020\t*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0005H\u0000\u001a\"\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0005H\u0000\u001aK\u0010\u0019\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00002\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lokio/FileSystem;", "Lokio/Path;", ClientCookie.PATH_ATTR, "Lokio/FileMetadata;", "commonMetadata", "", "commonExists", "dir", "mustCreate", "", "commonCreateDirectories", "source", TypedValues.Attributes.S_TARGET, "commonCopy", "fileOrDirectory", "mustExist", "commonDeleteRecursively", "followSymlinks", "Lkotlin/sequences/Sequence;", "commonListRecursively", "Lkotlin/sequences/SequenceScope;", "fileSystem", "Lkotlin/collections/ArrayDeque;", "stack", "postorder", "collectRecursively", "(Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "symlinkTarget", "okio"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class _FileSystemKt {
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d6, code lost:
        if (r0 != false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d8, code lost:
        if (r14 != 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00da, code lost:
        r6.addLast(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e1, code lost:
        r13 = r12;
        r12 = r11;
        r11 = r6;
        r6 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r3.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0125, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0126, code lost:
        r11 = r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0148  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object collectRecursively(@NotNull SequenceScope<? super Path> sequenceScope, @NotNull FileSystem fileSystem, @NotNull ArrayDeque<Path> arrayDeque, @NotNull Path path, boolean z, boolean z2, @NotNull Continuation<? super Unit> continuation) {
        _FileSystemKt$collectRecursively$1 _filesystemkt_collectrecursively_1;
        Object coroutine_suspended;
        int i2;
        FileSystem fileSystem2;
        ArrayDeque<Path> arrayDeque2;
        boolean z3;
        SequenceScope<? super Path> sequenceScope2;
        boolean z4;
        FileSystem fileSystem3;
        List<Path> listOrNull;
        Path path2 = path;
        boolean z5 = z2;
        if (continuation instanceof _FileSystemKt$collectRecursively$1) {
            _filesystemkt_collectrecursively_1 = (_FileSystemKt$collectRecursively$1) continuation;
            int i3 = _filesystemkt_collectrecursively_1.f12661i;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                _filesystemkt_collectrecursively_1.f12661i = i3 - Integer.MIN_VALUE;
                Object obj = _filesystemkt_collectrecursively_1.f12660h;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = _filesystemkt_collectrecursively_1.f12661i;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (z5) {
                        fileSystem2 = fileSystem;
                        arrayDeque2 = arrayDeque;
                        z3 = z;
                    } else {
                        _filesystemkt_collectrecursively_1.f12653a = sequenceScope;
                        fileSystem2 = fileSystem;
                        _filesystemkt_collectrecursively_1.f12654b = fileSystem2;
                        arrayDeque2 = arrayDeque;
                        _filesystemkt_collectrecursively_1.f12655c = arrayDeque2;
                        _filesystemkt_collectrecursively_1.f12656d = path2;
                        z3 = z;
                        _filesystemkt_collectrecursively_1.f12658f = z3;
                        _filesystemkt_collectrecursively_1.f12659g = z5;
                        _filesystemkt_collectrecursively_1.f12661i = 1;
                        if (sequenceScope.yield(path2, _filesystemkt_collectrecursively_1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    sequenceScope2 = sequenceScope;
                    z4 = z3;
                    fileSystem3 = fileSystem2;
                } else if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 == 3) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    boolean z6 = _filesystemkt_collectrecursively_1.f12659g;
                    boolean z7 = _filesystemkt_collectrecursively_1.f12658f;
                    Iterator<Path> it = (Iterator) _filesystemkt_collectrecursively_1.f12657e;
                    Path path3 = (Path) _filesystemkt_collectrecursively_1.f12656d;
                    ArrayDeque<Path> arrayDeque3 = (ArrayDeque) _filesystemkt_collectrecursively_1.f12655c;
                    FileSystem fileSystem4 = (FileSystem) _filesystemkt_collectrecursively_1.f12654b;
                    SequenceScope<? super Path> sequenceScope3 = (SequenceScope) _filesystemkt_collectrecursively_1.f12653a;
                    try {
                        ResultKt.throwOnFailure(obj);
                        while (it.hasNext()) {
                            Path next = it.next();
                            boolean z8 = z6;
                            _filesystemkt_collectrecursively_1.f12653a = sequenceScope3;
                            _filesystemkt_collectrecursively_1.f12654b = fileSystem4;
                            _filesystemkt_collectrecursively_1.f12655c = arrayDeque3;
                            _filesystemkt_collectrecursively_1.f12656d = path3;
                            _filesystemkt_collectrecursively_1.f12657e = it;
                            _filesystemkt_collectrecursively_1.f12658f = z7;
                            _filesystemkt_collectrecursively_1.f12659g = z6;
                            _filesystemkt_collectrecursively_1.f12661i = 2;
                            if (collectRecursively(sequenceScope3, fileSystem4, arrayDeque3, next, z7, z8, _filesystemkt_collectrecursively_1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        arrayDeque3.removeLast();
                        z5 = z6;
                        path2 = path3;
                        sequenceScope2 = sequenceScope3;
                        if (z5) {
                            _filesystemkt_collectrecursively_1.f12653a = null;
                            _filesystemkt_collectrecursively_1.f12654b = null;
                            _filesystemkt_collectrecursively_1.f12655c = null;
                            _filesystemkt_collectrecursively_1.f12656d = null;
                            _filesystemkt_collectrecursively_1.f12657e = null;
                            _filesystemkt_collectrecursively_1.f12661i = 3;
                            if (sequenceScope2.yield(path2, _filesystemkt_collectrecursively_1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        }
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        th = th;
                        arrayDeque3.removeLast();
                        throw th;
                    }
                } else {
                    boolean z9 = _filesystemkt_collectrecursively_1.f12659g;
                    boolean z10 = _filesystemkt_collectrecursively_1.f12658f;
                    Path path4 = (Path) _filesystemkt_collectrecursively_1.f12656d;
                    arrayDeque2 = (ArrayDeque) _filesystemkt_collectrecursively_1.f12655c;
                    fileSystem3 = (FileSystem) _filesystemkt_collectrecursively_1.f12654b;
                    sequenceScope2 = (SequenceScope) _filesystemkt_collectrecursively_1.f12653a;
                    ResultKt.throwOnFailure(obj);
                    z5 = z9;
                    z4 = z10;
                    path2 = path4;
                }
                listOrNull = fileSystem3.listOrNull(path2);
                if (listOrNull == null) {
                    listOrNull = CollectionsKt__CollectionsKt.emptyList();
                }
                if (!listOrNull.isEmpty()) {
                    Path path5 = path2;
                    int i4 = 0;
                    while (true) {
                        if (z4 && arrayDeque2.contains(path5)) {
                            throw new IOException(Intrinsics.stringPlus("symlink cycle at ", path2));
                        }
                        Path symlinkTarget = symlinkTarget(fileSystem3, path5);
                        if (symlinkTarget == null) {
                            break;
                        }
                        i4++;
                        path5 = symlinkTarget;
                    }
                }
                if (z5) {
                }
            }
        }
        _filesystemkt_collectrecursively_1 = new _FileSystemKt$collectRecursively$1(continuation);
        Object obj2 = _filesystemkt_collectrecursively_1.f12660h;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = _filesystemkt_collectrecursively_1.f12661i;
        if (i2 != 0) {
        }
        listOrNull = fileSystem3.listOrNull(path2);
        if (listOrNull == null) {
        }
        if (!listOrNull.isEmpty()) {
        }
        if (z5) {
        }
    }

    public static final void commonCopy(@NotNull FileSystem fileSystem, @NotNull Path source, @NotNull Path target) {
        Long l2;
        Long l3;
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        Source source2 = fileSystem.source(source);
        Throwable th = null;
        try {
            BufferedSink buffer = Okio.buffer(fileSystem.sink(target));
            try {
                l3 = Long.valueOf(buffer.writeAll(source2));
                th = null;
            } catch (Throwable th2) {
                th = th2;
                l3 = null;
            }
            if (buffer != null) {
                buffer.close();
            }
        } catch (Throwable th3) {
            th = th3;
            l2 = null;
        }
        if (th != null) {
            throw th;
        }
        Intrinsics.checkNotNull(l3);
        l2 = Long.valueOf(l3.longValue());
        if (source2 != null) {
            try {
                source2.close();
            } catch (Throwable th4) {
                if (th == null) {
                    th = th4;
                } else {
                    ExceptionsKt__ExceptionsKt.addSuppressed(th, th4);
                }
            }
        }
        if (th != null) {
            throw th;
        }
        Intrinsics.checkNotNull(l2);
    }

    public static final void commonCreateDirectories(@NotNull FileSystem fileSystem, @NotNull Path dir, boolean z) {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(dir, "dir");
        ArrayDeque arrayDeque = new ArrayDeque();
        for (Path path = dir; path != null && !fileSystem.exists(path); path = path.parent()) {
            arrayDeque.addFirst(path);
        }
        if (z && arrayDeque.isEmpty()) {
            throw new IOException(dir + " already exist.");
        }
        Iterator<E> it = arrayDeque.iterator();
        while (it.hasNext()) {
            fileSystem.createDirectory((Path) it.next());
        }
    }

    public static final void commonDeleteRecursively(@NotNull FileSystem fileSystem, @NotNull Path fileOrDirectory, boolean z) {
        Sequence sequence;
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(fileOrDirectory, "fileOrDirectory");
        sequence = SequencesKt__SequenceBuilderKt.sequence(new _FileSystemKt$commonDeleteRecursively$sequence$1(fileSystem, fileOrDirectory, null));
        Iterator it = sequence.iterator();
        while (it.hasNext()) {
            fileSystem.delete((Path) it.next(), z && !it.hasNext());
        }
    }

    public static final boolean commonExists(@NotNull FileSystem fileSystem, @NotNull Path path) {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        return fileSystem.metadataOrNull(path) != null;
    }

    @NotNull
    public static final Sequence<Path> commonListRecursively(@NotNull FileSystem fileSystem, @NotNull Path dir, boolean z) {
        Sequence<Path> sequence;
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(dir, "dir");
        sequence = SequencesKt__SequenceBuilderKt.sequence(new _FileSystemKt$commonListRecursively$1(dir, fileSystem, z, null));
        return sequence;
    }

    @NotNull
    public static final FileMetadata commonMetadata(@NotNull FileSystem fileSystem, @NotNull Path path) {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        FileMetadata metadataOrNull = fileSystem.metadataOrNull(path);
        if (metadataOrNull != null) {
            return metadataOrNull;
        }
        throw new FileNotFoundException(Intrinsics.stringPlus("no such file: ", path));
    }

    @Nullable
    public static final Path symlinkTarget(@NotNull FileSystem fileSystem, @NotNull Path path) {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        Path symlinkTarget = fileSystem.metadata(path).getSymlinkTarget();
        if (symlinkTarget == null) {
            return null;
        }
        Path parent = path.parent();
        Intrinsics.checkNotNull(parent);
        return parent.resolve(symlinkTarget);
    }
}
