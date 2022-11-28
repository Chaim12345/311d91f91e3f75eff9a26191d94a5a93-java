package androidx.car.app.model;

import android.text.SpannableString;
import android.text.Spanned;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.utils.CollectionUtils;
import androidx.car.app.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CarText {
    @Keep
    private final List<SpanWrapper> mSpans;
    @Keep
    private final List<List<SpanWrapper>> mSpansForVariants;
    @Keep
    private final String mText;
    @Keep
    private final List<String> mTextVariants;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Keep
        CharSequence mText;
        @Keep
        List<CharSequence> mTextVariants = new ArrayList();

        public Builder(@NonNull CharSequence charSequence) {
            Objects.requireNonNull(charSequence);
            this.mText = charSequence;
        }

        @NonNull
        @RequiresCarApi(2)
        public Builder addVariant(@NonNull CharSequence charSequence) {
            List<CharSequence> list = this.mTextVariants;
            Objects.requireNonNull(charSequence);
            list.add(charSequence);
            return this;
        }

        @NonNull
        public CarText build() {
            return new CarText(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SpanWrapper {
        @NonNull
        @Keep
        private final CarSpan mCarSpan;
        @Keep
        private final int mEnd;
        @Keep
        private final int mFlags;
        @Keep
        private final int mStart;

        SpanWrapper() {
            this.mStart = 0;
            this.mEnd = 0;
            this.mFlags = 0;
            this.mCarSpan = new CarSpan();
        }

        SpanWrapper(@NonNull Spanned spanned, @NonNull CarSpan carSpan) {
            this.mStart = spanned.getSpanStart(carSpan);
            this.mEnd = spanned.getSpanEnd(carSpan);
            this.mFlags = spanned.getSpanFlags(carSpan);
            this.mCarSpan = carSpan;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SpanWrapper) {
                SpanWrapper spanWrapper = (SpanWrapper) obj;
                return this.mStart == spanWrapper.mStart && this.mEnd == spanWrapper.mEnd && this.mFlags == spanWrapper.mFlags && Objects.equals(this.mCarSpan, spanWrapper.mCarSpan);
            }
            return false;
        }

        @NonNull
        public CarSpan getCarSpan() {
            return this.mCarSpan;
        }

        public int getEnd() {
            return this.mEnd;
        }

        public int getFlags() {
            return this.mFlags;
        }

        public int getStart() {
            return this.mStart;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.mStart), Integer.valueOf(this.mEnd), Integer.valueOf(this.mFlags), this.mCarSpan);
        }

        @NonNull
        public String toString() {
            return "[" + this.mCarSpan + ": " + this.mStart + ", " + this.mEnd + ", flags: " + this.mFlags + "]";
        }
    }

    private CarText() {
        this.mText = "";
        this.mSpans = Collections.emptyList();
        this.mTextVariants = Collections.emptyList();
        this.mSpansForVariants = Collections.emptyList();
    }

    CarText(Builder builder) {
        this.mText = builder.mText.toString();
        this.mSpans = getSpans(builder.mText);
        List<CharSequence> list = builder.mTextVariants;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            CharSequence charSequence = list.get(i2);
            arrayList.add(charSequence.toString());
            arrayList2.add(getSpans(charSequence));
        }
        this.mTextVariants = CollectionUtils.unmodifiableCopy(arrayList);
        this.mSpansForVariants = CollectionUtils.unmodifiableCopy(arrayList2);
    }

    CarText(CharSequence charSequence) {
        this.mText = charSequence.toString();
        this.mSpans = getSpans(charSequence);
        this.mTextVariants = Collections.emptyList();
        this.mSpansForVariants = Collections.emptyList();
    }

    @NonNull
    public static CarText create(@NonNull CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        return new CarText(charSequence);
    }

    private static CharSequence getCharSequence(String str, List<SpanWrapper> list) {
        SpannableString spannableString = new SpannableString(str);
        for (SpanWrapper spanWrapper : CollectionUtils.emptyIfNull(list)) {
            spannableString.setSpan(spanWrapper.getCarSpan(), spanWrapper.getStart(), spanWrapper.getEnd(), spanWrapper.getFlags());
        }
        return spannableString;
    }

    private static List<SpanWrapper> getSpans(CharSequence charSequence) {
        Object[] spans;
        ArrayList arrayList = new ArrayList();
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            for (Object obj : spanned.getSpans(0, charSequence.length(), Object.class)) {
                if (obj instanceof CarSpan) {
                    arrayList.add(new SpanWrapper(spanned, (CarSpan) obj));
                }
            }
        }
        return CollectionUtils.unmodifiableCopy(arrayList);
    }

    public static boolean isNullOrEmpty(@Nullable CarText carText) {
        return carText == null || carText.isEmpty();
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static String toShortString(@Nullable CarText carText) {
        if (carText == null) {
            return null;
        }
        return StringUtils.shortenString(carText.toString());
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CarText) {
            CarText carText = (CarText) obj;
            return Objects.equals(this.mText, carText.mText) && Objects.equals(this.mSpans, carText.mSpans) && Objects.equals(this.mTextVariants, carText.mTextVariants) && Objects.equals(this.mSpansForVariants, carText.mSpansForVariants);
        }
        return false;
    }

    @NonNull
    public List<CharSequence> getVariants() {
        if (this.mTextVariants.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mTextVariants.size(); i2++) {
            arrayList.add(getCharSequence(this.mTextVariants.get(i2), this.mSpansForVariants.get(i2)));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public int hashCode() {
        return Objects.hash(this.mText, this.mSpans, this.mTextVariants, this.mSpansForVariants);
    }

    public boolean isEmpty() {
        return this.mText.isEmpty();
    }

    @NonNull
    public CharSequence toCharSequence() {
        return getCharSequence(this.mText, this.mSpans);
    }

    @NonNull
    public String toString() {
        return this.mText;
    }
}
