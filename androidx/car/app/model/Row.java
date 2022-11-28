package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.utils.CollectionUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Row implements Item {
    public static final int IMAGE_TYPE_ICON = 4;
    public static final int IMAGE_TYPE_LARGE = 2;
    public static final int IMAGE_TYPE_SMALL = 1;
    private static final String YOUR_BOAT = "🚣";
    @Nullable
    @Keep
    private final CarIcon mImage;
    @Keep
    private final boolean mIsBrowsable;
    @Keep
    private final Metadata mMetadata;
    @Nullable
    @Keep
    private final OnClickDelegate mOnClickDelegate;
    @Keep
    private final int mRowImageType;
    @Keep
    private final List<CarText> mTexts;
    @Nullable
    @Keep
    private final CarText mTitle;
    @Nullable
    @Keep
    private final Toggle mToggle;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        CarIcon mImage;
        boolean mIsBrowsable;
        @Nullable
        OnClickDelegate mOnClickDelegate;
        @Nullable
        CarText mTitle;
        @Nullable
        Toggle mToggle;
        final List<CarText> mTexts = new ArrayList();
        Metadata mMetadata = Metadata.EMPTY_METADATA;
        int mRowImageType = 1;

        @NonNull
        public Builder addText(@NonNull CarText carText) {
            List<CarText> list = this.mTexts;
            Objects.requireNonNull(carText);
            list.add(carText);
            return this;
        }

        @NonNull
        public Builder addText(@NonNull CharSequence charSequence) {
            List<CarText> list = this.mTexts;
            Objects.requireNonNull(charSequence);
            list.add(CarText.create(charSequence));
            return this;
        }

        @NonNull
        public Row build() {
            if (this.mTitle != null) {
                if (this.mIsBrowsable) {
                    if (this.mToggle != null) {
                        throw new IllegalStateException("A browsable row must not have a toggle set");
                    }
                    if (this.mOnClickDelegate == null) {
                        throw new IllegalStateException("A browsable row must have its onClickListener set");
                    }
                }
                if (this.mToggle == null || this.mOnClickDelegate == null) {
                    return new Row(this);
                }
                throw new IllegalStateException("If a row contains a toggle, it must not have a onClickListener set");
            }
            throw new IllegalStateException("A title must be set on the row");
        }

        @NonNull
        public Builder setBrowsable(boolean z) {
            this.mIsBrowsable = z;
            return this;
        }

        @NonNull
        public Builder setImage(@NonNull CarIcon carIcon) {
            Objects.requireNonNull(carIcon);
            return setImage(carIcon, 1);
        }

        @NonNull
        public Builder setImage(@NonNull CarIcon carIcon, int i2) {
            CarIconConstraints carIconConstraints = CarIconConstraints.UNCONSTRAINED;
            Objects.requireNonNull(carIcon);
            carIconConstraints.validateOrThrow(carIcon);
            this.mImage = carIcon;
            this.mRowImageType = i2;
            return this;
        }

        @NonNull
        public Builder setMetadata(@NonNull Metadata metadata) {
            this.mMetadata = metadata;
            return this;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder", "ExecutorRegistration"})
        public Builder setOnClickListener(@NonNull OnClickListener onClickListener) {
            this.mOnClickDelegate = OnClickDelegateImpl.create(onClickListener);
            return this;
        }

        @NonNull
        public Builder setTitle(@NonNull CarText carText) {
            Objects.requireNonNull(carText);
            if (carText.isEmpty()) {
                throw new IllegalArgumentException("The title cannot be null or empty");
            }
            this.mTitle = carText;
            return this;
        }

        @NonNull
        public Builder setTitle(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            CarText create = CarText.create(charSequence);
            if (create.isEmpty()) {
                throw new IllegalArgumentException("The title cannot be null or empty");
            }
            this.mTitle = create;
            return this;
        }

        @NonNull
        public Builder setToggle(@NonNull Toggle toggle) {
            Objects.requireNonNull(toggle);
            this.mToggle = toggle;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface RowImageType {
    }

    private Row() {
        this.mTitle = null;
        this.mTexts = Collections.emptyList();
        this.mImage = null;
        this.mToggle = null;
        this.mOnClickDelegate = null;
        this.mMetadata = Metadata.EMPTY_METADATA;
        this.mIsBrowsable = false;
        this.mRowImageType = 1;
    }

    Row(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mTexts = CollectionUtils.unmodifiableCopy(builder.mTexts);
        this.mImage = builder.mImage;
        this.mToggle = builder.mToggle;
        this.mOnClickDelegate = builder.mOnClickDelegate;
        this.mMetadata = builder.mMetadata;
        this.mIsBrowsable = builder.mIsBrowsable;
        this.mRowImageType = builder.mRowImageType;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Row) {
            Row row = (Row) obj;
            if (Objects.equals(this.mTitle, row.mTitle) && Objects.equals(this.mTexts, row.mTexts) && Objects.equals(this.mImage, row.mImage) && Objects.equals(this.mToggle, row.mToggle)) {
                if (Objects.equals(Boolean.valueOf(this.mOnClickDelegate == null), Boolean.valueOf(row.mOnClickDelegate == null)) && Objects.equals(this.mMetadata, row.mMetadata) && this.mIsBrowsable == row.mIsBrowsable && this.mRowImageType == row.mRowImageType) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Nullable
    public CarIcon getImage() {
        return this.mImage;
    }

    @Nullable
    public Metadata getMetadata() {
        return this.mMetadata;
    }

    @Nullable
    public OnClickDelegate getOnClickDelegate() {
        return this.mOnClickDelegate;
    }

    public int getRowImageType() {
        return this.mRowImageType;
    }

    @NonNull
    public List<CarText> getTexts() {
        return CollectionUtils.emptyIfNull(this.mTexts);
    }

    @Nullable
    public CarText getTitle() {
        return this.mTitle;
    }

    @Nullable
    public Toggle getToggle() {
        return this.mToggle;
    }

    public int hashCode() {
        Object[] objArr = new Object[8];
        objArr[0] = this.mTitle;
        objArr[1] = this.mTexts;
        objArr[2] = this.mImage;
        objArr[3] = this.mToggle;
        objArr[4] = Boolean.valueOf(this.mOnClickDelegate == null);
        objArr[5] = this.mMetadata;
        objArr[6] = Boolean.valueOf(this.mIsBrowsable);
        objArr[7] = Integer.valueOf(this.mRowImageType);
        return Objects.hash(objArr);
    }

    public boolean isBrowsable() {
        return this.mIsBrowsable;
    }

    @NonNull
    public Row row() {
        return this;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[title: ");
        sb.append(CarText.toShortString(this.mTitle));
        sb.append(", text count: ");
        List<CarText> list = this.mTexts;
        sb.append(list != null ? list.size() : 0);
        sb.append(", image: ");
        sb.append(this.mImage);
        sb.append(", isBrowsable: ");
        sb.append(this.mIsBrowsable);
        sb.append("]");
        return sb.toString();
    }

    @NonNull
    public CharSequence yourBoat() {
        return YOUR_BOAT;
    }
}
