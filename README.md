# Notification DOP

Лабораторна робота №1 з дисципліни «Технології розробки на платформі Java»
(КПІ ім. Ігоря Сікорського).

**Тема:** сучасні можливості Java та підхід Data-Oriented Programming (DOP).

## Опис проєкту

Проєкт моделює систему сповіщень за підходом DOP: дані й алгоритми розділені.

- Дані: незмінні `record`-и в закритій ієрархії `sealed interface Notification`.
- Логіка: окремий клас `NotificationProcessor`.
- Результат формується через `switch` expression з pattern matching (без `default`)
  та Text Blocks.

Усі класи розташовані в пакеті `ua.kpi.comsys.dop`.

| Тип | Поля | Валідація |
|-----|------|-----------|
| `TextMsg` | `sender`, `text` | не `null` і не порожні/пробільні |
| `ImageMsg` | `sender`, `url`, `sizeKb` | `sender`, `url` не порожні; `sizeKb > 0` |
| `AudioMsg` | `sender`, `durationSeconds` | `sender` не порожній; `durationSeconds > 0` |

Некоректні дані призводять до `IllegalArgumentException`.
Для `process(null)` викидається `IllegalArgumentException("Notification cannot be null")`.

### Формат виводу

```
Notification: TEXT
From: <sender>
Message: <text>
```

```
Notification: IMAGE
From: <sender>
URL: <url> (<sizeKb> KB)
```

```
Notification: AUDIO
From: <sender>
Duration: <durationSeconds> seconds
```

Кожен результат закінчується символом переведення рядка (особливість Text Block,
у якому закриваючі `"""` розташовані на окремому рядку).

## Варіант

Виконано **варіант 2** (система сповіщень): `TextMsg`, `ImageMsg`, `AudioMsg`
та `NotificationProcessor`. Варіант обрано за погодженням з викладачем.

## Вимоги

- JDK 21 або новіший
- Maven 3.9+

## Структура

```
src/main/java/ua/kpi/comsys/dop/
    Notification.java
    TextMsg.java
    ImageMsg.java
    AudioMsg.java
    Validation.java
    NotificationProcessor.java
src/test/java/ua/kpi/comsys/dop/
    NotificationRecordsTest.java
    NotificationProcessorTest.java
```

## Запуск тестів

```bash
mvn test
```

Тести написані на JUnit 5. Звіт про покриття (JaCoCo) формується після запуску
тестів у `target/site/jacoco/index.html`.

## Автор

Сугак Каміла Романівна, група ІП-42
