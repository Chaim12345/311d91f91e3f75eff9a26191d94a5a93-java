package androidx.car.app.model.constraints;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarText;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class ActionsConstraints {
    @NonNull
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_BODY;
    @NonNull
    private static final ActionsConstraints ACTIONS_CONSTRAINTS_CONSERVATIVE;
    @NonNull
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_HEADER;
    @NonNull
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_NAVIGATION;
    @NonNull
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_NAVIGATION_MAP;
    @NonNull
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_SIMPLE;
    private final Set<Integer> mDisallowedActionTypes;
    private final int mMaxActions;
    private final int mMaxCustomTitles;
    private final Set<Integer> mRequiredActionTypes;

    @VisibleForTesting
    /* loaded from: classes.dex */
    public static final class Builder {
        final Set<Integer> mDisallowedActionTypes;
        int mMaxActions;
        int mMaxCustomTitles;
        final Set<Integer> mRequiredActionTypes;

        public Builder() {
            this.mMaxActions = Integer.MAX_VALUE;
            this.mRequiredActionTypes = new HashSet();
            this.mDisallowedActionTypes = new HashSet();
        }

        public Builder(@NonNull ActionsConstraints actionsConstraints) {
            this.mMaxActions = Integer.MAX_VALUE;
            HashSet hashSet = new HashSet();
            this.mRequiredActionTypes = hashSet;
            HashSet hashSet2 = new HashSet();
            this.mDisallowedActionTypes = hashSet2;
            Objects.requireNonNull(actionsConstraints);
            this.mMaxActions = actionsConstraints.getMaxActions();
            this.mMaxCustomTitles = actionsConstraints.getMaxCustomTitles();
            hashSet.addAll(actionsConstraints.getRequiredActionTypes());
            hashSet2.addAll(actionsConstraints.getDisallowedActionTypes());
        }

        @NonNull
        public Builder addDisallowedActionType(int i2) {
            this.mDisallowedActionTypes.add(Integer.valueOf(i2));
            return this;
        }

        @NonNull
        public Builder addRequiredActionType(int i2) {
            this.mRequiredActionTypes.add(Integer.valueOf(i2));
            return this;
        }

        @NonNull
        public ActionsConstraints build() {
            return new ActionsConstraints(this);
        }

        @NonNull
        public Builder setMaxActions(int i2) {
            this.mMaxActions = i2;
            return this;
        }

        @NonNull
        public Builder setMaxCustomTitles(int i2) {
            this.mMaxCustomTitles = i2;
            return this;
        }
    }

    static {
        ActionsConstraints build = new Builder().setMaxActions(2).build();
        ACTIONS_CONSTRAINTS_CONSERVATIVE = build;
        ACTIONS_CONSTRAINTS_BODY = new Builder(build).setMaxCustomTitles(2).build();
        ACTIONS_CONSTRAINTS_HEADER = new Builder().setMaxActions(1).addDisallowedActionType(1).build();
        ACTIONS_CONSTRAINTS_SIMPLE = new Builder(build).setMaxCustomTitles(1).build();
        ACTIONS_CONSTRAINTS_NAVIGATION = new Builder(build).setMaxActions(4).setMaxCustomTitles(1).addRequiredActionType(1).build();
        ACTIONS_CONSTRAINTS_NAVIGATION_MAP = new Builder(build).setMaxActions(4).build();
    }

    ActionsConstraints(Builder builder) {
        int i2 = builder.mMaxActions;
        this.mMaxActions = i2;
        this.mMaxCustomTitles = builder.mMaxCustomTitles;
        HashSet hashSet = new HashSet(builder.mRequiredActionTypes);
        this.mRequiredActionTypes = hashSet;
        if (!builder.mDisallowedActionTypes.isEmpty()) {
            HashSet hashSet2 = new HashSet(builder.mDisallowedActionTypes);
            hashSet2.retainAll(hashSet);
            if (!hashSet2.isEmpty()) {
                throw new IllegalArgumentException("Disallowed action types cannot also be in the required set");
            }
        }
        this.mDisallowedActionTypes = new HashSet(builder.mDisallowedActionTypes);
        if (hashSet.size() > i2) {
            throw new IllegalArgumentException("Required action types exceeded max allowed actions");
        }
    }

    @NonNull
    public Set<Integer> getDisallowedActionTypes() {
        return this.mDisallowedActionTypes;
    }

    public int getMaxActions() {
        return this.mMaxActions;
    }

    public int getMaxCustomTitles() {
        return this.mMaxCustomTitles;
    }

    @NonNull
    public Set<Integer> getRequiredActionTypes() {
        return this.mRequiredActionTypes;
    }

    public void validateOrThrow(@NonNull List<Action> list) {
        int i2 = this.mMaxActions;
        int i3 = this.mMaxCustomTitles;
        Set<Integer> emptySet = this.mRequiredActionTypes.isEmpty() ? Collections.emptySet() : new HashSet(this.mRequiredActionTypes);
        for (Action action : list) {
            if (this.mDisallowedActionTypes.contains(Integer.valueOf(action.getType()))) {
                throw new IllegalArgumentException(Action.typeToString(action.getType()) + " is disallowed");
            }
            emptySet.remove(Integer.valueOf(action.getType()));
            CarText title = action.getTitle();
            if (title != null && !title.isEmpty() && i3 - 1 < 0) {
                throw new IllegalArgumentException("Action strip exceeded max number of " + this.mMaxCustomTitles + " actions with custom titles");
            }
            i2--;
            if (i2 < 0) {
                throw new IllegalArgumentException("Action strip exceeded max number of " + this.mMaxActions + " actions");
            }
        }
        if (emptySet.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : emptySet) {
            sb.append(Action.typeToString(num.intValue()));
            sb.append(",");
        }
        throw new IllegalArgumentException("Missing required action types: " + ((Object) sb));
    }
}
