package com.booknet.system;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventCenter {
    static class Listeners extends CopyOnWriteArrayList<Consumer<Object>>{}

    static ConcurrentHashMap<String, Listeners> eventMap = new ConcurrentHashMap<>();

    public static void sub(String evId, Consumer<Object> consumer) {
        if (!eventMap.containsKey(evId)) eventMap.put(evId, new Listeners());
        Listeners listeners = eventMap.get(evId);
        if (!listeners.contains(consumer)) listeners.add(consumer);
    }

    public static void pub(String evId) {
        pub(evId, null);
    }

    public static void pub(String evId, Object eventData) {
        Listeners listeners = eventMap.get(evId);
        listeners.forEach(consumer -> {
            if (consumer != null) consumer.accept(eventData);
        });
    }

    public static void remove(String evId) {
        eventMap.remove(evId);
    }

    public static void remove(String evId, Consumer<Object> deleteConsumer) {
        Listeners listeners = eventMap.get(evId);
        listeners.removeIf(consumer -> consumer == deleteConsumer);
    }
}
