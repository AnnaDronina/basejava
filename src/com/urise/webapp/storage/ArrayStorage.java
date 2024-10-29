package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    private boolean isResumeExist(String uuid) {
        boolean isExist = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                isExist = true;
            }
        }
        return isExist;
    }

    public void update(Resume resume) {
        //TODO check if resume present
        if (isResumeExist(resume.toString())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].equals(resume)) {
                    storage[i] = resume;
                }
            }
        } else {
            System.out.println("Резюме " + resume.toString() + " нельзя обновить, т.к. не его не существует");
        }
    }

    public void save(Resume r) {
        //TODO check if resume  not present
        if (!isResumeExist(r.toString()) && size != storage.length) {
            storage[size] = r;
            size += 1;
        } else if (isResumeExist(r.toString())) {
            System.out.println("Резюме " + r.toString() + " уже было сохранено");
        } else if (size == storage.length) {
            System.out.println("Ошибка переполнения массива");
        }
    }

    public Resume get(String uuid) {
        if (isResumeExist(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
        return null;
    }

    public void delete(String uuid) {
        //TODO check if resume present
        if (isResumeExist(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size -= 1;
                    break;
                }
            }
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = Arrays.copyOfRange(storage, 0, size);
        return resumes;
    }

    public int size() {
        return size;
    }
}