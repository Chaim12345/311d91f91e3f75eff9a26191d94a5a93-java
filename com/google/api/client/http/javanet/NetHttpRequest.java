package com.google.api.client.http.javanet;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes2.dex */
final class NetHttpRequest extends LowLevelHttpRequest {
    private static final OutputWriter DEFAULT_CONNECTION_WRITER = new DefaultOutputWriter();
    private final HttpURLConnection connection;
    private int writeTimeout = 0;

    /* loaded from: classes2.dex */
    static class DefaultOutputWriter implements OutputWriter {
        DefaultOutputWriter() {
        }

        @Override // com.google.api.client.http.javanet.NetHttpRequest.OutputWriter
        public void write(OutputStream outputStream, StreamingContent streamingContent) {
            streamingContent.writeTo(outputStream);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface OutputWriter {
        void write(OutputStream outputStream, StreamingContent streamingContent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NetHttpRequest(HttpURLConnection httpURLConnection) {
        this.connection = httpURLConnection;
        httpURLConnection.setInstanceFollowRedirects(false);
    }

    private boolean hasResponse(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getResponseCode() > 0;
        } catch (IOException unused) {
            return false;
        }
    }

    private void writeContentToOutputStream(final OutputWriter outputWriter, final OutputStream outputStream) {
        if (this.writeTimeout == 0) {
            outputWriter.write(outputStream, getStreamingContent());
            return;
        }
        final StreamingContent streamingContent = getStreamingContent();
        Callable<Boolean> callable = new Callable<Boolean>(this) { // from class: com.google.api.client.http.javanet.NetHttpRequest.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() {
                outputWriter.write(outputStream, streamingContent);
                return Boolean.TRUE;
            }
        };
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new FutureTask(callable), null);
        newSingleThreadExecutor.shutdown();
        try {
            submit.get(this.writeTimeout, TimeUnit.MILLISECONDS);
            if (newSingleThreadExecutor.isTerminated()) {
                return;
            }
            newSingleThreadExecutor.shutdown();
        } catch (InterruptedException e2) {
            throw new IOException("Socket write interrupted", e2);
        } catch (ExecutionException e3) {
            throw new IOException("Exception in socket write", e3);
        } catch (TimeoutException e4) {
            throw new IOException("Socket write timed out", e4);
        }
    }

    @VisibleForTesting
    LowLevelHttpResponse a(OutputWriter outputWriter) {
        HttpURLConnection httpURLConnection = this.connection;
        if (getStreamingContent() != null) {
            String contentType = getContentType();
            if (contentType != null) {
                addHeader("Content-Type", contentType);
            }
            String contentEncoding = getContentEncoding();
            if (contentEncoding != null) {
                addHeader("Content-Encoding", contentEncoding);
            }
            long contentLength = getContentLength();
            int i2 = (contentLength > 0L ? 1 : (contentLength == 0L ? 0 : -1));
            if (i2 >= 0) {
                httpURLConnection.setRequestProperty("Content-Length", Long.toString(contentLength));
            }
            String requestMethod = httpURLConnection.getRequestMethod();
            if ("POST".equals(requestMethod) || "PUT".equals(requestMethod)) {
                httpURLConnection.setDoOutput(true);
                if (i2 < 0 || contentLength > 2147483647L) {
                    httpURLConnection.setChunkedStreamingMode(0);
                } else {
                    httpURLConnection.setFixedLengthStreamingMode((int) contentLength);
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    try {
                        writeContentToOutputStream(outputWriter, outputStream);
                        try {
                            outputStream.close();
                        } catch (IOException e2) {
                            throw e2;
                        }
                    } catch (IOException e3) {
                        if (!hasResponse(httpURLConnection)) {
                            throw e3;
                        }
                        try {
                            outputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                } catch (Throwable th) {
                    try {
                        outputStream.close();
                    } catch (IOException unused2) {
                    }
                    throw th;
                }
            } else {
                Preconditions.checkArgument(i2 == 0, "%s with non-zero content length is not supported", requestMethod);
            }
        }
        try {
            httpURLConnection.connect();
            return new NetHttpResponse(httpURLConnection);
        } catch (Throwable th2) {
            httpURLConnection.disconnect();
            throw th2;
        }
    }

    @Override // com.google.api.client.http.LowLevelHttpRequest
    public void addHeader(String str, String str2) {
        this.connection.addRequestProperty(str, str2);
    }

    @Override // com.google.api.client.http.LowLevelHttpRequest
    public LowLevelHttpResponse execute() {
        return a(DEFAULT_CONNECTION_WRITER);
    }

    @Override // com.google.api.client.http.LowLevelHttpRequest
    public void setTimeout(int i2, int i3) {
        this.connection.setReadTimeout(i3);
        this.connection.setConnectTimeout(i2);
    }

    @Override // com.google.api.client.http.LowLevelHttpRequest
    public void setWriteTimeout(int i2) {
        this.writeTimeout = i2;
    }
}
