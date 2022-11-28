package androidx.fragment.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import java.io.PrintWriter;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BackStackRecord extends FragmentTransaction implements FragmentManager.BackStackEntry, FragmentManager.OpGenerator {
    private static final String TAG = "FragmentManager";

    /* renamed from: r  reason: collision with root package name */
    final FragmentManager f2878r;

    /* renamed from: s  reason: collision with root package name */
    boolean f2879s;

    /* renamed from: t  reason: collision with root package name */
    int f2880t;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BackStackRecord(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager.getFragmentFactory(), fragmentManager.S() != null ? fragmentManager.S().b().getClassLoader() : null);
        this.f2880t = -1;
        this.f2878r = fragmentManager;
    }

    private static boolean isFragmentPostponed(FragmentTransaction.Op op) {
        Fragment fragment = op.f3056b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.fragment.app.FragmentTransaction
    public void c(int i2, Fragment fragment, @Nullable String str, int i3) {
        super.c(i2, fragment, str, i3);
        fragment.mFragmentManager = this.f2878r;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public int commit() {
        return e(false);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public int commitAllowingStateLoss() {
        return e(true);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public void commitNow() {
        disallowAddToBackStack();
        this.f2878r.L(this, false);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public void commitNowAllowingStateLoss() {
        disallowAddToBackStack();
        this.f2878r.L(this, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i2) {
        if (this.f3044g) {
            if (FragmentManager.c0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Bump nesting in ");
                sb.append(this);
                sb.append(" by ");
                sb.append(i2);
            }
            int size = this.f3038a.size();
            for (int i3 = 0; i3 < size; i3++) {
                FragmentTransaction.Op op = (FragmentTransaction.Op) this.f3038a.get(i3);
                Fragment fragment = op.f3056b;
                if (fragment != null) {
                    fragment.mBackStackNesting += i2;
                    if (FragmentManager.c0(2)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Bump nesting of ");
                        sb2.append(op.f3056b);
                        sb2.append(" to ");
                        sb2.append(op.f3056b.mBackStackNesting);
                    }
                }
            }
        }
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction detach(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = fragment.mFragmentManager;
        if (fragmentManager == null || fragmentManager == this.f2878r) {
            return super.detach(fragment);
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    public void dump(String str, PrintWriter printWriter) {
        dump(str, printWriter, true);
    }

    public void dump(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f3046i);
            printWriter.print(" mIndex=");
            printWriter.print(this.f2880t);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f2879s);
            if (this.f3043f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f3043f));
            }
            if (this.f3039b != 0 || this.f3040c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f3039b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f3040c));
            }
            if (this.f3041d != 0 || this.f3042e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f3041d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f3042e));
            }
            if (this.f3047j != 0 || this.f3048k != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f3047j));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f3048k);
            }
            if (this.f3049l != 0 || this.f3050m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f3049l));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f3050m);
            }
        }
        if (this.f3038a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f3038a.size();
        for (int i2 = 0; i2 < size; i2++) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f3038a.get(i2);
            switch (op.f3055a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + op.f3055a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i2);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(op.f3056b);
            if (z) {
                if (op.f3057c != 0 || op.f3058d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(op.f3057c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(op.f3058d));
                }
                if (op.f3059e != 0 || op.f3060f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(op.f3059e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(op.f3060f));
                }
            }
        }
    }

    int e(boolean z) {
        if (this.f2879s) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManager.c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Commit: ");
            sb.append(this);
            PrintWriter printWriter = new PrintWriter(new LogWriter(TAG));
            dump("  ", printWriter);
            printWriter.close();
        }
        this.f2879s = true;
        this.f2880t = this.f3044g ? this.f2878r.h() : -1;
        this.f2878r.J(this, z);
        return this.f2880t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        int size = this.f3038a.size();
        for (int i2 = 0; i2 < size; i2++) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f3038a.get(i2);
            Fragment fragment = op.f3056b;
            if (fragment != null) {
                fragment.setPopDirection(false);
                fragment.setNextTransition(this.f3043f);
                fragment.setSharedElementNames(this.f3051n, this.f3052o);
            }
            switch (op.f3055a) {
                case 1:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.A0(fragment, false);
                    this.f2878r.f(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + op.f3055a);
                case 3:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.s0(fragment);
                    break;
                case 4:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.a0(fragment);
                    break;
                case 5:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.A0(fragment, false);
                    this.f2878r.D0(fragment);
                    break;
                case 6:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.n(fragment);
                    break;
                case 7:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.A0(fragment, false);
                    this.f2878r.j(fragment);
                    break;
                case 8:
                    this.f2878r.C0(fragment);
                    break;
                case 9:
                    this.f2878r.C0(null);
                    break;
                case 10:
                    this.f2878r.B0(fragment, op.f3062h);
                    break;
            }
            if (!this.f3053p && op.f3055a != 1 && fragment != null && !FragmentManager.f2982e) {
                this.f2878r.j0(fragment);
            }
        }
        if (this.f3053p || FragmentManager.f2982e) {
            return;
        }
        FragmentManager fragmentManager = this.f2878r;
        fragmentManager.k0(fragmentManager.f2984b, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(boolean z) {
        for (int size = this.f3038a.size() - 1; size >= 0; size--) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f3038a.get(size);
            Fragment fragment = op.f3056b;
            if (fragment != null) {
                fragment.setPopDirection(true);
                fragment.setNextTransition(FragmentManager.x0(this.f3043f));
                fragment.setSharedElementNames(this.f3052o, this.f3051n);
            }
            switch (op.f3055a) {
                case 1:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.A0(fragment, true);
                    this.f2878r.s0(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + op.f3055a);
                case 3:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.f(fragment);
                    break;
                case 4:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.D0(fragment);
                    break;
                case 5:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.A0(fragment, true);
                    this.f2878r.a0(fragment);
                    break;
                case 6:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.j(fragment);
                    break;
                case 7:
                    fragment.setAnimations(op.f3057c, op.f3058d, op.f3059e, op.f3060f);
                    this.f2878r.A0(fragment, true);
                    this.f2878r.n(fragment);
                    break;
                case 8:
                    this.f2878r.C0(null);
                    break;
                case 9:
                    this.f2878r.C0(fragment);
                    break;
                case 10:
                    this.f2878r.B0(fragment, op.f3061g);
                    break;
            }
            if (!this.f3053p && op.f3055a != 3 && fragment != null && !FragmentManager.f2982e) {
                this.f2878r.j0(fragment);
            }
        }
        if (this.f3053p || !z || FragmentManager.f2982e) {
            return;
        }
        FragmentManager fragmentManager = this.f2878r;
        fragmentManager.k0(fragmentManager.f2984b, true);
    }

    @Override // androidx.fragment.app.FragmentManager.OpGenerator
    public boolean generateOps(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        if (FragmentManager.c0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Run: ");
            sb.append(this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (this.f3044g) {
            this.f2878r.d(this);
            return true;
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    @Nullable
    public CharSequence getBreadCrumbShortTitle() {
        return this.f3049l != 0 ? this.f2878r.S().b().getText(this.f3049l) : this.f3050m;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    public int getBreadCrumbShortTitleRes() {
        return this.f3049l;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    @Nullable
    public CharSequence getBreadCrumbTitle() {
        return this.f3047j != 0 ? this.f2878r.S().b().getText(this.f3047j) : this.f3048k;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    public int getBreadCrumbTitleRes() {
        return this.f3047j;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    public int getId() {
        return this.f2880t;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    @Nullable
    public String getName() {
        return this.f3046i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment h(ArrayList arrayList, Fragment fragment) {
        Fragment fragment2 = fragment;
        int i2 = 0;
        while (i2 < this.f3038a.size()) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f3038a.get(i2);
            int i3 = op.f3055a;
            if (i3 != 1) {
                if (i3 == 2) {
                    Fragment fragment3 = op.f3056b;
                    int i4 = fragment3.mContainerId;
                    boolean z = false;
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        Fragment fragment4 = (Fragment) arrayList.get(size);
                        if (fragment4.mContainerId == i4) {
                            if (fragment4 == fragment3) {
                                z = true;
                            } else {
                                if (fragment4 == fragment2) {
                                    this.f3038a.add(i2, new FragmentTransaction.Op(9, fragment4));
                                    i2++;
                                    fragment2 = null;
                                }
                                FragmentTransaction.Op op2 = new FragmentTransaction.Op(3, fragment4);
                                op2.f3057c = op.f3057c;
                                op2.f3059e = op.f3059e;
                                op2.f3058d = op.f3058d;
                                op2.f3060f = op.f3060f;
                                this.f3038a.add(i2, op2);
                                arrayList.remove(fragment4);
                                i2++;
                            }
                        }
                    }
                    if (z) {
                        this.f3038a.remove(i2);
                        i2--;
                    } else {
                        op.f3055a = 1;
                        arrayList.add(fragment3);
                    }
                } else if (i3 == 3 || i3 == 6) {
                    arrayList.remove(op.f3056b);
                    Fragment fragment5 = op.f3056b;
                    if (fragment5 == fragment2) {
                        this.f3038a.add(i2, new FragmentTransaction.Op(9, fragment5));
                        i2++;
                        fragment2 = null;
                    }
                } else if (i3 != 7) {
                    if (i3 == 8) {
                        this.f3038a.add(i2, new FragmentTransaction.Op(9, fragment2));
                        i2++;
                        fragment2 = op.f3056b;
                    }
                }
                i2++;
            }
            arrayList.add(op.f3056b);
            i2++;
        }
        return fragment2;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction hide(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = fragment.mFragmentManager;
        if (fragmentManager == null || fragmentManager == this.f2878r) {
            return super.hide(fragment);
        }
        throw new IllegalStateException("Cannot hide Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i(int i2) {
        int size = this.f3038a.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = ((FragmentTransaction.Op) this.f3038a.get(i3)).f3056b;
            int i4 = fragment != null ? fragment.mContainerId : 0;
            if (i4 != 0 && i4 == i2) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public boolean isEmpty() {
        return this.f3038a.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j(ArrayList arrayList, int i2, int i3) {
        if (i3 == i2) {
            return false;
        }
        int size = this.f3038a.size();
        int i4 = -1;
        for (int i5 = 0; i5 < size; i5++) {
            Fragment fragment = ((FragmentTransaction.Op) this.f3038a.get(i5)).f3056b;
            int i6 = fragment != null ? fragment.mContainerId : 0;
            if (i6 != 0 && i6 != i4) {
                for (int i7 = i2; i7 < i3; i7++) {
                    BackStackRecord backStackRecord = (BackStackRecord) arrayList.get(i7);
                    int size2 = backStackRecord.f3038a.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        Fragment fragment2 = ((FragmentTransaction.Op) backStackRecord.f3038a.get(i8)).f3056b;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i6) {
                            return true;
                        }
                    }
                }
                i4 = i6;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        for (int i2 = 0; i2 < this.f3038a.size(); i2++) {
            if (isFragmentPostponed((FragmentTransaction.Op) this.f3038a.get(i2))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(Fragment.OnStartEnterTransitionListener onStartEnterTransitionListener) {
        for (int i2 = 0; i2 < this.f3038a.size(); i2++) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f3038a.get(i2);
            if (isFragmentPostponed(op)) {
                op.f3056b.setOnStartEnterTransitionListener(onStartEnterTransitionListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment m(ArrayList arrayList, Fragment fragment) {
        for (int size = this.f3038a.size() - 1; size >= 0; size--) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f3038a.get(size);
            int i2 = op.f3055a;
            if (i2 != 1) {
                if (i2 != 3) {
                    switch (i2) {
                        case 8:
                            fragment = null;
                            break;
                        case 9:
                            fragment = op.f3056b;
                            break;
                        case 10:
                            op.f3062h = op.f3061g;
                            break;
                    }
                }
                arrayList.add(op.f3056b);
            }
            arrayList.remove(op.f3056b);
        }
        return fragment;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction remove(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = fragment.mFragmentManager;
        if (fragmentManager == null || fragmentManager == this.f2878r) {
            return super.remove(fragment);
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    public void runOnCommitRunnables() {
        if (this.f3054q != null) {
            for (int i2 = 0; i2 < this.f3054q.size(); i2++) {
                ((Runnable) this.f3054q.get(i2)).run();
            }
            this.f3054q = null;
        }
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction setMaxLifecycle(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        if (fragment.mFragmentManager != this.f2878r) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.f2878r);
        } else if (state == Lifecycle.State.INITIALIZED && fragment.mState > -1) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + state + " after the Fragment has been created");
        } else if (state != Lifecycle.State.DESTROYED) {
            return super.setMaxLifecycle(fragment, state);
        } else {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + state + ". Use remove() to remove the fragment from the FragmentManager and trigger its destruction.");
        }
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment fragment) {
        FragmentManager fragmentManager;
        if (fragment == null || (fragmentManager = fragment.mFragmentManager) == null || fragmentManager == this.f2878r) {
            return super.setPrimaryNavigationFragment(fragment);
        }
        throw new IllegalStateException("Cannot setPrimaryNavigation for Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction show(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = fragment.mFragmentManager;
        if (fragmentManager == null || fragmentManager == this.f2878r) {
            return super.show(fragment);
        }
        throw new IllegalStateException("Cannot show Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f2880t >= 0) {
            sb.append(" #");
            sb.append(this.f2880t);
        }
        if (this.f3046i != null) {
            sb.append(" ");
            sb.append(this.f3046i);
        }
        sb.append("}");
        return sb.toString();
    }
}
