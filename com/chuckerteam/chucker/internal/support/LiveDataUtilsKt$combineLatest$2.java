package com.chuckerteam.chucker.internal.support;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T1", "T2", "a", "b", "Lkotlin/Pair;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
final class LiveDataUtilsKt$combineLatest$2 extends Lambda implements Function2<T1, T2, Pair<? extends T1, ? extends T2>> {
    public static final LiveDataUtilsKt$combineLatest$2 INSTANCE = new LiveDataUtilsKt$combineLatest$2();

    LiveDataUtilsKt$combineLatest$2() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((LiveDataUtilsKt$combineLatest$2) obj, obj2);
    }

    @Override // kotlin.jvm.functions.Function2
    @NotNull
    public final Pair<T1, T2> invoke(T1 t1, T2 t2) {
        return TuplesKt.to(t1, t2);
    }
}
