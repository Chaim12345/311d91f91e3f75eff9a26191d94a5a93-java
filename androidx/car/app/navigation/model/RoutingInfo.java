package androidx.car.app.navigation.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.Distance;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.navigation.model.NavigationTemplate;
import java.util.Objects;
/* loaded from: classes.dex */
public final class RoutingInfo implements NavigationTemplate.NavigationInfo {
    @Nullable
    @Keep
    private final Distance mCurrentDistance;
    @Nullable
    @Keep
    private final Step mCurrentStep;
    @Keep
    private final boolean mIsLoading;
    @Nullable
    @Keep
    private final CarIcon mJunctionImage;
    @Nullable
    @Keep
    private final Step mNextStep;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        Distance mCurrentDistance;
        @Nullable
        Step mCurrentStep;
        boolean mIsLoading;
        @Nullable
        CarIcon mJunctionImage;
        @Nullable
        Step mNextStep;

        @NonNull
        public RoutingInfo build() {
            Step step = this.mCurrentStep;
            Distance distance = this.mCurrentDistance;
            if (this.mIsLoading) {
                if (step != null || distance != null || this.mNextStep != null || this.mJunctionImage != null) {
                    throw new IllegalStateException("The routing info is set to loading but is not empty");
                }
            } else if (step == null || distance == null) {
                throw new IllegalStateException("Current step and distance must be set during the navigating state");
            } else {
                if (!step.getLanes().isEmpty() && step.getLanesImage() == null) {
                    throw new IllegalStateException("Current step must have a lanes image if the lane information is set");
                }
            }
            return new RoutingInfo(this);
        }

        @NonNull
        public Builder setCurrentStep(@NonNull Step step, @NonNull Distance distance) {
            Objects.requireNonNull(step);
            this.mCurrentStep = step;
            Objects.requireNonNull(distance);
            this.mCurrentDistance = distance;
            return this;
        }

        @NonNull
        public Builder setJunctionImage(@NonNull CarIcon carIcon) {
            CarIconConstraints carIconConstraints = CarIconConstraints.DEFAULT;
            Objects.requireNonNull(carIcon);
            carIconConstraints.validateOrThrow(carIcon);
            this.mJunctionImage = carIcon;
            return this;
        }

        @NonNull
        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        @NonNull
        public Builder setNextStep(@NonNull Step step) {
            Objects.requireNonNull(step);
            this.mNextStep = step;
            return this;
        }
    }

    private RoutingInfo() {
        this.mCurrentStep = null;
        this.mCurrentDistance = null;
        this.mNextStep = null;
        this.mJunctionImage = null;
        this.mIsLoading = false;
    }

    RoutingInfo(Builder builder) {
        this.mCurrentStep = builder.mCurrentStep;
        this.mCurrentDistance = builder.mCurrentDistance;
        this.mNextStep = builder.mNextStep;
        this.mJunctionImage = builder.mJunctionImage;
        this.mIsLoading = builder.mIsLoading;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RoutingInfo) {
            RoutingInfo routingInfo = (RoutingInfo) obj;
            return this.mIsLoading == routingInfo.mIsLoading && Objects.equals(this.mCurrentStep, routingInfo.mCurrentStep) && Objects.equals(this.mCurrentDistance, routingInfo.mCurrentDistance) && Objects.equals(this.mNextStep, routingInfo.mNextStep) && Objects.equals(this.mJunctionImage, routingInfo.mJunctionImage);
        }
        return false;
    }

    @Nullable
    public Distance getCurrentDistance() {
        return this.mCurrentDistance;
    }

    @Nullable
    public Step getCurrentStep() {
        return this.mCurrentStep;
    }

    @Nullable
    public CarIcon getJunctionImage() {
        return this.mJunctionImage;
    }

    @Nullable
    public Step getNextStep() {
        return this.mNextStep;
    }

    public int hashCode() {
        return Objects.hash(this.mCurrentStep, this.mCurrentDistance, this.mNextStep, this.mJunctionImage, Boolean.valueOf(this.mIsLoading));
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        return "RoutingInfo";
    }
}
