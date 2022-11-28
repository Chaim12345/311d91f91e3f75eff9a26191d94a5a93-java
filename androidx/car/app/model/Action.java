package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.CameraInfo;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.CarColorConstraints;
import androidx.car.app.model.constraints.CarIconConstraints;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Action {
    static final int FLAG_STANDARD = 65536;
    public static final int TYPE_CUSTOM = 1;
    public static final int TYPE_PAN = 65540;
    @Keep
    private final CarColor mBackgroundColor;
    @Nullable
    @Keep
    private final CarIcon mIcon;
    @Nullable
    @Keep
    private final OnClickDelegate mOnClickDelegate;
    @Nullable
    @Keep
    private final CarText mTitle;
    @Keep
    private final int mType;
    public static final int TYPE_APP_ICON = 65538;
    @NonNull
    public static final Action APP_ICON = new Action((int) TYPE_APP_ICON);
    public static final int TYPE_BACK = 65539;
    @NonNull
    public static final Action BACK = new Action((int) TYPE_BACK);
    @NonNull
    public static final Action PAN = new Action(65540);

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface ActionType {
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        CarColor mBackgroundColor;
        @Nullable
        CarIcon mIcon;
        @Nullable
        OnClickDelegate mOnClickDelegate;
        @Nullable
        CarText mTitle;
        int mType;

        public Builder() {
            this.mBackgroundColor = CarColor.DEFAULT;
            this.mType = 1;
        }

        @RequiresCarApi(2)
        public Builder(@NonNull Action action) {
            CarColor carColor = CarColor.DEFAULT;
            this.mBackgroundColor = carColor;
            this.mType = 1;
            Objects.requireNonNull(action);
            this.mType = action.getType();
            this.mIcon = action.getIcon();
            this.mTitle = action.getTitle();
            this.mOnClickDelegate = action.getOnClickDelegate();
            CarColor backgroundColor = action.getBackgroundColor();
            this.mBackgroundColor = backgroundColor != null ? backgroundColor : carColor;
        }

        @NonNull
        public Action build() {
            CarText carText;
            CarText carText2;
            if (!Action.isStandardActionType(this.mType) && this.mIcon == null && ((carText2 = this.mTitle) == null || TextUtils.isEmpty(carText2.toString()))) {
                throw new IllegalStateException("An action must have either an icon or a title");
            }
            int i2 = this.mType;
            if (i2 == 65538 || i2 == 65539) {
                if (this.mOnClickDelegate != null) {
                    throw new IllegalStateException("An on-click listener can't be set on the standard back or app-icon action");
                }
                if (this.mIcon != null || ((carText = this.mTitle) != null && !TextUtils.isEmpty(carText.toString()))) {
                    throw new IllegalStateException("An icon or title can't be set on the standard back or app-icon action");
                }
            }
            if (this.mType != 65540 || this.mOnClickDelegate == null) {
                return new Action(this);
            }
            throw new IllegalStateException("An on-click listener can't be set on the pan mode action");
        }

        @NonNull
        public Builder setBackgroundColor(@NonNull CarColor carColor) {
            CarColorConstraints carColorConstraints = CarColorConstraints.UNCONSTRAINED;
            Objects.requireNonNull(carColor);
            carColorConstraints.validateOrThrow(carColor);
            this.mBackgroundColor = carColor;
            return this;
        }

        @NonNull
        public Builder setIcon(@NonNull CarIcon carIcon) {
            CarIconConstraints carIconConstraints = CarIconConstraints.DEFAULT;
            Objects.requireNonNull(carIcon);
            carIconConstraints.validateOrThrow(carIcon);
            this.mIcon = carIcon;
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

    private Action() {
        this.mTitle = null;
        this.mIcon = null;
        this.mBackgroundColor = CarColor.DEFAULT;
        this.mOnClickDelegate = null;
        this.mType = 1;
    }

    private Action(int i2) {
        if (i2 == 1) {
            throw new IllegalArgumentException("Standard action constructor used with non standard type");
        }
        this.mTitle = null;
        this.mIcon = null;
        this.mBackgroundColor = CarColor.DEFAULT;
        this.mOnClickDelegate = null;
        this.mType = i2;
    }

    Action(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mIcon = builder.mIcon;
        this.mBackgroundColor = builder.mBackgroundColor;
        this.mOnClickDelegate = builder.mOnClickDelegate;
        this.mType = builder.mType;
    }

    static boolean isStandardActionType(int i2) {
        return (i2 & 65536) != 0;
    }

    @NonNull
    public static String typeToString(int i2) {
        if (i2 != 1) {
            switch (i2) {
                case TYPE_APP_ICON /* 65538 */:
                    return "APP_ICON";
                case TYPE_BACK /* 65539 */:
                    return "BACK";
                case 65540:
                    return "PAN";
                default:
                    return CameraInfo.IMPLEMENTATION_TYPE_UNKNOWN;
            }
        }
        return "CUSTOM";
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Action) {
            Action action = (Action) obj;
            if (Objects.equals(this.mTitle, action.mTitle) && this.mType == action.mType && Objects.equals(this.mIcon, action.mIcon)) {
                if (Objects.equals(Boolean.valueOf(this.mOnClickDelegate == null), Boolean.valueOf(action.mOnClickDelegate == null))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Nullable
    public CarColor getBackgroundColor() {
        return this.mBackgroundColor;
    }

    @Nullable
    public CarIcon getIcon() {
        return this.mIcon;
    }

    @Nullable
    public OnClickDelegate getOnClickDelegate() {
        return this.mOnClickDelegate;
    }

    @Nullable
    public CarText getTitle() {
        return this.mTitle;
    }

    public int getType() {
        return this.mType;
    }

    public int hashCode() {
        Object[] objArr = new Object[4];
        objArr[0] = this.mTitle;
        objArr[1] = Integer.valueOf(this.mType);
        objArr[2] = Boolean.valueOf(this.mOnClickDelegate == null);
        objArr[3] = Boolean.valueOf(this.mIcon == null);
        return Objects.hash(objArr);
    }

    public boolean isStandard() {
        return isStandardActionType(this.mType);
    }

    @NonNull
    public String toString() {
        return "[type: " + typeToString(this.mType) + ", icon: " + this.mIcon + ", bkg: " + this.mBackgroundColor + "]";
    }
}
