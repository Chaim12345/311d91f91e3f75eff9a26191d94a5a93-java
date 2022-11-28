package okio.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.BufferedSource;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.ZipFileSystem;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a.\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0000\u001a\"\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00050\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u0002\u001a\f\u0010\u000f\u001a\u00020\u0005*\u00020\u000eH\u0000\u001a\f\u0010\u0011\u001a\u00020\u0010*\u00020\u000eH\u0002\u001a\u0014\u0010\u0013\u001a\u00020\u0010*\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0010H\u0002\u001a.\u0010\u001a\u001a\u00020\u0018*\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00142\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016H\u0002\u001a\f\u0010\u001b\u001a\u00020\u0018*\u00020\u000eH\u0000\u001a\u0014\u0010\u001e\u001a\u00020\u001c*\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001cH\u0000\u001a\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001c*\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002\u001a!\u0010\"\u001a\u0004\u0018\u00010\u00172\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\"\u0010#\"\u0016\u0010$\u001a\u00020\u00148\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b$\u0010%\"\u0016\u0010&\u001a\u00020\u00148\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b&\u0010%\"\u0016\u0010'\u001a\u00020\u00148\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b'\u0010%\"\u0016\u0010(\u001a\u00020\u00148\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b(\u0010%\"\u0016\u0010)\u001a\u00020\u00148\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b)\u0010%\"\u0016\u0010*\u001a\u00020\u00148\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b*\u0010%\"\u0016\u0010+\u001a\u00020\u00148\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b+\u0010%\"\u0016\u0010,\u001a\u00020\u00148\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b,\u0010%\"\u0016\u0010-\u001a\u00020\u00148\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b-\u0010%\"\u0016\u0010.\u001a\u00020\u00178\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b.\u0010/\"\u0016\u00100\u001a\u00020\u00148\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b0\u0010%\"\u0016\u00101\u001a\u00020\u00148\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b1\u0010%\"\u001a\u00105\u001a\u000202*\u00020\u00148B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00104¨\u00066"}, d2 = {"Lokio/Path;", "zipPath", "Lokio/FileSystem;", "fileSystem", "Lkotlin/Function1;", "Lokio/internal/ZipEntry;", "", "predicate", "Lokio/ZipFileSystem;", "openZip", "", "entries", "", "buildIndex", "Lokio/BufferedSource;", "readEntry", "Lokio/internal/EocdRecord;", "readEocdRecord", "regularRecord", "readZip64EocdRecord", "", "extraSize", "Lkotlin/Function2;", "", "", "block", "readExtra", "skipLocalHeader", "Lokio/FileMetadata;", "basicMetadata", "readLocalHeader", "readOrSkipLocalHeader", "date", AppConstants.GEO_FENCE_TIME, "dosDateTimeToEpochMillis", "(II)Ljava/lang/Long;", "LOCAL_FILE_HEADER_SIGNATURE", "I", "CENTRAL_FILE_HEADER_SIGNATURE", "END_OF_CENTRAL_DIRECTORY_SIGNATURE", "ZIP64_LOCATOR_SIGNATURE", "ZIP64_EOCD_RECORD_SIGNATURE", "COMPRESSION_METHOD_DEFLATED", "COMPRESSION_METHOD_STORED", "BIT_FLAG_ENCRYPTED", "BIT_FLAG_UNSUPPORTED_MASK", "MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE", "J", "HEADER_ID_ZIP64_EXTENDED_INFO", "HEADER_ID_EXTENDED_TIMESTAMP", "", "getHex", "(I)Ljava/lang/String;", "hex", "okio"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class ZipKt {
    private static final int BIT_FLAG_ENCRYPTED = 1;
    private static final int BIT_FLAG_UNSUPPORTED_MASK = 1;
    private static final int CENTRAL_FILE_HEADER_SIGNATURE = 33639248;
    public static final int COMPRESSION_METHOD_DEFLATED = 8;
    public static final int COMPRESSION_METHOD_STORED = 0;
    private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 101010256;
    private static final int HEADER_ID_EXTENDED_TIMESTAMP = 21589;
    private static final int HEADER_ID_ZIP64_EXTENDED_INFO = 1;
    private static final int LOCAL_FILE_HEADER_SIGNATURE = 67324752;
    private static final long MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE = 4294967295L;
    private static final int ZIP64_EOCD_RECORD_SIGNATURE = 101075792;
    private static final int ZIP64_LOCATOR_SIGNATURE = 117853008;

    private static final Map<Path, ZipEntry> buildIndex(List<ZipEntry> list) {
        List<ZipEntry> sortedWith;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: okio.internal.ZipKt$buildIndex$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t2, T t3) {
                int compareValues;
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(((ZipEntry) t2).getCanonicalPath(), ((ZipEntry) t3).getCanonicalPath());
                return compareValues;
            }
        });
        for (ZipEntry zipEntry : sortedWith) {
            if (((ZipEntry) linkedHashMap.put(zipEntry.getCanonicalPath(), zipEntry)) == null) {
                while (true) {
                    Path parent = zipEntry.getCanonicalPath().parent();
                    if (parent != null) {
                        ZipEntry zipEntry2 = (ZipEntry) linkedHashMap.get(parent);
                        if (zipEntry2 != null) {
                            zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                            break;
                        }
                        ZipEntry zipEntry3 = new ZipEntry(parent, true, null, 0L, 0L, 0L, 0, null, 0L, TypedValues.Position.TYPE_CURVE_FIT, null);
                        linkedHashMap.put(parent, zipEntry3);
                        zipEntry3.getChildren().add(zipEntry.getCanonicalPath());
                        zipEntry = zipEntry3;
                    }
                }
            }
        }
        return linkedHashMap;
    }

    private static final Long dosDateTimeToEpochMillis(int i2, int i3) {
        if (i3 == -1) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(14, 0);
        gregorianCalendar.set(((i2 >> 9) & 127) + 1980, ((i2 >> 5) & 15) - 1, i2 & 31, (i3 >> 11) & 31, (i3 >> 5) & 63, (i3 & 31) << 1);
        return Long.valueOf(gregorianCalendar.getTime().getTime());
    }

    private static final String getHex(int i2) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(16);
        String num = Integer.toString(i2, checkRadix);
        Intrinsics.checkNotNullExpressionValue(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return Intrinsics.stringPlus("0x", num);
    }

    @NotNull
    public static final ZipFileSystem openZip(@NotNull Path zipPath, @NotNull FileSystem fileSystem, @NotNull Function1<? super ZipEntry, Boolean> predicate) {
        int readIntLe;
        Intrinsics.checkNotNullParameter(zipPath, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        FileHandle openReadOnly = fileSystem.openReadOnly(zipPath);
        long j2 = 0;
        try {
            BufferedSource buffer = Okio.buffer(FileHandle.source$default(openReadOnly, 0L, 1, null));
            int readIntLe2 = buffer.readIntLe();
            if (readIntLe2 != LOCAL_FILE_HEADER_SIGNATURE) {
                if (readIntLe2 == END_OF_CENTRAL_DIRECTORY_SIGNATURE) {
                    throw new IOException("unsupported zip: empty");
                }
                throw new IOException("not a zip: expected " + getHex(LOCAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(readIntLe2));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(buffer, null);
            long size = openReadOnly.size() - 22;
            if (size < 0) {
                throw new IOException(Intrinsics.stringPlus("not a zip: size=", Long.valueOf(openReadOnly.size())));
            }
            long max = Math.max(size - PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH, 0L);
            while (true) {
                BufferedSource buffer2 = Okio.buffer(openReadOnly.source(size));
                if (buffer2.readIntLe() == END_OF_CENTRAL_DIRECTORY_SIGNATURE) {
                    EocdRecord readEocdRecord = readEocdRecord(buffer2);
                    String readUtf8 = buffer2.readUtf8(readEocdRecord.getCommentByteCount());
                    buffer2.close();
                    long j3 = size - 20;
                    if (j3 > 0) {
                        BufferedSource buffer3 = Okio.buffer(openReadOnly.source(j3));
                        if (buffer3.readIntLe() == ZIP64_LOCATOR_SIGNATURE) {
                            int readIntLe3 = buffer3.readIntLe();
                            long readLongLe = buffer3.readLongLe();
                            if (buffer3.readIntLe() != 1 || readIntLe3 != 0) {
                                throw new IOException("unsupported zip: spanned");
                            }
                            BufferedSource buffer4 = Okio.buffer(openReadOnly.source(readLongLe));
                            if (buffer4.readIntLe() != ZIP64_EOCD_RECORD_SIGNATURE) {
                                throw new IOException("bad zip: expected " + getHex(ZIP64_EOCD_RECORD_SIGNATURE) + " but was " + getHex(readIntLe));
                            }
                            EocdRecord readZip64EocdRecord = readZip64EocdRecord(buffer4, readEocdRecord);
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(buffer4, null);
                            readEocdRecord = readZip64EocdRecord;
                        }
                        Unit unit3 = Unit.INSTANCE;
                        CloseableKt.closeFinally(buffer3, null);
                    }
                    ArrayList arrayList = new ArrayList();
                    BufferedSource buffer5 = Okio.buffer(openReadOnly.source(readEocdRecord.getCentralDirectoryOffset()));
                    long entryCount = readEocdRecord.getEntryCount();
                    if (0 < entryCount) {
                        do {
                            j2++;
                            ZipEntry readEntry = readEntry(buffer5);
                            if (readEntry.getOffset() >= readEocdRecord.getCentralDirectoryOffset()) {
                                throw new IOException("bad zip: local file header offset >= central directory offset");
                            }
                            if (predicate.invoke(readEntry).booleanValue()) {
                                arrayList.add(readEntry);
                            }
                        } while (j2 < entryCount);
                    }
                    Unit unit4 = Unit.INSTANCE;
                    CloseableKt.closeFinally(buffer5, null);
                    ZipFileSystem zipFileSystem = new ZipFileSystem(zipPath, fileSystem, buildIndex(arrayList), readUtf8);
                    CloseableKt.closeFinally(openReadOnly, null);
                    return zipFileSystem;
                }
                buffer2.close();
                size--;
                if (size < max) {
                    throw new IOException("not a zip: end of central directory signature not found");
                }
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(openReadOnly, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ ZipFileSystem openZip$default(Path path, FileSystem fileSystem, Function1 function1, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            function1 = ZipKt$openZip$1.INSTANCE;
        }
        return openZip(path, fileSystem, function1);
    }

    @NotNull
    public static final ZipEntry readEntry(@NotNull BufferedSource bufferedSource) {
        int readIntLe;
        boolean contains$default;
        Ref.LongRef longRef;
        long j2;
        boolean endsWith$default;
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        if (bufferedSource.readIntLe() != CENTRAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(CENTRAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(readIntLe));
        }
        bufferedSource.skip(4L);
        int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        if ((readShortLe & 1) == 0) {
            int readShortLe2 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            Long dosDateTimeToEpochMillis = dosDateTimeToEpochMillis(bufferedSource.readShortLe() & UShort.MAX_VALUE, bufferedSource.readShortLe() & UShort.MAX_VALUE);
            long readIntLe2 = bufferedSource.readIntLe() & 4294967295L;
            Ref.LongRef longRef2 = new Ref.LongRef();
            longRef2.element = bufferedSource.readIntLe() & 4294967295L;
            Ref.LongRef longRef3 = new Ref.LongRef();
            longRef3.element = bufferedSource.readIntLe() & 4294967295L;
            int readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            int readShortLe4 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            int readShortLe5 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            bufferedSource.skip(8L);
            Ref.LongRef longRef4 = new Ref.LongRef();
            longRef4.element = bufferedSource.readIntLe() & 4294967295L;
            String readUtf8 = bufferedSource.readUtf8(readShortLe3);
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) readUtf8, (char) 0, false, 2, (Object) null);
            if (contains$default) {
                throw new IOException("bad zip: filename contains 0x00");
            }
            if (longRef3.element == 4294967295L) {
                j2 = 8 + 0;
                longRef = longRef4;
            } else {
                longRef = longRef4;
                j2 = 0;
            }
            if (longRef2.element == 4294967295L) {
                j2 += 8;
            }
            Ref.LongRef longRef5 = longRef;
            if (longRef5.element == 4294967295L) {
                j2 += 8;
            }
            long j3 = j2;
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            readExtra(bufferedSource, readShortLe4, new ZipKt$readEntry$1(booleanRef, j3, longRef3, bufferedSource, longRef2, longRef5));
            if (j3 <= 0 || booleanRef.element) {
                String readUtf82 = bufferedSource.readUtf8(readShortLe5);
                Path resolve = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null).resolve(readUtf8);
                endsWith$default = StringsKt__StringsJVMKt.endsWith$default(readUtf8, "/", false, 2, null);
                return new ZipEntry(resolve, endsWith$default, readUtf82, readIntLe2, longRef2.element, longRef3.element, readShortLe2, dosDateTimeToEpochMillis, longRef5.element);
            }
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        throw new IOException(Intrinsics.stringPlus("unsupported zip: general purpose bit flag=", getHex(readShortLe)));
    }

    private static final EocdRecord readEocdRecord(BufferedSource bufferedSource) {
        int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        int readShortLe2 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        long readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        if (readShortLe3 == (bufferedSource.readShortLe() & UShort.MAX_VALUE) && readShortLe == 0 && readShortLe2 == 0) {
            bufferedSource.skip(4L);
            return new EocdRecord(readShortLe3, 4294967295L & bufferedSource.readIntLe(), bufferedSource.readShortLe() & UShort.MAX_VALUE);
        }
        throw new IOException("unsupported zip: spanned");
    }

    private static final void readExtra(BufferedSource bufferedSource, int i2, Function2<? super Integer, ? super Long, Unit> function2) {
        long j2 = i2;
        while (j2 != 0) {
            if (j2 < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            long readShortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            long j3 = j2 - 4;
            if (j3 < readShortLe2) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            bufferedSource.require(readShortLe2);
            long size = bufferedSource.getBuffer().size();
            function2.invoke(Integer.valueOf(readShortLe), Long.valueOf(readShortLe2));
            long size2 = (bufferedSource.getBuffer().size() + readShortLe2) - size;
            int i3 = (size2 > 0L ? 1 : (size2 == 0L ? 0 : -1));
            if (i3 < 0) {
                throw new IOException(Intrinsics.stringPlus("unsupported zip: too many bytes processed for ", Integer.valueOf(readShortLe)));
            }
            if (i3 > 0) {
                bufferedSource.getBuffer().skip(size2);
            }
            j2 = j3 - readShortLe2;
        }
    }

    @NotNull
    public static final FileMetadata readLocalHeader(@NotNull BufferedSource bufferedSource, @NotNull FileMetadata basicMetadata) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        Intrinsics.checkNotNullParameter(basicMetadata, "basicMetadata");
        FileMetadata readOrSkipLocalHeader = readOrSkipLocalHeader(bufferedSource, basicMetadata);
        Intrinsics.checkNotNull(readOrSkipLocalHeader);
        return readOrSkipLocalHeader;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FileMetadata readOrSkipLocalHeader(BufferedSource bufferedSource, FileMetadata fileMetadata) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = fileMetadata == null ? 0 : fileMetadata.getLastModifiedAtMillis();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe != LOCAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(LOCAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(readIntLe));
        }
        bufferedSource.skip(2L);
        int readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        if ((readShortLe & 1) == 0) {
            bufferedSource.skip(18L);
            long readShortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            int readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            bufferedSource.skip(readShortLe2);
            if (fileMetadata == null) {
                bufferedSource.skip(readShortLe3);
                return null;
            }
            readExtra(bufferedSource, readShortLe3, new ZipKt$readOrSkipLocalHeader$1(bufferedSource, objectRef, objectRef2, objectRef3));
            return new FileMetadata(fileMetadata.isRegularFile(), fileMetadata.isDirectory(), null, fileMetadata.getSize(), (Long) objectRef3.element, (Long) objectRef.element, (Long) objectRef2.element, null, 128, null);
        }
        throw new IOException(Intrinsics.stringPlus("unsupported zip: general purpose bit flag=", getHex(readShortLe)));
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource bufferedSource, EocdRecord eocdRecord) {
        bufferedSource.skip(12L);
        int readIntLe = bufferedSource.readIntLe();
        int readIntLe2 = bufferedSource.readIntLe();
        long readLongLe = bufferedSource.readLongLe();
        if (readLongLe == bufferedSource.readLongLe() && readIntLe == 0 && readIntLe2 == 0) {
            bufferedSource.skip(8L);
            return new EocdRecord(readLongLe, bufferedSource.readLongLe(), eocdRecord.getCommentByteCount());
        }
        throw new IOException("unsupported zip: spanned");
    }

    public static final void skipLocalHeader(@NotNull BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        readOrSkipLocalHeader(bufferedSource, null);
    }
}
