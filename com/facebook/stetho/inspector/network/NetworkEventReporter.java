package com.facebook.stetho.inspector.network;

import java.io.InputStream;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
public interface NetworkEventReporter {

    /* loaded from: classes.dex */
    public interface InspectorHeaders {
        @Nullable
        String firstHeaderValue(String str);

        int headerCount();

        String headerName(int i2);

        String headerValue(int i2);
    }

    /* loaded from: classes.dex */
    public interface InspectorRequest extends InspectorRequestCommon {
        @Nullable
        byte[] body();

        @Nullable
        Integer friendlyNameExtra();

        String method();

        String url();
    }

    /* loaded from: classes.dex */
    public interface InspectorRequestCommon extends InspectorHeaders {
        String friendlyName();

        String id();
    }

    /* loaded from: classes.dex */
    public interface InspectorResponse extends InspectorResponseCommon {
        int connectionId();

        boolean connectionReused();

        boolean fromDiskCache();

        String url();
    }

    /* loaded from: classes.dex */
    public interface InspectorResponseCommon extends InspectorHeaders {
        String reasonPhrase();

        String requestId();

        int statusCode();
    }

    /* loaded from: classes.dex */
    public interface InspectorWebSocketFrame {
        public static final int OPCODE_BINARY = 2;
        public static final int OPCODE_CONNECTION_CLOSE = 8;
        public static final int OPCODE_CONTINUATION = 0;
        public static final int OPCODE_PING = 9;
        public static final int OPCODE_PONG = 10;
        public static final int OPCODE_TEXT = 1;

        boolean mask();

        int opcode();

        String payloadData();

        String requestId();
    }

    /* loaded from: classes.dex */
    public interface InspectorWebSocketRequest extends InspectorRequestCommon {
    }

    /* loaded from: classes.dex */
    public interface InspectorWebSocketResponse extends InspectorResponseCommon {
        @Nullable
        InspectorHeaders requestHeaders();
    }

    void dataReceived(String str, int i2, int i3);

    void dataSent(String str, int i2, int i3);

    void httpExchangeFailed(String str, String str2);

    @Nullable
    InputStream interpretResponseStream(String str, @Nullable String str2, @Nullable String str3, @Nullable InputStream inputStream, ResponseHandler responseHandler);

    boolean isEnabled();

    String nextRequestId();

    void requestWillBeSent(InspectorRequest inspectorRequest);

    void responseHeadersReceived(InspectorResponse inspectorResponse);

    void responseReadFailed(String str, String str2);

    void responseReadFinished(String str);

    void webSocketClosed(String str);

    void webSocketCreated(String str, String str2);

    void webSocketFrameError(String str, String str2);

    void webSocketFrameReceived(InspectorWebSocketFrame inspectorWebSocketFrame);

    void webSocketFrameSent(InspectorWebSocketFrame inspectorWebSocketFrame);

    void webSocketHandshakeResponseReceived(InspectorWebSocketResponse inspectorWebSocketResponse);

    void webSocketWillSendHandshakeRequest(InspectorWebSocketRequest inspectorWebSocketRequest);
}
