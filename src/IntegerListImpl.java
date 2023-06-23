import exceptions.ElementNotFoundException;
import exceptions.InvalidIndexException;
import exceptions.NullItemException;
import exceptions.StorageIsFullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] storage;
    private int size;

    public IntegerListImpl() {
        storage = new Integer[10];
    }

    public IntegerListImpl(int size) {
        storage = new Integer[size];
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


        if(index == size){
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

        if(index == -1){
            throw new ElementNotFoundException();
        }

        if(index != size){
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = storage[index];

        if(index != size){
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        for (Integer el: storage) {
            if(item.equals(el)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if(item.equals(storage[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if(item.equals(storage[i])){
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
        for (int i = 0; i < storage.length; i ++) {
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

    private void validateItem (Integer item){
        if(item == null){
            throw new NullItemException();
        }
    }
    /**проверяем длину массива и если массив заполнен,
     * то увеличиваем его размер на заданную величину*/
    private void validateSize(){
        if(size == storage.length){
//            throw new StorageIsFullException();
            int newSize = storage.length + 5;
            Integer[] newStorage = new Integer[newSize];
            System.arraycopy(storage, 0, newStorage, 0, storage.length);
            storage = newStorage;
        }
    }

    private void validateIndex(int index){
        if(index < 0 || index > storage.length){
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

    public Integer[] getArray() {
        return storage;
    }
}
