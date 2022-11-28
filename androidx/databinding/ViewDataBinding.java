package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.databinding.CallbackRegistry;
import androidx.databinding.Observable;
import androidx.databinding.ObservableList;
import androidx.databinding.ObservableMap;
import androidx.databinding.library.R;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewbinding.ViewBinding;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public abstract class ViewDataBinding extends BaseObservable implements ViewBinding {
    private static final int BINDING_NUMBER_START = 8;
    public static final String BINDING_TAG_PREFIX = "binding_";
    private static final CreateWeakListener CREATE_LIST_LISTENER;
    private static final CreateWeakListener CREATE_LIVE_DATA_LISTENER;
    private static final CreateWeakListener CREATE_MAP_LISTENER;
    private static final CreateWeakListener CREATE_PROPERTY_LISTENER;
    private static final int HALTED = 2;
    private static final int REBIND = 1;
    private static final CallbackRegistry.NotifierCallback<OnRebindCallback, ViewDataBinding, Void> REBIND_NOTIFIER;
    private static final int REBOUND = 3;
    private static final View.OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
    private static final boolean USE_CHOREOGRAPHER;

    /* renamed from: c  reason: collision with root package name */
    static int f2769c;
    private static final ReferenceQueue<ViewDataBinding> sReferenceQueue;

    /* renamed from: a  reason: collision with root package name */
    protected final DataBindingComponent f2770a;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: b  reason: collision with root package name */
    protected boolean f2771b;
    private Choreographer mChoreographer;
    private ViewDataBinding mContainingBinding;
    private final Choreographer.FrameCallback mFrameCallback;
    private boolean mInLiveDataRegisterObserver;
    private boolean mIsExecutingPendingBindings;
    private LifecycleOwner mLifecycleOwner;
    private WeakListener[] mLocalFieldObservers;
    private OnStartListener mOnStartListener;
    private boolean mPendingRebind;
    private CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> mRebindCallbacks;
    private boolean mRebindHalted;
    private final Runnable mRebindRunnable;
    private final View mRoot;
    private Handler mUIThreadHandler;

    /* renamed from: androidx.databinding.ViewDataBinding$7  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass7 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ViewDataBinding f2772a;

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                this.f2772a.mPendingRebind = false;
            }
            ViewDataBinding.processReferenceQueue();
            if (Build.VERSION.SDK_INT < 19 || this.f2772a.mRoot.isAttachedToWindow()) {
                this.f2772a.executePendingBindings();
                return;
            }
            this.f2772a.mRoot.removeOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
            this.f2772a.mRoot.addOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
        }
    }

    /* renamed from: androidx.databinding.ViewDataBinding$8  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass8 implements Choreographer.FrameCallback {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ViewDataBinding f2773a;

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j2) {
            this.f2773a.mRebindRunnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class IncludedLayouts {
        public final int[][] indexes;
        public final int[][] layoutIds;
        public final String[][] layouts;

        public IncludedLayouts(int i2) {
            this.layouts = new String[i2];
            this.indexes = new int[i2];
            this.layoutIds = new int[i2];
        }

        public void setIncludes(int i2, String[] strArr, int[] iArr, int[] iArr2) {
            this.layouts[i2] = strArr;
            this.indexes[i2] = iArr;
            this.layoutIds[i2] = iArr2;
        }
    }

    /* loaded from: classes.dex */
    private static class LiveDataListener implements Observer, ObservableReference<LiveData<?>> {

        /* renamed from: a  reason: collision with root package name */
        final WeakListener f2774a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        WeakReference f2775b = null;

        public LiveDataListener(ViewDataBinding viewDataBinding, int i2, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.f2774a = new WeakListener(viewDataBinding, i2, this, referenceQueue);
        }

        @Nullable
        private LifecycleOwner getLifecycleOwner() {
            WeakReference weakReference = this.f2775b;
            if (weakReference == null) {
                return null;
            }
            return (LifecycleOwner) weakReference.get();
        }

        @Override // androidx.databinding.ObservableReference
        public void addListener(LiveData<?> liveData) {
            LifecycleOwner lifecycleOwner = getLifecycleOwner();
            if (lifecycleOwner != null) {
                liveData.observe(lifecycleOwner, this);
            }
        }

        @Override // androidx.databinding.ObservableReference
        public WeakListener<LiveData<?>> getListener() {
            return this.f2774a;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable Object obj) {
            ViewDataBinding a2 = this.f2774a.a();
            if (a2 != null) {
                WeakListener weakListener = this.f2774a;
                a2.j(weakListener.f2786a, weakListener.getTarget(), 0);
            }
        }

        @Override // androidx.databinding.ObservableReference
        public void removeListener(LiveData<?> liveData) {
            liveData.removeObserver(this);
        }

        @Override // androidx.databinding.ObservableReference
        public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
            LifecycleOwner lifecycleOwner2 = getLifecycleOwner();
            LiveData liveData = (LiveData) this.f2774a.getTarget();
            if (liveData != null) {
                if (lifecycleOwner2 != null) {
                    liveData.removeObserver(this);
                }
                if (lifecycleOwner != null) {
                    liveData.observe(lifecycleOwner, this);
                }
            }
            if (lifecycleOwner != null) {
                this.f2775b = new WeakReference(lifecycleOwner);
            }
        }
    }

    /* loaded from: classes.dex */
    static class OnStartListener implements LifecycleObserver {

        /* renamed from: a  reason: collision with root package name */
        final WeakReference f2776a;

        private OnStartListener(ViewDataBinding viewDataBinding) {
            this.f2776a = new WeakReference(viewDataBinding);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = (ViewDataBinding) this.f2776a.get();
            if (viewDataBinding != null) {
                viewDataBinding.executePendingBindings();
            }
        }
    }

    /* loaded from: classes.dex */
    protected static abstract class PropertyChangedInverseListener extends Observable.OnPropertyChangedCallback implements InverseBindingListener {

        /* renamed from: a  reason: collision with root package name */
        final int f2777a;

        public PropertyChangedInverseListener(int i2) {
            this.f2777a = i2;
        }

        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i2) {
            if (i2 == this.f2777a || i2 == 0) {
                onChange();
            }
        }
    }

    /* loaded from: classes.dex */
    private static class WeakListListener extends ObservableList.OnListChangedCallback implements ObservableReference<ObservableList> {

        /* renamed from: a  reason: collision with root package name */
        final WeakListener f2778a;

        public WeakListListener(ViewDataBinding viewDataBinding, int i2, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.f2778a = new WeakListener(viewDataBinding, i2, this, referenceQueue);
        }

        @Override // androidx.databinding.ObservableReference
        public void addListener(ObservableList observableList) {
            observableList.addOnListChangedCallback(this);
        }

        @Override // androidx.databinding.ObservableReference
        public WeakListener<ObservableList> getListener() {
            return this.f2778a;
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onChanged(ObservableList observableList) {
            ObservableList observableList2;
            ViewDataBinding a2 = this.f2778a.a();
            if (a2 != null && (observableList2 = (ObservableList) this.f2778a.getTarget()) == observableList) {
                a2.j(this.f2778a.f2786a, observableList2, 0);
            }
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeChanged(ObservableList observableList, int i2, int i3) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeInserted(ObservableList observableList, int i2, int i3) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeMoved(ObservableList observableList, int i2, int i3, int i4) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeRemoved(ObservableList observableList, int i2, int i3) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableReference
        public void removeListener(ObservableList observableList) {
            observableList.removeOnListChangedCallback(this);
        }

        @Override // androidx.databinding.ObservableReference
        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }
    }

    /* loaded from: classes.dex */
    private static class WeakMapListener extends ObservableMap.OnMapChangedCallback implements ObservableReference<ObservableMap> {

        /* renamed from: a  reason: collision with root package name */
        final WeakListener f2779a;

        public WeakMapListener(ViewDataBinding viewDataBinding, int i2, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.f2779a = new WeakListener(viewDataBinding, i2, this, referenceQueue);
        }

        @Override // androidx.databinding.ObservableReference
        public void addListener(ObservableMap observableMap) {
            observableMap.addOnMapChangedCallback(this);
        }

        @Override // androidx.databinding.ObservableReference
        public WeakListener<ObservableMap> getListener() {
            return this.f2779a;
        }

        @Override // androidx.databinding.ObservableMap.OnMapChangedCallback
        public void onMapChanged(ObservableMap observableMap, Object obj) {
            ViewDataBinding a2 = this.f2779a.a();
            if (a2 == null || observableMap != this.f2779a.getTarget()) {
                return;
            }
            a2.j(this.f2779a.f2786a, observableMap, 0);
        }

        @Override // androidx.databinding.ObservableReference
        public void removeListener(ObservableMap observableMap) {
            observableMap.removeOnMapChangedCallback(this);
        }

        @Override // androidx.databinding.ObservableReference
        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }
    }

    /* loaded from: classes.dex */
    private static class WeakPropertyListener extends Observable.OnPropertyChangedCallback implements ObservableReference<Observable> {

        /* renamed from: a  reason: collision with root package name */
        final WeakListener f2780a;

        public WeakPropertyListener(ViewDataBinding viewDataBinding, int i2, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.f2780a = new WeakListener(viewDataBinding, i2, this, referenceQueue);
        }

        @Override // androidx.databinding.ObservableReference
        public void addListener(Observable observable) {
            observable.addOnPropertyChangedCallback(this);
        }

        @Override // androidx.databinding.ObservableReference
        public WeakListener<Observable> getListener() {
            return this.f2780a;
        }

        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i2) {
            ViewDataBinding a2 = this.f2780a.a();
            if (a2 != null && ((Observable) this.f2780a.getTarget()) == observable) {
                a2.j(this.f2780a.f2786a, observable, i2);
            }
        }

        @Override // androidx.databinding.ObservableReference
        public void removeListener(Observable observable) {
            observable.removeOnPropertyChangedCallback(this);
        }

        @Override // androidx.databinding.ObservableReference
        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f2769c = i2;
        USE_CHOREOGRAPHER = i2 >= 16;
        CREATE_PROPERTY_LISTENER = new CreateWeakListener() { // from class: androidx.databinding.ViewDataBinding.1
            @Override // androidx.databinding.CreateWeakListener
            public WeakListener create(ViewDataBinding viewDataBinding, int i3, ReferenceQueue<ViewDataBinding> referenceQueue) {
                return new WeakPropertyListener(viewDataBinding, i3, referenceQueue).getListener();
            }
        };
        CREATE_LIST_LISTENER = new CreateWeakListener() { // from class: androidx.databinding.ViewDataBinding.2
            @Override // androidx.databinding.CreateWeakListener
            public WeakListener create(ViewDataBinding viewDataBinding, int i3, ReferenceQueue<ViewDataBinding> referenceQueue) {
                return new WeakListListener(viewDataBinding, i3, referenceQueue).getListener();
            }
        };
        CREATE_MAP_LISTENER = new CreateWeakListener() { // from class: androidx.databinding.ViewDataBinding.3
            @Override // androidx.databinding.CreateWeakListener
            public WeakListener create(ViewDataBinding viewDataBinding, int i3, ReferenceQueue<ViewDataBinding> referenceQueue) {
                return new WeakMapListener(viewDataBinding, i3, referenceQueue).getListener();
            }
        };
        CREATE_LIVE_DATA_LISTENER = new CreateWeakListener() { // from class: androidx.databinding.ViewDataBinding.4
            @Override // androidx.databinding.CreateWeakListener
            public WeakListener create(ViewDataBinding viewDataBinding, int i3, ReferenceQueue<ViewDataBinding> referenceQueue) {
                return new LiveDataListener(viewDataBinding, i3, referenceQueue).getListener();
            }
        };
        REBIND_NOTIFIER = new CallbackRegistry.NotifierCallback<OnRebindCallback, ViewDataBinding, Void>() { // from class: androidx.databinding.ViewDataBinding.5
            @Override // androidx.databinding.CallbackRegistry.NotifierCallback
            public void onNotifyCallback(OnRebindCallback onRebindCallback, ViewDataBinding viewDataBinding, int i3, Void r4) {
                if (i3 == 1) {
                    if (onRebindCallback.onPreBind(viewDataBinding)) {
                        return;
                    }
                    viewDataBinding.mRebindHalted = true;
                } else if (i3 == 2) {
                    onRebindCallback.onCanceled(viewDataBinding);
                } else if (i3 != 3) {
                } else {
                    onRebindCallback.onBound(viewDataBinding);
                }
            }
        };
        sReferenceQueue = new ReferenceQueue<>();
        ROOT_REATTACHED_LISTENER = i2 < 19 ? null : new View.OnAttachStateChangeListener() { // from class: androidx.databinding.ViewDataBinding.6
            @Override // android.view.View.OnAttachStateChangeListener
            @TargetApi(19)
            public void onViewAttachedToWindow(View view) {
                ViewDataBinding.i(view).mRebindRunnable.run();
                view.removeOnAttachStateChangeListener(this);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }
        };
    }

    private static DataBindingComponent checkAndCastToBindingComponent(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof DataBindingComponent) {
            return (DataBindingComponent) obj;
        }
        throw new IllegalArgumentException("The provided bindingComponent parameter must be an instance of DataBindingComponent. See  https://issuetracker.google.com/issues/116541301 for details of why this parameter is not defined as DataBindingComponent");
    }

    private void executeBindingsInternal() {
        if (this.mIsExecutingPendingBindings) {
            m();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> callbackRegistry = this.mRebindCallbacks;
            if (callbackRegistry != null) {
                callbackRegistry.notifyCallbacks(this, 1, null);
                if (this.mRebindHalted) {
                    this.mRebindCallbacks.notifyCallbacks(this, 2, null);
                }
            }
            if (!this.mRebindHalted) {
                g();
                CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> callbackRegistry2 = this.mRebindCallbacks;
                if (callbackRegistry2 != null) {
                    callbackRegistry2.notifyCallbacks(this, 3, null);
                }
            }
            this.mIsExecutingPendingBindings = false;
        }
    }

    private static int findIncludeIndex(String str, int i2, IncludedLayouts includedLayouts, int i3) {
        CharSequence subSequence = str.subSequence(str.indexOf(47) + 1, str.length() - 2);
        String[] strArr = includedLayouts.layouts[i3];
        int length = strArr.length;
        while (i2 < length) {
            if (TextUtils.equals(subSequence, strArr[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    private static int findLastMatching(ViewGroup viewGroup, int i2) {
        String str = (String) viewGroup.getChildAt(i2).getTag();
        String substring = str.substring(0, str.length() - 1);
        int length = substring.length();
        int childCount = viewGroup.getChildCount();
        for (int i3 = i2 + 1; i3 < childCount; i3++) {
            View childAt = viewGroup.getChildAt(i3);
            String str2 = childAt.getTag() instanceof String ? (String) childAt.getTag() : null;
            if (str2 != null && str2.startsWith(substring)) {
                if (str2.length() == str.length() && str2.charAt(str2.length() - 1) == '0') {
                    return i2;
                }
                if (isNumeric(str2, length)) {
                    i2 = i3;
                }
            }
        }
        return i2;
    }

    public static int getBuildSdkInt() {
        return f2769c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewDataBinding i(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(R.id.dataBinding);
        }
        return null;
    }

    private static boolean isNumeric(String str, int i2) {
        int length = str.length();
        if (length == i2) {
            return false;
        }
        while (i2 < length) {
            if (!Character.isDigit(str.charAt(i2))) {
                return false;
            }
            i2++;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void mapBindings(DataBindingComponent dataBindingComponent, View view, Object[] objArr, IncludedLayouts includedLayouts, SparseIntArray sparseIntArray, boolean z) {
        int i2;
        boolean z2;
        int i3;
        int i4;
        int i5;
        int findIncludeIndex;
        int id;
        int i6;
        int i7;
        if (i(view) != null) {
            return;
        }
        Object tag = view.getTag();
        String str = tag instanceof String ? (String) tag : null;
        int i8 = 1;
        if (z && str != null && str.startsWith("layout")) {
            int lastIndexOf = str.lastIndexOf(95);
            if (lastIndexOf > 0) {
                int i9 = lastIndexOf + 1;
                if (isNumeric(str, i9)) {
                    i7 = parseTagInt(str, i9);
                    if (objArr[i7] == null) {
                        objArr[i7] = view;
                    }
                    if (includedLayouts == null) {
                        i7 = -1;
                    }
                    z2 = true;
                    i2 = i7;
                }
            }
            i7 = -1;
            z2 = false;
            i2 = i7;
        } else if (str == null || !str.startsWith(BINDING_TAG_PREFIX)) {
            i2 = -1;
            z2 = false;
        } else {
            int parseTagInt = parseTagInt(str, BINDING_NUMBER_START);
            if (objArr[parseTagInt] == null) {
                objArr[parseTagInt] = view;
            }
            if (includedLayouts == null) {
                parseTagInt = -1;
            }
            i2 = parseTagInt;
            z2 = true;
        }
        if (!z2 && (id = view.getId()) > 0 && sparseIntArray != null && (i6 = sparseIntArray.get(id, -1)) >= 0 && objArr[i6] == null) {
            objArr[i6] = view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            int i10 = 0;
            int i11 = 0;
            while (i10 < childCount) {
                View childAt = viewGroup.getChildAt(i10);
                if (i2 >= 0 && (childAt.getTag() instanceof String)) {
                    String str2 = (String) childAt.getTag();
                    if (str2.endsWith("_0") && str2.startsWith("layout") && str2.indexOf(47) > 0 && (findIncludeIndex = findIncludeIndex(str2, i11, includedLayouts, i2)) >= 0) {
                        int i12 = findIncludeIndex + 1;
                        int i13 = includedLayouts.indexes[i2][findIncludeIndex];
                        int i14 = includedLayouts.layoutIds[i2][findIncludeIndex];
                        int findLastMatching = findLastMatching(viewGroup, i10);
                        if (findLastMatching == i10) {
                            objArr[i13] = DataBindingUtil.a(dataBindingComponent, childAt, i14);
                            i3 = i10;
                            i5 = i8;
                            i4 = i12;
                        } else {
                            int i15 = (findLastMatching - i10) + i8;
                            View[] viewArr = new View[i15];
                            for (int i16 = 0; i16 < i15; i16++) {
                                viewArr[i16] = viewGroup.getChildAt(i10 + i16);
                            }
                            objArr[i13] = DataBindingUtil.b(dataBindingComponent, viewArr, i14);
                            i3 = i10 + (i15 - 1);
                            i4 = i12;
                            i5 = 1;
                        }
                        if (i5 != 0) {
                            mapBindings(dataBindingComponent, childAt, objArr, includedLayouts, sparseIntArray, false);
                        }
                        int i17 = i4;
                        i8 = 1;
                        i10 = i3 + 1;
                        i11 = i17;
                    }
                }
                i3 = i10;
                i4 = i11;
                i5 = 0;
                if (i5 != 0) {
                }
                int i172 = i4;
                i8 = 1;
                i10 = i3 + 1;
                i11 = i172;
            }
        }
    }

    private static int parseTagInt(String str, int i2) {
        int length = str.length();
        int i3 = 0;
        while (i2 < length) {
            i3 = (i3 * 10) + (str.charAt(i2) - '0');
            i2++;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void processReferenceQueue() {
        while (true) {
            Reference<? extends ViewDataBinding> poll = sReferenceQueue.poll();
            if (poll == null) {
                return;
            }
            if (poll instanceof WeakListener) {
                ((WeakListener) poll).unregister();
            }
        }
    }

    public void addOnRebindCallback(@NonNull OnRebindCallback onRebindCallback) {
        if (this.mRebindCallbacks == null) {
            this.mRebindCallbacks = new CallbackRegistry<>(REBIND_NOTIFIER);
        }
        this.mRebindCallbacks.add(onRebindCallback);
    }

    public void executePendingBindings() {
        ViewDataBinding viewDataBinding = this.mContainingBinding;
        if (viewDataBinding == null) {
            executeBindingsInternal();
        } else {
            viewDataBinding.executePendingBindings();
        }
    }

    protected abstract void g();

    @Nullable
    public LifecycleOwner getLifecycleOwner() {
        return this.mLifecycleOwner;
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public View getRoot() {
        return this.mRoot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        g();
    }

    public abstract boolean hasPendingBindings();

    public abstract void invalidateAll();

    /* JADX INFO: Access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void j(int i2, Object obj, int i3) {
        if (this.mInLiveDataRegisterObserver || this.f2771b || !k(i2, obj, i3)) {
            return;
        }
        m();
    }

    protected abstract boolean k(int i2, Object obj, int i3);

    protected void l(int i2, Object obj, CreateWeakListener createWeakListener) {
        if (obj == null) {
            return;
        }
        WeakListener weakListener = this.mLocalFieldObservers[i2];
        if (weakListener == null) {
            weakListener = createWeakListener.create(this, i2, sReferenceQueue);
            this.mLocalFieldObservers[i2] = weakListener;
            LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
            if (lifecycleOwner != null) {
                weakListener.setLifecycleOwner(lifecycleOwner);
            }
        }
        weakListener.setTarget(obj);
    }

    protected void m() {
        ViewDataBinding viewDataBinding = this.mContainingBinding;
        if (viewDataBinding != null) {
            viewDataBinding.m();
            return;
        }
        LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
        if (lifecycleOwner == null || lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            synchronized (this) {
                if (this.mPendingRebind) {
                    return;
                }
                this.mPendingRebind = true;
                if (USE_CHOREOGRAPHER) {
                    this.mChoreographer.postFrameCallback(this.mFrameCallback);
                } else {
                    this.mUIThreadHandler.post(this.mRebindRunnable);
                }
            }
        }
    }

    protected boolean n(int i2) {
        WeakListener weakListener = this.mLocalFieldObservers[i2];
        if (weakListener != null) {
            return weakListener.unregister();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean o(int i2, Object obj, CreateWeakListener createWeakListener) {
        if (obj == null) {
            return n(i2);
        }
        WeakListener weakListener = this.mLocalFieldObservers[i2];
        if (weakListener == null) {
            l(i2, obj, createWeakListener);
            return true;
        } else if (weakListener.getTarget() == obj) {
            return false;
        } else {
            n(i2);
            l(i2, obj, createWeakListener);
            return true;
        }
    }

    public void removeOnRebindCallback(@NonNull OnRebindCallback onRebindCallback) {
        CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> callbackRegistry = this.mRebindCallbacks;
        if (callbackRegistry != null) {
            callbackRegistry.remove(onRebindCallback);
        }
    }

    @MainThread
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        WeakListener[] weakListenerArr;
        boolean z = lifecycleOwner instanceof Fragment;
        LifecycleOwner lifecycleOwner2 = this.mLifecycleOwner;
        if (lifecycleOwner2 == lifecycleOwner) {
            return;
        }
        if (lifecycleOwner2 != null) {
            lifecycleOwner2.getLifecycle().removeObserver(this.mOnStartListener);
        }
        this.mLifecycleOwner = lifecycleOwner;
        if (lifecycleOwner != null) {
            if (this.mOnStartListener == null) {
                this.mOnStartListener = new OnStartListener();
            }
            lifecycleOwner.getLifecycle().addObserver(this.mOnStartListener);
        }
        for (WeakListener weakListener : this.mLocalFieldObservers) {
            if (weakListener != null) {
                weakListener.setLifecycleOwner(lifecycleOwner);
            }
        }
    }

    public abstract boolean setVariable(int i2, @Nullable Object obj);

    public void unbind() {
        WeakListener[] weakListenerArr;
        for (WeakListener weakListener : this.mLocalFieldObservers) {
            if (weakListener != null) {
                weakListener.unregister();
            }
        }
    }
}
