package kotlinx.coroutines.flow.internal;

import java.util.Arrays;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public abstract class AbstractSharedFlow<S extends AbstractSharedFlowSlot<?>> {
    @Nullable
    private SubscriptionCountStateFlow _subscriptionCount;
    private int nCollectors;
    private int nextIndex;
    @Nullable
    private S[] slots;

    public static final /* synthetic */ int access$getNCollectors(AbstractSharedFlow abstractSharedFlow) {
        return abstractSharedFlow.nCollectors;
    }

    public static final /* synthetic */ AbstractSharedFlowSlot[] access$getSlots(AbstractSharedFlow abstractSharedFlow) {
        return abstractSharedFlow.slots;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    @NotNull
    public final AbstractSharedFlowSlot a() {
        S[] sArr;
        AbstractSharedFlowSlot abstractSharedFlowSlot;
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        synchronized (this) {
            S[] sArr2 = this.slots;
            if (sArr2 == null) {
                S[] sArr3 = (S[]) createSlotArray(2);
                this.slots = sArr3;
                sArr = sArr3;
            } else {
                int i2 = this.nCollectors;
                int length = sArr2.length;
                sArr = sArr2;
                if (i2 >= length) {
                    Object[] copyOf = Arrays.copyOf(sArr2, sArr2.length * 2);
                    Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                    this.slots = (S[]) ((AbstractSharedFlowSlot[]) copyOf);
                    sArr = (S[]) ((AbstractSharedFlowSlot[]) copyOf);
                }
            }
            int i3 = this.nextIndex;
            do {
                ?? r2 = sArr[i3];
                abstractSharedFlowSlot = r2;
                if (r2 == 0) {
                    AbstractSharedFlowSlot createSlot = createSlot();
                    sArr[i3] = createSlot;
                    abstractSharedFlowSlot = createSlot;
                }
                i3++;
                if (i3 >= sArr.length) {
                    i3 = 0;
                }
            } while (!abstractSharedFlowSlot.allocateLocked(this));
            this.nextIndex = i3;
            this.nCollectors++;
            subscriptionCountStateFlow = this._subscriptionCount;
        }
        if (subscriptionCountStateFlow != null) {
            subscriptionCountStateFlow.increment(1);
        }
        return abstractSharedFlowSlot;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(@NotNull AbstractSharedFlowSlot abstractSharedFlowSlot) {
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        int i2;
        Continuation<Unit>[] freeLocked;
        synchronized (this) {
            int i3 = this.nCollectors - 1;
            this.nCollectors = i3;
            subscriptionCountStateFlow = this._subscriptionCount;
            if (i3 == 0) {
                this.nextIndex = 0;
            }
            freeLocked = abstractSharedFlowSlot.freeLocked(this);
        }
        for (Continuation<Unit> continuation : freeLocked) {
            if (continuation != null) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m187constructorimpl(Unit.INSTANCE));
            }
        }
        if (subscriptionCountStateFlow != null) {
            subscriptionCountStateFlow.increment(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int c() {
        return this.nCollectors;
    }

    @NotNull
    protected abstract AbstractSharedFlowSlot createSlot();

    @NotNull
    protected abstract AbstractSharedFlowSlot[] createSlotArray(int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final AbstractSharedFlowSlot[] d() {
        return this.slots;
    }

    @NotNull
    public final StateFlow<Integer> getSubscriptionCount() {
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        synchronized (this) {
            subscriptionCountStateFlow = this._subscriptionCount;
            if (subscriptionCountStateFlow == null) {
                subscriptionCountStateFlow = new SubscriptionCountStateFlow(this.nCollectors);
                this._subscriptionCount = subscriptionCountStateFlow;
            }
        }
        return subscriptionCountStateFlow;
    }
}
