package com.psa.mym.mycitroenconnect.controller.activities;

import android.os.Bundle;
import android.util.Size;
import android.view.OrientationEventListener;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.google.common.util.concurrent.ListenableFuture;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.controller.activities.BarCodeScannerActivity;
import com.psa.mym.mycitroenconnect.views.barcode_scanning.MLKitBarcodeAnalyzer;
import com.psa.mym.mycitroenconnect.views.barcode_scanning.ViewFinderOverlay;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BarCodeScannerActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ExecutorService cameraExecutor;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private boolean flashEnabled;

    private final void bindPreview(ProcessCameraProvider processCameraProvider) {
        CameraInfo cameraInfo;
        if (isDestroyed() || isFinishing()) {
            return;
        }
        if (processCameraProvider != null) {
            processCameraProvider.unbindAll();
        }
        Preview build = new Preview.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .build()");
        boolean z = true;
        CameraSelector build2 = new CameraSelector.Builder().requireLensFacing(1).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder()\n            .r…ACK)\n            .build()");
        ImageAnalysis.Builder builder = new ImageAnalysis.Builder();
        int i2 = R.id.cameraPreview;
        final ImageAnalysis build3 = builder.setTargetResolution(new Size(((PreviewView) _$_findCachedViewById(i2)).getWidth(), ((PreviewView) _$_findCachedViewById(i2)).getHeight())).setBackpressureStrategy(0).build();
        Intrinsics.checkNotNullExpressionValue(build3, "Builder()\n            .s…EST)\n            .build()");
        new OrientationEventListener(this) { // from class: com.psa.mym.mycitroenconnect.controller.activities.BarCodeScannerActivity$bindPreview$orientationEventListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this);
            }

            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i3) {
                int i4 = 1;
                if (45 <= i3 && i3 < 135) {
                    i4 = 3;
                } else {
                    if (135 <= i3 && i3 < 225) {
                        i4 = 2;
                    } else {
                        if (!(225 <= i3 && i3 < 315)) {
                            i4 = 0;
                        }
                    }
                }
                build3.setTargetRotation(i4);
            }
        }.enable();
        MLKitBarcodeAnalyzer mLKitBarcodeAnalyzer = new MLKitBarcodeAnalyzer(new BarCodeScannerActivity$bindPreview$ScanningListener(this, build3, processCameraProvider));
        ExecutorService executorService = this.cameraExecutor;
        if (executorService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraExecutor");
            executorService = null;
        }
        build3.setAnalyzer(executorService, mLKitBarcodeAnalyzer);
        build.setSurfaceProvider(((PreviewView) _$_findCachedViewById(i2)).getSurfaceProvider());
        final Camera bindToLifecycle = processCameraProvider != null ? processCameraProvider.bindToLifecycle(this, build2, build3, build) : null;
        if (bindToLifecycle == null || (cameraInfo = bindToLifecycle.getCameraInfo()) == null || !cameraInfo.hasFlashUnit()) {
            z = false;
        }
        if (z) {
            int i3 = R.id.ivFlashControl;
            ((AppCompatImageView) _$_findCachedViewById(i3)).setVisibility(0);
            ((AppCompatImageView) _$_findCachedViewById(i3)).setOnClickListener(new View.OnClickListener() { // from class: i.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BarCodeScannerActivity.m72bindPreview$lambda2(Camera.this, this, view);
                }
            });
            bindToLifecycle.getCameraInfo().getTorchState().observe(this, new Observer() { // from class: i.e
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    BarCodeScannerActivity.m73bindPreview$lambda4(BarCodeScannerActivity.this, (Integer) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: bindPreview$lambda-2  reason: not valid java name */
    public static final void m72bindPreview$lambda2(Camera camera, BarCodeScannerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        camera.getCameraControl().enableTorch(!this$0.flashEnabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: bindPreview$lambda-4  reason: not valid java name */
    public static final void m73bindPreview$lambda4(BarCodeScannerActivity this$0, Integer num) {
        AppCompatImageView appCompatImageView;
        int i2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (num != null) {
            if (num.intValue() == 1) {
                this$0.flashEnabled = true;
                appCompatImageView = (AppCompatImageView) this$0._$_findCachedViewById(R.id.ivFlashControl);
                i2 = uat.psa.mym.mycitroenconnect.R.drawable.ic_round_flash_on;
            } else {
                this$0.flashEnabled = false;
                appCompatImageView = (AppCompatImageView) this$0._$_findCachedViewById(R.id.ivFlashControl);
                i2 = uat.psa.mym.mycitroenconnect.R.drawable.ic_round_flash_off;
            }
            appCompatImageView.setImageResource(i2);
        }
    }

    private final void init() {
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(this);
        ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(this);
        Intrinsics.checkNotNullExpressionValue(processCameraProvider, "getInstance(this)");
        this.cameraProviderFuture = processCameraProvider;
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "newSingleThreadExecutor()");
        this.cameraExecutor = newSingleThreadExecutor;
        ListenableFuture<ProcessCameraProvider> listenableFuture = this.cameraProviderFuture;
        if (listenableFuture == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraProviderFuture");
            listenableFuture = null;
        }
        listenableFuture.addListener(new Runnable() { // from class: i.f
            @Override // java.lang.Runnable
            public final void run() {
                BarCodeScannerActivity.m74init$lambda0(BarCodeScannerActivity.this);
            }
        }, ContextCompat.getMainExecutor(this));
        ((ViewFinderOverlay) _$_findCachedViewById(R.id.overlay)).post(new Runnable() { // from class: i.g
            @Override // java.lang.Runnable
            public final void run() {
                BarCodeScannerActivity.m75init$lambda1(BarCodeScannerActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: init$lambda-0  reason: not valid java name */
    public static final void m74init$lambda0(BarCodeScannerActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ListenableFuture<ProcessCameraProvider> listenableFuture = this$0.cameraProviderFuture;
        if (listenableFuture == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraProviderFuture");
            listenableFuture = null;
        }
        this$0.bindPreview(listenableFuture.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: init$lambda-1  reason: not valid java name */
    public static final void m75init$lambda1(BarCodeScannerActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ViewFinderOverlay) this$0._$_findCachedViewById(R.id.overlay)).setViewFinder();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivBack))) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_bar_code_scanner);
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ExecutorService executorService = this.cameraExecutor;
        if (executorService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraExecutor");
            executorService = null;
        }
        executorService.shutdown();
    }
}
