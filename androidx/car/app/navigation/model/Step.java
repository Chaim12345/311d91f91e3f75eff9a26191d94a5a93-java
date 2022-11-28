package androidx.car.app.navigation.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Step {
    @Nullable
    @Keep
    private final CarText mCue;
    @Keep
    private final List<Lane> mLanes;
    @Nullable
    @Keep
    private final CarIcon mLanesImage;
    @Nullable
    @Keep
    private final Maneuver mManeuver;
    @Nullable
    @Keep
    private final CarText mRoad;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        private CarText mCue;
        private final List<Lane> mLanes = new ArrayList();
        @Nullable
        private CarIcon mLanesImage;
        @Nullable
        private Maneuver mManeuver;
        @Nullable
        private CarText mRoad;

        public Builder() {
        }

        public Builder(@NonNull CarText carText) {
            Objects.requireNonNull(carText);
            this.mCue = carText;
        }

        public Builder(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mCue = CarText.create(charSequence);
        }

        @NonNull
        public Builder addLane(@NonNull Lane lane) {
            List<Lane> list = this.mLanes;
            Objects.requireNonNull(lane);
            list.add(lane);
            return this;
        }

        @NonNull
        public Step build() {
            if (this.mLanesImage == null || !this.mLanes.isEmpty()) {
                return new Step(this.mManeuver, this.mLanes, this.mLanesImage, this.mCue, this.mRoad);
            }
            throw new IllegalStateException("A step must have lane data when the lanes image is set");
        }

        @NonNull
        public Builder setCue(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mCue = CarText.create(charSequence);
            return this;
        }

        @NonNull
        public Builder setLanesImage(@NonNull CarIcon carIcon) {
            Objects.requireNonNull(carIcon);
            this.mLanesImage = carIcon;
            return this;
        }

        @NonNull
        public Builder setManeuver(@NonNull Maneuver maneuver) {
            Objects.requireNonNull(maneuver);
            this.mManeuver = maneuver;
            return this;
        }

        @NonNull
        public Builder setRoad(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mRoad = CarText.create(charSequence);
            return this;
        }
    }

    private Step() {
        this.mManeuver = null;
        this.mLanes = Collections.emptyList();
        this.mLanesImage = null;
        this.mCue = null;
        this.mRoad = null;
    }

    Step(@Nullable Maneuver maneuver, List<Lane> list, @Nullable CarIcon carIcon, @Nullable CarText carText, @Nullable CarText carText2) {
        this.mManeuver = maneuver;
        this.mLanes = CollectionUtils.unmodifiableCopy(list);
        this.mLanesImage = carIcon;
        this.mCue = carText;
        this.mRoad = carText2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Step) {
            Step step = (Step) obj;
            return Objects.equals(this.mManeuver, step.mManeuver) && Objects.equals(this.mLanes, step.mLanes) && Objects.equals(this.mLanesImage, step.mLanesImage) && Objects.equals(this.mCue, step.mCue) && Objects.equals(this.mRoad, step.mRoad);
        }
        return false;
    }

    @Nullable
    public CarText getCue() {
        return this.mCue;
    }

    @NonNull
    public List<Lane> getLanes() {
        return CollectionUtils.emptyIfNull(this.mLanes);
    }

    @Nullable
    public CarIcon getLanesImage() {
        return this.mLanesImage;
    }

    @Nullable
    public Maneuver getManeuver() {
        return this.mManeuver;
    }

    @Nullable
    public CarText getRoad() {
        return this.mRoad;
    }

    public int hashCode() {
        return Objects.hash(this.mManeuver, this.mLanes, this.mLanesImage, this.mCue, this.mRoad);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[maneuver: ");
        sb.append(this.mManeuver);
        sb.append(", lane count: ");
        List<Lane> list = this.mLanes;
        sb.append(list != null ? list.size() : 0);
        sb.append(", lanes image: ");
        sb.append(this.mLanesImage);
        sb.append(", cue: ");
        sb.append(CarText.toShortString(this.mCue));
        sb.append(", road: ");
        sb.append(CarText.toShortString(this.mRoad));
        sb.append("]");
        return sb.toString();
    }
}
