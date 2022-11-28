package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
class LayoutState {

    /* renamed from: b  reason: collision with root package name */
    int f3632b;

    /* renamed from: c  reason: collision with root package name */
    int f3633c;

    /* renamed from: d  reason: collision with root package name */
    int f3634d;

    /* renamed from: e  reason: collision with root package name */
    int f3635e;

    /* renamed from: h  reason: collision with root package name */
    boolean f3638h;

    /* renamed from: i  reason: collision with root package name */
    boolean f3639i;

    /* renamed from: a  reason: collision with root package name */
    boolean f3631a = true;

    /* renamed from: f  reason: collision with root package name */
    int f3636f = 0;

    /* renamed from: g  reason: collision with root package name */
    int f3637g = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(RecyclerView.State state) {
        int i2 = this.f3633c;
        return i2 >= 0 && i2 < state.getItemCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View b(RecyclerView.Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(this.f3633c);
        this.f3633c += this.f3634d;
        return viewForPosition;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f3632b + ", mCurrentPosition=" + this.f3633c + ", mItemDirection=" + this.f3634d + ", mLayoutDirection=" + this.f3635e + ", mStartLine=" + this.f3636f + ", mEndLine=" + this.f3637g + AbstractJsonLexerKt.END_OBJ;
    }
}
