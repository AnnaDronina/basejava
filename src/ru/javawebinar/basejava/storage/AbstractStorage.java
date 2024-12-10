package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {

    }

    public void save(Resume r) {

    }

    public Resume get(String uuid) {
        return new Resume();
    }

    public void delete(String uuid) {

    }
}
