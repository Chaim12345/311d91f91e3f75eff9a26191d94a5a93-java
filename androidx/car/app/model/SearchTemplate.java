package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import java.util.Collections;
import java.util.Objects;
/* loaded from: classes.dex */
public final class SearchTemplate implements Template {
    @Nullable
    @Keep
    private final ActionStrip mActionStrip;
    @Nullable
    @Keep
    private final Action mHeaderAction;
    @Nullable
    @Keep
    private final String mInitialSearchText;
    @Keep
    private final boolean mIsLoading;
    @Nullable
    @Keep
    private final ItemList mItemList;
    @Nullable
    @Keep
    private final SearchCallbackDelegate mSearchCallbackDelegate;
    @Nullable
    @Keep
    private final String mSearchHint;
    @Keep
    private final boolean mShowKeyboardByDefault;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        ActionStrip mActionStrip;
        @Nullable
        Action mHeaderAction;
        @Nullable
        String mInitialSearchText;
        boolean mIsLoading;
        @Nullable
        ItemList mItemList;
        final SearchCallbackDelegate mSearchCallbackDelegate;
        @Nullable
        String mSearchHint;
        boolean mShowKeyboardByDefault = true;

        @SuppressLint({"ExecutorRegistration"})
        public Builder(@NonNull SearchCallback searchCallback) {
            this.mSearchCallbackDelegate = SearchCallbackDelegateImpl.create(searchCallback);
        }

        @NonNull
        public SearchTemplate build() {
            if (!this.mIsLoading || this.mItemList == null) {
                return new SearchTemplate(this);
            }
            throw new IllegalArgumentException("Template is in a loading state but a list is set");
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
        public Builder setInitialSearchText(@NonNull String str) {
            Objects.requireNonNull(str);
            this.mInitialSearchText = str;
            return this;
        }

        @NonNull
        public Builder setItemList(@NonNull ItemList itemList) {
            RowListConstraints rowListConstraints = RowListConstraints.ROW_LIST_CONSTRAINTS_SIMPLE;
            Objects.requireNonNull(itemList);
            rowListConstraints.validateOrThrow(itemList);
            this.mItemList = itemList;
            return this;
        }

        @NonNull
        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        @NonNull
        public Builder setSearchHint(@NonNull String str) {
            Objects.requireNonNull(str);
            this.mSearchHint = str;
            return this;
        }

        @NonNull
        public Builder setShowKeyboardByDefault(boolean z) {
            this.mShowKeyboardByDefault = z;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface SearchCallback {
        default void onSearchSubmitted(@NonNull String str) {
        }

        default void onSearchTextChanged(@NonNull String str) {
        }
    }

    private SearchTemplate() {
        this.mInitialSearchText = null;
        this.mSearchHint = null;
        this.mIsLoading = false;
        this.mItemList = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mSearchCallbackDelegate = null;
        this.mShowKeyboardByDefault = true;
    }

    SearchTemplate(Builder builder) {
        this.mInitialSearchText = builder.mInitialSearchText;
        this.mSearchHint = builder.mSearchHint;
        this.mIsLoading = builder.mIsLoading;
        this.mItemList = builder.mItemList;
        this.mSearchCallbackDelegate = builder.mSearchCallbackDelegate;
        this.mShowKeyboardByDefault = builder.mShowKeyboardByDefault;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionStrip = builder.mActionStrip;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SearchTemplate) {
            SearchTemplate searchTemplate = (SearchTemplate) obj;
            return this.mIsLoading == searchTemplate.mIsLoading && Objects.equals(this.mInitialSearchText, searchTemplate.mInitialSearchText) && Objects.equals(this.mSearchHint, searchTemplate.mSearchHint) && Objects.equals(this.mItemList, searchTemplate.mItemList) && Objects.equals(this.mHeaderAction, searchTemplate.mHeaderAction) && Objects.equals(this.mActionStrip, searchTemplate.mActionStrip) && this.mShowKeyboardByDefault == searchTemplate.mShowKeyboardByDefault;
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
    public String getInitialSearchText() {
        return this.mInitialSearchText;
    }

    @Nullable
    public ItemList getItemList() {
        return this.mItemList;
    }

    @NonNull
    public SearchCallbackDelegate getSearchCallbackDelegate() {
        SearchCallbackDelegate searchCallbackDelegate = this.mSearchCallbackDelegate;
        Objects.requireNonNull(searchCallbackDelegate);
        return searchCallbackDelegate;
    }

    @Nullable
    public String getSearchHint() {
        return this.mSearchHint;
    }

    public int hashCode() {
        return Objects.hash(this.mInitialSearchText, Boolean.valueOf(this.mIsLoading), this.mSearchHint, this.mItemList, Boolean.valueOf(this.mShowKeyboardByDefault), this.mHeaderAction, this.mActionStrip);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public boolean isShowKeyboardByDefault() {
        return this.mShowKeyboardByDefault;
    }

    @NonNull
    public String toString() {
        return "SearchTemplate";
    }
}
