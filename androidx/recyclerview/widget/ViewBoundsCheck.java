package androidx.recyclerview.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
class ViewBoundsCheck {

    /* renamed from: a  reason: collision with root package name */
    final Callback f3818a;

    /* renamed from: b  reason: collision with root package name */
    BoundFlags f3819b = new BoundFlags();

    /* loaded from: classes.dex */
    static class BoundFlags {

        /* renamed from: a  reason: collision with root package name */
        int f3820a = 0;

        /* renamed from: b  reason: collision with root package name */
        int f3821b;

        /* renamed from: c  reason: collision with root package name */
        int f3822c;

        /* renamed from: d  reason: collision with root package name */
        int f3823d;

        /* renamed from: e  reason: collision with root package name */
        int f3824e;

        BoundFlags() {
        }

        void a(int i2) {
            this.f3820a = i2 | this.f3820a;
        }

        boolean b() {
            int i2 = this.f3820a;
            if ((i2 & 7) == 0 || (i2 & (c(this.f3823d, this.f3821b) << 0)) != 0) {
                int i3 = this.f3820a;
                if ((i3 & 112) == 0 || (i3 & (c(this.f3823d, this.f3822c) << 4)) != 0) {
                    int i4 = this.f3820a;
                    if ((i4 & 1792) == 0 || (i4 & (c(this.f3824e, this.f3821b) << 8)) != 0) {
                        int i5 = this.f3820a;
                        return (i5 & 28672) == 0 || (i5 & (c(this.f3824e, this.f3822c) << 12)) != 0;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        int c(int i2, int i3) {
            if (i2 > i3) {
                return 1;
            }
            return i2 == i3 ? 2 : 4;
        }

        void d() {
            this.f3820a = 0;
        }

        void e(int i2, int i3, int i4, int i5) {
            this.f3821b = i2;
            this.f3822c = i3;
            this.f3823d = i4;
            this.f3824e = i5;
        }
    }

    /* loaded from: classes.dex */
    interface Callback {
        View getChildAt(int i2);

        int getChildEnd(View view);

        int getChildStart(View view);

        int getParentEnd();

        int getParentStart();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ViewBounds {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewBoundsCheck(Callback callback) {
        this.f3818a = callback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View a(int i2, int i3, int i4, int i5) {
        int parentStart = this.f3818a.getParentStart();
        int parentEnd = this.f3818a.getParentEnd();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        while (i2 != i3) {
            View childAt = this.f3818a.getChildAt(i2);
            this.f3819b.e(parentStart, parentEnd, this.f3818a.getChildStart(childAt), this.f3818a.getChildEnd(childAt));
            if (i4 != 0) {
                this.f3819b.d();
                this.f3819b.a(i4);
                if (this.f3819b.b()) {
                    return childAt;
                }
            }
            if (i5 != 0) {
                this.f3819b.d();
                this.f3819b.a(i5);
                if (this.f3819b.b()) {
                    view = childAt;
                }
            }
            i2 += i6;
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(View view, int i2) {
        this.f3819b.e(this.f3818a.getParentStart(), this.f3818a.getParentEnd(), this.f3818a.getChildStart(view), this.f3818a.getChildEnd(view));
        if (i2 != 0) {
            this.f3819b.d();
            this.f3819b.a(i2);
            return this.f3819b.b();
        }
        return false;
    }
}
