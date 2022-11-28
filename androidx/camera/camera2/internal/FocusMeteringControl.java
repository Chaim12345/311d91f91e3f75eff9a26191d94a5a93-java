package androidx.camera.camera2.internal;

import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.os.Build;
import android.util.Rational;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class FocusMeteringControl {
    private static final String TAG = "FocusMeteringControl";

    /* renamed from: a  reason: collision with root package name */
    final Executor f709a;
    private ScheduledFuture<?> mAutoCancelHandle;
    private final Camera2CameraControlImpl mCameraControl;
    private final ScheduledExecutorService mScheduler;
    private volatile boolean mIsActive = false;
    private boolean mIsInAfAutoMode = false;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    Integer f710b = 0;

    /* renamed from: c  reason: collision with root package name */
    long f711c = 0;

    /* renamed from: d  reason: collision with root package name */
    boolean f712d = false;

    /* renamed from: e  reason: collision with root package name */
    boolean f713e = false;
    private Camera2CameraControlImpl.CaptureResultListener mSessionListenerForFocus = null;
    private Camera2CameraControlImpl.CaptureResultListener mSessionListenerForCancel = null;
    private MeteringRectangle[] mAfRects = new MeteringRectangle[0];
    private MeteringRectangle[] mAeRects = new MeteringRectangle[0];
    private MeteringRectangle[] mAwbRects = new MeteringRectangle[0];

    /* renamed from: f  reason: collision with root package name */
    MeteringRectangle[] f714f = new MeteringRectangle[0];

    /* renamed from: g  reason: collision with root package name */
    MeteringRectangle[] f715g = new MeteringRectangle[0];

    /* renamed from: h  reason: collision with root package name */
    MeteringRectangle[] f716h = new MeteringRectangle[0];

    /* renamed from: i  reason: collision with root package name */
    CallbackToFutureAdapter.Completer f717i = null;

    /* renamed from: j  reason: collision with root package name */
    CallbackToFutureAdapter.Completer f718j = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FocusMeteringControl(@NonNull Camera2CameraControlImpl camera2CameraControlImpl, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull Executor executor) {
        this.mCameraControl = camera2CameraControlImpl;
        this.f709a = executor;
        this.mScheduler = scheduledExecutorService;
    }

    private void completeActionFuture(boolean z) {
        CallbackToFutureAdapter.Completer completer = this.f717i;
        if (completer != null) {
            completer.set(FocusMeteringResult.create(z));
            this.f717i = null;
        }
    }

    private void completeCancelFuture() {
        CallbackToFutureAdapter.Completer completer = this.f718j;
        if (completer != null) {
            completer.set(null);
            this.f718j = null;
        }
    }

    private void disableAutoCancel() {
        ScheduledFuture<?> scheduledFuture = this.mAutoCancelHandle;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.mAutoCancelHandle = null;
        }
    }

    private void executeMeteringAction(@NonNull final MeteringRectangle[] meteringRectangleArr, @NonNull final MeteringRectangle[] meteringRectangleArr2, @NonNull final MeteringRectangle[] meteringRectangleArr3, FocusMeteringAction focusMeteringAction) {
        this.mCameraControl.y(this.mSessionListenerForFocus);
        disableAutoCancel();
        this.mAfRects = meteringRectangleArr;
        this.mAeRects = meteringRectangleArr2;
        this.mAwbRects = meteringRectangleArr3;
        if (shouldTriggerAF()) {
            this.mIsInAfAutoMode = true;
            this.f712d = false;
            this.f713e = false;
            this.mCameraControl.C();
            s(null);
        } else {
            this.mIsInAfAutoMode = false;
            this.f712d = true;
            this.f713e = false;
            this.mCameraControl.C();
        }
        this.f710b = 0;
        final boolean isAfModeSupported = isAfModeSupported();
        Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.q0
            @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
            public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
                boolean lambda$executeMeteringAction$2;
                lambda$executeMeteringAction$2 = FocusMeteringControl.this.lambda$executeMeteringAction$2(isAfModeSupported, meteringRectangleArr, meteringRectangleArr2, meteringRectangleArr3, totalCaptureResult);
                return lambda$executeMeteringAction$2;
            }
        };
        this.mSessionListenerForFocus = captureResultListener;
        this.mCameraControl.l(captureResultListener);
        if (focusMeteringAction.isAutoCancelEnabled()) {
            final long j2 = this.f711c + 1;
            this.f711c = j2;
            this.mAutoCancelHandle = this.mScheduler.schedule(new Runnable() { // from class: androidx.camera.camera2.internal.u0
                @Override // java.lang.Runnable
                public final void run() {
                    FocusMeteringControl.this.lambda$executeMeteringAction$4(j2);
                }
            }, focusMeteringAction.getAutoCancelDurationInMillis(), TimeUnit.MILLISECONDS);
        }
    }

    private void failActionFuture(String str) {
        this.mCameraControl.y(this.mSessionListenerForFocus);
        CallbackToFutureAdapter.Completer completer = this.f717i;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException(str));
            this.f717i = null;
        }
    }

    private void failCancelFuture(String str) {
        this.mCameraControl.y(this.mSessionListenerForCancel);
        CallbackToFutureAdapter.Completer completer = this.f718j;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException(str));
            this.f718j = null;
        }
    }

    private int getDefaultTemplate() {
        return 1;
    }

    private static PointF getFovAdjustedPoint(@NonNull MeteringPoint meteringPoint, @NonNull Rational rational, @NonNull Rational rational2) {
        if (meteringPoint.getSurfaceAspectRatio() != null) {
            rational2 = meteringPoint.getSurfaceAspectRatio();
        }
        PointF pointF = new PointF(meteringPoint.getX(), meteringPoint.getY());
        if (!rational2.equals(rational)) {
            if (rational2.compareTo(rational) > 0) {
                float doubleValue = (float) (rational2.doubleValue() / rational.doubleValue());
                pointF.y = (((float) ((doubleValue - 1.0d) / 2.0d)) + pointF.y) * (1.0f / doubleValue);
            } else {
                float doubleValue2 = (float) (rational.doubleValue() / rational2.doubleValue());
                pointF.x = (((float) ((doubleValue2 - 1.0d) / 2.0d)) + pointF.x) * (1.0f / doubleValue2);
            }
        }
        return pointF;
    }

    private static MeteringRectangle getMeteringRect(MeteringPoint meteringPoint, PointF pointF, Rect rect) {
        int width = (int) (rect.left + (pointF.x * rect.width()));
        int height = (int) (rect.top + (pointF.y * rect.height()));
        int size = ((int) (meteringPoint.getSize() * rect.width())) / 2;
        int size2 = ((int) (meteringPoint.getSize() * rect.height())) / 2;
        Rect rect2 = new Rect(width - size, height - size2, width + size, height + size2);
        rect2.left = rangeLimit(rect2.left, rect.right, rect.left);
        rect2.right = rangeLimit(rect2.right, rect.right, rect.left);
        rect2.top = rangeLimit(rect2.top, rect.bottom, rect.top);
        rect2.bottom = rangeLimit(rect2.bottom, rect.bottom, rect.top);
        return new MeteringRectangle(rect2, 1000);
    }

    private static int getRegionCount(@Nullable MeteringRectangle[] meteringRectangleArr) {
        if (meteringRectangleArr == null) {
            return 0;
        }
        return meteringRectangleArr.length;
    }

    private static boolean hasEqualRegions(@Nullable MeteringRectangle[] meteringRectangleArr, @Nullable MeteringRectangle[] meteringRectangleArr2) {
        if (getRegionCount(meteringRectangleArr) == 0 && getRegionCount(meteringRectangleArr2) == 0) {
            return true;
        }
        if (getRegionCount(meteringRectangleArr) != getRegionCount(meteringRectangleArr2)) {
            return false;
        }
        if (meteringRectangleArr != null && meteringRectangleArr2 != null) {
            for (int i2 = 0; i2 < meteringRectangleArr.length; i2++) {
                if (!meteringRectangleArr[i2].equals(meteringRectangleArr2[i2])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isAfModeSupported() {
        return this.mCameraControl.v(1) == 1;
    }

    private static boolean isValid(@NonNull MeteringPoint meteringPoint) {
        return meteringPoint.getX() >= 0.0f && meteringPoint.getX() <= 1.0f && meteringPoint.getY() >= 0.0f && meteringPoint.getY() <= 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$cancelFocusAndMetering$6(final CallbackToFutureAdapter.Completer completer) {
        this.f709a.execute(new Runnable() { // from class: androidx.camera.camera2.internal.v0
            @Override // java.lang.Runnable
            public final void run() {
                FocusMeteringControl.this.lambda$cancelFocusAndMetering$5(completer);
            }
        });
        return "cancelFocusAndMetering";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$cancelFocusAndMeteringInternal$7(int i2, TotalCaptureResult totalCaptureResult) {
        CaptureRequest request = totalCaptureResult.getRequest();
        MeteringRectangle[] meteringRectangleArr = (MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AF_REGIONS);
        MeteringRectangle[] meteringRectangleArr2 = (MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AE_REGIONS);
        MeteringRectangle[] meteringRectangleArr3 = (MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AWB_REGIONS);
        if (((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_MODE)).intValue() == i2 && hasEqualRegions(meteringRectangleArr, this.f714f) && hasEqualRegions(meteringRectangleArr2, this.f715g) && hasEqualRegions(meteringRectangleArr3, this.f716h)) {
            completeCancelFuture();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$executeMeteringAction$2(boolean z, MeteringRectangle[] meteringRectangleArr, MeteringRectangle[] meteringRectangleArr2, MeteringRectangle[] meteringRectangleArr3, TotalCaptureResult totalCaptureResult) {
        Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
        if (shouldTriggerAF()) {
            if (z && num != null) {
                if (this.f710b.intValue() == 3) {
                    if (num.intValue() != 4) {
                        if (num.intValue() == 5) {
                            this.f713e = false;
                            this.f712d = true;
                        }
                    }
                }
            }
            this.f713e = true;
            this.f712d = true;
        }
        if (this.f712d && totalCaptureResult.getRequest() != null) {
            if (meteringRectangleArr.length == 0) {
                meteringRectangleArr = this.f714f;
            }
            if (meteringRectangleArr2.length == 0) {
                meteringRectangleArr2 = this.f715g;
            }
            if (meteringRectangleArr3.length == 0) {
                meteringRectangleArr3 = this.f716h;
            }
            CaptureRequest request = totalCaptureResult.getRequest();
            if (hasEqualRegions((MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AF_REGIONS), meteringRectangleArr) && hasEqualRegions((MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AE_REGIONS), meteringRectangleArr2) && hasEqualRegions((MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AWB_REGIONS), meteringRectangleArr3)) {
                completeActionFuture(this.f713e);
                return true;
            }
        }
        if (!this.f710b.equals(num) && num != null) {
            this.f710b = num;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeMeteringAction$3(long j2) {
        if (j2 == this.f711c) {
            m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeMeteringAction$4(final long j2) {
        this.f709a.execute(new Runnable() { // from class: androidx.camera.camera2.internal.t0
            @Override // java.lang.Runnable
            public final void run() {
                FocusMeteringControl.this.lambda$executeMeteringAction$3(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$startFocusAndMetering$1(final FocusMeteringAction focusMeteringAction, final Rational rational, final CallbackToFutureAdapter.Completer completer) {
        this.f709a.execute(new Runnable() { // from class: androidx.camera.camera2.internal.w0
            @Override // java.lang.Runnable
            public final void run() {
                FocusMeteringControl.this.lambda$startFocusAndMetering$0(completer, focusMeteringAction, rational);
            }
        });
        return "startFocusAndMetering";
    }

    private static int rangeLimit(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i4), i3);
    }

    private boolean shouldTriggerAF() {
        return this.mAfRects.length > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(@NonNull Camera2ImplConfig.Builder builder) {
        builder.setCaptureRequestOption(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(this.mCameraControl.v(this.mIsInAfAutoMode ? 1 : 4)));
        MeteringRectangle[] meteringRectangleArr = this.mAfRects;
        if (meteringRectangleArr.length != 0) {
            builder.setCaptureRequestOption(CaptureRequest.CONTROL_AF_REGIONS, meteringRectangleArr);
        }
        MeteringRectangle[] meteringRectangleArr2 = this.mAeRects;
        if (meteringRectangleArr2.length != 0) {
            builder.setCaptureRequestOption(CaptureRequest.CONTROL_AE_REGIONS, meteringRectangleArr2);
        }
        MeteringRectangle[] meteringRectangleArr3 = this.mAwbRects;
        if (meteringRectangleArr3.length != 0) {
            builder.setCaptureRequestOption(CaptureRequest.CONTROL_AWB_REGIONS, meteringRectangleArr3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(boolean z, boolean z2) {
        if (this.mIsActive) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setUseRepeatingSurface(true);
            builder.setTemplateType(getDefaultTemplate());
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            if (z) {
                builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            }
            if (Build.VERSION.SDK_INT >= 23 && z2) {
                builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 2);
            }
            builder.addImplementationOptions(builder2.build());
            this.mCameraControl.lambda$submitCaptureRequests$8(Collections.singletonList(builder.build()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenableFuture k() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.r0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$cancelFocusAndMetering$6;
                lambda$cancelFocusAndMetering$6 = FocusMeteringControl.this.lambda$cancelFocusAndMetering$6(completer);
                return lambda$cancelFocusAndMetering$6;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: l */
    public void lambda$cancelFocusAndMetering$5(@Nullable CallbackToFutureAdapter.Completer completer) {
        failCancelFuture("Cancelled by another cancelFocusAndMetering()");
        failActionFuture("Cancelled by cancelFocusAndMetering()");
        this.f718j = completer;
        disableAutoCancel();
        if (this.f718j != null) {
            final int v = this.mCameraControl.v(4);
            Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.p0
                @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
                public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
                    boolean lambda$cancelFocusAndMeteringInternal$7;
                    lambda$cancelFocusAndMeteringInternal$7 = FocusMeteringControl.this.lambda$cancelFocusAndMeteringInternal$7(v, totalCaptureResult);
                    return lambda$cancelFocusAndMeteringInternal$7;
                }
            };
            this.mSessionListenerForCancel = captureResultListener;
            this.mCameraControl.l(captureResultListener);
        }
        if (shouldTriggerAF()) {
            j(true, false);
        }
        this.mAfRects = new MeteringRectangle[0];
        this.mAeRects = new MeteringRectangle[0];
        this.mAwbRects = new MeteringRectangle[0];
        this.mIsInAfAutoMode = false;
        this.mCameraControl.C();
    }

    void m() {
        lambda$cancelFocusAndMetering$5(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(boolean z) {
        if (z == this.mIsActive) {
            return;
        }
        this.mIsActive = z;
        if (this.mIsActive) {
            return;
        }
        m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(@NonNull CaptureRequest.Builder builder) {
        this.f714f = (MeteringRectangle[]) builder.get(CaptureRequest.CONTROL_AF_REGIONS);
        this.f715g = (MeteringRectangle[]) builder.get(CaptureRequest.CONTROL_AE_REGIONS);
        this.f716h = (MeteringRectangle[]) builder.get(CaptureRequest.CONTROL_AWB_REGIONS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenableFuture p(@NonNull final FocusMeteringAction focusMeteringAction, @Nullable final Rational rational) {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.s0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object lambda$startFocusAndMetering$1;
                lambda$startFocusAndMetering$1 = FocusMeteringControl.this.lambda$startFocusAndMetering$1(focusMeteringAction, rational, completer);
                return lambda$startFocusAndMetering$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: q */
    public void lambda$startFocusAndMetering$0(@NonNull CallbackToFutureAdapter.Completer completer, @NonNull FocusMeteringAction focusMeteringAction, @Nullable Rational rational) {
        if (!this.mIsActive) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
        } else if (focusMeteringAction.getMeteringPointsAf().isEmpty() && focusMeteringAction.getMeteringPointsAe().isEmpty() && focusMeteringAction.getMeteringPointsAwb().isEmpty()) {
            completer.setException(new IllegalArgumentException("No AF/AE/AWB MeteringPoints are added."));
        } else {
            int min = Math.min(focusMeteringAction.getMeteringPointsAf().size(), this.mCameraControl.s());
            int min2 = Math.min(focusMeteringAction.getMeteringPointsAe().size(), this.mCameraControl.r());
            int min3 = Math.min(focusMeteringAction.getMeteringPointsAwb().size(), this.mCameraControl.t());
            if (min + min2 + min3 <= 0) {
                completer.setException(new IllegalArgumentException("None of the specified AF/AE/AWB MeteringPoints is supported on this camera."));
                return;
            }
            ArrayList<MeteringPoint> arrayList = new ArrayList();
            ArrayList<MeteringPoint> arrayList2 = new ArrayList();
            ArrayList<MeteringPoint> arrayList3 = new ArrayList();
            if (min > 0) {
                arrayList.addAll(focusMeteringAction.getMeteringPointsAf().subList(0, min));
            }
            if (min2 > 0) {
                arrayList2.addAll(focusMeteringAction.getMeteringPointsAe().subList(0, min2));
            }
            if (min3 > 0) {
                arrayList3.addAll(focusMeteringAction.getMeteringPointsAwb().subList(0, min3));
            }
            Rect p2 = this.mCameraControl.p();
            Rational rational2 = new Rational(p2.width(), p2.height());
            if (rational == null) {
                rational = rational2;
            }
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            for (MeteringPoint meteringPoint : arrayList) {
                if (isValid(meteringPoint)) {
                    MeteringRectangle meteringRect = getMeteringRect(meteringPoint, getFovAdjustedPoint(meteringPoint, rational2, rational), p2);
                    if (meteringRect.getWidth() != 0 && meteringRect.getHeight() != 0) {
                        arrayList4.add(meteringRect);
                    }
                }
            }
            for (MeteringPoint meteringPoint2 : arrayList2) {
                if (isValid(meteringPoint2)) {
                    MeteringRectangle meteringRect2 = getMeteringRect(meteringPoint2, getFovAdjustedPoint(meteringPoint2, rational2, rational), p2);
                    if (meteringRect2.getWidth() != 0 && meteringRect2.getHeight() != 0) {
                        arrayList5.add(meteringRect2);
                    }
                }
            }
            for (MeteringPoint meteringPoint3 : arrayList3) {
                if (isValid(meteringPoint3)) {
                    MeteringRectangle meteringRect3 = getMeteringRect(meteringPoint3, getFovAdjustedPoint(meteringPoint3, rational2, rational), p2);
                    if (meteringRect3.getWidth() != 0 && meteringRect3.getHeight() != 0) {
                        arrayList6.add(meteringRect3);
                    }
                }
            }
            if (arrayList4.isEmpty() && arrayList5.isEmpty() && arrayList6.isEmpty()) {
                completer.setException(new IllegalArgumentException("None of the specified AF/AE/AWB MeteringPoints are valid."));
                return;
            }
            failActionFuture("Cancelled by another startFocusAndMetering()");
            failCancelFuture("Cancelled by another startFocusAndMetering()");
            disableAutoCancel();
            this.f717i = completer;
            executeMeteringAction((MeteringRectangle[]) arrayList4.toArray(new MeteringRectangle[0]), (MeteringRectangle[]) arrayList5.toArray(new MeteringRectangle[0]), (MeteringRectangle[]) arrayList6.toArray(new MeteringRectangle[0]), focusMeteringAction);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(@Nullable final CallbackToFutureAdapter.Completer completer) {
        if (!this.mIsActive) {
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
                return;
            }
            return;
        }
        CaptureConfig.Builder builder = new CaptureConfig.Builder();
        builder.setTemplateType(getDefaultTemplate());
        builder.setUseRepeatingSurface(true);
        Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
        builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
        builder.addImplementationOptions(builder2.build());
        builder.addCameraCaptureCallback(new CameraCaptureCallback(this) { // from class: androidx.camera.camera2.internal.FocusMeteringControl.2
            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCancelled() {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.setException(new CameraControl.OperationCanceledException("Camera is closed"));
                }
            }

            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.set(cameraCaptureResult);
                }
            }

            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureFailed(@NonNull CameraCaptureFailure cameraCaptureFailure) {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.setException(new CameraControlInternal.CameraControlException(cameraCaptureFailure));
                }
            }
        });
        this.mCameraControl.lambda$submitCaptureRequests$8(Collections.singletonList(builder.build()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(@Nullable final CallbackToFutureAdapter.Completer completer) {
        if (!this.mIsActive) {
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
                return;
            }
            return;
        }
        CaptureConfig.Builder builder = new CaptureConfig.Builder();
        builder.setTemplateType(getDefaultTemplate());
        builder.setUseRepeatingSurface(true);
        Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
        builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AF_TRIGGER, 1);
        builder.addImplementationOptions(builder2.build());
        builder.addCameraCaptureCallback(new CameraCaptureCallback(this) { // from class: androidx.camera.camera2.internal.FocusMeteringControl.1
            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCancelled() {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.setException(new CameraControl.OperationCanceledException("Camera is closed"));
                }
            }

            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.set(cameraCaptureResult);
                }
            }

            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureFailed(@NonNull CameraCaptureFailure cameraCaptureFailure) {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.setException(new CameraControlInternal.CameraControlException(cameraCaptureFailure));
                }
            }
        });
        this.mCameraControl.lambda$submitCaptureRequests$8(Collections.singletonList(builder.build()));
    }
}
