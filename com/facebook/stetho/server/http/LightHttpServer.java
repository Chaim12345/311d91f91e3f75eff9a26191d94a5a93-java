package com.facebook.stetho.server.http;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.facebook.stetho.server.LeakyBufferedInputStream;
import com.facebook.stetho.server.SocketLike;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.http.protocol.HTTP;
/* loaded from: classes.dex */
public class LightHttpServer {
    private static final String TAG = "LightHttpServer";
    private final HandlerRegistry mHandlerRegistry;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class HttpMessageReader {
        private final BufferedInputStream mIn;
        private final StringBuilder mBuffer = new StringBuilder();
        private final NewLineDetector mNewLineDetector = new NewLineDetector();

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class NewLineDetector {
            private static final int STATE_ON_CR = 2;
            private static final int STATE_ON_CRLF = 3;
            private static final int STATE_ON_OTHER = 1;
            private int state;

            private NewLineDetector() {
                this.state = 1;
            }

            /* JADX WARN: Code restructure failed: missing block: B:7:0x000d, code lost:
                if (r6 == '\r') goto L10;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void accept(char c2) {
                int i2 = this.state;
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            throw new IllegalArgumentException("Unknown state: " + this.state);
                        }
                    } else if (c2 == '\n') {
                        this.state = 3;
                        return;
                    }
                    this.state = 1;
                    return;
                } else if (c2 != '\r') {
                    return;
                }
                this.state = 2;
            }

            public int state() {
                return this.state;
            }
        }

        public HttpMessageReader(BufferedInputStream bufferedInputStream) {
            this.mIn = bufferedInputStream;
        }

        @Nullable
        public String readLine() {
            while (true) {
                int read = this.mIn.read();
                if (read < 0) {
                    return null;
                }
                char c2 = (char) read;
                this.mNewLineDetector.accept(c2);
                int state = this.mNewLineDetector.state();
                if (state == 1) {
                    this.mBuffer.append(c2);
                } else if (state == 3) {
                    String sb = this.mBuffer.toString();
                    this.mBuffer.setLength(0);
                    return sb;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class HttpMessageWriter {
        private static final byte[] CRLF = "\r\n".getBytes();
        private final BufferedOutputStream mOut;

        public HttpMessageWriter(BufferedOutputStream bufferedOutputStream) {
            this.mOut = bufferedOutputStream;
        }

        public void flush() {
            this.mOut.flush();
        }

        public void writeLine() {
            this.mOut.write(CRLF);
        }

        public void writeLine(String str) {
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                this.mOut.write(str.charAt(i2));
            }
            this.mOut.write(CRLF);
        }
    }

    public LightHttpServer(HandlerRegistry handlerRegistry) {
        this.mHandlerRegistry = handlerRegistry;
    }

    private boolean dispatchToHandler(SocketLike socketLike, LightHttpRequest lightHttpRequest, LightHttpResponse lightHttpResponse) {
        String stringWriter;
        HttpHandler lookup = this.mHandlerRegistry.lookup(lightHttpRequest.uri.getPath());
        if (lookup == null) {
            lightHttpResponse.code = 404;
            lightHttpResponse.reasonPhrase = "Not found";
            stringWriter = "No handler found\n";
        } else {
            try {
                return lookup.handleRequest(socketLike, lightHttpRequest, lightHttpResponse);
            } catch (RuntimeException e2) {
                lightHttpResponse.code = 500;
                lightHttpResponse.reasonPhrase = "Internal Server Error";
                StringWriter stringWriter2 = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter2);
                try {
                    e2.printStackTrace(printWriter);
                    printWriter.close();
                    stringWriter = stringWriter2.toString();
                } catch (Throwable th) {
                    printWriter.close();
                    throw th;
                }
            }
        }
        lightHttpResponse.body = LightHttpBody.create(stringWriter, HTTP.PLAIN_TEXT_TYPE);
        return true;
    }

    private static void readHeaders(LightHttpMessage lightHttpMessage, HttpMessageReader httpMessageReader) {
        while (true) {
            String readLine = httpMessageReader.readLine();
            if (readLine == null) {
                throw new EOFException();
            }
            if ("".equals(readLine)) {
                return;
            }
            String[] split = readLine.split(": ", 2);
            if (split.length != 2) {
                throw new IOException("Malformed header: " + readLine);
            }
            String str = split[0];
            String str2 = split[1];
            lightHttpMessage.headerNames.add(str);
            lightHttpMessage.headerValues.add(str2);
        }
    }

    @Nullable
    private static LightHttpRequest readRequestMessage(LightHttpRequest lightHttpRequest, HttpMessageReader httpMessageReader) {
        lightHttpRequest.reset();
        String readLine = httpMessageReader.readLine();
        if (readLine == null) {
            return null;
        }
        String[] split = readLine.split(" ", 3);
        if (split.length != 3) {
            throw new IOException("Invalid request line: " + readLine);
        }
        lightHttpRequest.method = split[0];
        lightHttpRequest.uri = Uri.parse(split[1]);
        lightHttpRequest.protocol = split[2];
        readHeaders(lightHttpRequest, httpMessageReader);
        return lightHttpRequest;
    }

    private static void writeFullResponse(LightHttpResponse lightHttpResponse, HttpMessageWriter httpMessageWriter, OutputStream outputStream) {
        lightHttpResponse.prepare();
        writeResponseMessage(lightHttpResponse, httpMessageWriter);
        LightHttpBody lightHttpBody = lightHttpResponse.body;
        if (lightHttpBody != null) {
            lightHttpBody.writeTo(outputStream);
        }
    }

    public static void writeResponseMessage(LightHttpResponse lightHttpResponse, HttpMessageWriter httpMessageWriter) {
        httpMessageWriter.writeLine("HTTP/1.1 " + lightHttpResponse.code + " " + lightHttpResponse.reasonPhrase);
        int size = lightHttpResponse.headerNames.size();
        for (int i2 = 0; i2 < size; i2++) {
            httpMessageWriter.writeLine(lightHttpResponse.headerNames.get(i2) + ": " + lightHttpResponse.headerValues.get(i2));
        }
        httpMessageWriter.writeLine();
        httpMessageWriter.flush();
    }

    public void serve(SocketLike socketLike) {
        LeakyBufferedInputStream leakyBufferedInputStream = new LeakyBufferedInputStream(socketLike.getInput(), 1024);
        OutputStream output = socketLike.getOutput();
        HttpMessageReader httpMessageReader = new HttpMessageReader(leakyBufferedInputStream);
        HttpMessageWriter httpMessageWriter = new HttpMessageWriter(new BufferedOutputStream(output));
        SocketLike socketLike2 = new SocketLike(socketLike, leakyBufferedInputStream);
        LightHttpRequest lightHttpRequest = new LightHttpRequest();
        LightHttpResponse lightHttpResponse = new LightHttpResponse();
        while (true) {
            LightHttpRequest readRequestMessage = readRequestMessage(lightHttpRequest, httpMessageReader);
            if (readRequestMessage == null) {
                return;
            }
            lightHttpResponse.reset();
            if (!dispatchToHandler(socketLike2, readRequestMessage, lightHttpResponse)) {
                return;
            }
            writeFullResponse(lightHttpResponse, httpMessageWriter, output);
        }
    }
}
