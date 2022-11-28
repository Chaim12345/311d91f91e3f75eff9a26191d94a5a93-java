package com.google.api.client.googleapis.json;

import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Strings;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
/* loaded from: classes2.dex */
public class GoogleJsonResponseException extends HttpResponseException {
    private static final long serialVersionUID = 409811126989994864L;
    private final transient GoogleJsonError details;

    public GoogleJsonResponseException(HttpResponseException.Builder builder, GoogleJsonError googleJsonError) {
        super(builder);
        this.details = googleJsonError;
    }

    public static HttpResponse execute(JsonFactory jsonFactory, HttpRequest httpRequest) {
        Preconditions.checkNotNull(jsonFactory);
        boolean throwExceptionOnExecuteError = httpRequest.getThrowExceptionOnExecuteError();
        if (throwExceptionOnExecuteError) {
            httpRequest.setThrowExceptionOnExecuteError(false);
        }
        HttpResponse execute = httpRequest.execute();
        httpRequest.setThrowExceptionOnExecuteError(throwExceptionOnExecuteError);
        if (!throwExceptionOnExecuteError || execute.isSuccessStatusCode()) {
            return execute;
        }
        throw from(jsonFactory, execute);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a9 A[Catch: IOException -> 0x0094, TryCatch #3 {IOException -> 0x0094, blocks: (B:51:0x00a5, B:53:0x00ac, B:52:0x00a9, B:42:0x0090, B:46:0x009a), top: B:66:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static GoogleJsonResponseException from(JsonFactory jsonFactory, HttpResponse httpResponse) {
        String str;
        JsonParser jsonParser;
        GoogleJsonError googleJsonError;
        JsonToken currentToken;
        HttpResponseException.Builder builder = new HttpResponseException.Builder(httpResponse.getStatusCode(), httpResponse.getStatusMessage(), httpResponse.getHeaders());
        Preconditions.checkNotNull(jsonFactory);
        GoogleJsonError googleJsonError2 = 0;
        try {
        } catch (IOException e2) {
            e = e2;
            str = null;
        }
        if (!httpResponse.isSuccessStatusCode()) {
            String contentType = httpResponse.getContentType();
            if (HttpMediaType.equalsIgnoreParameters(Json.MEDIA_TYPE, contentType)) {
                try {
                } catch (IOException e3) {
                    e = e3;
                    str = null;
                    googleJsonError2 = contentType;
                }
                if (httpResponse.getContent() != null) {
                    try {
                        jsonParser = jsonFactory.createJsonParser(httpResponse.getContent());
                        try {
                            currentToken = jsonParser.getCurrentToken();
                            if (currentToken == null) {
                                currentToken = jsonParser.nextToken();
                            }
                        } catch (IOException e4) {
                            e = e4;
                            googleJsonError = null;
                        } catch (Throwable th) {
                            th = th;
                            googleJsonError = null;
                        }
                    } catch (IOException e5) {
                        e = e5;
                        jsonParser = null;
                        googleJsonError = null;
                    } catch (Throwable th2) {
                        th = th2;
                        jsonParser = null;
                        googleJsonError = null;
                    }
                    if (currentToken != null) {
                        jsonParser.skipToKey(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
                            str = jsonParser.getText();
                        } else if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                            GoogleJsonError googleJsonError3 = (GoogleJsonError) jsonParser.parseAndClose((Class<Object>) GoogleJsonError.class);
                            try {
                                str = googleJsonError3.toPrettyString();
                                googleJsonError2 = googleJsonError3;
                            } catch (IOException e6) {
                                googleJsonError = googleJsonError3;
                                e = e6;
                                try {
                                    e.printStackTrace();
                                    if (jsonParser == null) {
                                        httpResponse.ignore();
                                    } else if (googleJsonError == null) {
                                        jsonParser.close();
                                    }
                                    str = null;
                                    googleJsonError2 = googleJsonError;
                                    StringBuilder computeMessageBuffer = HttpResponseException.computeMessageBuffer(httpResponse);
                                    if (!Strings.isNullOrEmpty(str)) {
                                    }
                                    builder.setMessage(computeMessageBuffer.toString());
                                    return new GoogleJsonResponseException(builder, googleJsonError2);
                                } catch (Throwable th3) {
                                    th = th3;
                                    if (jsonParser != null) {
                                        httpResponse.ignore();
                                    } else if (googleJsonError == null) {
                                        jsonParser.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                googleJsonError = googleJsonError3;
                                th = th4;
                                if (jsonParser != null) {
                                }
                                throw th;
                            }
                        }
                        if (googleJsonError2 == null) {
                            try {
                                jsonParser.close();
                            } catch (IOException e7) {
                                e = e7;
                                e.printStackTrace();
                                StringBuilder computeMessageBuffer2 = HttpResponseException.computeMessageBuffer(httpResponse);
                                if (!Strings.isNullOrEmpty(str)) {
                                }
                                builder.setMessage(computeMessageBuffer2.toString());
                                return new GoogleJsonResponseException(builder, googleJsonError2);
                            }
                        }
                        StringBuilder computeMessageBuffer22 = HttpResponseException.computeMessageBuffer(httpResponse);
                        if (!Strings.isNullOrEmpty(str)) {
                            computeMessageBuffer22.append(StringUtils.LINE_SEPARATOR);
                            computeMessageBuffer22.append(str);
                            builder.setContent(str);
                        }
                        builder.setMessage(computeMessageBuffer22.toString());
                        return new GoogleJsonResponseException(builder, googleJsonError2);
                    }
                    str = null;
                    if (googleJsonError2 == null) {
                    }
                    StringBuilder computeMessageBuffer222 = HttpResponseException.computeMessageBuffer(httpResponse);
                    if (!Strings.isNullOrEmpty(str)) {
                    }
                    builder.setMessage(computeMessageBuffer222.toString());
                    return new GoogleJsonResponseException(builder, googleJsonError2);
                }
            }
        }
        str = httpResponse.parseAsString();
        StringBuilder computeMessageBuffer2222 = HttpResponseException.computeMessageBuffer(httpResponse);
        if (!Strings.isNullOrEmpty(str)) {
        }
        builder.setMessage(computeMessageBuffer2222.toString());
        return new GoogleJsonResponseException(builder, googleJsonError2);
    }

    public final GoogleJsonError getDetails() {
        return this.details;
    }
}
