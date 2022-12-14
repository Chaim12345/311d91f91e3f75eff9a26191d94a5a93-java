package com.facebook.stetho.websocket;

import android.util.Base64;
import com.facebook.stetho.common.Utf8Charset;
import com.facebook.stetho.server.SocketLike;
import com.facebook.stetho.server.http.HttpHandler;
import com.facebook.stetho.server.http.LightHttpBody;
import com.facebook.stetho.server.http.LightHttpMessage;
import com.facebook.stetho.server.http.LightHttpRequest;
import com.facebook.stetho.server.http.LightHttpResponse;
import com.facebook.stetho.server.http.LightHttpServer;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import org.apache.http.protocol.HTTP;
/* loaded from: classes.dex */
public class WebSocketHandler implements HttpHandler {
    private static final String HEADER_CONNECTION = "Connection";
    private static final String HEADER_CONNECTION_UPGRADE = "Upgrade";
    private static final String HEADER_SEC_WEBSOCKET_ACCEPT = "Sec-WebSocket-Accept";
    private static final String HEADER_SEC_WEBSOCKET_KEY = "Sec-WebSocket-Key";
    private static final String HEADER_SEC_WEBSOCKET_PROTOCOL = "Sec-WebSocket-Protocol";
    private static final String HEADER_SEC_WEBSOCKET_VERSION = "Sec-WebSocket-Version";
    private static final String HEADER_SEC_WEBSOCKET_VERSION_13 = "13";
    private static final String HEADER_UPGRADE = "Upgrade";
    private static final String HEADER_UPGRADE_WEBSOCKET = "websocket";
    private static final String SERVER_KEY_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private final SimpleEndpoint mEndpoint;

    public WebSocketHandler(SimpleEndpoint simpleEndpoint) {
        this.mEndpoint = simpleEndpoint;
    }

    private void doUpgrade(SocketLike socketLike, LightHttpRequest lightHttpRequest, LightHttpResponse lightHttpResponse) {
        lightHttpResponse.code = 101;
        lightHttpResponse.reasonPhrase = "Switching Protocols";
        lightHttpResponse.addHeader("Upgrade", HEADER_UPGRADE_WEBSOCKET);
        lightHttpResponse.addHeader("Connection", "Upgrade");
        lightHttpResponse.body = null;
        String firstHeaderValue = getFirstHeaderValue(lightHttpRequest, "Sec-WebSocket-Key");
        if (firstHeaderValue != null) {
            lightHttpResponse.addHeader("Sec-WebSocket-Accept", generateServerKey(firstHeaderValue));
        }
        InputStream input = socketLike.getInput();
        OutputStream output = socketLike.getOutput();
        LightHttpServer.writeResponseMessage(lightHttpResponse, new LightHttpServer.HttpMessageWriter(new BufferedOutputStream(output)));
        new WebSocketSession(input, output, this.mEndpoint).handle();
    }

    private static String generateServerKey(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(Utf8Charset.encodeUTF8(str + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"));
            return Base64.encodeToString(messageDigest.digest(), 2);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Nullable
    private static String getFirstHeaderValue(LightHttpMessage lightHttpMessage, String str) {
        return lightHttpMessage.getFirstHeaderValue(str);
    }

    private static boolean isSupportableUpgradeRequest(LightHttpRequest lightHttpRequest) {
        return HEADER_UPGRADE_WEBSOCKET.equalsIgnoreCase(getFirstHeaderValue(lightHttpRequest, "Upgrade")) && "Upgrade".equals(getFirstHeaderValue(lightHttpRequest, "Connection")) && HEADER_SEC_WEBSOCKET_VERSION_13.equals(getFirstHeaderValue(lightHttpRequest, "Sec-WebSocket-Version"));
    }

    @Override // com.facebook.stetho.server.http.HttpHandler
    public boolean handleRequest(SocketLike socketLike, LightHttpRequest lightHttpRequest, LightHttpResponse lightHttpResponse) {
        if (isSupportableUpgradeRequest(lightHttpRequest)) {
            doUpgrade(socketLike, lightHttpRequest, lightHttpResponse);
            return false;
        }
        lightHttpResponse.code = 501;
        lightHttpResponse.reasonPhrase = "Not Implemented";
        lightHttpResponse.body = LightHttpBody.create("Not a supported WebSocket upgrade request\n", HTTP.PLAIN_TEXT_TYPE);
        return true;
    }
}
