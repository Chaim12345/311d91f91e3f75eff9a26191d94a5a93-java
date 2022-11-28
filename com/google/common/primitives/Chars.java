package com.google.common.primitives;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import kotlin.jvm.internal.CharCompanionObject;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.internal.ws.WebSocketProtocol;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Chars {
    public static final int BYTES = 2;

    @GwtCompatible
    /* loaded from: classes2.dex */
    private static class CharArrayAsList extends AbstractList<Character> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final char[] f9347a;

        /* renamed from: b  reason: collision with root package name */
        final int f9348b;

        /* renamed from: c  reason: collision with root package name */
        final int f9349c;

        CharArrayAsList(char[] cArr) {
            this(cArr, 0, cArr.length);
        }

        CharArrayAsList(char[] cArr, int i2, int i3) {
            this.f9347a = cArr;
            this.f9348b = i2;
            this.f9349c = i3;
        }

        char[] a() {
            return Arrays.copyOfRange(this.f9347a, this.f9348b, this.f9349c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Character) && Chars.indexOf(this.f9347a, ((Character) obj).charValue(), this.f9348b, this.f9349c) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof CharArrayAsList) {
                CharArrayAsList charArrayAsList = (CharArrayAsList) obj;
                int size = size();
                if (charArrayAsList.size() != size) {
                    return false;
                }
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.f9347a[this.f9348b + i2] != charArrayAsList.f9347a[charArrayAsList.f9348b + i2]) {
                        return false;
                    }
                }
                return true;
            }
            return super.equals(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public Character get(int i2) {
            Preconditions.checkElementIndex(i2, size());
            return Character.valueOf(this.f9347a[this.f9348b + i2]);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int i2 = 1;
            for (int i3 = this.f9348b; i3 < this.f9349c; i3++) {
                i2 = (i2 * 31) + Chars.hashCode(this.f9347a[i3]);
            }
            return i2;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int indexOf;
            if (!(obj instanceof Character) || (indexOf = Chars.indexOf(this.f9347a, ((Character) obj).charValue(), this.f9348b, this.f9349c)) < 0) {
                return -1;
            }
            return indexOf - this.f9348b;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            int lastIndexOf;
            if (!(obj instanceof Character) || (lastIndexOf = Chars.lastIndexOf(this.f9347a, ((Character) obj).charValue(), this.f9348b, this.f9349c)) < 0) {
                return -1;
            }
            return lastIndexOf - this.f9348b;
        }

        @Override // java.util.AbstractList, java.util.List
        public Character set(int i2, Character ch) {
            Preconditions.checkElementIndex(i2, size());
            char[] cArr = this.f9347a;
            int i3 = this.f9348b;
            char c2 = cArr[i3 + i2];
            cArr[i3 + i2] = ((Character) Preconditions.checkNotNull(ch)).charValue();
            return Character.valueOf(c2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.f9349c - this.f9348b;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Character> subList(int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i3, size());
            if (i2 == i3) {
                return Collections.emptyList();
            }
            char[] cArr = this.f9347a;
            int i4 = this.f9348b;
            return new CharArrayAsList(cArr, i2 + i4, i4 + i3);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 3);
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(this.f9347a[this.f9348b]);
            int i2 = this.f9348b;
            while (true) {
                i2++;
                if (i2 >= this.f9349c) {
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    return sb.toString();
                }
                sb.append(", ");
                sb.append(this.f9347a[i2]);
            }
        }
    }

    /* loaded from: classes2.dex */
    private enum LexicographicalComparator implements Comparator<char[]> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(char[] cArr, char[] cArr2) {
            int min = Math.min(cArr.length, cArr2.length);
            for (int i2 = 0; i2 < min; i2++) {
                int compare = Chars.compare(cArr[i2], cArr2[i2]);
                if (compare != 0) {
                    return compare;
                }
            }
            return cArr.length - cArr2.length;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Chars.lexicographicalComparator()";
        }
    }

    private Chars() {
    }

    public static List<Character> asList(char... cArr) {
        return cArr.length == 0 ? Collections.emptyList() : new CharArrayAsList(cArr);
    }

    public static char checkedCast(long j2) {
        char c2 = (char) j2;
        Preconditions.checkArgument(((long) c2) == j2, "Out of range: %s", j2);
        return c2;
    }

    public static int compare(char c2, char c3) {
        return c2 - c3;
    }

    public static char[] concat(char[]... cArr) {
        int i2 = 0;
        for (char[] cArr2 : cArr) {
            i2 += cArr2.length;
        }
        char[] cArr3 = new char[i2];
        int i3 = 0;
        for (char[] cArr4 : cArr) {
            System.arraycopy(cArr4, 0, cArr3, i3, cArr4.length);
            i3 += cArr4.length;
        }
        return cArr3;
    }

    @Beta
    public static char constrainToRange(char c2, char c3, char c4) {
        Preconditions.checkArgument(c3 <= c4, "min (%s) must be less than or equal to max (%s)", c3, c4);
        return c2 < c3 ? c3 : c2 < c4 ? c2 : c4;
    }

    public static boolean contains(char[] cArr, char c2) {
        for (char c3 : cArr) {
            if (c3 == c2) {
                return true;
            }
        }
        return false;
    }

    public static char[] ensureCapacity(char[] cArr, int i2, int i3) {
        Preconditions.checkArgument(i2 >= 0, "Invalid minLength: %s", i2);
        Preconditions.checkArgument(i3 >= 0, "Invalid padding: %s", i3);
        return cArr.length < i2 ? Arrays.copyOf(cArr, i2 + i3) : cArr;
    }

    @GwtIncompatible
    public static char fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 2, "array too small: %s < %s", bArr.length, 2);
        return fromBytes(bArr[0], bArr[1]);
    }

    @GwtIncompatible
    public static char fromBytes(byte b2, byte b3) {
        return (char) ((b2 << 8) | (b3 & 255));
    }

    public static int hashCode(char c2) {
        return c2;
    }

    public static int indexOf(char[] cArr, char c2) {
        return indexOf(cArr, c2, 0, cArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(char[] cArr, char c2, int i2, int i3) {
        while (i2 < i3) {
            if (cArr[i2] == c2) {
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
    public static int indexOf(char[] cArr, char[] cArr2) {
        Preconditions.checkNotNull(cArr, "array");
        Preconditions.checkNotNull(cArr2, TypedValues.Attributes.S_TARGET);
        if (cArr2.length == 0) {
            return 0;
        }
        int i2 = 0;
        while (i2 < (cArr.length - cArr2.length) + 1) {
            for (int i3 = 0; i3 < cArr2.length; i3++) {
                if (cArr[i2 + i3] != cArr2[i3]) {
                    break;
                }
            }
            return i2;
        }
        return -1;
    }

    public static String join(String str, char... cArr) {
        Preconditions.checkNotNull(str);
        int length = cArr.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder((str.length() * (length - 1)) + length);
        sb.append(cArr[0]);
        for (int i2 = 1; i2 < length; i2++) {
            sb.append(str);
            sb.append(cArr[i2]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(char[] cArr, char c2) {
        return lastIndexOf(cArr, c2, 0, cArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(char[] cArr, char c2, int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            if (cArr[i4] == c2) {
                return i4;
            }
        }
        return -1;
    }

    public static Comparator<char[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static char max(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c2 = cArr[0];
        for (int i2 = 1; i2 < cArr.length; i2++) {
            if (cArr[i2] > c2) {
                c2 = cArr[i2];
            }
        }
        return c2;
    }

    public static char min(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c2 = cArr[0];
        for (int i2 = 1; i2 < cArr.length; i2++) {
            if (cArr[i2] < c2) {
                c2 = cArr[i2];
            }
        }
        return c2;
    }

    public static void reverse(char[] cArr) {
        Preconditions.checkNotNull(cArr);
        reverse(cArr, 0, cArr.length);
    }

    public static void reverse(char[] cArr, int i2, int i3) {
        Preconditions.checkNotNull(cArr);
        Preconditions.checkPositionIndexes(i2, i3, cArr.length);
        for (int i4 = i3 - 1; i2 < i4; i4--) {
            char c2 = cArr[i2];
            cArr[i2] = cArr[i4];
            cArr[i4] = c2;
            i2++;
        }
    }

    public static char saturatedCast(long j2) {
        if (j2 > WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            return CharCompanionObject.MAX_VALUE;
        }
        if (j2 < 0) {
            return (char) 0;
        }
        return (char) j2;
    }

    public static void sortDescending(char[] cArr) {
        Preconditions.checkNotNull(cArr);
        sortDescending(cArr, 0, cArr.length);
    }

    public static void sortDescending(char[] cArr, int i2, int i3) {
        Preconditions.checkNotNull(cArr);
        Preconditions.checkPositionIndexes(i2, i3, cArr.length);
        Arrays.sort(cArr, i2, i3);
        reverse(cArr, i2, i3);
    }

    public static char[] toArray(Collection<Character> collection) {
        if (collection instanceof CharArrayAsList) {
            return ((CharArrayAsList) collection).a();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 < length; i2++) {
            cArr[i2] = ((Character) Preconditions.checkNotNull(array[i2])).charValue();
        }
        return cArr;
    }

    @GwtIncompatible
    public static byte[] toByteArray(char c2) {
        return new byte[]{(byte) (c2 >> '\b'), (byte) c2};
    }
}
