package br.com.itau.pix.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

    @JsonProperty(value = "data")
    private Object data;

    public static Data builder(){
        return new Data();
    }


    public Data data(Object data){
        this.data = data;
        return this;
    }

    public Object getData(){
        return  this.data;
    }
}
