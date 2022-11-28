package androidx.car.app.model.signin;

import android.annotation.SuppressLint;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.CarText;
import androidx.car.app.model.InputCallback;
import androidx.car.app.model.InputCallbackDelegate;
import androidx.car.app.model.InputCallbackDelegateImpl;
import androidx.car.app.model.signin.SignInTemplate;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
@RequiresCarApi(2)
/* loaded from: classes.dex */
public final class InputSignInMethod implements SignInTemplate.SignInMethod {
    public static final int INPUT_TYPE_DEFAULT = 1;
    public static final int INPUT_TYPE_PASSWORD = 2;
    public static final int KEYBOARD_DEFAULT = 1;
    public static final int KEYBOARD_EMAIL = 2;
    public static final int KEYBOARD_NUMBER = 4;
    public static final int KEYBOARD_PHONE = 3;
    @Nullable
    @Keep
    private final CarText mDefaultValue;
    @Nullable
    @Keep
    private final CarText mErrorMessage;
    @Nullable
    @Keep
    private final CarText mHint;
    @Nullable
    private final InputCallbackDelegate mInputCallbackDelegate;
    @Keep
    private final int mInputType;
    @Keep
    private final int mKeyboardType;
    @Keep
    private final boolean mShowKeyboardByDefault;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable
        CarText mDefaultValue;
        @Nullable
        CarText mErrorMessage;
        @Nullable
        CarText mHint;
        @Nullable
        final InputCallbackDelegate mInputCallbackDelegate;
        int mInputType = 1;
        int mKeyboardType = 1;
        boolean mShowKeyboardByDefault;

        @SuppressLint({"ExecutorRegistration"})
        public Builder(@NonNull InputCallback inputCallback) {
            Objects.requireNonNull(inputCallback);
            this.mInputCallbackDelegate = InputCallbackDelegateImpl.create(inputCallback);
        }

        private static int validateInputType(int i2) {
            if (i2 == 1 || i2 == 2) {
                return i2;
            }
            throw new IllegalArgumentException("Invalid input type: " + i2);
        }

        private static int validateKeyboardType(int i2) {
            if (i2 == 1 || i2 == 2 || i2 == 4 || i2 == 3) {
                return i2;
            }
            throw new IllegalArgumentException("Keyboard type is not supported: " + i2);
        }

        @NonNull
        public InputSignInMethod build() {
            return new InputSignInMethod(this);
        }

        @NonNull
        public Builder setDefaultValue(@NonNull String str) {
            Objects.requireNonNull(str);
            this.mDefaultValue = CarText.create(str);
            return this;
        }

        @NonNull
        public Builder setErrorMessage(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mErrorMessage = CarText.create(charSequence);
            return this;
        }

        @NonNull
        public Builder setHint(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mHint = CarText.create(charSequence);
            return this;
        }

        @NonNull
        public Builder setInputType(int i2) {
            this.mInputType = validateInputType(i2);
            return this;
        }

        @NonNull
        public Builder setKeyboardType(int i2) {
            this.mKeyboardType = validateKeyboardType(i2);
            return this;
        }

        @NonNull
        public Builder setShowKeyboardByDefault(boolean z) {
            this.mShowKeyboardByDefault = z;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface InputType {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface KeyboardType {
    }

    private InputSignInMethod() {
        this.mHint = null;
        this.mDefaultValue = null;
        this.mInputType = 1;
        this.mErrorMessage = null;
        this.mKeyboardType = 1;
        this.mInputCallbackDelegate = null;
        this.mShowKeyboardByDefault = false;
    }

    InputSignInMethod(Builder builder) {
        this.mHint = builder.mHint;
        this.mDefaultValue = builder.mDefaultValue;
        this.mInputType = builder.mInputType;
        this.mErrorMessage = builder.mErrorMessage;
        this.mKeyboardType = builder.mKeyboardType;
        this.mInputCallbackDelegate = builder.mInputCallbackDelegate;
        this.mShowKeyboardByDefault = builder.mShowKeyboardByDefault;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InputSignInMethod) {
            InputSignInMethod inputSignInMethod = (InputSignInMethod) obj;
            return this.mInputType == inputSignInMethod.mInputType && this.mKeyboardType == inputSignInMethod.mKeyboardType && this.mShowKeyboardByDefault == inputSignInMethod.mShowKeyboardByDefault && Objects.equals(this.mHint, inputSignInMethod.mHint) && Objects.equals(this.mDefaultValue, inputSignInMethod.mDefaultValue) && Objects.equals(this.mErrorMessage, inputSignInMethod.mErrorMessage);
        }
        return false;
    }

    @Nullable
    public CarText getDefaultValue() {
        return this.mDefaultValue;
    }

    @Nullable
    public CarText getErrorMessage() {
        return this.mErrorMessage;
    }

    @Nullable
    public CarText getHint() {
        return this.mHint;
    }

    @NonNull
    public InputCallbackDelegate getInputCallbackDelegate() {
        InputCallbackDelegate inputCallbackDelegate = this.mInputCallbackDelegate;
        Objects.requireNonNull(inputCallbackDelegate);
        return inputCallbackDelegate;
    }

    public int getInputType() {
        return this.mInputType;
    }

    public int getKeyboardType() {
        return this.mKeyboardType;
    }

    public int hashCode() {
        return Objects.hash(this.mHint, this.mDefaultValue, Integer.valueOf(this.mInputType), this.mErrorMessage, Integer.valueOf(this.mKeyboardType), Boolean.valueOf(this.mShowKeyboardByDefault));
    }

    public boolean isShowKeyboardByDefault() {
        return this.mShowKeyboardByDefault;
    }

    @NonNull
    public String toString() {
        return "[inputType:" + this.mInputType + ", keyboardType: " + this.mKeyboardType + "]";
    }
}
