package com.google.maps.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class WifiAccessPoint implements Serializable {
    private static final long serialVersionUID = 1;
    public Integer age;
    public Integer channel;
    public String macAddress;
    public Integer signalStrength;
    public Integer signalToNoiseRatio;

    /* loaded from: classes2.dex */
    public static class WifiAccessPointBuilder {
        private String _macAddress = null;
        private Integer _signalStrength = null;
        private Integer _age = null;
        private Integer _channel = null;
        private Integer _signalToNoiseRatio = null;

        public WifiAccessPointBuilder Age(int i2) {
            this._age = Integer.valueOf(i2);
            return this;
        }

        public WifiAccessPointBuilder Channel(int i2) {
            this._channel = Integer.valueOf(i2);
            return this;
        }

        public WifiAccessPointBuilder MacAddress(String str) {
            this._macAddress = str;
            return this;
        }

        public WifiAccessPointBuilder SignalStrength(int i2) {
            this._signalStrength = Integer.valueOf(i2);
            return this;
        }

        public WifiAccessPointBuilder SignalToNoiseRatio(int i2) {
            this._signalToNoiseRatio = Integer.valueOf(i2);
            return this;
        }

        public WifiAccessPoint createWifiAccessPoint() {
            return new WifiAccessPoint(this._macAddress, this._signalStrength, this._age, this._channel, this._signalToNoiseRatio);
        }
    }

    public WifiAccessPoint() {
        this.signalStrength = null;
        this.age = null;
        this.channel = null;
        this.signalToNoiseRatio = null;
    }

    private WifiAccessPoint(String str, Integer num, Integer num2, Integer num3, Integer num4) {
        this.signalStrength = null;
        this.age = null;
        this.channel = null;
        this.signalToNoiseRatio = null;
        this.macAddress = str;
        this.signalStrength = num;
        this.age = num2;
        this.channel = num3;
        this.signalToNoiseRatio = num4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[WifiAccessPoint:");
        if (this.macAddress != null) {
            sb.append(" macAddress=");
            sb.append(this.macAddress);
        }
        if (this.signalStrength != null) {
            sb.append(" signalStrength=");
            sb.append(this.signalStrength);
        }
        if (this.age != null) {
            sb.append(" age=");
            sb.append(this.age);
        }
        if (this.channel != null) {
            sb.append(" channel=");
            sb.append(this.channel);
        }
        if (this.signalToNoiseRatio != null) {
            sb.append(" signalToNoiseRatio=");
            sb.append(this.signalToNoiseRatio);
        }
        sb.append("]");
        return sb.toString();
    }
}
