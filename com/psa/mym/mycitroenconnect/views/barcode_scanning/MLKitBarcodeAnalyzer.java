package com.psa.mym.mycitroenconnect.views.barcode_scanning;

import android.media.Image;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.views.barcode_scanning.MLKitBarcodeAnalyzer;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class MLKitBarcodeAnalyzer implements ImageAnalysis.Analyzer {
    private boolean isScanning;
    @NotNull
    private final ScanningResultListener listener;

    public MLKitBarcodeAnalyzer(@NotNull ScanningResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: analyze$lambda-2  reason: not valid java name */
    public static final void m171analyze$lambda2(MLKitBarcodeAnalyzer this$0, ImageProxy imageProxy, List barcodes) {
        Object firstOrNull;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(imageProxy, "$imageProxy");
        Intrinsics.checkNotNullExpressionValue(barcodes, "barcodes");
        firstOrNull = CollectionsKt___CollectionsKt.firstOrNull((List<? extends Object>) barcodes);
        Barcode barcode = (Barcode) firstOrNull;
        String rawValue = barcode != null ? barcode.getRawValue() : null;
        if (rawValue != null) {
            Logger.INSTANCE.d(rawValue);
            this$0.listener.onScanned(rawValue);
        }
        this$0.isScanning = false;
        imageProxy.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: analyze$lambda-3  reason: not valid java name */
    public static final void m172analyze$lambda3(MLKitBarcodeAnalyzer this$0, ImageProxy imageProxy, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(imageProxy, "$imageProxy");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.isScanning = false;
        imageProxy.close();
    }

    @Override // androidx.camera.core.ImageAnalysis.Analyzer
    @ExperimentalGetImage
    public void analyze(@NotNull final ImageProxy imageProxy) {
        Intrinsics.checkNotNullParameter(imageProxy, "imageProxy");
        Image image = imageProxy.getImage();
        if (image == null || this.isScanning) {
            return;
        }
        InputImage fromMediaImage = InputImage.fromMediaImage(image, imageProxy.getImageInfo().getRotationDegrees());
        Intrinsics.checkNotNullExpressionValue(fromMediaImage, "fromMediaImage(mediaImag…mageInfo.rotationDegrees)");
        BarcodeScanner client = BarcodeScanning.getClient();
        Intrinsics.checkNotNullExpressionValue(client, "getClient()");
        this.isScanning = true;
        client.process(fromMediaImage).addOnSuccessListener(new OnSuccessListener() { // from class: p.b
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                MLKitBarcodeAnalyzer.m171analyze$lambda2(MLKitBarcodeAnalyzer.this, imageProxy, (List) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: p.a
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                MLKitBarcodeAnalyzer.m172analyze$lambda3(MLKitBarcodeAnalyzer.this, imageProxy, exc);
            }
        });
    }
}
