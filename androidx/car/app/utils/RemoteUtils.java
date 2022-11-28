package androidx.car.app.utils;

import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.FailureResponse;
import androidx.car.app.HostException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.ISurfaceCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.SurfaceCallback;
import androidx.car.app.SurfaceContainer;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import androidx.lifecycle.Lifecycle;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class RemoteUtils {

    /* loaded from: classes.dex */
    public interface HostCall {
        @Nullable
        Object dispatch();
    }

    /* loaded from: classes.dex */
    public interface RemoteCall<ReturnT> {
        @Nullable
        ReturnT call();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SurfaceCallbackStub extends ISurfaceCallback.Stub {
        private final Lifecycle mLifecycle;
        private final SurfaceCallback mSurfaceCallback;

        SurfaceCallbackStub(Lifecycle lifecycle, SurfaceCallback surfaceCallback) {
            this.mLifecycle = lifecycle;
            this.mSurfaceCallback = surfaceCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onFling$5(float f2, float f3) {
            this.mSurfaceCallback.onFling(f2, f3);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onScale$6(float f2, float f3, float f4) {
            this.mSurfaceCallback.onScale(f2, f3, f4);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onScroll$4(float f2, float f3) {
            this.mSurfaceCallback.onScroll(f2, f3);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onStableAreaChanged$2(Rect rect) {
            this.mSurfaceCallback.onStableAreaChanged(rect);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onSurfaceAvailable$0(Bundleable bundleable) {
            this.mSurfaceCallback.onSurfaceAvailable((SurfaceContainer) bundleable.get());
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onSurfaceDestroyed$3(Bundleable bundleable) {
            this.mSurfaceCallback.onSurfaceDestroyed((SurfaceContainer) bundleable.get());
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onVisibleAreaChanged$1(Rect rect) {
            this.mSurfaceCallback.onVisibleAreaChanged(rect);
            return null;
        }

        @Override // androidx.car.app.ISurfaceCallback
        @RequiresCarApi(2)
        public void onFling(final float f2, final float f3) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onFling", new HostCall() { // from class: androidx.car.app.utils.f
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onFling$5;
                    lambda$onFling$5 = RemoteUtils.SurfaceCallbackStub.this.lambda$onFling$5(f2, f3);
                    return lambda$onFling$5;
                }
            });
        }

        @Override // androidx.car.app.ISurfaceCallback
        @RequiresCarApi(2)
        public void onScale(final float f2, final float f3, final float f4) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onScale", new HostCall() { // from class: androidx.car.app.utils.h
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onScale$6;
                    lambda$onScale$6 = RemoteUtils.SurfaceCallbackStub.this.lambda$onScale$6(f2, f3, f4);
                    return lambda$onScale$6;
                }
            });
        }

        @Override // androidx.car.app.ISurfaceCallback
        @RequiresCarApi(2)
        public void onScroll(final float f2, final float f3) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onScroll", new HostCall() { // from class: androidx.car.app.utils.g
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onScroll$4;
                    lambda$onScroll$4 = RemoteUtils.SurfaceCallbackStub.this.lambda$onScroll$4(f2, f3);
                    return lambda$onScroll$4;
                }
            });
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onStableAreaChanged(final Rect rect, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, iOnDoneCallback, "onStableAreaChanged", new HostCall() { // from class: androidx.car.app.utils.j
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onStableAreaChanged$2;
                    lambda$onStableAreaChanged$2 = RemoteUtils.SurfaceCallbackStub.this.lambda$onStableAreaChanged$2(rect);
                    return lambda$onStableAreaChanged$2;
                }
            });
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onSurfaceAvailable(final Bundleable bundleable, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, iOnDoneCallback, "onSurfaceAvailable", new HostCall() { // from class: androidx.car.app.utils.l
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onSurfaceAvailable$0;
                    lambda$onSurfaceAvailable$0 = RemoteUtils.SurfaceCallbackStub.this.lambda$onSurfaceAvailable$0(bundleable);
                    return lambda$onSurfaceAvailable$0;
                }
            });
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onSurfaceDestroyed(final Bundleable bundleable, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, iOnDoneCallback, "onSurfaceDestroyed", new HostCall() { // from class: androidx.car.app.utils.k
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onSurfaceDestroyed$3;
                    lambda$onSurfaceDestroyed$3 = RemoteUtils.SurfaceCallbackStub.this.lambda$onSurfaceDestroyed$3(bundleable);
                    return lambda$onSurfaceDestroyed$3;
                }
            });
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onVisibleAreaChanged(final Rect rect, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, iOnDoneCallback, "onVisibleAreaChanged", new HostCall() { // from class: androidx.car.app.utils.i
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onVisibleAreaChanged$1;
                    lambda$onVisibleAreaChanged$1 = RemoteUtils.SurfaceCallbackStub.this.lambda$onVisibleAreaChanged$1(rect);
                    return lambda$onVisibleAreaChanged$1;
                }
            });
        }
    }

    private RemoteUtils() {
    }

    @NonNull
    public static IOnDoneCallback createOnDoneCallbackStub(@NonNull final OnDoneCallback onDoneCallback) {
        return new IOnDoneCallback.Stub() { // from class: androidx.car.app.utils.RemoteUtils.1
            @Override // androidx.car.app.IOnDoneCallback
            public void onFailure(Bundleable bundleable) {
                OnDoneCallback.this.onFailure(bundleable);
            }

            @Override // androidx.car.app.IOnDoneCallback
            public void onSuccess(Bundleable bundleable) {
                OnDoneCallback.this.onSuccess(bundleable);
            }
        };
    }

    public static void dispatchCallFromHost(@NonNull final IOnDoneCallback iOnDoneCallback, @NonNull final String str, @NonNull final HostCall hostCall) {
        ThreadUtils.runOnMain(new Runnable() { // from class: androidx.car.app.utils.c
            @Override // java.lang.Runnable
            public final void run() {
                RemoteUtils.lambda$dispatchCallFromHost$0(IOnDoneCallback.this, str, hostCall);
            }
        });
    }

    public static void dispatchCallFromHost(@Nullable final Lifecycle lifecycle, @NonNull final IOnDoneCallback iOnDoneCallback, @NonNull final String str, @NonNull final HostCall hostCall) {
        ThreadUtils.runOnMain(new Runnable() { // from class: androidx.car.app.utils.d
            @Override // java.lang.Runnable
            public final void run() {
                RemoteUtils.lambda$dispatchCallFromHost$1(Lifecycle.this, iOnDoneCallback, str, hostCall);
            }
        });
    }

    public static void dispatchCallFromHost(@Nullable final Lifecycle lifecycle, @NonNull final String str, @NonNull final HostCall hostCall) {
        ThreadUtils.runOnMain(new Runnable() { // from class: androidx.car.app.utils.e
            @Override // java.lang.Runnable
            public final void run() {
                RemoteUtils.lambda$dispatchCallFromHost$2(Lifecycle.this, hostCall, str);
            }
        });
    }

    public static void dispatchCallToHost(@NonNull String str, @NonNull RemoteCall<?> remoteCall) {
        try {
            dispatchCallToHostForResult(str, remoteCall);
        } catch (RemoteException e2) {
            Log.e(LogTags.TAG_DISPATCH, "Host unresponsive when dispatching call " + str, e2);
        }
    }

    @Nullable
    public static <ReturnT> ReturnT dispatchCallToHostForResult(@NonNull String str, @NonNull RemoteCall<ReturnT> remoteCall) {
        try {
            if (Log.isLoggable(LogTags.TAG, 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Dispatching call ");
                sb.append(str);
                sb.append(" to host");
            }
            return remoteCall.call();
        } catch (SecurityException e2) {
            throw e2;
        } catch (RuntimeException e3) {
            throw new HostException("Remote " + str + " call failed", e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$dispatchCallFromHost$0(IOnDoneCallback iOnDoneCallback, String str, HostCall hostCall) {
        try {
            sendSuccessResponseToHost(iOnDoneCallback, str, hostCall.dispatch());
        } catch (BundlerException e2) {
            sendFailureResponseToHost(iOnDoneCallback, str, e2);
        } catch (RuntimeException e3) {
            sendFailureResponseToHost(iOnDoneCallback, str, e3);
            throw new RuntimeException(e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$dispatchCallFromHost$1(Lifecycle lifecycle, IOnDoneCallback iOnDoneCallback, String str, HostCall hostCall) {
        if (lifecycle != null && lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
            dispatchCallFromHost(iOnDoneCallback, str, hostCall);
            return;
        }
        sendFailureResponseToHost(iOnDoneCallback, str, new IllegalStateException("Lifecycle is not at least created when dispatching " + hostCall));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$dispatchCallFromHost$2(Lifecycle lifecycle, HostCall hostCall, String str) {
        if (lifecycle != null) {
            try {
                if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                    hostCall.dispatch();
                    return;
                }
            } catch (BundlerException e2) {
                Log.e(LogTags.TAG_DISPATCH, "Serialization failure in " + str, e2);
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Lifecycle is not at least created when dispatching ");
        sb.append(hostCall);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$sendFailureResponseToHost$4(IOnDoneCallback iOnDoneCallback, Throwable th, String str) {
        try {
            iOnDoneCallback.onFailure(Bundleable.create(new FailureResponse(th)));
            return null;
        } catch (BundlerException e2) {
            Log.e(LogTags.TAG_DISPATCH, "Serialization failure in " + str, e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$sendSuccessResponseToHost$3(IOnDoneCallback iOnDoneCallback, Object obj, String str) {
        Bundleable create;
        if (obj == null) {
            create = null;
        } else {
            try {
                create = Bundleable.create(obj);
            } catch (BundlerException e2) {
                sendFailureResponseToHost(iOnDoneCallback, str, e2);
            }
        }
        iOnDoneCallback.onSuccess(create);
        return null;
    }

    public static void sendFailureResponseToHost(@NonNull final IOnDoneCallback iOnDoneCallback, @NonNull final String str, @NonNull final Throwable th) {
        dispatchCallToHost(str + " onFailure", new RemoteCall() { // from class: androidx.car.app.utils.b
            @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
            public final Object call() {
                Object lambda$sendFailureResponseToHost$4;
                lambda$sendFailureResponseToHost$4 = RemoteUtils.lambda$sendFailureResponseToHost$4(IOnDoneCallback.this, th, str);
                return lambda$sendFailureResponseToHost$4;
            }
        });
    }

    public static void sendSuccessResponseToHost(@NonNull final IOnDoneCallback iOnDoneCallback, @NonNull final String str, @Nullable final Object obj) {
        dispatchCallToHost(str + " onSuccess", new RemoteCall() { // from class: androidx.car.app.utils.a
            @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
            public final Object call() {
                Object lambda$sendSuccessResponseToHost$3;
                lambda$sendSuccessResponseToHost$3 = RemoteUtils.lambda$sendSuccessResponseToHost$3(IOnDoneCallback.this, obj, str);
                return lambda$sendSuccessResponseToHost$3;
            }
        });
    }

    @Nullable
    public static ISurfaceCallback stubSurfaceCallback(@NonNull Lifecycle lifecycle, @Nullable SurfaceCallback surfaceCallback) {
        if (surfaceCallback == null) {
            return null;
        }
        return new SurfaceCallbackStub(lifecycle, surfaceCallback);
    }
}
