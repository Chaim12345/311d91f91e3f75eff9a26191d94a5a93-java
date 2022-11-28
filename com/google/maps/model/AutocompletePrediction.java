package com.google.maps.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes2.dex */
public class AutocompletePrediction implements Serializable {
    private static final long serialVersionUID = 1;
    public String description;
    public MatchedSubstring[] matchedSubstrings;
    public String placeId;
    public AutocompleteStructuredFormatting structuredFormatting;
    public Term[] terms;
    public String[] types;

    /* loaded from: classes2.dex */
    public static class MatchedSubstring implements Serializable {
        private static final long serialVersionUID = 1;
        public int length;
        public int offset;

        public String toString() {
            return String.format("(offset=%d, length=%d)", Integer.valueOf(this.offset), Integer.valueOf(this.length));
        }
    }

    /* loaded from: classes2.dex */
    public static class Term implements Serializable {
        private static final long serialVersionUID = 1;
        public int offset;
        public String value;

        public String toString() {
            return String.format("(offset=%d, value=%s)", Integer.valueOf(this.offset), this.value);
        }
    }

    public String toString() {
        return String.format("[AutocompletePrediction: \"%s\", placeId=%s, types=%s, terms=%s, matchedSubstrings=%s, structuredFormatting=%s]", this.description, this.placeId, Arrays.toString(this.types), Arrays.toString(this.terms), Arrays.toString(this.matchedSubstrings), Objects.toString(this.structuredFormatting));
    }
}
