package androidx.car.app.model;

import android.text.Spanned;
import android.text.style.CharacterStyle;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.List;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class ModelUtils {
    private ModelUtils() {
    }

    private static boolean checkCarTextHasSpanType(CarText carText, Class<? extends CharacterStyle> cls) {
        Object[] spans;
        if (carText.isEmpty()) {
            return false;
        }
        Spanned spanned = (Spanned) carText.toCharSequence();
        for (Object obj : spanned.getSpans(0, spanned.length(), Object.class)) {
            int spanStart = spanned.getSpanStart(obj);
            int spanEnd = spanned.getSpanEnd(obj);
            if (cls.isInstance(obj) && spanStart >= 0 && spanStart != spanEnd && spanStart < spanned.length()) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRowHasSpanType(Row row, Class<? extends CharacterStyle> cls) {
        CarText title = row.getTitle();
        Objects.requireNonNull(title);
        if (checkCarTextHasSpanType(title, cls)) {
            return true;
        }
        List<CarText> texts = row.getTexts();
        for (int i2 = 0; i2 < texts.size(); i2++) {
            if (checkCarTextHasSpanType(texts.get(i2), cls)) {
                return true;
            }
        }
        return false;
    }

    public static void validateAllNonBrowsableRowsHaveDistance(@NonNull List<Item> list) {
        int i2 = 0;
        int i3 = 0;
        for (Item item : list) {
            if (!(item instanceof Row)) {
                throw new IllegalArgumentException("Item in the list is not a Row");
            }
            Row row = (Row) item;
            if (!row.isBrowsable()) {
                i2++;
            }
            if (checkRowHasSpanType(row, DistanceSpan.class)) {
                i3++;
            }
        }
        if (i2 > i3) {
            throw new IllegalArgumentException("All non-browsable rows must have a distance span attached to either its title or texts");
        }
    }

    public static void validateAllRowsHaveDistanceOrDuration(@NonNull List<Item> list) {
        for (Item item : list) {
            if (!(item instanceof Row)) {
                throw new IllegalArgumentException("Item in the list is not a Row");
            }
            Row row = (Row) item;
            if (!checkRowHasSpanType(row, DistanceSpan.class) && !checkRowHasSpanType(row, DurationSpan.class)) {
                throw new IllegalArgumentException("All rows must have either a distance or duration span attached to either its title or texts");
            }
        }
    }

    public static void validateAllRowsHaveOnlySmallImages(@NonNull List<Item> list) {
        for (Item item : list) {
            if (!(item instanceof Row)) {
                throw new IllegalArgumentException("Item in the list is not a Row");
            }
            Row row = (Row) item;
            if (row.getImage() != null && row.getRowImageType() == 2) {
                throw new IllegalArgumentException("Rows must only use small-sized images");
            }
        }
    }

    public static void validateNoRowsHaveBothMarkersAndImages(@NonNull List<Item> list) {
        for (Item item : list) {
            if (!(item instanceof Row)) {
                throw new IllegalArgumentException("Item in the list is not a Row");
            }
            Row row = (Row) item;
            Metadata metadata = row.getMetadata();
            if (metadata != null) {
                boolean z = true;
                boolean z2 = row.getImage() != null;
                Place place = metadata.getPlace();
                z = (place == null || place.getMarker() == null) ? false : false;
                if (z2 && z) {
                    throw new IllegalArgumentException("Rows can't have both a marker and an image");
                }
            }
        }
    }
}
