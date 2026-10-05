package ua.kpi.comsys.dop;

/**
 * Сповіщення. Закрита ієрархія: текстове, зображення або аудіо.
 */
public sealed interface Notification permits TextMsg, ImageMsg, AudioMsg {
}
