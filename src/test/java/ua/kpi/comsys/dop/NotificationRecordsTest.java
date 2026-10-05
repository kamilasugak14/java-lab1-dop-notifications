package ua.kpi.comsys.dop;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NotificationRecordsTest {

    @Nested
    class TextMsgTests {

        @Test
        void createsValidMessage() {
            TextMsg msg = new TextMsg("Alice", "Hello");
            assertEquals("Alice", msg.sender());
            assertEquals("Hello", msg.text());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "   ", "\t", "\n"})
        void rejectsInvalidSender(String sender) {
            assertThrows(IllegalArgumentException.class, () -> new TextMsg(sender, "Hello"));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "   ", "\t", "\n"})
        void rejectsInvalidText(String text) {
            assertThrows(IllegalArgumentException.class, () -> new TextMsg("Alice", text));
        }
    }

    @Nested
    class ImageMsgTests {

        @Test
        void createsValidMessage() {
            ImageMsg msg = new ImageMsg("Bob", "https://example.com/a.png", 256);
            assertEquals("Bob", msg.sender());
            assertEquals("https://example.com/a.png", msg.url());
            assertEquals(256, msg.sizeKb());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t"})
        void rejectsInvalidSender(String sender) {
            assertThrows(IllegalArgumentException.class,
                    () -> new ImageMsg(sender, "https://example.com/a.png", 10));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t"})
        void rejectsInvalidUrl(String url) {
            assertThrows(IllegalArgumentException.class, () -> new ImageMsg("Bob", url, 10));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100, Integer.MIN_VALUE})
        void rejectsNonPositiveSize(int sizeKb) {
            assertThrows(IllegalArgumentException.class,
                    () -> new ImageMsg("Bob", "https://example.com/a.png", sizeKb));
        }

        @Test
        void acceptsMinimalPositiveSize() {
            assertDoesNotThrow(() -> new ImageMsg("Bob", "https://example.com/a.png", 1));
        }
    }

    @Nested
    class AudioMsgTests {

        @Test
        void createsValidMessage() {
            AudioMsg msg = new AudioMsg("Carol", 42);
            assertEquals("Carol", msg.sender());
            assertEquals(42, msg.durationSeconds());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t"})
        void rejectsInvalidSender(String sender) {
            assertThrows(IllegalArgumentException.class, () -> new AudioMsg(sender, 10));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100, Integer.MIN_VALUE})
        void rejectsNonPositiveDuration(int duration) {
            assertThrows(IllegalArgumentException.class, () -> new AudioMsg("Carol", duration));
        }

        @Test
        void acceptsMinimalPositiveDuration() {
            assertDoesNotThrow(() -> new AudioMsg("Carol", 1));
        }
    }

    @Test
    void recordsAreValueBased() {
        assertEquals(new TextMsg("A", "B"), new TextMsg("A", "B"));
        assertEquals(new ImageMsg("A", "u", 1), new ImageMsg("A", "u", 1));
        assertEquals(new AudioMsg("A", 1), new AudioMsg("A", 1));
    }
}
