//package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@RequiredArgsConstructor
//@NoArgsConstructor
//public class ErrorResponse {
//
//    private LocalDateTime timestamp =LocalDateTime.now();
//    @NonNull
//    private String error;
//
//    @NonNull
//    private String message;
//}



package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;



        import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now();
    private String error;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timestamp, String error, String message) {
        this.timestamp = timestamp;
        this.error = error;
        this.message = message;
    }

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
