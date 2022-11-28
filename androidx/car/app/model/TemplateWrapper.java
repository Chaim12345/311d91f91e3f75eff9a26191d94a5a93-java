package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes.dex */
public final class TemplateWrapper {
    private int mCurrentTaskStep;
    @Nullable
    @Keep
    private String mId;
    private boolean mIsRefresh;
    @Nullable
    @Keep
    private Template mTemplate;
    @Keep
    private List<TemplateInfo> mTemplateInfoForScreenStack;

    private TemplateWrapper() {
        this.mTemplateInfoForScreenStack = new ArrayList();
        this.mTemplate = null;
        this.mId = "";
    }

    private TemplateWrapper(Template template, String str) {
        this.mTemplateInfoForScreenStack = new ArrayList();
        this.mTemplate = template;
        this.mId = str;
    }

    @NonNull
    public static TemplateWrapper copyOf(@NonNull TemplateWrapper templateWrapper) {
        TemplateWrapper wrap = wrap(templateWrapper.getTemplate(), templateWrapper.getId());
        wrap.setRefresh(templateWrapper.isRefresh());
        wrap.setCurrentTaskStep(templateWrapper.getCurrentTaskStep());
        List<TemplateInfo> templateInfosForScreenStack = templateWrapper.getTemplateInfosForScreenStack();
        if (templateInfosForScreenStack != null) {
            wrap.setTemplateInfosForScreenStack(templateInfosForScreenStack);
        }
        return wrap;
    }

    private static String createRandomId() {
        return UUID.randomUUID().toString();
    }

    @NonNull
    public static TemplateWrapper wrap(@NonNull Template template) {
        return wrap(template, createRandomId());
    }

    @NonNull
    public static TemplateWrapper wrap(@NonNull Template template, @NonNull String str) {
        Objects.requireNonNull(template);
        Objects.requireNonNull(str);
        return new TemplateWrapper(template, str);
    }

    public int getCurrentTaskStep() {
        return this.mCurrentTaskStep;
    }

    @NonNull
    public String getId() {
        String str = this.mId;
        Objects.requireNonNull(str);
        return str;
    }

    @NonNull
    public Template getTemplate() {
        Template template = this.mTemplate;
        Objects.requireNonNull(template);
        return template;
    }

    @NonNull
    public List<TemplateInfo> getTemplateInfosForScreenStack() {
        return CollectionUtils.emptyIfNull(this.mTemplateInfoForScreenStack);
    }

    public boolean isRefresh() {
        return this.mIsRefresh;
    }

    public void setCurrentTaskStep(int i2) {
        this.mCurrentTaskStep = i2;
    }

    public void setId(@NonNull String str) {
        this.mId = str;
    }

    public void setRefresh(boolean z) {
        this.mIsRefresh = z;
    }

    public void setTemplate(@NonNull Template template) {
        this.mTemplate = template;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setTemplateInfosForScreenStack(@NonNull List<TemplateInfo> list) {
        this.mTemplateInfoForScreenStack = list;
    }

    @NonNull
    public String toString() {
        return "[template: " + this.mTemplate + ", ID: " + this.mId + "]";
    }
}
