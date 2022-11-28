package androidx.recyclerview.widget;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class DiffUtil {
    private static final Comparator<Diagonal> DIAGONAL_COMPARATOR = new Comparator<Diagonal>() { // from class: androidx.recyclerview.widget.DiffUtil.1
        @Override // java.util.Comparator
        public int compare(Diagonal diagonal, Diagonal diagonal2) {
            return diagonal.x - diagonal2.x;
        }
    };

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i2, int i3);

        public abstract boolean areItemsTheSame(int i2, int i3);

        @Nullable
        public Object getChangePayload(int i2, int i3) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CenteredArray {
        private final int[] mData;
        private final int mMid;

        CenteredArray(int i2) {
            int[] iArr = new int[i2];
            this.mData = iArr;
            this.mMid = iArr.length / 2;
        }

        int[] a() {
            return this.mData;
        }

        int b(int i2) {
            return this.mData[i2 + this.mMid];
        }

        void c(int i2, int i3) {
            this.mData[i2 + this.mMid] = i3;
        }

        public void fill(int i2) {
            Arrays.fill(this.mData, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Diagonal {
        public final int size;
        public final int x;
        public final int y;

        Diagonal(int i2, int i3, int i4) {
            this.x = i2;
            this.y = i3;
            this.size = i4;
        }

        int a() {
            return this.x + this.size;
        }

        int b() {
            return this.y + this.size;
        }
    }

    /* loaded from: classes.dex */
    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_MASK = 15;
        private static final int FLAG_MOVED = 12;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 4;
        public static final int NO_POSITION = -1;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final List<Diagonal> mDiagonals;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;

        DiffResult(Callback callback, List list, int[] iArr, int[] iArr2, boolean z) {
            this.mDiagonals = list;
            this.mOldItemStatuses = iArr;
            this.mNewItemStatuses = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 0);
            this.mCallback = callback;
            this.mOldListSize = callback.getOldListSize();
            this.mNewListSize = callback.getNewListSize();
            this.mDetectMoves = z;
            addEdgeDiagonals();
            findMatchingItems();
        }

        private void addEdgeDiagonals() {
            Diagonal diagonal = this.mDiagonals.isEmpty() ? null : this.mDiagonals.get(0);
            if (diagonal == null || diagonal.x != 0 || diagonal.y != 0) {
                this.mDiagonals.add(0, new Diagonal(0, 0, 0));
            }
            this.mDiagonals.add(new Diagonal(this.mOldListSize, this.mNewListSize, 0));
        }

        private void findMatchingAddition(int i2) {
            int size = this.mDiagonals.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                Diagonal diagonal = this.mDiagonals.get(i4);
                while (i3 < diagonal.y) {
                    if (this.mNewItemStatuses[i3] == 0 && this.mCallback.areItemsTheSame(i2, i3)) {
                        int i5 = this.mCallback.areContentsTheSame(i2, i3) ? 8 : 4;
                        this.mOldItemStatuses[i2] = (i3 << 4) | i5;
                        this.mNewItemStatuses[i3] = (i2 << 4) | i5;
                        return;
                    }
                    i3++;
                }
                i3 = diagonal.b();
            }
        }

        private void findMatchingItems() {
            for (Diagonal diagonal : this.mDiagonals) {
                for (int i2 = 0; i2 < diagonal.size; i2++) {
                    int i3 = diagonal.x + i2;
                    int i4 = diagonal.y + i2;
                    int i5 = this.mCallback.areContentsTheSame(i3, i4) ? 1 : 2;
                    this.mOldItemStatuses[i3] = (i4 << 4) | i5;
                    this.mNewItemStatuses[i4] = (i3 << 4) | i5;
                }
            }
            if (this.mDetectMoves) {
                findMoveMatches();
            }
        }

        private void findMoveMatches() {
            int i2 = 0;
            for (Diagonal diagonal : this.mDiagonals) {
                while (i2 < diagonal.x) {
                    if (this.mOldItemStatuses[i2] == 0) {
                        findMatchingAddition(i2);
                    }
                    i2++;
                }
                i2 = diagonal.a();
            }
        }

        @Nullable
        private static PostponedUpdate getPostponedUpdate(Collection<PostponedUpdate> collection, int i2, boolean z) {
            PostponedUpdate postponedUpdate;
            Iterator<PostponedUpdate> it = collection.iterator();
            while (true) {
                if (!it.hasNext()) {
                    postponedUpdate = null;
                    break;
                }
                postponedUpdate = it.next();
                if (postponedUpdate.f3554a == i2 && postponedUpdate.f3556c == z) {
                    it.remove();
                    break;
                }
            }
            while (it.hasNext()) {
                PostponedUpdate next = it.next();
                int i3 = next.f3555b;
                next.f3555b = z ? i3 - 1 : i3 + 1;
            }
            return postponedUpdate;
        }

        public int convertNewPositionToOld(@IntRange(from = 0) int i2) {
            if (i2 >= 0 && i2 < this.mNewListSize) {
                int i3 = this.mNewItemStatuses[i2];
                if ((i3 & 15) == 0) {
                    return -1;
                }
                return i3 >> 4;
            }
            throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i2 + ", new list size = " + this.mNewListSize);
        }

        public int convertOldPositionToNew(@IntRange(from = 0) int i2) {
            if (i2 >= 0 && i2 < this.mOldListSize) {
                int i3 = this.mOldItemStatuses[i2];
                if ((i3 & 15) == 0) {
                    return -1;
                }
                return i3 >> 4;
            }
            throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i2 + ", old list size = " + this.mOldListSize);
        }

        public void dispatchUpdatesTo(@NonNull ListUpdateCallback listUpdateCallback) {
            int i2;
            BatchingListUpdateCallback batchingListUpdateCallback = listUpdateCallback instanceof BatchingListUpdateCallback ? (BatchingListUpdateCallback) listUpdateCallback : new BatchingListUpdateCallback(listUpdateCallback);
            int i3 = this.mOldListSize;
            ArrayDeque arrayDeque = new ArrayDeque();
            int i4 = this.mOldListSize;
            int i5 = this.mNewListSize;
            for (int size = this.mDiagonals.size() - 1; size >= 0; size--) {
                Diagonal diagonal = this.mDiagonals.get(size);
                int a2 = diagonal.a();
                int b2 = diagonal.b();
                while (true) {
                    if (i4 <= a2) {
                        break;
                    }
                    i4--;
                    int i6 = this.mOldItemStatuses[i4];
                    if ((i6 & 12) != 0) {
                        int i7 = i6 >> 4;
                        PostponedUpdate postponedUpdate = getPostponedUpdate(arrayDeque, i7, false);
                        if (postponedUpdate != null) {
                            int i8 = (i3 - postponedUpdate.f3555b) - 1;
                            batchingListUpdateCallback.onMoved(i4, i8);
                            if ((i6 & 4) != 0) {
                                batchingListUpdateCallback.onChanged(i8, 1, this.mCallback.getChangePayload(i4, i7));
                            }
                        } else {
                            arrayDeque.add(new PostponedUpdate(i4, (i3 - i4) - 1, true));
                        }
                    } else {
                        batchingListUpdateCallback.onRemoved(i4, 1);
                        i3--;
                    }
                }
                while (i5 > b2) {
                    i5--;
                    int i9 = this.mNewItemStatuses[i5];
                    if ((i9 & 12) != 0) {
                        int i10 = i9 >> 4;
                        PostponedUpdate postponedUpdate2 = getPostponedUpdate(arrayDeque, i10, true);
                        if (postponedUpdate2 == null) {
                            arrayDeque.add(new PostponedUpdate(i5, i3 - i4, false));
                        } else {
                            batchingListUpdateCallback.onMoved((i3 - postponedUpdate2.f3555b) - 1, i4);
                            if ((i9 & 4) != 0) {
                                batchingListUpdateCallback.onChanged(i4, 1, this.mCallback.getChangePayload(i10, i5));
                            }
                        }
                    } else {
                        batchingListUpdateCallback.onInserted(i4, 1);
                        i3++;
                    }
                }
                int i11 = diagonal.x;
                int i12 = diagonal.y;
                for (i2 = 0; i2 < diagonal.size; i2++) {
                    if ((this.mOldItemStatuses[i11] & 15) == 2) {
                        batchingListUpdateCallback.onChanged(i11, 1, this.mCallback.getChangePayload(i11, i12));
                    }
                    i11++;
                    i12++;
                }
                i4 = diagonal.x;
                i5 = diagonal.y;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }

        public void dispatchUpdatesTo(@NonNull RecyclerView.Adapter adapter) {
            dispatchUpdatesTo(new AdapterListUpdateCallback(adapter));
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(@NonNull T t2, @NonNull T t3);

        public abstract boolean areItemsTheSame(@NonNull T t2, @NonNull T t3);

        @Nullable
        public Object getChangePayload(@NonNull T t2, @NonNull T t3) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PostponedUpdate {

        /* renamed from: a  reason: collision with root package name */
        int f3554a;

        /* renamed from: b  reason: collision with root package name */
        int f3555b;

        /* renamed from: c  reason: collision with root package name */
        boolean f3556c;

        PostponedUpdate(int i2, int i3, boolean z) {
            this.f3554a = i2;
            this.f3555b = i3;
            this.f3556c = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Range {

        /* renamed from: a  reason: collision with root package name */
        int f3557a;

        /* renamed from: b  reason: collision with root package name */
        int f3558b;

        /* renamed from: c  reason: collision with root package name */
        int f3559c;

        /* renamed from: d  reason: collision with root package name */
        int f3560d;

        public Range() {
        }

        public Range(int i2, int i3, int i4, int i5) {
            this.f3557a = i2;
            this.f3558b = i3;
            this.f3559c = i4;
            this.f3560d = i5;
        }

        int a() {
            return this.f3560d - this.f3559c;
        }

        int b() {
            return this.f3558b - this.f3557a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Snake {
        public int endX;
        public int endY;
        public boolean reverse;
        public int startX;
        public int startY;

        Snake() {
        }

        int a() {
            return Math.min(this.endX - this.startX, this.endY - this.startY);
        }

        boolean b() {
            return this.endY - this.startY != this.endX - this.startX;
        }

        boolean c() {
            return this.endY - this.startY > this.endX - this.startX;
        }

        @NonNull
        Diagonal d() {
            if (b()) {
                return this.reverse ? new Diagonal(this.startX, this.startY, a()) : c() ? new Diagonal(this.startX, this.startY + 1, a()) : new Diagonal(this.startX + 1, this.startY, a());
            }
            int i2 = this.startX;
            return new Diagonal(i2, this.startY, this.endX - i2);
        }
    }

    private DiffUtil() {
    }

    @Nullable
    private static Snake backward(Range range, Callback callback, CenteredArray centeredArray, CenteredArray centeredArray2, int i2) {
        int b2;
        int i3;
        int i4;
        boolean z = (range.b() - range.a()) % 2 == 0;
        int b3 = range.b() - range.a();
        int i5 = -i2;
        for (int i6 = i5; i6 <= i2; i6 += 2) {
            if (i6 == i5 || (i6 != i2 && centeredArray2.b(i6 + 1) < centeredArray2.b(i6 - 1))) {
                b2 = centeredArray2.b(i6 + 1);
                i3 = b2;
            } else {
                b2 = centeredArray2.b(i6 - 1);
                i3 = b2 - 1;
            }
            int i7 = range.f3560d - ((range.f3558b - i3) - i6);
            int i8 = (i2 == 0 || i3 != b2) ? i7 : i7 + 1;
            while (i3 > range.f3557a && i7 > range.f3559c && callback.areItemsTheSame(i3 - 1, i7 - 1)) {
                i3--;
                i7--;
            }
            centeredArray2.c(i6, i3);
            if (z && (i4 = b3 - i6) >= i5 && i4 <= i2 && centeredArray.b(i4) >= i3) {
                Snake snake = new Snake();
                snake.startX = i3;
                snake.startY = i7;
                snake.endX = b2;
                snake.endY = i8;
                snake.reverse = true;
                return snake;
            }
        }
        return null;
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback callback) {
        return calculateDiff(callback, true);
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback callback, boolean z) {
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Range(0, oldListSize, 0, newListSize));
        int i2 = ((((oldListSize + newListSize) + 1) / 2) * 2) + 1;
        CenteredArray centeredArray = new CenteredArray(i2);
        CenteredArray centeredArray2 = new CenteredArray(i2);
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            Range range = (Range) arrayList2.remove(arrayList2.size() - 1);
            Snake midPoint = midPoint(range, callback, centeredArray, centeredArray2);
            if (midPoint != null) {
                if (midPoint.a() > 0) {
                    arrayList.add(midPoint.d());
                }
                Range range2 = arrayList3.isEmpty() ? new Range() : (Range) arrayList3.remove(arrayList3.size() - 1);
                range2.f3557a = range.f3557a;
                range2.f3559c = range.f3559c;
                range2.f3558b = midPoint.startX;
                range2.f3560d = midPoint.startY;
                arrayList2.add(range2);
                range.f3558b = range.f3558b;
                range.f3560d = range.f3560d;
                range.f3557a = midPoint.endX;
                range.f3559c = midPoint.endY;
                arrayList2.add(range);
            } else {
                arrayList3.add(range);
            }
        }
        Collections.sort(arrayList, DIAGONAL_COMPARATOR);
        return new DiffResult(callback, arrayList, centeredArray.a(), centeredArray2.a(), z);
    }

    @Nullable
    private static Snake forward(Range range, Callback callback, CenteredArray centeredArray, CenteredArray centeredArray2, int i2) {
        int b2;
        int i3;
        int i4;
        boolean z = Math.abs(range.b() - range.a()) % 2 == 1;
        int b3 = range.b() - range.a();
        int i5 = -i2;
        for (int i6 = i5; i6 <= i2; i6 += 2) {
            if (i6 == i5 || (i6 != i2 && centeredArray.b(i6 + 1) > centeredArray.b(i6 - 1))) {
                b2 = centeredArray.b(i6 + 1);
                i3 = b2;
            } else {
                b2 = centeredArray.b(i6 - 1);
                i3 = b2 + 1;
            }
            int i7 = (range.f3559c + (i3 - range.f3557a)) - i6;
            int i8 = (i2 == 0 || i3 != b2) ? i7 : i7 - 1;
            while (i3 < range.f3558b && i7 < range.f3560d && callback.areItemsTheSame(i3, i7)) {
                i3++;
                i7++;
            }
            centeredArray.c(i6, i3);
            if (z && (i4 = b3 - i6) >= i5 + 1 && i4 <= i2 - 1 && centeredArray2.b(i4) <= i3) {
                Snake snake = new Snake();
                snake.startX = b2;
                snake.startY = i8;
                snake.endX = i3;
                snake.endY = i7;
                snake.reverse = false;
                return snake;
            }
        }
        return null;
    }

    @Nullable
    private static Snake midPoint(Range range, Callback callback, CenteredArray centeredArray, CenteredArray centeredArray2) {
        if (range.b() >= 1 && range.a() >= 1) {
            int b2 = ((range.b() + range.a()) + 1) / 2;
            centeredArray.c(1, range.f3557a);
            centeredArray2.c(1, range.f3558b);
            for (int i2 = 0; i2 < b2; i2++) {
                Snake forward = forward(range, callback, centeredArray, centeredArray2, i2);
                if (forward != null) {
                    return forward;
                }
                Snake backward = backward(range, callback, centeredArray, centeredArray2, i2);
                if (backward != null) {
                    return backward;
                }
            }
        }
        return null;
    }
}
