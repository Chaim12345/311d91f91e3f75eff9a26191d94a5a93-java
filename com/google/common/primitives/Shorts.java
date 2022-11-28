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
import kotlin.jvm.internal.ShortCompanionObject;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Shorts extends ShortsMethodsForWeb {
    public static final int BYTES = 2;
    public static final short MAX_POWER_OF_TWO = 16384;

    /* loaded from: classes2.dex */
    private enum LexicographicalComparator implements Comparator<short[]> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(short[] sArr, short[] sArr2) {
            int min = Math.min(sArr.length, sArr2.length);
            for (int i2 = 0; i2 < min; i2++) {
                int compare = Shorts.compare(sArr[i2], sArr2[i2]);
                if (compare != 0) {
                    return compare;
                }
            }
            return sArr.length - sArr2.length;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Shorts.lexicographicalComparator()";
        }
    }

    @GwtCompatible
    /* loaded from: classes2.dex */
    private static class ShortArrayAsList extends AbstractList<Short> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final short[] f9369a;

        /* renamed from: b  reason: collision with root package name */
        final int f9370b;

        /* renamed from: c  reason: collision with root package name */
        final int f9371c;

        ShortArrayAsList(short[] sArr) {
            this(sArr, 0, sArr.length);
        }

        ShortArrayAsList(short[] sArr, int i2, int i3) {
            this.f9369a = sArr;
            this.f9370b = i2;
            this.f9371c = i3;
        }

        short[] a() {
            return Arrays.copyOfRange(this.f9369a, this.f9370b, this.f9371c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@NullableDecl Object obj) {
            return (obj instanceof Short) && Shorts.indexOf(this.f9369a, ((Short) obj).shortValue(), this.f9370b, this.f9371c) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ShortArrayAsList) {
                ShortArrayAsList shortArrayAsList = (ShortArrayAsList) obj;
                int size = size();
                if (shortArrayAsList.size() != size) {
                    return false;
                }
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.f9369a[this.f9370b + i2] != shortArrayAsList.f9369a[shortArrayAsList.f9370b + i2]) {
                        return false;
                    }
                }
                return true;
            }
            return super.equals(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public Short get(int i2) {
            Preconditions.checkElementIndex(i2, size());
            return Short.valueOf(this.f9369a[this.f9370b + i2]);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int i2 = 1;
            for (int i3 = this.f9370b; i3 < this.f9371c; i3++) {
                i2 = (i2 * 31) + Shorts.hashCode(this.f9369a[i3]);
            }
            return i2;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(@NullableDecl Object obj) {
            int indexOf;
            if (!(obj instanceof Short) || (indexOf = Shorts.indexOf(this.f9369a, ((Short) obj).shortValue(), this.f9370b, this.f9371c)) < 0) {
                return -1;
            }
            return indexOf - this.f9370b;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(@NullableDecl Object obj) {
            int lastIndexOf;
            if (!(obj instanceof Short) || (lastIndexOf = Shorts.lastIndexOf(this.f9369a, ((Short) obj).shortValue(), this.f9370b, this.f9371c)) < 0) {
                return -1;
            }
            return lastIndexOf - this.f9370b;
        }

        @Override // java.util.AbstractList, java.util.List
        public Short set(int i2, Short sh) {
            Preconditions.checkElementIndex(i2, size());
            short[] sArr = this.f9369a;
            int i3 = this.f9370b;
            short s2 = sArr[i3 + i2];
            sArr[i3 + i2] = ((Short) Preconditions.checkNotNull(sh)).shortValue();
            return Short.valueOf(s2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.f9371c - this.f9370b;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Short> subList(int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i3, size());
            if (i2 == i3) {
                return Collections.emptyList();
            }
            short[] sArr = this.f9369a;
            int i4 = this.f9370b;
            return new ShortArrayAsList(sArr, i2 + i4, i4 + i3);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 6);
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append((int) this.f9369a[this.f9370b]);
            int i2 = this.f9370b;
            while (true) {
                i2++;
                if (i2 >= this.f9371c) {
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    return sb.toString();
                }
                sb.append(", ");
                sb.append((int) this.f9369a[i2]);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static final class ShortConverter extends Converter<String, Short> implements Serializable {

        /* renamed from: a  reason: collision with root package name */
        static final ShortConverter f9372a = new ShortConverter();
        private static final long serialVersionUID = 1;

        private ShortConverter() {
        }

        private Object readResolve() {
            return f9372a;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        /* renamed from: f */
        public String d(Short sh) {
            return sh.toString();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        /* renamed from: g */
        public Short e(String str) {
            return Short.decode(str);
        }

        public String toString() {
            return "Shorts.stringConverter()";
        }
    }

    private Shorts() {
    }

    public static List<Short> asList(short... sArr) {
        return sArr.length == 0 ? Collections.emptyList() : new ShortArrayAsList(sArr);
    }

    public static short checkedCast(long j2) {
        short s2 = (short) j2;
        Preconditions.checkArgument(((long) s2) == j2, "Out of range: %s", j2);
        return s2;
    }

    public static int compare(short s2, short s3) {
        return s2 - s3;
    }

    public static short[] concat(short[]... sArr) {
        int i2 = 0;
        for (short[] sArr2 : sArr) {
            i2 += sArr2.length;
        }
        short[] sArr3 = new short[i2];
        int i3 = 0;
        for (short[] sArr4 : sArr) {
            System.arraycopy(sArr4, 0, sArr3, i3, sArr4.length);
            i3 += sArr4.length;
        }
        return sArr3;
    }

    @Beta
    public static short constrainToRange(short s2, short s3, short s4) {
        Preconditions.checkArgument(s3 <= s4, "min (%s) must be less than or equal to max (%s)", (int) s3, (int) s4);
        return s2 < s3 ? s3 : s2 < s4 ? s2 : s4;
    }

    public static boolean contains(short[] sArr, short s2) {
        for (short s3 : sArr) {
            if (s3 == s2) {
                return true;
            }
        }
        return false;
    }

    public static short[] ensureCapacity(short[] sArr, int i2, int i3) {
        Preconditions.checkArgument(i2 >= 0, "Invalid minLength: %s", i2);
        Preconditions.checkArgument(i3 >= 0, "Invalid padding: %s", i3);
        return sArr.length < i2 ? Arrays.copyOf(sArr, i2 + i3) : sArr;
    }

    @GwtIncompatible
    public static short fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 2, "array too small: %s < %s", bArr.length, 2);
        return fromBytes(bArr[0], bArr[1]);
    }

    @GwtIncompatible
    public static short fromBytes(byte b2, byte b3) {
        return (short) ((b2 << 8) | (b3 & 255));
    }

    public static int hashCode(short s2) {
        return s2;
    }

    public static int indexOf(short[] sArr, short s2) {
        return indexOf(sArr, s2, 0, sArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(short[] sArr, short s2, int i2, int i3) {
        while (i2 < i3) {
            if (sArr[i2] == s2) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int indexOf(short[] sArr, short[] sArr2) {
        Preconditions.checkNotNull(sArr, "array");
        Preconditions.checkNotNull(sArr2, TypedValues.Attributes.S_TARGET);
        if (sArr2.length == 0) {
            return 0;
        }
        int i2 = 0;
        while (i2 < (sArr.length - sArr2.length) + 1) {
            for (int i3 = 0; i3 < sArr2.length; i3++) {
                if (sArr[i2 + i3] != sArr2[i3]) {
                    break;
                }
            }
            return i2;
        }
        return -1;
    }

    public static String join(String str, short... sArr) {
        Preconditions.checkNotNull(str);
        if (sArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(sArr.length * 6);
        sb.append((int) sArr[0]);
        for (int i2 = 1; i2 < sArr.length; i2++) {
            sb.append(str);
            sb.append((int) sArr[i2]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(short[] sArr, short s2) {
        return lastIndexOf(sArr, s2, 0, sArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(short[] sArr, short s2, int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            if (sArr[i4] == s2) {
                return i4;
            }
        }
        return -1;
    }

    public static Comparator<short[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static short max(short... sArr) {
        Preconditions.checkArgument(sArr.length > 0);
        short s2 = sArr[0];
        for (int i2 = 1; i2 < sArr.length; i2++) {
            if (sArr[i2] > s2) {
                s2 = sArr[i2];
            }
        }
        return s2;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static short min(short... sArr) {
        Preconditions.checkArgument(sArr.length > 0);
        short s2 = sArr[0];
        for (int i2 = 1; i2 < sArr.length; i2++) {
            if (sArr[i2] < s2) {
                s2 = sArr[i2];
            }
        }
        return s2;
    }

    public static void reverse(short[] sArr) {
        Preconditions.checkNotNull(sArr);
        reverse(sArr, 0, sArr.length);
    }

    public static void reverse(short[] sArr, int i2, int i3) {
        Preconditions.checkNotNull(sArr);
        Preconditions.checkPositionIndexes(i2, i3, sArr.length);
        for (int i4 = i3 - 1; i2 < i4; i4--) {
            short s2 = sArr[i2];
            sArr[i2] = sArr[i4];
            sArr[i4] = s2;
            i2++;
        }
    }

    public static short saturatedCast(long j2) {
        return j2 > 32767 ? ShortCompanionObject.MAX_VALUE : j2 < -32768 ? ShortCompanionObject.MIN_VALUE : (short) j2;
    }

    public static void sortDescending(short[] sArr) {
        Preconditions.checkNotNull(sArr);
        sortDescending(sArr, 0, sArr.length);
    }

    public static void sortDescending(short[] sArr, int i2, int i3) {
        Preconditions.checkNotNull(sArr);
        Preconditions.checkPositionIndexes(i2, i3, sArr.length);
        Arrays.sort(sArr, i2, i3);
        reverse(sArr, i2, i3);
    }

    @Beta
    public static Converter<String, Short> stringConverter() {
        return ShortConverter.f9372a;
    }

    public static short[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ShortArrayAsList) {
            return ((ShortArrayAsList) collection).a();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        short[] sArr = new short[length];
        for (int i2 = 0; i2 < length; i2++) {
            sArr[i2] = ((Number) Preconditions.checkNotNull(array[i2])).shortValue();
        }
        return sArr;
    }

    @GwtIncompatible
    public static byte[] toByteArray(short s2) {
        return new byte[]{(byte) (s2 >> 8), (byte) s2};
    }
}
