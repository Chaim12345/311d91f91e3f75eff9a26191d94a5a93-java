package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonemetadata;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public class AsYouTypeFormatter {
    private static final int MIN_LEADING_DIGITS_LENGTH = 3;
    private static final char SEPARATOR_BEFORE_NATIONAL_NUMBER = ' ';
    private Phonemetadata.PhoneMetadata currentMetadata;
    private String defaultCountry;
    private Phonemetadata.PhoneMetadata defaultMetadata;
    private static final Phonemetadata.PhoneMetadata EMPTY_METADATA = new Phonemetadata.PhoneMetadata().setInternationalPrefix(AppConstants.SECONDARY_USER_STATE_NA);
    private static final Pattern CHARACTER_CLASS_PATTERN = Pattern.compile("\\[([^\\[\\]])*\\]");
    private static final Pattern STANDALONE_DIGIT_PATTERN = Pattern.compile("\\d(?=[^,}][^,}])");
    private static final Pattern ELIGIBLE_FORMAT_PATTERN = Pattern.compile("[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]*(\\$\\d[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]*)+");
    private static final Pattern NATIONAL_PREFIX_SEPARATORS_PATTERN = Pattern.compile("[- ]");
    private static final String DIGIT_PLACEHOLDER = "\u2008";
    private static final Pattern DIGIT_PATTERN = Pattern.compile(DIGIT_PLACEHOLDER);
    private String currentOutput = "";
    private StringBuilder formattingTemplate = new StringBuilder();
    private String currentFormattingPattern = "";
    private StringBuilder accruedInput = new StringBuilder();
    private StringBuilder accruedInputWithoutFormatting = new StringBuilder();
    private boolean ableToFormat = true;
    private boolean inputHasFormatting = false;
    private boolean isCompleteNumber = false;
    private boolean isExpectingCountryCallingCode = false;
    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    private int lastMatchPosition = 0;
    private int originalPosition = 0;
    private int positionToRemember = 0;
    private StringBuilder prefixBeforeNationalNumber = new StringBuilder();
    private boolean shouldAddSpaceAfterNationalPrefix = false;
    private String extractedNationalPrefix = "";
    private StringBuilder nationalNumber = new StringBuilder();
    private List<Phonemetadata.NumberFormat> possibleFormats = new ArrayList();
    private RegexCache regexCache = new RegexCache(64);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AsYouTypeFormatter(String str) {
        this.defaultCountry = str;
        Phonemetadata.PhoneMetadata metadataForRegion = getMetadataForRegion(str);
        this.currentMetadata = metadataForRegion;
        this.defaultMetadata = metadataForRegion;
    }

    private boolean ableToExtractLongerNdd() {
        if (this.extractedNationalPrefix.length() > 0) {
            this.nationalNumber.insert(0, this.extractedNationalPrefix);
            this.prefixBeforeNationalNumber.setLength(this.prefixBeforeNationalNumber.lastIndexOf(this.extractedNationalPrefix));
        }
        return !this.extractedNationalPrefix.equals(removeNationalPrefixFromNationalNumber());
    }

    private String appendNationalNumber(String str) {
        StringBuilder sb;
        int length = this.prefixBeforeNationalNumber.length();
        if (!this.shouldAddSpaceAfterNationalPrefix || length <= 0 || this.prefixBeforeNationalNumber.charAt(length - 1) == ' ') {
            sb = new StringBuilder();
            sb.append((Object) this.prefixBeforeNationalNumber);
        } else {
            sb = new StringBuilder();
            sb.append(new String(this.prefixBeforeNationalNumber));
            sb.append(' ');
        }
        sb.append(str);
        return sb.toString();
    }

    private String attemptToChooseFormattingPattern() {
        if (this.nationalNumber.length() >= 3) {
            getAvailableFormats(this.nationalNumber.toString());
            String a2 = a();
            return a2.length() > 0 ? a2 : maybeCreateNewTemplate() ? inputAccruedNationalNumber() : this.accruedInput.toString();
        }
        return appendNationalNumber(this.nationalNumber.toString());
    }

    private String attemptToChoosePatternWithPrefixExtracted() {
        this.ableToFormat = true;
        this.isExpectingCountryCallingCode = false;
        this.possibleFormats.clear();
        this.lastMatchPosition = 0;
        this.formattingTemplate.setLength(0);
        this.currentFormattingPattern = "";
        return attemptToChooseFormattingPattern();
    }

    private boolean attemptToExtractCountryCallingCode() {
        StringBuilder sb;
        int c2;
        Phonemetadata.PhoneMetadata metadataForRegion;
        if (this.nationalNumber.length() == 0 || (c2 = this.phoneUtil.c(this.nationalNumber, (sb = new StringBuilder()))) == 0) {
            return false;
        }
        this.nationalNumber.setLength(0);
        this.nationalNumber.append((CharSequence) sb);
        String regionCodeForCountryCode = this.phoneUtil.getRegionCodeForCountryCode(c2);
        if (!PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY.equals(regionCodeForCountryCode)) {
            if (!regionCodeForCountryCode.equals(this.defaultCountry)) {
                metadataForRegion = getMetadataForRegion(regionCodeForCountryCode);
            }
            String num = Integer.toString(c2);
            StringBuilder sb2 = this.prefixBeforeNationalNumber;
            sb2.append(num);
            sb2.append(' ');
            this.extractedNationalPrefix = "";
            return true;
        }
        metadataForRegion = this.phoneUtil.g(c2);
        this.currentMetadata = metadataForRegion;
        String num2 = Integer.toString(c2);
        StringBuilder sb22 = this.prefixBeforeNationalNumber;
        sb22.append(num2);
        sb22.append(' ');
        this.extractedNationalPrefix = "";
        return true;
    }

    private boolean attemptToExtractIdd() {
        RegexCache regexCache = this.regexCache;
        Matcher matcher = regexCache.getPatternForRegex("\\+|" + this.currentMetadata.getInternationalPrefix()).matcher(this.accruedInputWithoutFormatting);
        if (matcher.lookingAt()) {
            this.isCompleteNumber = true;
            int end = matcher.end();
            this.nationalNumber.setLength(0);
            this.nationalNumber.append(this.accruedInputWithoutFormatting.substring(end));
            this.prefixBeforeNationalNumber.setLength(0);
            this.prefixBeforeNationalNumber.append(this.accruedInputWithoutFormatting.substring(0, end));
            if (this.accruedInputWithoutFormatting.charAt(0) != '+') {
                this.prefixBeforeNationalNumber.append(' ');
            }
            return true;
        }
        return false;
    }

    private boolean createFormattingTemplate(Phonemetadata.NumberFormat numberFormat) {
        String pattern = numberFormat.getPattern();
        if (pattern.indexOf(124) != -1) {
            return false;
        }
        String replaceAll = STANDALONE_DIGIT_PATTERN.matcher(CHARACTER_CLASS_PATTERN.matcher(pattern).replaceAll("\\\\d")).replaceAll("\\\\d");
        this.formattingTemplate.setLength(0);
        String formattingTemplate = getFormattingTemplate(replaceAll, numberFormat.getFormat());
        if (formattingTemplate.length() > 0) {
            this.formattingTemplate.append(formattingTemplate);
            return true;
        }
        return false;
    }

    private void getAvailableFormats(String str) {
        List<Phonemetadata.NumberFormat> numberFormats = (!this.isCompleteNumber || this.currentMetadata.intlNumberFormatSize() <= 0) ? this.currentMetadata.numberFormats() : this.currentMetadata.intlNumberFormats();
        boolean hasNationalPrefix = this.currentMetadata.hasNationalPrefix();
        for (Phonemetadata.NumberFormat numberFormat : numberFormats) {
            if (!hasNationalPrefix || this.isCompleteNumber || numberFormat.isNationalPrefixOptionalWhenFormatting() || PhoneNumberUtil.f(numberFormat.getNationalPrefixFormattingRule())) {
                if (isFormatEligible(numberFormat.getFormat())) {
                    this.possibleFormats.add(numberFormat);
                }
            }
        }
        narrowDownPossibleFormats(str);
    }

    private String getFormattingTemplate(String str, String str2) {
        Matcher matcher = this.regexCache.getPatternForRegex(str).matcher("999999999999999");
        matcher.find();
        String group = matcher.group();
        return group.length() < this.nationalNumber.length() ? "" : group.replaceAll(str, str2).replaceAll("9", DIGIT_PLACEHOLDER);
    }

    private Phonemetadata.PhoneMetadata getMetadataForRegion(String str) {
        Phonemetadata.PhoneMetadata h2 = this.phoneUtil.h(this.phoneUtil.getRegionCodeForCountryCode(this.phoneUtil.getCountryCodeForRegion(str)));
        return h2 != null ? h2 : EMPTY_METADATA;
    }

    private String inputAccruedNationalNumber() {
        int length = this.nationalNumber.length();
        if (length > 0) {
            String str = "";
            for (int i2 = 0; i2 < length; i2++) {
                str = inputDigitHelper(this.nationalNumber.charAt(i2));
            }
            return this.ableToFormat ? appendNationalNumber(str) : this.accruedInput.toString();
        }
        return this.prefixBeforeNationalNumber.toString();
    }

    private String inputDigitHelper(char c2) {
        Matcher matcher = DIGIT_PATTERN.matcher(this.formattingTemplate);
        if (!matcher.find(this.lastMatchPosition)) {
            if (this.possibleFormats.size() == 1) {
                this.ableToFormat = false;
            }
            this.currentFormattingPattern = "";
            return this.accruedInput.toString();
        }
        String replaceFirst = matcher.replaceFirst(Character.toString(c2));
        this.formattingTemplate.replace(0, replaceFirst.length(), replaceFirst);
        int start = matcher.start();
        this.lastMatchPosition = start;
        return this.formattingTemplate.substring(0, start + 1);
    }

    private String inputDigitWithOptionToRememberPosition(char c2, boolean z) {
        this.accruedInput.append(c2);
        if (z) {
            this.originalPosition = this.accruedInput.length();
        }
        if (isDigitOrLeadingPlusSign(c2)) {
            c2 = normalizeAndAccrueDigitsAndPlusSign(c2, z);
        } else {
            this.ableToFormat = false;
            this.inputHasFormatting = true;
        }
        if (!this.ableToFormat) {
            if (this.inputHasFormatting) {
                return this.accruedInput.toString();
            }
            if (attemptToExtractIdd()) {
                if (attemptToExtractCountryCallingCode()) {
                    return attemptToChoosePatternWithPrefixExtracted();
                }
            } else if (ableToExtractLongerNdd()) {
                this.prefixBeforeNationalNumber.append(' ');
                return attemptToChoosePatternWithPrefixExtracted();
            }
            return this.accruedInput.toString();
        }
        int length = this.accruedInputWithoutFormatting.length();
        if (length == 0 || length == 1 || length == 2) {
            return this.accruedInput.toString();
        }
        if (length == 3) {
            if (!attemptToExtractIdd()) {
                this.extractedNationalPrefix = removeNationalPrefixFromNationalNumber();
                return attemptToChooseFormattingPattern();
            }
            this.isExpectingCountryCallingCode = true;
        }
        if (this.isExpectingCountryCallingCode) {
            if (attemptToExtractCountryCallingCode()) {
                this.isExpectingCountryCallingCode = false;
            }
            return ((Object) this.prefixBeforeNationalNumber) + this.nationalNumber.toString();
        } else if (this.possibleFormats.size() > 0) {
            String inputDigitHelper = inputDigitHelper(c2);
            String a2 = a();
            if (a2.length() > 0) {
                return a2;
            }
            narrowDownPossibleFormats(this.nationalNumber.toString());
            return maybeCreateNewTemplate() ? inputAccruedNationalNumber() : this.ableToFormat ? appendNationalNumber(inputDigitHelper) : this.accruedInput.toString();
        } else {
            return attemptToChooseFormattingPattern();
        }
    }

    private boolean isDigitOrLeadingPlusSign(char c2) {
        if (Character.isDigit(c2)) {
            return true;
        }
        return this.accruedInput.length() == 1 && PhoneNumberUtil.f10250a.matcher(Character.toString(c2)).matches();
    }

    private boolean isFormatEligible(String str) {
        return ELIGIBLE_FORMAT_PATTERN.matcher(str).matches();
    }

    private boolean isNanpaNumberWithNationalPrefix() {
        return this.currentMetadata.getCountryCode() == 1 && this.nationalNumber.charAt(0) == '1' && this.nationalNumber.charAt(1) != '0' && this.nationalNumber.charAt(1) != '1';
    }

    private boolean maybeCreateNewTemplate() {
        Iterator<Phonemetadata.NumberFormat> it = this.possibleFormats.iterator();
        while (it.hasNext()) {
            Phonemetadata.NumberFormat next = it.next();
            String pattern = next.getPattern();
            if (this.currentFormattingPattern.equals(pattern)) {
                return false;
            }
            if (createFormattingTemplate(next)) {
                this.currentFormattingPattern = pattern;
                this.shouldAddSpaceAfterNationalPrefix = NATIONAL_PREFIX_SEPARATORS_PATTERN.matcher(next.getNationalPrefixFormattingRule()).find();
                this.lastMatchPosition = 0;
                return true;
            }
            it.remove();
        }
        this.ableToFormat = false;
        return false;
    }

    private void narrowDownPossibleFormats(String str) {
        int length = str.length() - 3;
        Iterator<Phonemetadata.NumberFormat> it = this.possibleFormats.iterator();
        while (it.hasNext()) {
            Phonemetadata.NumberFormat next = it.next();
            if (next.leadingDigitsPatternSize() != 0) {
                if (!this.regexCache.getPatternForRegex(next.getLeadingDigitsPattern(Math.min(length, next.leadingDigitsPatternSize() - 1))).matcher(str).lookingAt()) {
                    it.remove();
                }
            }
        }
    }

    private char normalizeAndAccrueDigitsAndPlusSign(char c2, boolean z) {
        StringBuilder sb;
        if (c2 == '+') {
            sb = this.accruedInputWithoutFormatting;
        } else {
            c2 = Character.forDigit(Character.digit(c2, 10), 10);
            this.accruedInputWithoutFormatting.append(c2);
            sb = this.nationalNumber;
        }
        sb.append(c2);
        if (z) {
            this.positionToRemember = this.accruedInputWithoutFormatting.length();
        }
        return c2;
    }

    private String removeNationalPrefixFromNationalNumber() {
        int i2 = 1;
        if (isNanpaNumberWithNationalPrefix()) {
            StringBuilder sb = this.prefixBeforeNationalNumber;
            sb.append('1');
            sb.append(' ');
            this.isCompleteNumber = true;
        } else {
            if (this.currentMetadata.hasNationalPrefixForParsing()) {
                Matcher matcher = this.regexCache.getPatternForRegex(this.currentMetadata.getNationalPrefixForParsing()).matcher(this.nationalNumber);
                if (matcher.lookingAt() && matcher.end() > 0) {
                    this.isCompleteNumber = true;
                    i2 = matcher.end();
                    this.prefixBeforeNationalNumber.append(this.nationalNumber.substring(0, i2));
                }
            }
            i2 = 0;
        }
        String substring = this.nationalNumber.substring(0, i2);
        this.nationalNumber.delete(0, i2);
        return substring;
    }

    String a() {
        for (Phonemetadata.NumberFormat numberFormat : this.possibleFormats) {
            Matcher matcher = this.regexCache.getPatternForRegex(numberFormat.getPattern()).matcher(this.nationalNumber);
            if (matcher.matches()) {
                this.shouldAddSpaceAfterNationalPrefix = NATIONAL_PREFIX_SEPARATORS_PATTERN.matcher(numberFormat.getNationalPrefixFormattingRule()).find();
                return appendNationalNumber(matcher.replaceAll(numberFormat.getFormat()));
            }
        }
        return "";
    }

    public void clear() {
        this.currentOutput = "";
        this.accruedInput.setLength(0);
        this.accruedInputWithoutFormatting.setLength(0);
        this.formattingTemplate.setLength(0);
        this.lastMatchPosition = 0;
        this.currentFormattingPattern = "";
        this.prefixBeforeNationalNumber.setLength(0);
        this.extractedNationalPrefix = "";
        this.nationalNumber.setLength(0);
        this.ableToFormat = true;
        this.inputHasFormatting = false;
        this.positionToRemember = 0;
        this.originalPosition = 0;
        this.isCompleteNumber = false;
        this.isExpectingCountryCallingCode = false;
        this.possibleFormats.clear();
        this.shouldAddSpaceAfterNationalPrefix = false;
        if (this.currentMetadata.equals(this.defaultMetadata)) {
            return;
        }
        this.currentMetadata = getMetadataForRegion(this.defaultCountry);
    }

    public int getRememberedPosition() {
        if (this.ableToFormat) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < this.positionToRemember && i3 < this.currentOutput.length()) {
                if (this.accruedInputWithoutFormatting.charAt(i2) == this.currentOutput.charAt(i3)) {
                    i2++;
                }
                i3++;
            }
            return i3;
        }
        return this.originalPosition;
    }

    public String inputDigit(char c2) {
        String inputDigitWithOptionToRememberPosition = inputDigitWithOptionToRememberPosition(c2, false);
        this.currentOutput = inputDigitWithOptionToRememberPosition;
        return inputDigitWithOptionToRememberPosition;
    }

    public String inputDigitAndRememberPosition(char c2) {
        String inputDigitWithOptionToRememberPosition = inputDigitWithOptionToRememberPosition(c2, true);
        this.currentOutput = inputDigitWithOptionToRememberPosition;
        return inputDigitWithOptionToRememberPosition;
    }
}
