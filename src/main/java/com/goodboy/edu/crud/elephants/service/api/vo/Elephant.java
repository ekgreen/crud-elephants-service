package com.goodboy.edu.crud.elephants.service.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Elephant {

    /**
     * Уникальный идентификатор слона
     */
    private Long id;

    /**
     * Имя (кличка) слона
     */
    private String name;

    /**
     * Возвраст слона
     */
    private Integer age;

    /**
     * Тип слона
     */
    private Type type;

    /**
     * Длина хобота
     */
    private Short trunkLength;

    @Data
    @Accessors(chain = true)
    public static class Type{
        private String value;
        private String description;
    }
}
