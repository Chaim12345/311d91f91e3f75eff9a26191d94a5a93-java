package androidx.car.app.model.constraints;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.Row;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class RowConstraints {
    @NonNull
    public static final RowConstraints ROW_CONSTRAINTS_FULL_LIST;
    @NonNull
    public static final RowConstraints ROW_CONSTRAINTS_SIMPLE;
    private final CarIconConstraints mCarIconConstraints;
    private final boolean mIsImageAllowed;
    private final boolean mIsOnClickListenerAllowed;
    private final boolean mIsToggleAllowed;
    private final int mMaxActionsExclusive;
    private final int mMaxTextLinesPerRow;
    @NonNull
    public static final RowConstraints UNCONSTRAINED = new Builder().build();
    @NonNull
    public static final RowConstraints ROW_CONSTRAINTS_CONSERVATIVE = new Builder().setMaxActionsExclusive(0).setImageAllowed(false).setMaxTextLinesPerRow(1).setOnClickListenerAllowed(true).setToggleAllowed(false).build();
    @NonNull
    public static final RowConstraints ROW_CONSTRAINTS_PANE = new Builder().setMaxActionsExclusive(2).setImageAllowed(true).setMaxTextLinesPerRow(2).setToggleAllowed(false).setOnClickListenerAllowed(false).build();

    /* loaded from: classes.dex */
    public static final class Builder {
        CarIconConstraints mCarIconConstraints;
        boolean mIsImageAllowed;
        boolean mIsOnClickListenerAllowed;
        boolean mIsToggleAllowed;
        int mMaxActionsExclusive;
        int mMaxTextLines;

        public Builder() {
            this.mIsOnClickListenerAllowed = true;
            this.mIsToggleAllowed = true;
            this.mMaxTextLines = Integer.MAX_VALUE;
            this.mMaxActionsExclusive = Integer.MAX_VALUE;
            this.mIsImageAllowed = true;
            this.mCarIconConstraints = CarIconConstraints.UNCONSTRAINED;
        }

        public Builder(@NonNull RowConstraints rowConstraints) {
            this.mIsOnClickListenerAllowed = true;
            this.mIsToggleAllowed = true;
            this.mMaxTextLines = Integer.MAX_VALUE;
            this.mMaxActionsExclusive = Integer.MAX_VALUE;
            this.mIsImageAllowed = true;
            this.mCarIconConstraints = CarIconConstraints.UNCONSTRAINED;
            Objects.requireNonNull(rowConstraints);
            this.mIsOnClickListenerAllowed = rowConstraints.isOnClickListenerAllowed();
            this.mMaxTextLines = rowConstraints.getMaxTextLinesPerRow();
            this.mMaxActionsExclusive = rowConstraints.getMaxActionsExclusive();
            this.mIsToggleAllowed = rowConstraints.isToggleAllowed();
            this.mIsImageAllowed = rowConstraints.isImageAllowed();
            this.mCarIconConstraints = rowConstraints.getCarIconConstraints();
        }

        @NonNull
        public RowConstraints build() {
            return new RowConstraints(this);
        }

        @NonNull
        public Builder setCarIconConstraints(@NonNull CarIconConstraints carIconConstraints) {
            this.mCarIconConstraints = carIconConstraints;
            return this;
        }

        @NonNull
        public Builder setImageAllowed(boolean z) {
            this.mIsImageAllowed = z;
            return this;
        }

        @NonNull
        public Builder setMaxActionsExclusive(int i2) {
            this.mMaxActionsExclusive = i2;
            return this;
        }

        @NonNull
        public Builder setMaxTextLinesPerRow(int i2) {
            this.mMaxTextLines = i2;
            return this;
        }

        @NonNull
        public Builder setOnClickListenerAllowed(boolean z) {
            this.mIsOnClickListenerAllowed = z;
            return this;
        }

        @NonNull
        public Builder setToggleAllowed(boolean z) {
            this.mIsToggleAllowed = z;
            return this;
        }
    }

    static {
        RowConstraints build = new Builder().setMaxActionsExclusive(0).setImageAllowed(true).setMaxTextLinesPerRow(2).setToggleAllowed(false).setOnClickListenerAllowed(true).build();
        ROW_CONSTRAINTS_SIMPLE = build;
        ROW_CONSTRAINTS_FULL_LIST = new Builder(build).setToggleAllowed(true).build();
    }

    RowConstraints(Builder builder) {
        this.mIsOnClickListenerAllowed = builder.mIsOnClickListenerAllowed;
        this.mMaxTextLinesPerRow = builder.mMaxTextLines;
        this.mMaxActionsExclusive = builder.mMaxActionsExclusive;
        this.mIsToggleAllowed = builder.mIsToggleAllowed;
        this.mIsImageAllowed = builder.mIsImageAllowed;
        this.mCarIconConstraints = builder.mCarIconConstraints;
    }

    @NonNull
    public CarIconConstraints getCarIconConstraints() {
        return this.mCarIconConstraints;
    }

    public int getMaxActionsExclusive() {
        return this.mMaxActionsExclusive;
    }

    public int getMaxTextLinesPerRow() {
        return this.mMaxTextLinesPerRow;
    }

    public boolean isImageAllowed() {
        return this.mIsImageAllowed;
    }

    public boolean isOnClickListenerAllowed() {
        return this.mIsOnClickListenerAllowed;
    }

    public boolean isToggleAllowed() {
        return this.mIsToggleAllowed;
    }

    public void validateOrThrow(@NonNull Row row) {
        if (!this.mIsOnClickListenerAllowed && row.getOnClickDelegate() != null) {
            throw new IllegalArgumentException("A click listener is not allowed on the row");
        }
        if (!this.mIsToggleAllowed && row.getToggle() != null) {
            throw new IllegalArgumentException("A toggle is not allowed on the row");
        }
        CarIcon image = row.getImage();
        if (image != null) {
            if (!this.mIsImageAllowed) {
                throw new IllegalArgumentException("An image is not allowed on the row");
            }
            this.mCarIconConstraints.validateOrThrow(image);
        }
        if (row.getTexts().size() <= this.mMaxTextLinesPerRow) {
            return;
        }
        throw new IllegalArgumentException("The number of lines of texts for the row exceeded the supported max of " + this.mMaxTextLinesPerRow);
    }
}
