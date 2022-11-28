package androidx.car.app.navigation.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.CarText;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Trip {
    @Nullable
    @Keep
    private final CarText mCurrentRoad;
    @Keep
    private final List<TravelEstimate> mDestinationTravelEstimates;
    @Keep
    private final List<Destination> mDestinations;
    @Keep
    private final boolean mIsLoading;
    @Keep
    private final List<TravelEstimate> mStepTravelEstimates;
    @Keep
    private final List<Step> mSteps;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        CarText mCurrentRoad;
        boolean mIsLoading;
        final List<Destination> mDestinations = new ArrayList();
        final List<Step> mSteps = new ArrayList();
        final List<TravelEstimate> mDestinationTravelEstimates = new ArrayList();
        final List<TravelEstimate> mStepTravelEstimates = new ArrayList();

        @NonNull
        public Builder addDestination(@NonNull Destination destination, @NonNull TravelEstimate travelEstimate) {
            List<Destination> list = this.mDestinations;
            Objects.requireNonNull(destination);
            list.add(destination);
            List<TravelEstimate> list2 = this.mDestinationTravelEstimates;
            Objects.requireNonNull(travelEstimate);
            list2.add(travelEstimate);
            return this;
        }

        @NonNull
        public Builder addStep(@NonNull Step step, @NonNull TravelEstimate travelEstimate) {
            List<Step> list = this.mSteps;
            Objects.requireNonNull(step);
            list.add(step);
            List<TravelEstimate> list2 = this.mStepTravelEstimates;
            Objects.requireNonNull(travelEstimate);
            list2.add(travelEstimate);
            return this;
        }

        @NonNull
        public Trip build() {
            if (this.mDestinations.size() == this.mDestinationTravelEstimates.size()) {
                if (this.mSteps.size() == this.mStepTravelEstimates.size()) {
                    if (!this.mIsLoading || this.mSteps.isEmpty()) {
                        return new Trip(this);
                    }
                    throw new IllegalArgumentException("Step information may not be set while loading");
                }
                throw new IllegalArgumentException("Steps and step travel estimates sizes must match");
            }
            throw new IllegalArgumentException("Destinations and destination travel estimates sizes must match");
        }

        @NonNull
        public Builder setCurrentRoad(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mCurrentRoad = CarText.create(charSequence);
            return this;
        }

        @NonNull
        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }
    }

    private Trip() {
        this.mDestinations = Collections.emptyList();
        this.mSteps = Collections.emptyList();
        this.mDestinationTravelEstimates = Collections.emptyList();
        this.mStepTravelEstimates = Collections.emptyList();
        this.mCurrentRoad = null;
        this.mIsLoading = false;
    }

    Trip(Builder builder) {
        this.mDestinations = CollectionUtils.unmodifiableCopy(builder.mDestinations);
        this.mSteps = CollectionUtils.unmodifiableCopy(builder.mSteps);
        this.mDestinationTravelEstimates = CollectionUtils.unmodifiableCopy(builder.mDestinationTravelEstimates);
        this.mStepTravelEstimates = CollectionUtils.unmodifiableCopy(builder.mStepTravelEstimates);
        this.mCurrentRoad = builder.mCurrentRoad;
        this.mIsLoading = builder.mIsLoading;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Trip) {
            Trip trip = (Trip) obj;
            return Objects.equals(this.mDestinations, trip.mDestinations) && Objects.equals(this.mSteps, trip.mSteps) && Objects.equals(this.mDestinationTravelEstimates, trip.mDestinationTravelEstimates) && Objects.equals(this.mStepTravelEstimates, trip.mStepTravelEstimates) && Objects.equals(this.mCurrentRoad, trip.mCurrentRoad) && Objects.equals(Boolean.valueOf(this.mIsLoading), Boolean.valueOf(trip.mIsLoading));
        }
        return false;
    }

    @Nullable
    public CarText getCurrentRoad() {
        return this.mCurrentRoad;
    }

    @NonNull
    public List<TravelEstimate> getDestinationTravelEstimates() {
        return CollectionUtils.emptyIfNull(this.mDestinationTravelEstimates);
    }

    @NonNull
    public List<Destination> getDestinations() {
        return CollectionUtils.emptyIfNull(this.mDestinations);
    }

    @NonNull
    public List<TravelEstimate> getStepTravelEstimates() {
        return CollectionUtils.emptyIfNull(this.mStepTravelEstimates);
    }

    @NonNull
    public List<Step> getSteps() {
        return CollectionUtils.emptyIfNull(this.mSteps);
    }

    public int hashCode() {
        return Objects.hash(this.mDestinations, this.mSteps, this.mDestinationTravelEstimates, this.mStepTravelEstimates, this.mCurrentRoad);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        return "[ destinations : " + this.mDestinations.toString() + ", steps: " + this.mSteps.toString() + ", dest estimates: " + this.mDestinationTravelEstimates.toString() + ", step estimates: " + this.mStepTravelEstimates.toString() + ", road: " + CarText.toShortString(this.mCurrentRoad) + ", isLoading: " + this.mIsLoading + "]";
    }
}
