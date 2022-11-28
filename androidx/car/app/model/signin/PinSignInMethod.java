package androidx.car.app.model.signin;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.CarText;
import androidx.car.app.model.signin.SignInTemplate;
import java.util.Objects;
@RequiresCarApi(2)
/* loaded from: classes.dex */
public final class PinSignInMethod implements SignInTemplate.SignInMethod {
    private static final int MAX_PIN_LENGTH = 12;
    @Nullable
    @Keep
    private final String mPin;
    @Nullable
    @Keep
    private final CarText mPinCode;

    private PinSignInMethod() {
        this.mPinCode = null;
        this.mPin = null;
    }

    public PinSignInMethod(@NonNull CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        int length = charSequence.length();
        if (length == 0) {
            throw new IllegalArgumentException("PIN must not be empty");
        }
        if (length > 12) {
            throw new IllegalArgumentException("PIN must not be longer than 12 characters");
        }
        CarText create = CarText.create(charSequence);
        this.mPinCode = create;
        this.mPin = create.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PinSignInMethod) {
            return Objects.equals(this.mPinCode, ((PinSignInMethod) obj).mPinCode);
        }
        return false;
    }

    @NonNull
    public CarText getPinCode() {
        CarText carText = this.mPinCode;
        if (carText != null) {
            return carText;
        }
        String str = this.mPin;
        Objects.requireNonNull(str);
        return CarText.create(str);
    }

    public int hashCode() {
        return Objects.hash(this.mPinCode);
    }
}
