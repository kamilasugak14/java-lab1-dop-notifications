package ua.kpi.comsys.dop;

/**
 * Повідомлення із зображенням.
 *
 * @param sender відправник
 * @param url    посилання на зображення
 * @param sizeKb розмір у кілобайтах
 */
public record ImageMsg(String sender, String url, int sizeKb) implements Notification {

    public ImageMsg {
        Validation.requireNotBlank(sender, "sender");
        Validation.requireNotBlank(url, "url");
        Validation.requirePositive(sizeKb, "sizeKb");
    }
}
