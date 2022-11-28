package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public class BatchingListUpdateCallback implements ListUpdateCallback {
    private static final int TYPE_ADD = 1;
    private static final int TYPE_CHANGE = 3;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_REMOVE = 2;

    /* renamed from: a  reason: collision with root package name */
    final ListUpdateCallback f3513a;

    /* renamed from: b  reason: collision with root package name */
    int f3514b = 0;

    /* renamed from: c  reason: collision with root package name */
    int f3515c = -1;

    /* renamed from: d  reason: collision with root package name */
    int f3516d = -1;

    /* renamed from: e  reason: collision with root package name */
    Object f3517e = null;

    public BatchingListUpdateCallback(@NonNull ListUpdateCallback listUpdateCallback) {
        this.f3513a = listUpdateCallback;
    }

    public void dispatchLastEvent() {
        int i2 = this.f3514b;
        if (i2 == 0) {
            return;
        }
        if (i2 == 1) {
            this.f3513a.onInserted(this.f3515c, this.f3516d);
        } else if (i2 == 2) {
            this.f3513a.onRemoved(this.f3515c, this.f3516d);
        } else if (i2 == 3) {
            this.f3513a.onChanged(this.f3515c, this.f3516d, this.f3517e);
        }
        this.f3517e = null;
        this.f3514b = 0;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onChanged(int i2, int i3, Object obj) {
        int i4;
        if (this.f3514b == 3) {
            int i5 = this.f3515c;
            int i6 = this.f3516d;
            if (i2 <= i5 + i6 && (i4 = i2 + i3) >= i5 && this.f3517e == obj) {
                this.f3515c = Math.min(i2, i5);
                this.f3516d = Math.max(i6 + i5, i4) - this.f3515c;
                return;
            }
        }
        dispatchLastEvent();
        this.f3515c = i2;
        this.f3516d = i3;
        this.f3517e = obj;
        this.f3514b = 3;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onInserted(int i2, int i3) {
        int i4;
        if (this.f3514b == 1 && i2 >= (i4 = this.f3515c)) {
            int i5 = this.f3516d;
            if (i2 <= i4 + i5) {
                this.f3516d = i5 + i3;
                this.f3515c = Math.min(i2, i4);
                return;
            }
        }
        dispatchLastEvent();
        this.f3515c = i2;
        this.f3516d = i3;
        this.f3514b = 1;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onMoved(int i2, int i3) {
        dispatchLastEvent();
        this.f3513a.onMoved(i2, i3);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onRemoved(int i2, int i3) {
        int i4;
        if (this.f3514b == 2 && (i4 = this.f3515c) >= i2 && i4 <= i2 + i3) {
            this.f3516d += i3;
            this.f3515c = i2;
            return;
        }
        dispatchLastEvent();
        this.f3515c = i2;
        this.f3516d = i3;
        this.f3514b = 2;
    }
}
