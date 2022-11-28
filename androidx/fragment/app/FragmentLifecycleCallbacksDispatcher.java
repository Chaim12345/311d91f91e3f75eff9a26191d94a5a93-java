package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class FragmentLifecycleCallbacksDispatcher {
    @NonNull
    private final FragmentManager mFragmentManager;
    @NonNull
    private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> mLifecycleCallbacks = new CopyOnWriteArrayList<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class FragmentLifecycleCallbacksHolder {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        final FragmentManager.FragmentLifecycleCallbacks f2980a;

        /* renamed from: b  reason: collision with root package name */
        final boolean f2981b;

        FragmentLifecycleCallbacksHolder(@NonNull FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
            this.f2980a = fragmentLifecycleCallbacks;
            this.f2981b = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentLifecycleCallbacksDispatcher(@NonNull FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().a(fragment, bundle, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentActivityCreated(this.mFragmentManager, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(@NonNull Fragment fragment, boolean z) {
        Context b2 = this.mFragmentManager.S().b();
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().b(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentAttached(this.mFragmentManager, fragment, b2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().c(fragment, bundle, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentCreated(this.mFragmentManager, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(@NonNull Fragment fragment, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().d(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentDestroyed(this.mFragmentManager, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(@NonNull Fragment fragment, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().e(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentDetached(this.mFragmentManager, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(@NonNull Fragment fragment, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().f(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentPaused(this.mFragmentManager, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(@NonNull Fragment fragment, boolean z) {
        Context b2 = this.mFragmentManager.S().b();
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().g(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentPreAttached(this.mFragmentManager, fragment, b2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().h(fragment, bundle, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentPreCreated(this.mFragmentManager, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(@NonNull Fragment fragment, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().i(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentResumed(this.mFragmentManager, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(@NonNull Fragment fragment, @NonNull Bundle bundle, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().j(fragment, bundle, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentSaveInstanceState(this.mFragmentManager, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(@NonNull Fragment fragment, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().k(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentStarted(this.mFragmentManager, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(@NonNull Fragment fragment, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().l(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentStopped(this.mFragmentManager, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(@NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().m(fragment, view, bundle, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentViewCreated(this.mFragmentManager, fragment, view, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(@NonNull Fragment fragment, boolean z) {
        Fragment V = this.mFragmentManager.V();
        if (V != null) {
            V.getParentFragmentManager().U().n(fragment, true);
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z || next.f2981b) {
                next.f2980a.onFragmentViewDestroyed(this.mFragmentManager, fragment);
            }
        }
    }

    public void registerFragmentLifecycleCallbacks(@NonNull FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.mLifecycleCallbacks.add(new FragmentLifecycleCallbacksHolder(fragmentLifecycleCallbacks, z));
    }

    public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        synchronized (this.mLifecycleCallbacks) {
            int i2 = 0;
            int size = this.mLifecycleCallbacks.size();
            while (true) {
                if (i2 >= size) {
                    break;
                } else if (this.mLifecycleCallbacks.get(i2).f2980a == fragmentLifecycleCallbacks) {
                    this.mLifecycleCallbacks.remove(i2);
                    break;
                } else {
                    i2++;
                }
            }
        }
    }
}
