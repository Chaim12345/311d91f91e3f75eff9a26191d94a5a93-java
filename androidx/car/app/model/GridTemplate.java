package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.constraints.ActionsConstraints;
import java.util.Collections;
import java.util.Objects;
/* loaded from: classes.dex */
public final class GridTemplate implements Template {
    @Nullable
    @Keep
    private final ActionStrip mActionStrip;
    @Nullable
    @Keep
    private final Action mHeaderAction;
    @Keep
    private final boolean mIsLoading;
    @Nullable
    @Keep
    private final ItemList mSingleList;
    @Nullable
    @Keep
    private final CarText mTitle;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        ActionStrip mActionStrip;
        @Nullable
        Action mHeaderAction;
        boolean mIsLoading;
        @Nullable
        ItemList mSingleList;
        @Nullable
        CarText mTitle;

        @NonNull
        public GridTemplate build() {
            ItemList itemList = this.mSingleList;
            if (this.mIsLoading != (itemList != null)) {
                if (itemList != null) {
                    for (Item item : itemList.getItems()) {
                        if (!(item instanceof GridItem)) {
                            throw new IllegalArgumentException("All the items in grid template's item list must be grid items");
                        }
                    }
                }
                if (CarText.isNullOrEmpty(this.mTitle) && this.mHeaderAction == null) {
                    throw new IllegalStateException("Either the title or header action must be set");
                }
                return new GridTemplate(this);
            }
            throw new IllegalStateException("Template is in a loading state but lists are added, or vice versa");
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
            ActionsConstraints.ACTIONS_CONSTRAINTS_HEADER.validateOrThrow(Collections.singletonList(action));
            this.mHeaderAction = action;
            return this;
        }

        @NonNull
        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        @NonNull
        public Builder setSingleList(@NonNull ItemList itemList) {
            Objects.requireNonNull(itemList);
            this.mSingleList = itemList;
            return this;
        }

        @NonNull
        public Builder setTitle(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mTitle = CarText.create(charSequence);
            return this;
        }
    }

    private GridTemplate() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mHeaderAction = null;
        this.mSingleList = null;
        this.mActionStrip = null;
    }

    GridTemplate(Builder builder) {
        this.mIsLoading = builder.mIsLoading;
        this.mTitle = builder.mTitle;
        this.mHeaderAction = builder.mHeaderAction;
        this.mSingleList = builder.mSingleList;
        this.mActionStrip = builder.mActionStrip;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GridTemplate) {
            GridTemplate gridTemplate = (GridTemplate) obj;
            return this.mIsLoading == gridTemplate.mIsLoading && Objects.equals(this.mTitle, gridTemplate.mTitle) && Objects.equals(this.mHeaderAction, gridTemplate.mHeaderAction) && Objects.equals(this.mSingleList, gridTemplate.mSingleList) && Objects.equals(this.mActionStrip, gridTemplate.mActionStrip);
        }
        return false;
    }

    @Nullable
    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    @Nullable
    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    @Nullable
    public ItemList getSingleList() {
        return this.mSingleList;
    }

    @Nullable
    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsLoading), this.mTitle, this.mHeaderAction, this.mSingleList, this.mActionStrip);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        return "GridTemplate";
    }
}
