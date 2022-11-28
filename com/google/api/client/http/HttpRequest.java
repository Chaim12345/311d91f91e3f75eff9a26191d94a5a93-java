package com.google.api.client.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.LoggingStreamingContent;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import com.google.api.client.util.StringUtils;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.opencensus.common.Scope;
import io.opencensus.contrib.http.util.HttpTraceAttributeConstants;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.message.TokenParser;
/* loaded from: classes2.dex */
public final class HttpRequest {
    public static final int DEFAULT_NUMBER_OF_RETRIES = 10;
    public static final String USER_AGENT_SUFFIX;
    public static final String VERSION;
    @Beta
    @Deprecated
    private BackOffPolicy backOffPolicy;
    private HttpContent content;
    private HttpEncoding encoding;
    private HttpExecuteInterceptor executeInterceptor;
    @Beta
    private HttpIOExceptionHandler ioExceptionHandler;
    private ObjectParser objectParser;
    private String requestMethod;
    private HttpResponseInterceptor responseInterceptor;
    private boolean suppressUserAgentSuffix;
    private final HttpTransport transport;
    private HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
    private GenericUrl url;
    private HttpHeaders headers = new HttpHeaders();
    private HttpHeaders responseHeaders = new HttpHeaders();
    private int numRetries = 10;
    private int contentLoggingLimit = 16384;
    private boolean loggingEnabled = true;
    private boolean curlLoggingEnabled = true;
    private int connectTimeout = 20000;
    private int readTimeout = 20000;
    private int writeTimeout = 0;
    private boolean followRedirects = true;
    private boolean useRawRedirectUrls = false;
    private boolean throwExceptionOnExecuteError = true;
    @Beta
    @Deprecated
    private boolean retryOnExecuteIOException = false;
    private Sleeper sleeper = Sleeper.DEFAULT;
    private final Tracer tracer = OpenCensusUtils.getTracer();
    private boolean responseReturnRawInputStream = false;

