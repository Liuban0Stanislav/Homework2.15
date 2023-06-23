import exceptions.ElementNotFoundException;
import exceptions.InvalidIndexException;
import exceptions.NullItemException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] storage;
    private int size;

    public IntegerListImpl() {
        storage = new Integer[100_000];
    }

    public IntegerListImpl(int size) {
        storage = new Integer[size];
    }

    @Override
    public boolean contains(Integer item, String Inspection_Selection_Bubbles) {
        switch (Inspection_Selection_Bubbles) {
            case "Inspection":
                sortInsertion(storage);
                break;
            case "Selection":
                sortSelection(storage);
                break;
            case "Bubbles":
                sortBubbles(storage);
                break;
        }

        int min = 0;
        int max = storage.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == storage[mid]) {
                return true;
            }

            if (item < storage[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;

//        for (Integer el : storage) {
//            if (item.equals(el)) {
//                return true;
//            }
//        }
//        return false;
    }

    private static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private void sortBubbles(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);


        if (index == size) {
            storage[size++] = item;
            return item;
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;//не storage[size++] а index
        size++;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);

        if (index == -1) {
            throw new ElementNotFoundException();
        }

        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = storage[index];

        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(storage[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(storage[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        for (int i = 0; i < storage.length; i++) {
            validateItem(otherList.get(i));
        }
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    public void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    /**
     * проверяем длину массива и если массив заполнен,
     * то увеличиваем его размер на заданную величину
     */
    private void validateSize() {
        if (size == storage.length) {
//            throw new StorageIsFullException();
            int newSize = storage.length + 5;
            Integer[] newStorage = new Integer[newSize];
            System.arraycopy(storage, 0, newStorage, 0, storage.length);
            storage = newStorage;
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > storage.length) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (Integer el : storage) {
            if (el != null) {
                sb.append(el + " ");
            }
        }
        return sb.toString();
    }

    public IntegerList integerListCopy(IntegerList list) {
        IntegerList newIntegerList = new IntegerListImpl(100_001);
        System.arraycopy(this.getStorage(), 0, list.getStorage(), 0, this.getStorage().length);
        list = newIntegerList;
        return list;
    }

    public Integer[] getStorage() {
        return storage;
    }
}
