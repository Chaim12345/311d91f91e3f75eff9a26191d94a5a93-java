package com.appmattus.certificatetransparency.datasource;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "com.appmattus.certificatetransparency.datasource.DataSource$compose$1", f = "DataSource.kt", i = {0, 1, 1, 2}, l = {57, 59, 62, 62}, m = "get", n = {"this", "this", "result", "this"}, s = {"L$0", "L$0", "L$1", "L$0"})
/* loaded from: classes.dex */
public final class DataSource$compose$1$get$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f4567a;

    /* renamed from: b  reason: collision with root package name */
    Object f4568b;

    /* renamed from: c  reason: collision with root package name */
    /* synthetic */ Object f4569c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ DataSource$compose$1 f4570d;

    /* renamed from: e  reason: collision with root package name */
    int f4571e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataSource$compose$1$get$1(DataSource$compose$1 dataSource$compose$1, Continuation continuation) {
        super(continuation);
        this.f4570d = dataSource$compose$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f4569c = obj;
        this.f4571e |= Integer.MIN_VALUE;
        return this.f4570d.get(this);
    }
}
