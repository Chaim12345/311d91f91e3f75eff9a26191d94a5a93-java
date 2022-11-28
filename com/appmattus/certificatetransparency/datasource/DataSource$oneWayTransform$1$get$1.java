package com.appmattus.certificatetransparency.datasource;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "com.appmattus.certificatetransparency.datasource.DataSource$oneWayTransform$1", f = "DataSource.kt", i = {0}, l = {117}, m = "get", n = {"this"}, s = {"L$0"})
/* loaded from: classes.dex */
public final class DataSource$oneWayTransform$1$get$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f4584a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f4585b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ DataSource$oneWayTransform$1 f4586c;

    /* renamed from: d  reason: collision with root package name */
    int f4587d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataSource$oneWayTransform$1$get$1(DataSource$oneWayTransform$1 dataSource$oneWayTransform$1, Continuation continuation) {
        super(continuation);
        this.f4586c = dataSource$oneWayTransform$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f4585b = obj;
        this.f4587d |= Integer.MIN_VALUE;
        return this.f4586c.get(this);
    }
}
