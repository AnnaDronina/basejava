package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME4 = new Resume(UUID_4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void SetUp() throws Exception {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME4);
        assertEquals(storage.get(UUID_4), RESUME4);
    }

    @Test(expected = StorageException.class)
    public void saveOwerflow() throws Exception {
        try {
            for (int i = 3; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(RESUME4);
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(RESUME4);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
    }

    @Test
    public void get() throws Exception {
        assertEquals(storage.get(UUID_1), RESUME1);
        assertEquals(storage.get(UUID_2), RESUME2);
        assertEquals(storage.get(UUID_3), RESUME3);
    }

    @Test
    public void update() throws Exception {
        storage.update(RESUME1);
        assertSame(storage.get(UUID_1), RESUME1);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumeArray = storage.getAll();
        assertSame(RESUME1, resumeArray[0]);
        assertSame(RESUME2, resumeArray[1]);
        assertSame(RESUME3, resumeArray[2]);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME4);
    }
}