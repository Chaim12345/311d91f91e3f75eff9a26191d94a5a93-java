package io.opencensus.contrib.http.util;

import io.opencensus.trace.Status;
import javax.annotation.Nullable;
import org.apache.http.HttpStatus;
/* loaded from: classes3.dex */
public final class HttpTraceUtil {
    private static final Status STATUS_100;
    private static final Status STATUS_101;
    private static final Status STATUS_402;
    private static final Status STATUS_405;
    private static final Status STATUS_406;
    private static final Status STATUS_407;
    private static final Status STATUS_408;
    private static final Status STATUS_409;
    private static final Status STATUS_410;
    private static final Status STATUS_411;
    private static final Status STATUS_412;
    private static final Status STATUS_413;
    private static final Status STATUS_414;
    private static final Status STATUS_415;
    private static final Status STATUS_416;
    private static final Status STATUS_417;
    private static final Status STATUS_500;
    private static final Status STATUS_502;
    private static final Status STATUS_505;

    static {
        Status status = Status.UNKNOWN;
        STATUS_100 = status.withDescription("Continue");
        STATUS_101 = status.withDescription("Switching Protocols");
        STATUS_402 = status.withDescription("Payment Required");
        STATUS_405 = status.withDescription("Method Not Allowed");
        STATUS_406 = status.withDescription("Not Acceptable");
        STATUS_407 = status.withDescription("Proxy Authentication Required");
        STATUS_408 = status.withDescription("Request Time-out");
        STATUS_409 = status.withDescription("Conflict");
        STATUS_410 = status.withDescription("Gone");
        STATUS_411 = status.withDescription("Length Required");
        STATUS_412 = status.withDescription("Precondition Failed");
        STATUS_413 = status.withDescription("Request Entity Too Large");
        STATUS_414 = status.withDescription("Request-URI Too Large");
        STATUS_415 = status.withDescription("Unsupported Media Type");
        STATUS_416 = status.withDescription("Requested range not satisfiable");
        STATUS_417 = status.withDescription("Expectation Failed");
        STATUS_500 = status.withDescription("Internal Server Error");
        STATUS_502 = status.withDescription("Bad Gateway");
        STATUS_505 = status.withDescription("HTTP Version not supported");
    }

    private HttpTraceUtil() {
    }

    public static final Status parseResponseStatus(int i2, @Nullable Throwable th) {
        String str;
        Status status;
        if (th != null) {
            str = th.getMessage();
            if (str == null) {
                str = th.getClass().getSimpleName();
            }
        } else {
            str = null;
        }
        if (i2 != 0) {
            if (i2 < 200 || i2 >= 400) {
                if (i2 != 100) {
                    if (i2 != 101) {
                        if (i2 != 429) {
                            switch (i2) {
                                case 400:
                                    status = Status.INVALID_ARGUMENT;
                                    break;
                                case 401:
                                    status = Status.UNAUTHENTICATED;
                                    break;
                                case 402:
                                    return STATUS_402;
                                case 403:
                                    status = Status.PERMISSION_DENIED;
                                    break;
                                case 404:
                                    status = Status.NOT_FOUND;
                                    break;
                                case 405:
                                    return STATUS_405;
                                case HttpStatus.SC_NOT_ACCEPTABLE /* 406 */:
                                    return STATUS_406;
                                case HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED /* 407 */:
                                    return STATUS_407;
                                case HttpStatus.SC_REQUEST_TIMEOUT /* 408 */:
                                    return STATUS_408;
                                case 409:
                                    return STATUS_409;
                                case HttpStatus.SC_GONE /* 410 */:
                                    return STATUS_410;
                                case HttpStatus.SC_LENGTH_REQUIRED /* 411 */:
                                    return STATUS_411;
                                case 412:
                                    return STATUS_412;
                                case HttpStatus.SC_REQUEST_TOO_LONG /* 413 */:
                                    return STATUS_413;
                                case HttpStatus.SC_REQUEST_URI_TOO_LONG /* 414 */:
                                    return STATUS_414;
                                case HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE /* 415 */:
                                    return STATUS_415;
                                case 416:
                                    return STATUS_416;
                                case HttpStatus.SC_EXPECTATION_FAILED /* 417 */:
                                    return STATUS_417;
                                default:
                                    switch (i2) {
                                        case 500:
                                            return STATUS_500;
                                        case 501:
                                            status = Status.UNIMPLEMENTED;
                                            break;
                                        case 502:
                                            return STATUS_502;
                                        case 503:
                                            status = Status.UNAVAILABLE;
                                            break;
                                        case 504:
                                            status = Status.DEADLINE_EXCEEDED;
                                            break;
                                        case 505:
                                            return STATUS_505;
                                    }
                            }
                        } else {
                            status = Status.RESOURCE_EXHAUSTED;
                        }
                        return status.withDescription(str);
                    }
                    return STATUS_101;
                }
                return STATUS_100;
            }
            return Status.OK;
        }
        status = Status.UNKNOWN;
        return status.withDescription(str);
    }
}
