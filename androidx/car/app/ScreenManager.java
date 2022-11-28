package androidx.car.app;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.managers.Manager;
import androidx.car.app.model.TemplateWrapper;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.ThreadUtils;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
@MainThread
/* loaded from: classes.dex */
public class ScreenManager implements Manager {
    private final Lifecycle mAppLifecycle;
    private final CarContext mCarContext;
    private final Deque<Screen> mScreenStack = new ArrayDeque();

    /* loaded from: classes.dex */
    class LifecycleObserverImpl implements DefaultLifecycleObserver {
        LifecycleObserverImpl() {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onCreate(@NonNull LifecycleOwner lifecycleOwner) {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
            ScreenManager.this.destroyAndClearScreenStack();
            lifecycleOwner.getLifecycle().removeObserver(this);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onPause(@NonNull LifecycleOwner lifecycleOwner) {
            Screen peek = ScreenManager.this.getScreenStack().peek();
            if (peek == null) {
                Log.e(LogTags.TAG, "Screen stack was empty during lifecycle onPause");
            } else {
                peek.dispatchLifecycleEvent(Lifecycle.Event.ON_PAUSE);
            }
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onResume(@NonNull LifecycleOwner lifecycleOwner) {
            Screen peek = ScreenManager.this.getScreenStack().peek();
            if (peek == null) {
                Log.e(LogTags.TAG, "Screen stack was empty during lifecycle onResume");
            } else {
                peek.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
            }
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onStart(@NonNull LifecycleOwner lifecycleOwner) {
            Screen peek = ScreenManager.this.getScreenStack().peek();
            if (peek == null) {
                Log.e(LogTags.TAG, "Screen stack was empty during lifecycle onStart");
            } else {
                peek.dispatchLifecycleEvent(Lifecycle.Event.ON_START);
            }
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onStop(@NonNull LifecycleOwner lifecycleOwner) {
            Screen peek = ScreenManager.this.getScreenStack().peek();
            if (peek == null) {
                Log.e(LogTags.TAG, "Screen stack was empty during lifecycle onStop");
            } else {
                peek.dispatchLifecycleEvent(Lifecycle.Event.ON_STOP);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected ScreenManager(@NonNull CarContext carContext, @NonNull Lifecycle lifecycle) {
        this.mCarContext = carContext;
        this.mAppLifecycle = lifecycle;
        lifecycle.addObserver(new LifecycleObserverImpl());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ScreenManager create(CarContext carContext, Lifecycle lifecycle) {
        return new ScreenManager(carContext, lifecycle);
    }

    private boolean foundMarker(String str) {
        return str.equals(getTop().getMarker());
    }

    private void moveToTop(Screen screen, boolean z) {
        Screen peek = this.mScreenStack.peek();
        if (peek == null || peek == screen) {
            return;
        }
        if (z) {
            this.mScreenStack.pop();
        }
        this.mScreenStack.remove(screen);
        pushAndStart(screen, false);
        stop(peek, z);
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }
    }

    private void popInternal(List<Screen> list) {
        Screen top = getTop();
        top.setUseLastTemplateId(true);
        ((AppManager) this.mCarContext.getCarService(AppManager.class)).invalidate();
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            top.dispatchLifecycleEvent(Lifecycle.Event.ON_START);
        }
        for (Screen screen : list) {
            if (Log.isLoggable(LogTags.TAG, 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Popping screen ");
                sb.append(screen);
                sb.append(" off the screen stack");
            }
            stop(screen, true);
        }
        if (Log.isLoggable(LogTags.TAG, 3)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Screen ");
            sb2.append(top);
            sb2.append(" is at the top of the screen stack");
        }
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && this.mScreenStack.contains(top)) {
            top.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }
    }

    private void pushAndStart(Screen screen, boolean z) {
        this.mScreenStack.push(screen);
        if (z && this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_CREATE);
        }
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((AppManager) this.mCarContext.getCarService(AppManager.class)).invalidate();
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_START);
        }
    }

    private void pushInternal(Screen screen) {
        if (Log.isLoggable(LogTags.TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Pushing screen ");
            sb.append(screen);
            sb.append(" to the top of the screen stack");
        }
        if (this.mScreenStack.contains(screen)) {
            moveToTop(screen, false);
            return;
        }
        Screen peek = this.mScreenStack.peek();
        pushAndStart(screen, true);
        if (peek != null) {
            stop(peek, false);
        }
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }
    }

    private void stop(Screen screen, boolean z) {
        Lifecycle.State currentState = screen.getLifecycle().getCurrentState();
        if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }
        if (currentState.isAtLeast(Lifecycle.State.STARTED)) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_STOP);
        }
        if (z) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        }
    }

    void destroyAndClearScreenStack() {
        for (Screen screen : this.mScreenStack) {
            stop(screen, true);
        }
        this.mScreenStack.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Deque<Screen> getScreenStack() {
        return this.mScreenStack;
    }

    @NonNull
    public Screen getTop() {
        ThreadUtils.checkMainThread();
        Screen peek = this.mScreenStack.peek();
        Objects.requireNonNull(peek);
        return peek;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public TemplateWrapper getTopTemplate() {
        ThreadUtils.checkMainThread();
        Screen top = getTop();
        if (Log.isLoggable(LogTags.TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Requesting template from Screen ");
            sb.append(top);
        }
        TemplateWrapper templateWrapper = top.getTemplateWrapper();
        ArrayList arrayList = new ArrayList();
        for (Screen screen : this.mScreenStack) {
            arrayList.add(screen.getLastTemplateInfo());
        }
        templateWrapper.setTemplateInfosForScreenStack(arrayList);
        return templateWrapper;
    }

    public void pop() {
        ThreadUtils.checkMainThread();
        if (this.mScreenStack.size() > 1) {
            popInternal(Collections.singletonList(this.mScreenStack.pop()));
        }
    }

    public void popTo(@NonNull String str) {
        ThreadUtils.checkMainThread();
        Objects.requireNonNull(str);
        ArrayList arrayList = new ArrayList();
        while (this.mScreenStack.size() > 1 && !foundMarker(str)) {
            arrayList.add(this.mScreenStack.pop());
        }
        if (arrayList.isEmpty()) {
            return;
        }
        popInternal(arrayList);
    }

    public void popToRoot() {
        ThreadUtils.checkMainThread();
        if (this.mScreenStack.size() <= 1) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        while (this.mScreenStack.size() > 1) {
            arrayList.add(this.mScreenStack.pop());
        }
        popInternal(arrayList);
    }

    public void push(@NonNull Screen screen) {
        ThreadUtils.checkMainThread();
        Objects.requireNonNull(screen);
        pushInternal(screen);
    }

    @SuppressLint({"ExecutorRegistration"})
    public void pushForResult(@NonNull Screen screen, @NonNull OnScreenResultListener onScreenResultListener) {
        ThreadUtils.checkMainThread();
        Objects.requireNonNull(screen);
        Objects.requireNonNull(onScreenResultListener);
        screen.setOnScreenResultListener(onScreenResultListener);
        pushInternal(screen);
    }

    public void remove(@NonNull Screen screen) {
        ThreadUtils.checkMainThread();
        Objects.requireNonNull(screen);
        if (this.mScreenStack.size() <= 1) {
            return;
        }
        if (screen.equals(getTop())) {
            this.mScreenStack.pop();
            popInternal(Collections.singletonList(screen));
        } else if (this.mScreenStack.remove(screen)) {
            screen.dispatchLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        }
    }
}
