package com.proofpoint.dlp.service;

import java.util.List;

public class SSNDetector implements IDetector {
    private String type;
    private String pattern;
    @Override
    public boolean detect(String text) {
        return text.matches(pattern);
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
