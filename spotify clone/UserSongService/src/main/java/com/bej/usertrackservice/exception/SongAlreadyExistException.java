package com.bej.usertrackservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND,reason = "Track Already Exist")
public class SongAlreadyExistException extends Exception{
}
