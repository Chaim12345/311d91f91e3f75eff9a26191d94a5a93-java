package androidx.car.app.navigation.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarText;
import androidx.car.app.model.Item;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ModelUtils;
import androidx.car.app.model.Template;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class PlaceListNavigationTemplate implements Template {
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
    private final ItemList mItemList;
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
        ItemList mItemList;
        @Nullable
        CarText mTitle;

        @NonNull
        public PlaceListNavigationTemplate build() {
            if (this.mIsLoading != (this.mItemList != null)) {
                if (CarText.isNullOrEmpty(this.mTitle) && this.mHeaderAction == null) {
                    throw new IllegalStateException("Either the title or header action must be set");
                }
                return new PlaceListNavigationTemplate(this);
            }
            throw new IllegalArgumentException("Template is in a loading state but a list is set, or vice versa");
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
        public Builder setItemList(@NonNull ItemList itemList) {
            Objects.requireNonNull(itemList);
            List<Item> items = itemList.getItems();
            RowListConstraints.ROW_LIST_CONSTRAINTS_SIMPLE.validateOrThrow(itemList);
            ModelUtils.validateAllNonBrowsableRowsHaveDistance(items);
            ModelUtils.validateAllRowsHaveOnlySmallImages(items);
            ModelUtils.validateNoRowsHaveBothMarkersAndImages(items);
            this.mItemList = itemList;
            return this;
        }

        @NonNull
        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        @NonNull
        public Builder setTitle(@NonNull CarText carText) {
            Objects.requireNonNull(carText);
            this.mTitle = carText;
            return this;
        }

        @NonNull
        public Builder setTitle(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mTitle = CarText.create(charSequence);
            return this;
        }
    }

    private PlaceListNavigationTemplate() {
        this.mTitle = null;
        this.mIsLoading = false;
        this.mItemList = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
    }

    PlaceListNavigationTemplate(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mIsLoading = builder.mIsLoading;
        this.mItemList = builder.mItemList;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionStrip = builder.mActionStrip;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlaceListNavigationTemplate) {
            PlaceListNavigationTemplate placeListNavigationTemplate = (PlaceListNavigationTemplate) obj;
            return this.mIsLoading == placeListNavigationTemplate.mIsLoading && Objects.equals(this.mTitle, placeListNavigationTemplate.mTitle) && Objects.equals(this.mItemList, placeListNavigationTemplate.mItemList) && Objects.equals(this.mHeaderAction, placeListNavigationTemplate.mHeaderAction) && Objects.equals(this.mActionStrip, placeListNavigationTemplate.mActionStrip);
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
    public ItemList getItemList() {
        return this.mItemList;
    }

    @Nullable
    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, Boolean.valueOf(this.mIsLoading), this.mItemList, this.mHeaderAction, this.mActionStrip);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        return "PlaceListNavigationTemplate";
    }
}
