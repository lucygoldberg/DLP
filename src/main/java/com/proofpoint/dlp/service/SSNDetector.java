package com.proofpoint.dlp.service;

public class SSNDetector implements IDetector {
    private DetectorType type;
    private String pattern;
    private String contextPattern;
    @Override
    public boolean detect(String text) {
        return text.matches(contextPattern) && text.matches(pattern);
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public DetectorType getType() {
        return type;
    }

    public void setType(DetectorType type) {
        this.type = type;
    }

    public void setContextPattern(String contextPattern) {
        this.contextPattern = contextPattern;
    }
}
