package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes.dex */
public final class SectionedItemList {
    @Nullable
    @Keep
    private final CarText mHeader;
    @Nullable
    @Keep
    private final ItemList mItemList;

    private SectionedItemList() {
        this.mItemList = null;
        this.mHeader = null;
    }

    private SectionedItemList(@Nullable ItemList itemList, @Nullable CarText carText) {
        this.mItemList = itemList;
        this.mHeader = carText;
    }

    @NonNull
    public static SectionedItemList create(@NonNull ItemList itemList, @NonNull CharSequence charSequence) {
        Objects.requireNonNull(itemList);
        Objects.requireNonNull(charSequence);
        return new SectionedItemList(itemList, CarText.create(charSequence));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SectionedItemList) {
            SectionedItemList sectionedItemList = (SectionedItemList) obj;
            return Objects.equals(this.mItemList, sectionedItemList.mItemList) && Objects.equals(this.mHeader, sectionedItemList.mHeader);
        }
        return false;
    }

    @NonNull
    public CarText getHeader() {
        CarText carText = this.mHeader;
        Objects.requireNonNull(carText);
        return carText;
    }

    @NonNull
    public ItemList getItemList() {
        ItemList itemList = this.mItemList;
        Objects.requireNonNull(itemList);
        return itemList;
    }

    public int hashCode() {
        return Objects.hash(this.mItemList, this.mHeader);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ items: ");
        sb.append(this.mItemList);
        sb.append(", has header: ");
        sb.append(this.mHeader != null);
        sb.append("]");
        return sb.toString();
    }
}
