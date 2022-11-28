package androidx.car.app.navigation.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.navigation.model.NavigationTemplate;
import java.util.Objects;
/* loaded from: classes.dex */
public final class MessageInfo implements NavigationTemplate.NavigationInfo {
    @Nullable
    @Keep
    private final CarIcon mImage;
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
        @Nullable
        CarText mText;
        @Nullable
        CarText mTitle;

        public Builder(@NonNull CarText carText) {
            Objects.requireNonNull(carText);
            this.mTitle = carText;
        }

        public Builder(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mTitle = CarText.create(charSequence);
        }

        @NonNull
        public MessageInfo build() {
            return new MessageInfo(this);
        }

        @NonNull
        public Builder setImage(@NonNull CarIcon carIcon) {
            CarIconConstraints carIconConstraints = CarIconConstraints.DEFAULT;
            Objects.requireNonNull(carIcon);
            carIconConstraints.validateOrThrow(carIcon);
            this.mImage = carIcon;
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
        public Builder setTitle(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mTitle = CarText.create(charSequence);
            return this;
        }
    }

    private MessageInfo() {
        this.mTitle = null;
        this.mText = null;
        this.mImage = null;
    }

    MessageInfo(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mText = builder.mText;
        this.mImage = builder.mImage;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MessageInfo) {
            MessageInfo messageInfo = (MessageInfo) obj;
            return Objects.equals(this.mTitle, messageInfo.mTitle) && Objects.equals(this.mText, messageInfo.mText) && Objects.equals(this.mImage, messageInfo.mImage);
        }
        return false;
    }

    @Nullable
    public CarIcon getImage() {
        return this.mImage;
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
        return Objects.hash(this.mTitle, this.mText, this.mImage);
    }

    @NonNull
    public String toString() {
        return "MessageInfo";
    }
}
