package Dyplom.rString.model;

import Dyplom.rString.entity.RstringsEntity;

public class rStringModel {
    private String name;
    private String code;


    public static rStringModel toModel(RstringsEntity entity)
    {
        rStringModel model = new rStringModel();
        model.setName(entity.getName());
        model.setCode(entity.getCode());
        return model;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public rStringModel(){

    }
}
