package androidx.camera.core;

import androidx.camera.core.ImageReaderFormatRecommender;
/* loaded from: classes.dex */
final class AutoValue_ImageReaderFormatRecommender_FormatCombo extends ImageReaderFormatRecommender.FormatCombo {
    private final int imageAnalysisFormat;
    private final int imageCaptureFormat;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.camera.core.ImageReaderFormatRecommender.FormatCombo
    public int a() {
        return this.imageAnalysisFormat;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.camera.core.ImageReaderFormatRecommender.FormatCombo
    public int b() {
        return this.imageCaptureFormat;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImageReaderFormatRecommender.FormatCombo) {
            ImageReaderFormatRecommender.FormatCombo formatCombo = (ImageReaderFormatRecommender.FormatCombo) obj;
            return this.imageCaptureFormat == formatCombo.b() && this.imageAnalysisFormat == formatCombo.a();
        }
        return false;
    }

    public int hashCode() {
        return ((this.imageCaptureFormat ^ 1000003) * 1000003) ^ this.imageAnalysisFormat;
    }

    public String toString() {
        return "FormatCombo{imageCaptureFormat=" + this.imageCaptureFormat + ", imageAnalysisFormat=" + this.imageAnalysisFormat + "}";
    }
}
