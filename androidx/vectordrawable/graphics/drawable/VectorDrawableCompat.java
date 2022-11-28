package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.content.res.ComplexColorCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import androidx.core.graphics.drawable.DrawableCompat;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class VectorDrawableCompat extends VectorDrawableCommon {
    private static final boolean DBG_VECTOR_DRAWABLE = false;
    private static final int LINECAP_BUTT = 0;
    private static final int LINECAP_ROUND = 1;
    private static final int LINECAP_SQUARE = 2;
    private static final int LINEJOIN_BEVEL = 2;
    private static final int LINEJOIN_MITER = 0;
    private static final int LINEJOIN_ROUND = 1;
    private static final int MAX_CACHED_BITMAP_SIZE = 2048;
    private static final String SHAPE_CLIP_PATH = "clip-path";
    private static final String SHAPE_GROUP = "group";
    private static final String SHAPE_PATH = "path";
    private static final String SHAPE_VECTOR = "vector";

    /* renamed from: b  reason: collision with root package name */
    static final PorterDuff.Mode f4182b = PorterDuff.Mode.SRC_IN;
    private boolean mAllowCaching;
    private Drawable.ConstantState mCachedConstantStateDelegate;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float[] mTmpFloats;
    private final Matrix mTmpMatrix;
    private VectorDrawableCompatState mVectorState;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class VClipPath extends VPath {
        VClipPath() {
        }

        VClipPath(VClipPath vClipPath) {
            super(vClipPath);
        }

        private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f4200b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f4199a = PathParser.createNodesFromPathData(string2);
            }
            this.f4201c = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "fillType", 2, 0);
        }

        public void inflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
                TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.f4167d);
                updateStateFromTypedArray(obtainAttributes, xmlPullParser);
                obtainAttributes.recycle();
            }
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VPath
        public boolean isClipPath() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class VFullPath extends VPath {

        /* renamed from: e  reason: collision with root package name */
        ComplexColorCompat f4183e;

        /* renamed from: f  reason: collision with root package name */
        float f4184f;

        /* renamed from: g  reason: collision with root package name */
        ComplexColorCompat f4185g;

        /* renamed from: h  reason: collision with root package name */
        float f4186h;

        /* renamed from: i  reason: collision with root package name */
        float f4187i;

        /* renamed from: j  reason: collision with root package name */
        float f4188j;

        /* renamed from: k  reason: collision with root package name */
        float f4189k;

        /* renamed from: l  reason: collision with root package name */
        float f4190l;

        /* renamed from: m  reason: collision with root package name */
        Paint.Cap f4191m;
        private int[] mThemeAttrs;

        /* renamed from: n  reason: collision with root package name */
        Paint.Join f4192n;

        /* renamed from: o  reason: collision with root package name */
        float f4193o;

        VFullPath() {
            this.f4184f = 0.0f;
            this.f4186h = 1.0f;
            this.f4187i = 1.0f;
            this.f4188j = 0.0f;
            this.f4189k = 1.0f;
            this.f4190l = 0.0f;
            this.f4191m = Paint.Cap.BUTT;
            this.f4192n = Paint.Join.MITER;
            this.f4193o = 4.0f;
        }

        VFullPath(VFullPath vFullPath) {
            super(vFullPath);
            this.f4184f = 0.0f;
            this.f4186h = 1.0f;
            this.f4187i = 1.0f;
            this.f4188j = 0.0f;
            this.f4189k = 1.0f;
            this.f4190l = 0.0f;
            this.f4191m = Paint.Cap.BUTT;
            this.f4192n = Paint.Join.MITER;
            this.f4193o = 4.0f;
            this.mThemeAttrs = vFullPath.mThemeAttrs;
            this.f4183e = vFullPath.f4183e;
            this.f4184f = vFullPath.f4184f;
            this.f4186h = vFullPath.f4186h;
            this.f4185g = vFullPath.f4185g;
            this.f4201c = vFullPath.f4201c;
            this.f4187i = vFullPath.f4187i;
            this.f4188j = vFullPath.f4188j;
            this.f4189k = vFullPath.f4189k;
            this.f4190l = vFullPath.f4190l;
            this.f4191m = vFullPath.f4191m;
            this.f4192n = vFullPath.f4192n;
            this.f4193o = vFullPath.f4193o;
        }

        private Paint.Cap getStrokeLineCap(int i2, Paint.Cap cap) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        private Paint.Join getStrokeLineJoin(int i2, Paint.Join join) {
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.mThemeAttrs = null;
            if (TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f4200b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f4199a = PathParser.createNodesFromPathData(string2);
                }
                this.f4185g = TypedArrayUtils.getNamedComplexColor(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.f4187i = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "fillAlpha", 12, this.f4187i);
                this.f4191m = getStrokeLineCap(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.f4191m);
                this.f4192n = getStrokeLineJoin(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.f4192n);
                this.f4193o = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.f4193o);
                this.f4183e = TypedArrayUtils.getNamedComplexColor(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.f4186h = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeAlpha", 11, this.f4186h);
                this.f4184f = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeWidth", 4, this.f4184f);
                this.f4189k = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathEnd", 6, this.f4189k);
                this.f4190l = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathOffset", 7, this.f4190l);
                this.f4188j = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathStart", 5, this.f4188j);
                this.f4201c = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "fillType", 13, this.f4201c);
            }
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VPath
        public void applyTheme(Resources.Theme theme) {
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VPath
        public boolean canApplyTheme() {
            return this.mThemeAttrs != null;
        }

        float getFillAlpha() {
            return this.f4187i;
        }

        @ColorInt
        int getFillColor() {
            return this.f4185g.getColor();
        }

        float getStrokeAlpha() {
            return this.f4186h;
        }

        @ColorInt
        int getStrokeColor() {
            return this.f4183e.getColor();
        }

        float getStrokeWidth() {
            return this.f4184f;
        }

        float getTrimPathEnd() {
            return this.f4189k;
        }

        float getTrimPathOffset() {
            return this.f4190l;
        }

        float getTrimPathStart() {
            return this.f4188j;
        }

        public void inflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.f4166c);
            updateStateFromTypedArray(obtainAttributes, xmlPullParser, theme);
            obtainAttributes.recycle();
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean isStateful() {
            return this.f4185g.isStateful() || this.f4183e.isStateful();
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean onStateChanged(int[] iArr) {
            return this.f4183e.onStateChanged(iArr) | this.f4185g.onStateChanged(iArr);
        }

        void setFillAlpha(float f2) {
            this.f4187i = f2;
        }

        void setFillColor(int i2) {
            this.f4185g.setColor(i2);
        }

        void setStrokeAlpha(float f2) {
            this.f4186h = f2;
        }

        void setStrokeColor(int i2) {
            this.f4183e.setColor(i2);
        }

        void setStrokeWidth(float f2) {
            this.f4184f = f2;
        }

        void setTrimPathEnd(float f2) {
            this.f4189k = f2;
        }

        void setTrimPathOffset(float f2) {
            this.f4190l = f2;
        }

        void setTrimPathStart(float f2) {
            this.f4188j = f2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class VGroup extends VObject {

        /* renamed from: a  reason: collision with root package name */
        final Matrix f4194a;

        /* renamed from: b  reason: collision with root package name */
        final ArrayList f4195b;

        /* renamed from: c  reason: collision with root package name */
        float f4196c;

        /* renamed from: d  reason: collision with root package name */
        final Matrix f4197d;

        /* renamed from: e  reason: collision with root package name */
        int f4198e;
        private String mGroupName;
        private float mPivotX;
        private float mPivotY;
        private float mScaleX;
        private float mScaleY;
        private int[] mThemeAttrs;
        private float mTranslateX;
        private float mTranslateY;

        public VGroup() {
            super();
            this.f4194a = new Matrix();
            this.f4195b = new ArrayList();
            this.f4196c = 0.0f;
            this.mPivotX = 0.0f;
            this.mPivotY = 0.0f;
            this.mScaleX = 1.0f;
            this.mScaleY = 1.0f;
            this.mTranslateX = 0.0f;
            this.mTranslateY = 0.0f;
            this.f4197d = new Matrix();
            this.mGroupName = null;
        }

        public VGroup(VGroup vGroup, ArrayMap<String, Object> arrayMap) {
            super();
            VPath vClipPath;
            this.f4194a = new Matrix();
            this.f4195b = new ArrayList();
            this.f4196c = 0.0f;
            this.mPivotX = 0.0f;
            this.mPivotY = 0.0f;
            this.mScaleX = 1.0f;
            this.mScaleY = 1.0f;
            this.mTranslateX = 0.0f;
            this.mTranslateY = 0.0f;
            Matrix matrix = new Matrix();
            this.f4197d = matrix;
            this.mGroupName = null;
            this.f4196c = vGroup.f4196c;
            this.mPivotX = vGroup.mPivotX;
            this.mPivotY = vGroup.mPivotY;
            this.mScaleX = vGroup.mScaleX;
            this.mScaleY = vGroup.mScaleY;
            this.mTranslateX = vGroup.mTranslateX;
            this.mTranslateY = vGroup.mTranslateY;
            this.mThemeAttrs = vGroup.mThemeAttrs;
            String str = vGroup.mGroupName;
            this.mGroupName = str;
            this.f4198e = vGroup.f4198e;
            if (str != null) {
                arrayMap.put(str, this);
            }
            matrix.set(vGroup.f4197d);
            ArrayList arrayList = vGroup.f4195b;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Object obj = arrayList.get(i2);
                if (obj instanceof VGroup) {
                    this.f4195b.add(new VGroup((VGroup) obj, arrayMap));
                } else {
                    if (obj instanceof VFullPath) {
                        vClipPath = new VFullPath((VFullPath) obj);
                    } else if (!(obj instanceof VClipPath)) {
                        throw new IllegalStateException("Unknown object in the tree!");
                    } else {
                        vClipPath = new VClipPath((VClipPath) obj);
                    }
                    this.f4195b.add(vClipPath);
                    String str2 = vClipPath.f4200b;
                    if (str2 != null) {
                        arrayMap.put(str2, vClipPath);
                    }
                }
            }
        }

        private void updateLocalMatrix() {
            this.f4197d.reset();
            this.f4197d.postTranslate(-this.mPivotX, -this.mPivotY);
            this.f4197d.postScale(this.mScaleX, this.mScaleY);
            this.f4197d.postRotate(this.f4196c, 0.0f, 0.0f);
            this.f4197d.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
        }

        private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.mThemeAttrs = null;
            this.f4196c = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, Key.ROTATION, 5, this.f4196c);
            this.mPivotX = typedArray.getFloat(1, this.mPivotX);
            this.mPivotY = typedArray.getFloat(2, this.mPivotY);
            this.mScaleX = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "scaleX", 3, this.mScaleX);
            this.mScaleY = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "scaleY", 4, this.mScaleY);
            this.mTranslateX = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "translateX", 6, this.mTranslateX);
            this.mTranslateY = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "translateY", 7, this.mTranslateY);
            String string = typedArray.getString(0);
            if (string != null) {
                this.mGroupName = string;
            }
            updateLocalMatrix();
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public Matrix getLocalMatrix() {
            return this.f4197d;
        }

        public float getPivotX() {
            return this.mPivotX;
        }

        public float getPivotY() {
            return this.mPivotY;
        }

        public float getRotation() {
            return this.f4196c;
        }

        public float getScaleX() {
            return this.mScaleX;
        }

        public float getScaleY() {
            return this.mScaleY;
        }

        public float getTranslateX() {
            return this.mTranslateX;
        }

        public float getTranslateY() {
            return this.mTranslateY;
        }

        public void inflate(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.f4165b);
            updateStateFromTypedArray(obtainAttributes, xmlPullParser);
            obtainAttributes.recycle();
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean isStateful() {
            for (int i2 = 0; i2 < this.f4195b.size(); i2++) {
                if (((VObject) this.f4195b.get(i2)).isStateful()) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean onStateChanged(int[] iArr) {
            boolean z = false;
            for (int i2 = 0; i2 < this.f4195b.size(); i2++) {
                z |= ((VObject) this.f4195b.get(i2)).onStateChanged(iArr);
            }
            return z;
        }

        public void setPivotX(float f2) {
            if (f2 != this.mPivotX) {
                this.mPivotX = f2;
                updateLocalMatrix();
            }
        }

        public void setPivotY(float f2) {
            if (f2 != this.mPivotY) {
                this.mPivotY = f2;
                updateLocalMatrix();
            }
        }

        public void setRotation(float f2) {
            if (f2 != this.f4196c) {
                this.f4196c = f2;
                updateLocalMatrix();
            }
        }

        public void setScaleX(float f2) {
            if (f2 != this.mScaleX) {
                this.mScaleX = f2;
                updateLocalMatrix();
            }
        }

        public void setScaleY(float f2) {
            if (f2 != this.mScaleY) {
                this.mScaleY = f2;
                updateLocalMatrix();
            }
        }

        public void setTranslateX(float f2) {
            if (f2 != this.mTranslateX) {
                this.mTranslateX = f2;
                updateLocalMatrix();
            }
        }

        public void setTranslateY(float f2) {
            if (f2 != this.mTranslateY) {
                this.mTranslateY = f2;
                updateLocalMatrix();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class VObject {
        private VObject() {
        }

        public boolean isStateful() {
            return false;
        }

        public boolean onStateChanged(int[] iArr) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class VPath extends VObject {

        /* renamed from: a  reason: collision with root package name */
        protected PathParser.PathDataNode[] f4199a;

        /* renamed from: b  reason: collision with root package name */
        String f4200b;

        /* renamed from: c  reason: collision with root package name */
        int f4201c;

        /* renamed from: d  reason: collision with root package name */
        int f4202d;

        public VPath() {
            super();
            this.f4199a = null;
            this.f4201c = 0;
        }

        public VPath(VPath vPath) {
            super();
            this.f4199a = null;
            this.f4201c = 0;
            this.f4200b = vPath.f4200b;
            this.f4202d = vPath.f4202d;
            this.f4199a = PathParser.deepCopyNodes(vPath.f4199a);
        }

        public void applyTheme(Resources.Theme theme) {
        }

        public boolean canApplyTheme() {
            return false;
        }

        public PathParser.PathDataNode[] getPathData() {
            return this.f4199a;
        }

        public String getPathName() {
            return this.f4200b;
        }

        public boolean isClipPath() {
            return false;
        }

        public String nodesToString(PathParser.PathDataNode[] pathDataNodeArr) {
            float[] fArr;
            String str = " ";
            for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
                str = str + pathDataNodeArr[i2].mType + ":";
                for (int i3 = 0; i3 < pathDataNodeArr[i2].mParams.length; i3++) {
                    str = str + fArr[i3] + ",";
                }
            }
            return str;
        }

        public void printVPath(int i2) {
            String str = "";
            for (int i3 = 0; i3 < i2; i3++) {
                str = str + "    ";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("current path is :");
            sb.append(this.f4200b);
            sb.append(" pathData is ");
            sb.append(nodesToString(this.f4199a));
        }

        public void setPathData(PathParser.PathDataNode[] pathDataNodeArr) {
            if (PathParser.canMorph(this.f4199a, pathDataNodeArr)) {
                PathParser.updateNodes(this.f4199a, pathDataNodeArr);
            } else {
                this.f4199a = PathParser.deepCopyNodes(pathDataNodeArr);
            }
        }

        public void toPath(Path path) {
            path.reset();
            PathParser.PathDataNode[] pathDataNodeArr = this.f4199a;
            if (pathDataNodeArr != null) {
                PathParser.PathDataNode.nodesToPath(pathDataNodeArr, path);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class VPathRenderer {
        private static final Matrix IDENTITY_MATRIX = new Matrix();

        /* renamed from: a  reason: collision with root package name */
        Paint f4203a;

        /* renamed from: b  reason: collision with root package name */
        Paint f4204b;

        /* renamed from: c  reason: collision with root package name */
        final VGroup f4205c;

        /* renamed from: d  reason: collision with root package name */
        float f4206d;

        /* renamed from: e  reason: collision with root package name */
        float f4207e;

        /* renamed from: f  reason: collision with root package name */
        float f4208f;

        /* renamed from: g  reason: collision with root package name */
        float f4209g;

        /* renamed from: h  reason: collision with root package name */
        int f4210h;

        /* renamed from: i  reason: collision with root package name */
        String f4211i;

        /* renamed from: j  reason: collision with root package name */
        Boolean f4212j;

        /* renamed from: k  reason: collision with root package name */
        final ArrayMap f4213k;
        private int mChangingConfigurations;
        private final Matrix mFinalPathMatrix;
        private final Path mPath;
        private PathMeasure mPathMeasure;
        private final Path mRenderPath;

        public VPathRenderer() {
            this.mFinalPathMatrix = new Matrix();
            this.f4206d = 0.0f;
            this.f4207e = 0.0f;
            this.f4208f = 0.0f;
            this.f4209g = 0.0f;
            this.f4210h = 255;
            this.f4211i = null;
            this.f4212j = null;
            this.f4213k = new ArrayMap();
            this.f4205c = new VGroup();
            this.mPath = new Path();
            this.mRenderPath = new Path();
        }

        public VPathRenderer(VPathRenderer vPathRenderer) {
            this.mFinalPathMatrix = new Matrix();
            this.f4206d = 0.0f;
            this.f4207e = 0.0f;
            this.f4208f = 0.0f;
            this.f4209g = 0.0f;
            this.f4210h = 255;
            this.f4211i = null;
            this.f4212j = null;
            ArrayMap arrayMap = new ArrayMap();
            this.f4213k = arrayMap;
            this.f4205c = new VGroup(vPathRenderer.f4205c, arrayMap);
            this.mPath = new Path(vPathRenderer.mPath);
            this.mRenderPath = new Path(vPathRenderer.mRenderPath);
            this.f4206d = vPathRenderer.f4206d;
            this.f4207e = vPathRenderer.f4207e;
            this.f4208f = vPathRenderer.f4208f;
            this.f4209g = vPathRenderer.f4209g;
            this.mChangingConfigurations = vPathRenderer.mChangingConfigurations;
            this.f4210h = vPathRenderer.f4210h;
            this.f4211i = vPathRenderer.f4211i;
            String str = vPathRenderer.f4211i;
            if (str != null) {
                arrayMap.put(str, this);
            }
            this.f4212j = vPathRenderer.f4212j;
        }

        private static float cross(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        private void drawGroupTree(VGroup vGroup, Matrix matrix, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            vGroup.f4194a.set(matrix);
            vGroup.f4194a.preConcat(vGroup.f4197d);
            canvas.save();
            for (int i4 = 0; i4 < vGroup.f4195b.size(); i4++) {
                VObject vObject = (VObject) vGroup.f4195b.get(i4);
                if (vObject instanceof VGroup) {
                    drawGroupTree((VGroup) vObject, vGroup.f4194a, canvas, i2, i3, colorFilter);
                } else if (vObject instanceof VPath) {
                    drawPath(vGroup, (VPath) vObject, canvas, i2, i3, colorFilter);
                }
            }
            canvas.restore();
        }

        private void drawPath(VGroup vGroup, VPath vPath, Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            float f2 = i2 / this.f4208f;
            float f3 = i3 / this.f4209g;
            float min = Math.min(f2, f3);
            Matrix matrix = vGroup.f4194a;
            this.mFinalPathMatrix.set(matrix);
            this.mFinalPathMatrix.postScale(f2, f3);
            float matrixScale = getMatrixScale(matrix);
            if (matrixScale == 0.0f) {
                return;
            }
            vPath.toPath(this.mPath);
            Path path = this.mPath;
            this.mRenderPath.reset();
            if (vPath.isClipPath()) {
                this.mRenderPath.setFillType(vPath.f4201c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.mRenderPath.addPath(path, this.mFinalPathMatrix);
                canvas.clipPath(this.mRenderPath);
                return;
            }
            VFullPath vFullPath = (VFullPath) vPath;
            float f4 = vFullPath.f4188j;
            if (f4 != 0.0f || vFullPath.f4189k != 1.0f) {
                float f5 = vFullPath.f4190l;
                float f6 = (f4 + f5) % 1.0f;
                float f7 = (vFullPath.f4189k + f5) % 1.0f;
                if (this.mPathMeasure == null) {
                    this.mPathMeasure = new PathMeasure();
                }
                this.mPathMeasure.setPath(this.mPath, false);
                float length = this.mPathMeasure.getLength();
                float f8 = f6 * length;
                float f9 = f7 * length;
                path.reset();
                if (f8 > f9) {
                    this.mPathMeasure.getSegment(f8, length, path, true);
                    this.mPathMeasure.getSegment(0.0f, f9, path, true);
                } else {
                    this.mPathMeasure.getSegment(f8, f9, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.mRenderPath.addPath(path, this.mFinalPathMatrix);
            if (vFullPath.f4185g.willDraw()) {
                ComplexColorCompat complexColorCompat = vFullPath.f4185g;
                if (this.f4204b == null) {
                    Paint paint = new Paint(1);
                    this.f4204b = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.f4204b;
                if (complexColorCompat.isGradient()) {
                    Shader shader = complexColorCompat.getShader();
                    shader.setLocalMatrix(this.mFinalPathMatrix);
                    paint2.setShader(shader);
                    paint2.setAlpha(Math.round(vFullPath.f4187i * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(VectorDrawableCompat.a(complexColorCompat.getColor(), vFullPath.f4187i));
                }
                paint2.setColorFilter(colorFilter);
                this.mRenderPath.setFillType(vFullPath.f4201c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.mRenderPath, paint2);
            }
            if (vFullPath.f4183e.willDraw()) {
                ComplexColorCompat complexColorCompat2 = vFullPath.f4183e;
                if (this.f4203a == null) {
                    Paint paint3 = new Paint(1);
                    this.f4203a = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.f4203a;
                Paint.Join join = vFullPath.f4192n;
                if (join != null) {
                    paint4.setStrokeJoin(join);
                }
                Paint.Cap cap = vFullPath.f4191m;
                if (cap != null) {
                    paint4.setStrokeCap(cap);
                }
                paint4.setStrokeMiter(vFullPath.f4193o);
                if (complexColorCompat2.isGradient()) {
                    Shader shader2 = complexColorCompat2.getShader();
                    shader2.setLocalMatrix(this.mFinalPathMatrix);
                    paint4.setShader(shader2);
                    paint4.setAlpha(Math.round(vFullPath.f4186h * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(VectorDrawableCompat.a(complexColorCompat2.getColor(), vFullPath.f4186h));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(vFullPath.f4184f * min * matrixScale);
                canvas.drawPath(this.mRenderPath, paint4);
            }
        }

        private float getMatrixScale(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float cross = cross(fArr[0], fArr[1], fArr[2], fArr[3]);
            float max = Math.max((float) Math.hypot(fArr[0], fArr[1]), (float) Math.hypot(fArr[2], fArr[3]));
            if (max > 0.0f) {
                return Math.abs(cross) / max;
            }
            return 0.0f;
        }

        public void draw(Canvas canvas, int i2, int i3, ColorFilter colorFilter) {
            drawGroupTree(this.f4205c, IDENTITY_MATRIX, canvas, i2, i3, colorFilter);
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.f4210h;
        }

        public boolean isStateful() {
            if (this.f4212j == null) {
                this.f4212j = Boolean.valueOf(this.f4205c.isStateful());
            }
            return this.f4212j.booleanValue();
        }

        public boolean onStateChanged(int[] iArr) {
            return this.f4205c.onStateChanged(iArr);
        }

        public void setAlpha(float f2) {
            setRootAlpha((int) (f2 * 255.0f));
        }

        public void setRootAlpha(int i2) {
            this.f4210h = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class VectorDrawableCompatState extends Drawable.ConstantState {

        /* renamed from: a  reason: collision with root package name */
        int f4214a;

        /* renamed from: b  reason: collision with root package name */
        VPathRenderer f4215b;

        /* renamed from: c  reason: collision with root package name */
        ColorStateList f4216c;

        /* renamed from: d  reason: collision with root package name */
        PorterDuff.Mode f4217d;

        /* renamed from: e  reason: collision with root package name */
        boolean f4218e;

        /* renamed from: f  reason: collision with root package name */
        Bitmap f4219f;

        /* renamed from: g  reason: collision with root package name */
        ColorStateList f4220g;

        /* renamed from: h  reason: collision with root package name */
        PorterDuff.Mode f4221h;

        /* renamed from: i  reason: collision with root package name */
        int f4222i;

        /* renamed from: j  reason: collision with root package name */
        boolean f4223j;

        /* renamed from: k  reason: collision with root package name */
        boolean f4224k;

        /* renamed from: l  reason: collision with root package name */
        Paint f4225l;

        public VectorDrawableCompatState() {
            this.f4216c = null;
            this.f4217d = VectorDrawableCompat.f4182b;
            this.f4215b = new VPathRenderer();
        }

        public VectorDrawableCompatState(VectorDrawableCompatState vectorDrawableCompatState) {
            this.f4216c = null;
            this.f4217d = VectorDrawableCompat.f4182b;
            if (vectorDrawableCompatState != null) {
                this.f4214a = vectorDrawableCompatState.f4214a;
                VPathRenderer vPathRenderer = new VPathRenderer(vectorDrawableCompatState.f4215b);
                this.f4215b = vPathRenderer;
                if (vectorDrawableCompatState.f4215b.f4204b != null) {
                    vPathRenderer.f4204b = new Paint(vectorDrawableCompatState.f4215b.f4204b);
                }
                if (vectorDrawableCompatState.f4215b.f4203a != null) {
                    this.f4215b.f4203a = new Paint(vectorDrawableCompatState.f4215b.f4203a);
                }
                this.f4216c = vectorDrawableCompatState.f4216c;
                this.f4217d = vectorDrawableCompatState.f4217d;
                this.f4218e = vectorDrawableCompatState.f4218e;
            }
        }

        public boolean canReuseBitmap(int i2, int i3) {
            return i2 == this.f4219f.getWidth() && i3 == this.f4219f.getHeight();
        }

        public boolean canReuseCache() {
            return !this.f4224k && this.f4220g == this.f4216c && this.f4221h == this.f4217d && this.f4223j == this.f4218e && this.f4222i == this.f4215b.getRootAlpha();
        }

        public void createCachedBitmapIfNeeded(int i2, int i3) {
            if (this.f4219f == null || !canReuseBitmap(i2, i3)) {
                this.f4219f = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                this.f4224k = true;
            }
        }

        public void drawCachedBitmapWithRootAlpha(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f4219f, (Rect) null, rect, getPaint(colorFilter));
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f4214a;
        }

        public Paint getPaint(ColorFilter colorFilter) {
            if (hasTranslucentRoot() || colorFilter != null) {
                if (this.f4225l == null) {
                    Paint paint = new Paint();
                    this.f4225l = paint;
                    paint.setFilterBitmap(true);
                }
                this.f4225l.setAlpha(this.f4215b.getRootAlpha());
                this.f4225l.setColorFilter(colorFilter);
                return this.f4225l;
            }
            return null;
        }

        public boolean hasTranslucentRoot() {
            return this.f4215b.getRootAlpha() < 255;
        }

        public boolean isStateful() {
            return this.f4215b.isStateful();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable(Resources resources) {
            return new VectorDrawableCompat(this);
        }

        public boolean onStateChanged(int[] iArr) {
            boolean onStateChanged = this.f4215b.onStateChanged(iArr);
            this.f4224k |= onStateChanged;
            return onStateChanged;
        }

        public void updateCacheStates() {
            this.f4220g = this.f4216c;
            this.f4221h = this.f4217d;
            this.f4222i = this.f4215b.getRootAlpha();
            this.f4223j = this.f4218e;
            this.f4224k = false;
        }

        public void updateCachedBitmap(int i2, int i3) {
            this.f4219f.eraseColor(0);
            this.f4215b.draw(new Canvas(this.f4219f), i2, i3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(24)
    /* loaded from: classes.dex */
    public static class VectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public VectorDrawableDelegateState(Drawable.ConstantState constantState) {
            this.mDelegateState = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.f4181a = (VectorDrawable) this.mDelegateState.newDrawable();
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.f4181a = (VectorDrawable) this.mDelegateState.newDrawable(resources);
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.f4181a = (VectorDrawable) this.mDelegateState.newDrawable(resources, theme);
            return vectorDrawableCompat;
        }
    }

    VectorDrawableCompat() {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = new VectorDrawableCompatState();
    }

    VectorDrawableCompat(@NonNull VectorDrawableCompatState vectorDrawableCompatState) {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = vectorDrawableCompatState;
        this.mTintFilter = d(this.mTintFilter, vectorDrawableCompatState.f4216c, vectorDrawableCompatState.f4217d);
    }

    static int a(int i2, float f2) {
        return (i2 & 16777215) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    @Nullable
    public static VectorDrawableCompat create(@NonNull Resources resources, @DrawableRes int i2, @Nullable Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.f4181a = ResourcesCompat.getDrawable(resources, i2, theme);
            vectorDrawableCompat.mCachedConstantStateDelegate = new VectorDrawableDelegateState(vectorDrawableCompat.f4181a.getConstantState());
            return vectorDrawableCompat;
        }
        try {
            XmlResourceParser xml = resources.getXml(i2);
            AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
            while (true) {
                next = xml.next();
                if (next == 2 || next == 1) {
                    break;
                }
            }
            if (next == 2) {
                return createFromXmlInner(resources, (XmlPullParser) xml, asAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException | XmlPullParserException e2) {
            Log.e("VectorDrawableCompat", "parser error", e2);
            return null;
        }
    }

    public static VectorDrawableCompat createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
        vectorDrawableCompat.inflate(resources, xmlPullParser, attributeSet, theme);
        return vectorDrawableCompat;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void inflateInternal(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        int i2;
        int i3;
        VClipPath vClipPath;
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        VPathRenderer vPathRenderer = vectorDrawableCompatState.f4215b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(vPathRenderer.f4205c);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                VGroup vGroup = (VGroup) arrayDeque.peek();
                if ("path".equals(name)) {
                    VFullPath vFullPath = new VFullPath();
                    vFullPath.inflate(resources, attributeSet, theme, xmlPullParser);
                    vGroup.f4195b.add(vFullPath);
                    if (vFullPath.getPathName() != null) {
                        vPathRenderer.f4213k.put(vFullPath.getPathName(), vFullPath);
                    }
                    z = false;
                    vClipPath = vFullPath;
                } else if (SHAPE_CLIP_PATH.equals(name)) {
                    VClipPath vClipPath2 = new VClipPath();
                    vClipPath2.inflate(resources, attributeSet, theme, xmlPullParser);
                    vGroup.f4195b.add(vClipPath2);
                    String pathName = vClipPath2.getPathName();
                    vClipPath = vClipPath2;
                    if (pathName != null) {
                        vPathRenderer.f4213k.put(vClipPath2.getPathName(), vClipPath2);
                        vClipPath = vClipPath2;
                    }
                } else if (SHAPE_GROUP.equals(name)) {
                    VGroup vGroup2 = new VGroup();
                    vGroup2.inflate(resources, attributeSet, theme, xmlPullParser);
                    vGroup.f4195b.add(vGroup2);
                    arrayDeque.push(vGroup2);
                    if (vGroup2.getGroupName() != null) {
                        vPathRenderer.f4213k.put(vGroup2.getGroupName(), vGroup2);
                    }
                    i2 = vectorDrawableCompatState.f4214a;
                    i3 = vGroup2.f4198e;
                    vectorDrawableCompatState.f4214a = i3 | i2;
                }
                i2 = vectorDrawableCompatState.f4214a;
                i3 = vClipPath.f4202d;
                vectorDrawableCompatState.f4214a = i3 | i2;
            } else if (eventType == 3 && SHAPE_GROUP.equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    private boolean needMirroring() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && DrawableCompat.getLayoutDirection(this) == 1;
    }

    private static PorterDuff.Mode parseTintModeCompat(int i2, PorterDuff.Mode mode) {
        if (i2 != 3) {
            if (i2 != 5) {
                if (i2 != 9) {
                    switch (i2) {
                        case 14:
                            return PorterDuff.Mode.MULTIPLY;
                        case 15:
                            return PorterDuff.Mode.SCREEN;
                        case 16:
                            return PorterDuff.Mode.ADD;
                        default:
                            return mode;
                    }
                }
                return PorterDuff.Mode.SRC_ATOP;
            }
            return PorterDuff.Mode.SRC_IN;
        }
        return PorterDuff.Mode.SRC_OVER;
    }

    private void printGroupTree(VGroup vGroup, int i2) {
        String str = "";
        for (int i3 = 0; i3 < i2; i3++) {
            str = str + "    ";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("current group is :");
        sb.append(vGroup.getGroupName());
        sb.append(" rotation is ");
        sb.append(vGroup.f4196c);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("matrix is :");
        sb2.append(vGroup.getLocalMatrix().toString());
        for (int i4 = 0; i4 < vGroup.f4195b.size(); i4++) {
            VObject vObject = (VObject) vGroup.f4195b.get(i4);
            if (vObject instanceof VGroup) {
                printGroupTree((VGroup) vObject, i2 + 1);
            } else {
                ((VPath) vObject).printVPath(i2 + 1);
            }
        }
    }

    private void updateStateFromTypedArray(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        VPathRenderer vPathRenderer = vectorDrawableCompatState.f4215b;
        vectorDrawableCompatState.f4217d = parseTintModeCompat(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList namedColorStateList = TypedArrayUtils.getNamedColorStateList(typedArray, xmlPullParser, theme, "tint", 1);
        if (namedColorStateList != null) {
            vectorDrawableCompatState.f4216c = namedColorStateList;
        }
        vectorDrawableCompatState.f4218e = TypedArrayUtils.getNamedBoolean(typedArray, xmlPullParser, "autoMirrored", 5, vectorDrawableCompatState.f4218e);
        vPathRenderer.f4208f = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "viewportWidth", 7, vPathRenderer.f4208f);
        float namedFloat = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "viewportHeight", 8, vPathRenderer.f4209g);
        vPathRenderer.f4209g = namedFloat;
        if (vPathRenderer.f4208f <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        } else if (namedFloat <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        } else {
            vPathRenderer.f4206d = typedArray.getDimension(3, vPathRenderer.f4206d);
            float dimension = typedArray.getDimension(2, vPathRenderer.f4207e);
            vPathRenderer.f4207e = dimension;
            if (vPathRenderer.f4206d <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
            } else if (dimension <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
            } else {
                vPathRenderer.setAlpha(TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "alpha", 4, vPathRenderer.getAlpha()));
                String string = typedArray.getString(0);
                if (string != null) {
                    vPathRenderer.f4211i = string;
                    vPathRenderer.f4213k.put(string, vPathRenderer);
                }
            }
        }
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object b(String str) {
        return this.mVectorState.f4215b.f4213k.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(boolean z) {
        this.mAllowCaching = z;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            DrawableCompat.canApplyTheme(drawable);
            return false;
        }
        return false;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    PorterDuffColorFilter d(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.mTmpBounds);
        if (this.mTmpBounds.width() <= 0 || this.mTmpBounds.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.mColorFilter;
        if (colorFilter == null) {
            colorFilter = this.mTintFilter;
        }
        canvas.getMatrix(this.mTmpMatrix);
        this.mTmpMatrix.getValues(this.mTmpFloats);
        float abs = Math.abs(this.mTmpFloats[0]);
        float abs2 = Math.abs(this.mTmpFloats[4]);
        float abs3 = Math.abs(this.mTmpFloats[1]);
        float abs4 = Math.abs(this.mTmpFloats[3]);
        if (abs3 != 0.0f || abs4 != 0.0f) {
            abs = 1.0f;
            abs2 = 1.0f;
        }
        int min = Math.min(2048, (int) (this.mTmpBounds.width() * abs));
        int min2 = Math.min(2048, (int) (this.mTmpBounds.height() * abs2));
        if (min <= 0 || min2 <= 0) {
            return;
        }
        int save = canvas.save();
        Rect rect = this.mTmpBounds;
        canvas.translate(rect.left, rect.top);
        if (needMirroring()) {
            canvas.translate(this.mTmpBounds.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.mTmpBounds.offsetTo(0, 0);
        this.mVectorState.createCachedBitmapIfNeeded(min, min2);
        if (!this.mAllowCaching) {
            this.mVectorState.updateCachedBitmap(min, min2);
        } else if (!this.mVectorState.canReuseCache()) {
            this.mVectorState.updateCachedBitmap(min, min2);
            this.mVectorState.updateCacheStates();
        }
        this.mVectorState.drawCachedBitmapWithRootAlpha(canvas, colorFilter, this.mTmpBounds);
        canvas.restoreToCount(save);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f4181a;
        return drawable != null ? DrawableCompat.getAlpha(drawable) : this.mVectorState.f4215b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f4181a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f4181a;
        return drawable != null ? DrawableCompat.getColorFilter(drawable) : this.mColorFilter;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.f4181a == null || Build.VERSION.SDK_INT < 24) {
            this.mVectorState.f4214a = getChangingConfigurations();
            return this.mVectorState;
        }
        return new VectorDrawableDelegateState(this.f4181a.getConstantState());
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f4181a;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.mVectorState.f4215b.f4207e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f4181a;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.mVectorState.f4215b.f4206d;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public float getPixelSize() {
        VPathRenderer vPathRenderer;
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState == null || (vPathRenderer = vectorDrawableCompatState.f4215b) == null) {
            return 1.0f;
        }
        float f2 = vPathRenderer.f4206d;
        if (f2 != 0.0f) {
            float f3 = vPathRenderer.f4207e;
            if (f3 != 0.0f) {
                float f4 = vPathRenderer.f4209g;
                if (f4 != 0.0f) {
                    float f5 = vPathRenderer.f4208f;
                    if (f5 == 0.0f) {
                        return 1.0f;
                    }
                    return Math.min(f5 / f2, f4 / f3);
                }
                return 1.0f;
            }
            return 1.0f;
        }
        return 1.0f;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            DrawableCompat.inflate(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        vectorDrawableCompatState.f4215b = new VPathRenderer();
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.f4164a);
        updateStateFromTypedArray(obtainAttributes, xmlPullParser, theme);
        obtainAttributes.recycle();
        vectorDrawableCompatState.f4214a = getChangingConfigurations();
        vectorDrawableCompatState.f4224k = true;
        inflateInternal(resources, xmlPullParser, attributeSet, theme);
        this.mTintFilter = d(this.mTintFilter, vectorDrawableCompatState.f4216c, vectorDrawableCompatState.f4217d);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f4181a;
        return drawable != null ? DrawableCompat.isAutoMirrored(drawable) : this.mVectorState.f4218e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        VectorDrawableCompatState vectorDrawableCompatState;
        ColorStateList colorStateList;
        Drawable drawable = this.f4181a;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((vectorDrawableCompatState = this.mVectorState) != null && (vectorDrawableCompatState.isStateful() || ((colorStateList = this.mVectorState.f4216c) != null && colorStateList.isStateful())));
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.mMutated && super.mutate() == this) {
            this.mVectorState = new VectorDrawableCompatState(this.mVectorState);
            this.mMutated = true;
        }
        return this;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        ColorStateList colorStateList = vectorDrawableCompatState.f4216c;
        if (colorStateList != null && (mode = vectorDrawableCompatState.f4217d) != null) {
            this.mTintFilter = d(this.mTintFilter, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (vectorDrawableCompatState.isStateful() && vectorDrawableCompatState.onStateChanged(iArr)) {
            invalidateSelf();
            return true;
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j2) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j2);
        } else {
            super.scheduleSelf(runnable, j2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.mVectorState.f4215b.getRootAlpha() != i2) {
            this.mVectorState.f4215b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            DrawableCompat.setAutoMirrored(drawable, z);
        } else {
            this.mVectorState.f4218e = z;
        }
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i2) {
        super.setChangingConfigurations(i2);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i2, PorterDuff.Mode mode) {
        super.setColorFilter(i2, mode);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
            return;
        }
        this.mColorFilter = colorFilter;
        invalidateSelf();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i2, int i3, int i4, int i5) {
        super.setHotspotBounds(i2, i3, i4, i5);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(int i2) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            DrawableCompat.setTint(drawable, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            DrawableCompat.setTintList(drawable, colorStateList);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState.f4216c != colorStateList) {
            vectorDrawableCompatState.f4216c = colorStateList;
            this.mTintFilter = d(this.mTintFilter, colorStateList, vectorDrawableCompatState.f4217d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            DrawableCompat.setTintMode(drawable, mode);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState.f4217d != mode) {
            vectorDrawableCompatState.f4217d = mode;
            this.mTintFilter = d(this.mTintFilter, vectorDrawableCompatState.f4216c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f4181a;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f4181a;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }
}
