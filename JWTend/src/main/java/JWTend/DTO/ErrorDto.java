package JWTend.DTO;

public class ErrorDto {
    private String message;

    // Prywatny konstruktor, aby wymusić użycie Buildera
    private ErrorDto(Builder builder) {
        this.message = builder.message;
    }

    // Publiczny konstruktor przyjmujący pojedynczy argument
    public ErrorDto(String message) {
        this.message = message;
    }

    // Getter dla pola 'message'
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "message='" + message + '\'' +
                '}';
    }

    // Statyczna metoda 'builder' zwracająca nową instancję Buildera
    public static Builder builder() {
        return new Builder();
    }

    // Statyczna klasa Buildera
    public static class Builder {
        private String message;

        // Metoda ustawiająca wartość pola 'message' w Builderze
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        // Metoda budująca finalny obiekt `ErrorDto`
        public ErrorDto build() {
            return new ErrorDto(this);
        }
    }
}
