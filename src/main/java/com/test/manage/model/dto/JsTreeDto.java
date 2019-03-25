package com.test.manage.model.dto;

public class JsTreeDto {
    private String id;
    private String parent;
    private String text;
    private JsTreeStateDto state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public JsTreeStateDto getState() {
        return state;
    }

    public void setState(JsTreeStateDto state) {
        this.state = state;
    }
}
