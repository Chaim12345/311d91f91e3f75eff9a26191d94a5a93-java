package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.SendChannel;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface ProducerScope<E> extends CoroutineScope, SendChannel<E> {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
        public static <E> boolean offer(@NotNull ProducerScope<? super E> producerScope, E e2) {
            return SendChannel.DefaultImpls.offer(producerScope, e2);
        }
    }

    @NotNull
    SendChannel<E> getChannel();
}
