package androidx.car.app.model;

import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class MessageTemplate implements Template {
    @Keep
    private final List<Action> mActionList;
    @Nullable
    @Keep
    private final ActionStrip mActionStrip;
    @Nullable
    @Keep
    private final CarText mDebugMessage;
    @Nullable
    @Keep
    private final Action mHeaderAction;
    @Nullable
    @Keep
    private final CarIcon mIcon;
    @Keep
    private final boolean mIsLoading;
    @Nullable
    @Keep
    private final CarText mMessage;
    @Nullable
    @Keep
    private final CarText mTitle;

    /* loaded from: classes.dex */
    public static final class Builder {
        List<Action> mActionList = new ArrayList();
        @Nullable
        ActionStrip mActionStrip;
        @Nullable
        Throwable mDebugCause;
        @Nullable
        CarText mDebugMessage;
        @Nullable
        String mDebugString;
        @Nullable
        Action mHeaderAction;
        @Nullable
        CarIcon mIcon;
        boolean mIsLoading;
        CarText mMessage;
        @Nullable
        CarText mTitle;

        public Builder(@NonNull CarText carText) {
            Objects.requireNonNull(carText);
            this.mMessage = carText;
        }

        public Builder(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mMessage = CarText.create(charSequence);
        }

        @NonNull
        public Builder addAction(@NonNull Action action) {
            List<Action> list = this.mActionList;
            Objects.requireNonNull(action);
            list.add(action);
            ActionsConstraints.ACTIONS_CONSTRAINTS_BODY.validateOrThrow(this.mActionList);
            return this;
        }

        @NonNull
        public MessageTemplate build() {
            if (!this.mIsLoading || this.mIcon == null) {
                if (this.mMessage.isEmpty()) {
                    throw new IllegalStateException("Message cannot be empty");
                }
                String str = this.mDebugString;
                if (str == null) {
                    str = "";
                }
                if (!str.isEmpty() && this.mDebugCause != null) {
                    str = str + "\n";
                }
                String str2 = str + Log.getStackTraceString(this.mDebugCause);
                if (!str2.isEmpty()) {
                    this.mDebugMessage = CarText.create(str2);
                }
                if (CarText.isNullOrEmpty(this.mTitle) && this.mHeaderAction == null) {
                    throw new IllegalStateException("Either the title or header action must be set");
                }
                return new MessageTemplate(this);
            }
            throw new IllegalStateException("Template in a loading state can not have an icon");
        }

        @NonNull
        @RequiresCarApi(2)
        public Builder setActionStrip(@NonNull ActionStrip actionStrip) {
            ActionsConstraints actionsConstraints = ActionsConstraints.ACTIONS_CONSTRAINTS_SIMPLE;
            Objects.requireNonNull(actionStrip);
            actionsConstraints.validateOrThrow(actionStrip.getActions());
            this.mActionStrip = actionStrip;
            return this;
        }

        @NonNull
        public Builder setDebugMessage(@NonNull String str) {
            Objects.requireNonNull(str);
            this.mDebugString = str;
            return this;
        }

        @NonNull
        public Builder setDebugMessage(@NonNull Throwable th) {
            Objects.requireNonNull(th);
            this.mDebugCause = th;
            return this;
        }

        @NonNull
        public Builder setHeaderAction(@NonNull Action action) {
            ActionsConstraints actionsConstraints = ActionsConstraints.ACTIONS_CONSTRAINTS_HEADER;
            Objects.requireNonNull(action);
            actionsConstraints.validateOrThrow(Collections.singletonList(action));
            this.mHeaderAction = action;
            return this;
        }

        @NonNull
        public Builder setIcon(@NonNull CarIcon carIcon) {
            CarIconConstraints carIconConstraints = CarIconConstraints.DEFAULT;
            Objects.requireNonNull(carIcon);
            carIconConstraints.validateOrThrow(carIcon);
            this.mIcon = carIcon;
            return this;
        }

        @NonNull
        @RequiresCarApi(2)
        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        @NonNull
        public Builder setTitle(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mTitle = CarText.create(charSequence);
            return this;
        }
    }

    private MessageTemplate() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mMessage = null;
        this.mDebugMessage = null;
        this.mIcon = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mActionList = Collections.emptyList();
    }

    MessageTemplate(Builder builder) {
        this.mIsLoading = builder.mIsLoading;
        this.mTitle = builder.mTitle;
        this.mMessage = builder.mMessage;
        this.mDebugMessage = builder.mDebugMessage;
        this.mIcon = builder.mIcon;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionStrip = builder.mActionStrip;
        this.mActionList = CollectionUtils.unmodifiableCopy(builder.mActionList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MessageTemplate) {
            MessageTemplate messageTemplate = (MessageTemplate) obj;
            return this.mIsLoading == messageTemplate.mIsLoading && Objects.equals(this.mTitle, messageTemplate.mTitle) && Objects.equals(this.mMessage, messageTemplate.mMessage) && Objects.equals(this.mDebugMessage, messageTemplate.mDebugMessage) && Objects.equals(this.mHeaderAction, messageTemplate.mHeaderAction) && Objects.equals(this.mActionList, messageTemplate.mActionList) && Objects.equals(this.mIcon, messageTemplate.mIcon) && Objects.equals(this.mActionStrip, messageTemplate.mActionStrip);
        }
        return false;
    }

    @Nullable
    @RequiresCarApi(2)
    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    @NonNull
    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActionList);
    }

    @Nullable
    public CarText getDebugMessage() {
        return this.mDebugMessage;
    }

    @Nullable
    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    @Nullable
    public CarIcon getIcon() {
        return this.mIcon;
    }

    @NonNull
    public CarText getMessage() {
        CarText carText = this.mMessage;
        Objects.requireNonNull(carText);
        return carText;
    }

    @Nullable
    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsLoading), this.mTitle, this.mMessage, this.mDebugMessage, this.mHeaderAction, this.mActionList, this.mIcon, this.mActionStrip);
    }

    @RequiresCarApi(2)
    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        return "MessageTemplate";
    }
}
