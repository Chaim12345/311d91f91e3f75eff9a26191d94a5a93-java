package com.appmattus.certificatetransparency.internal.loglist;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
final class LogListZipNetworkDataSource$readZip$2$1 extends Lambda implements Function0<ZipEntry> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ZipInputStream f4625a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogListZipNetworkDataSource$readZip$2$1(ZipInputStream zipInputStream) {
        super(0);
        this.f4625a = zipInputStream;
    }

    @Override // kotlin.jvm.functions.Function0
    @Nullable
    public final ZipEntry invoke() {
        return this.f4625a.getNextEntry();
    }
}
