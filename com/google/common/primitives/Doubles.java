package com.google.common.primitives;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.slf4j.Marker;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Doubles extends DoublesMethodsForWeb {
    public static final int BYTES = 8;
    @GwtIncompatible

    /* renamed from: a  reason: collision with root package name */
    static final Pattern f9350a = fpPattern();

    @GwtCompatible
    /* loaded from: classes2.dex */
    private static class DoubleArrayAsList extends AbstractList<Double> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final double[] f9351a;

        /* renamed from: b  reason: collision with root package name */
        final int f9352b;

        /* renamed from: c  reason: collision with root package name */
        final int f9353c;

        DoubleArrayAsList(double[] dArr) {
            this(dArr, 0, dArr.length);
        }

        DoubleArrayAsList(double[] dArr, int i2, int i3) {
            this.f9351a = dArr;
            this.f9352b = i2;
            this.f9353c = i3;
        }

        double[] a() {
            return Arrays.copyOfRange(this.f9351a, this.f9352b, this.f9353c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Double) && Doubles.indexOf(this.f9351a, ((Double) obj).doubleValue(), this.f9352b, this.f9353c) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof DoubleArrayAsList) {
                DoubleArrayAsList doubleArrayAsList = (DoubleArrayAsList) obj;
                int size = size();
                if (doubleArrayAsList.size() != size) {
                    return false;
                }
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.f9351a[this.f9352b + i2] != doubleArrayAsList.f9351a[doubleArrayAsList.f9352b + i2]) {
                        return false;
                    }
                }
                return true;
            }
            return super.equals(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public Double get(int i2) {
            Preconditions.checkElementIndex(i2, size());
            return Double.valueOf(this.f9351a[this.f9352b + i2]);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int i2 = 1;
            for (int i3 = this.f9352b; i3 < this.f9353c; i3++) {
                i2 = (i2 * 31) + Doubles.hashCode(this.f9351a[i3]);
            }
            return i2;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int indexOf;
            if (!(obj instanceof Double) || (indexOf = Doubles.indexOf(this.f9351a, ((Double) obj).doubleValue(), this.f9352b, this.f9353c)) < 0) {
                return -1;
            }
            return indexOf - this.f9352b;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            int lastIndexOf;
            if (!(obj instanceof Double) || (lastIndexOf = Doubles.lastIndexOf(this.f9351a, ((Double) obj).doubleValue(), this.f9352b, this.f9353c)) < 0) {
                return -1;
            }
            return lastIndexOf - this.f9352b;
        }

        @Override // java.util.AbstractList, java.util.List
        public Double set(int i2, Double d2) {
            Preconditions.checkElementIndex(i2, size());
            double[] dArr = this.f9351a;
            int i3 = this.f9352b;
            double d3 = dArr[i3 + i2];
            dArr[i3 + i2] = ((Double) Preconditions.checkNotNull(d2)).doubleValue();
            return Double.valueOf(d3);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.f9353c - this.f9352b;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Double> subList(int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i3, size());
            if (i2 == i3) {
                return Collections.emptyList();
            }
            double[] dArr = this.f9351a;
            int i4 = this.f9352b;
            return new DoubleArrayAsList(dArr, i2 + i4, i4 + i3);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 12);
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(this.f9351a[this.f9352b]);
            int i2 = this.f9352b;
            while (true) {
                i2++;
                if (i2 >= this.f9353c) {
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    return sb.toString();
                }
                sb.append(", ");
                sb.append(this.f9351a[i2]);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static final class DoubleConverter extends Converter<String, Double> implements Serializable {

        /* renamed from: a  reason: collision with root package name */
        static final DoubleConverter f9354a = new DoubleConverter();
        private static final long serialVersionUID = 1;

        private DoubleConverter() {
        }

        private Object readResolve() {
            return f9354a;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        /* renamed from: f */
        public String d(Double d2) {
            return d2.toString();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        /* renamed from: g */
        public Double e(String str) {
            return Double.valueOf(str);
        }

        public String toString() {
            return "Doubles.stringConverter()";
        }
    }

    /* loaded from: classes2.dex */
    private enum LexicographicalComparator implements Comparator<double[]> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(double[] dArr, double[] dArr2) {
            int min = Math.min(dArr.length, dArr2.length);
            for (int i2 = 0; i2 < min; i2++) {
                int compare = Double.compare(dArr[i2], dArr2[i2]);
                if (compare != 0) {
                    return compare;
                }
            }
            return dArr.length - dArr2.length;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Doubles.lexicographicalComparator()";
        }
    }

    private Doubles() {
    }

    public static List<Double> asList(double... dArr) {
        return dArr.length == 0 ? Collections.emptyList() : new DoubleArrayAsList(dArr);
    }

    public static int compare(double d2, double d3) {
        return Double.compare(d2, d3);
    }

    public static double[] concat(double[]... dArr) {
        int i2 = 0;
        for (double[] dArr2 : dArr) {
            i2 += dArr2.length;
        }
        double[] dArr3 = new double[i2];
        int i3 = 0;
        for (double[] dArr4 : dArr) {
            System.arraycopy(dArr4, 0, dArr3, i3, dArr4.length);
            i3 += dArr4.length;
        }
        return dArr3;
    }

    @Beta
    public static double constrainToRange(double d2, double d3, double d4) {
        Preconditions.checkArgument(d3 <= d4, "min (%s) must be less than or equal to max (%s)", Double.valueOf(d3), Double.valueOf(d4));
        return Math.min(Math.max(d2, d3), d4);
    }

    public static boolean contains(double[] dArr, double d2) {
        for (double d3 : dArr) {
            if (d3 == d2) {
                return true;
            }
        }
        return false;
    }

    public static double[] ensureCapacity(double[] dArr, int i2, int i3) {
        Preconditions.checkArgument(i2 >= 0, "Invalid minLength: %s", i2);
        Preconditions.checkArgument(i3 >= 0, "Invalid padding: %s", i3);
        return dArr.length < i2 ? Arrays.copyOf(dArr, i2 + i3) : dArr;
    }

    @GwtIncompatible
    private static Pattern fpPattern() {
        return Pattern.compile(("[+-]?(?:NaN|Infinity|" + ("(?:\\d+#(?:\\.\\d*#)?|\\.\\d+#)(?:[eE][+-]?\\d+#)?[fFdD]?") + "|" + ("0[xX](?:[0-9a-fA-F]+#(?:\\.[0-9a-fA-F]*#)?|\\.[0-9a-fA-F]+#)[pP][+-]?\\d+#[fFdD]?") + ")").replace("#", Marker.ANY_NON_NULL_MARKER));
    }

    public static int hashCode(double d2) {
        return Double.valueOf(d2).hashCode();
    }

    public static int indexOf(double[] dArr, double d2) {
        return indexOf(dArr, d2, 0, dArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(double[] dArr, double d2, int i2, int i3) {
        while (i2 < i3) {
            if (dArr[i2] == d2) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int indexOf(double[] dArr, double[] dArr2) {
        Preconditions.checkNotNull(dArr, "array");
        Preconditions.checkNotNull(dArr2, TypedValues.Attributes.S_TARGET);
        if (dArr2.length == 0) {
            return 0;
        }
        int i2 = 0;
        while (i2 < (dArr.length - dArr2.length) + 1) {
            for (int i3 = 0; i3 < dArr2.length; i3++) {
                if (dArr[i2 + i3] != dArr2[i3]) {
                    break;
                }
            }
            return i2;
        }
        return -1;
    }

    public static boolean isFinite(double d2) {
        return Double.NEGATIVE_INFINITY < d2 && d2 < Double.POSITIVE_INFINITY;
    }

    public static String join(String str, double... dArr) {
        Preconditions.checkNotNull(str);
        if (dArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(dArr.length * 12);
        sb.append(dArr[0]);
        for (int i2 = 1; i2 < dArr.length; i2++) {
            sb.append(str);
            sb.append(dArr[i2]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(double[] dArr, double d2) {
        return lastIndexOf(dArr, d2, 0, dArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(double[] dArr, double d2, int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            if (dArr[i4] == d2) {
                return i4;
            }
        }
        return -1;
    }

    public static Comparator<double[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static double max(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0);
        double d2 = dArr[0];
        for (int i2 = 1; i2 < dArr.length; i2++) {
            d2 = Math.max(d2, dArr[i2]);
        }
        return d2;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static double min(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0);
        double d2 = dArr[0];
        for (int i2 = 1; i2 < dArr.length; i2++) {
            d2 = Math.min(d2, dArr[i2]);
        }
        return d2;
    }

    public static void reverse(double[] dArr) {
        Preconditions.checkNotNull(dArr);
        reverse(dArr, 0, dArr.length);
    }

    public static void reverse(double[] dArr, int i2, int i3) {
        Preconditions.checkNotNull(dArr);
        Preconditions.checkPositionIndexes(i2, i3, dArr.length);
        for (int i4 = i3 - 1; i2 < i4; i4--) {
            double d2 = dArr[i2];
            dArr[i2] = dArr[i4];
            dArr[i4] = d2;
            i2++;
        }
    }

    public static void sortDescending(double[] dArr) {
        Preconditions.checkNotNull(dArr);
        sortDescending(dArr, 0, dArr.length);
    }

    public static void sortDescending(double[] dArr, int i2, int i3) {
        Preconditions.checkNotNull(dArr);
        Preconditions.checkPositionIndexes(i2, i3, dArr.length);
        Arrays.sort(dArr, i2, i3);
        reverse(dArr, i2, i3);
    }

    @Beta
    public static Converter<String, Double> stringConverter() {
        return DoubleConverter.f9354a;
    }

    public static double[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof DoubleArrayAsList) {
            return ((DoubleArrayAsList) collection).a();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        double[] dArr = new double[length];
        for (int i2 = 0; i2 < length; i2++) {
            dArr[i2] = ((Number) Preconditions.checkNotNull(array[i2])).doubleValue();
        }
        return dArr;
    }

    @Beta
    @NullableDecl
    @GwtIncompatible
    public static Double tryParse(String str) {
        if (f9350a.matcher(str).matches()) {
            try {
                return Double.valueOf(Double.parseDouble(str));
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return null;
    }
}
