package com.stone.EasyTeaching.models;

/**
 * Created by hangli2 on 2015/6/25.
 */
public class ContactsModel {
    private String id;//用户ID
    private String name;//姓名
    private String intro;//简介
    private String headerUrl;//头像url

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }
}
