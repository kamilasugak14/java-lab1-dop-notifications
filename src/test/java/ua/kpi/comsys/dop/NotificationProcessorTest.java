package ua.kpi.comsys.dop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotificationProcessorTest {

    private NotificationProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new NotificationProcessor();
    }

    @Test
    void processesTextMessage() {
        String expected = """
                Notification: TEXT
                From: Alice
                Message: Hello, world!
                """;
        assertEquals(expected, processor.process(new TextMsg("Alice", "Hello, world!")));
    }

    @Test
    void processesImageMessage() {
        String expected = """
                Notification: IMAGE
                From: Bob
                URL: https://example.com/photo.png (256 KB)
                """;
        assertEquals(expected,
                processor.process(new ImageMsg("Bob", "https://example.com/photo.png", 256)));
    }

    @Test
    void processesAudioMessage() {
        String expected = """
                Notification: AUDIO
                From: Carol
                Duration: 42 seconds
                """;
        assertEquals(expected, processor.process(new AudioMsg("Carol", 42)));
    }

    @Test
    void keepsSpecialCharactersInText() {
        String expected = """
                Notification: TEXT
                From: Олена
                Message: Привіт! 100% готово: %s
                """;
        assertEquals(expected, processor.process(new TextMsg("Олена", "Привіт! 100% готово: %s")));
    }

    @Test
    void throwsOnNullWithExactMessage() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> processor.process(null));
        assertEquals("Notification cannot be null", ex.getMessage());
    }
}
