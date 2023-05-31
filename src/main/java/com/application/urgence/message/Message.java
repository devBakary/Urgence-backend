package com.application.urgence.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Message {
    private String message;

    private Boolean status;

    public static Object set(String message, boolean status) {
        return new Message(message, status);
    }



}