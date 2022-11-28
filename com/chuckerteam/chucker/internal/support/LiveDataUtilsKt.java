package com.chuckerteam.chucker.internal.support;

import android.annotation.SuppressLint;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\u001aR\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0005H\u0000\u001a>\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b0\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0000\u001ab\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u00028\u00000\u00032\b\b\u0002\u0010\u000b\u001a\u00020\n28\b\u0002\u0010\u0011\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u0005H\u0000\u001a\b\u0010\u0013\u001a\u00020\nH\u0003\"\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"T1", "T2", "R", "Landroidx/lifecycle/LiveData;", "other", "Lkotlin/Function2;", "func", "combineLatest", "Lkotlin/Pair;", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/util/concurrent/Executor;", "executor", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "old", "new", "", "areEqual", "distinctUntilChanged", "ioExecutor", "", "uninitializedToken", "Ljava/lang/Object;", "com.github.ChuckerTeam.Chucker.library"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class LiveDataUtilsKt {
    private static final Object uninitializedToken = new Object();

    @NotNull
    public static final <T1, T2> LiveData<Pair<T1, T2>> combineLatest(@NotNull LiveData<T1> combineLatest, @NotNull LiveData<T2> other) {
        Intrinsics.checkNotNullParameter(combineLatest, "$this$combineLatest");
        Intrinsics.checkNotNullParameter(other, "other");
        return combineLatest(combineLatest, other, LiveDataUtilsKt$combineLatest$2.INSTANCE);
    }

    @NotNull
    public static final <T1, T2, R> LiveData<R> combineLatest(@NotNull final LiveData<T1> combineLatest, @NotNull final LiveData<T2> other, @NotNull final Function2<? super T1, ? super T2, ? extends R> func) {
        Intrinsics.checkNotNullParameter(combineLatest, "$this$combineLatest");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(func, "func");
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = null;
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = null;
        mediatorLiveData.addSource(combineLatest, new Observer<T1>(objectRef, objectRef2, combineLatest, func, other) { // from class: com.chuckerteam.chucker.internal.support.LiveDataUtilsKt$combineLatest$$inlined$apply$lambda$1

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ Ref.ObjectRef f4897b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ Ref.ObjectRef f4898c;

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ Function2 f4899d;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f4899d = func;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T1 t1) {
                this.f4897b.element = t1;
                T t2 = this.f4898c.element;
                if (t1 == 0 && MediatorLiveData.this.getValue() != null) {
                    MediatorLiveData.this.setValue(null);
                } else if (t1 == 0 || t2 == null) {
                } else {
                    MediatorLiveData.this.setValue(this.f4899d.invoke(t1, t2));
                }
            }
        });
        mediatorLiveData.addSource(other, new Observer<T2>(objectRef2, objectRef, combineLatest, func, other) { // from class: com.chuckerteam.chucker.internal.support.LiveDataUtilsKt$combineLatest$$inlined$apply$lambda$2

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ Ref.ObjectRef f4901b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ Ref.ObjectRef f4902c;

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ Function2 f4903d;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.f4903d = func;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T2 t2) {
                this.f4901b.element = t2;
                T t3 = this.f4902c.element;
                if (t2 == 0 && MediatorLiveData.this.getValue() != null) {
                    MediatorLiveData.this.setValue(null);
                } else if (t3 == null || t2 == 0) {
                } else {
                    MediatorLiveData.this.setValue(this.f4903d.invoke(t3, t2));
                }
            }
        });
        return mediatorLiveData;
    }

    @NotNull
    public static final <T> LiveData<T> distinctUntilChanged(@NotNull LiveData<T> distinctUntilChanged, @NotNull final Executor executor, @NotNull final Function2<? super T, ? super T, Boolean> areEqual) {
        Intrinsics.checkNotNullParameter(distinctUntilChanged, "$this$distinctUntilChanged");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(areEqual, "areEqual");
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) uninitializedToken;
        mediatorLiveData.addSource(distinctUntilChanged, new Observer<T>() { // from class: com.chuckerteam.chucker.internal.support.LiveDataUtilsKt$distinctUntilChanged$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(final T t2) {
                executor.execute(new Runnable() { // from class: com.chuckerteam.chucker.internal.support.LiveDataUtilsKt$distinctUntilChanged$2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Object obj;
                        T t3 = objectRef.element;
                        obj = LiveDataUtilsKt.uninitializedToken;
                        if (t3 != obj) {
                            LiveDataUtilsKt$distinctUntilChanged$2 liveDataUtilsKt$distinctUntilChanged$2 = LiveDataUtilsKt$distinctUntilChanged$2.this;
                            if (((Boolean) areEqual.invoke(objectRef.element, t2)).booleanValue()) {
                                return;
                            }
                        }
                        LiveDataUtilsKt$distinctUntilChanged$2 liveDataUtilsKt$distinctUntilChanged$22 = LiveDataUtilsKt$distinctUntilChanged$2.this;
                        Ref.ObjectRef objectRef2 = objectRef;
                        T t4 = (T) t2;
                        objectRef2.element = t4;
                        mediatorLiveData.postValue(t4);
                    }
                });
            }
        });
        return mediatorLiveData;
    }

    public static /* synthetic */ LiveData distinctUntilChanged$default(LiveData liveData, Executor executor, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            executor = ioExecutor();
        }
        if ((i2 & 2) != 0) {
            function2 = LiveDataUtilsKt$distinctUntilChanged$1.INSTANCE;
        }
        return distinctUntilChanged(liveData, executor, function2);
    }

    @SuppressLint({"RestrictedApi"})
    private static final Executor ioExecutor() {
        Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(iOThreadExecutor, "ArchTaskExecutor.getIOThreadExecutor()");
        return iOThreadExecutor;
    }
}
