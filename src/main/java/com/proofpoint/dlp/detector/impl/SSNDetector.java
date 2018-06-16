package com.proofpoint.dlp.detector.impl;

import com.proofpoint.dlp.detector.IDetector;
import com.proofpoint.dlp.entity.DetectorType;

import java.util.regex.Pattern;

public class SSNDetector implements IDetector {
    private DetectorType type;
    private Pattern pattern;
    private Pattern contextPattern;
    @Override
    public boolean detect(String text) {
        return contextPattern.matcher(text).find() && pattern.matcher(text).find();
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

    public void setContextPattern(String contextPattern) {
        this.contextPattern = Pattern.compile(contextPattern);
    }
}
