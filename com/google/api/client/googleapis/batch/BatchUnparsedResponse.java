package com.google.api.client.googleapis.batch;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.util.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
/* loaded from: classes2.dex */
final class BatchUnparsedResponse {
    private final String boundary;
    private final InputStream inputStream;
    private final List<BatchRequest.RequestInfo<?, ?>> requestInfos;
    private final boolean retryAllowed;

    /* renamed from: a  reason: collision with root package name */
    boolean f8007a = true;

    /* renamed from: b  reason: collision with root package name */
    List f8008b = new ArrayList();
    private int contentId = 0;

    /* loaded from: classes2.dex */
    private static class FakeLowLevelHttpRequest extends LowLevelHttpRequest {
        private List<String> headerNames;
        private List<String> headerValues;
        private InputStream partContent;
        private int statusCode;

        FakeLowLevelHttpRequest(InputStream inputStream, int i2, List list, List list2) {
            this.partContent = inputStream;
            this.statusCode = i2;
            this.headerNames = list;
            this.headerValues = list2;
        }

        @Override // com.google.api.client.http.LowLevelHttpRequest
        public void addHeader(String str, String str2) {
        }

        @Override // com.google.api.client.http.LowLevelHttpRequest
        public LowLevelHttpResponse execute() {
            return new FakeLowLevelHttpResponse(this.partContent, this.statusCode, this.headerNames, this.headerValues);
        }
    }

    /* loaded from: classes2.dex */
    private static class FakeLowLevelHttpResponse extends LowLevelHttpResponse {
        private List<String> headerNames;
        private List<String> headerValues;
        private InputStream partContent;
        private int statusCode;

        FakeLowLevelHttpResponse(InputStream inputStream, int i2, List list, List list2) {
            this.headerNames = new ArrayList();
            this.headerValues = new ArrayList();
            this.partContent = inputStream;
            this.statusCode = i2;
            this.headerNames = list;
            this.headerValues = list2;
        }

        @Override // com.google.api.client.http.LowLevelHttpResponse
        public InputStream getContent() {
            return this.partContent;
        }

        @Override // com.google.api.client.http.LowLevelHttpResponse
        public String getContentEncoding() {
            return null;
        }

        @Override // com.google.api.client.http.LowLevelHttpResponse
        public long getContentLength() {
            return 0L;
        }

        @Override // com.google.api.client.http.LowLevelHttpResponse
        public String getContentType() {
            return null;
        }

        @Override // com.google.api.client.http.LowLevelHttpResponse
        public int getHeaderCount() {
            return this.headerNames.size();
        }

        @Override // com.google.api.client.http.LowLevelHttpResponse
        public String getHeaderName(int i2) {
            return this.headerNames.get(i2);
        }

        @Override // com.google.api.client.http.LowLevelHttpResponse
        public String getHeaderValue(int i2) {
            return this.headerValues.get(i2);
        }

        @Override // com.google.api.client.http.LowLevelHttpResponse
        public String getReasonPhrase() {
            return null;
        }

        @Override // com.google.api.client.http.LowLevelHttpResponse
        public int getStatusCode() {
            return this.statusCode;
        }

