package androidx.car.app.model.signin;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarText;
import androidx.car.app.model.OnClickDelegate;
import androidx.car.app.model.Template;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
@RequiresCarApi(2)
/* loaded from: classes.dex */
public final class SignInTemplate implements Template {
    @Keep
    private final List<Action> mActionList;
    @Nullable
    @Keep
    private final ActionStrip mActionStrip;
    @Nullable
    @Keep
    private final CarText mAdditionalText;
    @Nullable
    @Keep
    private final Action mHeaderAction;
    @Nullable
    @Keep
    private final CarText mInstructions;
    @Keep
    private final boolean mIsLoading;
    @Nullable
    @Keep
    private final SignInMethod mSignInMethod;
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
        CarText mAdditionalText;
        @Nullable
        Action mHeaderAction;
        @Nullable
        CarText mInstructions;
        boolean mIsLoading;
        final SignInMethod mSignInMethod;
        @Nullable
        CarText mTitle;

        public Builder(@NonNull SignInMethod signInMethod) {
            Objects.requireNonNull(signInMethod);
            this.mSignInMethod = signInMethod;
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
        public SignInTemplate build() {
            if (CarText.isNullOrEmpty(this.mTitle) && this.mHeaderAction == null) {
                throw new IllegalStateException("Either the title or header action must be set");
            }
            return new SignInTemplate(this);
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
        public Builder setAdditionalText(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mAdditionalText = CarText.create(charSequence);
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
        public Builder setInstructions(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mInstructions = CarText.create(charSequence);
            return this;
        }

        @NonNull
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

    /* loaded from: classes.dex */
    public interface SignInMethod {
    }

    private SignInTemplate() {
        this.mIsLoading = false;
        this.mHeaderAction = null;
        this.mTitle = null;
        this.mInstructions = null;
        this.mAdditionalText = null;
        this.mActionStrip = null;
        this.mActionList = Collections.emptyList();
        this.mSignInMethod = null;
    }

    SignInTemplate(Builder builder) {
        this.mIsLoading = builder.mIsLoading;
        this.mHeaderAction = builder.mHeaderAction;
        this.mTitle = builder.mTitle;
        this.mInstructions = builder.mInstructions;
        this.mAdditionalText = builder.mAdditionalText;
        this.mActionStrip = builder.mActionStrip;
        this.mActionList = CollectionUtils.unmodifiableCopy(builder.mActionList);
        this.mSignInMethod = builder.mSignInMethod;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SignInTemplate) {
            SignInTemplate signInTemplate = (SignInTemplate) obj;
            return this.mIsLoading == signInTemplate.mIsLoading && Objects.equals(this.mHeaderAction, signInTemplate.mHeaderAction) && Objects.equals(this.mTitle, signInTemplate.mTitle) && Objects.equals(this.mInstructions, signInTemplate.mInstructions) && Objects.equals(this.mAdditionalText, signInTemplate.mAdditionalText) && Objects.equals(this.mActionStrip, signInTemplate.mActionStrip) && Objects.equals(this.mActionList, signInTemplate.mActionList) && Objects.equals(this.mSignInMethod, signInTemplate.mSignInMethod);
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
    public CarText getAdditionalText() {
        return this.mAdditionalText;
    }

    @Nullable
    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    @Nullable
    public CarText getInstructions() {
        return this.mInstructions;
    }

    @NonNull
    public SignInMethod getSignInMethod() {
        SignInMethod signInMethod = this.mSignInMethod;
        Objects.requireNonNull(signInMethod);
        return signInMethod;
    }

    @Nullable
    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsLoading), this.mHeaderAction, this.mTitle, this.mInstructions, this.mAdditionalText, this.mActionStrip, this.mActionList, this.mSignInMethod);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        return "SignInTemplate";
    }
}
