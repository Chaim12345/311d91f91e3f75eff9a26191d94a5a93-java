package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes.dex */
public final class TemplateInfo {
    @Nullable
    @Keep
    private final Class<? extends Template> mTemplateClass;
    @Nullable
    @Keep
    private final String mTemplateId;

    private TemplateInfo() {
        this.mTemplateClass = null;
        this.mTemplateId = null;
    }

    public TemplateInfo(@NonNull Class<? extends Template> cls, @NonNull String str) {
        this.mTemplateClass = cls;
        this.mTemplateId = str;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TemplateInfo) {
            TemplateInfo templateInfo = (TemplateInfo) obj;
            return Objects.equals(this.mTemplateClass, templateInfo.mTemplateClass) && Objects.equals(this.mTemplateId, templateInfo.mTemplateId);
        }
        return false;
    }

    @NonNull
    public Class<? extends Template> getTemplateClass() {
        Class<? extends Template> cls = this.mTemplateClass;
        Objects.requireNonNull(cls);
        return cls;
    }

    @NonNull
    public String getTemplateId() {
        String str = this.mTemplateId;
        Objects.requireNonNull(str);
        return str;
    }

    public int hashCode() {
        return Objects.hash(this.mTemplateClass, this.mTemplateId);
    }
}
