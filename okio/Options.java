package okio;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u000b\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004:\u0001\u0017B!\b\u0002\u0012\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0096\u0002R$\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\b8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u000e\u001a\u00020\r8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0014\u001a\u00020\u00058V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, d2 = {"Lokio/Options;", "Lkotlin/collections/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "", FirebaseAnalytics.Param.INDEX, "get", "", "byteStrings", "[Lokio/ByteString;", "getByteStrings$okio", "()[Lokio/ByteString;", "", "trie", "[I", "getTrie$okio", "()[I", "getSize", "()I", "size", "<init>", "([Lokio/ByteString;[I)V", "Companion", "okio"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ByteString[] byteStrings;
    @NotNull
    private final int[] trie;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0017\u0010\u0018JT\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\u0002J#\u0010\u0012\u001a\u00020\u00112\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u0010\"\u00020\tH\u0007¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0016\u001a\u00020\u0002*\u00020\u00048B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"Lokio/Options$Companion;", "", "", "nodeOffset", "Lokio/Buffer;", "node", "", "byteStringOffset", "", "Lokio/ByteString;", "byteStrings", "fromIndex", "toIndex", "indexes", "", "buildTrieRecursive", "", "Lokio/Options;", "of", "([Lokio/ByteString;)Lokio/Options;", "getIntCount", "(Lokio/Buffer;)J", "intCount", "<init>", "()V", "okio"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        static /* synthetic */ void a(Companion companion, long j2, Buffer buffer, int i2, List list, int i3, int i4, List list2, int i5, Object obj) {
            companion.buildTrieRecursive((i5 & 1) != 0 ? 0L : j2, buffer, (i5 & 4) != 0 ? 0 : i2, list, (i5 & 16) != 0 ? 0 : i3, (i5 & 32) != 0 ? list.size() : i4, list2);
        }

        private final void buildTrieRecursive(long j2, Buffer buffer, int i2, List<? extends ByteString> list, int i3, int i4, List<Integer> list2) {
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            Buffer buffer2;
            int i10 = i2;
            if (!(i3 < i4)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (i3 < i4) {
                int i11 = i3;
                while (true) {
                    int i12 = i11 + 1;
                    if (!(list.get(i11).size() >= i10)) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                    if (i12 >= i4) {
                        break;
                    }
                    i11 = i12;
                }
            }
            ByteString byteString = list.get(i3);
            ByteString byteString2 = list.get(i4 - 1);
            int i13 = -1;
            if (i10 == byteString.size()) {
                int i14 = i3 + 1;
                i5 = i14;
                i6 = list2.get(i3).intValue();
                byteString = list.get(i14);
            } else {
                i5 = i3;
                i6 = -1;
            }
            if (byteString.getByte(i10) == byteString2.getByte(i10)) {
                int min = Math.min(byteString.size(), byteString2.size());
                if (i10 < min) {
                    int i15 = i10;
                    i7 = 0;
                    while (true) {
                        int i16 = i15 + 1;
                        if (byteString.getByte(i15) != byteString2.getByte(i15)) {
                            break;
                        }
                        i7++;
                        if (i16 >= min) {
                            break;
                        }
                        i15 = i16;
                    }
                } else {
                    i7 = 0;
                }
                long intCount = j2 + getIntCount(buffer) + 2 + i7 + 1;
                buffer.writeInt(-i7);
                buffer.writeInt(i6);
                int i17 = i10 + i7;
                if (i10 < i17) {
                    while (true) {
                        int i18 = i10 + 1;
                        buffer.writeInt(byteString.getByte(i10) & 255);
                        if (i18 >= i17) {
                            break;
                        }
                        i10 = i18;
                    }
                }
                if (i5 + 1 == i4) {
                    if (!(i17 == list.get(i5).size())) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    buffer.writeInt(list2.get(i5).intValue());
                    return;
                }
                Buffer buffer3 = new Buffer();
                buffer.writeInt(((int) (getIntCount(buffer3) + intCount)) * (-1));
                buildTrieRecursive(intCount, buffer3, i17, list, i5, i4, list2);
                buffer.writeAll(buffer3);
                return;
            }
            int i19 = i5 + 1;
            int i20 = 1;
            if (i19 < i4) {
                while (true) {
                    int i21 = i19 + 1;
                    if (list.get(i19 - 1).getByte(i10) != list.get(i19).getByte(i10)) {
                        i20++;
                    }
                    if (i21 >= i4) {
                        break;
                    }
                    i19 = i21;
                }
            }
            long intCount2 = j2 + getIntCount(buffer) + 2 + (i20 * 2);
            buffer.writeInt(i20);
            buffer.writeInt(i6);
            if (i5 < i4) {
                int i22 = i5;
                while (true) {
                    int i23 = i22 + 1;
                    byte b2 = list.get(i22).getByte(i10);
                    if (i22 == i5 || b2 != list.get(i22 - 1).getByte(i10)) {
                        buffer.writeInt(b2 & 255);
                    }
                    if (i23 >= i4) {
                        break;
                    }
                    i22 = i23;
                }
            }
            Buffer buffer4 = new Buffer();
            while (i5 < i4) {
                byte b3 = list.get(i5).getByte(i10);
                int i24 = i5 + 1;
                if (i24 < i4) {
                    int i25 = i24;
                    while (true) {
                        int i26 = i25 + 1;
                        if (b3 != list.get(i25).getByte(i10)) {
                            i8 = i25;
                            break;
                        } else if (i26 >= i4) {
                            break;
                        } else {
                            i25 = i26;
                        }
                    }
                }
                i8 = i4;
                if (i24 == i8 && i10 + 1 == list.get(i5).size()) {
                    buffer.writeInt(list2.get(i5).intValue());
                    i9 = i8;
                    buffer2 = buffer4;
                } else {
                    buffer.writeInt(((int) (intCount2 + getIntCount(buffer4))) * i13);
                    i9 = i8;
                    buffer2 = buffer4;
                    buildTrieRecursive(intCount2, buffer4, i10 + 1, list, i5, i8, list2);
                }
                buffer4 = buffer2;
                i5 = i9;
                i13 = -1;
            }
            buffer.writeAll(buffer4);
        }

        private final long getIntCount(Buffer buffer) {
            return buffer.size() / 4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x00e4, code lost:
            continue;
         */
        @JvmStatic
        @NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Options of(@NotNull ByteString... byteStrings) {
            List mutableList;
            List mutableListOf;
            int binarySearch$default;
            Intrinsics.checkNotNullParameter(byteStrings, "byteStrings");
            int i2 = 0;
            if (byteStrings.length == 0) {
                return new Options(new ByteString[0], new int[]{0, -1}, null);
            }
            mutableList = ArraysKt___ArraysKt.toMutableList(byteStrings);
            CollectionsKt__MutableCollectionsJVMKt.sort(mutableList);
            ArrayList arrayList = new ArrayList(byteStrings.length);
            for (ByteString byteString : byteStrings) {
                arrayList.add(-1);
            }
            Object[] array = arrayList.toArray(new Integer[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            Integer[] numArr = (Integer[]) array;
            mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(Arrays.copyOf(numArr, numArr.length));
            int length = byteStrings.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                binarySearch$default = CollectionsKt__CollectionsKt.binarySearch$default(mutableList, byteStrings[i3], 0, 0, 6, (Object) null);
                mutableListOf.set(binarySearch$default, Integer.valueOf(i4));
                i3++;
                i4++;
            }
            if (((ByteString) mutableList.get(0)).size() > 0) {
                int i5 = 0;
                while (i5 < mutableList.size()) {
                    ByteString byteString2 = (ByteString) mutableList.get(i5);
                    int i6 = i5 + 1;
                    int i7 = i6;
                    while (i7 < mutableList.size()) {
                        ByteString byteString3 = (ByteString) mutableList.get(i7);
                        if (!byteString3.startsWith(byteString2)) {
                            break;
                        }
                        if (!(byteString3.size() != byteString2.size())) {
                            throw new IllegalArgumentException(Intrinsics.stringPlus("duplicate option: ", byteString3).toString());
                        }
                        if (((Number) mutableListOf.get(i7)).intValue() > ((Number) mutableListOf.get(i5)).intValue()) {
                            mutableList.remove(i7);
                            mutableListOf.remove(i7);
                        } else {
                            i7++;
                        }
                    }
                    i5 = i6;
                }
                Buffer buffer = new Buffer();
                a(this, 0L, buffer, 0, mutableList, 0, 0, mutableListOf, 53, null);
                int[] iArr = new int[(int) getIntCount(buffer)];
                while (!buffer.exhausted()) {
                    iArr[i2] = buffer.readInt();
                    i2++;
                }
                Object[] copyOf = Arrays.copyOf(byteStrings, byteStrings.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
                return new Options((ByteString[]) copyOf, iArr, null);
            }
            throw new IllegalArgumentException("the empty byte string is not a supported option".toString());
        }
    }

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    public /* synthetic */ Options(ByteString[] byteStringArr, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, iArr);
    }

    @JvmStatic
    @NotNull
    public static final Options of(@NotNull ByteString... byteStringArr) {
        return Companion.of(byteStringArr);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return contains((ByteString) obj);
        }
        return false;
    }

    public /* bridge */ boolean contains(ByteString byteString) {
        return super.contains((Options) byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public ByteString get(int i2) {
        return this.byteStrings[i2];
    }

    @NotNull
    public final ByteString[] getByteStrings$okio() {
        return this.byteStrings;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.byteStrings.length;
    }

    @NotNull
    public final int[] getTrie$okio() {
        return this.trie;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return indexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int indexOf(ByteString byteString) {
        return super.indexOf((Options) byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return lastIndexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(ByteString byteString) {
        return super.lastIndexOf((Options) byteString);
    }
}
