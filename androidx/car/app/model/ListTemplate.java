package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class ListTemplate implements Template {
    @Nullable
    @Keep
    private final ActionStrip mActionStrip;
    @Nullable
    @Keep
    private final Action mHeaderAction;
    @Keep
    private final boolean mIsLoading;
    @Keep
    private final List<SectionedItemList> mSectionedLists;
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
        boolean mHasSelectableList;
        @Nullable
        Action mHeaderAction;
        boolean mIsLoading;
        final List<SectionedItemList> mSectionedLists = new ArrayList();
        @Nullable
        ItemList mSingleList;
        @Nullable
        CarText mTitle;

        @NonNull
        public Builder addSectionedList(@NonNull SectionedItemList sectionedItemList) {
            Objects.requireNonNull(sectionedItemList);
            if (sectionedItemList.getHeader().toString().length() != 0) {
                ItemList itemList = sectionedItemList.getItemList();
                boolean z = itemList.getOnSelectedDelegate() != null;
                if (this.mHasSelectableList || (z && !this.mSectionedLists.isEmpty())) {
                    throw new IllegalArgumentException("A selectable list cannot be added alongside any other lists");
                }
                this.mHasSelectableList = z;
                if (itemList.getItems().isEmpty()) {
                    throw new IllegalArgumentException("List cannot be empty");
                }
                if (itemList.getOnItemVisibilityChangedDelegate() == null) {
                    this.mSingleList = null;
                    this.mSectionedLists.add(sectionedItemList);
                    return this;
                }
                throw new IllegalArgumentException("OnItemVisibilityChangedListener in the list is disallowed");
            }
            throw new IllegalArgumentException("Header cannot be empty");
        }

        @NonNull
        public ListTemplate build() {
            boolean z = (this.mSingleList == null && this.mSectionedLists.isEmpty()) ? false : true;
            if (this.mIsLoading != z) {
                if (z) {
                    if (this.mSectionedLists.isEmpty()) {
                        ItemList itemList = this.mSingleList;
                        if (itemList != null) {
                            RowListConstraints.ROW_LIST_CONSTRAINTS_FULL_LIST.validateOrThrow(itemList);
                        }
                    } else {
                        RowListConstraints.ROW_LIST_CONSTRAINTS_FULL_LIST.validateOrThrow(this.mSectionedLists);
                    }
                }
                if (CarText.isNullOrEmpty(this.mTitle) && this.mHeaderAction == null) {
                    throw new IllegalStateException("Either the title or header action must be set");
                }
                return new ListTemplate(this);
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
            ActionsConstraints actionsConstraints = ActionsConstraints.ACTIONS_CONSTRAINTS_HEADER;
            Objects.requireNonNull(action);
            actionsConstraints.validateOrThrow(Collections.singletonList(action));
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
            this.mSectionedLists.clear();
            this.mHasSelectableList = false;
            return this;
        }

        @NonNull
        public Builder setTitle(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mTitle = CarText.create(charSequence);
            return this;
        }
    }

    private ListTemplate() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mHeaderAction = null;
        this.mSingleList = null;
        this.mSectionedLists = Collections.emptyList();
        this.mActionStrip = null;
    }

    ListTemplate(Builder builder) {
        this.mIsLoading = builder.mIsLoading;
        this.mTitle = builder.mTitle;
        this.mHeaderAction = builder.mHeaderAction;
        this.mSingleList = builder.mSingleList;
        this.mSectionedLists = CollectionUtils.unmodifiableCopy(builder.mSectionedLists);
        this.mActionStrip = builder.mActionStrip;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ListTemplate) {
            ListTemplate listTemplate = (ListTemplate) obj;
            return this.mIsLoading == listTemplate.mIsLoading && Objects.equals(this.mTitle, listTemplate.mTitle) && Objects.equals(this.mHeaderAction, listTemplate.mHeaderAction) && Objects.equals(this.mSingleList, listTemplate.mSingleList) && Objects.equals(this.mSectionedLists, listTemplate.mSectionedLists) && Objects.equals(this.mActionStrip, listTemplate.mActionStrip);
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

    @NonNull
    public List<SectionedItemList> getSectionedLists() {
        return CollectionUtils.emptyIfNull(this.mSectionedLists);
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
        return Objects.hash(Boolean.valueOf(this.mIsLoading), this.mTitle, this.mHeaderAction, this.mSingleList, this.mSectionedLists, this.mActionStrip);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        return "ListTemplate";
    }
}
