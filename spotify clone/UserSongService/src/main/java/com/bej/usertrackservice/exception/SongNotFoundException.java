package com.bej.usertrackservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Track Not Found")
public class SongNotFoundException extends Exception {
}
