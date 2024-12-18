package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.ref.Cleaner;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        //TODO invoke r.toString via reflection
        Method invokeMethod = Class.forName("ru.javawebinar.basejava.model.Resume").getMethod("toString");
        Object result = invokeMethod.invoke(r);
        System.out.println(result);
    }
}
