package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IdRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.collection.ArraySet;
import androidx.core.os.CancellationSignal;
import androidx.fragment.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransition;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class FragmentManager implements FragmentResultOwner {
    private static boolean DEBUG = false;
    private static final String EXTRA_CREATED_FILLIN_INTENT = "androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE";
    public static final int POP_BACK_STACK_INCLUSIVE = 1;

    /* renamed from: e  reason: collision with root package name */
    static boolean f2982e = true;

    /* renamed from: a  reason: collision with root package name */
    ArrayList f2983a;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    Fragment f2985c;
    private ArrayList<OnBackStackChangedListener> mBackStackChangeListeners;
    private FragmentContainer mContainer;
    private ArrayList<Fragment> mCreatedMenus;
    private boolean mDestroyed;
    private boolean mExecutingActions;
    private boolean mHavePendingDeferredStart;
    private FragmentHostCallback<?> mHost;
    private boolean mNeedMenuInvalidate;
    private FragmentManagerViewModel mNonConfig;
    private OnBackPressedDispatcher mOnBackPressedDispatcher;
    private Fragment mParent;
    private ArrayList<StartEnterTransitionListener> mPostponedTransactions;
    private ActivityResultLauncher<String[]> mRequestPermissions;
    private ActivityResultLauncher<Intent> mStartActivityForResult;
    private ActivityResultLauncher<IntentSenderRequest> mStartIntentSenderForResult;
    private boolean mStateSaved;
    private boolean mStopped;
    private ArrayList<Fragment> mTmpAddedFragments;
    private ArrayList<Boolean> mTmpIsPop;
    private ArrayList<BackStackRecord> mTmpRecords;
    private final ArrayList<OpGenerator> mPendingActions = new ArrayList<>();
    private final FragmentStore mFragmentStore = new FragmentStore();
    private final FragmentLayoutInflaterFactory mLayoutInflaterFactory = new FragmentLayoutInflaterFactory(this);
    private final OnBackPressedCallback mOnBackPressedCallback = new OnBackPressedCallback(false) { // from class: androidx.fragment.app.FragmentManager.1
        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            FragmentManager.this.Z();
        }
    };
    private final AtomicInteger mBackStackIndex = new AtomicInteger();
    private final Map<String, Bundle> mResults = Collections.synchronizedMap(new HashMap());
    private final Map<String, LifecycleAwareResultListener> mResultListeners = Collections.synchronizedMap(new HashMap());
    private Map<Fragment, HashSet<CancellationSignal>> mExitAnimationCancellationSignals = Collections.synchronizedMap(new HashMap());
    private final FragmentTransition.Callback mFragmentTransitionCallback = new FragmentTransition.Callback() { // from class: androidx.fragment.app.FragmentManager.2
        @Override // androidx.fragment.app.FragmentTransition.Callback
        public void onComplete(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            if (cancellationSignal.isCanceled()) {
                return;
            }
            FragmentManager.this.r0(fragment, cancellationSignal);
        }

        @Override // androidx.fragment.app.FragmentTransition.Callback
        public void onStart(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            FragmentManager.this.e(fragment, cancellationSignal);
        }
    };
    private final FragmentLifecycleCallbacksDispatcher mLifecycleCallbacksDispatcher = new FragmentLifecycleCallbacksDispatcher(this);
    private final CopyOnWriteArrayList<FragmentOnAttachListener> mOnAttachListeners = new CopyOnWriteArrayList<>();

    /* renamed from: b  reason: collision with root package name */
    int f2984b = -1;
    private FragmentFactory mFragmentFactory = null;
    private FragmentFactory mHostFragmentFactory = new FragmentFactory() { // from class: androidx.fragment.app.FragmentManager.3
        @Override // androidx.fragment.app.FragmentFactory
        @NonNull
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String str) {
            return FragmentManager.this.S().instantiate(FragmentManager.this.S().b(), str, null);
        }
    };
    private SpecialEffectsControllerFactory mSpecialEffectsControllerFactory = null;
    private SpecialEffectsControllerFactory mDefaultSpecialEffectsControllerFactory = new SpecialEffectsControllerFactory(this) { // from class: androidx.fragment.app.FragmentManager.4
        @Override // androidx.fragment.app.SpecialEffectsControllerFactory
        @NonNull
        public SpecialEffectsController createController(@NonNull ViewGroup viewGroup) {
            return new DefaultSpecialEffectsController(viewGroup);
        }
    };

    /* renamed from: d  reason: collision with root package name */
    ArrayDeque f2986d = new ArrayDeque();
    private Runnable mExecCommit = new Runnable() { // from class: androidx.fragment.app.FragmentManager.5
        @Override // java.lang.Runnable
        public void run() {
            FragmentManager.this.K(true);
        }
    };

    /* loaded from: classes.dex */
    public interface BackStackEntry {
        @Nullable
        @Deprecated
        CharSequence getBreadCrumbShortTitle();

        @StringRes
        @Deprecated
        int getBreadCrumbShortTitleRes();

        @Nullable
        @Deprecated
        CharSequence getBreadCrumbTitle();

        @StringRes
        @Deprecated
        int getBreadCrumbTitleRes();

        int getId();

        @Nullable
        String getName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class FragmentIntentSenderContract extends ActivityResultContract<IntentSenderRequest, ActivityResult> {
        FragmentIntentSenderContract() {
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        @NonNull
        public Intent createIntent(@NonNull Context context, IntentSenderRequest intentSenderRequest) {
            Bundle bundleExtra;
            Intent intent = new Intent(ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST);
            Intent fillInIntent = intentSenderRequest.getFillInIntent();
            if (fillInIntent != null && (bundleExtra = fillInIntent.getBundleExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE)) != null) {
                intent.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundleExtra);
                fillInIntent.removeExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
                if (fillInIntent.getBooleanExtra(FragmentManager.EXTRA_CREATED_FILLIN_INTENT, false)) {
                    intentSenderRequest = new IntentSenderRequest.Builder(intentSenderRequest.getIntentSender()).setFillInIntent(null).setFlags(intentSenderRequest.getFlagsValues(), intentSenderRequest.getFlagsMask()).build();
                }
            }
            intent.putExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_INTENT_SENDER_REQUEST, intentSenderRequest);
            if (FragmentManager.c0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("CreateIntent created the following intent: ");
                sb.append(intent);
            }
            return intent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.activity.result.contract.ActivityResultContract
        @NonNull
        public ActivityResult parseResult(int i2, @Nullable Intent intent) {
            return new ActivityResult(i2, intent);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class FragmentLifecycleCallbacks {
        @Deprecated
        public void onFragmentActivityCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
        }

        public void onFragmentCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentDetached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentPaused(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentPreAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
        }

        public void onFragmentPreCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentResumed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentSaveInstanceState(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Bundle bundle) {
        }

        public void onFragmentStarted(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentStopped(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle) {
        }

        public void onFragmentViewDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes.dex */
    public static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new Parcelable.Creator<LaunchedFragmentInfo>() { // from class: androidx.fragment.app.FragmentManager.LaunchedFragmentInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LaunchedFragmentInfo createFromParcel(Parcel parcel) {
                return new LaunchedFragmentInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LaunchedFragmentInfo[] newArray(int i2) {
                return new LaunchedFragmentInfo[i2];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        String f3002a;

        /* renamed from: b  reason: collision with root package name */
        int f3003b;

        LaunchedFragmentInfo(@NonNull Parcel parcel) {
            this.f3002a = parcel.readString();
            this.f3003b = parcel.readInt();
        }

        LaunchedFragmentInfo(@NonNull String str, int i2) {
            this.f3002a = str;
            this.f3003b = i2;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f3002a);
            parcel.writeInt(this.f3003b);
        }
    }

    /* loaded from: classes.dex */
    private static class LifecycleAwareResultListener implements FragmentResultListener {
        private final Lifecycle mLifecycle;
        private final FragmentResultListener mListener;
        private final LifecycleEventObserver mObserver;

        LifecycleAwareResultListener(@NonNull Lifecycle lifecycle, @NonNull FragmentResultListener fragmentResultListener, @NonNull LifecycleEventObserver lifecycleEventObserver) {
            this.mLifecycle = lifecycle;
            this.mListener = fragmentResultListener;
            this.mObserver = lifecycleEventObserver;
        }

        public boolean isAtLeast(Lifecycle.State state) {
            return this.mLifecycle.getCurrentState().isAtLeast(state);
        }

        @Override // androidx.fragment.app.FragmentResultListener
        public void onFragmentResult(@NonNull String str, @NonNull Bundle bundle) {
            this.mListener.onFragmentResult(str, bundle);
        }

        public void removeObserver() {
            this.mLifecycle.removeObserver(this.mObserver);
        }
    }

    /* loaded from: classes.dex */
    public interface OnBackStackChangedListener {
        @MainThread
        void onBackStackChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface OpGenerator {
        boolean generateOps(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2);
    }

    /* loaded from: classes.dex */
    private class PopBackStackState implements OpGenerator {

        /* renamed from: a  reason: collision with root package name */
        final String f3004a;

        /* renamed from: b  reason: collision with root package name */
        final int f3005b;

        /* renamed from: c  reason: collision with root package name */
        final int f3006c;

        PopBackStackState(@Nullable String str, int i2, int i3) {
            this.f3004a = str;
            this.f3005b = i2;
            this.f3006c = i3;
        }

        @Override // androidx.fragment.app.FragmentManager.OpGenerator
        public boolean generateOps(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
            Fragment fragment = FragmentManager.this.f2985c;
            if (fragment == null || this.f3005b >= 0 || this.f3004a != null || !fragment.getChildFragmentManager().popBackStackImmediate()) {
                return FragmentManager.this.q0(arrayList, arrayList2, this.f3004a, this.f3005b, this.f3006c);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {

        /* renamed from: a  reason: collision with root package name */
        final boolean f3008a;

        /* renamed from: b  reason: collision with root package name */
        final BackStackRecord f3009b;
        private int mNumPostponed;

        StartEnterTransitionListener(@NonNull BackStackRecord backStackRecord, boolean z) {
            this.f3008a = z;
            this.f3009b = backStackRecord;
        }

        void a() {
            BackStackRecord backStackRecord = this.f3009b;
            backStackRecord.f2878r.l(backStackRecord, this.f3008a, false, false);
        }

        void b() {
            boolean z = this.mNumPostponed > 0;
            for (Fragment fragment : this.f3009b.f2878r.getFragments()) {
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            BackStackRecord backStackRecord = this.f3009b;
            backStackRecord.f2878r.l(backStackRecord, this.f3008a, !z, true);
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        @Override // androidx.fragment.app.Fragment.OnStartEnterTransitionListener
        public void onStartEnterTransition() {
            int i2 = this.mNumPostponed - 1;
            this.mNumPostponed = i2;
            if (i2 != 0) {
                return;
            }
            this.f3009b.f2878r.z0();
        }

        @Override // androidx.fragment.app.Fragment.OnStartEnterTransitionListener
        public void startListening() {
            this.mNumPostponed++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Fragment X(@NonNull View view) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    private void addAddedFragments(@NonNull ArraySet<Fragment> arraySet) {
        int i2 = this.f2984b;
        if (i2 < 1) {
            return;
        }
        int min = Math.min(i2, 5);
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment.mState < min) {
                m0(fragment, min);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    arraySet.add(fragment);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c0(int i2) {
        return DEBUG || Log.isLoggable("FragmentManager", i2);
    }

    private void cancelExitAnimation(@NonNull Fragment fragment) {
        HashSet<CancellationSignal> hashSet = this.mExitAnimationCancellationSignals.get(fragment);
        if (hashSet != null) {
            Iterator<CancellationSignal> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            hashSet.clear();
            destroyFragmentView(fragment);
            this.mExitAnimationCancellationSignals.remove(fragment);
        }
    }

    private void checkStateLoss() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    private Set<SpecialEffectsController> collectAllSpecialEffectsController() {
        HashSet hashSet = new HashSet();
        for (FragmentStateManager fragmentStateManager : this.mFragmentStore.l()) {
            ViewGroup viewGroup = fragmentStateManager.k().mContainer;
            if (viewGroup != null) {
                hashSet.add(SpecialEffectsController.k(viewGroup, W()));
            }
        }
        return hashSet;
    }

    private Set<SpecialEffectsController> collectChangedControllers(@NonNull ArrayList<BackStackRecord> arrayList, int i2, int i3) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i2 < i3) {
            Iterator it = arrayList.get(i2).f3038a.iterator();
            while (it.hasNext()) {
                Fragment fragment = ((FragmentTransaction.Op) it.next()).f3056b;
                if (fragment != null && (viewGroup = fragment.mContainer) != null) {
                    hashSet.add(SpecialEffectsController.j(viewGroup, this));
                }
            }
            i2++;
        }
        return hashSet;
    }

    private void completeShowHideFragment(@NonNull final Fragment fragment) {
        Animator animator;
        if (fragment.mView != null) {
            FragmentAnim.AnimationOrAnimator b2 = FragmentAnim.b(this.mHost.b(), fragment, !fragment.mHidden, fragment.getPopDirection());
            if (b2 == null || (animator = b2.animator) == null) {
                if (b2 != null) {
                    fragment.mView.startAnimation(b2.animation);
                    b2.animation.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    final ViewGroup viewGroup = fragment.mContainer;
                    final View view = fragment.mView;
                    viewGroup.startViewTransition(view);
                    b2.animator.addListener(new AnimatorListenerAdapter(this) { // from class: androidx.fragment.app.FragmentManager.7
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator2) {
                            viewGroup.endViewTransition(view);
                            animator2.removeListener(this);
                            Fragment fragment2 = fragment;
                            View view2 = fragment2.mView;
                            if (view2 == null || !fragment2.mHidden) {
                                return;
                            }
                            view2.setVisibility(8);
                        }
                    });
                }
                b2.animator.start();
            }
        }
        b0(fragment);
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    private void destroyFragmentView(@NonNull Fragment fragment) {
        fragment.performDestroyView();
        this.mLifecycleCallbacksDispatcher.n(fragment, false);
        fragment.mContainer = null;
        fragment.mView = null;
        fragment.mViewLifecycleOwner = null;
        fragment.mViewLifecycleOwnerLiveData.setValue(null);
        fragment.mInLayout = false;
    }

    private void dispatchParentPrimaryNavigationFragmentChanged(@Nullable Fragment fragment) {
        if (fragment == null || !fragment.equals(M(fragment.mWho))) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
    }

    private void dispatchStateChange(int i2) {
        try {
            this.mExecutingActions = true;
            this.mFragmentStore.d(i2);
            k0(i2, false);
            if (f2982e) {
                for (SpecialEffectsController specialEffectsController : collectAllSpecialEffectsController()) {
                    specialEffectsController.g();
                }
            }
            this.mExecutingActions = false;
            K(true);
        } catch (Throwable th) {
            this.mExecutingActions = false;
            throw th;
        }
    }

    private void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
    }

    @Deprecated
    public static void enableDebugLogging(boolean z) {
        DEBUG = z;
    }

    @FragmentStateManagerControl
    public static void enableNewStateManager(boolean z) {
        f2982e = z;
    }

    private void endAnimatingAwayFragments() {
        if (f2982e) {
            for (SpecialEffectsController specialEffectsController : collectAllSpecialEffectsController()) {
                specialEffectsController.g();
            }
        } else if (!this.mExitAnimationCancellationSignals.isEmpty()) {
            for (Fragment fragment : this.mExitAnimationCancellationSignals.keySet()) {
                cancelExitAnimation(fragment);
                l0(fragment);
            }
        }
    }

    private void ensureExecReady(boolean z) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.mHost == null) {
            if (!this.mDestroyed) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        } else if (Looper.myLooper() != this.mHost.c().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        } else {
            if (!z) {
                checkStateLoss();
            }
            if (this.mTmpRecords == null) {
                this.mTmpRecords = new ArrayList<>();
                this.mTmpIsPop = new ArrayList<>();
            }
            this.mExecutingActions = true;
            try {
                executePostponedTransaction(null, null);
            } finally {
                this.mExecutingActions = false;
            }
        }
    }

    private static void executeOps(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            BackStackRecord backStackRecord = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                backStackRecord.d(-1);
                backStackRecord.g(i2 == i3 + (-1));
            } else {
                backStackRecord.d(1);
                backStackRecord.f();
            }
            i2++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01be  */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [int, boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void executeOpsTogether(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3) {
        ?? r1;
        int i4;
        boolean z;
        int i5;
        int i6;
        ArrayList<Boolean> arrayList3;
        int i7;
        ArrayList<Boolean> arrayList4;
        int i8;
        boolean z2;
        int i9;
        boolean z3 = arrayList.get(i2).f3053p;
        ArrayList<Fragment> arrayList5 = this.mTmpAddedFragments;
        if (arrayList5 == null) {
            this.mTmpAddedFragments = new ArrayList<>();
        } else {
            arrayList5.clear();
        }
        this.mTmpAddedFragments.addAll(this.mFragmentStore.o());
        Fragment primaryNavigationFragment = getPrimaryNavigationFragment();
        boolean z4 = false;
        for (int i10 = i2; i10 < i3; i10++) {
            BackStackRecord backStackRecord = arrayList.get(i10);
            primaryNavigationFragment = !arrayList2.get(i10).booleanValue() ? backStackRecord.h(this.mTmpAddedFragments, primaryNavigationFragment) : backStackRecord.m(this.mTmpAddedFragments, primaryNavigationFragment);
            z4 = z4 || backStackRecord.f3044g;
        }
        this.mTmpAddedFragments.clear();
        if (!z3 && this.f2984b >= 1) {
            if (!f2982e) {
                r1 = 1;
                FragmentTransition.h(this.mHost.b(), this.mContainer, arrayList, arrayList2, i2, i3, false, this.mFragmentTransitionCallback);
                executeOps(arrayList, arrayList2, i2, i3);
                if (f2982e) {
                    if (z3) {
                        ArraySet arraySet = new ArraySet();
                        addAddedFragments(arraySet);
                        i4 = r1;
                        z = z3;
                        i5 = i3;
                        i6 = i2;
                        arrayList3 = arrayList2;
                        i7 = postponePostponableTransactions(arrayList, arrayList2, i2, i3, arraySet);
                        makeRemovedFragmentsInvisible(arraySet);
                    } else {
                        i4 = r1;
                        z = z3;
                        i5 = i3;
                        i6 = i2;
                        arrayList3 = arrayList2;
                        i7 = i5;
                    }
                    if (i7 == i6 || !z) {
                        arrayList4 = arrayList3;
                        i8 = i5;
                    } else {
                        if (this.f2984b >= i4) {
                            arrayList4 = arrayList3;
                            int i11 = i7;
                            i8 = i5;
                            z2 = i4;
                            FragmentTransition.h(this.mHost.b(), this.mContainer, arrayList, arrayList2, i2, i11, true, this.mFragmentTransitionCallback);
                        } else {
                            arrayList4 = arrayList3;
                            i8 = i5;
                            z2 = i4;
                        }
                        k0(this.f2984b, z2);
                    }
                } else {
                    boolean booleanValue = arrayList2.get(i3 - 1).booleanValue();
                    for (int i12 = i2; i12 < i3; i12++) {
                        BackStackRecord backStackRecord2 = arrayList.get(i12);
                        if (booleanValue) {
                            for (int size = backStackRecord2.f3038a.size() - r1; size >= 0; size--) {
                                Fragment fragment = ((FragmentTransaction.Op) backStackRecord2.f3038a.get(size)).f3056b;
                                if (fragment != null) {
                                    m(fragment).l();
                                }
                            }
                        } else {
                            Iterator it = backStackRecord2.f3038a.iterator();
                            while (it.hasNext()) {
                                Fragment fragment2 = ((FragmentTransaction.Op) it.next()).f3056b;
                                if (fragment2 != null) {
                                    m(fragment2).l();
                                }
                            }
                        }
                    }
                    k0(this.f2984b, r1);
                    for (SpecialEffectsController specialEffectsController : collectChangedControllers(arrayList, i2, i3)) {
                        specialEffectsController.m(booleanValue);
                        specialEffectsController.l();
                        specialEffectsController.f();
                    }
                    i8 = i3;
                    arrayList4 = arrayList2;
                }
                for (i9 = i2; i9 < i8; i9++) {
                    BackStackRecord backStackRecord3 = arrayList.get(i9);
                    if (arrayList4.get(i9).booleanValue() && backStackRecord3.f2880t >= 0) {
                        backStackRecord3.f2880t = -1;
                    }
                    backStackRecord3.runOnCommitRunnables();
                }
                if (z4) {
                    return;
                }
                reportBackStackChanged();
                return;
            }
            for (int i13 = i2; i13 < i3; i13++) {
                Iterator it2 = arrayList.get(i13).f3038a.iterator();
                while (it2.hasNext()) {
                    Fragment fragment3 = ((FragmentTransaction.Op) it2.next()).f3056b;
                    if (fragment3 != null && fragment3.mFragmentManager != null) {
                        this.mFragmentStore.q(m(fragment3));
                    }
                }
            }
        }
        r1 = 1;
        executeOps(arrayList, arrayList2, i2, i3);
        if (f2982e) {
        }
        while (i9 < i8) {
        }
        if (z4) {
        }
    }

    private void executePostponedTransaction(@Nullable ArrayList<BackStackRecord> arrayList, @Nullable ArrayList<Boolean> arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList<StartEnterTransitionListener> arrayList3 = this.mPostponedTransactions;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            StartEnterTransitionListener startEnterTransitionListener = this.mPostponedTransactions.get(i2);
            if (arrayList == null || startEnterTransitionListener.f3008a || (indexOf2 = arrayList.indexOf(startEnterTransitionListener.f3009b)) == -1 || arrayList2 == null || !arrayList2.get(indexOf2).booleanValue()) {
                if (startEnterTransitionListener.isReady() || (arrayList != null && startEnterTransitionListener.f3009b.j(arrayList, 0, arrayList.size()))) {
                    this.mPostponedTransactions.remove(i2);
                    i2--;
                    size--;
                    if (arrayList == null || startEnterTransitionListener.f3008a || (indexOf = arrayList.indexOf(startEnterTransitionListener.f3009b)) == -1 || arrayList2 == null || !arrayList2.get(indexOf).booleanValue()) {
                        startEnterTransitionListener.b();
                    }
                }
                i2++;
            } else {
                this.mPostponedTransactions.remove(i2);
                i2--;
                size--;
            }
            startEnterTransitionListener.a();
            i2++;
        }
    }

    @NonNull
    public static <F extends Fragment> F findFragment(@NonNull View view) {
        F f2 = (F) findViewFragment(view);
        if (f2 != null) {
            return f2;
        }
        throw new IllegalStateException("View " + view + " does not have a Fragment set");
    }

    @Nullable
    private static Fragment findViewFragment(@NonNull View view) {
        while (view != null) {
            Fragment X = X(view);
            if (X != null) {
                return X;
            }
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    private void forcePostponedTransactions() {
        if (f2982e) {
            for (SpecialEffectsController specialEffectsController : collectAllSpecialEffectsController()) {
                specialEffectsController.h();
            }
        } else if (this.mPostponedTransactions != null) {
            while (!this.mPostponedTransactions.isEmpty()) {
                this.mPostponedTransactions.remove(0).b();
            }
        }
    }

    private boolean generateOpsForPendingActions(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        synchronized (this.mPendingActions) {
            if (this.mPendingActions.isEmpty()) {
                return false;
            }
            int size = this.mPendingActions.size();
            boolean z = false;
            for (int i2 = 0; i2 < size; i2++) {
                z |= this.mPendingActions.get(i2).generateOps(arrayList, arrayList2);
            }
            this.mPendingActions.clear();
            this.mHost.c().removeCallbacks(this.mExecCommit);
            return z;
        }
    }

    @NonNull
    private FragmentManagerViewModel getChildNonConfig(@NonNull Fragment fragment) {
        return this.mNonConfig.d(fragment);
    }

    private ViewGroup getFragmentContainer(@NonNull Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.mContainerId > 0 && this.mContainer.onHasView()) {
            View onFindViewById = this.mContainer.onFindViewById(fragment.mContainerId);
            if (onFindViewById instanceof ViewGroup) {
                return (ViewGroup) onFindViewById;
            }
        }
        return null;
    }

    private boolean isMenuAvailable(@NonNull Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.k();
    }

    private void makeRemovedFragmentsInvisible(@NonNull ArraySet<Fragment> arraySet) {
        int size = arraySet.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment valueAt = arraySet.valueAt(i2);
            if (!valueAt.mAdded) {
                View requireView = valueAt.requireView();
                valueAt.mPostponedAlpha = requireView.getAlpha();
                requireView.setAlpha(0.0f);
            }
        }
    }

    private boolean popBackStackImmediate(@Nullable String str, int i2, int i3) {
        K(false);
        ensureExecReady(true);
        Fragment fragment = this.f2985c;
        if (fragment == null || i2 >= 0 || str != null || !fragment.getChildFragmentManager().popBackStackImmediate()) {
            boolean q0 = q0(this.mTmpRecords, this.mTmpIsPop, str, i2, i3);
            if (q0) {
                this.mExecutingActions = true;
                try {
                    removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                } finally {
                    cleanupExec();
                }
            }
            updateOnBackPressedCallbackEnabled();
            doPendingDeferredStart();
            this.mFragmentStore.b();
            return q0;
        }
        return true;
    }

    private int postponePostponableTransactions(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3, @NonNull ArraySet<Fragment> arraySet) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            BackStackRecord backStackRecord = arrayList.get(i5);
            boolean booleanValue = arrayList2.get(i5).booleanValue();
            if (backStackRecord.k() && !backStackRecord.j(arrayList, i5 + 1, i3)) {
                if (this.mPostponedTransactions == null) {
                    this.mPostponedTransactions = new ArrayList<>();
                }
                StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord, booleanValue);
                this.mPostponedTransactions.add(startEnterTransitionListener);
                backStackRecord.l(startEnterTransitionListener);
                if (booleanValue) {
                    backStackRecord.f();
                } else {
                    backStackRecord.g(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, backStackRecord);
                }
                addAddedFragments(arraySet);
            }
        }
        return i4;
    }

    private void removeRedundantOperationsAndExecute(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        executePostponedTransaction(arrayList, arrayList2);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!arrayList.get(i2).f3053p) {
                if (i3 != i2) {
                    executeOpsTogether(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (arrayList2.get(i2).booleanValue()) {
                    while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).f3053p) {
                        i3++;
                    }
                }
                executeOpsTogether(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            executeOpsTogether(arrayList, arrayList2, i3, size);
        }
    }

    private void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i2 = 0; i2 < this.mBackStackChangeListeners.size(); i2++) {
                this.mBackStackChangeListeners.get(i2).onBackStackChanged();
            }
        }
    }

    private void setVisibleRemovingFragment(@NonNull Fragment fragment) {
        ViewGroup fragmentContainer = getFragmentContainer(fragment);
        if (fragmentContainer == null || fragment.getEnterAnim() + fragment.getExitAnim() + fragment.getPopEnterAnim() + fragment.getPopExitAnim() <= 0) {
            return;
        }
        int i2 = R.id.visible_removing_fragment_view_tag;
        if (fragmentContainer.getTag(i2) == null) {
            fragmentContainer.setTag(i2, fragment);
        }
        ((Fragment) fragmentContainer.getTag(i2)).setPopDirection(fragment.getPopDirection());
    }

    private void startPendingDeferredFragments() {
        for (FragmentStateManager fragmentStateManager : this.mFragmentStore.l()) {
            p0(fragmentStateManager);
        }
    }

    private void throwException(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        try {
            if (fragmentHostCallback != null) {
                fragmentHostCallback.onDump("  ", null, printWriter, new String[0]);
            } else {
                dump("  ", null, printWriter, new String[0]);
            }
        } catch (Exception e2) {
            Log.e("FragmentManager", "Failed dumping state", e2);
        }
        throw runtimeException;
    }

    private void updateOnBackPressedCallbackEnabled() {
        synchronized (this.mPendingActions) {
            boolean z = true;
            if (this.mPendingActions.isEmpty()) {
                this.mOnBackPressedCallback.setEnabled((getBackStackEntryCount() <= 0 || !e0(this.mParent)) ? false : false);
            } else {
                this.mOnBackPressedCallback.setEnabled(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int x0(int i2) {
        if (i2 != 4097) {
            if (i2 != 4099) {
                if (i2 != 8194) {
                    return 0;
                }
                return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
            }
            return FragmentTransaction.TRANSIT_FRAGMENT_FADE;
        }
        return 8194;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A(@NonNull Menu menu) {
        if (this.f2984b < 1) {
            return;
        }
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A0(@NonNull Fragment fragment, boolean z) {
        ViewGroup fragmentContainer = getFragmentContainer(fragment);
        if (fragmentContainer == null || !(fragmentContainer instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) fragmentContainer).setDrawDisappearingViewsLast(!z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B() {
        dispatchStateChange(5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B0(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        if (fragment.equals(M(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this)) {
            fragment.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void C(boolean z) {
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void C0(@Nullable Fragment fragment) {
        if (fragment == null || (fragment.equals(M(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this))) {
            Fragment fragment2 = this.f2985c;
            this.f2985c = fragment;
            dispatchParentPrimaryNavigationFragmentChanged(fragment2);
            dispatchParentPrimaryNavigationFragmentChanged(this.f2985c);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean D(@NonNull Menu menu) {
        boolean z = false;
        if (this.f2984b < 1) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment != null && d0(fragment) && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D0(@NonNull Fragment fragment) {
        if (c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("show: ");
            sb.append(fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void E() {
        updateOnBackPressedCallbackEnabled();
        dispatchParentPrimaryNavigationFragmentChanged(this.f2985c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.l(false);
        dispatchStateChange(7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void G() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.l(false);
        dispatchStateChange(5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void H() {
        this.mStopped = true;
        this.mNonConfig.l(true);
        dispatchStateChange(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I() {
        dispatchStateChange(2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void J(@NonNull OpGenerator opGenerator, boolean z) {
        if (!z) {
            if (this.mHost == null) {
                if (!this.mDestroyed) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            checkStateLoss();
        }
        synchronized (this.mPendingActions) {
            if (this.mHost == null) {
                if (!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
                return;
            }
            this.mPendingActions.add(opGenerator);
            z0();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean K(boolean z) {
        ensureExecReady(z);
        boolean z2 = false;
        while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                cleanupExec();
                z2 = true;
            } catch (Throwable th) {
                cleanupExec();
                throw th;
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.b();
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void L(@NonNull OpGenerator opGenerator, boolean z) {
        if (z && (this.mHost == null || this.mDestroyed)) {
            return;
        }
        ensureExecReady(z);
        if (opGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Fragment M(@NonNull String str) {
        return this.mFragmentStore.f(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment N(@NonNull String str) {
        return this.mFragmentStore.i(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int O() {
        return this.mFragmentStore.k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public List P() {
        return this.mFragmentStore.m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public FragmentContainer Q() {
        return this.mContainer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public FragmentStore R() {
        return this.mFragmentStore;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public FragmentHostCallback S() {
        return this.mHost;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public LayoutInflater.Factory2 T() {
        return this.mLayoutInflaterFactory;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public FragmentLifecycleCallbacksDispatcher U() {
        return this.mLifecycleCallbacksDispatcher;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Fragment V() {
        return this.mParent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public SpecialEffectsControllerFactory W() {
        SpecialEffectsControllerFactory specialEffectsControllerFactory = this.mSpecialEffectsControllerFactory;
        if (specialEffectsControllerFactory != null) {
            return specialEffectsControllerFactory;
        }
        Fragment fragment = this.mParent;
        return fragment != null ? fragment.mFragmentManager.W() : this.mDefaultSpecialEffectsControllerFactory;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public ViewModelStore Y(@NonNull Fragment fragment) {
        return this.mNonConfig.h(fragment);
    }

    void Z() {
        K(true);
        if (this.mOnBackPressedCallback.isEnabled()) {
            popBackStackImmediate();
        } else {
            this.mOnBackPressedDispatcher.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a0(@NonNull Fragment fragment) {
        if (c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("hide: ");
            sb.append(fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
        setVisibleRemovingFragment(fragment);
    }

    public void addFragmentOnAttachListener(@NonNull FragmentOnAttachListener fragmentOnAttachListener) {
        this.mOnAttachListeners.add(fragmentOnAttachListener);
    }

    public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener onBackStackChangedListener) {
        if (this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList<>();
        }
        this.mBackStackChangeListeners.add(onBackStackChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b0(@NonNull Fragment fragment) {
        if (fragment.mAdded && isMenuAvailable(fragment)) {
            this.mNeedMenuInvalidate = true;
        }
    }

    @NonNull
    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void clearFragmentResult(@NonNull String str) {
        this.mResults.remove(str);
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void clearFragmentResultListener(@NonNull String str) {
        LifecycleAwareResultListener remove = this.mResultListeners.remove(str);
        if (remove != null) {
            remove.removeObserver();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(BackStackRecord backStackRecord) {
        if (this.f2983a == null) {
            this.f2983a = new ArrayList();
        }
        this.f2983a.add(backStackRecord);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d0(@Nullable Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.isMenuVisible();
    }

    public void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        this.mFragmentStore.e(str, fileDescriptor, printWriter, strArr);
        ArrayList<Fragment> arrayList = this.mCreatedMenus;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(this.mCreatedMenus.get(i2).toString());
            }
        }
        ArrayList arrayList2 = this.f2983a;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                BackStackRecord backStackRecord = (BackStackRecord) this.f2983a.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                backStackRecord.dump(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.mBackStackIndex.get());
        synchronized (this.mPendingActions) {
            int size3 = this.mPendingActions.size();
            if (size3 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size3; i4++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println(this.mPendingActions.get(i4));
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.mHost);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.mContainer);
        if (this.mParent != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.mParent);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f2984b);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
    }

    void e(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
        if (this.mExitAnimationCancellationSignals.get(fragment) == null) {
            this.mExitAnimationCancellationSignals.put(fragment, new HashSet<>());
        }
        this.mExitAnimationCancellationSignals.get(fragment).add(cancellationSignal);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e0(@Nullable Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.mFragmentManager;
        return fragment.equals(fragmentManager.getPrimaryNavigationFragment()) && e0(fragmentManager.mParent);
    }

    public boolean executePendingTransactions() {
        boolean K = K(true);
        forcePostponedTransactions();
        return K;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentStateManager f(@NonNull Fragment fragment) {
        if (c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("add: ");
            sb.append(fragment);
        }
        FragmentStateManager m2 = m(fragment);
        fragment.mFragmentManager = this;
        this.mFragmentStore.q(m2);
        if (!fragment.mDetached) {
            this.mFragmentStore.a(fragment);
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
        }
        return m2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f0(int i2) {
        return this.f2984b >= i2;
    }

    @Nullable
    public Fragment findFragmentById(@IdRes int i2) {
        return this.mFragmentStore.g(i2);
    }

    @Nullable
    public Fragment findFragmentByTag(@Nullable String str) {
        return this.mFragmentStore.h(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(@NonNull Fragment fragment) {
        this.mNonConfig.a(fragment);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g0(@NonNull Fragment fragment, @NonNull String[] strArr, int i2) {
        if (this.mRequestPermissions == null) {
            this.mHost.onRequestPermissionsFromFragment(fragment, strArr, i2);
            return;
        }
        this.f2986d.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
        this.mRequestPermissions.launch(strArr);
    }

    @NonNull
    public BackStackEntry getBackStackEntryAt(int i2) {
        return (BackStackEntry) this.f2983a.get(i2);
    }

    public int getBackStackEntryCount() {
        ArrayList arrayList = this.f2983a;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Nullable
    public Fragment getFragment(@NonNull Bundle bundle, @NonNull String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment M = M(string);
        if (M == null) {
            throwException(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return M;
    }

    @NonNull
    public FragmentFactory getFragmentFactory() {
        FragmentFactory fragmentFactory = this.mFragmentFactory;
        if (fragmentFactory != null) {
            return fragmentFactory;
        }
        Fragment fragment = this.mParent;
        return fragment != null ? fragment.mFragmentManager.getFragmentFactory() : this.mHostFragmentFactory;
    }

    @NonNull
    public List<Fragment> getFragments() {
        return this.mFragmentStore.o();
    }

    @Nullable
    public Fragment getPrimaryNavigationFragment() {
        return this.f2985c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h() {
        return this.mBackStackIndex.getAndIncrement();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h0(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i2, @Nullable Bundle bundle) {
        if (this.mStartActivityForResult == null) {
            this.mHost.onStartActivityFromFragment(fragment, intent, i2, bundle);
            return;
        }
        this.f2986d.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
        if (intent != null && bundle != null) {
            intent.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundle);
        }
        this.mStartActivityForResult.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    @SuppressLint({"SyntheticAccessor"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void i(@NonNull FragmentHostCallback fragmentHostCallback, @NonNull FragmentContainer fragmentContainer, @Nullable final Fragment fragment) {
        FragmentOnAttachListener fragmentOnAttachListener;
        FragmentHostCallback<?> fragmentHostCallback2;
        String str;
        if (this.mHost != null) {
            throw new IllegalStateException("Already attached");
        }
        this.mHost = fragmentHostCallback;
        this.mContainer = fragmentContainer;
        this.mParent = fragment;
        if (fragment == null) {
            if (fragmentHostCallback instanceof FragmentOnAttachListener) {
                fragmentOnAttachListener = (FragmentOnAttachListener) fragmentHostCallback;
            }
            if (this.mParent != null) {
                updateOnBackPressedCallbackEnabled();
            }
            if (fragmentHostCallback instanceof OnBackPressedDispatcherOwner) {
                OnBackPressedDispatcherOwner onBackPressedDispatcherOwner = (OnBackPressedDispatcherOwner) fragmentHostCallback;
                OnBackPressedDispatcher onBackPressedDispatcher = onBackPressedDispatcherOwner.getOnBackPressedDispatcher();
                this.mOnBackPressedDispatcher = onBackPressedDispatcher;
                LifecycleOwner lifecycleOwner = onBackPressedDispatcherOwner;
                if (fragment != null) {
                    lifecycleOwner = fragment;
                }
                onBackPressedDispatcher.addCallback(lifecycleOwner, this.mOnBackPressedCallback);
            }
            this.mNonConfig = fragment == null ? fragment.mFragmentManager.getChildNonConfig(fragment) : fragmentHostCallback instanceof ViewModelStoreOwner ? FragmentManagerViewModel.e(((ViewModelStoreOwner) fragmentHostCallback).getViewModelStore()) : new FragmentManagerViewModel(false);
            this.mNonConfig.l(isStateSaved());
            this.mFragmentStore.y(this.mNonConfig);
            fragmentHostCallback2 = this.mHost;
            if (fragmentHostCallback2 instanceof ActivityResultRegistryOwner) {
                return;
            }
            ActivityResultRegistry activityResultRegistry = ((ActivityResultRegistryOwner) fragmentHostCallback2).getActivityResultRegistry();
            if (fragment != null) {
                str = fragment.mWho + ":";
            } else {
                str = "";
            }
            String str2 = "FragmentManager:" + str;
            this.mStartActivityForResult = activityResultRegistry.register(str2 + "StartActivityForResult", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: androidx.fragment.app.FragmentManager.9
                @Override // androidx.activity.result.ActivityResultCallback
                public void onActivityResult(ActivityResult activityResult) {
                    LaunchedFragmentInfo launchedFragmentInfo = (LaunchedFragmentInfo) FragmentManager.this.f2986d.pollFirst();
                    if (launchedFragmentInfo == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("No Activities were started for result for ");
                        sb.append(this);
                        return;
                    }
                    String str3 = launchedFragmentInfo.f3002a;
                    int i2 = launchedFragmentInfo.f3003b;
                    Fragment i3 = FragmentManager.this.mFragmentStore.i(str3);
                    if (i3 != null) {
                        i3.onActivityResult(i2, activityResult.getResultCode(), activityResult.getData());
                        return;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Activity result delivered for unknown Fragment ");
                    sb2.append(str3);
                }
            });
            this.mStartIntentSenderForResult = activityResultRegistry.register(str2 + "StartIntentSenderForResult", new FragmentIntentSenderContract(), new ActivityResultCallback<ActivityResult>() { // from class: androidx.fragment.app.FragmentManager.10
                @Override // androidx.activity.result.ActivityResultCallback
                public void onActivityResult(ActivityResult activityResult) {
                    LaunchedFragmentInfo launchedFragmentInfo = (LaunchedFragmentInfo) FragmentManager.this.f2986d.pollFirst();
                    if (launchedFragmentInfo == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("No IntentSenders were started for ");
                        sb.append(this);
                        return;
                    }
                    String str3 = launchedFragmentInfo.f3002a;
                    int i2 = launchedFragmentInfo.f3003b;
                    Fragment i3 = FragmentManager.this.mFragmentStore.i(str3);
                    if (i3 != null) {
                        i3.onActivityResult(i2, activityResult.getResultCode(), activityResult.getData());
                        return;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Intent Sender result delivered for unknown Fragment ");
                    sb2.append(str3);
                }
            });
            this.mRequestPermissions = activityResultRegistry.register(str2 + "RequestPermissions", new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() { // from class: androidx.fragment.app.FragmentManager.11
                @Override // androidx.activity.result.ActivityResultCallback
                @SuppressLint({"SyntheticAccessor"})
                public void onActivityResult(Map<String, Boolean> map) {
                    String[] strArr = (String[]) map.keySet().toArray(new String[0]);
                    ArrayList arrayList = new ArrayList(map.values());
                    int[] iArr = new int[arrayList.size()];
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        iArr[i2] = ((Boolean) arrayList.get(i2)).booleanValue() ? 0 : -1;
                    }
                    LaunchedFragmentInfo launchedFragmentInfo = (LaunchedFragmentInfo) FragmentManager.this.f2986d.pollFirst();
                    if (launchedFragmentInfo == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("No permissions were requested for ");
                        sb.append(this);
                        return;
                    }
                    String str3 = launchedFragmentInfo.f3002a;
                    int i3 = launchedFragmentInfo.f3003b;
                    Fragment i4 = FragmentManager.this.mFragmentStore.i(str3);
                    if (i4 != null) {
                        i4.onRequestPermissionsResult(i3, strArr, iArr);
                        return;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Permission request result delivered for unknown Fragment ");
                    sb2.append(str3);
                }
            });
            return;
        }
        fragmentOnAttachListener = new FragmentOnAttachListener(this) { // from class: androidx.fragment.app.FragmentManager.8
            @Override // androidx.fragment.app.FragmentOnAttachListener
            public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment2) {
                fragment.onAttachFragment(fragment2);
            }
        };
        addFragmentOnAttachListener(fragmentOnAttachListener);
        if (this.mParent != null) {
        }
        if (fragmentHostCallback instanceof OnBackPressedDispatcherOwner) {
        }
        this.mNonConfig = fragment == null ? fragment.mFragmentManager.getChildNonConfig(fragment) : fragmentHostCallback instanceof ViewModelStoreOwner ? FragmentManagerViewModel.e(((ViewModelStoreOwner) fragmentHostCallback).getViewModelStore()) : new FragmentManagerViewModel(false);
        this.mNonConfig.l(isStateSaved());
        this.mFragmentStore.y(this.mNonConfig);
        fragmentHostCallback2 = this.mHost;
        if (fragmentHostCallback2 instanceof ActivityResultRegistryOwner) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i0(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i2, @Nullable Intent intent, int i3, int i4, int i5, @Nullable Bundle bundle) {
        Intent intent2;
        if (this.mStartIntentSenderForResult == null) {
            this.mHost.onStartIntentSenderFromFragment(fragment, intentSender, i2, intent, i3, i4, i5, bundle);
            return;
        }
        if (bundle != null) {
            if (intent == null) {
                intent2 = new Intent();
                intent2.putExtra(EXTRA_CREATED_FILLIN_INTENT, true);
            } else {
                intent2 = intent;
            }
            if (c0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("ActivityOptions ");
                sb.append(bundle);
                sb.append(" were added to fillInIntent ");
                sb.append(intent2);
                sb.append(" for fragment ");
                sb.append(fragment);
            }
            intent2.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundle);
        } else {
            intent2 = intent;
        }
        IntentSenderRequest build = new IntentSenderRequest.Builder(intentSender).setFillInIntent(intent2).setFlags(i4, i3).build();
        this.f2986d.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
        if (c0(2)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Fragment ");
            sb2.append(fragment);
            sb2.append("is launching an IntentSender for result ");
        }
        this.mStartIntentSenderForResult.launch(build);
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(@NonNull Fragment fragment) {
        if (c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("attach: ");
            sb.append(fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            this.mFragmentStore.a(fragment);
            if (c0(2)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("add from attach: ");
                sb2.append(fragment);
            }
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j0(@NonNull Fragment fragment) {
        if (!this.mFragmentStore.c(fragment.mWho)) {
            if (c0(3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Ignoring moving ");
                sb.append(fragment);
                sb.append(" to state ");
                sb.append(this.f2984b);
                sb.append("since it is not added to ");
                sb.append(this);
                return;
            }
            return;
        }
        l0(fragment);
        View view = fragment.mView;
        if (view != null && fragment.mIsNewlyAdded && fragment.mContainer != null) {
            float f2 = fragment.mPostponedAlpha;
            if (f2 > 0.0f) {
                view.setAlpha(f2);
            }
            fragment.mPostponedAlpha = 0.0f;
            fragment.mIsNewlyAdded = false;
            FragmentAnim.AnimationOrAnimator b2 = FragmentAnim.b(this.mHost.b(), fragment, true, fragment.getPopDirection());
            if (b2 != null) {
                Animation animation = b2.animation;
                if (animation != null) {
                    fragment.mView.startAnimation(animation);
                } else {
                    b2.animator.setTarget(fragment.mView);
                    b2.animator.start();
                }
            }
        }
        if (fragment.mHiddenChanged) {
            completeShowHideFragment(fragment);
        }
    }

    boolean k() {
        boolean z = false;
        for (Fragment fragment : this.mFragmentStore.m()) {
            if (fragment != null) {
                z = isMenuAvailable(fragment);
                continue;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k0(int i2, boolean z) {
        FragmentHostCallback<?> fragmentHostCallback;
        if (this.mHost == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.f2984b) {
            this.f2984b = i2;
            if (f2982e) {
                this.mFragmentStore.s();
            } else {
                for (Fragment fragment : this.mFragmentStore.o()) {
                    j0(fragment);
                }
                for (FragmentStateManager fragmentStateManager : this.mFragmentStore.l()) {
                    Fragment k2 = fragmentStateManager.k();
                    if (!k2.mIsNewlyAdded) {
                        j0(k2);
                    }
                    if (k2.mRemoving && !k2.isInBackStack()) {
                        this.mFragmentStore.r(fragmentStateManager);
                    }
                }
            }
            startPendingDeferredFragments();
            if (this.mNeedMenuInvalidate && (fragmentHostCallback = this.mHost) != null && this.f2984b == 7) {
                fragmentHostCallback.onSupportInvalidateOptionsMenu();
                this.mNeedMenuInvalidate = false;
            }
        }
    }

    void l(@NonNull BackStackRecord backStackRecord, boolean z, boolean z2, boolean z3) {
        if (z) {
            backStackRecord.g(z3);
        } else {
            backStackRecord.f();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(backStackRecord);
        arrayList2.add(Boolean.valueOf(z));
        if (z2 && this.f2984b >= 1) {
            FragmentTransition.h(this.mHost.b(), this.mContainer, arrayList, arrayList2, 0, 1, true, this.mFragmentTransitionCallback);
        }
        if (z3) {
            k0(this.f2984b, true);
        }
        for (Fragment fragment : this.mFragmentStore.m()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && backStackRecord.i(fragment.mContainerId)) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                if (z3) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l0(@NonNull Fragment fragment) {
        m0(fragment, this.f2984b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public FragmentStateManager m(@NonNull Fragment fragment) {
        FragmentStateManager n2 = this.mFragmentStore.n(fragment.mWho);
        if (n2 != null) {
            return n2;
        }
        FragmentStateManager fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment);
        fragmentStateManager.n(this.mHost.b().getClassLoader());
        fragmentStateManager.s(this.f2984b);
        return fragmentStateManager;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
        if (r2 != 5) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0150  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void m0(@NonNull Fragment fragment, int i2) {
        ViewGroup viewGroup;
        FragmentStateManager n2 = this.mFragmentStore.n(fragment.mWho);
        int i3 = 1;
        if (n2 == null) {
            n2 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment);
            n2.s(1);
        }
        if (fragment.mFromLayout && fragment.mInLayout && fragment.mState == 2) {
            i2 = Math.max(i2, 2);
        }
        int min = Math.min(i2, n2.d());
        int i4 = fragment.mState;
        if (i4 <= min) {
            if (i4 < min && !this.mExitAnimationCancellationSignals.isEmpty()) {
                cancelExitAnimation(fragment);
            }
            int i5 = fragment.mState;
            if (i5 != -1) {
                if (i5 != 0) {
                    if (i5 != 1) {
                        if (i5 != 2) {
                            if (i5 != 4) {
                            }
                            if (min > 4) {
                                n2.t();
                            }
                            if (min > 5) {
                                n2.o();
                            }
                        }
                        if (min > 2) {
                            n2.a();
                        }
                        if (min > 4) {
                        }
                        if (min > 5) {
                        }
                    }
                    if (min > -1) {
                        n2.j();
                    }
                    if (min > 1) {
                        n2.f();
                    }
                    if (min > 2) {
                    }
                    if (min > 4) {
                    }
                    if (min > 5) {
                    }
                }
            } else if (min > -1) {
                n2.c();
            }
            if (min > 0) {
                n2.e();
            }
            if (min > -1) {
            }
            if (min > 1) {
            }
            if (min > 2) {
            }
            if (min > 4) {
            }
            if (min > 5) {
            }
        } else if (i4 > min) {
            if (i4 != 0) {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 4) {
                            if (i4 != 5) {
                                if (i4 == 7) {
                                    if (min < 7) {
                                        n2.m();
                                    }
                                }
                            }
                            if (min < 5) {
                                n2.u();
                            }
                        }
                        if (min < 4) {
                            if (c0(3)) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("movefrom ACTIVITY_CREATED: ");
                                sb.append(fragment);
                            }
                            if (fragment.mView != null && this.mHost.onShouldSaveFragmentState(fragment) && fragment.mSavedViewState == null) {
                                n2.r();
                            }
                        }
                    }
                    if (min < 2) {
                        FragmentAnim.AnimationOrAnimator animationOrAnimator = null;
                        View view = fragment.mView;
                        if (view != null && (viewGroup = fragment.mContainer) != null) {
                            viewGroup.endViewTransition(view);
                            fragment.mView.clearAnimation();
                            if (!fragment.isRemovingParent()) {
                                if (this.f2984b > -1 && !this.mDestroyed && fragment.mView.getVisibility() == 0 && fragment.mPostponedAlpha >= 0.0f) {
                                    animationOrAnimator = FragmentAnim.b(this.mHost.b(), fragment, false, fragment.getPopDirection());
                                }
                                fragment.mPostponedAlpha = 0.0f;
                                ViewGroup viewGroup2 = fragment.mContainer;
                                View view2 = fragment.mView;
                                if (animationOrAnimator != null) {
                                    FragmentAnim.a(fragment, animationOrAnimator, this.mFragmentTransitionCallback);
                                }
                                viewGroup2.removeView(view2);
                                if (c0(2)) {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("Removing view ");
                                    sb2.append(view2);
                                    sb2.append(" for fragment ");
                                    sb2.append(fragment);
                                    sb2.append(" from container ");
                                    sb2.append(viewGroup2);
                                }
                                if (viewGroup2 != fragment.mContainer) {
                                    return;
                                }
                            }
                        }
                        if (this.mExitAnimationCancellationSignals.get(fragment) == null) {
                            n2.h();
                        }
                    }
                }
                if (min < 1) {
                    if (this.mExitAnimationCancellationSignals.get(fragment) == null) {
                        n2.g();
                    }
                    if (i3 < 0) {
                        n2.i();
                    }
                    min = i3;
                }
            }
            i3 = min;
            if (i3 < 0) {
            }
            min = i3;
        }
        if (fragment.mState != min) {
            if (c0(3)) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("moveToState: Fragment state for ");
                sb3.append(fragment);
                sb3.append(" not updated inline; expected state ");
                sb3.append(min);
                sb3.append(" found ");
                sb3.append(fragment.mState);
            }
            fragment.mState = min;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(@NonNull Fragment fragment) {
        if (c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("detach: ");
            sb.append(fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (c0(2)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("remove from detach: ");
                sb2.append(fragment);
            }
            this.mFragmentStore.t(fragment);
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
            setVisibleRemovingFragment(fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n0() {
        if (this.mHost == null) {
            return;
        }
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.l(false);
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.l(false);
        dispatchStateChange(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o0(@NonNull FragmentContainerView fragmentContainerView) {
        View view;
        for (FragmentStateManager fragmentStateManager : this.mFragmentStore.l()) {
            Fragment k2 = fragmentStateManager.k();
            if (k2.mContainerId == fragmentContainerView.getId() && (view = k2.mView) != null && view.getParent() == null) {
                k2.mContainer = fragmentContainerView;
                fragmentStateManager.b();
            }
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public FragmentTransaction openTransaction() {
        return beginTransaction();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.l(false);
        dispatchStateChange(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p0(@NonNull FragmentStateManager fragmentStateManager) {
        Fragment k2 = fragmentStateManager.k();
        if (k2.mDeferStart) {
            if (this.mExecutingActions) {
                this.mHavePendingDeferredStart = true;
                return;
            }
            k2.mDeferStart = false;
            if (f2982e) {
                fragmentStateManager.l();
            } else {
                l0(k2);
            }
        }
    }

    public void popBackStack() {
        J(new PopBackStackState(null, -1, 0), false);
    }

    public void popBackStack(int i2, int i3) {
        if (i2 >= 0) {
            J(new PopBackStackState(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public void popBackStack(@Nullable String str, int i2) {
        J(new PopBackStackState(str, -1, i2), false);
    }

    public boolean popBackStackImmediate() {
        return popBackStackImmediate(null, -1, 0);
    }

    public boolean popBackStackImmediate(int i2, int i3) {
        if (i2 >= 0) {
            return popBackStackImmediate(null, i2, i3);
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public boolean popBackStackImmediate(@Nullable String str, int i2) {
        return popBackStackImmediate(str, -1, i2);
    }

    public void putFragment(@NonNull Bundle bundle, @NonNull String str, @NonNull Fragment fragment) {
        if (fragment.mFragmentManager != this) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.mWho);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(@NonNull Configuration configuration) {
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    boolean q0(@NonNull ArrayList arrayList, @NonNull ArrayList arrayList2, @Nullable String str, int i2, int i3) {
        int i4;
        ArrayList arrayList3 = this.f2983a;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.f2983a.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    BackStackRecord backStackRecord = (BackStackRecord) this.f2983a.get(size2);
                    if ((str != null && str.equals(backStackRecord.getName())) || (i2 >= 0 && i2 == backStackRecord.f2880t)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        BackStackRecord backStackRecord2 = (BackStackRecord) this.f2983a.get(size2);
                        if (str == null || !str.equals(backStackRecord2.getName())) {
                            if (i2 < 0 || i2 != backStackRecord2.f2880t) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            } else {
                i4 = -1;
            }
            if (i4 == this.f2983a.size() - 1) {
                return false;
            }
            for (int size3 = this.f2983a.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.f2983a.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean r(@NonNull MenuItem menuItem) {
        if (this.f2984b < 1) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void r0(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
        HashSet<CancellationSignal> hashSet = this.mExitAnimationCancellationSignals.get(fragment);
        if (hashSet != null && hashSet.remove(cancellationSignal) && hashSet.isEmpty()) {
            this.mExitAnimationCancellationSignals.remove(fragment);
            if (fragment.mState < 5) {
                destroyFragmentView(fragment);
                l0(fragment);
            }
        }
    }

    public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.mLifecycleCallbacksDispatcher.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, z);
    }

    public void removeFragmentOnAttachListener(@NonNull FragmentOnAttachListener fragmentOnAttachListener) {
        this.mOnAttachListeners.remove(fragmentOnAttachListener);
    }

    public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener onBackStackChangedListener) {
        ArrayList<OnBackStackChangedListener> arrayList = this.mBackStackChangeListeners;
        if (arrayList != null) {
            arrayList.remove(onBackStackChangedListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.l(false);
        dispatchStateChange(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s0(@NonNull Fragment fragment) {
        if (c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("remove: ");
            sb.append(fragment);
            sb.append(" nesting=");
            sb.append(fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            this.mFragmentStore.t(fragment);
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mRemoving = true;
            setVisibleRemovingFragment(fragment);
        }
    }

    @Nullable
    public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment fragment) {
        FragmentStateManager n2 = this.mFragmentStore.n(fragment.mWho);
        if (n2 == null || !n2.k().equals(fragment)) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        return n2.p();
    }

    public void setFragmentFactory(@NonNull FragmentFactory fragmentFactory) {
        this.mFragmentFactory = fragmentFactory;
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void setFragmentResult(@NonNull String str, @NonNull Bundle bundle) {
        LifecycleAwareResultListener lifecycleAwareResultListener = this.mResultListeners.get(str);
        if (lifecycleAwareResultListener == null || !lifecycleAwareResultListener.isAtLeast(Lifecycle.State.STARTED)) {
            this.mResults.put(str, bundle);
        } else {
            lifecycleAwareResultListener.onFragmentResult(str, bundle);
        }
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    @SuppressLint({"SyntheticAccessor"})
    public final void setFragmentResultListener(@NonNull final String str, @NonNull LifecycleOwner lifecycleOwner, @NonNull final FragmentResultListener fragmentResultListener) {
        final Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.fragment.app.FragmentManager.6
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner2, @NonNull Lifecycle.Event event) {
                Bundle bundle;
                if (event == Lifecycle.Event.ON_START && (bundle = (Bundle) FragmentManager.this.mResults.get(str)) != null) {
                    fragmentResultListener.onFragmentResult(str, bundle);
                    FragmentManager.this.clearFragmentResult(str);
                }
                if (event == Lifecycle.Event.ON_DESTROY) {
                    lifecycle.removeObserver(this);
                    FragmentManager.this.mResultListeners.remove(str);
                }
            }
        };
        lifecycle.addObserver(lifecycleEventObserver);
        LifecycleAwareResultListener put = this.mResultListeners.put(str, new LifecycleAwareResultListener(lifecycle, fragmentResultListener, lifecycleEventObserver));
        if (put != null) {
            put.removeObserver();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean t(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        if (this.f2984b < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment != null && d0(fragment) && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i2 = 0; i2 < this.mCreatedMenus.size(); i2++) {
                Fragment fragment2 = this.mCreatedMenus.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = arrayList;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t0(@NonNull Fragment fragment) {
        this.mNonConfig.j(fragment);
    }

    @NonNull
    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.mParent;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            obj = this.mParent;
        } else {
            FragmentHostCallback<?> fragmentHostCallback = this.mHost;
            if (fragmentHostCallback == null) {
                sb.append("null");
                sb.append("}}");
                return sb.toString();
            }
            sb.append(fragmentHostCallback.getClass().getSimpleName());
            sb.append("{");
            obj = this.mHost;
        }
        sb.append(Integer.toHexString(System.identityHashCode(obj)));
        sb.append("}");
        sb.append("}}");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u() {
        this.mDestroyed = true;
        K(true);
        endAnimatingAwayFragments();
        dispatchStateChange(-1);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
        if (this.mOnBackPressedDispatcher != null) {
            this.mOnBackPressedCallback.remove();
            this.mOnBackPressedDispatcher = null;
        }
        ActivityResultLauncher<Intent> activityResultLauncher = this.mStartActivityForResult;
        if (activityResultLauncher != null) {
            activityResultLauncher.unregister();
            this.mStartIntentSenderForResult.unregister();
            this.mRequestPermissions.unregister();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u0(@Nullable Parcelable parcelable, @Nullable FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (this.mHost instanceof ViewModelStoreOwner) {
            throwException(new IllegalStateException("You must use restoreSaveState when your FragmentHostCallback implements ViewModelStoreOwner"));
        }
        this.mNonConfig.k(fragmentManagerNonConfig);
        v0(parcelable);
    }

    public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        this.mLifecycleCallbacksDispatcher.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v() {
        dispatchStateChange(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public void v0(@Nullable Parcelable parcelable) {
        FragmentStateManager fragmentStateManager;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f3010a == null) {
            return;
        }
        this.mFragmentStore.u();
        Iterator it = fragmentManagerState.f3010a.iterator();
        while (it.hasNext()) {
            FragmentState fragmentState = (FragmentState) it.next();
            if (fragmentState != null) {
                Fragment c2 = this.mNonConfig.c(fragmentState.f3019b);
                if (c2 != null) {
                    if (c0(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("restoreSaveState: re-attaching retained ");
                        sb.append(c2);
                    }
                    fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, c2, fragmentState);
                } else {
                    fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, this.mHost.b().getClassLoader(), getFragmentFactory(), fragmentState);
                }
                Fragment k2 = fragmentStateManager.k();
                k2.mFragmentManager = this;
                if (c0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("restoreSaveState: active (");
                    sb2.append(k2.mWho);
                    sb2.append("): ");
                    sb2.append(k2);
                }
                fragmentStateManager.n(this.mHost.b().getClassLoader());
                this.mFragmentStore.q(fragmentStateManager);
                fragmentStateManager.s(this.f2984b);
            }
        }
        for (Fragment fragment : this.mNonConfig.f()) {
            if (!this.mFragmentStore.c(fragment.mWho)) {
                if (c0(2)) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Discarding retained Fragment ");
                    sb3.append(fragment);
                    sb3.append(" that was not found in the set of active Fragments ");
                    sb3.append(fragmentManagerState.f3010a);
                }
                this.mNonConfig.j(fragment);
                fragment.mFragmentManager = this;
                FragmentStateManager fragmentStateManager2 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment);
                fragmentStateManager2.s(1);
                fragmentStateManager2.l();
                fragment.mRemoving = true;
                fragmentStateManager2.l();
            }
        }
        this.mFragmentStore.v(fragmentManagerState.f3011b);
        if (fragmentManagerState.f3012c != null) {
            this.f2983a = new ArrayList(fragmentManagerState.f3012c.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f3012c;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                BackStackRecord instantiate = backStackStateArr[i2].instantiate(this);
                if (c0(2)) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("restoreAllState: back stack #");
                    sb4.append(i2);
                    sb4.append(" (index ");
                    sb4.append(instantiate.f2880t);
                    sb4.append("): ");
                    sb4.append(instantiate);
                    PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
                    instantiate.dump("  ", printWriter, false);
                    printWriter.close();
                }
                this.f2983a.add(instantiate);
                i2++;
            }
        } else {
            this.f2983a = null;
        }
        this.mBackStackIndex.set(fragmentManagerState.f3013d);
        String str = fragmentManagerState.f3014e;
        if (str != null) {
            Fragment M = M(str);
            this.f2985c = M;
            dispatchParentPrimaryNavigationFragmentChanged(M);
        }
        ArrayList arrayList = fragmentManagerState.f3015f;
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                Bundle bundle = (Bundle) fragmentManagerState.f3016g.get(i3);
                bundle.setClassLoader(this.mHost.b().getClassLoader());
                this.mResults.put(arrayList.get(i3), bundle);
            }
        }
        this.f2986d = new ArrayDeque(fragmentManagerState.f3017h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w() {
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public FragmentManagerNonConfig w0() {
        if (this.mHost instanceof ViewModelStoreOwner) {
            throwException(new IllegalStateException("You cannot use retainNonConfig when your FragmentHostCallback implements ViewModelStoreOwner."));
        }
        return this.mNonConfig.g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(boolean z) {
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y(@NonNull Fragment fragment) {
        Iterator<FragmentOnAttachListener> it = this.mOnAttachListeners.iterator();
        while (it.hasNext()) {
            it.next().onAttachFragment(this, fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Parcelable y0() {
        int size;
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        K(true);
        this.mStateSaved = true;
        this.mNonConfig.l(true);
        ArrayList w = this.mFragmentStore.w();
        BackStackState[] backStackStateArr = null;
        if (w.isEmpty()) {
            c0(2);
            return null;
        }
        ArrayList x = this.mFragmentStore.x();
        ArrayList arrayList = this.f2983a;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i2 = 0; i2 < size; i2++) {
                backStackStateArr[i2] = new BackStackState((BackStackRecord) this.f2983a.get(i2));
                if (c0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("saveAllState: adding back stack #");
                    sb.append(i2);
                    sb.append(": ");
                    sb.append(this.f2983a.get(i2));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f3010a = w;
        fragmentManagerState.f3011b = x;
        fragmentManagerState.f3012c = backStackStateArr;
        fragmentManagerState.f3013d = this.mBackStackIndex.get();
        Fragment fragment = this.f2985c;
        if (fragment != null) {
            fragmentManagerState.f3014e = fragment.mWho;
        }
        fragmentManagerState.f3015f.addAll(this.mResults.keySet());
        fragmentManagerState.f3016g.addAll(this.mResults.values());
        fragmentManagerState.f3017h = new ArrayList(this.f2986d);
        return fragmentManagerState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean z(@NonNull MenuItem menuItem) {
        if (this.f2984b < 1) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.o()) {
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void z0() {
        synchronized (this.mPendingActions) {
            ArrayList<StartEnterTransitionListener> arrayList = this.mPostponedTransactions;
            boolean z = (arrayList == null || arrayList.isEmpty()) ? false : true;
            boolean z2 = this.mPendingActions.size() == 1;
            if (z || z2) {
                this.mHost.c().removeCallbacks(this.mExecCommit);
                this.mHost.c().post(this.mExecCommit);
                updateOnBackPressedCallbackEnabled();
            }
        }
    }
}
