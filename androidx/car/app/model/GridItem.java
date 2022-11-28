package androidx.car.app.model;

import android.annotation.SuppressLint;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.model.constraints.CarIconConstraints;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes.dex */
public final class GridItem implements Item {
    public static final int IMAGE_TYPE_ICON = 1;
    public static final int IMAGE_TYPE_LARGE = 2;
    @Nullable
    @Keep
    private final CarIcon mImage;
    @Keep
    private final int mImageType;
    @Keep
    private final boolean mIsLoading;
    @Nullable
    @Keep
    private final OnClickDelegate mOnClickDelegate;
    @Nullable
    @Keep
    private final CarText mText;
    @Nullable
    @Keep
    private final CarText mTitle;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        CarIcon mImage;
        int mImageType = 2;
        boolean mIsLoading;
        @Nullable
        OnClickDelegate mOnClickDelegate;
        @Nullable
        CarText mText;
        @Nullable
        CarText mTitle;

        @NonNull
        public GridItem build() {
            if (this.mTitle != null) {
                boolean z = this.mIsLoading;
                if (z != (this.mImage != null)) {
                    if (!z || this.mOnClickDelegate == null) {
                        return new GridItem(this);
                    }
                    throw new IllegalStateException("The click listener must not be set on the grid item when it is loading");
                }
                throw new IllegalStateException("When a grid item is loading, the image must not be set and vice versa");
            }
            throw new IllegalStateException("A title must be set on the grid item");
        }

        @NonNull
        public Builder setImage(@NonNull CarIcon carIcon) {
            Objects.requireNonNull(carIcon);
            return setImage(carIcon, 2);
        }

        @NonNull
        public Builder setImage(@NonNull CarIcon carIcon, int i2) {
            CarIconConstraints carIconConstraints = CarIconConstraints.UNCONSTRAINED;
            Objects.requireNonNull(carIcon);
            carIconConstraints.validateOrThrow(carIcon);
            this.mImage = carIcon;
            this.mImageType = i2;
            return this;
        }

        @NonNull
        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder", "ExecutorRegistration"})
        public Builder setOnClickListener(@NonNull OnClickListener onClickListener) {
            this.mOnClickDelegate = OnClickDelegateImpl.create(onClickListener);
            return this;
        }

        @NonNull
        public Builder setText(@NonNull CarText carText) {
            Objects.requireNonNull(carText);
            this.mText = carText;
            return this;
        }

        @NonNull
        public Builder setText(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mText = CarText.create(charSequence);
            return this;
        }

        @NonNull
        public Builder setTitle(@NonNull CarText carText) {
            if (CarText.isNullOrEmpty(carText)) {
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
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface GridItemImageType {
    }

    private GridItem() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mText = null;
        this.mImage = null;
        this.mImageType = 2;
        this.mOnClickDelegate = null;
    }

    GridItem(Builder builder) {
        this.mIsLoading = builder.mIsLoading;
        this.mTitle = builder.mTitle;
        this.mText = builder.mText;
        this.mImage = builder.mImage;
        this.mImageType = builder.mImageType;
        this.mOnClickDelegate = builder.mOnClickDelegate;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GridItem) {
            GridItem gridItem = (GridItem) obj;
            if (this.mIsLoading == gridItem.mIsLoading && Objects.equals(this.mTitle, gridItem.mTitle) && Objects.equals(this.mText, gridItem.mText) && Objects.equals(this.mImage, gridItem.mImage)) {
                if (Objects.equals(Boolean.valueOf(this.mOnClickDelegate == null), Boolean.valueOf(gridItem.mOnClickDelegate == null)) && this.mImageType == gridItem.mImageType) {
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

    public int getImageType() {
        return this.mImageType;
    }

    @Nullable
    public OnClickDelegate getOnClickDelegate() {
        return this.mOnClickDelegate;
    }

    @Nullable
    public CarText getText() {
        return this.mText;
    }

    @Nullable
    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        Object[] objArr = new Object[5];
        objArr[0] = Boolean.valueOf(this.mIsLoading);
        objArr[1] = this.mTitle;
        objArr[2] = this.mImage;
        objArr[3] = Integer.valueOf(this.mImageType);
        objArr[4] = Boolean.valueOf(this.mOnClickDelegate == null);
        return Objects.hash(objArr);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @NonNull
    public String toString() {
        return "[title: " + CarText.toShortString(this.mTitle) + ", text: " + CarText.toShortString(this.mText) + ", image: " + this.mImage + ", isLoading: " + this.mIsLoading + "]";
    }
}
