package com.application.urgence.Autres;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class MessageReponse {

    public static ResponseEntity<Object> Response(String message, HttpStatus status, Object object) {

        Map<String, Object> map = new HashMap<>();

        map.put("Message", message);
        map.put("Statut", status.value());
        map.put("Data", object);

        return new ResponseEntity<Object>(map, status);

    }
}


