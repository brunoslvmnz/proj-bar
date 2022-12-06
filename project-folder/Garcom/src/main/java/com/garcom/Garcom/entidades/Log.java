package com.garcom.Garcom.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log<T> {
    private String id;
    private Action action = Action.NONE;
    private Object object = new Object();

    public Log(T object) {
        this.object = object;
    }
}
