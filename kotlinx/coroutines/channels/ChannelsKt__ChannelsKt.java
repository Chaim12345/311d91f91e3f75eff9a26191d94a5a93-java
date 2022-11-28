package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.channels.ChannelResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final /* synthetic */ class ChannelsKt__ChannelsKt {
    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySendBlocking'. Consider handling the result of 'trySendBlocking' explicitly and rethrow exception if necessary", replaceWith = @ReplaceWith(expression = "trySendBlocking(element)", imports = {}))
    public static final <E> void sendBlocking(@NotNull SendChannel<? super E> sendChannel, E e2) {
        if (ChannelResult.m1644isSuccessimpl(sendChannel.mo1629trySendJP2dKIU(e2))) {
            return;
        }
        BuildersKt__BuildersKt.runBlocking$default(null, new ChannelsKt__ChannelsKt$sendBlocking$1(sendChannel, e2, null), 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <E> Object trySendBlocking(@NotNull SendChannel<? super E> sendChannel, E e2) {
        Object runBlocking$default;
        Object mo1629trySendJP2dKIU = sendChannel.mo1629trySendJP2dKIU(e2);
        if (mo1629trySendJP2dKIU instanceof ChannelResult.Failed) {
            runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new ChannelsKt__ChannelsKt$trySendBlocking$2(sendChannel, e2, null), 1, null);
            return ((ChannelResult) runBlocking$default).m1646unboximpl();
        }
        Unit unit = (Unit) mo1629trySendJP2dKIU;
        return ChannelResult.Companion.m1649successJP2dKIU(Unit.INSTANCE);
    }
}
