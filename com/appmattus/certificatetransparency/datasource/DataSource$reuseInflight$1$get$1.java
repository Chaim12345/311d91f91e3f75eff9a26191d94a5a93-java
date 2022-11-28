package com.appmattus.certificatetransparency.datasource;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "com.appmattus.certificatetransparency.datasource.DataSource$reuseInflight$1", f = "DataSource.kt", i = {}, l = {90, 99}, m = "get", n = {}, s = {})
/* loaded from: classes.dex */
public final class DataSource$reuseInflight$1$get$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    /* synthetic */ Object f4589a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DataSource$reuseInflight$1 f4590b;

    /* renamed from: c  reason: collision with root package name */
    int f4591c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataSource$reuseInflight$1$get$1(DataSource$reuseInflight$1 dataSource$reuseInflight$1, Continuation continuation) {
        super(continuation);
        this.f4590b = dataSource$reuseInflight$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f4589a = obj;
        this.f4591c |= Integer.MIN_VALUE;
        return this.f4590b.get(this);
    }
}
