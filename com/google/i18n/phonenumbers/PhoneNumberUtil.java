package com.google.i18n.phonenumbers;

import com.fasterxml.jackson.core.JsonPointer;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberMatcher;
import com.google.i18n.phonenumbers.Phonemetadata;
import com.google.i18n.phonenumbers.Phonenumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.text.Typography;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.codec.language.Soundex;
import org.apache.http.message.TokenParser;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.slf4j.Marker;
/* loaded from: classes2.dex */
public class PhoneNumberUtil {
    private static final Map<Character, Character> ALL_PLUS_NUMBER_GROUPING_SYMBOLS;
    private static final Map<Character, Character> ALPHA_MAPPINGS;
    private static final Map<Character, Character> ALPHA_PHONE_MAPPINGS;
    private static final Pattern CAPTURING_DIGIT_PATTERN;
    private static final String CAPTURING_EXTN_DIGITS = "(\\p{Nd}{1,7})";
    private static final Pattern CC_PATTERN;
    private static final String COLOMBIA_MOBILE_TO_FIXED_LINE_PREFIX = "3";
    private static final String DEFAULT_EXTN_PREFIX = " ext. ";
    private static final Map<Character, Character> DIALLABLE_CHAR_MAPPINGS;
    private static final String DIGITS = "\\p{Nd}";
    private static final Pattern EXTN_PATTERN;
    private static final String EXTN_PATTERNS_FOR_PARSING;
    private static final Pattern FG_PATTERN;
    private static final Pattern FIRST_GROUP_ONLY_PREFIX_PATTERN;
    private static final Pattern FIRST_GROUP_PATTERN;
    private static final Set<Integer> GEO_MOBILE_COUNTRIES;
    private static final Set<Integer> GEO_MOBILE_COUNTRIES_WITHOUT_MOBILE_AREA_CODES;
    private static final int MAX_INPUT_STRING_LENGTH = 250;
    private static final int MIN_LENGTH_FOR_NSN = 2;
    private static final Map<Integer, String> MOBILE_TOKEN_MAPPINGS;
    private static final int NANPA_COUNTRY_CODE = 1;
    private static final Pattern NP_PATTERN;
    public static final String REGION_CODE_FOR_NON_GEO_ENTITY = "001";
    private static final String RFC3966_EXTN_PREFIX = ";ext=";
    private static final String RFC3966_ISDN_SUBADDRESS = ";isub=";
    private static final String RFC3966_PHONE_CONTEXT = ";phone-context=";
    private static final String RFC3966_PREFIX = "tel:";
    private static final String SECOND_NUMBER_START = "[\\\\/] *x";
    private static final Pattern SEPARATOR_PATTERN;
    private static final char STAR_SIGN = '*';
    private static final Pattern UNIQUE_INTERNATIONAL_PREFIX;
    private static final String UNKNOWN_REGION = "ZZ";
    private static final String UNWANTED_END_CHARS = "[[\\P{N}&&\\P{L}]&&[^#]]+$";
    private static final String VALID_ALPHA;
    private static final Pattern VALID_ALPHA_PHONE_PATTERN;
    private static final String VALID_PHONE_NUMBER;
    private static final Pattern VALID_PHONE_NUMBER_PATTERN;
    private static final String VALID_START_CHAR = "[+＋\\p{Nd}]";
    private static final Pattern VALID_START_CHAR_PATTERN;

    /* renamed from: a  reason: collision with root package name */
    static final Pattern f10250a;

    /* renamed from: b  reason: collision with root package name */
    static final Pattern f10251b;

    /* renamed from: c  reason: collision with root package name */
    static final Pattern f10252c;

    /* renamed from: d  reason: collision with root package name */
    static final String f10253d;

    /* renamed from: e  reason: collision with root package name */
    static final Pattern f10254e;
    private static PhoneNumberUtil instance;
    private static final Logger logger = Logger.getLogger(PhoneNumberUtil.class.getName());
    private final Map<Integer, List<String>> countryCallingCodeToRegionCodeMap;
    private final MetadataSource metadataSource;
    private final Set<String> nanpaRegions = new HashSet(35);
    private final RegexCache regexCache = new RegexCache(100);
    private final Set<String> supportedRegions = new HashSet(320);
    private final Set<Integer> countryCodesForNonGeographicalRegion = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.i18n.phonenumbers.PhoneNumberUtil$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f10260a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f10261b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f10262c;

