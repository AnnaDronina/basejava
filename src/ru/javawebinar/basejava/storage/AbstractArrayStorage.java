package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    private boolean isExisting(int index) {
        return index >= 0 && storage[index] != null;
    }

    public void save(Resume r) {
        int index = getIndex(r.toString());
        if (!isExisting(index) && size != storage.length) {
            addResume(r, index);
            size += 1;
        } else if (isExisting(index)) {
            throw new ExistStorageException(r.getUuid());
        } else if (size == storage.length) {
            throw new StorageException("Ошибка переполнения массива", r.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExisting(index)) {
            deleteResume(index);
            storage[size - 1] = null;
            size -= 1;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (!isExisting(index)) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }


    public void update(Resume resume) {
        int index = getIndex(resume.toString());
        if (isExisting(index)) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(Resume r, int index);

    protected abstract void deleteResume(int index);
}
