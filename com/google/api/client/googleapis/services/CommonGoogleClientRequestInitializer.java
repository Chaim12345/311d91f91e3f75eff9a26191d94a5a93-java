package com.google.api.client.googleapis.services;
/* loaded from: classes2.dex */
public class CommonGoogleClientRequestInitializer implements GoogleClientRequestInitializer {
    private static final String REQUEST_REASON_HEADER_NAME = "X-Goog-Request-Reason";
    private static final String USER_PROJECT_HEADER_NAME = "X-Goog-User-Project";
    private final String key;
    private final String requestReason;
    private final String userAgent;
    private final String userIp;
    private final String userProject;

    /* loaded from: classes2.dex */
    public static class Builder {
        private String key;
        private String requestReason;
        private String userAgent;
        private String userIp;
        private String userProject;

        protected Builder a() {
            return this;
        }

        public CommonGoogleClientRequestInitializer build() {
            return new CommonGoogleClientRequestInitializer(this);
        }

        public String getKey() {
            return this.key;
        }

        public String getRequestReason() {
            return this.requestReason;
        }

        public String getUserAgent() {
            return this.userAgent;
        }

        public String getUserIp() {
            return this.userIp;
        }

        public String getUserProject() {
            return this.userProject;
        }

        public Builder setKey(String str) {
            this.key = str;
            return a();
        }

        public Builder setRequestReason(String str) {
            this.requestReason = str;
            return a();
        }

        public Builder setUserAgent(String str) {
            this.userAgent = str;
            return a();
        }

        public Builder setUserIp(String str) {
            this.userIp = str;
            return a();
        }

        public Builder setUserProject(String str) {
            this.userProject = str;
            return a();
        }
    }

    @Deprecated
    public CommonGoogleClientRequestInitializer() {
        this(newBuilder());
    }

    protected CommonGoogleClientRequestInitializer(Builder builder) {
        this.key = builder.getKey();
        this.userIp = builder.getUserIp();
        this.userAgent = builder.getUserAgent();
        this.requestReason = builder.getRequestReason();
        this.userProject = builder.getUserProject();
    }

    @Deprecated
    public CommonGoogleClientRequestInitializer(String str) {
        this(str, null);
    }

    @Deprecated
    public CommonGoogleClientRequestInitializer(String str, String str2) {
        this(newBuilder().setKey(str).setUserIp(str2));
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final String getKey() {
        return this.key;
    }

    public final String getRequestReason() {
        return this.requestReason;
    }

    public final String getUserAgent() {
        return this.userAgent;
    }

    public final String getUserIp() {
        return this.userIp;
    }

    public final String getUserProject() {
        return this.userProject;
    }

    @Override // com.google.api.client.googleapis.services.GoogleClientRequestInitializer
    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
        String str = this.key;
        if (str != null) {
            abstractGoogleClientRequest.put("key", (Object) str);
        }
        String str2 = this.userIp;
        if (str2 != null) {
            abstractGoogleClientRequest.put("userIp", (Object) str2);
        }
        if (this.userAgent != null) {
            abstractGoogleClientRequest.getRequestHeaders().setUserAgent(this.userAgent);
        }
        if (this.requestReason != null) {
            abstractGoogleClientRequest.getRequestHeaders().set(REQUEST_REASON_HEADER_NAME, (Object) this.requestReason);
        }
        if (this.userProject != null) {
            abstractGoogleClientRequest.getRequestHeaders().set(USER_PROJECT_HEADER_NAME, (Object) this.userProject);
        }
    }
}
