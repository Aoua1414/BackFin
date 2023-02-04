package com.aoua.medoc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    private String message;
    private Boolean status;

    public static Messages set(String message, boolean status) {
        return new Messages(message, status);
    }
}
