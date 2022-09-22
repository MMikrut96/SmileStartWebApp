package pl.edu.wat.projektai.ai.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private HttpStatus status;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

     public ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
