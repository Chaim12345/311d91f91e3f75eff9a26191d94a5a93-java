package com.appmattus.certificatetransparency.internal.loglist;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "com.appmattus.certificatetransparency.internal.loglist.LogListZipNetworkDataSource", f = "LogListZipNetworkDataSource.kt", i = {0}, l = {78}, m = "wrap", n = {"tooBig"}, s = {"L$0"})
/* loaded from: classes.dex */
public final class LogListZipNetworkDataSource$wrap$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f4630a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f4631b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ LogListZipNetworkDataSource f4632c;

    /* renamed from: d  reason: collision with root package name */
    int f4633d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogListZipNetworkDataSource$wrap$1(LogListZipNetworkDataSource logListZipNetworkDataSource, Continuation continuation) {
        super(continuation);
        this.f4632c = logListZipNetworkDataSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object wrap;
        this.f4631b = obj;
        this.f4633d |= Integer.MIN_VALUE;
        wrap = this.f4632c.wrap(null, null, this);
        return wrap;
    }
}
