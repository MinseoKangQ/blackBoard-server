package dev.line4.blackBoard.utils.exception.service;

import dev.line4.blackBoard.utils.exception.custom.EntityNotFoundException;

import java.util.Optional;

public class ServiceUtils {

    public static <T> T getEntityOrThrow(Optional<T> optional, String message) {
        return optional.orElseThrow(() -> new EntityNotFoundException(message));
    }
}
