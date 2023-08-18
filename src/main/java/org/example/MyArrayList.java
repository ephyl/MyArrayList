package org.example;

import java.util.Comparator;

/**
 * Реализация структуры данных MyArrayList.
 * Примечание:
 *      является учебным проектом.  Рекомендую использовать  java.util.ArrayList
 * @author Egor Filippov
 * @version 0.1
 */

public class MyArrayList<E> {
    /** Размерность массива по умолчанию */
    private static final int DEFAULT_CAPACITY = 10;
    /** Коэфициент расширения массива */
    private static final double ENLARGEMENT_RATE = 1.5;
    /** Коэфициент заполнения массива */
    private static final double CAPACITY_LIMIT = 0.9;
    /** Текущая размерность массива */
    private int capacity;
    /** Массив элементов */
    private Object[] elementData = new Object[DEFAULT_CAPACITY];

    /** Количество элементов в коллекции */
    private int size;
    /** Конструктор - создание нового объекта с определенными значениями */
    public MyArrayList() {
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }
    /** Конструктор - создание нового объекта с заданной в параметре размерностью внутреннего массива */
    public MyArrayList(int capacity) {
        if (capacity <=0){
            throw new IllegalArgumentException ("Capacity should be greater then 0");
        }
        this.capacity = capacity;
        elementData = new Object[capacity];
        size = 0;
    }
    /**
     *   @return  Возвращающаяет символьную строку описывающую объект
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('{');
        for (int i = 0; i < size; i++) {
            result.append(elementData[i]);
            if (i != size - 1) {
                result.append(", ");
            }
        }
        result.append('}');

        return result.toString();
    }
    /**
     *   Расширяет размер внутреннего массива, если текущее состояние превышает норматив.
     */
    private void increaseCapacity() {
        capacity = (int) (capacity * ENLARGEMENT_RATE+1);
        Object[] newArray = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elementData[i];
            elementData[i] = null;
        }
        elementData = newArray;
    }


    /**
     * @return Возвращает текущее количество эллементов в коллекции
     */
    public int size() {
        return size;
    }

    /**
     *  Проверяет существуют ли элементы с требуемым индексом, выбрасывает  исключение IndexOutOfBoundsException в противном случае
     */
    private void isNotInBounds(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    /**
     *  Добавляет элемент в конец списка.
     *  @return Возвращает true, в случае успешного добавления.
     */
    public boolean add(E e) {
        if (size >= capacity * CAPACITY_LIMIT) {
            increaseCapacity();
        }
        elementData[size++] = e;
        return true;
    }
    /**
     *  Добавляет элемент по указанному индексу. Расширяет ёмкость внутреннего массива, если текущее состояние превышает норматив.
     */
    public void add(int index, E e) {

        isNotInBounds(index);

        if (size >= capacity * CAPACITY_LIMIT) {
            increaseCapacity();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        size++;
    }

    /**
     *  @return Возвращает элемент по индексу.
     */
    public E get(int index) {
        isNotInBounds(index);
        return (E) elementData[index];
    }

    private void cutTail() {
        capacity = (int) (capacity / ENLARGEMENT_RATE);
        Object[] newArray = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elementData[i];
            elementData[i] = null;
        }
        elementData = newArray;
    }

    /**
     *  Удаляет элемент по индексу.
     *  @return Возвращает удаленный элемент
     */
    public E remove(int index) {
        isNotInBounds(index);

        E result = (E) elementData[index];

        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
            elementData[size - 1] = null;
            size--;
        }

        if ((size > DEFAULT_CAPACITY) && (capacity / size >= 3)) {
            cutTail();
        }

        return result;
    }

    /**
     *  Очищает список,  удаляет все значения.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }
    /**
     *  Сортирует список с помощью алгоритма быстрой сортировки на основании принимаемого экземпляра класса, реализующего интерфейс Comparator.
     */
    public void sort(Comparator<E> comparator) {
        Object [] newData = new Object[size];
        System.arraycopy(elementData, 0, newData, 0, size);
        qSort((E[]) newData, comparator);
        System.arraycopy(newData, 0, elementData, 0, size);

    }

    private E[] qSort(E[] arr, Comparator<E> comp) {

        if (arr.length < 2)
            return arr;
        int less = 0;
        int more = 0;
        E pivot = arr[arr.length / 2 - 1];
        int pivotCount = 0;
        for (E k : arr) {
            if (comp.compare(k, pivot) > 0) {
                more++;
            } else if (comp.compare(k, pivot) < 0) {
                less++;
            } else {
                pivotCount++;
            }
        }
        E[] resultLess = (E[]) new Object[less];
        E[] resultMore = (E[]) new Object[more];

        int countLess = 0, countMore = 0;

        for (E j : arr) {
            if (comp.compare(j, pivot) < 0) {
                resultLess[countLess++] = j;
            } else if (comp.compare(j, pivot) > 0) {
                resultMore[countMore++] = j;
            }
        }

        resultLess = qSort(resultLess, comp);
        resultMore = qSort(resultMore, comp);

        int y = 0;
        while (y < less) {
            arr[y] = resultLess[y];
            y++;
        }
        while (pivotCount > 0) {
            arr[y] = pivot;
            pivotCount--;
            y++;
        }
        int x = 0;
        while (x < more) {
            arr[x + y] = resultMore[x];
            x++;
        }
        return arr;
    }


}
