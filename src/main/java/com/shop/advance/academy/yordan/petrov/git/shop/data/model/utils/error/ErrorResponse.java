package com.shop.advance.academy.yordan.petrov.git.shop.data.model.utils.error;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Class  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class ErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now();
    private String error;
    private String message;

    /**
     * Constructor
     */
    public ErrorResponse() {
    }

    /**
     * @param timestamp
     * @param error
     * @param message
     */
    public ErrorResponse(LocalDateTime timestamp, String error, String message) {
        this.timestamp = timestamp;
        this.error = error;
        this.message = message;
    }

    /**
     * @param error
     * @param message
     */
    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    /**
     * @return
     */
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * @param timestamp
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return
     */
    public String getError() {
        return this.error;
    }

    /**
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorResponse)) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(error, that.error) &&
                Objects.equals(message, that.message);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(timestamp, error, message);
    }

    /**
     * @return
     */
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
