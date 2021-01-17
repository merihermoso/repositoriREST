package edu.upc.dsa.orm.models.API;

public class UpdateParameterRequest {

    String parameterValue;

    public UpdateParameterRequest() {


    }

    public UpdateParameterRequest(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

}
