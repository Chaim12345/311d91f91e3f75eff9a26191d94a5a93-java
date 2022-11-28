package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_common.zzam;
import java.util.List;
@KeepForSdk
/* loaded from: classes2.dex */
public class OptionalModuleUtils {
    @NonNull
    @KeepForSdk
    public static final String BARCODE = "barcode";
    @NonNull
    @KeepForSdk
    public static final String CUSTOM_ICA = "custom_ica";
    @NonNull
    @KeepForSdk
    public static final String DEPRECATED_DYNAMITE_MODULE_ID = "com.google.android.gms.vision.dynamite";
    @NonNull
    @KeepForSdk
    public static final String FACE = "face";
    @NonNull
    @KeepForSdk
    public static final String ICA = "ica";
    @NonNull
    @KeepForSdk
    public static final String LANGID = "langid";
    @NonNull
    @KeepForSdk
    public static final String MLKIT_BARCODE_UI = "barcode_ui";
    @NonNull
    @KeepForSdk
    public static final String NLCLASSIFIER = "nlclassifier";
    @NonNull
    @KeepForSdk
    public static final String NLCLASSIFIER_MODULE_ID = "com.google.android.gms.mlkit.nlclassifier";
    @NonNull
    @KeepForSdk
    public static final String OCR = "ocr";
    @NonNull
    @KeepForSdk
    public static final String TFLITE_DYNAMITE = "tflite_dynamite";
    @NonNull
    @KeepForSdk
    public static final String TFLITE_DYNAMITE_MODULE_ID = "com.google.android.gms.tflite_dynamite";

    private OptionalModuleUtils() {
    }

    @KeepForSdk
    public static boolean areAllRequiredModulesAvailable(@NonNull Context context, @NonNull List<String> list) {
        try {
            for (String str : list) {
                DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, str);
            }
            return true;
        } catch (DynamiteModule.LoadingException unused) {
            return false;
        }
    }

    @KeepForSdk
    public static void requestDownload(@NonNull Context context, @NonNull String str) {
        requestDownload(context, zzam.zzj(str));
    }

    @KeepForSdk
    public static void requestDownload(@NonNull Context context, @NonNull List<String> list) {
        Intent intent = new Intent();
        intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
        intent.setAction("com.google.android.gms.vision.DEPENDENCY");
        intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", TextUtils.join(",", list));
        intent.putExtra("requester_app_package", context.getApplicationInfo().packageName);
        context.sendBroadcast(intent);
    }
}
