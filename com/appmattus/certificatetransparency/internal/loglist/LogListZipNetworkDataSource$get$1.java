package com.appmattus.certificatetransparency.internal.loglist;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "com.appmattus.certificatetransparency.internal.loglist.LogListZipNetworkDataSource", f = "LogListZipNetworkDataSource.kt", i = {0}, l = {35, 36}, m = "get", n = {"this"}, s = {"L$0"})
/* loaded from: classes.dex */
public final class LogListZipNetworkDataSource$get$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f4609a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f4610b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ LogListZipNetworkDataSource f4611c;

    /* renamed from: d  reason: collision with root package name */
    int f4612d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogListZipNetworkDataSource$get$1(LogListZipNetworkDataSource logListZipNetworkDataSource, Continuation continuation) {
        super(continuation);
        this.f4611c = logListZipNetworkDataSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f4610b = obj;
        this.f4612d |= Integer.MIN_VALUE;
        return this.f4611c.get(this);
    }
}
