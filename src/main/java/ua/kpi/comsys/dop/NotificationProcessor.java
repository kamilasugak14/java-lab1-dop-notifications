package ua.kpi.comsys.dop;

/**
 * Обробник сповіщень. Містить лише логіку, дані зберігаються в record-ах.
 */
public class NotificationProcessor {

    /**
     * Формує текстове представлення сповіщення.
     *
     * @param notification сповіщення
     * @return відформатований текст
     * @throws IllegalArgumentException якщо notification == null
     */
    public String process(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Notification cannot be null");
        }

        return switch (notification) {
            case TextMsg(String sender, String text) -> """
                    Notification: TEXT
                    From: %s
                    Message: %s
                    """.formatted(sender, text);
            case ImageMsg(String sender, String url, int sizeKb) -> """
                    Notification: IMAGE
                    From: %s
                    URL: %s (%d KB)
                    """.formatted(sender, url, sizeKb);
            case AudioMsg(String sender, int durationSeconds) -> """
                    Notification: AUDIO
                    From: %s
                    Duration: %d seconds
                    """.formatted(sender, durationSeconds);
        };
    }
}
