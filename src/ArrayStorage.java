/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        size = size + 1;
        for (int i = 0; i < size(); i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[size() - 1];
                storage[size() - 1] = null;
                break;
            }
        }
        size = size - 1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        int index = 0;
        for (int i = 0; i < size(); i++) {
            resumes[index] = storage[i];
            index++;
        }
        return resumes;
    }

    int size() {
        return size;
    }
}