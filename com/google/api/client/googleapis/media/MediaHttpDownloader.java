package com.google.api.client.googleapis.media;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Preconditions;
import com.google.common.base.MoreObjects;
import com.google.common.io.ByteStreams;
import java.io.OutputStream;
import org.apache.commons.cli.HelpFormatter;
/* loaded from: classes2.dex */
public final class MediaHttpDownloader {
    public static final int MAXIMUM_CHUNK_SIZE = 33554432;
    private long bytesDownloaded;
    private long mediaContentLength;
    private MediaHttpDownloaderProgressListener progressListener;
    private final HttpRequestFactory requestFactory;
    private final HttpTransport transport;
    private boolean directDownloadEnabled = false;
    private int chunkSize = MAXIMUM_CHUNK_SIZE;
    private DownloadState downloadState = DownloadState.NOT_STARTED;
    private long lastBytePos = -1;

    /* loaded from: classes2.dex */
    public enum DownloadState {
        NOT_STARTED,
        MEDIA_IN_PROGRESS,
        MEDIA_COMPLETE
    }

    public MediaHttpDownloader(HttpTransport httpTransport, HttpRequestInitializer httpRequestInitializer) {
        this.transport = (HttpTransport) Preconditions.checkNotNull(httpTransport);
        this.requestFactory = httpRequestInitializer == null ? httpTransport.createRequestFactory() : httpTransport.createRequestFactory(httpRequestInitializer);
    }

    private HttpResponse executeCurrentRequest(long j2, GenericUrl genericUrl, HttpHeaders httpHeaders, OutputStream outputStream) {
        HttpRequest buildGetRequest = this.requestFactory.buildGetRequest(genericUrl);
        if (httpHeaders != null) {
            buildGetRequest.getHeaders().putAll(httpHeaders);
        }
        if (this.bytesDownloaded != 0 || j2 != -1) {
            StringBuilder sb = new StringBuilder();
            sb.append("bytes=");
            sb.append(this.bytesDownloaded);
            sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
            if (j2 != -1) {
                sb.append(j2);
            }
            buildGetRequest.getHeaders().setRange(sb.toString());
        }
        HttpResponse execute = buildGetRequest.execute();
        try {
            ByteStreams.copy(execute.getContent(), outputStream);
            return execute;
        } finally {
            execute.disconnect();
        }
    }

    private long getNextByteIndex(String str) {
        if (str == null) {
            return 0L;
        }
        return Long.parseLong(str.substring(str.indexOf(45) + 1, str.indexOf(47))) + 1;
    }

    private void setMediaContentLength(String str) {
        if (str != null && this.mediaContentLength == 0) {
            this.mediaContentLength = Long.parseLong(str.substring(str.indexOf(47) + 1));
        }
    }

    private void updateStateAndNotifyListener(DownloadState downloadState) {
        this.downloadState = downloadState;
        MediaHttpDownloaderProgressListener mediaHttpDownloaderProgressListener = this.progressListener;
        if (mediaHttpDownloaderProgressListener != null) {
            mediaHttpDownloaderProgressListener.progressChanged(this);
        }
    }

    public void download(GenericUrl genericUrl, HttpHeaders httpHeaders, OutputStream outputStream) {
        Preconditions.checkArgument(this.downloadState == DownloadState.NOT_STARTED);
        genericUrl.put("alt", "media");
        if (!this.directDownloadEnabled) {
            while (true) {
                long j2 = (this.bytesDownloaded + this.chunkSize) - 1;
                long j3 = this.lastBytePos;
                if (j3 != -1) {
                    j2 = Math.min(j3, j2);
                }
                String contentRange = executeCurrentRequest(j2, genericUrl, httpHeaders, outputStream).getHeaders().getContentRange();
                long nextByteIndex = getNextByteIndex(contentRange);
                setMediaContentLength(contentRange);
                long j4 = this.lastBytePos;
                if (j4 != -1 && j4 <= nextByteIndex) {
                    this.bytesDownloaded = j4;
                    break;
                }
                long j5 = this.mediaContentLength;
                if (j5 <= nextByteIndex) {
                    this.bytesDownloaded = j5;
                    break;
                } else {
                    this.bytesDownloaded = nextByteIndex;
                    updateStateAndNotifyListener(DownloadState.MEDIA_IN_PROGRESS);
                }
            }
        } else {
            updateStateAndNotifyListener(DownloadState.MEDIA_IN_PROGRESS);
            long longValue = ((Long) MoreObjects.firstNonNull(executeCurrentRequest(this.lastBytePos, genericUrl, httpHeaders, outputStream).getHeaders().getContentLength(), Long.valueOf(this.mediaContentLength))).longValue();
            this.mediaContentLength = longValue;
            this.bytesDownloaded = longValue;
        }
        updateStateAndNotifyListener(DownloadState.MEDIA_COMPLETE);
    }

    public void download(GenericUrl genericUrl, OutputStream outputStream) {
        download(genericUrl, null, outputStream);
    }

    public int getChunkSize() {
        return this.chunkSize;
    }

    public DownloadState getDownloadState() {
        return this.downloadState;
    }

    public long getLastBytePosition() {
        return this.lastBytePos;
    }

    public long getNumBytesDownloaded() {
        return this.bytesDownloaded;
    }

    public double getProgress() {
        long j2 = this.mediaContentLength;
        if (j2 == 0) {
            return 0.0d;
        }
        return this.bytesDownloaded / j2;
    }

    public MediaHttpDownloaderProgressListener getProgressListener() {
        return this.progressListener;
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public boolean isDirectDownloadEnabled() {
        return this.directDownloadEnabled;
    }

    public MediaHttpDownloader setBytesDownloaded(long j2) {
        Preconditions.checkArgument(j2 >= 0);
        this.bytesDownloaded = j2;
        return this;
    }

    public MediaHttpDownloader setChunkSize(int i2) {
        Preconditions.checkArgument(i2 > 0 && i2 <= 33554432);
        this.chunkSize = i2;
        return this;
    }

    @Deprecated
    public MediaHttpDownloader setContentRange(long j2, int i2) {
        return setContentRange(j2, i2);
    }

    public MediaHttpDownloader setContentRange(long j2, long j3) {
        Preconditions.checkArgument(j3 >= j2);
        setBytesDownloaded(j2);
        this.lastBytePos = j3;
        return this;
    }

    public MediaHttpDownloader setDirectDownloadEnabled(boolean z) {
        this.directDownloadEnabled = z;
        return this;
    }

    public MediaHttpDownloader setProgressListener(MediaHttpDownloaderProgressListener mediaHttpDownloaderProgressListener) {
        this.progressListener = mediaHttpDownloaderProgressListener;
        return this;
    }
}
