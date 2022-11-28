package androidx.car.app.navigation.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Lane {
    @Keep
    private final List<LaneDirection> mDirections;

    /* loaded from: classes.dex */
    public static final class Builder {
        private final List<LaneDirection> mDirections = new ArrayList();

        @NonNull
        public Builder addDirection(@NonNull LaneDirection laneDirection) {
            List<LaneDirection> list = this.mDirections;
            Objects.requireNonNull(laneDirection);
            list.add(laneDirection);
            return this;
        }

        @NonNull
        public Lane build() {
            return new Lane(this.mDirections);
        }
    }

    private Lane() {
        this.mDirections = Collections.emptyList();
    }

    Lane(List<LaneDirection> list) {
        this.mDirections = CollectionUtils.unmodifiableCopy(list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Lane) {
            return Objects.equals(this.mDirections, ((Lane) obj).mDirections);
        }
        return false;
    }

    @NonNull
    public List<LaneDirection> getDirections() {
        return CollectionUtils.emptyIfNull(this.mDirections);
    }

    public int hashCode() {
        return Objects.hashCode(this.mDirections);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[direction count: ");
        List<LaneDirection> list = this.mDirections;
        sb.append(list != null ? list.size() : 0);
        sb.append("]");
        return sb.toString();
    }
}
