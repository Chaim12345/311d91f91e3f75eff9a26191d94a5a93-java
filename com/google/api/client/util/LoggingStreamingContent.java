package com.google.api.client.util;

import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes2.dex */
public final class LoggingStreamingContent implements StreamingContent {
    private final StreamingContent content;
    private final int contentLoggingLimit;
    private final Logger logger;
    private final Level loggingLevel;

    public LoggingStreamingContent(StreamingContent streamingContent, Logger logger, Level level, int i2) {
        this.content = streamingContent;
        this.logger = logger;
        this.loggingLevel = level;
        this.contentLoggingLimit = i2;
    }

    @Override // com.google.api.client.util.StreamingContent
    public void writeTo(OutputStream outputStream) {
        LoggingOutputStream loggingOutputStream = new LoggingOutputStream(outputStream, this.logger, this.loggingLevel, this.contentLoggingLimit);
        try {
            this.content.writeTo(loggingOutputStream);
            loggingOutputStream.getLogStream().close();
            outputStream.flush();
        } catch (Throwable th) {
            loggingOutputStream.getLogStream().close();
            throw th;
        }
    }
}
