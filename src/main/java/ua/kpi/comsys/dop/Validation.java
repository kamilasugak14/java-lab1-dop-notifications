package ua.kpi.comsys.dop;

/**
 * Допоміжні перевірки для конструкторів record-ів.
 */
final class Validation {

    private Validation() {
    }

    static void requireNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be null or blank");
        }
    }

    static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than 0");
        }
    }
}
