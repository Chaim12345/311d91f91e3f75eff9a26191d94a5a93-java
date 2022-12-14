package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonemetadata;
import com.google.i18n.phonenumbers.Phonenumber;
import java.lang.Character;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Marker;
/* loaded from: classes2.dex */
final class PhoneNumberMatcher implements Iterator<PhoneNumberMatch> {
    private static final Pattern LEAD_CLASS;
    private static final Pattern MATCHING_BRACKETS;
    private static final Pattern PATTERN;
    private final PhoneNumberUtil.Leniency leniency;
    private long maxTries;
    private final PhoneNumberUtil phoneUtil;
    private final String preferredRegion;
    private final CharSequence text;
    private static final Pattern PUB_PAGES = Pattern.compile("\\d{1,5}-+\\d{1,5}\\s{0,4}\\(\\d{1,4}");
    private static final Pattern SLASH_SEPARATED_DATES = Pattern.compile("(?:(?:[0-3]?\\d/[01]?\\d)|(?:[01]?\\d/[0-3]?\\d))/(?:[12]\\d)?\\d{2}");
    private static final Pattern TIME_STAMPS = Pattern.compile("[12]\\d{3}[-/]?[01]\\d[-/]?[0-3]\\d +[0-2]\\d$");
    private static final Pattern TIME_STAMPS_SUFFIX = Pattern.compile(":[0-5]\\d");
    private static final Pattern[] INNER_MATCHES = {Pattern.compile("/+(.*)"), Pattern.compile("(\\([^(]*)"), Pattern.compile("(?:\\p{Z}-|-\\p{Z})\\p{Z}*(.+)"), Pattern.compile("[‒-―－]\\p{Z}*(.+)"), Pattern.compile("\\.+\\p{Z}*([^.]+)"), Pattern.compile("\\p{Z}+(\\P{Z}+)")};
    private State state = State.NOT_READY;
    private PhoneNumberMatch lastMatch = null;
    private int searchIndex = 0;

