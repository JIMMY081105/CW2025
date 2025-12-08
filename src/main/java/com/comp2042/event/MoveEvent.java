package com.comp2042.event;

/**
 * The {@code MoveEvent} class encapsulates a movement command and its origin, allowing controllers to distinguish
 * user input from automated ticks.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/event/MoveEvent.java">
 * MoveEvent.java</a>
 */
public final class MoveEvent {

    private final EventType eventType;
    private final EventSource eventSource;

    /**
     * Constructs a move event with the given type and source.
     *
     * @param eventType   movement command.
     * @param eventSource originator of the event.
     */
    public MoveEvent(EventType eventType, EventSource eventSource) {
        this.eventType = eventType;
        this.eventSource = eventSource;
    }

    /**
     * Returns the movement command type.
     *
     * @return event type.
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Returns the origin of the command.
     *
     * @return event source.
     */
    public EventSource getEventSource() {
        return eventSource;
    }
}
