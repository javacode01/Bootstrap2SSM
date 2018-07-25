package com.sys.model;

public class SysOrganDesign {
    private String recid;

    private String organLevel;

    private String organLevelName;

    private String nextLevel;

    private String haveUser;

    private String iconUrl;

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    public String getOrganLevel() {
        return organLevel;
    }

    public void setOrganLevel(String organLevel) {
        this.organLevel = organLevel;
    }

    public String getOrganLevelName() {
        return organLevelName;
    }

    public void setOrganLevelName(String organLevelName) {
        this.organLevelName = organLevelName;
    }

    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }

    public String getHaveUser() {
        return haveUser;
    }

    public void setHaveUser(String haveUser) {
        this.haveUser = haveUser;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}