        @Override // com.google.api.client.http.LowLevelHttpResponse
        public String getStatusLine() {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FakeResponseHttpTransport extends HttpTransport {
        private List<String> headerNames;
        private List<String> headerValues;
        private InputStream partContent;
        private int statusCode;

        FakeResponseHttpTransport(int i2, InputStream inputStream, List list, List list2) {
            this.statusCode = i2;
            this.partContent = inputStream;
            this.headerNames = list;
            this.headerValues = list2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.client.http.HttpTransport
        public LowLevelHttpRequest buildRequest(String str, String str2) {
            return new FakeLowLevelHttpRequest(this.partContent, this.statusCode, this.headerNames, this.headerValues);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BatchUnparsedResponse(InputStream inputStream, String str, List list, boolean z) {
        this.boundary = str;
        this.requestInfos = list;
        this.retryAllowed = z;
        this.inputStream = inputStream;
        checkForFinalBoundary(readLine());
    }

    private void checkForFinalBoundary(String str) {
        if (str.equals(this.boundary + HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
            this.f8007a = false;
            this.inputStream.close();
        }
    }

    private HttpResponse getFakeResponse(int i2, InputStream inputStream, List<String> list, List<String> list2) {
        HttpRequest buildPostRequest = new FakeResponseHttpTransport(i2, inputStream, list, list2).createRequestFactory().buildPostRequest(new GenericUrl(HttpTesting.SIMPLE_URL), null);
        buildPostRequest.setLoggingEnabled(false);
        buildPostRequest.setThrowExceptionOnExecuteError(false);
        return buildPostRequest.execute();
    }

    private <A, T, E> A getParsedDataClass(Class<A> cls, HttpResponse httpResponse, BatchRequest.RequestInfo<T, E> requestInfo) {
        if (cls == Void.class) {
            return null;
        }
        return (A) requestInfo.f8006d.getParser().parseAndClose(httpResponse.getContent(), httpResponse.getContentCharset(), (Class<Object>) cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T, E> void parseAndCallback(BatchRequest.RequestInfo<T, E> requestInfo, int i2, HttpResponse httpResponse) {
        BatchCallback batchCallback = requestInfo.f8003a;
        HttpHeaders headers = httpResponse.getHeaders();
        HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler = requestInfo.f8006d.getUnsuccessfulResponseHandler();
        if (HttpStatusCodes.isSuccess(i2)) {
            if (batchCallback == 0) {
                return;
            }
            batchCallback.onSuccess(getParsedDataClass(requestInfo.f8004b, httpResponse, requestInfo), headers);
            return;
        }
        HttpContent content = requestInfo.f8006d.getContent();
        boolean z = true;
        boolean z2 = this.retryAllowed && (content == null || content.retrySupported());
        boolean handleResponse = unsuccessfulResponseHandler != null ? unsuccessfulResponseHandler.handleResponse(requestInfo.f8006d, httpResponse, z2) : false;
        if (handleResponse || !requestInfo.f8006d.handleRedirect(httpResponse.getStatusCode(), httpResponse.getHeaders())) {
            z = false;
        }
        if (z2 && (handleResponse || z)) {
            this.f8008b.add(requestInfo);
        } else if (batchCallback == 0) {
        } else {
            batchCallback.onFailure(getParsedDataClass(requestInfo.f8005c, httpResponse, requestInfo), headers);
        }
    }

    private String readLine() {
        return trimCrlf(readRawLine());
    }

    private String readRawLine() {
        int read = this.inputStream.read();
        if (read == -1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (read != -1) {
            sb.append((char) read);
            if (read == 10) {
                break;
            }
            read = this.inputStream.read();
        }
        return sb.toString();
    }

    private static InputStream trimCrlf(byte[] bArr) {
        int length = bArr.length;
        if (length > 0 && bArr[length - 1] == 10) {
            length--;
        }
        if (length > 0 && bArr[length - 1] == 13) {
            length--;
        }
        return new ByteArrayInputStream(bArr, 0, length);
    }

    private static String trimCrlf(String str) {
        int length;
        if (str.endsWith("\r\n")) {
            length = str.length() - 2;
        } else if (!str.endsWith("\n")) {
            return str;
        } else {
            length = str.length() - 1;
        }
        return str.substring(0, length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        String readLine;
        String readLine2;
        InputStream inputStream;
        String readRawLine;
        this.contentId++;
        do {
            readLine = readLine();
            if (readLine == null) {
                break;
            }
        } while (!readLine.equals(""));
        int parseInt = Integer.parseInt(readLine().split(" ")[1]);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        long j2 = -1;
        while (true) {
            readLine2 = readLine();
            if (readLine2 == null || readLine2.equals("")) {
                break;
            }
            String[] split = readLine2.split(": ", 2);
            String str = split[0];
            String str2 = split[1];
            arrayList.add(str);
            arrayList2.add(str2);
            if ("Content-Length".equalsIgnoreCase(str.trim())) {
                j2 = Long.parseLong(str2);
            }
        }
        int i2 = (j2 > (-1L) ? 1 : (j2 == (-1L) ? 0 : -1));
        if (i2 == 0) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                readRawLine = readRawLine();
                if (readRawLine == null || readRawLine.startsWith(this.boundary)) {
                    break;
                }
                byteArrayOutputStream.write(readRawLine.getBytes("ISO-8859-1"));
            }
            inputStream = trimCrlf(byteArrayOutputStream.toByteArray());
            readLine2 = trimCrlf(readRawLine);
        } else {
            inputStream = new FilterInputStream(this, ByteStreams.limit(this.inputStream, j2)) { // from class: com.google.api.client.googleapis.batch.BatchUnparsedResponse.1
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
        }
        parseAndCallback(this.requestInfos.get(this.contentId - 1), parseInt, getFakeResponse(parseInt, inputStream, arrayList, arrayList2));
        while (true) {
            if (inputStream.skip(j2) <= 0 && inputStream.read() == -1) {
                break;
            }
        }
        if (i2 != 0) {
            readLine2 = readLine();
        }
        while (readLine2 != null && readLine2.length() == 0) {
            readLine2 = readLine();
        }
        checkForFinalBoundary(readLine2);
    }
}
