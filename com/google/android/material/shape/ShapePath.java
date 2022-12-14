package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class ShapePath {
    private static final float ANGLE_UP = 270.0f;
    private boolean containsIncompatibleShadowOp;
    @Deprecated
    public float currentShadowAngle;
    @Deprecated
    public float endShadowAngle;
    @Deprecated
    public float endX;
    @Deprecated
    public float endY;
    private final List<PathOperation> operations = new ArrayList();
    private final List<ShadowCompatOperation> shadowCompatOperations = new ArrayList();
    @Deprecated
    public float startX;
    @Deprecated
    public float startY;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class ArcShadowOperation extends ShadowCompatOperation {
        private final PathArcOperation operation;

        public ArcShadowOperation(PathArcOperation pathArcOperation) {
            this.operation = pathArcOperation;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix matrix, @NonNull ShadowRenderer shadowRenderer, int i2, @NonNull Canvas canvas) {
            shadowRenderer.drawCornerShadow(canvas, matrix, new RectF(this.operation.getLeft(), this.operation.getTop(), this.operation.getRight(), this.operation.getBottom()), i2, this.operation.getStartAngle(), this.operation.getSweepAngle());
        }
    }

    /* loaded from: classes2.dex */
    static class LineShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation;
        private final float startX;
        private final float startY;

        public LineShadowOperation(PathLineOperation pathLineOperation, float f2, float f3) {
            this.operation = pathLineOperation;
            this.startX = f2;
            this.startY = f3;
        }

        float a() {
            return (float) Math.toDegrees(Math.atan((this.operation.y - this.startY) / (this.operation.x - this.startX)));
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix matrix, @NonNull ShadowRenderer shadowRenderer, int i2, @NonNull Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.operation.y - this.startY, this.operation.x - this.startX), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.startX, this.startY);
            matrix2.preRotate(a());
            shadowRenderer.drawEdgeShadow(canvas, matrix2, rectF, i2);
        }
    }

    /* loaded from: classes2.dex */
    public static class PathArcOperation extends PathOperation {
        private static final RectF rectF = new RectF();
        @Deprecated
        public float bottom;
        @Deprecated
        public float left;
        @Deprecated
        public float right;
        @Deprecated
        public float startAngle;
        @Deprecated
        public float sweepAngle;
        @Deprecated
        public float top;

        public PathArcOperation(float f2, float f3, float f4, float f5) {
            setLeft(f2);
            setTop(f3);
            setRight(f4);
            setBottom(f5);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getBottom() {
            return this.bottom;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getLeft() {
            return this.left;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getRight() {
            return this.right;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getStartAngle() {
            return this.startAngle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getSweepAngle() {
            return this.sweepAngle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getTop() {
            return this.top;
        }

        private void setBottom(float f2) {
            this.bottom = f2;
        }

        private void setLeft(float f2) {
            this.left = f2;
        }

        private void setRight(float f2) {
            this.right = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartAngle(float f2) {
            this.startAngle = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSweepAngle(float f2) {
            this.sweepAngle = f2;
        }

        private void setTop(float f2) {
            this.top = f2;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.f7474a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF2 = rectF;
            rectF2.set(getLeft(), getTop(), getRight(), getBottom());
            path.arcTo(rectF2, getStartAngle(), getSweepAngle(), false);
            path.transform(matrix);
        }
    }

    /* loaded from: classes2.dex */
    public static class PathCubicOperation extends PathOperation {
        private float controlX1;
        private float controlX2;
        private float controlY1;
        private float controlY2;
        private float endX;
        private float endY;

        public PathCubicOperation(float f2, float f3, float f4, float f5, float f6, float f7) {
            setControlX1(f2);
            setControlY1(f3);
            setControlX2(f4);
            setControlY2(f5);
            setEndX(f6);
            setEndY(f7);
        }

        private float getControlX1() {
            return this.controlX1;
        }

        private float getControlX2() {
            return this.controlX2;
        }

        private float getControlY1() {
            return this.controlY1;
        }

        private float getControlY2() {
            return this.controlY1;
        }

        private float getEndX() {
            return this.endX;
        }

        private float getEndY() {
            return this.endY;
        }

        private void setControlX1(float f2) {
            this.controlX1 = f2;
        }

        private void setControlX2(float f2) {
            this.controlX2 = f2;
        }

        private void setControlY1(float f2) {
            this.controlY1 = f2;
        }

        private void setControlY2(float f2) {
            this.controlY2 = f2;
        }

        private void setEndX(float f2) {
            this.endX = f2;
        }

        private void setEndY(float f2) {
            this.endY = f2;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.f7474a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX, this.endY);
            path.transform(matrix);
        }
    }

    /* loaded from: classes2.dex */
    public static class PathLineOperation extends PathOperation {
        private float x;
        private float y;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.f7474a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.x, this.y);
            path.transform(matrix);
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class PathOperation {

        /* renamed from: a  reason: collision with root package name */
        protected final Matrix f7474a = new Matrix();

        public abstract void applyToPath(Matrix matrix, Path path);
    }

    /* loaded from: classes2.dex */
    public static class PathQuadOperation extends PathOperation {
        @Deprecated
        public float controlX;
        @Deprecated
        public float controlY;
        @Deprecated
        public float endX;
        @Deprecated
        public float endY;

        private float getControlX() {
            return this.controlX;
        }

        private float getControlY() {
            return this.controlY;
        }

        private float getEndX() {
            return this.endX;
        }

        private float getEndY() {
            return this.endY;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setControlX(float f2) {
            this.controlX = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setControlY(float f2) {
            this.controlY = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndX(float f2) {
            this.endX = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndY(float f2) {
            this.endY = f2;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.f7474a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.quadTo(getControlX(), getControlY(), getEndX(), getEndY());
            path.transform(matrix);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class ShadowCompatOperation {

        /* renamed from: a  reason: collision with root package name */
        static final Matrix f7475a = new Matrix();

        ShadowCompatOperation() {
        }

        public abstract void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i2, Canvas canvas);

        public final void draw(ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
            draw(f7475a, shadowRenderer, i2, canvas);
        }
    }

    public ShapePath() {
        reset(0.0f, 0.0f);
    }

    public ShapePath(float f2, float f3) {
        reset(f2, f3);
    }

    private void addConnectingShadowIfNecessary(float f2) {
        if (getCurrentShadowAngle() == f2) {
            return;
        }
        float currentShadowAngle = ((f2 - getCurrentShadowAngle()) + 360.0f) % 360.0f;
        if (currentShadowAngle > 180.0f) {
            return;
        }
        PathArcOperation pathArcOperation = new PathArcOperation(c(), d(), c(), d());
        pathArcOperation.setStartAngle(getCurrentShadowAngle());
        pathArcOperation.setSweepAngle(currentShadowAngle);
        this.shadowCompatOperations.add(new ArcShadowOperation(pathArcOperation));
        setCurrentShadowAngle(f2);
    }

    private void addShadowCompatOperation(ShadowCompatOperation shadowCompatOperation, float f2, float f3) {
        addConnectingShadowIfNecessary(f2);
        this.shadowCompatOperations.add(shadowCompatOperation);
        setCurrentShadowAngle(f3);
    }

    private float getCurrentShadowAngle() {
        return this.currentShadowAngle;
    }

    private float getEndShadowAngle() {
        return this.endShadowAngle;
    }

    private void setCurrentShadowAngle(float f2) {
        this.currentShadowAngle = f2;
    }

    private void setEndShadowAngle(float f2) {
        this.endShadowAngle = f2;
    }

    private void setEndX(float f2) {
        this.endX = f2;
    }

    private void setEndY(float f2) {
        this.endY = f2;
    }

    private void setStartX(float f2) {
        this.startX = f2;
    }

    private void setStartY(float f2) {
        this.startY = f2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return this.containsIncompatibleShadowOp;
    }

    public void addArc(float f2, float f3, float f4, float f5, float f6, float f7) {
        PathArcOperation pathArcOperation = new PathArcOperation(f2, f3, f4, f5);
        pathArcOperation.setStartAngle(f6);
        pathArcOperation.setSweepAngle(f7);
        this.operations.add(pathArcOperation);
        ArcShadowOperation arcShadowOperation = new ArcShadowOperation(pathArcOperation);
        float f8 = f6 + f7;
        boolean z = f7 < 0.0f;
        if (z) {
            f6 = (f6 + 180.0f) % 360.0f;
        }
        addShadowCompatOperation(arcShadowOperation, f6, z ? (180.0f + f8) % 360.0f : f8);
        double d2 = f8;
        setEndX(((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.cos(Math.toRadians(d2)))));
        setEndY(((f3 + f5) * 0.5f) + (((f5 - f3) / 2.0f) * ((float) Math.sin(Math.toRadians(d2)))));
    }

    public void applyToPath(Matrix matrix, Path path) {
        int size = this.operations.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.operations.get(i2).applyToPath(matrix, path);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public ShadowCompatOperation b(Matrix matrix) {
        addConnectingShadowIfNecessary(getEndShadowAngle());
        final Matrix matrix2 = new Matrix(matrix);
        final ArrayList arrayList = new ArrayList(this.shadowCompatOperations);
        return new ShadowCompatOperation(this) { // from class: com.google.android.material.shape.ShapePath.1
            @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
            public void draw(Matrix matrix3, ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
                for (ShadowCompatOperation shadowCompatOperation : arrayList) {
                    shadowCompatOperation.draw(matrix2, shadowRenderer, i2, canvas);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c() {
        return this.endX;
    }

    @RequiresApi(21)
    public void cubicToPoint(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.operations.add(new PathCubicOperation(f2, f3, f4, f5, f6, f7));
        this.containsIncompatibleShadowOp = true;
        setEndX(f6);
        setEndY(f7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float d() {
        return this.endY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float e() {
        return this.startX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float f() {
        return this.startY;
    }

    public void lineTo(float f2, float f3) {
        PathLineOperation pathLineOperation = new PathLineOperation();
        pathLineOperation.x = f2;
        pathLineOperation.y = f3;
        this.operations.add(pathLineOperation);
        LineShadowOperation lineShadowOperation = new LineShadowOperation(pathLineOperation, c(), d());
        addShadowCompatOperation(lineShadowOperation, lineShadowOperation.a() + 270.0f, lineShadowOperation.a() + 270.0f);
        setEndX(f2);
        setEndY(f3);
    }

    @RequiresApi(21)
    public void quadToPoint(float f2, float f3, float f4, float f5) {
        PathQuadOperation pathQuadOperation = new PathQuadOperation();
        pathQuadOperation.setControlX(f2);
        pathQuadOperation.setControlY(f3);
        pathQuadOperation.setEndX(f4);
        pathQuadOperation.setEndY(f5);
        this.operations.add(pathQuadOperation);
        this.containsIncompatibleShadowOp = true;
        setEndX(f4);
        setEndY(f5);
    }

    public void reset(float f2, float f3) {
        reset(f2, f3, 270.0f, 0.0f);
    }

    public void reset(float f2, float f3, float f4, float f5) {
        setStartX(f2);
        setStartY(f3);
        setEndX(f2);
        setEndY(f3);
        setCurrentShadowAngle(f4);
        setEndShadowAngle((f4 + f5) % 360.0f);
        this.operations.clear();
        this.shadowCompatOperations.clear();
        this.containsIncompatibleShadowOp = false;
    }
}
