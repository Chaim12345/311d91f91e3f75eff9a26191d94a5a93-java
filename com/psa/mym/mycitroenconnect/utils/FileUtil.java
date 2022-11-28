package com.psa.mym.mycitroenconnect.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FileUtil {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int EOF = -1;
    @NotNull
    public static final FileUtil INSTANCE = new FileUtil();

    private FileUtil() {
    }

    private final long copy(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[4096];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            Unit unit = Unit.INSTANCE;
            if (-1 == read) {
                return j2;
            }
            outputStream.write(bArr, 0, read);
            j2 += read;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0040 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getFileName(Context context, Uri uri) {
        boolean equals$default;
        String str;
        int lastIndexOf$default;
        Integer num = null;
        equals$default = StringsKt__StringsJVMKt.equals$default(uri.getScheme(), FirebaseAnalytics.Param.CONTENT, false, 2, null);
        if (equals$default) {
            Cursor query = context.getContentResolver().query(uri, null, null, null, null);
            if (query != null) {
                try {
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    query.close();
                }
                if (query.moveToFirst()) {
                    str = query.getString(query.getColumnIndex("_display_name"));
                    if (query != null) {
                    }
                    if (str == null) {
                        String path = uri.getPath();
                        if (path != null) {
                            String separator = File.separator;
                            Intrinsics.checkNotNullExpressionValue(separator, "separator");
                            lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) path, separator, 0, false, 6, (Object) null);
                            num = Integer.valueOf(lastIndexOf$default);
                        }
                        Intrinsics.checkNotNull(num);
                        int intValue = num.intValue();
                        if (intValue != -1) {
                            String substring = path.substring(intValue + 1);
                            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                            return substring;
                        }
                        return path;
                    }
                    return str;
                }
            }
            str = null;
            if (query != null) {
            }
            if (str == null) {
            }
        }
        str = null;
        if (str == null) {
        }
    }

    private final File rename(File file, String str) {
        File file2 = new File(file.getParent(), str);
        if (!Intrinsics.areEqual(file2, file)) {
            if (file2.exists() && file2.delete()) {
                Logger logger = Logger.INSTANCE;
                logger.d("Delete old " + str + " file");
            }
            if (file.renameTo(file2)) {
                Logger logger2 = Logger.INSTANCE;
                logger2.d("Rename file to " + str);
            }
        }
        return file2;
    }

    private final String[] splitFileName(String str) {
        int lastIndexOf$default;
        String str2;
        lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) str, ".", 0, false, 6, (Object) null);
        if (lastIndexOf$default != -1) {
            String substring = str.substring(0, lastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            String substring2 = str.substring(lastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
            str2 = substring2;
            str = substring;
        } else {
            str2 = "";
        }
        return new String[]{str, str2};
    }

    @NotNull
    public final File from(@NotNull Context context, @NotNull Uri uri) {
        FileOutputStream fileOutputStream;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        InputStream openInputStream = context.getContentResolver().openInputStream(uri);
        String fileName = getFileName(context, uri);
        String[] splitFileName = splitFileName(fileName);
        File createTempFile = File.createTempFile(splitFileName[0], splitFileName[1]);
        Intrinsics.checkNotNullExpressionValue(createTempFile, "createTempFile(splitName[0], splitName[1])");
        File rename = rename(createTempFile, fileName);
        rename.deleteOnExit();
        try {
            fileOutputStream = new FileOutputStream(rename);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            fileOutputStream = null;
        }
        if (openInputStream != null) {
            Intrinsics.checkNotNull(fileOutputStream);
            copy(openInputStream, fileOutputStream);
            openInputStream.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        return rename;
    }

    @NotNull
    public final String getReadableFileSize(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        if (file.length() <= 0) {
            return "0";
        }
        int log10 = (int) (Math.log10(file.length()) / Math.log10(1024.0d));
        return new DecimalFormat("#,##0.#").format(file.length() / Math.pow(1024.0d, log10)) + TokenParser.SP + new String[]{"B", "KB", "MB", "GB", "TB"}[log10];
    }

    public final double getSize(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        if (file.exists()) {
            return file.length();
        }
        return 0.0d;
    }

    public final double getSizeInGb(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return getSizeInMb(file) / 1024;
    }

    public final double getSizeInKb(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return getSize(file) / 1024;
    }

    public final double getSizeInMb(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return getSizeInKb(file) / 1024;
    }

    public final double getSizeInTb(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return getSizeInGb(file) / 1024;
    }
}
