package com.proofpoint.dlp.detector.impl;

import com.proofpoint.dlp.detector.IDetector;
import com.proofpoint.dlp.entity.DetectorType;
import org.apache.commons.validator.routines.CreditCardValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardDetector implements IDetector {
    private DetectorType type;
    private Pattern pattern;
    private Pattern contextPattern;
    private CreditCardValidator creditCardValidator;
    @Override
    public boolean detect(String text) {
        if (!contextPattern.matcher(text).find()) {
            return false;
        }
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if (matcher.groupCount() >= 1) {
                String code = matcher.group(1);
                if (creditCardValidator.isValid(code.replaceAll("[\\s|-]+", ""))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public DetectorType getType() {
        return type;
    }

    public void setType(DetectorType type) {
        this.type = type;
    }

    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public void setContextPattern(String contextPattern) {
        this.contextPattern = Pattern.compile(contextPattern);
    }

    public void setCreditCardValidator(CreditCardValidator creditCardValidator) {
        this.creditCardValidator = creditCardValidator;
    }
}
