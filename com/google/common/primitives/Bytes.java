package com.google.common.primitives;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes2.dex */
public final class Bytes {

    @GwtCompatible
    /* loaded from: classes2.dex */
    private static class ByteArrayAsList extends AbstractList<Byte> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final byte[] f9344a;

        /* renamed from: b  reason: collision with root package name */
        final int f9345b;

        /* renamed from: c  reason: collision with root package name */
        final int f9346c;

        ByteArrayAsList(byte[] bArr) {
            this(bArr, 0, bArr.length);
        }

        ByteArrayAsList(byte[] bArr, int i2, int i3) {
            this.f9344a = bArr;
            this.f9345b = i2;
            this.f9346c = i3;
        }

        byte[] a() {
            return Arrays.copyOfRange(this.f9344a, this.f9345b, this.f9346c);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Byte) && Bytes.indexOf(this.f9344a, ((Byte) obj).byteValue(), this.f9345b, this.f9346c) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ByteArrayAsList) {
                ByteArrayAsList byteArrayAsList = (ByteArrayAsList) obj;
                int size = size();
                if (byteArrayAsList.size() != size) {
                    return false;
                }
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.f9344a[this.f9345b + i2] != byteArrayAsList.f9344a[byteArrayAsList.f9345b + i2]) {
                        return false;
                    }
                }
                return true;
            }
            return super.equals(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public Byte get(int i2) {
            Preconditions.checkElementIndex(i2, size());
            return Byte.valueOf(this.f9344a[this.f9345b + i2]);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int i2 = 1;
            for (int i3 = this.f9345b; i3 < this.f9346c; i3++) {
                i2 = (i2 * 31) + Bytes.hashCode(this.f9344a[i3]);
            }
            return i2;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int indexOf;
            if (!(obj instanceof Byte) || (indexOf = Bytes.indexOf(this.f9344a, ((Byte) obj).byteValue(), this.f9345b, this.f9346c)) < 0) {
                return -1;
            }
            return indexOf - this.f9345b;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            int lastIndexOf;
            if (!(obj instanceof Byte) || (lastIndexOf = Bytes.lastIndexOf(this.f9344a, ((Byte) obj).byteValue(), this.f9345b, this.f9346c)) < 0) {
                return -1;
            }
            return lastIndexOf - this.f9345b;
        }

        @Override // java.util.AbstractList, java.util.List
        public Byte set(int i2, Byte b2) {
            Preconditions.checkElementIndex(i2, size());
            byte[] bArr = this.f9344a;
            int i3 = this.f9345b;
            byte b3 = bArr[i3 + i2];
            bArr[i3 + i2] = ((Byte) Preconditions.checkNotNull(b2)).byteValue();
            return Byte.valueOf(b3);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.f9346c - this.f9345b;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Byte> subList(int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i3, size());
            if (i2 == i3) {
                return Collections.emptyList();
            }
            byte[] bArr = this.f9344a;
            int i4 = this.f9345b;
            return new ByteArrayAsList(bArr, i2 + i4, i4 + i3);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 5);
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append((int) this.f9344a[this.f9345b]);
            int i2 = this.f9345b;
            while (true) {
                i2++;
                if (i2 >= this.f9346c) {
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    return sb.toString();
                }
                sb.append(", ");
                sb.append((int) this.f9344a[i2]);
            }
        }
    }

    private Bytes() {
    }

    public static List<Byte> asList(byte... bArr) {
        return bArr.length == 0 ? Collections.emptyList() : new ByteArrayAsList(bArr);
    }

    public static byte[] concat(byte[]... bArr) {
        int i2 = 0;
        for (byte[] bArr2 : bArr) {
            i2 += bArr2.length;
        }
        byte[] bArr3 = new byte[i2];
        int i3 = 0;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, i3, bArr4.length);
            i3 += bArr4.length;
        }
        return bArr3;
    }

    public static boolean contains(byte[] bArr, byte b2) {
        for (byte b3 : bArr) {
            if (b3 == b2) {
                return true;
            }
        }
        return false;
    }

    public static byte[] ensureCapacity(byte[] bArr, int i2, int i3) {
        Preconditions.checkArgument(i2 >= 0, "Invalid minLength: %s", i2);
        Preconditions.checkArgument(i3 >= 0, "Invalid padding: %s", i3);
        return bArr.length < i2 ? Arrays.copyOf(bArr, i2 + i3) : bArr;
    }

    public static int hashCode(byte b2) {
        return b2;
    }

    public static int indexOf(byte[] bArr, byte b2) {
        return indexOf(bArr, b2, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(byte[] bArr, byte b2, int i2, int i3) {
        while (i2 < i3) {
            if (bArr[i2] == b2) {
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
    public static int indexOf(byte[] bArr, byte[] bArr2) {
        Preconditions.checkNotNull(bArr, "array");
        Preconditions.checkNotNull(bArr2, TypedValues.Attributes.S_TARGET);
        if (bArr2.length == 0) {
            return 0;
        }
        int i2 = 0;
        while (i2 < (bArr.length - bArr2.length) + 1) {
            for (int i3 = 0; i3 < bArr2.length; i3++) {
                if (bArr[i2 + i3] != bArr2[i3]) {
                    break;
                }
            }
            return i2;
        }
        return -1;
    }

    public static int lastIndexOf(byte[] bArr, byte b2) {
        return lastIndexOf(bArr, b2, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(byte[] bArr, byte b2, int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            if (bArr[i4] == b2) {
                return i4;
            }
        }
        return -1;
    }

    public static void reverse(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        reverse(bArr, 0, bArr.length);
    }

    public static void reverse(byte[] bArr, int i2, int i3) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(i2, i3, bArr.length);
        for (int i4 = i3 - 1; i2 < i4; i4--) {
            byte b2 = bArr[i2];
            bArr[i2] = bArr[i4];
            bArr[i4] = b2;
            i2++;
        }
    }

    public static byte[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ByteArrayAsList) {
            return ((ByteArrayAsList) collection).a();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = ((Number) Preconditions.checkNotNull(array[i2])).byteValue();
        }
        return bArr;
    }
}
