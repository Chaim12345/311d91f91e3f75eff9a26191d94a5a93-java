package androidx.car.app;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.model.Template;
import androidx.car.app.model.TemplateInfo;
import androidx.car.app.model.TemplateWrapper;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.ThreadUtils;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class Screen implements LifecycleOwner {
    private final CarContext mCarContext;
    @Nullable
    private String mMarker;
    @Nullable
    private Object mResult;
    @Nullable
    private TemplateWrapper mTemplateWrapper;
    private boolean mUseLastTemplateId;
    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    private OnScreenResultListener mOnScreenResultListener = h0.f1476a;

    /* JADX INFO: Access modifiers changed from: protected */
    public Screen(@NonNull CarContext carContext) {
        Objects.requireNonNull(carContext);
        this.mCarContext = carContext;
    }

    @NonNull
    private static TemplateInfo getLastTemplateInfo(TemplateWrapper templateWrapper) {
        return new TemplateInfo(templateWrapper.getTemplate().getClass(), templateWrapper.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchLifecycleEvent$1(Lifecycle.Event event) {
        if (this.mLifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.INITIALIZED)) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                this.mOnScreenResultListener.onScreenResult(this.mResult);
            }
            this.mLifecycleRegistry.handleLifecycleEvent(event);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(Object obj) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void dispatchLifecycleEvent(final Lifecycle.Event event) {
        ThreadUtils.runOnMain(new Runnable() { // from class: androidx.car.app.i0
            @Override // java.lang.Runnable
            public final void run() {
                Screen.this.lambda$dispatchLifecycleEvent$1(event);
            }
        });
    }

    public final void finish() {
        ((ScreenManager) this.mCarContext.getCarService(ScreenManager.class)).remove(this);
    }

    @NonNull
    public final CarContext getCarContext() {
        return this.mCarContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public TemplateInfo getLastTemplateInfo() {
        if (this.mTemplateWrapper == null) {
            this.mTemplateWrapper = TemplateWrapper.wrap(onGetTemplate());
        }
        return new TemplateInfo(this.mTemplateWrapper.getTemplate().getClass(), this.mTemplateWrapper.getId());
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    public final Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Nullable
    public String getMarker() {
        return this.mMarker;
    }

    @NonNull
    public final ScreenManager getScreenManager() {
        return (ScreenManager) this.mCarContext.getCarService(ScreenManager.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public TemplateWrapper getTemplateWrapper() {
        TemplateWrapper wrap;
        Template onGetTemplate = onGetTemplate();
        if (this.mUseLastTemplateId) {
            TemplateWrapper templateWrapper = this.mTemplateWrapper;
            Objects.requireNonNull(templateWrapper);
            wrap = TemplateWrapper.wrap(onGetTemplate, getLastTemplateInfo(templateWrapper).getTemplateId());
        } else {
            wrap = TemplateWrapper.wrap(onGetTemplate);
        }
        this.mUseLastTemplateId = false;
        this.mTemplateWrapper = wrap;
        if (Log.isLoggable(LogTags.TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Returning ");
            sb.append(onGetTemplate);
            sb.append(" from screen ");
            sb.append(this);
        }
        return wrap;
    }

    public final void invalidate() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((AppManager) this.mCarContext.getCarService(AppManager.class)).invalidate();
        }
    }

    @NonNull
    public abstract Template onGetTemplate();

    public void setMarker(@Nullable String str) {
        this.mMarker = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnScreenResultListener(OnScreenResultListener onScreenResultListener) {
        this.mOnScreenResultListener = onScreenResultListener;
    }

    public void setResult(@Nullable Object obj) {
        this.mResult = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUseLastTemplateId(boolean z) {
        this.mUseLastTemplateId = z;
    }
}
