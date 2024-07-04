package com.sevenb.retenciones.repository.enums;

public enum RetentionTypeEnum {
    MUNICIPALITY(1L),
    ATM(2L);

    private Long value;
    RetentionTypeEnum(Long value){
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public static RetentionTypeEnum fromDataBase(Long dataType){
        for(RetentionTypeEnum data : values()){
            if(data.getValue().equals(dataType))
                    return data;
        }
        return null;
    }
}
