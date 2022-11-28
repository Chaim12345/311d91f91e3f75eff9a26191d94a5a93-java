package com.appmattus.certificatetransparency.internal.loglist;

import java.util.zip.ZipEntry;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
final class LogListZipNetworkDataSource$readZip$2$2 extends Lambda implements Function1<ZipEntry, Boolean> {
    public static final LogListZipNetworkDataSource$readZip$2$2 INSTANCE = new LogListZipNetworkDataSource$readZip$2$2();

    LogListZipNetworkDataSource$readZip$2$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Boolean invoke(@NotNull ZipEntry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Boolean.valueOf(!it.isDirectory());
    }
}
