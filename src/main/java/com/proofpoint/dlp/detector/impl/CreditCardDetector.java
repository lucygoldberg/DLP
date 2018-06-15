package com.proofpoint.dlp.detector.impl;

import com.proofpoint.dlp.detector.IDetector;
import com.proofpoint.dlp.entity.DetectorType;
import org.apache.commons.validator.routines.CreditCardValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardDetector implements IDetector {
    private DetectorType type;
    private Pattern pattern;
    private String contextPattern;
    private CreditCardValidator creditCardValidator;
    @Override
    public boolean detect(String text) {
        if (!text.matches(contextPattern)) {
            return false;
        }
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            if (matcher.groupCount() >= 1) {
                String code = matcher.group(1);
                return creditCardValidator.isValid(code.replaceAll("[\\s|-]+", ""));
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
        this.contextPattern = contextPattern;
    }

    public void setCreditCardValidator(CreditCardValidator creditCardValidator) {
        this.creditCardValidator = creditCardValidator;
    }
}
