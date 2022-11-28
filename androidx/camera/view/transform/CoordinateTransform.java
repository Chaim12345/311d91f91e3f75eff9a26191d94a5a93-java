package androidx.camera.view.transform;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.camera.core.Logger;
import androidx.camera.view.TransformExperimental;
import androidx.camera.view.TransformUtils;
import androidx.core.util.Preconditions;
@TransformExperimental
/* loaded from: classes.dex */
public final class CoordinateTransform {
    private static final String MISMATCH_MSG = "The source viewport (%s) does not match the target viewport (%s). Please make sure they are associated with the same Viewport.";
    private static final String TAG = "CoordinateTransform";
    private final Matrix mMatrix;

    public CoordinateTransform(@NonNull OutputTransform outputTransform, @NonNull OutputTransform outputTransform2) {
        if (!TransformUtils.isAspectRatioMatchingWithRoundingError(outputTransform.b(), false, outputTransform2.b(), false)) {
            Logger.w(TAG, String.format(MISMATCH_MSG, outputTransform.b(), outputTransform2.b()));
        }
        Matrix matrix = new Matrix();
        this.mMatrix = matrix;
        Preconditions.checkState(outputTransform.a().invert(matrix), "The source transform cannot be inverted");
        matrix.postConcat(outputTransform2.a());
    }

    public void mapPoint(@NonNull PointF pointF) {
        float[] fArr = {pointF.x, pointF.y};
        this.mMatrix.mapPoints(fArr);
        pointF.x = fArr[0];
        pointF.y = fArr[1];
    }

    public void mapPoints(@NonNull float[] fArr) {
        this.mMatrix.mapPoints(fArr);
    }

    public void mapRect(@NonNull RectF rectF) {
        this.mMatrix.mapRect(rectF);
    }

    public void transform(@NonNull Matrix matrix) {
        matrix.set(this.mMatrix);
    }
}
