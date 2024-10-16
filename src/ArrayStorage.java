/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            try {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            } catch (NullPointerException e) {
                return null;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                break;
            }
        }
        int j = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (i != j) {
                    Resume temp = storage[j];
                    storage[j] = storage[i];
                    storage[i] = temp;
                }
                j++;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int length = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                length++;
            } else {
                break;
            }
        }
        Resume[] resumes = new Resume[length];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (storage[i] != null) {
                resumes[index] = storage[i];
                index++;
            } else {
                break;
            }
        }
        return resumes;
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                size++;
            }
        }
        return size;
    }
}