        static {
            int[] iArr = new int[PhoneNumberType.values().length];
            f10262c = iArr;
            try {
                iArr[PhoneNumberType.PREMIUM_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10262c[PhoneNumberType.TOLL_FREE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10262c[PhoneNumberType.MOBILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10262c[PhoneNumberType.FIXED_LINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10262c[PhoneNumberType.FIXED_LINE_OR_MOBILE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10262c[PhoneNumberType.SHARED_COST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10262c[PhoneNumberType.VOIP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10262c[PhoneNumberType.PERSONAL_NUMBER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f10262c[PhoneNumberType.PAGER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f10262c[PhoneNumberType.UAN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f10262c[PhoneNumberType.VOICEMAIL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr2 = new int[PhoneNumberFormat.values().length];
            f10261b = iArr2;
            try {
                iArr2[PhoneNumberFormat.E164.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f10261b[PhoneNumberFormat.INTERNATIONAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f10261b[PhoneNumberFormat.RFC3966.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f10261b[PhoneNumberFormat.NATIONAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused15) {
            }
            int[] iArr3 = new int[Phonenumber.PhoneNumber.CountryCodeSource.values().length];
            f10260a = iArr3;
            try {
                iArr3[Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f10260a[Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_IDD.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f10260a[Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f10260a[Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum Leniency {
        POSSIBLE { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.1
            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            boolean a(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                return phoneNumberUtil.isPossibleNumber(phoneNumber);
            }
        },
        VALID { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.2
            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            boolean a(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                if (phoneNumberUtil.isValidNumber(phoneNumber) && PhoneNumberMatcher.e(phoneNumber, str, phoneNumberUtil)) {
                    return PhoneNumberMatcher.g(phoneNumber, phoneNumberUtil);
                }
                return false;
            }
        },
        STRICT_GROUPING { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.3
            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            boolean a(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                if (phoneNumberUtil.isValidNumber(phoneNumber) && PhoneNumberMatcher.e(phoneNumber, str, phoneNumberUtil) && !PhoneNumberMatcher.d(phoneNumber, str) && PhoneNumberMatcher.g(phoneNumber, phoneNumberUtil)) {
                    return PhoneNumberMatcher.c(phoneNumber, str, phoneNumberUtil, new PhoneNumberMatcher.NumberGroupingChecker(this) { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.3.1
                        @Override // com.google.i18n.phonenumbers.PhoneNumberMatcher.NumberGroupingChecker
                        public boolean checkGroups(PhoneNumberUtil phoneNumberUtil2, Phonenumber.PhoneNumber phoneNumber2, StringBuilder sb, String[] strArr) {
                            return PhoneNumberMatcher.b(phoneNumberUtil2, phoneNumber2, sb, strArr);
                        }
                    });
                }
                return false;
            }
        },
        EXACT_GROUPING { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.4
            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            boolean a(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                if (phoneNumberUtil.isValidNumber(phoneNumber) && PhoneNumberMatcher.e(phoneNumber, str, phoneNumberUtil) && !PhoneNumberMatcher.d(phoneNumber, str) && PhoneNumberMatcher.g(phoneNumber, phoneNumberUtil)) {
                    return PhoneNumberMatcher.c(phoneNumber, str, phoneNumberUtil, new PhoneNumberMatcher.NumberGroupingChecker(this) { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency.4.1
                        @Override // com.google.i18n.phonenumbers.PhoneNumberMatcher.NumberGroupingChecker
                        public boolean checkGroups(PhoneNumberUtil phoneNumberUtil2, Phonenumber.PhoneNumber phoneNumber2, StringBuilder sb, String[] strArr) {
                            return PhoneNumberMatcher.a(phoneNumberUtil2, phoneNumber2, sb, strArr);
                        }
                    });
                }
                return false;
            }
        };

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract boolean a(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil);
    }

    /* loaded from: classes2.dex */
    public enum MatchType {
        NOT_A_NUMBER,
        NO_MATCH,
        SHORT_NSN_MATCH,
        NSN_MATCH,
        EXACT_MATCH
    }

    /* loaded from: classes2.dex */
    public enum PhoneNumberFormat {
        E164,
        INTERNATIONAL,
        NATIONAL,
        RFC3966
    }

    /* loaded from: classes2.dex */
    public enum PhoneNumberType {
        FIXED_LINE,
        MOBILE,
        FIXED_LINE_OR_MOBILE,
        TOLL_FREE,
        PREMIUM_RATE,
        SHARED_COST,
        VOIP,
        PERSONAL_NUMBER,
        PAGER,
        UAN,
        VOICEMAIL,
        UNKNOWN
    }

    /* loaded from: classes2.dex */
    public enum ValidationResult {
        IS_POSSIBLE,
        INVALID_COUNTRY_CODE,
        TOO_SHORT,
        TOO_LONG
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(52, "1");
        hashMap.put(54, "9");
        MOBILE_TOKEN_MAPPINGS = Collections.unmodifiableMap(hashMap);
        HashSet hashSet = new HashSet();
        hashSet.add(86);
        GEO_MOBILE_COUNTRIES_WITHOUT_MOBILE_AREA_CODES = Collections.unmodifiableSet(hashSet);
        HashSet hashSet2 = new HashSet();
        hashSet2.add(52);
        hashSet2.add(54);
        hashSet2.add(55);
        hashSet2.add(62);
        hashSet2.addAll(hashSet);
        GEO_MOBILE_COUNTRIES = Collections.unmodifiableSet(hashSet2);
        HashMap hashMap2 = new HashMap();
        hashMap2.put('0', '0');
        hashMap2.put('1', '1');
        hashMap2.put('2', '2');
        hashMap2.put('3', '3');
        hashMap2.put('4', '4');
        hashMap2.put('5', '5');
        hashMap2.put('6', '6');
        hashMap2.put('7', '7');
        hashMap2.put('8', '8');
        hashMap2.put('9', '9');
        HashMap hashMap3 = new HashMap(40);
        hashMap3.put('A', '2');
        hashMap3.put('B', '2');
        hashMap3.put('C', '2');
        hashMap3.put('D', '3');
        hashMap3.put('E', '3');
        hashMap3.put('F', '3');
        hashMap3.put('G', '4');
        hashMap3.put('H', '4');
        hashMap3.put('I', '4');
        hashMap3.put('J', '5');
        hashMap3.put('K', '5');
        hashMap3.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT), '5');
        hashMap3.put('M', '6');
        hashMap3.put('N', '6');
        hashMap3.put('O', '6');
        hashMap3.put('P', '7');
        hashMap3.put('Q', '7');
        hashMap3.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR), '7');
        hashMap3.put('S', '7');
        hashMap3.put('T', '8');
        hashMap3.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT), '8');
        hashMap3.put('V', '8');
        hashMap3.put('W', '9');
        hashMap3.put('X', '9');
        hashMap3.put('Y', '9');
        hashMap3.put(Character.valueOf(Matrix.MATRIX_TYPE_ZERO), '9');
        Map<Character, Character> unmodifiableMap = Collections.unmodifiableMap(hashMap3);
        ALPHA_MAPPINGS = unmodifiableMap;
        HashMap hashMap4 = new HashMap(100);
        hashMap4.putAll(unmodifiableMap);
        hashMap4.putAll(hashMap2);
        ALPHA_PHONE_MAPPINGS = Collections.unmodifiableMap(hashMap4);
        HashMap hashMap5 = new HashMap();
        hashMap5.putAll(hashMap2);
        hashMap5.put('+', '+');
        Character valueOf = Character.valueOf(STAR_SIGN);
        hashMap5.put(valueOf, valueOf);
        hashMap5.put('#', '#');
        DIALLABLE_CHAR_MAPPINGS = Collections.unmodifiableMap(hashMap5);
        HashMap hashMap6 = new HashMap();
        for (Character ch : unmodifiableMap.keySet()) {
            char charValue = ch.charValue();
            hashMap6.put(Character.valueOf(Character.toLowerCase(charValue)), Character.valueOf(charValue));
            hashMap6.put(Character.valueOf(charValue), Character.valueOf(charValue));
        }
        hashMap6.putAll(hashMap2);
        hashMap6.put(Character.valueOf(Soundex.SILENT_MARKER), Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 65293, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 8208, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 8209, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 8210, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put(Character.valueOf(Typography.ndash), Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put(Character.valueOf(Typography.mdash), Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 8213, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 8722, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put(Character.valueOf(JsonPointer.SEPARATOR), Character.valueOf(JsonPointer.SEPARATOR));
        hashMap6.put((char) 65295, Character.valueOf(JsonPointer.SEPARATOR));
        hashMap6.put(Character.valueOf(TokenParser.SP), Character.valueOf(TokenParser.SP));
        hashMap6.put((char) 12288, Character.valueOf(TokenParser.SP));
        hashMap6.put((char) 8288, Character.valueOf(TokenParser.SP));
        hashMap6.put('.', '.');
        hashMap6.put((char) 65294, '.');
        ALL_PLUS_NUMBER_GROUPING_SYMBOLS = Collections.unmodifiableMap(hashMap6);
        UNIQUE_INTERNATIONAL_PREFIX = Pattern.compile("[\\d]+(?:[~⁓∼～][\\d]+)?");
        StringBuilder sb = new StringBuilder();
        Map<Character, Character> map = ALPHA_MAPPINGS;
        sb.append(Arrays.toString(map.keySet().toArray()).replaceAll("[, \\[\\]]", ""));
        sb.append(Arrays.toString(map.keySet().toArray()).toLowerCase().replaceAll("[, \\[\\]]", ""));
        String sb2 = sb.toString();
        VALID_ALPHA = sb2;
        f10250a = Pattern.compile("[+＋]+");
        SEPARATOR_PATTERN = Pattern.compile("[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]+");
        CAPTURING_DIGIT_PATTERN = Pattern.compile("(\\p{Nd})");
        VALID_START_CHAR_PATTERN = Pattern.compile(VALID_START_CHAR);
        f10251b = Pattern.compile(SECOND_NUMBER_START);
        f10252c = Pattern.compile(UNWANTED_END_CHARS);
        VALID_ALPHA_PHONE_PATTERN = Pattern.compile("(?:.*?[A-Za-z]){3}.*");
        String str = "\\p{Nd}{2}|[+＋]*+(?:[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*]*\\p{Nd}){3,}[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*" + sb2 + DIGITS + "]*";
        VALID_PHONE_NUMBER = str;
        String createExtnPattern = createExtnPattern(",;xｘ#＃~～");
        EXTN_PATTERNS_FOR_PARSING = createExtnPattern;
        f10253d = createExtnPattern("xｘ#＃~～");
        EXTN_PATTERN = Pattern.compile("(?:" + createExtnPattern + ")$", 66);
        VALID_PHONE_NUMBER_PATTERN = Pattern.compile(str + "(?:" + createExtnPattern + ")?", 66);
        f10254e = Pattern.compile("(\\D+)");
        FIRST_GROUP_PATTERN = Pattern.compile("(\\$\\d)");
        NP_PATTERN = Pattern.compile("\\$NP");
        FG_PATTERN = Pattern.compile("\\$FG");
        CC_PATTERN = Pattern.compile("\\$CC");
        FIRST_GROUP_ONLY_PREFIX_PATTERN = Pattern.compile("\\(?\\$1\\)?");
        instance = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    PhoneNumberUtil(MetadataSource metadataSource, Map map) {
        this.metadataSource = metadataSource;
        this.countryCallingCodeToRegionCodeMap = map;
        for (Map.Entry entry : map.entrySet()) {
            List list = (List) entry.getValue();
            if (list.size() == 1 && REGION_CODE_FOR_NON_GEO_ENTITY.equals(list.get(0))) {
                this.countryCodesForNonGeographicalRegion.add(entry.getKey());
            } else {
                this.supportedRegions.addAll(list);
            }
        }
        if (this.supportedRegions.remove(REGION_CODE_FOR_NON_GEO_ENTITY)) {
            logger.log(Level.WARNING, "invalid metadata (country calling code was mapped to the non-geo entity as well as specific region(s))");
        }
        this.nanpaRegions.addAll((Collection) map.get(1));
    }

    private void buildNationalNumberForParsing(String str, StringBuilder sb) {
        String d2;
        int indexOf = str.indexOf(RFC3966_PHONE_CONTEXT);
        if (indexOf > 0) {
            int i2 = indexOf + 15;
            if (str.charAt(i2) == '+') {
                int indexOf2 = str.indexOf(59, i2);
                sb.append(indexOf2 > 0 ? str.substring(i2, indexOf2) : str.substring(i2));
            }
            int indexOf3 = str.indexOf(RFC3966_PREFIX);
            d2 = str.substring(indexOf3 >= 0 ? indexOf3 + 4 : 0, indexOf);
        } else {
            d2 = d(str);
        }
        sb.append(d2);
        int indexOf4 = sb.indexOf(RFC3966_ISDN_SUBADDRESS);
        if (indexOf4 > 0) {
            sb.delete(indexOf4, sb.length());
        }
    }

    private boolean checkRegionForParsing(String str, String str2) {
        if (isValidRegionCode(str2)) {
            return true;
        }
        return (str == null || str.length() == 0 || !f10250a.matcher(str).lookingAt()) ? false : true;
    }

    public static String convertAlphaCharactersInNumber(String str) {
        return normalizeHelper(str, ALPHA_PHONE_MAPPINGS, false);
    }

    private static String createExtnPattern(String str) {
        return ";ext=(\\p{Nd}{1,7})|[  \\t,]*(?:e?xt(?:ensi(?:ó?|ó))?n?|ｅ?ｘｔｎ?|[" + str + "]|int|anexo|ｉｎｔ)[:\\.．]?[  \\t,-]*" + CAPTURING_EXTN_DIGITS + "#?|[- ]+(" + DIGITS + "{1,5})#";
    }

    public static PhoneNumberUtil createInstance(MetadataLoader metadataLoader) {
        if (metadataLoader != null) {
            return createInstance(new MultiFileMetadataSourceImpl(metadataLoader));
        }
        throw new IllegalArgumentException("metadataLoader could not be null.");
    }

    private static PhoneNumberUtil createInstance(MetadataSource metadataSource) {
        if (metadataSource != null) {
            return new PhoneNumberUtil(metadataSource, CountryCodeToRegionCodeMap.a());
        }
        throw new IllegalArgumentException("metadataSource could not be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String d(String str) {
        Matcher matcher = VALID_START_CHAR_PATTERN.matcher(str);
        if (matcher.find()) {
            String substring = str.substring(matcher.start());
            Matcher matcher2 = f10252c.matcher(substring);
            if (matcher2.find()) {
                substring = substring.substring(0, matcher2.start());
                Logger logger2 = logger;
                Level level = Level.FINER;
                logger2.log(level, "Stripped trailing characters: " + substring);
            }
            Matcher matcher3 = f10251b.matcher(substring);
            return matcher3.find() ? substring.substring(0, matcher3.start()) : substring;
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(String str) {
        return str.length() == 0 || FIRST_GROUP_ONLY_PREFIX_PATTERN.matcher(str).matches();
    }

    private String formatNsn(String str, Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberFormat phoneNumberFormat) {
        return formatNsn(str, phoneMetadata, phoneNumberFormat, null);
    }

    private String formatNsn(String str, Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberFormat phoneNumberFormat, String str2) {
        Phonemetadata.NumberFormat b2 = b((phoneMetadata.intlNumberFormats().size() == 0 || phoneNumberFormat == PhoneNumberFormat.NATIONAL) ? phoneMetadata.numberFormats() : phoneMetadata.intlNumberFormats(), str);
        return b2 == null ? str : formatNsnUsingPattern(str, b2, phoneNumberFormat, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String formatNsnUsingPattern(String str, Phonemetadata.NumberFormat numberFormat, PhoneNumberFormat phoneNumberFormat, String str2) {
        String nationalPrefixFormattingRule;
        String replaceAll;
        String format = numberFormat.getFormat();
        Matcher matcher = this.regexCache.getPatternForRegex(numberFormat.getPattern()).matcher(str);
        PhoneNumberFormat phoneNumberFormat2 = PhoneNumberFormat.NATIONAL;
        if (phoneNumberFormat != phoneNumberFormat2 || str2 == null || str2.length() <= 0 || numberFormat.getDomesticCarrierCodeFormattingRule().length() <= 0) {
            nationalPrefixFormattingRule = numberFormat.getNationalPrefixFormattingRule();
            if (phoneNumberFormat != phoneNumberFormat2 || nationalPrefixFormattingRule == null || nationalPrefixFormattingRule.length() <= 0) {
                replaceAll = matcher.replaceAll(format);
                if (phoneNumberFormat != PhoneNumberFormat.RFC3966) {
                    Matcher matcher2 = SEPARATOR_PATTERN.matcher(replaceAll);
                    if (matcher2.lookingAt()) {
                        replaceAll = matcher2.replaceFirst("");
                    }
                    return matcher2.reset(replaceAll).replaceAll(HelpFormatter.DEFAULT_OPT_PREFIX);
                }
                return replaceAll;
            }
        } else {
            nationalPrefixFormattingRule = CC_PATTERN.matcher(numberFormat.getDomesticCarrierCodeFormattingRule()).replaceFirst(str2);
        }
        replaceAll = matcher.replaceAll(FIRST_GROUP_PATTERN.matcher(format).replaceFirst(nationalPrefixFormattingRule));
        if (phoneNumberFormat != PhoneNumberFormat.RFC3966) {
        }
    }

    private int getCountryCodeForValidRegion(String str) {
        Phonemetadata.PhoneMetadata h2 = h(str);
        if (h2 != null) {
            return h2.getCountryCode();
        }
        throw new IllegalArgumentException("Invalid region code: " + str);
    }

    public static String getCountryMobileToken(int i2) {
        Map<Integer, String> map = MOBILE_TOKEN_MAPPINGS;
        return map.containsKey(Integer.valueOf(i2)) ? map.get(Integer.valueOf(i2)) : "";
    }

    public static synchronized PhoneNumberUtil getInstance() {
        PhoneNumberUtil phoneNumberUtil;
        synchronized (PhoneNumberUtil.class) {
            if (instance == null) {
                t(createInstance(MetadataManager.f10249a));
            }
            phoneNumberUtil = instance;
        }
        return phoneNumberUtil;
    }

    private Phonemetadata.PhoneMetadata getMetadataForRegionOrCallingCode(int i2, String str) {
        return REGION_CODE_FOR_NON_GEO_ENTITY.equals(str) ? g(i2) : h(str);
    }

    private PhoneNumberType getNumberTypeHelper(String str, Phonemetadata.PhoneMetadata phoneMetadata) {
        if (k(str, phoneMetadata.getGeneralDesc())) {
            if (k(str, phoneMetadata.getPremiumRate())) {
                return PhoneNumberType.PREMIUM_RATE;
            }
            if (k(str, phoneMetadata.getTollFree())) {
                return PhoneNumberType.TOLL_FREE;
            }
            if (k(str, phoneMetadata.getSharedCost())) {
                return PhoneNumberType.SHARED_COST;
            }
            if (k(str, phoneMetadata.getVoip())) {
                return PhoneNumberType.VOIP;
            }
            if (k(str, phoneMetadata.getPersonalNumber())) {
                return PhoneNumberType.PERSONAL_NUMBER;
            }
            if (k(str, phoneMetadata.getPager())) {
                return PhoneNumberType.PAGER;
            }
            if (k(str, phoneMetadata.getUan())) {
                return PhoneNumberType.UAN;
            }
            if (k(str, phoneMetadata.getVoicemail())) {
                return PhoneNumberType.VOICEMAIL;
            }
            if (!k(str, phoneMetadata.getFixedLine())) {
                return (phoneMetadata.isSameMobileAndFixedLinePattern() || !k(str, phoneMetadata.getMobile())) ? PhoneNumberType.UNKNOWN : PhoneNumberType.MOBILE;
            }
            if (!phoneMetadata.isSameMobileAndFixedLinePattern() && !k(str, phoneMetadata.getMobile())) {
                return PhoneNumberType.FIXED_LINE;
            }
            return PhoneNumberType.FIXED_LINE_OR_MOBILE;
        }
        return PhoneNumberType.UNKNOWN;
    }

    private String getRegionCodeForNumberFromRegionList(Phonenumber.PhoneNumber phoneNumber, List<String> list) {
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        for (String str : list) {
            Phonemetadata.PhoneMetadata h2 = h(str);
            if (h2.hasLeadingDigits()) {
                if (this.regexCache.getPatternForRegex(h2.getLeadingDigits()).matcher(nationalSignificantNumber).lookingAt()) {
                    return str;
                }
            } else if (getNumberTypeHelper(nationalSignificantNumber, h2) != PhoneNumberType.UNKNOWN) {
                return str;
            }
        }
        return null;
    }

    private boolean hasFormattingPatternForNumber(Phonenumber.PhoneNumber phoneNumber) {
        int countryCode = phoneNumber.getCountryCode();
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
        if (metadataForRegionOrCallingCode == null) {
            return false;
        }
        return b(metadataForRegionOrCallingCode.numberFormats(), getNationalSignificantNumber(phoneNumber)) != null;
    }

    private boolean hasUnexpectedItalianLeadingZero(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumber.isItalianLeadingZero() && !j(phoneNumber.getCountryCode());
    }

    private boolean hasValidCountryCallingCode(int i2) {
        return this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(i2));
    }

    private boolean isNationalNumberSuffixOfTheOther(Phonenumber.PhoneNumber phoneNumber, Phonenumber.PhoneNumber phoneNumber2) {
        String valueOf = String.valueOf(phoneNumber.getNationalNumber());
        String valueOf2 = String.valueOf(phoneNumber2.getNationalNumber());
        return valueOf.endsWith(valueOf2) || valueOf2.endsWith(valueOf);
    }

    private boolean isValidRegionCode(String str) {
        return str != null && this.supportedRegions.contains(str);
    }

    static boolean l(String str) {
        if (str.length() < 2) {
            return false;
        }
        return VALID_PHONE_NUMBER_PATTERN.matcher(str).matches();
    }

    private void maybeAppendFormattedExtension(Phonenumber.PhoneNumber phoneNumber, Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        if (!phoneNumber.hasExtension() || phoneNumber.getExtension().length() <= 0) {
            return;
        }
        sb.append(phoneNumberFormat == PhoneNumberFormat.RFC3966 ? RFC3966_EXTN_PREFIX : phoneMetadata.hasPreferredExtnPrefix() ? phoneMetadata.getPreferredExtnPrefix() : DEFAULT_EXTN_PREFIX);
        sb.append(phoneNumber.getExtension());
    }

    public static String normalizeDiallableCharsOnly(String str) {
        return normalizeHelper(str, DIALLABLE_CHAR_MAPPINGS, true);
    }

    public static String normalizeDigitsOnly(String str) {
        return s(str, false).toString();
    }

    private static String normalizeHelper(String str, Map<Character, Character> map, boolean z) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            Character ch = map.get(Character.valueOf(Character.toUpperCase(charAt)));
            if (ch != null) {
                sb.append(ch);
            } else if (!z) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    private void parseHelper(String str, String str2, boolean z, boolean z2, Phonenumber.PhoneNumber phoneNumber) {
        int m2;
        if (str == null) {
            throw new NumberParseException(NumberParseException.ErrorType.NOT_A_NUMBER, "The phone number supplied was null.");
        }
        if (str.length() > 250) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_LONG, "The string supplied was too long to parse.");
        }
        StringBuilder sb = new StringBuilder();
        buildNationalNumberForParsing(str, sb);
        if (!l(sb.toString())) {
            throw new NumberParseException(NumberParseException.ErrorType.NOT_A_NUMBER, "The string supplied did not seem to be a phone number.");
        }
        if (z2 && !checkRegionForParsing(sb.toString(), str2)) {
            throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Missing or invalid default region.");
        }
        if (z) {
            phoneNumber.setRawInput(str);
        }
        String n2 = n(sb);
        if (n2.length() > 0) {
            phoneNumber.setExtension(n2);
        }
        Phonemetadata.PhoneMetadata h2 = h(str2);
        StringBuilder sb2 = new StringBuilder();
        try {
            m2 = m(sb.toString(), h2, sb2, z, phoneNumber);
        } catch (NumberParseException e2) {
            Matcher matcher = f10250a.matcher(sb.toString());
            NumberParseException.ErrorType errorType = e2.getErrorType();
            NumberParseException.ErrorType errorType2 = NumberParseException.ErrorType.INVALID_COUNTRY_CODE;
            if (errorType != errorType2 || !matcher.lookingAt()) {
                throw new NumberParseException(e2.getErrorType(), e2.getMessage());
            }
            m2 = m(sb.substring(matcher.end()), h2, sb2, z, phoneNumber);
            if (m2 == 0) {
                throw new NumberParseException(errorType2, "Could not interpret numbers after plus-sign.");
            }
        }
        if (m2 != 0) {
            String regionCodeForCountryCode = getRegionCodeForCountryCode(m2);
            if (!regionCodeForCountryCode.equals(str2)) {
                h2 = getMetadataForRegionOrCallingCode(m2, regionCodeForCountryCode);
            }
        } else {
            r(sb);
            sb2.append((CharSequence) sb);
            if (str2 != null) {
                phoneNumber.setCountryCode(h2.getCountryCode());
            } else if (z) {
                phoneNumber.clearCountryCodeSource();
            }
        }
        if (sb2.length() < 2) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
        }
        if (h2 != null) {
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder(sb2);
            p(sb4, h2, sb3);
            if (testNumberLength(sb4.toString(), h2.getGeneralDesc()) != ValidationResult.TOO_SHORT) {
                if (z && sb3.length() > 0) {
                    phoneNumber.setPreferredDomesticCarrierCode(sb3.toString());
                }
                sb2 = sb4;
            }
        }
        int length = sb2.length();
        if (length < 2) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
        }
        if (length > 17) {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_LONG, "The string supplied is too long to be a phone number.");
        }
        u(sb2.toString(), phoneNumber);
        phoneNumber.setNationalNumber(Long.parseLong(sb2.toString()));
    }

    private boolean parsePrefixAsIdd(Pattern pattern, StringBuilder sb) {
        Matcher matcher = pattern.matcher(sb);
        if (matcher.lookingAt()) {
            int end = matcher.end();
            Matcher matcher2 = CAPTURING_DIGIT_PATTERN.matcher(sb.substring(end));
            if (matcher2.find() && normalizeDigitsOnly(matcher2.group(1)).equals("0")) {
                return false;
            }
            sb.delete(0, end);
            return true;
        }
        return false;
    }

    private void prefixNumberWithCountryCallingCode(int i2, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        int i3 = AnonymousClass2.f10261b[phoneNumberFormat.ordinal()];
        if (i3 == 1) {
            sb.insert(0, i2).insert(0, '+');
        } else if (i3 == 2) {
            sb.insert(0, " ").insert(0, i2).insert(0, '+');
        } else if (i3 != 3) {
        } else {
            sb.insert(0, HelpFormatter.DEFAULT_OPT_PREFIX).insert(0, i2).insert(0, '+').insert(0, RFC3966_PREFIX);
        }
    }

    static String q(String str) {
        return VALID_ALPHA_PHONE_PATTERN.matcher(str).matches() ? normalizeHelper(str, ALPHA_PHONE_MAPPINGS, true) : normalizeDigitsOnly(str);
    }

    static void r(StringBuilder sb) {
        sb.replace(0, sb.length(), q(sb.toString()));
    }

    private boolean rawInputContainsNationalPrefix(String str, String str2, String str3) {
        String normalizeDigitsOnly = normalizeDigitsOnly(str);
        if (normalizeDigitsOnly.startsWith(str2)) {
            try {
                return isValidNumber(parse(normalizeDigitsOnly.substring(str2.length()), str3));
            } catch (NumberParseException unused) {
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StringBuilder s(String str, boolean z) {
        char[] charArray;
        StringBuilder sb = new StringBuilder(str.length());
        for (char c2 : str.toCharArray()) {
            int digit = Character.digit(c2, 10);
            if (digit != -1) {
                sb.append(digit);
            } else if (z) {
                sb.append(c2);
            }
        }
        return sb;
    }

    static synchronized void t(PhoneNumberUtil phoneNumberUtil) {
        synchronized (PhoneNumberUtil.class) {
            instance = phoneNumberUtil;
        }
    }

    private ValidationResult testNumberLength(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        int intValue;
        List<Integer> possibleLengthList = phoneNumberDesc.getPossibleLengthList();
        List<Integer> possibleLengthLocalOnlyList = phoneNumberDesc.getPossibleLengthLocalOnlyList();
        int length = str.length();
        if (!possibleLengthLocalOnlyList.contains(Integer.valueOf(length)) && (intValue = possibleLengthList.get(0).intValue()) != length) {
            if (intValue > length) {
                return ValidationResult.TOO_SHORT;
            }
            if (possibleLengthList.get(possibleLengthList.size() - 1).intValue() >= length && possibleLengthList.subList(1, possibleLengthList.size()).contains(Integer.valueOf(length))) {
                return ValidationResult.IS_POSSIBLE;
            }
            return ValidationResult.TOO_LONG;
        }
        return ValidationResult.IS_POSSIBLE;
    }

    static void u(String str, Phonenumber.PhoneNumber phoneNumber) {
        if (str.length() <= 1 || str.charAt(0) != '0') {
            return;
        }
        phoneNumber.setItalianLeadingZero(true);
        int i2 = 1;
        while (i2 < str.length() - 1 && str.charAt(i2) == '0') {
            i2++;
        }
        if (i2 != 1) {
            phoneNumber.setNumberOfLeadingZeros(i2);
        }
    }

    boolean a(Phonenumber.PhoneNumber phoneNumber) {
        Phonemetadata.PhoneMetadata h2 = h(getRegionCodeForNumber(phoneNumber));
        if (h2 == null) {
            return true;
        }
        return !k(getNationalSignificantNumber(phoneNumber), h2.getNoInternationalDialling());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Phonemetadata.NumberFormat b(List list, String str) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Phonemetadata.NumberFormat numberFormat = (Phonemetadata.NumberFormat) it.next();
            int leadingDigitsPatternSize = numberFormat.leadingDigitsPatternSize();
            if (leadingDigitsPatternSize == 0 || this.regexCache.getPatternForRegex(numberFormat.getLeadingDigitsPattern(leadingDigitsPatternSize - 1)).matcher(str).lookingAt()) {
                if (this.regexCache.getPatternForRegex(numberFormat.getPattern()).matcher(str).matches()) {
                    return numberFormat;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(StringBuilder sb, StringBuilder sb2) {
        if (sb.length() != 0 && sb.charAt(0) != '0') {
            int length = sb.length();
            for (int i2 = 1; i2 <= 3 && i2 <= length; i2++) {
                int parseInt = Integer.parseInt(sb.substring(0, i2));
                if (this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(parseInt))) {
                    sb2.append(sb.substring(i2));
                    return parseInt;
                }
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String e(String str, Phonemetadata.NumberFormat numberFormat, PhoneNumberFormat phoneNumberFormat) {
        return formatNsnUsingPattern(str, numberFormat, phoneNumberFormat, null);
    }

    public Iterable<PhoneNumberMatch> findNumbers(CharSequence charSequence, String str) {
        return findNumbers(charSequence, str, Leniency.VALID, LongCompanionObject.MAX_VALUE);
    }

    public Iterable<PhoneNumberMatch> findNumbers(final CharSequence charSequence, final String str, final Leniency leniency, final long j2) {
        return new Iterable<PhoneNumberMatch>() { // from class: com.google.i18n.phonenumbers.PhoneNumberUtil.1
            @Override // java.lang.Iterable
            public Iterator<PhoneNumberMatch> iterator() {
                return new PhoneNumberMatcher(PhoneNumberUtil.this, charSequence, str, leniency, j2);
            }
        };
    }

    public String format(Phonenumber.PhoneNumber phoneNumber, PhoneNumberFormat phoneNumberFormat) {
        if (phoneNumber.getNationalNumber() == 0 && phoneNumber.hasRawInput()) {
            String rawInput = phoneNumber.getRawInput();
            if (rawInput.length() > 0) {
                return rawInput;
            }
        }
        StringBuilder sb = new StringBuilder(20);
        format(phoneNumber, phoneNumberFormat, sb);
        return sb.toString();
    }

    public void format(Phonenumber.PhoneNumber phoneNumber, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        sb.setLength(0);
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        PhoneNumberFormat phoneNumberFormat2 = PhoneNumberFormat.E164;
        if (phoneNumberFormat == phoneNumberFormat2) {
            sb.append(nationalSignificantNumber);
            prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat2, sb);
        } else if (!hasValidCountryCallingCode(countryCode)) {
            sb.append(nationalSignificantNumber);
        } else {
            Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
            sb.append(formatNsn(nationalSignificantNumber, metadataForRegionOrCallingCode, phoneNumberFormat));
            maybeAppendFormattedExtension(phoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb);
            prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb);
        }
    }

    public String formatByPattern(Phonenumber.PhoneNumber phoneNumber, PhoneNumberFormat phoneNumberFormat, List<Phonemetadata.NumberFormat> list) {
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (hasValidCountryCallingCode(countryCode)) {
            Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
            StringBuilder sb = new StringBuilder(20);
            Phonemetadata.NumberFormat b2 = b(list, nationalSignificantNumber);
            if (b2 == null) {
                sb.append(nationalSignificantNumber);
            } else {
                Phonemetadata.NumberFormat.Builder newBuilder = Phonemetadata.NumberFormat.newBuilder();
                newBuilder.mergeFrom(b2);
                String nationalPrefixFormattingRule = b2.getNationalPrefixFormattingRule();
                if (nationalPrefixFormattingRule.length() > 0) {
                    String nationalPrefix = metadataForRegionOrCallingCode.getNationalPrefix();
                    if (nationalPrefix.length() > 0) {
                        newBuilder.setNationalPrefixFormattingRule(FG_PATTERN.matcher(NP_PATTERN.matcher(nationalPrefixFormattingRule).replaceFirst(nationalPrefix)).replaceFirst("\\$1"));
                    } else {
                        newBuilder.clearNationalPrefixFormattingRule();
                    }
                }
                sb.append(e(nationalSignificantNumber, newBuilder, phoneNumberFormat));
            }
            maybeAppendFormattedExtension(phoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb);
            prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb);
            return sb.toString();
        }
        return nationalSignificantNumber;
    }

    public String formatInOriginalFormat(Phonenumber.PhoneNumber phoneNumber, String str) {
        String format;
        String nationalPrefixFormattingRule;
        int indexOf;
        if (!phoneNumber.hasRawInput() || (!hasUnexpectedItalianLeadingZero(phoneNumber) && hasFormattingPatternForNumber(phoneNumber))) {
            if (phoneNumber.hasCountryCodeSource()) {
                int i2 = AnonymousClass2.f10260a[phoneNumber.getCountryCodeSource().ordinal()];
                if (i2 == 1) {
                    format = format(phoneNumber, PhoneNumberFormat.INTERNATIONAL);
                } else if (i2 == 2) {
                    format = formatOutOfCountryCallingNumber(phoneNumber, str);
                } else if (i2 != 3) {
                    String regionCodeForCountryCode = getRegionCodeForCountryCode(phoneNumber.getCountryCode());
                    String nddPrefixForRegion = getNddPrefixForRegion(regionCodeForCountryCode, true);
                    PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.NATIONAL;
                    format = format(phoneNumber, phoneNumberFormat);
                    if (nddPrefixForRegion != null && nddPrefixForRegion.length() != 0 && !rawInputContainsNationalPrefix(phoneNumber.getRawInput(), nddPrefixForRegion, regionCodeForCountryCode)) {
                        Phonemetadata.NumberFormat b2 = b(h(regionCodeForCountryCode).numberFormats(), getNationalSignificantNumber(phoneNumber));
                        if (b2 != null && (indexOf = (nationalPrefixFormattingRule = b2.getNationalPrefixFormattingRule()).indexOf("$1")) > 0 && normalizeDigitsOnly(nationalPrefixFormattingRule.substring(0, indexOf)).length() != 0) {
                            Phonemetadata.NumberFormat.Builder newBuilder = Phonemetadata.NumberFormat.newBuilder();
                            newBuilder.mergeFrom(b2);
                            newBuilder.clearNationalPrefixFormattingRule();
                            List<Phonemetadata.NumberFormat> arrayList = new ArrayList<>(1);
                            arrayList.add(newBuilder);
                            format = formatByPattern(phoneNumber, phoneNumberFormat, arrayList);
                        }
                    }
                } else {
                    format = format(phoneNumber, PhoneNumberFormat.INTERNATIONAL).substring(1);
                }
                String rawInput = phoneNumber.getRawInput();
                return (format == null || rawInput.length() <= 0 || normalizeDiallableCharsOnly(format).equals(normalizeDiallableCharsOnly(rawInput))) ? format : rawInput;
            }
            return format(phoneNumber, PhoneNumberFormat.NATIONAL);
        }
        return phoneNumber.getRawInput();
    }

    public String formatNationalNumberWithCarrierCode(Phonenumber.PhoneNumber phoneNumber, String str) {
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (hasValidCountryCallingCode(countryCode)) {
            Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
            StringBuilder sb = new StringBuilder(20);
            PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.NATIONAL;
            sb.append(formatNsn(nationalSignificantNumber, metadataForRegionOrCallingCode, phoneNumberFormat, str));
            maybeAppendFormattedExtension(phoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb);
            prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb);
            return sb.toString();
        }
        return nationalSignificantNumber;
    }

    public String formatNationalNumberWithPreferredCarrierCode(Phonenumber.PhoneNumber phoneNumber, String str) {
        if (phoneNumber.getPreferredDomesticCarrierCode().length() > 0) {
            str = phoneNumber.getPreferredDomesticCarrierCode();
        }
        return formatNationalNumberWithCarrierCode(phoneNumber, str);
    }

    public String formatNumberForMobileDialing(Phonenumber.PhoneNumber phoneNumber, String str, boolean z) {
        PhoneNumberFormat phoneNumberFormat;
        String format;
        int countryCode = phoneNumber.getCountryCode();
        String str2 = "";
        if (!hasValidCountryCallingCode(countryCode)) {
            return phoneNumber.hasRawInput() ? phoneNumber.getRawInput() : "";
        }
        Phonenumber.PhoneNumber clearExtension = new Phonenumber.PhoneNumber().mergeFrom(phoneNumber).clearExtension();
        String regionCodeForCountryCode = getRegionCodeForCountryCode(countryCode);
        PhoneNumberType numberType = getNumberType(clearExtension);
        boolean z2 = false;
        boolean z3 = numberType != PhoneNumberType.UNKNOWN;
        if (str.equals(regionCodeForCountryCode)) {
            PhoneNumberType phoneNumberType = PhoneNumberType.FIXED_LINE;
            if (numberType == phoneNumberType || numberType == PhoneNumberType.MOBILE || numberType == PhoneNumberType.FIXED_LINE_OR_MOBILE) {
                z2 = true;
            }
            if (regionCodeForCountryCode.equals("CO") && numberType == phoneNumberType) {
                format = formatNationalNumberWithCarrierCode(clearExtension, "3");
            } else if (regionCodeForCountryCode.equals("BR") && z2) {
                if (clearExtension.getPreferredDomesticCarrierCode().length() > 0) {
                    str2 = formatNationalNumberWithPreferredCarrierCode(clearExtension, "");
                }
            } else if (z3 && regionCodeForCountryCode.equals("HU")) {
                format = getNddPrefixForRegion(regionCodeForCountryCode, true) + " " + format(clearExtension, PhoneNumberFormat.NATIONAL);
            } else {
                if (countryCode == 1) {
                    phoneNumberFormat = (!a(clearExtension) || testNumberLength(getNationalSignificantNumber(clearExtension), h(str).getGeneralDesc()) == ValidationResult.TOO_SHORT) ? PhoneNumberFormat.NATIONAL : PhoneNumberFormat.INTERNATIONAL;
                } else {
                    phoneNumberFormat = ((regionCodeForCountryCode.equals(REGION_CODE_FOR_NON_GEO_ENTITY) || ((regionCodeForCountryCode.equals("MX") || regionCodeForCountryCode.equals("CL")) && z2)) && a(clearExtension)) ? PhoneNumberFormat.INTERNATIONAL : PhoneNumberFormat.NATIONAL;
                }
                format = format(clearExtension, phoneNumberFormat);
            }
            str2 = format;
        } else if (z3 && a(clearExtension)) {
            return format(clearExtension, z ? PhoneNumberFormat.INTERNATIONAL : PhoneNumberFormat.E164);
        }
        return z ? str2 : normalizeDiallableCharsOnly(str2);
    }

    public String formatOutOfCountryCallingNumber(Phonenumber.PhoneNumber phoneNumber, String str) {
        if (!isValidRegionCode(str)) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            logger2.log(level, "Trying to format number from invalid region " + str + ". International formatting applied.");
            return format(phoneNumber, PhoneNumberFormat.INTERNATIONAL);
        }
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (hasValidCountryCallingCode(countryCode)) {
            if (countryCode == 1) {
                if (isNANPACountry(str)) {
                    return countryCode + " " + format(phoneNumber, PhoneNumberFormat.NATIONAL);
                }
            } else if (countryCode == getCountryCodeForValidRegion(str)) {
                return format(phoneNumber, PhoneNumberFormat.NATIONAL);
            }
            Phonemetadata.PhoneMetadata h2 = h(str);
            String internationalPrefix = h2.getInternationalPrefix();
            if (!UNIQUE_INTERNATIONAL_PREFIX.matcher(internationalPrefix).matches()) {
                internationalPrefix = h2.hasPreferredInternationalPrefix() ? h2.getPreferredInternationalPrefix() : "";
            }
            Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
            PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.INTERNATIONAL;
            StringBuilder sb = new StringBuilder(formatNsn(nationalSignificantNumber, metadataForRegionOrCallingCode, phoneNumberFormat));
            maybeAppendFormattedExtension(phoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb);
            if (internationalPrefix.length() > 0) {
                sb.insert(0, " ").insert(0, countryCode).insert(0, " ").insert(0, internationalPrefix);
            } else {
                prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb);
            }
            return sb.toString();
        }
        return nationalSignificantNumber;
    }

    public String formatOutOfCountryKeepingAlphaChars(Phonenumber.PhoneNumber phoneNumber, String str) {
        String str2;
        int indexOf;
        String rawInput = phoneNumber.getRawInput();
        if (rawInput.length() == 0) {
            return formatOutOfCountryCallingNumber(phoneNumber, str);
        }
        int countryCode = phoneNumber.getCountryCode();
        if (hasValidCountryCallingCode(countryCode)) {
            String normalizeHelper = normalizeHelper(rawInput, ALL_PLUS_NUMBER_GROUPING_SYMBOLS, true);
            String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
            if (nationalSignificantNumber.length() > 3 && (indexOf = normalizeHelper.indexOf(nationalSignificantNumber.substring(0, 3))) != -1) {
                normalizeHelper = normalizeHelper.substring(indexOf);
            }
            Phonemetadata.PhoneMetadata h2 = h(str);
            if (countryCode == 1) {
                if (isNANPACountry(str)) {
                    return countryCode + " " + normalizeHelper;
                }
            } else if (h2 != null && countryCode == getCountryCodeForValidRegion(str)) {
                Phonemetadata.NumberFormat b2 = b(h2.numberFormats(), nationalSignificantNumber);
                if (b2 == null) {
                    return normalizeHelper;
                }
                Phonemetadata.NumberFormat.Builder newBuilder = Phonemetadata.NumberFormat.newBuilder();
                newBuilder.mergeFrom(b2);
                newBuilder.setPattern("(\\d+)(.*)");
                newBuilder.setFormat("$1$2");
                return e(normalizeHelper, newBuilder, PhoneNumberFormat.NATIONAL);
            }
            if (h2 != null) {
                str2 = h2.getInternationalPrefix();
                if (!UNIQUE_INTERNATIONAL_PREFIX.matcher(str2).matches()) {
                    str2 = h2.getPreferredInternationalPrefix();
                }
            } else {
                str2 = "";
            }
            StringBuilder sb = new StringBuilder(normalizeHelper);
            Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
            PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.INTERNATIONAL;
            maybeAppendFormattedExtension(phoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb);
            if (str2.length() > 0) {
                sb.insert(0, " ").insert(0, countryCode).insert(0, " ").insert(0, str2);
            } else {
                if (!isValidRegionCode(str)) {
                    Logger logger2 = logger;
                    Level level = Level.WARNING;
                    logger2.log(level, "Trying to format number from invalid region " + str + ". International formatting applied.");
                }
                prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb);
            }
            return sb.toString();
        }
        return rawInput;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Phonemetadata.PhoneMetadata g(int i2) {
        if (this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(i2))) {
            return this.metadataSource.getMetadataForNonGeographicalRegion(i2);
        }
        return null;
    }

    public AsYouTypeFormatter getAsYouTypeFormatter(String str) {
        return new AsYouTypeFormatter(str);
    }

    public int getCountryCodeForRegion(String str) {
        if (isValidRegionCode(str)) {
            return getCountryCodeForValidRegion(str);
        }
        Logger logger2 = logger;
        Level level = Level.WARNING;
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid or missing region code (");
        if (str == null) {
            str = "null";
        }
        sb.append(str);
        sb.append(") provided.");
        logger2.log(level, sb.toString());
        return 0;
    }

    public Phonenumber.PhoneNumber getExampleNumber(String str) {
        return getExampleNumberForType(str, PhoneNumberType.FIXED_LINE);
    }

    public Phonenumber.PhoneNumber getExampleNumberForNonGeoEntity(int i2) {
        Phonemetadata.PhoneMetadata g2 = g(i2);
        if (g2 == null) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            logger2.log(level, "Invalid or unknown country calling code provided: " + i2);
            return null;
        }
        for (Phonemetadata.PhoneNumberDesc phoneNumberDesc : Arrays.asList(g2.getMobile(), g2.getTollFree(), g2.getSharedCost(), g2.getVoip(), g2.getVoicemail(), g2.getUan(), g2.getPremiumRate())) {
            if (phoneNumberDesc != null) {
                try {
                    if (phoneNumberDesc.hasExampleNumber()) {
                        return parse(Marker.ANY_NON_NULL_MARKER + i2 + phoneNumberDesc.getExampleNumber(), UNKNOWN_REGION);
                    }
                    continue;
                } catch (NumberParseException e2) {
                    logger.log(Level.SEVERE, e2.toString());
                }
            }
        }
        return null;
    }

    public Phonenumber.PhoneNumber getExampleNumberForType(PhoneNumberType phoneNumberType) {
        for (String str : getSupportedRegions()) {
            Phonenumber.PhoneNumber exampleNumberForType = getExampleNumberForType(str, phoneNumberType);
            if (exampleNumberForType != null) {
                return exampleNumberForType;
            }
        }
        for (Integer num : getSupportedGlobalNetworkCallingCodes()) {
            int intValue = num.intValue();
            Phonemetadata.PhoneNumberDesc i2 = i(g(intValue), phoneNumberType);
            try {
            } catch (NumberParseException e2) {
                logger.log(Level.SEVERE, e2.toString());
            }
            if (i2.hasExampleNumber()) {
                return parse(Marker.ANY_NON_NULL_MARKER + intValue + i2.getExampleNumber(), UNKNOWN_REGION);
            }
            continue;
        }
        return null;
    }

    public Phonenumber.PhoneNumber getExampleNumberForType(String str, PhoneNumberType phoneNumberType) {
        if (isValidRegionCode(str)) {
            Phonemetadata.PhoneNumberDesc i2 = i(h(str), phoneNumberType);
            try {
                if (i2.hasExampleNumber()) {
                    return parse(i2.getExampleNumber(), str);
                }
            } catch (NumberParseException e2) {
                logger.log(Level.SEVERE, e2.toString());
            }
            return null;
        }
        Logger logger2 = logger;
        Level level = Level.WARNING;
        logger2.log(level, "Invalid or unknown region code provided: " + str);
        return null;
    }

    /*  JADX ERROR: NullPointerException in pass: BlockProcessor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.BlockNode.getPredecessors()" because "to" is null
        	at jadx.core.dex.visitors.blocks.BlockSplitter.connect(BlockSplitter.java:150)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectSplittersAndHandlers(BlockExceptionHandler.java:457)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.wrapBlocksWithTryCatch(BlockExceptionHandler.java:358)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectExcHandlers(BlockExceptionHandler.java:84)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.process(BlockExceptionHandler.java:59)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.independentBlockTreeMod(BlockProcessor.java:318)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:46)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public com.google.i18n.phonenumbers.Phonenumber.PhoneNumber getInvalidExampleNumber(java.lang.String r6) {
        /*
            r5 = this;
            boolean r0 = r5.isValidRegionCode(r6)
            r1 = 0
            if (r0 != 0) goto L20
            java.util.logging.Logger r0 = com.google.i18n.phonenumbers.PhoneNumberUtil.logger
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Invalid or unknown region code provided: "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            r0.log(r2, r6)
            return r1
        L20:
            com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r0 = r5.h(r6)
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r2 = com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType.FIXED_LINE
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r0 = r5.i(r0, r2)
            boolean r2 = r0.hasExampleNumber()
            if (r2 != 0) goto L31
            return r1
        L31:
            java.lang.String r0 = r0.getExampleNumber()
            int r2 = r0.length()
            int r2 = r2 + (-1)
        L3b:
            r3 = 2
            if (r2 < r3) goto L51
            r3 = 0
            java.lang.String r3 = r0.substring(r3, r2)
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r3 = r5.parse(r3, r6)     // Catch: com.google.i18n.phonenumbers.NumberParseException -> L4e
            boolean r4 = r5.isValidNumber(r3)     // Catch: com.google.i18n.phonenumbers.NumberParseException -> L4e
            if (r4 != 0) goto L4e
            return r3
        L4e:
            int r2 = r2 + (-1)
            goto L3b
        L51:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.PhoneNumberUtil.getInvalidExampleNumber(java.lang.String):com.google.i18n.phonenumbers.Phonenumber$PhoneNumber");
    }

    public int getLengthOfGeographicalAreaCode(Phonenumber.PhoneNumber phoneNumber) {
        Phonemetadata.PhoneMetadata h2 = h(getRegionCodeForNumber(phoneNumber));
        if (h2 == null) {
            return 0;
        }
        if (h2.hasNationalPrefix() || phoneNumber.isItalianLeadingZero()) {
            PhoneNumberType numberType = getNumberType(phoneNumber);
            int countryCode = phoneNumber.getCountryCode();
            if (!(numberType == PhoneNumberType.MOBILE && GEO_MOBILE_COUNTRIES_WITHOUT_MOBILE_AREA_CODES.contains(Integer.valueOf(countryCode))) && isNumberGeographical(numberType, countryCode)) {
                return getLengthOfNationalDestinationCode(phoneNumber);
            }
            return 0;
        }
        return 0;
    }

    public int getLengthOfNationalDestinationCode(Phonenumber.PhoneNumber phoneNumber) {
        Phonenumber.PhoneNumber phoneNumber2;
        if (phoneNumber.hasExtension()) {
            phoneNumber2 = new Phonenumber.PhoneNumber();
            phoneNumber2.mergeFrom(phoneNumber);
            phoneNumber2.clearExtension();
        } else {
            phoneNumber2 = phoneNumber;
        }
        String[] split = f10254e.split(format(phoneNumber2, PhoneNumberFormat.INTERNATIONAL));
        if (split.length <= 3) {
            return 0;
        }
        return (getNumberType(phoneNumber) != PhoneNumberType.MOBILE || getCountryMobileToken(phoneNumber.getCountryCode()).equals("")) ? split[2].length() : split[2].length() + split[3].length();
    }

    public String getNationalSignificantNumber(Phonenumber.PhoneNumber phoneNumber) {
        StringBuilder sb = new StringBuilder();
        if (phoneNumber.isItalianLeadingZero()) {
            char[] cArr = new char[phoneNumber.getNumberOfLeadingZeros()];
            Arrays.fill(cArr, '0');
            sb.append(new String(cArr));
        }
        sb.append(phoneNumber.getNationalNumber());
        return sb.toString();
    }

    public String getNddPrefixForRegion(String str, boolean z) {
        Phonemetadata.PhoneMetadata h2 = h(str);
        if (h2 != null) {
            String nationalPrefix = h2.getNationalPrefix();
            if (nationalPrefix.length() == 0) {
                return null;
            }
            return z ? nationalPrefix.replace("~", "") : nationalPrefix;
        }
        Logger logger2 = logger;
        Level level = Level.WARNING;
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid or missing region code (");
        if (str == null) {
            str = "null";
        }
        sb.append(str);
        sb.append(") provided.");
        logger2.log(level, sb.toString());
        return null;
    }

    public PhoneNumberType getNumberType(Phonenumber.PhoneNumber phoneNumber) {
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(phoneNumber.getCountryCode(), getRegionCodeForNumber(phoneNumber));
        return metadataForRegionOrCallingCode == null ? PhoneNumberType.UNKNOWN : getNumberTypeHelper(getNationalSignificantNumber(phoneNumber), metadataForRegionOrCallingCode);
    }

    public String getRegionCodeForCountryCode(int i2) {
        List<String> list = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(i2));
        return list == null ? UNKNOWN_REGION : list.get(0);
    }

    public String getRegionCodeForNumber(Phonenumber.PhoneNumber phoneNumber) {
        int countryCode = phoneNumber.getCountryCode();
        List<String> list = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(countryCode));
        if (list != null) {
            return list.size() == 1 ? list.get(0) : getRegionCodeForNumberFromRegionList(phoneNumber, list);
        }
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        Logger logger2 = logger;
        Level level = Level.INFO;
        logger2.log(level, "Missing/invalid country_code (" + countryCode + ") for number " + nationalSignificantNumber);
        return null;
    }

    public List<String> getRegionCodesForCountryCode(int i2) {
        List<String> list = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(i2));
        if (list == null) {
            list = new ArrayList<>(0);
        }
        return Collections.unmodifiableList(list);
    }

    public Set<Integer> getSupportedGlobalNetworkCallingCodes() {
        return Collections.unmodifiableSet(this.countryCodesForNonGeographicalRegion);
    }

    public Set<String> getSupportedRegions() {
        return Collections.unmodifiableSet(this.supportedRegions);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Phonemetadata.PhoneMetadata h(String str) {
        if (isValidRegionCode(str)) {
            return this.metadataSource.getMetadataForRegion(str);
        }
        return null;
    }

    Phonemetadata.PhoneNumberDesc i(Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberType phoneNumberType) {
        switch (AnonymousClass2.f10262c[phoneNumberType.ordinal()]) {
            case 1:
                return phoneMetadata.getPremiumRate();
            case 2:
                return phoneMetadata.getTollFree();
            case 3:
                return phoneMetadata.getMobile();
            case 4:
            case 5:
                return phoneMetadata.getFixedLine();
            case 6:
                return phoneMetadata.getSharedCost();
            case 7:
                return phoneMetadata.getVoip();
            case 8:
                return phoneMetadata.getPersonalNumber();
            case 9:
                return phoneMetadata.getPager();
            case 10:
                return phoneMetadata.getUan();
            case 11:
                return phoneMetadata.getVoicemail();
            default:
                return phoneMetadata.getGeneralDesc();
        }
    }

    public boolean isAlphaNumber(String str) {
        if (l(str)) {
            StringBuilder sb = new StringBuilder(str);
            n(sb);
            return VALID_ALPHA_PHONE_PATTERN.matcher(sb).matches();
        }
        return false;
    }

    public boolean isMobileNumberPortableRegion(String str) {
        Phonemetadata.PhoneMetadata h2 = h(str);
        if (h2 == null) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            logger2.log(level, "Invalid or unknown region code provided: " + str);
            return false;
        }
        return h2.isMobileNumberPortableRegion();
    }

    public boolean isNANPACountry(String str) {
        return this.nanpaRegions.contains(str);
    }

    public boolean isNumberGeographical(PhoneNumberType phoneNumberType, int i2) {
        return phoneNumberType == PhoneNumberType.FIXED_LINE || phoneNumberType == PhoneNumberType.FIXED_LINE_OR_MOBILE || (GEO_MOBILE_COUNTRIES.contains(Integer.valueOf(i2)) && phoneNumberType == PhoneNumberType.MOBILE);
    }

    public boolean isNumberGeographical(Phonenumber.PhoneNumber phoneNumber) {
        return isNumberGeographical(getNumberType(phoneNumber), phoneNumber.getCountryCode());
    }

    public MatchType isNumberMatch(Phonenumber.PhoneNumber phoneNumber, Phonenumber.PhoneNumber phoneNumber2) {
        Phonenumber.PhoneNumber phoneNumber3 = new Phonenumber.PhoneNumber();
        phoneNumber3.mergeFrom(phoneNumber);
        Phonenumber.PhoneNumber phoneNumber4 = new Phonenumber.PhoneNumber();
        phoneNumber4.mergeFrom(phoneNumber2);
        phoneNumber3.clearRawInput();
        phoneNumber3.clearCountryCodeSource();
        phoneNumber3.clearPreferredDomesticCarrierCode();
        phoneNumber4.clearRawInput();
        phoneNumber4.clearCountryCodeSource();
        phoneNumber4.clearPreferredDomesticCarrierCode();
        if (phoneNumber3.hasExtension() && phoneNumber3.getExtension().length() == 0) {
            phoneNumber3.clearExtension();
        }
        if (phoneNumber4.hasExtension() && phoneNumber4.getExtension().length() == 0) {
            phoneNumber4.clearExtension();
        }
        if (phoneNumber3.hasExtension() && phoneNumber4.hasExtension() && !phoneNumber3.getExtension().equals(phoneNumber4.getExtension())) {
            return MatchType.NO_MATCH;
        }
        int countryCode = phoneNumber3.getCountryCode();
        int countryCode2 = phoneNumber4.getCountryCode();
        if (countryCode != 0 && countryCode2 != 0) {
            return phoneNumber3.exactlySameAs(phoneNumber4) ? MatchType.EXACT_MATCH : (countryCode == countryCode2 && isNationalNumberSuffixOfTheOther(phoneNumber3, phoneNumber4)) ? MatchType.SHORT_NSN_MATCH : MatchType.NO_MATCH;
        }
        phoneNumber3.setCountryCode(countryCode2);
        return phoneNumber3.exactlySameAs(phoneNumber4) ? MatchType.NSN_MATCH : isNationalNumberSuffixOfTheOther(phoneNumber3, phoneNumber4) ? MatchType.SHORT_NSN_MATCH : MatchType.NO_MATCH;
    }

    public MatchType isNumberMatch(Phonenumber.PhoneNumber phoneNumber, String str) {
        try {
            return isNumberMatch(phoneNumber, parse(str, UNKNOWN_REGION));
        } catch (NumberParseException e2) {
            if (e2.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                String regionCodeForCountryCode = getRegionCodeForCountryCode(phoneNumber.getCountryCode());
                try {
                    if (!regionCodeForCountryCode.equals(UNKNOWN_REGION)) {
                        MatchType isNumberMatch = isNumberMatch(phoneNumber, parse(str, regionCodeForCountryCode));
                        return isNumberMatch == MatchType.EXACT_MATCH ? MatchType.NSN_MATCH : isNumberMatch;
                    }
                    Phonenumber.PhoneNumber phoneNumber2 = new Phonenumber.PhoneNumber();
                    parseHelper(str, null, false, false, phoneNumber2);
                    return isNumberMatch(phoneNumber, phoneNumber2);
                } catch (NumberParseException unused) {
                    return MatchType.NOT_A_NUMBER;
                }
            }
            return MatchType.NOT_A_NUMBER;
        }
    }

    public MatchType isNumberMatch(String str, String str2) {
        try {
            return isNumberMatch(parse(str, UNKNOWN_REGION), str2);
        } catch (NumberParseException e2) {
            if (e2.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                try {
                    return isNumberMatch(parse(str2, UNKNOWN_REGION), str);
                } catch (NumberParseException e3) {
                    if (e3.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                        try {
                            Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
                            Phonenumber.PhoneNumber phoneNumber2 = new Phonenumber.PhoneNumber();
                            parseHelper(str, null, false, false, phoneNumber);
                            parseHelper(str2, null, false, false, phoneNumber2);
                            return isNumberMatch(phoneNumber, phoneNumber2);
                        } catch (NumberParseException unused) {
                            return MatchType.NOT_A_NUMBER;
                        }
                    }
                    return MatchType.NOT_A_NUMBER;
                }
            }
            return MatchType.NOT_A_NUMBER;
        }
    }

    public boolean isPossibleNumber(Phonenumber.PhoneNumber phoneNumber) {
        return isPossibleNumberWithReason(phoneNumber) == ValidationResult.IS_POSSIBLE;
    }

    public boolean isPossibleNumber(String str, String str2) {
        try {
            return isPossibleNumber(parse(str, str2));
        } catch (NumberParseException unused) {
            return false;
        }
    }

    public ValidationResult isPossibleNumberWithReason(Phonenumber.PhoneNumber phoneNumber) {
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        int countryCode = phoneNumber.getCountryCode();
        return !hasValidCountryCallingCode(countryCode) ? ValidationResult.INVALID_COUNTRY_CODE : testNumberLength(nationalSignificantNumber, getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode)).getGeneralDesc());
    }

    public boolean isValidNumber(Phonenumber.PhoneNumber phoneNumber) {
        return isValidNumberForRegion(phoneNumber, getRegionCodeForNumber(phoneNumber));
    }

    public boolean isValidNumberForRegion(Phonenumber.PhoneNumber phoneNumber, String str) {
        int countryCode = phoneNumber.getCountryCode();
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, str);
        if (metadataForRegionOrCallingCode != null) {
            return (REGION_CODE_FOR_NON_GEO_ENTITY.equals(str) || countryCode == getCountryCodeForValidRegion(str)) && getNumberTypeHelper(getNationalSignificantNumber(phoneNumber), metadataForRegionOrCallingCode) != PhoneNumberType.UNKNOWN;
        }
        return false;
    }

    boolean j(int i2) {
        Phonemetadata.PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(i2, getRegionCodeForCountryCode(i2));
        if (metadataForRegionOrCallingCode == null) {
            return false;
        }
        return metadataForRegionOrCallingCode.isLeadingZeroPossible();
    }

    boolean k(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        int length = str.length();
        List<Integer> possibleLengthList = phoneNumberDesc.getPossibleLengthList();
        if (possibleLengthList.size() <= 0 || possibleLengthList.contains(Integer.valueOf(length))) {
            return this.regexCache.getPatternForRegex(phoneNumberDesc.getNationalNumberPattern()).matcher(str).matches();
        }
        return false;
    }

    int m(String str, Phonemetadata.PhoneMetadata phoneMetadata, StringBuilder sb, boolean z, Phonenumber.PhoneNumber phoneNumber) {
        if (str.length() == 0) {
            return 0;
        }
        StringBuilder sb2 = new StringBuilder(str);
        Phonenumber.PhoneNumber.CountryCodeSource o2 = o(sb2, phoneMetadata != null ? phoneMetadata.getInternationalPrefix() : "NonMatch");
        if (z) {
            phoneNumber.setCountryCodeSource(o2);
        }
        if (o2 != Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            if (sb2.length() > 2) {
                int c2 = c(sb2, sb);
                if (c2 != 0) {
                    phoneNumber.setCountryCode(c2);
                    return c2;
                }
                throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Country calling code supplied was not recognised.");
            }
            throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_AFTER_IDD, "Phone number had an IDD, but after this was not long enough to be a viable phone number.");
        }
        if (phoneMetadata != null) {
            int countryCode = phoneMetadata.getCountryCode();
            String valueOf = String.valueOf(countryCode);
            String sb3 = sb2.toString();
            if (sb3.startsWith(valueOf)) {
                StringBuilder sb4 = new StringBuilder(sb3.substring(valueOf.length()));
                Phonemetadata.PhoneNumberDesc generalDesc = phoneMetadata.getGeneralDesc();
                Pattern patternForRegex = this.regexCache.getPatternForRegex(generalDesc.getNationalNumberPattern());
                p(sb4, phoneMetadata, null);
                if ((!patternForRegex.matcher(sb2).matches() && patternForRegex.matcher(sb4).matches()) || testNumberLength(sb2.toString(), generalDesc) == ValidationResult.TOO_LONG) {
                    sb.append((CharSequence) sb4);
                    if (z) {
                        phoneNumber.setCountryCodeSource(Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN);
                    }
                    phoneNumber.setCountryCode(countryCode);
                    return countryCode;
                }
            }
        }
        phoneNumber.setCountryCode(0);
        return 0;
    }

    String n(StringBuilder sb) {
        Matcher matcher = EXTN_PATTERN.matcher(sb);
        if (matcher.find() && l(sb.substring(0, matcher.start()))) {
            int groupCount = matcher.groupCount();
            for (int i2 = 1; i2 <= groupCount; i2++) {
                if (matcher.group(i2) != null) {
                    String group = matcher.group(i2);
                    sb.delete(matcher.start(), sb.length());
                    return group;
                }
            }
            return "";
        }
        return "";
    }

    Phonenumber.PhoneNumber.CountryCodeSource o(StringBuilder sb, String str) {
        if (sb.length() == 0) {
            return Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY;
        }
        Matcher matcher = f10250a.matcher(sb);
        if (matcher.lookingAt()) {
            sb.delete(0, matcher.end());
            r(sb);
            return Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN;
        }
        Pattern patternForRegex = this.regexCache.getPatternForRegex(str);
        r(sb);
        return parsePrefixAsIdd(patternForRegex, sb) ? Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_IDD : Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean p(StringBuilder sb, Phonemetadata.PhoneMetadata phoneMetadata, StringBuilder sb2) {
        int length = sb.length();
        String nationalPrefixForParsing = phoneMetadata.getNationalPrefixForParsing();
        if (length != 0 && nationalPrefixForParsing.length() != 0) {
            Matcher matcher = this.regexCache.getPatternForRegex(nationalPrefixForParsing).matcher(sb);
            if (matcher.lookingAt()) {
                Pattern patternForRegex = this.regexCache.getPatternForRegex(phoneMetadata.getGeneralDesc().getNationalNumberPattern());
                boolean matches = patternForRegex.matcher(sb).matches();
                int groupCount = matcher.groupCount();
                String nationalPrefixTransformRule = phoneMetadata.getNationalPrefixTransformRule();
                if (nationalPrefixTransformRule == null || nationalPrefixTransformRule.length() == 0 || matcher.group(groupCount) == null) {
                    if (!matches || patternForRegex.matcher(sb.substring(matcher.end())).matches()) {
                        if (sb2 != null && groupCount > 0 && matcher.group(groupCount) != null) {
                            sb2.append(matcher.group(1));
                        }
                        sb.delete(0, matcher.end());
                        return true;
                    }
                    return false;
                }
                StringBuilder sb3 = new StringBuilder(sb);
                sb3.replace(0, length, matcher.replaceFirst(nationalPrefixTransformRule));
                if (!matches || patternForRegex.matcher(sb3.toString()).matches()) {
                    if (sb2 != null && groupCount > 1) {
                        sb2.append(matcher.group(1));
                    }
                    sb.replace(0, sb.length(), sb3.toString());
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public Phonenumber.PhoneNumber parse(String str, String str2) {
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        parse(str, str2, phoneNumber);
        return phoneNumber;
    }

    public void parse(String str, String str2, Phonenumber.PhoneNumber phoneNumber) {
        parseHelper(str, str2, false, true, phoneNumber);
    }

    public Phonenumber.PhoneNumber parseAndKeepRawInput(String str, String str2) {
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        parseAndKeepRawInput(str, str2, phoneNumber);
        return phoneNumber;
    }

    public void parseAndKeepRawInput(String str, String str2, Phonenumber.PhoneNumber phoneNumber) {
        parseHelper(str, str2, true, true, phoneNumber);
    }

    public boolean truncateTooLongNumber(Phonenumber.PhoneNumber phoneNumber) {
        if (isValidNumber(phoneNumber)) {
            return true;
        }
        Phonenumber.PhoneNumber phoneNumber2 = new Phonenumber.PhoneNumber();
        phoneNumber2.mergeFrom(phoneNumber);
        long nationalNumber = phoneNumber.getNationalNumber();
        do {
            nationalNumber /= 10;
            phoneNumber2.setNationalNumber(nationalNumber);
            if (isPossibleNumberWithReason(phoneNumber2) == ValidationResult.TOO_SHORT || nationalNumber == 0) {
                return false;
            }
        } while (!isValidNumber(phoneNumber2));
        phoneNumber.setNationalNumber(nationalNumber);
        return true;
    }
}
