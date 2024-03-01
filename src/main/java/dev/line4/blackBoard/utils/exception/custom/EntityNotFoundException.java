package dev.line4.blackBoard.utils.exception.custom;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
