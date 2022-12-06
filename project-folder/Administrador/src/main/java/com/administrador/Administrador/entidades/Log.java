package com.administrador.Administrador.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

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
