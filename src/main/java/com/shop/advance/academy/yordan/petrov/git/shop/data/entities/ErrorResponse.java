package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorResponse)) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(error, that.error) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, error, message);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorResponse{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", error='").append(error).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
