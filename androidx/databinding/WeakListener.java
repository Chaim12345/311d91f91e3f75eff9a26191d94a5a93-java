package androidx.databinding;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LifecycleOwner;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class WeakListener<T> extends WeakReference<ViewDataBinding> {

    /* renamed from: a  reason: collision with root package name */
    protected final int f2786a;
    private final ObservableReference<T> mObservable;
    private T mTarget;

    public WeakListener(ViewDataBinding viewDataBinding, int i2, ObservableReference<T> observableReference, ReferenceQueue<ViewDataBinding> referenceQueue) {
        super(viewDataBinding, referenceQueue);
        this.f2786a = i2;
        this.mObservable = observableReference;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public ViewDataBinding a() {
        ViewDataBinding viewDataBinding = (ViewDataBinding) get();
        if (viewDataBinding == null) {
            unregister();
        }
        return viewDataBinding;
    }

    public T getTarget() {
        return this.mTarget;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.mObservable.setLifecycleOwner(lifecycleOwner);
    }

    public void setTarget(T t2) {
        unregister();
        this.mTarget = t2;
        if (t2 != null) {
            this.mObservable.addListener(t2);
        }
    }

    public boolean unregister() {
        boolean z;
        T t2 = this.mTarget;
        if (t2 != null) {
            this.mObservable.removeListener(t2);
            z = true;
        } else {
            z = false;
        }
        this.mTarget = null;
        return z;
    }
}
