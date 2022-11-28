package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class SpecialEffectsController {

    /* renamed from: a  reason: collision with root package name */
    final ArrayList f3135a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    final ArrayList f3136b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    boolean f3137c = false;

    /* renamed from: d  reason: collision with root package name */
    boolean f3138d = false;
    private final ViewGroup mContainer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.fragment.app.SpecialEffectsController$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3143a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f3144b;

        static {
            int[] iArr = new int[Operation.LifecycleImpact.values().length];
            f3144b = iArr;
            try {
                iArr[Operation.LifecycleImpact.ADDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3144b[Operation.LifecycleImpact.REMOVING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3144b[Operation.LifecycleImpact.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Operation.State.values().length];
            f3143a = iArr2;
            try {
                iArr2[Operation.State.REMOVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3143a[Operation.State.VISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3143a[Operation.State.GONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3143a[Operation.State.INVISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class FragmentStateManagerOperation extends Operation {
        @NonNull
        private final FragmentStateManager mFragmentStateManager;

        FragmentStateManagerOperation(@NonNull Operation.State state, @NonNull Operation.LifecycleImpact lifecycleImpact, @NonNull FragmentStateManager fragmentStateManager, @NonNull CancellationSignal cancellationSignal) {
            super(state, lifecycleImpact, fragmentStateManager.k(), cancellationSignal);
            this.mFragmentStateManager = fragmentStateManager;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void complete() {
            super.complete();
            this.mFragmentStateManager.l();
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        void g() {
            if (c() == Operation.LifecycleImpact.ADDING) {
                Fragment k2 = this.mFragmentStateManager.k();
                View findFocus = k2.mView.findFocus();
                if (findFocus != null) {
                    k2.setFocusedView(findFocus);
                    if (FragmentManager.c0(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("requestFocus: Saved focused view ");
                        sb.append(findFocus);
                        sb.append(" for Fragment ");
                        sb.append(k2);
                    }
                }
                View requireView = getFragment().requireView();
                if (requireView.getParent() == null) {
                    this.mFragmentStateManager.b();
                    requireView.setAlpha(0.0f);
                }
                if (requireView.getAlpha() == 0.0f && requireView.getVisibility() == 0) {
                    requireView.setVisibility(4);
                }
                requireView.setAlpha(k2.getPostOnViewCreatedAlpha());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Operation {
        @NonNull
        private State mFinalState;
        @NonNull
        private final Fragment mFragment;
        @NonNull
        private LifecycleImpact mLifecycleImpact;
        @NonNull
        private final List<Runnable> mCompletionListeners = new ArrayList();
        @NonNull
        private final HashSet<CancellationSignal> mSpecialEffectsSignals = new HashSet<>();
        private boolean mIsCanceled = false;
        private boolean mIsComplete = false;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public enum LifecycleImpact {
            NONE,
            ADDING,
            REMOVING
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public enum State {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            /* JADX INFO: Access modifiers changed from: package-private */
            @NonNull
            public static State b(int i2) {
                if (i2 != 0) {
                    if (i2 != 4) {
                        if (i2 == 8) {
                            return GONE;
                        }
                        throw new IllegalArgumentException("Unknown visibility " + i2);
                    }
                    return INVISIBLE;
                }
                return VISIBLE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @NonNull
            public static State c(@NonNull View view) {
                return (view.getAlpha() == 0.0f && view.getVisibility() == 0) ? INVISIBLE : b(view.getVisibility());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public void a(@NonNull View view) {
                int i2;
                int i3 = AnonymousClass3.f3143a[ordinal()];
                if (i3 == 1) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        if (FragmentManager.c0(2)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("SpecialEffectsController: Removing view ");
                            sb.append(view);
                            sb.append(" from container ");
                            sb.append(viewGroup);
                        }
                        viewGroup.removeView(view);
                        return;
                    }
                    return;
                }
                if (i3 == 2) {
                    if (FragmentManager.c0(2)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("SpecialEffectsController: Setting view ");
                        sb2.append(view);
                        sb2.append(" to VISIBLE");
                    }
                    i2 = 0;
                } else if (i3 != 3) {
                    if (i3 != 4) {
                        return;
                    }
                    if (FragmentManager.c0(2)) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("SpecialEffectsController: Setting view ");
                        sb3.append(view);
                        sb3.append(" to INVISIBLE");
                    }
                    view.setVisibility(4);
                    return;
                } else {
                    if (FragmentManager.c0(2)) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("SpecialEffectsController: Setting view ");
                        sb4.append(view);
                        sb4.append(" to GONE");
                    }
                    i2 = 8;
                }
                view.setVisibility(i2);
            }
        }

        Operation(@NonNull State state, @NonNull LifecycleImpact lifecycleImpact, @NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            this.mFinalState = state;
            this.mLifecycleImpact = lifecycleImpact;
            this.mFragment = fragment;
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.SpecialEffectsController.Operation.1
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    Operation.this.b();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void a(@NonNull Runnable runnable) {
            this.mCompletionListeners.add(runnable);
        }

        final void b() {
            if (d()) {
                return;
            }
            this.mIsCanceled = true;
            if (this.mSpecialEffectsSignals.isEmpty()) {
                complete();
                return;
            }
            Iterator it = new ArrayList(this.mSpecialEffectsSignals).iterator();
            while (it.hasNext()) {
                ((CancellationSignal) it.next()).cancel();
            }
        }

        @NonNull
        LifecycleImpact c() {
            return this.mLifecycleImpact;
        }

        @CallSuper
        public void complete() {
            if (this.mIsComplete) {
                return;
            }
            if (FragmentManager.c0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("SpecialEffectsController: ");
                sb.append(this);
                sb.append(" has called complete.");
            }
            this.mIsComplete = true;
            for (Runnable runnable : this.mCompletionListeners) {
                runnable.run();
            }
        }

        public final void completeSpecialEffect(@NonNull CancellationSignal cancellationSignal) {
            if (this.mSpecialEffectsSignals.remove(cancellationSignal) && this.mSpecialEffectsSignals.isEmpty()) {
                complete();
            }
        }

        final boolean d() {
            return this.mIsCanceled;
        }

        final boolean e() {
            return this.mIsComplete;
        }

        final void f(@NonNull State state, @NonNull LifecycleImpact lifecycleImpact) {
            LifecycleImpact lifecycleImpact2;
            int i2 = AnonymousClass3.f3144b[lifecycleImpact.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3 && this.mFinalState != State.REMOVED) {
                        if (FragmentManager.c0(2)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("SpecialEffectsController: For fragment ");
                            sb.append(this.mFragment);
                            sb.append(" mFinalState = ");
                            sb.append(this.mFinalState);
                            sb.append(" -> ");
                            sb.append(state);
                            sb.append(". ");
                        }
                        this.mFinalState = state;
                        return;
                    }
                    return;
                }
                if (FragmentManager.c0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("SpecialEffectsController: For fragment ");
                    sb2.append(this.mFragment);
                    sb2.append(" mFinalState = ");
                    sb2.append(this.mFinalState);
                    sb2.append(" -> REMOVED. mLifecycleImpact  = ");
                    sb2.append(this.mLifecycleImpact);
                    sb2.append(" to REMOVING.");
                }
                this.mFinalState = State.REMOVED;
                lifecycleImpact2 = LifecycleImpact.REMOVING;
            } else if (this.mFinalState != State.REMOVED) {
                return;
            } else {
                if (FragmentManager.c0(2)) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("SpecialEffectsController: For fragment ");
                    sb3.append(this.mFragment);
                    sb3.append(" mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = ");
                    sb3.append(this.mLifecycleImpact);
                    sb3.append(" to ADDING.");
                }
                this.mFinalState = State.VISIBLE;
                lifecycleImpact2 = LifecycleImpact.ADDING;
            }
            this.mLifecycleImpact = lifecycleImpact2;
        }

        void g() {
        }

        @NonNull
        public State getFinalState() {
            return this.mFinalState;
        }

        @NonNull
        public final Fragment getFragment() {
            return this.mFragment;
        }

        public final void markStartedSpecialEffect(@NonNull CancellationSignal cancellationSignal) {
            g();
            this.mSpecialEffectsSignals.add(cancellationSignal);
        }

        @NonNull
        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.mFinalState + "} {mLifecycleImpact = " + this.mLifecycleImpact + "} {mFragment = " + this.mFragment + "}";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpecialEffectsController(@NonNull ViewGroup viewGroup) {
        this.mContainer = viewGroup;
    }

    private void enqueue(@NonNull Operation.State state, @NonNull Operation.LifecycleImpact lifecycleImpact, @NonNull FragmentStateManager fragmentStateManager) {
        synchronized (this.f3135a) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            Operation findPendingOperation = findPendingOperation(fragmentStateManager.k());
            if (findPendingOperation != null) {
                findPendingOperation.f(state, lifecycleImpact);
                return;
            }
            final FragmentStateManagerOperation fragmentStateManagerOperation = new FragmentStateManagerOperation(state, lifecycleImpact, fragmentStateManager, cancellationSignal);
            this.f3135a.add(fragmentStateManagerOperation);
            fragmentStateManagerOperation.a(new Runnable() { // from class: androidx.fragment.app.SpecialEffectsController.1
                @Override // java.lang.Runnable
                public void run() {
                    if (SpecialEffectsController.this.f3135a.contains(fragmentStateManagerOperation)) {
                        fragmentStateManagerOperation.getFinalState().a(fragmentStateManagerOperation.getFragment().mView);
                    }
                }
            });
            fragmentStateManagerOperation.a(new Runnable() { // from class: androidx.fragment.app.SpecialEffectsController.2
                @Override // java.lang.Runnable
                public void run() {
                    SpecialEffectsController.this.f3135a.remove(fragmentStateManagerOperation);
                    SpecialEffectsController.this.f3136b.remove(fragmentStateManagerOperation);
                }
            });
        }
    }

    @Nullable
    private Operation findPendingOperation(@NonNull Fragment fragment) {
        Iterator it = this.f3135a.iterator();
        while (it.hasNext()) {
            Operation operation = (Operation) it.next();
            if (operation.getFragment().equals(fragment) && !operation.d()) {
                return operation;
            }
        }
        return null;
    }

    @Nullable
    private Operation findRunningOperation(@NonNull Fragment fragment) {
        Iterator it = this.f3136b.iterator();
        while (it.hasNext()) {
            Operation operation = (Operation) it.next();
            if (operation.getFragment().equals(fragment) && !operation.d()) {
                return operation;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static SpecialEffectsController j(@NonNull ViewGroup viewGroup, @NonNull FragmentManager fragmentManager) {
        return k(viewGroup, fragmentManager.W());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static SpecialEffectsController k(@NonNull ViewGroup viewGroup, @NonNull SpecialEffectsControllerFactory specialEffectsControllerFactory) {
        int i2 = R.id.special_effects_controller_view_tag;
        Object tag = viewGroup.getTag(i2);
        if (tag instanceof SpecialEffectsController) {
            return (SpecialEffectsController) tag;
        }
        SpecialEffectsController createController = specialEffectsControllerFactory.createController(viewGroup);
        viewGroup.setTag(i2, createController);
        return createController;
    }

    private void updateFinalState() {
        Iterator it = this.f3135a.iterator();
        while (it.hasNext()) {
            Operation operation = (Operation) it.next();
            if (operation.c() == Operation.LifecycleImpact.ADDING) {
                operation.f(Operation.State.b(operation.getFragment().requireView().getVisibility()), Operation.LifecycleImpact.NONE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(@NonNull Operation.State state, @NonNull FragmentStateManager fragmentStateManager) {
        if (FragmentManager.c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("SpecialEffectsController: Enqueuing add operation for fragment ");
            sb.append(fragmentStateManager.k());
        }
        enqueue(state, Operation.LifecycleImpact.ADDING, fragmentStateManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(@NonNull FragmentStateManager fragmentStateManager) {
        if (FragmentManager.c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("SpecialEffectsController: Enqueuing hide operation for fragment ");
            sb.append(fragmentStateManager.k());
        }
        enqueue(Operation.State.GONE, Operation.LifecycleImpact.NONE, fragmentStateManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(@NonNull FragmentStateManager fragmentStateManager) {
        if (FragmentManager.c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("SpecialEffectsController: Enqueuing remove operation for fragment ");
            sb.append(fragmentStateManager.k());
        }
        enqueue(Operation.State.REMOVED, Operation.LifecycleImpact.REMOVING, fragmentStateManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(@NonNull FragmentStateManager fragmentStateManager) {
        if (FragmentManager.c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("SpecialEffectsController: Enqueuing show operation for fragment ");
            sb.append(fragmentStateManager.k());
        }
        enqueue(Operation.State.VISIBLE, Operation.LifecycleImpact.NONE, fragmentStateManager);
    }

    abstract void e(@NonNull List list, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        if (this.f3138d) {
            return;
        }
        if (!ViewCompat.isAttachedToWindow(this.mContainer)) {
            g();
            this.f3137c = false;
            return;
        }
        synchronized (this.f3135a) {
            if (!this.f3135a.isEmpty()) {
                ArrayList arrayList = new ArrayList(this.f3136b);
                this.f3136b.clear();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Operation operation = (Operation) it.next();
                    if (FragmentManager.c0(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("SpecialEffectsController: Cancelling operation ");
                        sb.append(operation);
                    }
                    operation.b();
                    if (!operation.e()) {
                        this.f3136b.add(operation);
                    }
                }
                updateFinalState();
                ArrayList arrayList2 = new ArrayList(this.f3135a);
                this.f3135a.clear();
                this.f3136b.addAll(arrayList2);
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    ((Operation) it2.next()).g();
                }
                e(arrayList2, this.f3137c);
                this.f3137c = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        String str;
        String str2;
        boolean isAttachedToWindow = ViewCompat.isAttachedToWindow(this.mContainer);
        synchronized (this.f3135a) {
            updateFinalState();
            Iterator it = this.f3135a.iterator();
            while (it.hasNext()) {
                ((Operation) it.next()).g();
            }
            Iterator it2 = new ArrayList(this.f3136b).iterator();
            while (it2.hasNext()) {
                Operation operation = (Operation) it2.next();
                if (FragmentManager.c0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("SpecialEffectsController: ");
                    if (isAttachedToWindow) {
                        str2 = "";
                    } else {
                        str2 = "Container " + this.mContainer + " is not attached to window. ";
                    }
                    sb.append(str2);
                    sb.append("Cancelling running operation ");
                    sb.append(operation);
                }
                operation.b();
            }
            Iterator it3 = new ArrayList(this.f3135a).iterator();
            while (it3.hasNext()) {
                Operation operation2 = (Operation) it3.next();
                if (FragmentManager.c0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("SpecialEffectsController: ");
                    if (isAttachedToWindow) {
                        str = "";
                    } else {
                        str = "Container " + this.mContainer + " is not attached to window. ";
                    }
                    sb2.append(str);
                    sb2.append("Cancelling pending operation ");
                    sb2.append(operation2);
                }
                operation2.b();
            }
        }
    }

    @NonNull
    public ViewGroup getContainer() {
        return this.mContainer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        if (this.f3138d) {
            this.f3138d = false;
            f();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Operation.LifecycleImpact i(@NonNull FragmentStateManager fragmentStateManager) {
        Operation findPendingOperation = findPendingOperation(fragmentStateManager.k());
        Operation.LifecycleImpact c2 = findPendingOperation != null ? findPendingOperation.c() : null;
        Operation findRunningOperation = findRunningOperation(fragmentStateManager.k());
        return (findRunningOperation == null || !(c2 == null || c2 == Operation.LifecycleImpact.NONE)) ? c2 : findRunningOperation.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l() {
        synchronized (this.f3135a) {
            updateFinalState();
            this.f3138d = false;
            int size = this.f3135a.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                Operation operation = (Operation) this.f3135a.get(size);
                Operation.State c2 = Operation.State.c(operation.getFragment().mView);
                Operation.State finalState = operation.getFinalState();
                Operation.State state = Operation.State.VISIBLE;
                if (finalState == state && c2 != state) {
                    this.f3138d = operation.getFragment().isPostponed();
                    break;
                }
                size--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(boolean z) {
        this.f3137c = z;
    }
}
