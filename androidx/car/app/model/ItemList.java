package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class ItemList {
    @Keep
    private final List<Item> mItems;
    @Nullable
    @Keep
    private final CarText mNoItemsMessage;
    @Nullable
    @Keep
    private final OnItemVisibilityChangedDelegate mOnItemVisibilityChangedDelegate;
    @Nullable
    @Keep
    private final OnSelectedDelegate mOnSelectedDelegate;
    @Keep
    private final int mSelectedIndex;

    /* loaded from: classes.dex */
    public static final class Builder {
        final List<Item> mItems = new ArrayList();
        @Nullable
        CarText mNoItemsMessage;
        @Nullable
        OnItemVisibilityChangedDelegate mOnItemVisibilityChangedDelegate;
        @Nullable
        OnSelectedDelegate mOnSelectedDelegate;
        int mSelectedIndex;

        @NonNull
        public Builder addItem(@NonNull Item item) {
            List<Item> list = this.mItems;
            Objects.requireNonNull(item);
            list.add(item);
            return this;
        }

        @NonNull
        public ItemList build() {
            if (this.mOnSelectedDelegate != null) {
                int size = this.mItems.size();
                if (size == 0) {
                    throw new IllegalStateException("A selectable list cannot be empty");
                }
                if (this.mSelectedIndex >= size) {
                    throw new IllegalStateException("The selected item index (" + this.mSelectedIndex + ") is larger than the size of the list (" + size + ")");
                }
                for (Item item : this.mItems) {
                    if (ItemList.getOnClickDelegate(item) != null) {
                        throw new IllegalStateException("Items that belong to selectable lists can't have an onClickListener. Use the OnSelectedListener of the list instead");
                    }
                    if (ItemList.getToggle(item) != null) {
                        throw new IllegalStateException("Items that belong to selectable lists can't have a toggle");
                    }
                }
            }
            return new ItemList(this);
        }

        @NonNull
        public Builder setNoItemsMessage(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mNoItemsMessage = CarText.create(charSequence);
            return this;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder", "ExecutorRegistration"})
        public Builder setOnItemsVisibilityChangedListener(@NonNull OnItemVisibilityChangedListener onItemVisibilityChangedListener) {
            this.mOnItemVisibilityChangedDelegate = OnItemVisibilityChangedDelegateImpl.create(onItemVisibilityChangedListener);
            return this;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder", "ExecutorRegistration"})
        public Builder setOnSelectedListener(@NonNull OnSelectedListener onSelectedListener) {
            this.mOnSelectedDelegate = OnSelectedDelegateImpl.create(onSelectedListener);
            return this;
        }

        @NonNull
        public Builder setSelectedIndex(@IntRange(from = 0) int i2) {
            if (i2 >= 0) {
                this.mSelectedIndex = i2;
                return this;
            }
            throw new IllegalArgumentException("The item index must be larger than or equal to 0");
        }
    }

    /* loaded from: classes.dex */
    public interface OnItemVisibilityChangedListener {
        void onItemVisibilityChanged(int i2, int i3);
    }

    /* loaded from: classes.dex */
    public interface OnSelectedListener {
        void onSelected(int i2);
    }

    private ItemList() {
        this.mSelectedIndex = 0;
        this.mItems = Collections.emptyList();
        this.mNoItemsMessage = null;
        this.mOnSelectedDelegate = null;
        this.mOnItemVisibilityChangedDelegate = null;
    }

    ItemList(Builder builder) {
        this.mSelectedIndex = builder.mSelectedIndex;
        this.mItems = CollectionUtils.unmodifiableCopy(builder.mItems);
        this.mNoItemsMessage = builder.mNoItemsMessage;
        this.mOnSelectedDelegate = builder.mOnSelectedDelegate;
        this.mOnItemVisibilityChangedDelegate = builder.mOnItemVisibilityChangedDelegate;
    }

    @Nullable
    static OnClickDelegate getOnClickDelegate(Item item) {
        if (item instanceof Row) {
            return ((Row) item).getOnClickDelegate();
        }
        if (item instanceof GridItem) {
            return ((GridItem) item).getOnClickDelegate();
        }
        return null;
    }

    @Nullable
    static Toggle getToggle(Item item) {
        if (item instanceof Row) {
            return ((Row) item).getToggle();
        }
        return null;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ItemList) {
            ItemList itemList = (ItemList) obj;
            if (this.mSelectedIndex == itemList.mSelectedIndex && Objects.equals(this.mItems, itemList.mItems)) {
                if (Objects.equals(Boolean.valueOf(this.mOnSelectedDelegate == null), Boolean.valueOf(itemList.mOnSelectedDelegate == null))) {
                    if (Objects.equals(Boolean.valueOf(this.mOnItemVisibilityChangedDelegate == null), Boolean.valueOf(itemList.mOnItemVisibilityChangedDelegate == null)) && Objects.equals(this.mNoItemsMessage, itemList.mNoItemsMessage)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    @NonNull
    public List<Item> getItems() {
        return CollectionUtils.emptyIfNull(this.mItems);
    }

    @Nullable
    public CarText getNoItemsMessage() {
        return this.mNoItemsMessage;
    }

    @Nullable
    public OnItemVisibilityChangedDelegate getOnItemVisibilityChangedDelegate() {
        return this.mOnItemVisibilityChangedDelegate;
    }

    @Nullable
    public OnSelectedDelegate getOnSelectedDelegate() {
        return this.mOnSelectedDelegate;
    }

    public int getSelectedIndex() {
        return this.mSelectedIndex;
    }

    public int hashCode() {
        Object[] objArr = new Object[5];
        objArr[0] = Integer.valueOf(this.mSelectedIndex);
        objArr[1] = this.mItems;
        objArr[2] = Boolean.valueOf(this.mOnSelectedDelegate == null);
        objArr[3] = Boolean.valueOf(this.mOnItemVisibilityChangedDelegate == null);
        objArr[4] = this.mNoItemsMessage;
        return Objects.hash(objArr);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ items: ");
        List<Item> list = this.mItems;
        sb.append(list != null ? list.toString() : null);
        sb.append(", selected: ");
        sb.append(this.mSelectedIndex);
        sb.append("]");
        return sb.toString();
    }
}
