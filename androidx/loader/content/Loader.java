package androidx.loader.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: classes.dex */
public class Loader<D> {

    /* renamed from: a  reason: collision with root package name */
    int f3291a;

    /* renamed from: b  reason: collision with root package name */
    OnLoadCompleteListener f3292b;

    /* renamed from: c  reason: collision with root package name */
    OnLoadCanceledListener f3293c;

    /* renamed from: d  reason: collision with root package name */
    Context f3294d;

    /* renamed from: e  reason: collision with root package name */
    boolean f3295e = false;

    /* renamed from: f  reason: collision with root package name */
    boolean f3296f = false;

    /* renamed from: g  reason: collision with root package name */
    boolean f3297g = true;

    /* renamed from: h  reason: collision with root package name */
    boolean f3298h = false;

    /* renamed from: i  reason: collision with root package name */
    boolean f3299i = false;

    /* loaded from: classes.dex */
    public final class ForceLoadContentObserver extends ContentObserver {
        public ForceLoadContentObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            Loader.this.onContentChanged();
        }
    }

    /* loaded from: classes.dex */
    public interface OnLoadCanceledListener<D> {
        void onLoadCanceled(@NonNull Loader<D> loader);
    }

    /* loaded from: classes.dex */
    public interface OnLoadCompleteListener<D> {
        void onLoadComplete(@NonNull Loader<D> loader, @Nullable D d2);
    }

    public Loader(@NonNull Context context) {
        this.f3294d = context.getApplicationContext();
    }

    @MainThread
    protected void a() {
    }

    @MainThread
    public void abandon() {
        this.f3296f = true;
        a();
    }

    @MainThread
    protected boolean b() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @MainThread
    public void c() {
    }

    @MainThread
    public boolean cancelLoad() {
        return b();
    }

    public void commitContentChanged() {
        this.f3299i = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @MainThread
    public void d() {
    }

    @NonNull
    public String dataToString(@Nullable D d2) {
        StringBuilder sb = new StringBuilder(64);
        DebugUtils.buildShortClassTag(d2, sb);
        sb.append("}");
        return sb.toString();
    }

    @MainThread
    public void deliverCancellation() {
        OnLoadCanceledListener onLoadCanceledListener = this.f3293c;
        if (onLoadCanceledListener != null) {
            onLoadCanceledListener.onLoadCanceled(this);
        }
    }

    @MainThread
    public void deliverResult(@Nullable D d2) {
        OnLoadCompleteListener onLoadCompleteListener = this.f3292b;
        if (onLoadCompleteListener != null) {
            onLoadCompleteListener.onLoadComplete(this, d2);
        }
    }

    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.f3291a);
        printWriter.print(" mListener=");
        printWriter.println(this.f3292b);
        if (this.f3295e || this.f3298h || this.f3299i) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.f3295e);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.f3298h);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.f3299i);
        }
        if (this.f3296f || this.f3297g) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.f3296f);
            printWriter.print(" mReset=");
            printWriter.println(this.f3297g);
        }
    }

    @MainThread
    protected void e() {
    }

    @MainThread
    protected void f() {
    }

    @MainThread
    public void forceLoad() {
        c();
    }

    @NonNull
    public Context getContext() {
        return this.f3294d;
    }

    public int getId() {
        return this.f3291a;
    }

    public boolean isAbandoned() {
        return this.f3296f;
    }

    public boolean isReset() {
        return this.f3297g;
    }

    public boolean isStarted() {
        return this.f3295e;
    }

    @MainThread
    public void onContentChanged() {
        if (this.f3295e) {
            forceLoad();
        } else {
            this.f3298h = true;
        }
    }

    @MainThread
    public void registerListener(int i2, @NonNull OnLoadCompleteListener<D> onLoadCompleteListener) {
        if (this.f3292b != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.f3292b = onLoadCompleteListener;
        this.f3291a = i2;
    }

    @MainThread
    public void registerOnLoadCanceledListener(@NonNull OnLoadCanceledListener<D> onLoadCanceledListener) {
        if (this.f3293c != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.f3293c = onLoadCanceledListener;
    }

    @MainThread
    public void reset() {
        d();
        this.f3297g = true;
        this.f3295e = false;
        this.f3296f = false;
        this.f3298h = false;
        this.f3299i = false;
    }

    public void rollbackContentChanged() {
        if (this.f3299i) {
            onContentChanged();
        }
    }

    @MainThread
    public final void startLoading() {
        this.f3295e = true;
        this.f3297g = false;
        this.f3296f = false;
        e();
    }

    @MainThread
    public void stopLoading() {
        this.f3295e = false;
        f();
    }

    public boolean takeContentChanged() {
        boolean z = this.f3298h;
        this.f3298h = false;
        this.f3299i |= z;
        return z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        DebugUtils.buildShortClassTag(this, sb);
        sb.append(" id=");
        sb.append(this.f3291a);
        sb.append("}");
        return sb.toString();
    }

    @MainThread
    public void unregisterListener(@NonNull OnLoadCompleteListener<D> onLoadCompleteListener) {
        OnLoadCompleteListener<D> onLoadCompleteListener2 = this.f3292b;
        if (onLoadCompleteListener2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (onLoadCompleteListener2 != onLoadCompleteListener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.f3292b = null;
    }

    @MainThread
    public void unregisterOnLoadCanceledListener(@NonNull OnLoadCanceledListener<D> onLoadCanceledListener) {
        OnLoadCanceledListener<D> onLoadCanceledListener2 = this.f3293c;
        if (onLoadCanceledListener2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (onLoadCanceledListener2 != onLoadCanceledListener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.f3293c = null;
    }
}
