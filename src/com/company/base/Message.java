package com.company.base;

import java.io.Serializable;

/**
 * Класс для создания объекта (сообщение)
 * Поля: отправитель, сообщение
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 3529685098267757690L;
    private com.company.base.User sender;
    private String message;

    public Message(com.company.base.User sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public com.company.base.User getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
