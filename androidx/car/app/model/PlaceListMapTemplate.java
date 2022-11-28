package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class PlaceListMapTemplate implements Template {
    @Nullable
    @Keep
    private final ActionStrip mActionStrip;
    @Nullable
    @Keep
    private final Place mAnchor;
    @Nullable
    @Keep
    private final Action mHeaderAction;
    @Keep
    private final boolean mIsLoading;
    @Nullable
    @Keep
    private final ItemList mItemList;
    @Keep
    private final boolean mShowCurrentLocation;
    @Nullable
    @Keep
    private final CarText mTitle;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        ActionStrip mActionStrip;
        @Nullable
        Place mAnchor;
        @Nullable
        Action mHeaderAction;
        boolean mIsLoading;
        @Nullable
        ItemList mItemList;
        boolean mShowCurrentLocation;
        @Nullable
        CarText mTitle;

        @NonNull
        public PlaceListMapTemplate build() {
            if (this.mIsLoading != (this.mItemList != null)) {
                if (CarText.isNullOrEmpty(this.mTitle) && this.mHeaderAction == null) {
                    throw new IllegalStateException("Either the title or header action must be set");
                }
                return new PlaceListMapTemplate(this);
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
        public Builder setAnchor(@NonNull Place place) {
            Objects.requireNonNull(place);
            this.mAnchor = place;
            return this;
        }

        @NonNull
        public Builder setCurrentLocationEnabled(boolean z) {
            this.mShowCurrentLocation = z;
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

    private PlaceListMapTemplate() {
        this.mShowCurrentLocation = false;
        this.mIsLoading = false;
        this.mTitle = null;
        this.mItemList = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mAnchor = null;
    }

    PlaceListMapTemplate(Builder builder) {
        this.mShowCurrentLocation = builder.mShowCurrentLocation;
        this.mIsLoading = builder.mIsLoading;
        this.mTitle = builder.mTitle;
        this.mItemList = builder.mItemList;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionStrip = builder.mActionStrip;
        this.mAnchor = builder.mAnchor;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlaceListMapTemplate) {
            PlaceListMapTemplate placeListMapTemplate = (PlaceListMapTemplate) obj;
            return this.mShowCurrentLocation == placeListMapTemplate.mShowCurrentLocation && this.mIsLoading == placeListMapTemplate.mIsLoading && Objects.equals(this.mTitle, placeListMapTemplate.mTitle) && Objects.equals(this.mItemList, placeListMapTemplate.mItemList) && Objects.equals(this.mHeaderAction, placeListMapTemplate.mHeaderAction) && Objects.equals(this.mActionStrip, placeListMapTemplate.mActionStrip) && Objects.equals(this.mAnchor, placeListMapTemplate.mAnchor);
        }
        return false;
    }

    @Nullable
    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    @Nullable
    public Place getAnchor() {
        return this.mAnchor;
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
        return Objects.hash(Boolean.valueOf(this.mShowCurrentLocation), Boolean.valueOf(this.mIsLoading), this.mTitle, this.mItemList, this.mHeaderAction, this.mActionStrip, this.mAnchor);
    }

    public boolean isCurrentLocationEnabled() {
        return this.mShowCurrentLocation;
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        return "PlaceListMapTemplate";
    }
}
