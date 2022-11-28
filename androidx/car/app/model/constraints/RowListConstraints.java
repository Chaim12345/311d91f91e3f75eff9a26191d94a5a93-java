package androidx.car.app.model.constraints;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.model.Item;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Pane;
import androidx.car.app.model.Row;
import androidx.car.app.model.SectionedItemList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class RowListConstraints {
    @NonNull
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_CONSERVATIVE;
    @NonNull
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_FULL_LIST;
    @NonNull
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_PANE;
    @NonNull
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_ROUTE_PREVIEW;
    @NonNull
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_SIMPLE;
    private final boolean mAllowSelectableLists;
    private final int mMaxActions;
    private final RowConstraints mRowConstraints;

    /* loaded from: classes.dex */
    public static final class Builder {
        boolean mAllowSelectableLists;
        int mMaxActions;
        RowConstraints mRowConstraints;

        public Builder() {
            this.mRowConstraints = RowConstraints.UNCONSTRAINED;
        }

        public Builder(@NonNull RowListConstraints rowListConstraints) {
            this.mRowConstraints = RowConstraints.UNCONSTRAINED;
            Objects.requireNonNull(rowListConstraints);
            this.mMaxActions = rowListConstraints.getMaxActions();
            this.mRowConstraints = rowListConstraints.getRowConstraints();
            this.mAllowSelectableLists = rowListConstraints.isAllowSelectableLists();
        }

        @NonNull
        public RowListConstraints build() {
            return new RowListConstraints(this);
        }

        @NonNull
        public Builder setAllowSelectableLists(boolean z) {
            this.mAllowSelectableLists = z;
            return this;
        }

        @NonNull
        public Builder setMaxActions(int i2) {
            this.mMaxActions = i2;
            return this;
        }

        @NonNull
        public Builder setRowConstraints(@NonNull RowConstraints rowConstraints) {
            this.mRowConstraints = rowConstraints;
            return this;
        }
    }

    static {
        RowListConstraints build = new Builder().setMaxActions(0).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_CONSERVATIVE).setAllowSelectableLists(false).build();
        ROW_LIST_CONSTRAINTS_CONSERVATIVE = build;
        ROW_LIST_CONSTRAINTS_PANE = new Builder(build).setMaxActions(2).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_PANE).setAllowSelectableLists(false).build();
        Builder builder = new Builder(build);
        RowConstraints rowConstraints = RowConstraints.ROW_CONSTRAINTS_SIMPLE;
        ROW_LIST_CONSTRAINTS_SIMPLE = builder.setRowConstraints(rowConstraints).build();
        ROW_LIST_CONSTRAINTS_ROUTE_PREVIEW = new Builder(build).setRowConstraints(rowConstraints).setAllowSelectableLists(true).build();
        ROW_LIST_CONSTRAINTS_FULL_LIST = new Builder(build).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_FULL_LIST).setAllowSelectableLists(true).build();
    }

    RowListConstraints(Builder builder) {
        this.mMaxActions = builder.mMaxActions;
        this.mRowConstraints = builder.mRowConstraints;
        this.mAllowSelectableLists = builder.mAllowSelectableLists;
    }

    private void validateRows(List<? extends Item> list) {
        for (Item item : list) {
            if (!(item instanceof Row)) {
                throw new IllegalArgumentException("Only Row instances are supported in the list");
            }
            this.mRowConstraints.validateOrThrow((Row) item);
        }
    }

    public int getMaxActions() {
        return this.mMaxActions;
    }

    @NonNull
    public RowConstraints getRowConstraints() {
        return this.mRowConstraints;
    }

    public boolean isAllowSelectableLists() {
        return this.mAllowSelectableLists;
    }

    public void validateOrThrow(@NonNull ItemList itemList) {
        if (itemList.getOnSelectedDelegate() != null && !this.mAllowSelectableLists) {
            throw new IllegalArgumentException("Selectable lists are not allowed");
        }
        validateRows(itemList.getItems());
    }

    public void validateOrThrow(@NonNull Pane pane) {
        if (pane.getActions().size() <= this.mMaxActions) {
            validateRows(pane.getRows());
            return;
        }
        throw new IllegalArgumentException("The number of actions on the pane exceeded the supported max of " + this.mMaxActions);
    }

    public void validateOrThrow(@NonNull List<SectionedItemList> list) {
        ArrayList arrayList = new ArrayList();
        for (SectionedItemList sectionedItemList : list) {
            ItemList itemList = sectionedItemList.getItemList();
            if (itemList.getOnSelectedDelegate() != null && !this.mAllowSelectableLists) {
                throw new IllegalArgumentException("Selectable lists are not allowed");
            }
            arrayList.addAll(itemList.getItems());
        }
        validateRows(arrayList);
    }
}
