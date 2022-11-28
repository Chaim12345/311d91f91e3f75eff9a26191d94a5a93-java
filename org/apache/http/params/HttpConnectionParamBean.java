package org.apache.http.params;
@Deprecated
/* loaded from: classes3.dex */
public class HttpConnectionParamBean extends HttpAbstractParamBean {
    public HttpConnectionParamBean(HttpParams httpParams) {
        super(httpParams);
    }

    public void setConnectionTimeout(int i2) {
        HttpConnectionParams.setConnectionTimeout(this.params, i2);
    }

    public void setLinger(int i2) {
        HttpConnectionParams.setLinger(this.params, i2);
    }

    public void setSoTimeout(int i2) {
        HttpConnectionParams.setSoTimeout(this.params, i2);
    }

    public void setSocketBufferSize(int i2) {
        HttpConnectionParams.setSocketBufferSize(this.params, i2);
    }

    public void setStaleCheckingEnabled(boolean z) {
        HttpConnectionParams.setStaleCheckingEnabled(this.params, z);
    }

    public void setTcpNoDelay(boolean z) {
        HttpConnectionParams.setTcpNoDelay(this.params, z);
    }
}
