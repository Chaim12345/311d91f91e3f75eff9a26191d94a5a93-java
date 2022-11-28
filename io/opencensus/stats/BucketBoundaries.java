package io.opencensus.stats;

import io.opencensus.internal.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class BucketBoundaries {
    private static final Logger logger = Logger.getLogger(BucketBoundaries.class.getName());

    public static final BucketBoundaries create(List<Double> list) {
        Utils.checkNotNull(list, "bucketBoundaries");
        ArrayList arrayList = new ArrayList(list);
        if (arrayList.size() > 1) {
            double doubleValue = ((Double) arrayList.get(0)).doubleValue();
            int i2 = 1;
            while (i2 < arrayList.size()) {
                double doubleValue2 = ((Double) arrayList.get(i2)).doubleValue();
                Utils.checkArgument(doubleValue < doubleValue2, "Bucket boundaries not sorted.");
                i2++;
                doubleValue = doubleValue2;
            }
        }
        return new AutoValue_BucketBoundaries(Collections.unmodifiableList(dropNegativeBucketBounds(arrayList)));
    }

    private static List<Double> dropNegativeBucketBounds(List<Double> list) {
        int i2 = 0;
        int i3 = 0;
        for (Double d2 : list) {
            if (d2.doubleValue() > 0.0d) {
                break;
            } else if (d2.doubleValue() == 0.0d) {
                i3++;
            } else {
                i2++;
            }
        }
        if (i2 > 0) {
            logger.log(Level.WARNING, "Dropping " + i2 + " negative bucket boundaries, the values must be strictly > 0.");
        }
        return list.subList(i2 + i3, list.size());
    }

    public abstract List<Double> getBoundaries();
}