    /* loaded from: classes2.dex */
    interface NumberGroupingChecker {
        boolean checkGroups(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum State {
        NOT_READY,
        READY,
        DONE
    }

    static {
        String str = "[^(\\[（［)\\]）］]";
        MATCHING_BRACKETS = Pattern.compile("(?:[(\\[（［])?(?:" + str + "+[)\\]）］])?" + str + "+(?:[(\\[（［]" + str + "+[)\\]）］])" + limit(0, 3) + str + Marker.ANY_MARKER);
        String limit = limit(0, 2);
        String limit2 = limit(0, 4);
        String limit3 = limit(0, 20);
        String str2 = "[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]" + limit2;
        String str3 = "\\p{Nd}" + limit(1, 20);
        String str4 = "[" + ("(\\[（［+＋") + "]";
        LEAD_CLASS = Pattern.compile(str4);
        PATTERN = Pattern.compile("(?:" + str4 + str2 + ")" + limit + str3 + "(?:" + str2 + str3 + ")" + limit3 + "(?:" + PhoneNumberUtil.f10253d + ")?", 66);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PhoneNumberMatcher(PhoneNumberUtil phoneNumberUtil, String str, String str2, PhoneNumberUtil.Leniency leniency, long j2) {
        if (phoneNumberUtil == null || leniency == null) {
            throw null;
        }
        if (j2 < 0) {
            throw new IllegalArgumentException();
        }
        this.phoneUtil = phoneNumberUtil;
        this.text = str == null ? "" : str;
        this.preferredRegion = str2;
        this.leniency = leniency;
        this.maxTries = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr) {
        String[] split = PhoneNumberUtil.f10254e.split(sb.toString());
        int length = phoneNumber.hasExtension() ? split.length - 2 : split.length - 1;
        if (split.length == 1 || split[length].contains(phoneNumberUtil.getNationalSignificantNumber(phoneNumber))) {
            return true;
        }
        int length2 = strArr.length - 1;
        while (length2 > 0 && length >= 0) {
            if (!split[length].equals(strArr[length2])) {
                return false;
            }
            length2--;
            length--;
        }
        return length >= 0 && split[length].endsWith(strArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr) {
        int i2;
        if (phoneNumber.getCountryCodeSource() != Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            String num = Integer.toString(phoneNumber.getCountryCode());
            i2 = sb.indexOf(num) + num.length();
        } else {
            i2 = 0;
        }
        for (int i3 = 0; i3 < strArr.length; i3++) {
            int indexOf = sb.indexOf(strArr[i3], i2);
            if (indexOf < 0) {
                return false;
            }
            i2 = indexOf + strArr[i3].length();
            if (i3 == 0 && i2 < sb.length() && phoneNumberUtil.getNddPrefixForRegion(phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode()), true) != null && Character.isDigit(sb.charAt(i2))) {
                return sb.substring(i2 - strArr[i3].length()).startsWith(phoneNumberUtil.getNationalSignificantNumber(phoneNumber));
            }
        }
        return sb.substring(i2).contains(phoneNumber.getExtension());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil, NumberGroupingChecker numberGroupingChecker) {
        StringBuilder s2 = PhoneNumberUtil.s(str, true);
        if (numberGroupingChecker.checkGroups(phoneNumberUtil, phoneNumber, s2, getNationalNumberGroups(phoneNumberUtil, phoneNumber, null))) {
            return true;
        }
        Phonemetadata.PhoneMetadata b2 = MetadataManager.b(phoneNumber.getCountryCode());
        if (b2 != null) {
            for (Phonemetadata.NumberFormat numberFormat : b2.numberFormats()) {
                if (numberGroupingChecker.checkGroups(phoneNumberUtil, phoneNumber, s2, getNationalNumberGroups(phoneNumberUtil, phoneNumber, numberFormat))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(Phonenumber.PhoneNumber phoneNumber, String str) {
        int indexOf;
        int indexOf2 = str.indexOf(47);
        if (indexOf2 >= 0 && (indexOf = str.indexOf(47, indexOf2 + 1)) >= 0) {
            if ((phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN || phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN) && PhoneNumberUtil.normalizeDigitsOnly(str.substring(0, indexOf2)).equals(Integer.toString(phoneNumber.getCountryCode()))) {
                return str.substring(indexOf + 1).contains("/");
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
        int i2 = 0;
        while (i2 < str.length() - 1) {
            char charAt = str.charAt(i2);
            if (charAt == 'x' || charAt == 'X') {
                int i3 = i2 + 1;
                char charAt2 = str.charAt(i3);
                if (charAt2 == 'x' || charAt2 == 'X') {
                    if (phoneNumberUtil.isNumberMatch(phoneNumber, str.substring(i3)) != PhoneNumberUtil.MatchType.NSN_MATCH) {
                        return false;
                    }
                    i2 = i3;
                } else if (!PhoneNumberUtil.normalizeDigitsOnly(str.substring(i2)).equals(phoneNumber.getExtension())) {
                    return false;
                }
            }
            i2++;
        }
        return true;
    }

    private PhoneNumberMatch extractInnerMatch(String str, int i2) {
        for (Pattern pattern : INNER_MATCHES) {
            Matcher matcher = pattern.matcher(str);
            boolean z = true;
            while (matcher.find() && this.maxTries > 0) {
                if (z) {
                    PhoneNumberMatch parseAndVerify = parseAndVerify(trimAfterFirstMatch(PhoneNumberUtil.f10252c, str.substring(0, matcher.start())).toString(), i2);
                    if (parseAndVerify != null) {
                        return parseAndVerify;
                    }
                    this.maxTries--;
                    z = false;
                }
                PhoneNumberMatch parseAndVerify2 = parseAndVerify(trimAfterFirstMatch(PhoneNumberUtil.f10252c, matcher.group(1)).toString(), matcher.start(1) + i2);
                if (parseAndVerify2 != null) {
                    return parseAndVerify2;
                }
                this.maxTries--;
            }
        }
        return null;
    }

    private PhoneNumberMatch extractMatch(CharSequence charSequence, int i2) {
        if (SLASH_SEPARATED_DATES.matcher(charSequence).find()) {
            return null;
        }
        if (TIME_STAMPS.matcher(charSequence).find()) {
            if (TIME_STAMPS_SUFFIX.matcher(this.text.toString().substring(charSequence.length() + i2)).lookingAt()) {
                return null;
            }
        }
        String charSequence2 = charSequence.toString();
        PhoneNumberMatch parseAndVerify = parseAndVerify(charSequence2, i2);
        return parseAndVerify != null ? parseAndVerify : extractInnerMatch(charSequence2, i2);
    }

    static boolean f(char c2) {
        if (Character.isLetter(c2) || Character.getType(c2) == 6) {
            Character.UnicodeBlock of = Character.UnicodeBlock.of(c2);
            return of.equals(Character.UnicodeBlock.BASIC_LATIN) || of.equals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT) || of.equals(Character.UnicodeBlock.LATIN_EXTENDED_A) || of.equals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL) || of.equals(Character.UnicodeBlock.LATIN_EXTENDED_B) || of.equals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS);
        }
        return false;
    }

    private PhoneNumberMatch find(int i2) {
        Matcher matcher = PATTERN.matcher(this.text);
        while (this.maxTries > 0 && matcher.find(i2)) {
            int start = matcher.start();
            CharSequence trimAfterFirstMatch = trimAfterFirstMatch(PhoneNumberUtil.f10251b, this.text.subSequence(start, matcher.end()));
            PhoneNumberMatch extractMatch = extractMatch(trimAfterFirstMatch, start);
            if (extractMatch != null) {
                return extractMatch;
            }
            i2 = start + trimAfterFirstMatch.length();
            this.maxTries--;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g(Phonenumber.PhoneNumber phoneNumber, PhoneNumberUtil phoneNumberUtil) {
        Phonemetadata.PhoneMetadata h2;
        if (phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY && (h2 = phoneNumberUtil.h(phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode()))) != null) {
            Phonemetadata.NumberFormat b2 = phoneNumberUtil.b(h2.numberFormats(), phoneNumberUtil.getNationalSignificantNumber(phoneNumber));
            if (b2 == null || b2.getNationalPrefixFormattingRule().length() <= 0 || b2.isNationalPrefixOptionalWhenFormatting() || PhoneNumberUtil.f(b2.getNationalPrefixFormattingRule())) {
                return true;
            }
            return phoneNumberUtil.p(new StringBuilder(PhoneNumberUtil.normalizeDigitsOnly(phoneNumber.getRawInput())), h2, null);
        }
        return true;
    }

    private static String[] getNationalNumberGroups(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, Phonemetadata.NumberFormat numberFormat) {
        String e2;
        if (numberFormat == null) {
            String format = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.RFC3966);
            int indexOf = format.indexOf(59);
            if (indexOf < 0) {
                indexOf = format.length();
            }
            e2 = format.substring(format.indexOf(45) + 1, indexOf);
        } else {
            e2 = phoneNumberUtil.e(phoneNumberUtil.getNationalSignificantNumber(phoneNumber), numberFormat, PhoneNumberUtil.PhoneNumberFormat.RFC3966);
        }
        return e2.split(HelpFormatter.DEFAULT_OPT_PREFIX);
    }

    private static boolean isInvalidPunctuationSymbol(char c2) {
        return c2 == '%' || Character.getType(c2) == 26;
    }

    private static String limit(int i2, int i3) {
        if (i2 < 0 || i3 <= 0 || i3 < i2) {
            throw new IllegalArgumentException();
        }
        return "{" + i2 + "," + i3 + "}";
    }

    private PhoneNumberMatch parseAndVerify(String str, int i2) {
        try {
            if (MATCHING_BRACKETS.matcher(str).matches() && !PUB_PAGES.matcher(str).find()) {
                if (this.leniency.compareTo(PhoneNumberUtil.Leniency.VALID) >= 0) {
                    if (i2 > 0 && !LEAD_CLASS.matcher(str).lookingAt()) {
                        char charAt = this.text.charAt(i2 - 1);
                        if (isInvalidPunctuationSymbol(charAt) || f(charAt)) {
                            return null;
                        }
                    }
                    int length = str.length() + i2;
                    if (length < this.text.length()) {
                        char charAt2 = this.text.charAt(length);
                        if (isInvalidPunctuationSymbol(charAt2) || f(charAt2)) {
                            return null;
                        }
                    }
                }
                Phonenumber.PhoneNumber parseAndKeepRawInput = this.phoneUtil.parseAndKeepRawInput(str, this.preferredRegion);
                if ((!this.phoneUtil.getRegionCodeForCountryCode(parseAndKeepRawInput.getCountryCode()).equals("IL") || this.phoneUtil.getNationalSignificantNumber(parseAndKeepRawInput).length() != 4 || (i2 != 0 && (i2 <= 0 || this.text.charAt(i2 - 1) == '*'))) && this.leniency.a(parseAndKeepRawInput, str, this.phoneUtil)) {
                    parseAndKeepRawInput.clearCountryCodeSource();
                    parseAndKeepRawInput.clearRawInput();
                    parseAndKeepRawInput.clearPreferredDomesticCarrierCode();
                    return new PhoneNumberMatch(i2, str, parseAndKeepRawInput);
                }
            }
        } catch (NumberParseException unused) {
        }
        return null;
    }

    private static CharSequence trimAfterFirstMatch(Pattern pattern, CharSequence charSequence) {
        Matcher matcher = pattern.matcher(charSequence);
        return matcher.find() ? charSequence.subSequence(0, matcher.start()) : charSequence;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        State state;
        if (this.state == State.NOT_READY) {
            PhoneNumberMatch find = find(this.searchIndex);
            this.lastMatch = find;
            if (find == null) {
                state = State.DONE;
            } else {
                this.searchIndex = find.end();
                state = State.READY;
            }
            this.state = state;
        }
        return this.state == State.READY;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public PhoneNumberMatch next() {
        if (hasNext()) {
            PhoneNumberMatch phoneNumberMatch = this.lastMatch;
            this.lastMatch = null;
            this.state = State.NOT_READY;
            return phoneNumberMatch;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