    static {
        String version = getVersion();
        VERSION = version;
        USER_AGENT_SUFFIX = "Google-HTTP-Java-Client/" + version + " (gzip)";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequest(HttpTransport httpTransport, String str) {
        this.transport = httpTransport;
        setRequestMethod(str);
    }

    private static void addSpanAttribute(Span span, String str, String str2) {
        if (str2 != null) {
            span.putAttribute(str, AttributeValue.stringAttributeValue(str2));
        }
    }

    private static String getVersion() {
        String str = "unknown-version";
        try {
            InputStream resourceAsStream = HttpRequest.class.getResourceAsStream("/com/google/api/client/http/google-http-client.properties");
            if (resourceAsStream != null) {
                Properties properties = new Properties();
                properties.load(resourceAsStream);
                str = properties.getProperty("google-http-client.version");
            }
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        } catch (IOException unused) {
        }
        return str;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:(2:20|(24:22|(1:24)|25|(2:27|(1:29)(1:30))|31|(1:33)|34|(1:175)(1:38)|39|(8:41|(1:43)|44|(1:46)(1:173)|47|(5:49|(2:51|(1:53))(1:171)|(2:55|(1:57))|58|(1:60))(1:172)|(1:62)|63)(1:174)|(2:65|(3:67|(1:69)|70))|(1:170)(1:73)|74|75|76|(1:78)|79|80|81|(3:112|113|(7:115|(1:117)(1:137)|(3:119|(1:(3:127|128|(2:130|131)))|121)|134|(1:136)|86|(4:89|(1:91)|92|(4:94|(1:96)|97|(1:109)(3:101|102|103))(1:110))(1:88)))|(1:84)(1:111)|85|86|(0)(0)))(1:177)|75|76|(0)|79|80|81|(0)|(0)(0)|85|86|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0285, code lost:
        if (r1.retryOnExecuteIOException == false) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x029c, code lost:
        if (r9 != false) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x029e, code lost:
        r8.log(java.util.logging.Level.WARNING, "exception thrown while executing request", (java.lang.Throwable) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02a5, code lost:
        r4.close();
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0282, code lost:
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0338 A[LOOP:0: B:10:0x0035->B:167:0x0338, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x02ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0305 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0261 A[Catch: all -> 0x027f, IOException -> 0x0282, TRY_LEAVE, TryCatch #3 {IOException -> 0x0282, blocks: (B:87:0x025b, B:89:0x0261, B:93:0x0275, B:95:0x027b, B:96:0x027e), top: B:177:0x025b, outer: #5 }] */
    /* JADX WARN: Type inference failed for: r14v0, types: [com.google.api.client.http.HttpEncodingStreamingContent] */
    /* JADX WARN: Type inference failed for: r14v4, types: [com.google.api.client.util.LoggingStreamingContent] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public HttpResponse execute() {
        StringBuilder sb;
        StringBuilder sb2;
        HttpContent httpContent;
        Span span;
        int i2;
        Span span2;
        Scope withSpan;
        HttpResponse httpResponse;
        boolean z;
        BackOffPolicy backOffPolicy;
        LowLevelHttpResponse execute;
        String name;
        long j2;
        HttpRequest httpRequest = this;
        Preconditions.checkArgument(httpRequest.numRetries >= 0);
        int i3 = httpRequest.numRetries;
        BackOffPolicy backOffPolicy2 = httpRequest.backOffPolicy;
        if (backOffPolicy2 != null) {
            backOffPolicy2.reset();
        }
        Preconditions.checkNotNull(httpRequest.requestMethod);
        Preconditions.checkNotNull(httpRequest.url);
        Span startSpan = httpRequest.tracer.spanBuilder(OpenCensusUtils.SPAN_NAME_HTTP_REQUEST_EXECUTE).setRecordEvents(OpenCensusUtils.isRecordEvent()).startSpan();
        int i4 = i3;
        HttpResponse httpResponse2 = null;
        while (true) {
            startSpan.addAnnotation("retry #" + (httpRequest.numRetries - i4));
            if (httpResponse2 != null) {
                httpResponse2.ignore();
            }
            HttpExecuteInterceptor httpExecuteInterceptor = httpRequest.executeInterceptor;
            if (httpExecuteInterceptor != null) {
                httpExecuteInterceptor.intercept(httpRequest);
            }
            String build = httpRequest.url.build();
            addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_METHOD, httpRequest.requestMethod);
            addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_HOST, httpRequest.url.getHost());
            addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_PATH, httpRequest.url.getRawPath());
            addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_URL, build);
            LowLevelHttpRequest buildRequest = httpRequest.transport.buildRequest(httpRequest.requestMethod, build);
            Logger logger = HttpTransport.f8050a;
            boolean z2 = httpRequest.loggingEnabled && logger.isLoggable(Level.CONFIG);
            try {
                try {
                    if (z2) {
                        sb = new StringBuilder();
                        sb.append("-------------- REQUEST  --------------");
                        String str = StringUtils.LINE_SEPARATOR;
                        sb.append(str);
                        sb.append(httpRequest.requestMethod);
                        sb.append(TokenParser.SP);
                        sb.append(build);
                        sb.append(str);
                        if (httpRequest.curlLoggingEnabled) {
                            sb2 = new StringBuilder("curl -v --compressed");
                            if (!httpRequest.requestMethod.equals("GET")) {
                                sb2.append(" -X ");
                                sb2.append(httpRequest.requestMethod);
                            }
                            String userAgent = httpRequest.headers.getUserAgent();
                            if (!httpRequest.suppressUserAgentSuffix) {
                                if (userAgent == null) {
                                    HttpHeaders httpHeaders = httpRequest.headers;
                                    String str2 = USER_AGENT_SUFFIX;
                                    httpHeaders.setUserAgent(str2);
                                    addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_USER_AGENT, str2);
                                } else {
                                    String str3 = userAgent + " " + USER_AGENT_SUFFIX;
                                    httpRequest.headers.setUserAgent(str3);
                                    addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_USER_AGENT, str3);
                                }
                            }
                            OpenCensusUtils.propagateTracingContext(startSpan, httpRequest.headers);
                            HttpHeaders.b(httpRequest.headers, sb, sb2, logger, buildRequest);
                            if (!httpRequest.suppressUserAgentSuffix) {
                                httpRequest.headers.setUserAgent(userAgent);
                            }
                            httpContent = httpRequest.content;
                            boolean z3 = httpContent != null || httpContent.retrySupported();
                            if (httpContent == null) {
                                String type = httpRequest.content.getType();
                                if (z2) {
                                    httpContent = new LoggingStreamingContent(httpContent, logger, Level.CONFIG, httpRequest.contentLoggingLimit);
                                }
                                HttpEncoding httpEncoding = httpRequest.encoding;
                                if (httpEncoding == null) {
                                    j2 = httpRequest.content.getLength();
                                    name = null;
                                } else {
                                    name = httpEncoding.getName();
                                    httpContent = new HttpEncodingStreamingContent(httpContent, httpRequest.encoding);
                                    j2 = -1;
                                }
                                span = startSpan;
                                if (z2) {
                                    if (type != null) {
                                        StringBuilder sb3 = new StringBuilder();
                                        i2 = i4;
                                        sb3.append("Content-Type: ");
                                        sb3.append(type);
                                        String sb4 = sb3.toString();
                                        sb.append(sb4);
                                        sb.append(StringUtils.LINE_SEPARATOR);
                                        if (sb2 != null) {
                                            sb2.append(" -H '" + sb4 + "'");
                                        }
                                    } else {
                                        i2 = i4;
                                    }
                                    if (name != null) {
                                        String str4 = "Content-Encoding: " + name;
                                        sb.append(str4);
                                        sb.append(StringUtils.LINE_SEPARATOR);
                                        if (sb2 != null) {
                                            sb2.append(" -H '" + str4 + "'");
                                        }
                                    }
                                    if (j2 >= 0) {
                                        sb.append("Content-Length: " + j2);
                                        sb.append(StringUtils.LINE_SEPARATOR);
                                    }
                                } else {
                                    i2 = i4;
                                }
                                if (sb2 != null) {
                                    sb2.append(" -d '@-'");
                                }
                                buildRequest.setContentType(type);
                                buildRequest.setContentEncoding(name);
                                buildRequest.setContentLength(j2);
                                buildRequest.setStreamingContent(httpContent);
                            } else {
                                span = startSpan;
                                i2 = i4;
                            }
                            if (z2) {
                                logger.config(sb.toString());
                                if (sb2 != null) {
                                    sb2.append(" -- '");
                                    sb2.append(build.replaceAll("'", "'\"'\"'"));
                                    sb2.append("'");
                                    if (httpContent != null) {
                                        sb2.append(" << $$$");
                                    }
                                    logger.config(sb2.toString());
                                }
                            }
                            boolean z4 = !z3 && i2 > 0;
                            httpRequest = this;
                            buildRequest.setTimeout(httpRequest.connectTimeout, httpRequest.readTimeout);
                            buildRequest.setWriteTimeout(httpRequest.writeTimeout);
                            span2 = span;
                            withSpan = httpRequest.tracer.withSpan(span2);
                            OpenCensusUtils.recordSentMessageEvent(span2, buildRequest.getContentLength());
                            execute = buildRequest.execute();
                            if (execute != null) {
                                OpenCensusUtils.recordReceivedMessageEvent(span2, execute.getContentLength());
                            }
                            HttpResponse httpResponse3 = new HttpResponse(httpRequest, execute);
                            withSpan.close();
                            httpResponse = httpResponse3;
                            e = null;
                            Integer num = null;
                            if (httpResponse != null) {
                                try {
                                    if (!httpResponse.isSuccessStatusCode()) {
                                        HttpUnsuccessfulResponseHandler httpUnsuccessfulResponseHandler = httpRequest.unsuccessfulResponseHandler;
                                        boolean handleResponse = httpUnsuccessfulResponseHandler != null ? httpUnsuccessfulResponseHandler.handleResponse(httpRequest, httpResponse, z4) : false;
                                        if (!handleResponse) {
                                            if (!httpRequest.handleRedirect(httpResponse.getStatusCode(), httpResponse.getHeaders())) {
                                                if (z4 && (backOffPolicy = httpRequest.backOffPolicy) != null && backOffPolicy.isBackOffRequired(httpResponse.getStatusCode())) {
                                                    long nextBackOffMillis = httpRequest.backOffPolicy.getNextBackOffMillis();
                                                    if (nextBackOffMillis != -1) {
                                                        try {
                                                            httpRequest.sleeper.sleep(nextBackOffMillis);
                                                        } catch (InterruptedException unused) {
                                                        }
                                                    }
                                                }
                                            }
                                            handleResponse = true;
                                        }
                                        z = z4 & handleResponse;
                                        if (z) {
                                            httpResponse.ignore();
                                        }
                                        i4 = i2 - 1;
                                        if (!z) {
                                            if (httpResponse != null) {
                                                num = Integer.valueOf(httpResponse.getStatusCode());
                                            }
                                            span2.end(OpenCensusUtils.getEndSpanOptions(num));
                                            if (httpResponse != null) {
                                                HttpResponseInterceptor httpResponseInterceptor = httpRequest.responseInterceptor;
                                                if (httpResponseInterceptor != null) {
                                                    httpResponseInterceptor.interceptResponse(httpResponse);
                                                }
                                                if (!httpRequest.throwExceptionOnExecuteError || httpResponse.isSuccessStatusCode()) {
                                                    return httpResponse;
                                                }
                                                try {
                                                    throw new HttpResponseException(httpResponse);
                                                } finally {
                                                }
                                            }
                                            throw e;
                                        }
                                        httpResponse2 = httpResponse;
                                        startSpan = span2;
                                    }
                                } finally {
                                }
                            }
                            z = z4 & (httpResponse != null);
                            i4 = i2 - 1;
                            if (!z) {
                            }
                        }
                    } else {
                        sb = null;
                    }
                    HttpResponse httpResponse32 = new HttpResponse(httpRequest, execute);
                    withSpan.close();
                    httpResponse = httpResponse32;
                    e = null;
                    Integer num2 = null;
                    if (httpResponse != null) {
                    }
                    z = z4 & (httpResponse != null);
                    i4 = i2 - 1;
                    if (!z) {
                    }
                } catch (Throwable th) {
                    InputStream content = execute.getContent();
                    if (content != null) {
                        content.close();
                    }
                    throw th;
                    break;
                }
                execute = buildRequest.execute();
                if (execute != null) {
                }
            } catch (Throwable th2) {
                withSpan.close();
                throw th2;
            }
            sb2 = null;
            String userAgent2 = httpRequest.headers.getUserAgent();
            if (!httpRequest.suppressUserAgentSuffix) {
            }
            OpenCensusUtils.propagateTracingContext(startSpan, httpRequest.headers);
            HttpHeaders.b(httpRequest.headers, sb, sb2, logger, buildRequest);
            if (!httpRequest.suppressUserAgentSuffix) {
            }
            httpContent = httpRequest.content;
            if (httpContent != null) {
            }
            if (httpContent == null) {
            }
            if (z2) {
            }
            if (z3) {
            }
            httpRequest = this;
            buildRequest.setTimeout(httpRequest.connectTimeout, httpRequest.readTimeout);
            buildRequest.setWriteTimeout(httpRequest.writeTimeout);
            span2 = span;
            withSpan = httpRequest.tracer.withSpan(span2);
            OpenCensusUtils.recordSentMessageEvent(span2, buildRequest.getContentLength());
        }
        span2.end(OpenCensusUtils.getEndSpanOptions(null));
        throw e;
    }

    @Beta
    public Future<HttpResponse> executeAsync() {
        return executeAsync(Executors.newFixedThreadPool(1, new ThreadFactoryBuilder().setDaemon(true).build()));
    }

    @Beta
    public Future<HttpResponse> executeAsync(Executor executor) {
        FutureTask futureTask = new FutureTask(new Callable<HttpResponse>() { // from class: com.google.api.client.http.HttpRequest.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public HttpResponse call() {
                return HttpRequest.this.execute();
            }
        });
        executor.execute(futureTask);
        return futureTask;
    }

    @Beta
    @Deprecated
    public BackOffPolicy getBackOffPolicy() {
        return this.backOffPolicy;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public HttpContent getContent() {
        return this.content;
    }

    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }

    public HttpEncoding getEncoding() {
        return this.encoding;
    }

    public boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    @Beta
    public HttpIOExceptionHandler getIOExceptionHandler() {
        return this.ioExceptionHandler;
    }

    public HttpExecuteInterceptor getInterceptor() {
        return this.executeInterceptor;
    }

    public int getNumberOfRetries() {
        return this.numRetries;
    }

    public final ObjectParser getParser() {
        return this.objectParser;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public HttpResponseInterceptor getResponseInterceptor() {
        return this.responseInterceptor;
    }

    public boolean getResponseReturnRawInputStream() {
        return this.responseReturnRawInputStream;
    }

    @Beta
    @Deprecated
    public boolean getRetryOnExecuteIOException() {
        return this.retryOnExecuteIOException;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public boolean getSuppressUserAgentSuffix() {
        return this.suppressUserAgentSuffix;
    }

    public boolean getThrowExceptionOnExecuteError() {
        return this.throwExceptionOnExecuteError;
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler() {
        return this.unsuccessfulResponseHandler;
    }

    public GenericUrl getUrl() {
        return this.url;
    }

    public boolean getUseRawRedirectUrls() {
        return this.useRawRedirectUrls;
    }

    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    public boolean handleRedirect(int i2, HttpHeaders httpHeaders) {
        String location = httpHeaders.getLocation();
        if (getFollowRedirects() && HttpStatusCodes.isRedirect(i2) && location != null) {
            setUrl(new GenericUrl(this.url.toURL(location), this.useRawRedirectUrls));
            if (i2 == 303) {
                setRequestMethod("GET");
                setContent(null);
            }
            this.headers.setAuthorization((String) null);
            this.headers.setIfMatch(null);
            this.headers.setIfNoneMatch(null);
            this.headers.setIfModifiedSince(null);
            this.headers.setIfUnmodifiedSince(null);
            this.headers.setIfRange(null);
            return true;
        }
        return false;
    }

    public boolean isCurlLoggingEnabled() {
        return this.curlLoggingEnabled;
    }

    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }

    @Beta
    @Deprecated
    public HttpRequest setBackOffPolicy(BackOffPolicy backOffPolicy) {
        this.backOffPolicy = backOffPolicy;
        return this;
    }

    public HttpRequest setConnectTimeout(int i2) {
        Preconditions.checkArgument(i2 >= 0);
        this.connectTimeout = i2;
        return this;
    }

    public HttpRequest setContent(HttpContent httpContent) {
        this.content = httpContent;
        return this;
    }

    public HttpRequest setContentLoggingLimit(int i2) {
        Preconditions.checkArgument(i2 >= 0, "The content logging limit must be non-negative.");
        this.contentLoggingLimit = i2;
        return this;
    }

    public HttpRequest setCurlLoggingEnabled(boolean z) {
        this.curlLoggingEnabled = z;
        return this;
    }

    public HttpRequest setEncoding(HttpEncoding httpEncoding) {
        this.encoding = httpEncoding;
        return this;
    }

    public HttpRequest setFollowRedirects(boolean z) {
        this.followRedirects = z;
        return this;
    }

    public HttpRequest setHeaders(HttpHeaders httpHeaders) {
        this.headers = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
        return this;
    }

    @Beta
    public HttpRequest setIOExceptionHandler(HttpIOExceptionHandler httpIOExceptionHandler) {
        this.ioExceptionHandler = httpIOExceptionHandler;
        return this;
    }

    public HttpRequest setInterceptor(HttpExecuteInterceptor httpExecuteInterceptor) {
        this.executeInterceptor = httpExecuteInterceptor;
        return this;
    }

    public HttpRequest setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
        return this;
    }

    public HttpRequest setNumberOfRetries(int i2) {
        Preconditions.checkArgument(i2 >= 0);
        this.numRetries = i2;
        return this;
    }

    public HttpRequest setParser(ObjectParser objectParser) {
        this.objectParser = objectParser;
        return this;
    }

    public HttpRequest setReadTimeout(int i2) {
        Preconditions.checkArgument(i2 >= 0);
        this.readTimeout = i2;
        return this;
    }

    public HttpRequest setRequestMethod(String str) {
        Preconditions.checkArgument(str == null || HttpMediaType.a(str));
        this.requestMethod = str;
        return this;
    }

    public HttpRequest setResponseHeaders(HttpHeaders httpHeaders) {
        this.responseHeaders = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
        return this;
    }

    public HttpRequest setResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        this.responseInterceptor = httpResponseInterceptor;
        return this;
    }

    public HttpRequest setResponseReturnRawInputStream(boolean z) {
        this.responseReturnRawInputStream = z;
        return this;
    }

    @Beta
    @Deprecated
    public HttpRequest setRetryOnExecuteIOException(boolean z) {
        this.retryOnExecuteIOException = z;
        return this;
    }

    public HttpRequest setSleeper(Sleeper sleeper) {
        this.sleeper = (Sleeper) Preconditions.checkNotNull(sleeper);
        return this;
    }

    public HttpRequest setSuppressUserAgentSuffix(boolean z) {
        this.suppressUserAgentSuffix = z;
        return this;
    }

    public HttpRequest setThrowExceptionOnExecuteError(boolean z) {
        this.throwExceptionOnExecuteError = z;
        return this;
    }

    public HttpRequest setUnsuccessfulResponseHandler(HttpUnsuccessfulResponseHandler httpUnsuccessfulResponseHandler) {
        this.unsuccessfulResponseHandler = httpUnsuccessfulResponseHandler;
        return this;
    }

    public HttpRequest setUrl(GenericUrl genericUrl) {
        this.url = (GenericUrl) Preconditions.checkNotNull(genericUrl);
        return this;
    }

    public HttpRequest setUseRawRedirectUrls(boolean z) {
        this.useRawRedirectUrls = z;
        return this;
    }

    public HttpRequest setWriteTimeout(int i2) {
        Preconditions.checkArgument(i2 >= 0);
        this.writeTimeout = i2;
        return this;
    }
}
