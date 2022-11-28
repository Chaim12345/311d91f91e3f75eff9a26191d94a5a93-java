package androidx.car.app.navigation.model;

import android.annotation.SuppressLint;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.Template;
import androidx.car.app.model.Toggle;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarColorConstraints;
import java.util.Objects;
/* loaded from: classes.dex */
public final class NavigationTemplate implements Template {
    @Nullable
    @Keep
    private final ActionStrip mActionStrip;
    @Nullable
    @Keep
    private final CarColor mBackgroundColor;
    @Nullable
    @Keep
    private final TravelEstimate mDestinationTravelEstimate;
    @Nullable
    @Keep
    private final ActionStrip mMapActionStrip;
    @Nullable
    @Keep
    private final NavigationInfo mNavigationInfo;
    @Nullable
    @Keep
    private final PanModeDelegate mPanModeDelegate;
    @Nullable
    @Keep
    private final Toggle mPanModeToggle;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        ActionStrip mActionStrip;
        @Nullable
        CarColor mBackgroundColor;
        @Nullable
        TravelEstimate mDestinationTravelEstimate;
        @Nullable
        ActionStrip mMapActionStrip;
        @Nullable
        NavigationInfo mNavigationInfo;
        @Nullable
        PanModeDelegate mPanModeDelegate;
        @Nullable
        Toggle mPanModeToggle;

        @NonNull
        public NavigationTemplate build() {
            if (this.mActionStrip != null) {
                return new NavigationTemplate(this);
            }
            throw new IllegalStateException("Action strip for this template must be set");
        }

        @NonNull
        public Builder setActionStrip(@NonNull ActionStrip actionStrip) {
            ActionsConstraints actionsConstraints = ActionsConstraints.ACTIONS_CONSTRAINTS_NAVIGATION;
            Objects.requireNonNull(actionStrip);
            actionsConstraints.validateOrThrow(actionStrip.getActions());
            this.mActionStrip = actionStrip;
            return this;
        }

        @NonNull
        public Builder setBackgroundColor(@NonNull CarColor carColor) {
            CarColorConstraints carColorConstraints = CarColorConstraints.UNCONSTRAINED;
            Objects.requireNonNull(carColor);
            carColorConstraints.validateOrThrow(carColor);
            this.mBackgroundColor = carColor;
            return this;
        }

        @NonNull
        public Builder setDestinationTravelEstimate(@NonNull TravelEstimate travelEstimate) {
            Objects.requireNonNull(travelEstimate);
            if (travelEstimate.getRemainingTimeSeconds() >= 0) {
                this.mDestinationTravelEstimate = travelEstimate;
                return this;
            }
            throw new IllegalArgumentException("The destination travel estimate's remaining time must be greater or equal to zero");
        }

        @NonNull
        @RequiresCarApi(2)
        public Builder setMapActionStrip(@NonNull ActionStrip actionStrip) {
            ActionsConstraints actionsConstraints = ActionsConstraints.ACTIONS_CONSTRAINTS_NAVIGATION_MAP;
            Objects.requireNonNull(actionStrip);
            actionsConstraints.validateOrThrow(actionStrip.getActions());
            this.mMapActionStrip = actionStrip;
            return this;
        }

        @NonNull
        public Builder setNavigationInfo(@NonNull NavigationInfo navigationInfo) {
            Objects.requireNonNull(navigationInfo);
            this.mNavigationInfo = navigationInfo;
            return this;
        }

        @NonNull
        @RequiresCarApi(2)
        @SuppressLint({"MissingGetterMatchingBuilder", "ExecutorRegistration"})
        public Builder setPanModeListener(@NonNull final PanModeListener panModeListener) {
            Objects.requireNonNull(panModeListener);
            this.mPanModeToggle = new Toggle.Builder(new Toggle.OnCheckedChangeListener() { // from class: androidx.car.app.navigation.model.a
                @Override // androidx.car.app.model.Toggle.OnCheckedChangeListener
                public final void onCheckedChange(boolean z) {
                    PanModeListener.this.onPanModeChanged(z);
                }
            }).build();
            this.mPanModeDelegate = PanModeDelegateImpl.create(panModeListener);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface NavigationInfo {
    }

    private NavigationTemplate() {
        this.mNavigationInfo = null;
        this.mBackgroundColor = null;
        this.mDestinationTravelEstimate = null;
        this.mActionStrip = null;
        this.mMapActionStrip = null;
        this.mPanModeToggle = null;
        this.mPanModeDelegate = null;
    }

    NavigationTemplate(Builder builder) {
        this.mNavigationInfo = builder.mNavigationInfo;
        this.mBackgroundColor = builder.mBackgroundColor;
        this.mDestinationTravelEstimate = builder.mDestinationTravelEstimate;
        this.mActionStrip = builder.mActionStrip;
        this.mMapActionStrip = builder.mMapActionStrip;
        this.mPanModeToggle = builder.mPanModeToggle;
        this.mPanModeDelegate = builder.mPanModeDelegate;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NavigationTemplate) {
            NavigationTemplate navigationTemplate = (NavigationTemplate) obj;
            if (Objects.equals(this.mNavigationInfo, navigationTemplate.mNavigationInfo) && Objects.equals(this.mBackgroundColor, navigationTemplate.mBackgroundColor) && Objects.equals(this.mDestinationTravelEstimate, navigationTemplate.mDestinationTravelEstimate) && Objects.equals(this.mActionStrip, navigationTemplate.mActionStrip) && Objects.equals(this.mMapActionStrip, navigationTemplate.mMapActionStrip) && Objects.equals(this.mPanModeToggle, navigationTemplate.mPanModeToggle)) {
                if (Objects.equals(Boolean.valueOf(this.mPanModeDelegate == null), Boolean.valueOf(navigationTemplate.mPanModeDelegate == null))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Nullable
    public ActionStrip getActionStrip() {
        ActionStrip actionStrip = this.mActionStrip;
        Objects.requireNonNull(actionStrip);
        return actionStrip;
    }

    @Nullable
    public CarColor getBackgroundColor() {
        return this.mBackgroundColor;
    }

    @Nullable
    public TravelEstimate getDestinationTravelEstimate() {
        return this.mDestinationTravelEstimate;
    }

    @Nullable
    @RequiresCarApi(2)
    public ActionStrip getMapActionStrip() {
        return this.mMapActionStrip;
    }

    @Nullable
    public NavigationInfo getNavigationInfo() {
        return this.mNavigationInfo;
    }

    @Nullable
    @RequiresCarApi(2)
    public PanModeDelegate getPanModeDelegate() {
        return this.mPanModeDelegate;
    }

    @Nullable
    @RequiresCarApi(2)
    @Deprecated
    public Toggle getPanModeToggle() {
        return this.mPanModeToggle;
    }

    public int hashCode() {
        Object[] objArr = new Object[7];
        objArr[0] = this.mNavigationInfo;
        objArr[1] = this.mBackgroundColor;
        objArr[2] = this.mDestinationTravelEstimate;
        objArr[3] = this.mActionStrip;
        objArr[4] = this.mMapActionStrip;
        objArr[5] = this.mPanModeToggle;
        objArr[6] = Boolean.valueOf(this.mPanModeDelegate == null);
        return Objects.hash(objArr);
    }

    @NonNull
    public String toString() {
        return "NavigationTemplate";
    }
}
