package com.proofpoint.dlp.detector.impl;

import com.proofpoint.dlp.detector.IDetector;
import com.proofpoint.dlp.entity.DetectorType;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IBANDetector implements IDetector {
    private DetectorType type;
    private Pattern pattern;
    @Override
    public boolean detect(String text) {
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if (matcher.groupCount() >= 1) {
                String code = matcher.group(1);
                if (IBANCheckDigit.IBAN_CHECK_DIGIT.isValid(code.replaceAll("\\s+", ""))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public DetectorType getType() {
        return type;
    }

    public void setType(DetectorType type) {
        this.type = type;
    }
}
