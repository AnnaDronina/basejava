package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    public void clear() {
        list.clear();
    }

    protected Integer getKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    private boolean isExisting(Integer key) {
        return null != key;
    }

    @Override
    public void update(Resume r) {
        if (isExisting(getKey(r.getUuid()))) {
            list.set(getKey(r.getUuid()), r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        if (!isExisting(getKey(r.getUuid()))) {
            list.add(r);
        } else {
            throw new ExistStorageException(r.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        if (!isExisting(getKey(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        return list.get(getKey(uuid));
    }

    @Override
    public void delete(String uuid) {
        if (isExisting(getKey(uuid))) {
            list.remove(new Resume(uuid));
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    public int size() {
        return list.size();
    }
}
