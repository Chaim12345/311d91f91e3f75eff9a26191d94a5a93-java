package com.chuckerteam.chucker.internal.support;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\f\u001a\u0004\u0018\u00010\u000b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0080@"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/Sharable;", "Landroid/app/Activity;", "activity", "", "fileName", "intentTitle", "intentSubject", "clipDataLabel", "Lkotlin/coroutines/Continuation;", "Landroid/content/Intent;", "continuation", "", "shareAsFile"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.support.SharableKt", f = "Sharable.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {61, 62}, m = "shareAsFile", n = {"$this$shareAsFile", "activity", "fileName", "intentTitle", "intentSubject", "clipDataLabel", "cache", "file", "$this$shareAsFile", "activity", "fileName", "intentTitle", "intentSubject", "clipDataLabel", "cache", "file", "fileContent"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"})
/* loaded from: classes.dex */
public final class SharableKt$shareAsFile$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    /* synthetic */ Object f4912a;

    /* renamed from: b  reason: collision with root package name */
    int f4913b;

    /* renamed from: c  reason: collision with root package name */
    Object f4914c;

    /* renamed from: d  reason: collision with root package name */
    Object f4915d;

    /* renamed from: e  reason: collision with root package name */
    Object f4916e;

    /* renamed from: f  reason: collision with root package name */
    Object f4917f;

    /* renamed from: g  reason: collision with root package name */
    Object f4918g;

    /* renamed from: h  reason: collision with root package name */
    Object f4919h;

    /* renamed from: i  reason: collision with root package name */
    Object f4920i;

    /* renamed from: j  reason: collision with root package name */
    Object f4921j;

    /* renamed from: k  reason: collision with root package name */
    Object f4922k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SharableKt$shareAsFile$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f4912a = obj;
        this.f4913b |= Integer.MIN_VALUE;
        return SharableKt.shareAsFile(null, null, null, null, null, null, this);
    }
}
