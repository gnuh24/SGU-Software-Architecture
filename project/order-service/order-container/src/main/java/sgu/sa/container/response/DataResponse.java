package sgu.sa.container.response;

public record DataResponse<T>(int status, String message, T data) implements Response {

    public static <T> DataResponse<T> success(T data) {
        return new DataResponse<>(200, "Success", data);
    }

    public static <T> DataResponse<T> success(String message, T data) {
        return new DataResponse<>(200, message, data);
    }

    public static DataResponse<Void> successVoid(String message) {
        return new DataResponse<>(200, message, null);
    }

}