package com.chuckerteam.chucker.internal.support;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\n\u001a\u0004\u0018\u00010\t*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0080@"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/Sharable;", "Landroid/app/Activity;", "activity", "", "intentTitle", "intentSubject", "Lkotlin/coroutines/Continuation;", "Landroid/content/Intent;", "continuation", "", "shareAsUtf8Text"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.support.SharableKt", f = "Sharable.kt", i = {0, 0, 0, 0}, l = {31}, m = "shareAsUtf8Text", n = {"$this$shareAsUtf8Text", "activity", "intentTitle", "intentSubject"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes.dex */
public final class SharableKt$shareAsUtf8Text$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    /* synthetic */ Object f4929a;

    /* renamed from: b  reason: collision with root package name */
    int f4930b;

    /* renamed from: c  reason: collision with root package name */
    Object f4931c;

    /* renamed from: d  reason: collision with root package name */
    Object f4932d;

    /* renamed from: e  reason: collision with root package name */
    Object f4933e;

    /* renamed from: f  reason: collision with root package name */
    Object f4934f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SharableKt$shareAsUtf8Text$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f4929a = obj;
        this.f4930b |= Integer.MIN_VALUE;
        return SharableKt.shareAsUtf8Text(null, null, null, null, this);
    }
}
