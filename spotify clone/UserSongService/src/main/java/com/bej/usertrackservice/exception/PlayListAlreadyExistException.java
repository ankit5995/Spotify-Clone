package com.bej.usertrackservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND,reason = "Playlist Already Exist")
public class PlayListAlreadyExistException extends Exception{
}
