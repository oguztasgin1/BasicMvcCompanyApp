package com.company.exception;

import lombok.Getter;

@Getter
public class BasicCompanyAppProjectException extends RuntimeException{
    private final EErrorType EErrorType;

    public BasicCompanyAppProjectException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }

    public BasicCompanyAppProjectException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }
}
