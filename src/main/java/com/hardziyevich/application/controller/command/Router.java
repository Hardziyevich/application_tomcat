package com.hardziyevich.application.controller.command;

public enum Router {
    FORWARD,
    REDIRECT;
    private String pagePath;

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(final String pagePath) {
        this.pagePath = pagePath;
    }
}
