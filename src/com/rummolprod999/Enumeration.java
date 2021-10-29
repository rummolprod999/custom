package com.rummolprod999;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Enumeration {
    private static final List<Enumeration> items = new ArrayList<>();

    public static final Enumeration RUB = new Enumeration();
    public static final Enumeration EURO = new Enumeration();
    public static final Enumeration USD = new Enumeration();

    private final int ordinal;

    private Enumeration() {
        ordinal = items.size();
        items.add(this);
    }

    public static List<Enumeration> getItems() {
        return items;
    }

    public String getName() {
        return this.getClass().getDeclaredFields()[ordinal + 1].getName();
    }

    public int getOrdinal() {
        return ordinal;
    }

    public Enumeration getByNum(int num) {
        if (num < 0 || num > items.size() - 1) {
            throw new IllegalArgumentException("num is out of range");
        }
        return items.get(num);
    }

    public static Enumeration getByName(String name) throws IllegalAccessException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is null or empty");
        }
        for (Field declaredField : Enumeration.class.getDeclaredFields()) {
            if (declaredField.getName().equals(name)) {
                return (Enumeration) declaredField.get(null);
            }
        }
        throw new IllegalAccessException("Cannot find enum by number");
    }
}
