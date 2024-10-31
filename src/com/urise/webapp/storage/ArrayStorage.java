package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExisting(int index) {
        if (index >= 0 && storage[index] != null) {
            return true;
        }
        return false;
    }

    public void update(Resume resume) {
        //TODO check if resume present
        int index = getIndex(resume.toString());
        if (isExisting(index)) {
            storage[index] = resume;
        } else {
            System.out.println("Резюме " + resume.toString() + " нельзя обновить, т.к. не его не существует");
        }
    }

    public void save(Resume r) {
        //TODO check if resume  not present
        int index = getIndex(r.toString());
        if (!isExisting(index) && size != storage.length) {
            storage[size] = r;
            size += 1;
        } else if (isExisting(index)) {
            System.out.println("Резюме " + r.toString() + " уже было сохранено");
        } else if (size == storage.length) {
            System.out.println("Ошибка переполнения массива");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (isExisting(index)) {
            return storage[index];
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
        return null;
    }

    public void delete(String uuid) {
        //TODO check if resume present
        int index = getIndex(uuid);
        if (isExisting(index)) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size -= 1;
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}