package com.comp2042.event;

public final class MoveEvent {
    private final EventSource eventSource;

    public MoveEvent(EventType eventType, EventSource eventSource) {
        this.eventSource = eventSource;
    }

    public EventSource getEventSource() {
        return eventSource;
    }
}
