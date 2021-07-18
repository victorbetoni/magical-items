package net.threader.magicalitems.reflection;

import net.threader.magicalitems.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ObjectRetrievePath<T> {
    private Object initialInstance;
    private Queue<Pair<Class<?>, String>> methodCall = new LinkedBlockingQueue<>();

    public ObjectRetrievePath(Object initialInstance) {
        this.initialInstance = initialInstance;
    }

    public ObjectRetrievePath<T> thenCall(Class<?> clazz, String method) {
        methodCall.add(new Pair<>(clazz, method));
        return this;
    }

    @SuppressWarnings("unchecked")
    public T apply() {
        Object currentInstance = initialInstance;
        while(!methodCall.isEmpty()) {
            Pair<Class<?>, String> pair = methodCall.poll();
            try {
                Method method = pair.getFirst().getDeclaredMethod(pair.getSecond());
                currentInstance = method.invoke(currentInstance);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return (T) currentInstance;
    }

}
