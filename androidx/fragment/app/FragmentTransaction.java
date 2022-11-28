package androidx.fragment.app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class FragmentTransaction {
    public static final int TRANSIT_ENTER_MASK = 4096;
    public static final int TRANSIT_EXIT_MASK = 8192;
    public static final int TRANSIT_FRAGMENT_CLOSE = 8194;
    public static final int TRANSIT_FRAGMENT_FADE = 4099;
    public static final int TRANSIT_FRAGMENT_OPEN = 4097;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_UNSET = -1;

    /* renamed from: a  reason: collision with root package name */
    ArrayList f3038a;

    /* renamed from: b  reason: collision with root package name */
    int f3039b;

    /* renamed from: c  reason: collision with root package name */
    int f3040c;

    /* renamed from: d  reason: collision with root package name */
    int f3041d;

    /* renamed from: e  reason: collision with root package name */
    int f3042e;

    /* renamed from: f  reason: collision with root package name */
    int f3043f;

    /* renamed from: g  reason: collision with root package name */
    boolean f3044g;

    /* renamed from: h  reason: collision with root package name */
    boolean f3045h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    String f3046i;

    /* renamed from: j  reason: collision with root package name */
    int f3047j;

    /* renamed from: k  reason: collision with root package name */
    CharSequence f3048k;

    /* renamed from: l  reason: collision with root package name */
    int f3049l;

    /* renamed from: m  reason: collision with root package name */
    CharSequence f3050m;
    private final ClassLoader mClassLoader;
    private final FragmentFactory mFragmentFactory;

    /* renamed from: n  reason: collision with root package name */
    ArrayList f3051n;

    /* renamed from: o  reason: collision with root package name */
    ArrayList f3052o;

    /* renamed from: p  reason: collision with root package name */
    boolean f3053p;

    /* renamed from: q  reason: collision with root package name */
    ArrayList f3054q;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Op {

        /* renamed from: a  reason: collision with root package name */
        int f3055a;

        /* renamed from: b  reason: collision with root package name */
        Fragment f3056b;

        /* renamed from: c  reason: collision with root package name */
        int f3057c;

        /* renamed from: d  reason: collision with root package name */
        int f3058d;

        /* renamed from: e  reason: collision with root package name */
        int f3059e;

        /* renamed from: f  reason: collision with root package name */
        int f3060f;

        /* renamed from: g  reason: collision with root package name */
        Lifecycle.State f3061g;

        /* renamed from: h  reason: collision with root package name */
        Lifecycle.State f3062h;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Op() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Op(int i2, Fragment fragment) {
            this.f3055a = i2;
            this.f3056b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.f3061g = state;
            this.f3062h = state;
        }

        Op(int i2, @NonNull Fragment fragment, Lifecycle.State state) {
            this.f3055a = i2;
            this.f3056b = fragment;
            this.f3061g = fragment.mMaxState;
            this.f3062h = state;
        }
    }

    @Deprecated
    public FragmentTransaction() {
        this.f3038a = new ArrayList();
        this.f3045h = true;
        this.f3053p = false;
        this.mFragmentFactory = null;
        this.mClassLoader = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentTransaction(@NonNull FragmentFactory fragmentFactory, @Nullable ClassLoader classLoader) {
        this.f3038a = new ArrayList();
        this.f3045h = true;
        this.f3053p = false;
        this.mFragmentFactory = fragmentFactory;
        this.mClassLoader = classLoader;
    }

    @NonNull
    private Fragment createFragment(@NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle) {
        FragmentFactory fragmentFactory = this.mFragmentFactory;
        if (fragmentFactory != null) {
            ClassLoader classLoader = this.mClassLoader;
            if (classLoader != null) {
                Fragment instantiate = fragmentFactory.instantiate(classLoader, cls.getName());
                if (bundle != null) {
                    instantiate.setArguments(bundle);
                }
                return instantiate;
            }
            throw new IllegalStateException("The FragmentManager must be attached to itshost to create a Fragment");
        }
        throw new IllegalStateException("Creating a Fragment requires that this FragmentTransaction was built with FragmentManager.beginTransaction()");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentTransaction a(@NonNull ViewGroup viewGroup, @NonNull Fragment fragment, @Nullable String str) {
        fragment.mContainer = viewGroup;
        return add(viewGroup.getId(), fragment, str);
    }

    @NonNull
    public FragmentTransaction add(@IdRes int i2, @NonNull Fragment fragment) {
        c(i2, fragment, null, 1);
        return this;
    }

    @NonNull
    public FragmentTransaction add(@IdRes int i2, @NonNull Fragment fragment, @Nullable String str) {
        c(i2, fragment, str, 1);
        return this;
    }

    @NonNull
    public final FragmentTransaction add(@IdRes int i2, @NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle) {
        return add(i2, createFragment(cls, bundle));
    }

    @NonNull
    public final FragmentTransaction add(@IdRes int i2, @NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle, @Nullable String str) {
        return add(i2, createFragment(cls, bundle), str);
    }

    @NonNull
    public FragmentTransaction add(@NonNull Fragment fragment, @Nullable String str) {
        c(0, fragment, str, 1);
        return this;
    }

    @NonNull
    public final FragmentTransaction add(@NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle, @Nullable String str) {
        return add(createFragment(cls, bundle), str);
    }

    @NonNull
    public FragmentTransaction addSharedElement(@NonNull View view, @NonNull String str) {
        if (FragmentTransition.i()) {
            String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.f3051n == null) {
                this.f3051n = new ArrayList();
                this.f3052o = new ArrayList();
            } else if (this.f3052o.contains(str)) {
                throw new IllegalArgumentException("A shared element with the target name '" + str + "' has already been added to the transaction.");
            } else if (this.f3051n.contains(transitionName)) {
                throw new IllegalArgumentException("A shared element with the source name '" + transitionName + "' has already been added to the transaction.");
            }
            this.f3051n.add(transitionName);
            this.f3052o.add(str);
        }
        return this;
    }

    @NonNull
    public FragmentTransaction addToBackStack(@Nullable String str) {
        if (this.f3045h) {
            this.f3044g = true;
            this.f3046i = str;
            return this;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }

    @NonNull
    public FragmentTransaction attach(@NonNull Fragment fragment) {
        b(new Op(7, fragment));
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Op op) {
        this.f3038a.add(op);
        op.f3057c = this.f3039b;
        op.f3058d = this.f3040c;
        op.f3059e = this.f3041d;
        op.f3060f = this.f3042e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i2, Fragment fragment, @Nullable String str, int i3) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fragment.mTag;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
            fragment.mTag = str;
        }
        if (i2 != 0) {
            if (i2 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i4 = fragment.mFragmentId;
            if (i4 != 0 && i4 != i2) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i2);
            }
            fragment.mFragmentId = i2;
            fragment.mContainerId = i2;
        }
        b(new Op(i3, fragment));
    }

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    @NonNull
    public FragmentTransaction detach(@NonNull Fragment fragment) {
        b(new Op(6, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction disallowAddToBackStack() {
        if (this.f3044g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.f3045h = false;
        return this;
    }

    @NonNull
    public FragmentTransaction hide(@NonNull Fragment fragment) {
        b(new Op(4, fragment));
        return this;
    }

    public boolean isAddToBackStackAllowed() {
        return this.f3045h;
    }

    public boolean isEmpty() {
        return this.f3038a.isEmpty();
    }

    @NonNull
    public FragmentTransaction remove(@NonNull Fragment fragment) {
        b(new Op(3, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction replace(@IdRes int i2, @NonNull Fragment fragment) {
        return replace(i2, fragment, (String) null);
    }

    @NonNull
    public FragmentTransaction replace(@IdRes int i2, @NonNull Fragment fragment, @Nullable String str) {
        if (i2 != 0) {
            c(i2, fragment, str, 2);
            return this;
        }
        throw new IllegalArgumentException("Must use non-zero containerViewId");
    }

    @NonNull
    public final FragmentTransaction replace(@IdRes int i2, @NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle) {
        return replace(i2, cls, bundle, null);
    }

    @NonNull
    public final FragmentTransaction replace(@IdRes int i2, @NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle, @Nullable String str) {
        return replace(i2, createFragment(cls, bundle), str);
    }

    @NonNull
    public FragmentTransaction runOnCommit(@NonNull Runnable runnable) {
        disallowAddToBackStack();
        if (this.f3054q == null) {
            this.f3054q = new ArrayList();
        }
        this.f3054q.add(runnable);
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setAllowOptimization(boolean z) {
        return setReorderingAllowed(z);
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbShortTitle(@StringRes int i2) {
        this.f3049l = i2;
        this.f3050m = null;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbShortTitle(@Nullable CharSequence charSequence) {
        this.f3049l = 0;
        this.f3050m = charSequence;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbTitle(@StringRes int i2) {
        this.f3047j = i2;
        this.f3048k = null;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbTitle(@Nullable CharSequence charSequence) {
        this.f3047j = 0;
        this.f3048k = charSequence;
        return this;
    }

    @NonNull
    public FragmentTransaction setCustomAnimations(@AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        return setCustomAnimations(i2, i3, 0, 0);
    }

    @NonNull
    public FragmentTransaction setCustomAnimations(@AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        this.f3039b = i2;
        this.f3040c = i3;
        this.f3041d = i4;
        this.f3042e = i5;
        return this;
    }

    @NonNull
    public FragmentTransaction setMaxLifecycle(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        b(new Op(10, fragment, state));
        return this;
    }

    @NonNull
    public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment fragment) {
        b(new Op(8, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction setReorderingAllowed(boolean z) {
        this.f3053p = z;
        return this;
    }

    @NonNull
    public FragmentTransaction setTransition(int i2) {
        this.f3043f = i2;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setTransitionStyle(@StyleRes int i2) {
        return this;
    }

    @NonNull
    public FragmentTransaction show(@NonNull Fragment fragment) {
        b(new Op(5, fragment));
        return this;
    }
}
