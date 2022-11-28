package com.psa.mym.mycitroenconnect.controller.activities;

import android.content.Intent;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.psa.mym.mycitroenconnect.controller.activities.BarCodeScannerActivity$bindPreview$ScanningListener;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.views.barcode_scanning.ScanningResultListener;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BarCodeScannerActivity$bindPreview$ScanningListener implements ScanningResultListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BarCodeScannerActivity f10366a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ImageAnalysis f10367b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ ProcessCameraProvider f10368c;

    public BarCodeScannerActivity$bindPreview$ScanningListener(@NotNull BarCodeScannerActivity this$0, @NotNull ImageAnalysis imageAnalysis, @Nullable ProcessCameraProvider processCameraProvider) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(imageAnalysis, "$imageAnalysis");
        this.f10366a = this$0;
        this.f10367b = imageAnalysis;
        this.f10368c = processCameraProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onScanned$lambda-0  reason: not valid java name */
    public static final void m76onScanned$lambda0(ImageAnalysis imageAnalysis, ProcessCameraProvider processCameraProvider, String result, BarCodeScannerActivity this$0) {
        Intrinsics.checkNotNullParameter(imageAnalysis, "$imageAnalysis");
        Intrinsics.checkNotNullParameter(result, "$result");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        imageAnalysis.clearAnalyzer();
        if (processCameraProvider != null) {
            processCameraProvider.unbindAll();
        }
        Logger logger = Logger.INSTANCE;
        logger.e("Result: " + result);
        Intent intent = new Intent();
        intent.putExtra("result", result);
        this$0.setResult(-1, intent);
        this$0.finish();
    }

    @Override // com.psa.mym.mycitroenconnect.views.barcode_scanning.ScanningResultListener
    public void onScanned(@NotNull final String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        final BarCodeScannerActivity barCodeScannerActivity = this.f10366a;
        final ImageAnalysis imageAnalysis = this.f10367b;
        final ProcessCameraProvider processCameraProvider = this.f10368c;
        barCodeScannerActivity.runOnUiThread(new Runnable() { // from class: i.h
            @Override // java.lang.Runnable
            public final void run() {
                BarCodeScannerActivity$bindPreview$ScanningListener.m76onScanned$lambda0(ImageAnalysis.this, processCameraProvider, result, barCodeScannerActivity);
            }
        });
    }
}
