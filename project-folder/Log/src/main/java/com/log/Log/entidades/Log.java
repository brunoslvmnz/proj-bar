package com.log.Log.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "logs")
public class Log<T> {
    @Id
    private String id;
    private Action action = Action.NONE;
    private Object object = new Object();

    public Log(T object) {
        this.object = object;
    }
}
