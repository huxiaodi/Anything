package com.test.manage.model.dto;

public class JsTreeStateDto {
    private Boolean opened;
    //private Boolean checked;
    private Boolean selected = false;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

//    public Boolean getChecked() {
//        return checked;
//    }
//
//    public void setChecked(Boolean checked) {
//        this.checked = checked;
//    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public JsTreeStateDto(Boolean opened) {
        this.opened = opened;
    }
}
