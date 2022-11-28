package com.google.maps.model;

import com.google.maps.internal.StringJoin;
/* loaded from: classes2.dex */
public enum RankBy implements StringJoin.UrlValue {
    PROMINENCE("prominence"),
    DISTANCE("distance");
    
    private final String ranking;

    RankBy(String str) {
        this.ranking = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.ranking;
    }

    @Override // com.google.maps.internal.StringJoin.UrlValue
    public String toUrlValue() {
        return this.ranking;
    }
}
