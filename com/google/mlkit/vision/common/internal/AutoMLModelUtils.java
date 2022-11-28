package com.google.mlkit.vision.common.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.internal.model.ModelUtils;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.sdkinternal.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
@KeepForSdk
@WorkerThread
/* loaded from: classes2.dex */
public class AutoMLModelUtils {
    private AutoMLModelUtils() {
    }

    @NonNull
    @KeepForSdk
    public static String[] getModelAndLabelFilePaths(@NonNull Context context, @NonNull LocalModel localModel, boolean z) {
        String str;
        String str2 = (String) Preconditions.checkNotNull(z ? localModel.getAssetFilePath() : localModel.getAbsoluteFilePath());
        if (localModel.isManifestFile()) {
            ModelUtils.AutoMLManifest parseManifestFile = ModelUtils.parseManifestFile(str2, z, context);
            if (parseManifestFile == null) {
                throw new IOException("Failed to parse manifest file.");
            }
            Preconditions.checkState(Constants.AUTOML_IMAGE_LABELING_MODEL_TYPE.equals(parseManifestFile.getModelType()), "Model type should be: %s.", Constants.AUTOML_IMAGE_LABELING_MODEL_TYPE);
            str2 = new File(new File(str2).getParent(), parseManifestFile.getModelFile()).toString();
            str = new File(new File(str2).getParent(), parseManifestFile.getLabelsFile()).toString();
        } else {
            str = "";
        }
        return new String[]{str2, str};
    }

    @NonNull
    @KeepForSdk
    public static List<String> readLabelsFile(@NonNull Context context, @NonNull String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        InputStream open = z ? context.getAssets().open(str) : new FileInputStream(new File(str));
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open, "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                arrayList.add(readLine);
            }
            if (open != null) {
                open.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (open != null) {
                try {
                    open.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
