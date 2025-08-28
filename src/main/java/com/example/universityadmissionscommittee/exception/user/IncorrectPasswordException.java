package com.example.universityadmissionscommittee.exception.user;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }

  public IncorrectPasswordException() {
      super("Невірний пароль");
  }
}
