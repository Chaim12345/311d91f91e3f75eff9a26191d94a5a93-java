package androidx.appcompat.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.DrawableCompat;
import org.bouncycastle.tls.CipherSuite;
/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class DrawableContainer extends Drawable implements Drawable.Callback {
    private static final boolean DEBUG = false;
    private static final boolean DEFAULT_DITHER = true;
    private static final String TAG = "DrawableContainer";
    private Runnable mAnimationRunnable;
    private BlockInvalidateCallback mBlockInvalidateCallback;
    private Drawable mCurrDrawable;
    private DrawableContainerState mDrawableContainerState;
    private long mEnterAnimationEnd;
    private long mExitAnimationEnd;
    private boolean mHasAlpha;
    private Rect mHotspotBounds;
    private Drawable mLastDrawable;
    private boolean mMutated;
    private int mAlpha = 255;
    private int mCurIndex = -1;

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class Api21Impl {
        private Api21Impl() {
        }

        public static boolean canApplyTheme(Drawable.ConstantState constantState) {
            return constantState.canApplyTheme();
        }

        public static void getOutline(Drawable drawable, Outline outline) {
            drawable.getOutline(outline);
        }

        public static Resources getResources(Resources.Theme theme) {
            return theme.getResources();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class BlockInvalidateCallback implements Drawable.Callback {
        private Drawable.Callback mCallback;

        BlockInvalidateCallback() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(@NonNull Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j2) {
            Drawable.Callback callback = this.mCallback;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j2);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
            Drawable.Callback callback = this.mCallback;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }

        public Drawable.Callback unwrap() {
            Drawable.Callback callback = this.mCallback;
            this.mCallback = null;
            return callback;
        }

        public BlockInvalidateCallback wrap(Drawable.Callback callback) {
            this.mCallback = callback;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class DrawableContainerState extends Drawable.ConstantState {
        int A;
        int B;
        boolean C;
        ColorFilter D;
        boolean E;
        ColorStateList F;
        PorterDuff.Mode G;
        boolean H;
        boolean I;

        /* renamed from: a  reason: collision with root package name */
        final DrawableContainer f344a;

        /* renamed from: b  reason: collision with root package name */
        Resources f345b;

        /* renamed from: c  reason: collision with root package name */
        int f346c;

        /* renamed from: d  reason: collision with root package name */
        int f347d;

        /* renamed from: e  reason: collision with root package name */
        int f348e;

        /* renamed from: f  reason: collision with root package name */
        SparseArray f349f;

        /* renamed from: g  reason: collision with root package name */
        Drawable[] f350g;

        /* renamed from: h  reason: collision with root package name */
        int f351h;

        /* renamed from: i  reason: collision with root package name */
        boolean f352i;

        /* renamed from: j  reason: collision with root package name */
        boolean f353j;

        /* renamed from: k  reason: collision with root package name */
        Rect f354k;

        /* renamed from: l  reason: collision with root package name */
        boolean f355l;

        /* renamed from: m  reason: collision with root package name */
        boolean f356m;

        /* renamed from: n  reason: collision with root package name */
        int f357n;

        /* renamed from: o  reason: collision with root package name */
        int f358o;

        /* renamed from: p  reason: collision with root package name */
        int f359p;

        /* renamed from: q  reason: collision with root package name */
        int f360q;

        /* renamed from: r  reason: collision with root package name */
        boolean f361r;

        /* renamed from: s  reason: collision with root package name */
        int f362s;

        /* renamed from: t  reason: collision with root package name */
        boolean f363t;
        boolean u;
        boolean v;
        boolean w;
        boolean x;
        boolean y;
        int z;

        /* JADX INFO: Access modifiers changed from: package-private */
        public DrawableContainerState(DrawableContainerState drawableContainerState, DrawableContainer drawableContainer, Resources resources) {
            this.f352i = false;
            this.f355l = false;
            this.x = true;
            this.A = 0;
            this.B = 0;
            this.f344a = drawableContainer;
            this.f345b = resources != null ? resources : drawableContainerState != null ? drawableContainerState.f345b : null;
            int d2 = DrawableContainer.d(resources, drawableContainerState != null ? drawableContainerState.f346c : 0);
            this.f346c = d2;
            if (drawableContainerState == null) {
                this.f350g = new Drawable[10];
                this.f351h = 0;
                return;
            }
            this.f347d = drawableContainerState.f347d;
            this.f348e = drawableContainerState.f348e;
            this.v = true;
            this.w = true;
            this.f352i = drawableContainerState.f352i;
            this.f355l = drawableContainerState.f355l;
            this.x = drawableContainerState.x;
            this.y = drawableContainerState.y;
            this.z = drawableContainerState.z;
            this.A = drawableContainerState.A;
            this.B = drawableContainerState.B;
            this.C = drawableContainerState.C;
            this.D = drawableContainerState.D;
            this.E = drawableContainerState.E;
            this.F = drawableContainerState.F;
            this.G = drawableContainerState.G;
            this.H = drawableContainerState.H;
            this.I = drawableContainerState.I;
            if (drawableContainerState.f346c == d2) {
                if (drawableContainerState.f353j) {
                    this.f354k = drawableContainerState.f354k != null ? new Rect(drawableContainerState.f354k) : null;
                    this.f353j = true;
                }
                if (drawableContainerState.f356m) {
                    this.f357n = drawableContainerState.f357n;
                    this.f358o = drawableContainerState.f358o;
                    this.f359p = drawableContainerState.f359p;
                    this.f360q = drawableContainerState.f360q;
                    this.f356m = true;
                }
            }
            if (drawableContainerState.f361r) {
                this.f362s = drawableContainerState.f362s;
                this.f361r = true;
            }
            if (drawableContainerState.f363t) {
                this.u = drawableContainerState.u;
                this.f363t = true;
            }
            Drawable[] drawableArr = drawableContainerState.f350g;
            this.f350g = new Drawable[drawableArr.length];
            this.f351h = drawableContainerState.f351h;
            SparseArray sparseArray = drawableContainerState.f349f;
            this.f349f = sparseArray != null ? sparseArray.clone() : new SparseArray(this.f351h);
            int i2 = this.f351h;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3] != null) {
                    Drawable.ConstantState constantState = drawableArr[i3].getConstantState();
                    if (constantState != null) {
                        this.f349f.put(i3, constantState);
                    } else {
                        this.f350g[i3] = drawableArr[i3];
                    }
                }
            }
        }

        private void createAllFutures() {
            SparseArray sparseArray = this.f349f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.f350g[this.f349f.keyAt(i2)] = prepareDrawable(((Drawable.ConstantState) this.f349f.valueAt(i2)).newDrawable(this.f345b));
                }
                this.f349f = null;
            }
        }

        private Drawable prepareDrawable(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= 23) {
                DrawableCompat.setLayoutDirection(drawable, this.z);
            }
            Drawable mutate = drawable.mutate();
            mutate.setCallback(this.f344a);
            return mutate;
        }

        @RequiresApi(21)
        final void a(Resources.Theme theme) {
            if (theme != null) {
                createAllFutures();
                int i2 = this.f351h;
                Drawable[] drawableArr = this.f350g;
                for (int i3 = 0; i3 < i2; i3++) {
                    if (drawableArr[i3] != null && DrawableCompat.canApplyTheme(drawableArr[i3])) {
                        DrawableCompat.applyTheme(drawableArr[i3], theme);
                        this.f348e |= drawableArr[i3].getChangingConfigurations();
                    }
                }
                g(Api21Impl.getResources(theme));
            }
        }

        public final int addChild(Drawable drawable) {
            int i2 = this.f351h;
            if (i2 >= this.f350g.length) {
                growArray(i2, i2 + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.f344a);
            this.f350g[i2] = drawable;
            this.f351h++;
            this.f348e = drawable.getChangingConfigurations() | this.f348e;
            d();
            this.f354k = null;
            this.f353j = false;
            this.f356m = false;
            this.v = false;
            return i2;
        }

        protected void b() {
            this.f356m = true;
            createAllFutures();
            int i2 = this.f351h;
            Drawable[] drawableArr = this.f350g;
            this.f358o = -1;
            this.f357n = -1;
            this.f360q = 0;
            this.f359p = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.f357n) {
                    this.f357n = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.f358o) {
                    this.f358o = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.f359p) {
                    this.f359p = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.f360q) {
                    this.f360q = minimumHeight;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final int c() {
            return this.f350g.length;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @RequiresApi(21)
        public boolean canApplyTheme() {
            int i2 = this.f351h;
            Drawable[] drawableArr = this.f350g;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                if (drawable == null) {
                    Drawable.ConstantState constantState = (Drawable.ConstantState) this.f349f.get(i3);
                    if (constantState != null && Api21Impl.canApplyTheme(constantState)) {
                        return true;
                    }
                } else if (DrawableCompat.canApplyTheme(drawable)) {
                    return true;
                }
            }
            return false;
        }

        public boolean canConstantState() {
            if (this.v) {
                return this.w;
            }
            createAllFutures();
            this.v = true;
            int i2 = this.f351h;
            Drawable[] drawableArr = this.f350g;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3].getConstantState() == null) {
                    this.w = false;
                    return false;
                }
            }
            this.w = true;
            return true;
        }

        void d() {
            this.f361r = false;
            this.f363t = false;
        }

        void e() {
            int i2 = this.f351h;
            Drawable[] drawableArr = this.f350g;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3] != null) {
                    drawableArr[i3].mutate();
                }
            }
            this.y = true;
        }

        final boolean f(int i2, int i3) {
            int i4 = this.f351h;
            Drawable[] drawableArr = this.f350g;
            boolean z = false;
            for (int i5 = 0; i5 < i4; i5++) {
                if (drawableArr[i5] != null) {
                    boolean layoutDirection = Build.VERSION.SDK_INT >= 23 ? DrawableCompat.setLayoutDirection(drawableArr[i5], i2) : false;
                    if (i5 == i3) {
                        z = layoutDirection;
                    }
                }
            }
            this.z = i2;
            return z;
        }

        final void g(Resources resources) {
            if (resources != null) {
                this.f345b = resources;
                int d2 = DrawableContainer.d(resources, this.f346c);
                int i2 = this.f346c;
                this.f346c = d2;
                if (i2 != d2) {
                    this.f356m = false;
                    this.f353j = false;
                }
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f347d | this.f348e;
        }

        public final Drawable getChild(int i2) {
            int indexOfKey;
            Drawable drawable = this.f350g[i2];
            if (drawable != null) {
                return drawable;
            }
            SparseArray sparseArray = this.f349f;
            if (sparseArray == null || (indexOfKey = sparseArray.indexOfKey(i2)) < 0) {
                return null;
            }
            Drawable prepareDrawable = prepareDrawable(((Drawable.ConstantState) this.f349f.valueAt(indexOfKey)).newDrawable(this.f345b));
            this.f350g[i2] = prepareDrawable;
            this.f349f.removeAt(indexOfKey);
            if (this.f349f.size() == 0) {
                this.f349f = null;
            }
            return prepareDrawable;
        }

        public final int getChildCount() {
            return this.f351h;
        }

        public final int getConstantHeight() {
            if (!this.f356m) {
                b();
            }
            return this.f358o;
        }

        public final int getConstantMinimumHeight() {
            if (!this.f356m) {
                b();
            }
            return this.f360q;
        }

        public final int getConstantMinimumWidth() {
            if (!this.f356m) {
                b();
            }
            return this.f359p;
        }

        public final Rect getConstantPadding() {
            Rect rect = null;
            if (this.f352i) {
                return null;
            }
            Rect rect2 = this.f354k;
            if (rect2 != null || this.f353j) {
                return rect2;
            }
            createAllFutures();
            Rect rect3 = new Rect();
            int i2 = this.f351h;
            Drawable[] drawableArr = this.f350g;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3].getPadding(rect3)) {
                    if (rect == null) {
                        rect = new Rect(0, 0, 0, 0);
                    }
                    int i4 = rect3.left;
                    if (i4 > rect.left) {
                        rect.left = i4;
                    }
                    int i5 = rect3.top;
                    if (i5 > rect.top) {
                        rect.top = i5;
                    }
                    int i6 = rect3.right;
                    if (i6 > rect.right) {
                        rect.right = i6;
                    }
                    int i7 = rect3.bottom;
                    if (i7 > rect.bottom) {
                        rect.bottom = i7;
                    }
                }
            }
            this.f353j = true;
            this.f354k = rect;
            return rect;
        }

        public final int getConstantWidth() {
            if (!this.f356m) {
                b();
            }
            return this.f357n;
        }

        public final int getEnterFadeDuration() {
            return this.A;
        }

        public final int getExitFadeDuration() {
            return this.B;
        }

        public final int getOpacity() {
            if (this.f361r) {
                return this.f362s;
            }
            createAllFutures();
            int i2 = this.f351h;
            Drawable[] drawableArr = this.f350g;
            int opacity = i2 > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i3 = 1; i3 < i2; i3++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i3].getOpacity());
            }
            this.f362s = opacity;
            this.f361r = true;
            return opacity;
        }

        public void growArray(int i2, int i3) {
            Drawable[] drawableArr = new Drawable[i3];
            Drawable[] drawableArr2 = this.f350g;
            if (drawableArr2 != null) {
                System.arraycopy(drawableArr2, 0, drawableArr, 0, i2);
            }
            this.f350g = drawableArr;
        }

        public final boolean isConstantSize() {
            return this.f355l;
        }

        public final boolean isStateful() {
            if (this.f363t) {
                return this.u;
            }
            createAllFutures();
            int i2 = this.f351h;
            Drawable[] drawableArr = this.f350g;
            boolean z = false;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                } else if (drawableArr[i3].isStateful()) {
                    z = true;
                    break;
                } else {
                    i3++;
                }
            }
            this.u = z;
            this.f363t = true;
            return z;
        }

        public final void setConstantSize(boolean z) {
            this.f355l = z;
        }

        public final void setEnterFadeDuration(int i2) {
            this.A = i2;
        }

        public final void setExitFadeDuration(int i2) {
            this.B = i2;
        }

        public final void setVariablePadding(boolean z) {
            this.f352i = z;
        }
    }

    static int d(@Nullable Resources resources, int i2) {
        if (resources != null) {
            i2 = resources.getDisplayMetrics().densityDpi;
        }
        return i2 == 0 ? CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256 : i2;
    }

    private void initializeDrawableForDisplay(Drawable drawable) {
        if (this.mBlockInvalidateCallback == null) {
            this.mBlockInvalidateCallback = new BlockInvalidateCallback();
        }
        drawable.setCallback(this.mBlockInvalidateCallback.wrap(drawable.getCallback()));
        try {
            if (this.mDrawableContainerState.A <= 0 && this.mHasAlpha) {
                drawable.setAlpha(this.mAlpha);
            }
            DrawableContainerState drawableContainerState = this.mDrawableContainerState;
            if (drawableContainerState.E) {
                drawable.setColorFilter(drawableContainerState.D);
            } else {
                if (drawableContainerState.H) {
                    DrawableCompat.setTintList(drawable, drawableContainerState.F);
                }
                DrawableContainerState drawableContainerState2 = this.mDrawableContainerState;
                if (drawableContainerState2.I) {
                    DrawableCompat.setTintMode(drawable, drawableContainerState2.G);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.mDrawableContainerState.x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 23) {
                DrawableCompat.setLayoutDirection(drawable, DrawableCompat.getLayoutDirection(this));
            }
            if (i2 >= 19) {
                DrawableCompat.setAutoMirrored(drawable, this.mDrawableContainerState.C);
            }
            Rect rect = this.mHotspotBounds;
            if (i2 >= 21 && rect != null) {
                DrawableCompat.setHotspotBounds(drawable, rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.mBlockInvalidateCallback.unwrap());
        }
    }

    private boolean needsMirroring() {
        return isAutoMirrored() && DrawableCompat.getLayoutDirection(this) == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0062 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void a(boolean z) {
        boolean z2;
        Drawable drawable;
        boolean z3 = true;
        this.mHasAlpha = true;
        long uptimeMillis = SystemClock.uptimeMillis();
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            long j2 = this.mEnterAnimationEnd;
            if (j2 != 0) {
                if (j2 <= uptimeMillis) {
                    drawable2.setAlpha(this.mAlpha);
                } else {
                    drawable2.setAlpha(((255 - (((int) ((j2 - uptimeMillis) * 255)) / this.mDrawableContainerState.A)) * this.mAlpha) / 255);
                    z2 = true;
                    drawable = this.mLastDrawable;
                    if (drawable != null) {
                        long j3 = this.mExitAnimationEnd;
                        if (j3 != 0) {
                            if (j3 > uptimeMillis) {
                                drawable.setAlpha(((((int) ((j3 - uptimeMillis) * 255)) / this.mDrawableContainerState.B) * this.mAlpha) / 255);
                                if (z && z3) {
                                    scheduleSelf(this.mAnimationRunnable, uptimeMillis + 16);
                                    return;
                                }
                                return;
                            }
                            drawable.setVisible(false, false);
                            this.mLastDrawable = null;
                        }
                        z3 = z2;
                        if (z) {
                            return;
                        }
                        return;
                    }
                    this.mExitAnimationEnd = 0L;
                    z3 = z2;
                    if (z) {
                    }
                }
            }
            z2 = false;
            drawable = this.mLastDrawable;
            if (drawable != null) {
            }
            this.mExitAnimationEnd = 0L;
            z3 = z2;
            if (z) {
            }
        }
        this.mEnterAnimationEnd = 0L;
        z2 = false;
        drawable = this.mLastDrawable;
        if (drawable != null) {
        }
        this.mExitAnimationEnd = 0L;
        z3 = z2;
        if (z) {
        }
    }

    @Override // android.graphics.drawable.Drawable
    @RequiresApi(21)
    public void applyTheme(@NonNull Resources.Theme theme) {
        this.mDrawableContainerState.a(theme);
    }

    DrawableContainerState b() {
        return this.mDrawableContainerState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.mCurIndex;
    }

    @Override // android.graphics.drawable.Drawable
    @RequiresApi(21)
    public boolean canApplyTheme() {
        return this.mDrawableContainerState.canApplyTheme();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.mLastDrawable;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean e(int i2) {
        Runnable runnable;
        if (i2 == this.mCurIndex) {
            return false;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.mDrawableContainerState.B > 0) {
            Drawable drawable = this.mLastDrawable;
            if (drawable != null) {
                drawable.setVisible(false, false);
            }
            Drawable drawable2 = this.mCurrDrawable;
            if (drawable2 != null) {
                this.mLastDrawable = drawable2;
                this.mExitAnimationEnd = this.mDrawableContainerState.B + uptimeMillis;
            } else {
                this.mLastDrawable = null;
                this.mExitAnimationEnd = 0L;
            }
        } else {
            Drawable drawable3 = this.mCurrDrawable;
            if (drawable3 != null) {
                drawable3.setVisible(false, false);
            }
        }
        if (i2 >= 0) {
            DrawableContainerState drawableContainerState = this.mDrawableContainerState;
            if (i2 < drawableContainerState.f351h) {
                Drawable child = drawableContainerState.getChild(i2);
                this.mCurrDrawable = child;
                this.mCurIndex = i2;
                if (child != null) {
                    int i3 = this.mDrawableContainerState.A;
                    if (i3 > 0) {
                        this.mEnterAnimationEnd = uptimeMillis + i3;
                    }
                    initializeDrawableForDisplay(child);
                }
                if (this.mEnterAnimationEnd == 0 || this.mExitAnimationEnd != 0) {
                    runnable = this.mAnimationRunnable;
                    if (runnable != null) {
                        this.mAnimationRunnable = new Runnable() { // from class: androidx.appcompat.graphics.drawable.DrawableContainer.1
                            @Override // java.lang.Runnable
                            public void run() {
                                DrawableContainer.this.a(true);
                                DrawableContainer.this.invalidateSelf();
                            }
                        };
                    } else {
                        unscheduleSelf(runnable);
                    }
                    a(true);
                }
                invalidateSelf();
                return true;
            }
        }
        this.mCurrDrawable = null;
        this.mCurIndex = -1;
        if (this.mEnterAnimationEnd == 0) {
        }
        runnable = this.mAnimationRunnable;
        if (runnable != null) {
        }
        a(true);
        invalidateSelf();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(DrawableContainerState drawableContainerState) {
        this.mDrawableContainerState = drawableContainerState;
        int i2 = this.mCurIndex;
        if (i2 >= 0) {
            Drawable child = drawableContainerState.getChild(i2);
            this.mCurrDrawable = child;
            if (child != null) {
                initializeDrawableForDisplay(child);
            }
        }
        this.mLastDrawable = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void g(Resources resources) {
        this.mDrawableContainerState.g(resources);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mDrawableContainerState.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.mDrawableContainerState.canConstantState()) {
            this.mDrawableContainerState.f347d = getChangingConfigurations();
            return this.mDrawableContainerState;
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    @NonNull
    public Drawable getCurrent() {
        return this.mCurrDrawable;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(@NonNull Rect rect) {
        Rect rect2 = this.mHotspotBounds;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantHeight();
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantWidth();
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantMinimumHeight();
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        if (this.mDrawableContainerState.isConstantSize()) {
            return this.mDrawableContainerState.getConstantMinimumWidth();
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.mCurrDrawable;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.mDrawableContainerState.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    @RequiresApi(21)
    public void getOutline(@NonNull Outline outline) {
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            Api21Impl.getOutline(drawable, outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(@NonNull Rect rect) {
        boolean padding;
        Rect constantPadding = this.mDrawableContainerState.getConstantPadding();
        if (constantPadding != null) {
            rect.set(constantPadding);
            padding = (constantPadding.right | ((constantPadding.left | constantPadding.top) | constantPadding.bottom)) != 0;
        } else {
            Drawable drawable = this.mCurrDrawable;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (needsMirroring()) {
            int i2 = rect.left;
            rect.left = rect.right;
            rect.right = i2;
        }
        return padding;
    }

    public void invalidateDrawable(@NonNull Drawable drawable) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState != null) {
            drawableContainerState.d();
        }
        if (drawable != this.mCurrDrawable || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.mDrawableContainerState.C;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mDrawableContainerState.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.mLastDrawable;
        boolean z2 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.mLastDrawable = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.mHasAlpha) {
                this.mCurrDrawable.setAlpha(this.mAlpha);
            }
        }
        if (this.mExitAnimationEnd != 0) {
            this.mExitAnimationEnd = 0L;
            z = true;
        }
        if (this.mEnterAnimationEnd != 0) {
            this.mEnterAnimationEnd = 0L;
        } else {
            z2 = z;
        }
        if (z2) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            DrawableContainerState b2 = b();
            b2.e();
            f(b2);
            this.mMutated = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i2) {
        return this.mDrawableContainerState.f(i2, c());
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i2) {
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            return drawable.setLevel(i2);
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            return drawable2.setLevel(i2);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j2) {
        if (drawable != this.mCurrDrawable || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.mHasAlpha && this.mAlpha == i2) {
            return;
        }
        this.mHasAlpha = true;
        this.mAlpha = i2;
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            if (this.mEnterAnimationEnd == 0) {
                drawable.setAlpha(i2);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState.C != z) {
            drawableContainerState.C = z;
            Drawable drawable = this.mCurrDrawable;
            if (drawable != null) {
                DrawableCompat.setAutoMirrored(drawable, z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        drawableContainerState.E = true;
        if (drawableContainerState.D != colorFilter) {
            drawableContainerState.D = colorFilter;
            Drawable drawable = this.mCurrDrawable;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState.x != z) {
            drawableContainerState.x = z;
            Drawable drawable = this.mCurrDrawable;
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    public void setEnterFadeDuration(int i2) {
        this.mDrawableContainerState.A = i2;
    }

    public void setExitFadeDuration(int i2) {
        this.mDrawableContainerState.B = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f2, float f3) {
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspot(drawable, f2, f3);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i2, int i3, int i4, int i5) {
        Rect rect = this.mHotspotBounds;
        if (rect == null) {
            this.mHotspotBounds = new Rect(i2, i3, i4, i5);
        } else {
            rect.set(i2, i3, i4, i5);
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspotBounds(drawable, i2, i3, i4, i5);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        drawableContainerState.H = true;
        if (drawableContainerState.F != colorStateList) {
            drawableContainerState.F = colorStateList;
            DrawableCompat.setTintList(this.mCurrDrawable, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        drawableContainerState.I = true;
        if (drawableContainerState.G != mode) {
            drawableContainerState.G = mode;
            DrawableCompat.setTintMode(this.mCurrDrawable, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        if (drawable != this.mCurrDrawable || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
