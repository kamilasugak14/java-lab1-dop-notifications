package ua.kpi.comsys.dop;

/**
 * Аудіоповідомлення.
 *
 * @param sender          відправник
 * @param durationSeconds тривалість у секундах
 */
public record AudioMsg(String sender, int durationSeconds) implements Notification {

    public AudioMsg {
        Validation.requireNotBlank(sender, "sender");
        Validation.requirePositive(durationSeconds, "durationSeconds");
    }
}
