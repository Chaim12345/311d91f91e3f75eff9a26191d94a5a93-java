package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
@RequiresCarApi(2)
/* loaded from: classes.dex */
public final class LongMessageTemplate implements Template {
    @Keep
    private final List<Action> mActionList;
    @Nullable
    @Keep
    private final ActionStrip mActionStrip;
    @Nullable
    @Keep
    private final Action mHeaderAction;
    @Nullable
    @Keep
    private final CarText mMessage;
    @Nullable
    @Keep
    private final CarText mTitle;

    @RequiresCarApi(2)
    /* loaded from: classes.dex */
    public static final class Builder {
        List<Action> mActionList = new ArrayList();
        @Nullable
        ActionStrip mActionStrip;
        @Nullable
        Action mHeaderAction;
        final CarText mMessage;
        @Nullable
        CarText mTitle;

        public Builder(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mMessage = CarText.create(charSequence);
        }

        @NonNull
        public Builder addAction(@NonNull Action action) {
            Objects.requireNonNull(action);
            OnClickDelegate onClickDelegate = action.getOnClickDelegate();
            Objects.requireNonNull(onClickDelegate);
            if (onClickDelegate.isParkedOnly()) {
                this.mActionList.add(action);
                ActionsConstraints.ACTIONS_CONSTRAINTS_BODY.validateOrThrow(this.mActionList);
                return this;
            }
            throw new IllegalArgumentException("The action must use a ParkedOnlyOnClickListener");
        }

        @NonNull
        public LongMessageTemplate build() {
            if (this.mMessage.isEmpty()) {
                throw new IllegalStateException("Message cannot be empty");
            }
            if (CarText.isNullOrEmpty(this.mTitle) && this.mHeaderAction == null) {
                throw new IllegalStateException("Either the title or header action must be set");
            }
            return new LongMessageTemplate(this);
        }

        @NonNull
        public Builder setActionStrip(@NonNull ActionStrip actionStrip) {
            ActionsConstraints actionsConstraints = ActionsConstraints.ACTIONS_CONSTRAINTS_SIMPLE;
            Objects.requireNonNull(actionStrip);
            actionsConstraints.validateOrThrow(actionStrip.getActions());
            this.mActionStrip = actionStrip;
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
        public Builder setTitle(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mTitle = CarText.create(charSequence);
            return this;
        }
    }

    private LongMessageTemplate() {
        this.mTitle = null;
        this.mMessage = null;
        this.mActionStrip = null;
        this.mHeaderAction = null;
        this.mActionList = Collections.emptyList();
    }

    LongMessageTemplate(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mMessage = builder.mMessage;
        this.mActionStrip = builder.mActionStrip;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionList = CollectionUtils.unmodifiableCopy(builder.mActionList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LongMessageTemplate) {
            LongMessageTemplate longMessageTemplate = (LongMessageTemplate) obj;
            return Objects.equals(this.mTitle, longMessageTemplate.mTitle) && Objects.equals(this.mMessage, longMessageTemplate.mMessage) && Objects.equals(this.mHeaderAction, longMessageTemplate.mHeaderAction) && Objects.equals(this.mActionList, longMessageTemplate.mActionList) && Objects.equals(this.mActionStrip, longMessageTemplate.mActionStrip);
        }
        return false;
    }

    @Nullable
    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    @NonNull
    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActionList);
    }

    @Nullable
    public Action getHeaderAction() {
        return this.mHeaderAction;
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
        return Objects.hash(this.mTitle, this.mMessage, this.mHeaderAction, this.mActionList, this.mActionStrip);
    }

    @NonNull
    public String toString() {
        return "LongMessageTemplate";
    }
}
