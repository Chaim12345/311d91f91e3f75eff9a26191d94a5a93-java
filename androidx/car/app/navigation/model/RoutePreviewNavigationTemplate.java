package androidx.car.app.navigation.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarText;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ModelUtils;
import androidx.car.app.model.Template;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import java.util.Collections;
import java.util.Objects;
/* loaded from: classes.dex */
public final class RoutePreviewNavigationTemplate implements Template {
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
    private final Action mNavigateAction;
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
        Action mNavigateAction;
        @Nullable
        CarText mTitle;

        @NonNull
        public RoutePreviewNavigationTemplate build() {
            boolean z = this.mItemList != null;
            boolean z2 = this.mIsLoading;
            if (z2 != z) {
                if (z2 || this.mNavigateAction != null) {
                    if (CarText.isNullOrEmpty(this.mTitle) && this.mHeaderAction == null) {
                        throw new IllegalStateException("Either the title or header action must be set");
                    }
                    return new RoutePreviewNavigationTemplate(this);
                }
                throw new IllegalStateException("The navigation action cannot be null when the list is not in a loading state");
            }
            throw new IllegalStateException("Template is in a loading state but a list is set, or vice versa");
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
            RowListConstraints rowListConstraints = RowListConstraints.ROW_LIST_CONSTRAINTS_ROUTE_PREVIEW;
            Objects.requireNonNull(itemList);
            rowListConstraints.validateOrThrow(itemList);
            ModelUtils.validateAllRowsHaveDistanceOrDuration(itemList.getItems());
            ModelUtils.validateAllRowsHaveOnlySmallImages(itemList.getItems());
            if (itemList.getItems().isEmpty() || itemList.getOnSelectedDelegate() != null) {
                this.mItemList = itemList;
                return this;
            }
            throw new IllegalArgumentException("The OnSelectedListener must be set for the route list");
        }

        @NonNull
        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        @NonNull
        public Builder setNavigateAction(@NonNull Action action) {
            Objects.requireNonNull(action);
            if (CarText.isNullOrEmpty(action.getTitle())) {
                throw new IllegalArgumentException("The Action's title cannot be null or empty");
            }
            this.mNavigateAction = action;
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

    private RoutePreviewNavigationTemplate() {
        this.mTitle = null;
        this.mIsLoading = false;
        this.mNavigateAction = null;
        this.mItemList = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
    }

    RoutePreviewNavigationTemplate(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mIsLoading = builder.mIsLoading;
        this.mNavigateAction = builder.mNavigateAction;
        this.mItemList = builder.mItemList;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionStrip = builder.mActionStrip;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RoutePreviewNavigationTemplate) {
            RoutePreviewNavigationTemplate routePreviewNavigationTemplate = (RoutePreviewNavigationTemplate) obj;
            return this.mIsLoading == routePreviewNavigationTemplate.mIsLoading && Objects.equals(this.mTitle, routePreviewNavigationTemplate.mTitle) && Objects.equals(this.mNavigateAction, routePreviewNavigationTemplate.mNavigateAction) && Objects.equals(this.mItemList, routePreviewNavigationTemplate.mItemList) && Objects.equals(this.mHeaderAction, routePreviewNavigationTemplate.mHeaderAction) && Objects.equals(this.mActionStrip, routePreviewNavigationTemplate.mActionStrip);
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
    public Action getNavigateAction() {
        return this.mNavigateAction;
    }

    @Nullable
    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, Boolean.valueOf(this.mIsLoading), this.mNavigateAction, this.mItemList, this.mHeaderAction, this.mActionStrip);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        return "RoutePreviewNavigationTemplate";
    }
}
