package com.appmattus.certificatetransparency.internal.loglist;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "com.appmattus.certificatetransparency.internal.loglist.LogListNetworkDataSource", f = "LogListNetworkDataSource.kt", i = {0, 1}, l = {35, 41}, m = "get", n = {"this", "logListJson"}, s = {"L$0", "L$0"})
/* loaded from: classes.dex */
public final class LogListNetworkDataSource$get$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f4605a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f4606b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ LogListNetworkDataSource f4607c;

    /* renamed from: d  reason: collision with root package name */
    int f4608d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogListNetworkDataSource$get$1(LogListNetworkDataSource logListNetworkDataSource, Continuation continuation) {
        super(continuation);
        this.f4607c = logListNetworkDataSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f4606b = obj;
        this.f4608d |= Integer.MIN_VALUE;
        return this.f4607c.get(this);
    }
}
