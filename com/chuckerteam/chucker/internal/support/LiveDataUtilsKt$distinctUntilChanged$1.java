package com.chuckerteam.chucker.internal.support;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\u0006\u0010\u0002\u001a\u00028\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "old", "new", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class LiveDataUtilsKt$distinctUntilChanged$1 extends Lambda implements Function2<T, T, Boolean> {
    public static final LiveDataUtilsKt$distinctUntilChanged$1 INSTANCE = new LiveDataUtilsKt$distinctUntilChanged$1();

    LiveDataUtilsKt$distinctUntilChanged$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Boolean invoke(Object obj, Object obj2) {
        return Boolean.valueOf(invoke2(obj, obj2));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Boolean, boolean] */
    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Boolean invoke2(T t2, T t3) {
        return Intrinsics.areEqual(t2, t3);
    }
}
