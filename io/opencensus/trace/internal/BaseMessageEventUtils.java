package io.opencensus.trace.internal;

import androidx.core.app.NotificationCompat;
import io.opencensus.internal.Utils;
import io.opencensus.trace.BaseMessageEvent;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.NetworkEvent;
/* loaded from: classes3.dex */
public final class BaseMessageEventUtils {
    private BaseMessageEventUtils() {
    }

    public static MessageEvent asMessageEvent(BaseMessageEvent baseMessageEvent) {
        Utils.checkNotNull(baseMessageEvent, NotificationCompat.CATEGORY_EVENT);
        if (baseMessageEvent instanceof MessageEvent) {
            return (MessageEvent) baseMessageEvent;
        }
        NetworkEvent networkEvent = (NetworkEvent) baseMessageEvent;
        return MessageEvent.builder(networkEvent.getType() == NetworkEvent.Type.RECV ? MessageEvent.Type.RECEIVED : MessageEvent.Type.SENT, networkEvent.getMessageId()).setUncompressedMessageSize(networkEvent.getUncompressedMessageSize()).setCompressedMessageSize(networkEvent.getCompressedMessageSize()).build();
    }

    public static NetworkEvent asNetworkEvent(BaseMessageEvent baseMessageEvent) {
        Utils.checkNotNull(baseMessageEvent, NotificationCompat.CATEGORY_EVENT);
        if (baseMessageEvent instanceof NetworkEvent) {
            return (NetworkEvent) baseMessageEvent;
        }
        MessageEvent messageEvent = (MessageEvent) baseMessageEvent;
        return NetworkEvent.builder(messageEvent.getType() == MessageEvent.Type.RECEIVED ? NetworkEvent.Type.RECV : NetworkEvent.Type.SENT, messageEvent.getMessageId()).setUncompressedMessageSize(messageEvent.getUncompressedMessageSize()).setCompressedMessageSize(messageEvent.getCompressedMessageSize()).build();
    }
}
