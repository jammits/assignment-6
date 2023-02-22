package com.coderscampus.assignment6;

public enum TeslaModel {
    MODEL3("model3"),
    MODELS("modelS"),
    MODELX("modelX");

    private String modelName;
    TeslaModel(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
