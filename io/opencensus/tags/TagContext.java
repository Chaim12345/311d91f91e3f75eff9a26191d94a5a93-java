package io.opencensus.tags;

import java.util.HashMap;
import java.util.Iterator;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class TagContext {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Iterator a();

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof TagContext) {
            Iterator a2 = a();
            Iterator a3 = ((TagContext) obj).a();
            HashMap hashMap = new HashMap();
            while (a2 != null && a2.hasNext()) {
                Tag tag = (Tag) a2.next();
                hashMap.put(tag, hashMap.containsKey(tag) ? Integer.valueOf(((Integer) hashMap.get(tag)).intValue() + 1) : 1);
            }
            while (a3 != null && a3.hasNext()) {
                Tag tag2 = (Tag) a3.next();
                if (!hashMap.containsKey(tag2)) {
                    return false;
                }
                int intValue = ((Integer) hashMap.get(tag2)).intValue();
                if (intValue > 1) {
                    hashMap.put(tag2, Integer.valueOf(intValue - 1));
                } else {
                    hashMap.remove(tag2);
                }
            }
            return hashMap.isEmpty();
        }
        return false;
    }

    public final int hashCode() {
        Iterator a2 = a();
        int i2 = 0;
        if (a2 == null) {
            return 0;
        }
        while (a2.hasNext()) {
            Tag tag = (Tag) a2.next();
            if (tag != null) {
                i2 += tag.hashCode();
            }
        }
        return i2;
    }

    public String toString() {
        return "TagContext";
    }
}
