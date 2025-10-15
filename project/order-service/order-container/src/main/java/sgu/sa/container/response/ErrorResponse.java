package sgu.sa.container.response;

public record ErrorResponse(int status, String error) implements Response { }
