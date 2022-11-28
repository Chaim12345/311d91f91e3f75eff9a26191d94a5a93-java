package androidx.car.app.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Pane {
    @Keep
    private final List<Action> mActionList;
    @Keep
    private final boolean mIsLoading;
    @Keep
    private final List<Row> mRows;

    /* loaded from: classes.dex */
    public static final class Builder {
        boolean mIsLoading;
        final List<Row> mRows = new ArrayList();
        List<Action> mActionList = new ArrayList();

        private int size() {
            return this.mRows.size();
        }

        @NonNull
        public Builder addAction(@NonNull Action action) {
            Objects.requireNonNull(action);
            this.mActionList.add(action);
            return this;
        }

        @NonNull
        public Builder addRow(@NonNull Row row) {
            List<Row> list = this.mRows;
            Objects.requireNonNull(row);
            list.add(row);
            return this;
        }

        @NonNull
        public Pane build() {
            if ((size() > 0) != this.mIsLoading) {
                return new Pane(this);
            }
            throw new IllegalStateException("The pane is set to loading but is not empty, or vice versa");
        }

        @NonNull
        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }
    }

    private Pane() {
        this.mRows = Collections.emptyList();
        this.mActionList = Collections.emptyList();
        this.mIsLoading = false;
    }

    Pane(Builder builder) {
        this.mRows = CollectionUtils.unmodifiableCopy(builder.mRows);
        this.mActionList = CollectionUtils.unmodifiableCopy(builder.mActionList);
        this.mIsLoading = builder.mIsLoading;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Pane) {
            Pane pane = (Pane) obj;
            return this.mIsLoading == pane.mIsLoading && Objects.equals(this.mActionList, pane.mActionList) && Objects.equals(this.mRows, pane.mRows);
        }
        return false;
    }

    @NonNull
    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActionList);
    }

    @NonNull
    public List<Row> getRows() {
        return CollectionUtils.emptyIfNull(this.mRows);
    }

    public int hashCode() {
        return Objects.hash(this.mRows, this.mActionList, Boolean.valueOf(this.mIsLoading));
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ rows: ");
        List<Row> list = this.mRows;
        sb.append(list != null ? list.toString() : null);
        sb.append(", action list: ");
        sb.append(this.mActionList);
        sb.append("]");
        return sb.toString();
    }
}
