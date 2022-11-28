package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public final class ActionStrip {
    @Keep
    private final List<Action> mActions;

    /* loaded from: classes.dex */
    public static final class Builder {
        final List<Action> mActions = new ArrayList();
        final Set<Integer> mAddedActionTypes = new HashSet();

        @NonNull
        public Builder addAction(@NonNull Action action) {
            Objects.requireNonNull(action);
            Action action2 = action;
            int type = action2.getType();
            if (type != 1 && this.mAddedActionTypes.contains(Integer.valueOf(type))) {
                throw new IllegalArgumentException("Duplicated action types are disallowed: " + action);
            } else if (CarColor.DEFAULT.equals(action2.getBackgroundColor())) {
                this.mAddedActionTypes.add(Integer.valueOf(type));
                this.mActions.add(action);
                return this;
            } else {
                throw new IllegalArgumentException("Action strip actions don't support background colors");
            }
        }

        @NonNull
        public ActionStrip build() {
            if (this.mActions.isEmpty()) {
                throw new IllegalStateException("Action strip must contain at least one action");
            }
            return new ActionStrip(this);
        }
    }

    private ActionStrip() {
        this.mActions = Collections.emptyList();
    }

    ActionStrip(Builder builder) {
        this.mActions = CollectionUtils.unmodifiableCopy(builder.mActions);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActionStrip) {
            return Objects.equals(this.mActions, ((ActionStrip) obj).mActions);
        }
        return false;
    }

    @NonNull
    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActions);
    }

    @Nullable
    public Action getFirstActionOfType(int i2) {
        for (Action action : this.mActions) {
            if (action instanceof Action) {
                Action action2 = action;
                if (action2.getType() == i2) {
                    return action2;
                }
            }
        }
        return null;
    }

    public int hashCode() {
        return Objects.hashCode(this.mActions);
    }

    @NonNull
    public String toString() {
        return "[action count: " + this.mActions.size() + "]";
    }
}
