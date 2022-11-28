package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes.dex */
public class KeyCache {

    /* renamed from: a  reason: collision with root package name */
    HashMap f1767a = new HashMap();

    public float getFloatValue(Object obj, String str, int i2) {
        HashMap hashMap;
        float[] fArr;
        if (this.f1767a.containsKey(obj) && (hashMap = (HashMap) this.f1767a.get(obj)) != null && hashMap.containsKey(str) && (fArr = (float[]) hashMap.get(str)) != null && fArr.length > i2) {
            return fArr[i2];
        }
        return Float.NaN;
    }

    public void setFloatValue(Object obj, String str, int i2, float f2) {
        HashMap hashMap;
        if (this.f1767a.containsKey(obj)) {
            hashMap = (HashMap) this.f1767a.get(obj);
            if (hashMap == null) {
                hashMap = new HashMap();
            }
            if (hashMap.containsKey(str)) {
                float[] fArr = (float[]) hashMap.get(str);
                if (fArr == null) {
                    fArr = new float[0];
                }
                if (fArr.length <= i2) {
                    fArr = Arrays.copyOf(fArr, i2 + 1);
                }
                fArr[i2] = f2;
                hashMap.put(str, fArr);
                return;
            }
            float[] fArr2 = new float[i2 + 1];
            fArr2[i2] = f2;
            hashMap.put(str, fArr2);
        } else {
            hashMap = new HashMap();
            float[] fArr3 = new float[i2 + 1];
            fArr3[i2] = f2;
            hashMap.put(str, fArr3);
        }
        this.f1767a.put(obj, hashMap);
    }
}
