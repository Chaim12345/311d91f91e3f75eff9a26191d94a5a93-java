package com.appmattus.certificatetransparency.internal.loglist;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "com.appmattus.certificatetransparency.internal.loglist.LogListZipNetworkDataSource", f = "LogListZipNetworkDataSource.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {48, 53}, m = "readZip", n = {"this", "logListJson", "signature", "zipInputStream", "this", "logListJson", "signature", "zipInputStream"}, s = {"L$0", "L$1", "L$2", "L$4", "L$0", "L$1", "L$2", "L$4"})
/* loaded from: classes.dex */
public final class LogListZipNetworkDataSource$readZip$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f4615a;

    /* renamed from: b  reason: collision with root package name */
    Object f4616b;

    /* renamed from: c  reason: collision with root package name */
    Object f4617c;

    /* renamed from: d  reason: collision with root package name */
    Object f4618d;

    /* renamed from: e  reason: collision with root package name */
    Object f4619e;

    /* renamed from: f  reason: collision with root package name */
    Object f4620f;

    /* renamed from: g  reason: collision with root package name */
    Object f4621g;

    /* renamed from: h  reason: collision with root package name */
    /* synthetic */ Object f4622h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ LogListZipNetworkDataSource f4623i;

    /* renamed from: j  reason: collision with root package name */
    int f4624j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogListZipNetworkDataSource$readZip$1(LogListZipNetworkDataSource logListZipNetworkDataSource, Continuation continuation) {
        super(continuation);
        this.f4623i = logListZipNetworkDataSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object readZip;
        this.f4622h = obj;
        this.f4624j |= Integer.MIN_VALUE;
        readZip = this.f4623i.readZip(null, this);
        return readZip;
    }
}
