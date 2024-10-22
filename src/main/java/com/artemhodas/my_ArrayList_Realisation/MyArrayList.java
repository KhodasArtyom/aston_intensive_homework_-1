package com.artemhodas.my_ArrayList_Realisation;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Этот класс описывает свою реализацю класса ArrayList<T> наподобие {@Link java.util.ArrayList}.
 * Данный класс поддерживает на данный момент определенное количество функций,
 * которые позволяют взаимодействовать с данной структурой данных.
 * <p> Этот класс реализует Interface MyList,содержащий абстрактные методы.
 *
 * @author [Артём Ходас]
 * @version 1.0
 * @see java.util.ArrayList
 */

public class MyArrayList<T> implements MyList<T> {
    /**
     * Содержт переменную типа final, определяющую стандартный размер моего массива
     */
    private final int ORIGINAL_SIZE = 10;
    /**
     * Определяет непроиницализированный массив моего списка
     */
    private Object[] array;
    /**
     * Определяет размер счетчик моего массива пр добавлении элементов в список
     */
    int size;

    /**
     * Создаёт конструктор для того,чтобы инициализировать поля класса
     */
    public MyArrayList() {
        array = new Object[ORIGINAL_SIZE];
        this.size = 0;
    }

    /**
     * Переопределение метода toString() класса Object для отображения самого списка в консоли.
     *
     * @return String
     */
    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    /**
     * Метод по добавленю элемента в массив
     *
     * @param elem На начальном этапе проверяет кол-во элементов в массиве, если =длине массива,
     *             копирует этот массив в новый расширенный массив
     *             Записывает элемент в массив и увеличвает счетчик
     */
    @Override
    public void addElement(T elem) {
        if (size == array.length) {
            expendArray();
        }
        array[size] = elem;
        size++;
    }

    /**
     * Возвращает Колчество элементов в массиве
     *
     * @return int size
     */

    @Override
    public int showSize() {
        return size;
    }

    /**
     * Добавляет элемент в конкретный индекс массива,задаваемый параметром index
     *
     * @param index
     * @param elem  Проверяет на корректность заадаваемого параметра index
     *              Проверяет заполнен ли массив
     *              В Цкле каждый элемент сдвигается на один шаг, чтобы освободить место для нового элемента
     */
    @Override
    public void addElementByIndex(int index, T elem) {
        if (index < 0 || index > array.length - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            expendArray();
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = elem;
        size++;

    }

    /**
     * Метод получает объект по индексу
     * Также проводиться проверка на корректность задаваемого параметра пользователем
     *
     * @param index
     * @return
     */

    @Override
    public T getElement(int index) {
        if (index < 0 || index > array.length - 1)
            throw new IndexOutOfBoundsException();
        return (T) array[index];
    }

    /**
     * Метод удаляет элемент по ндексу
     * Проводится проверка на корректность задаваемого параметра
     *
     * @param index индекс элемента, который нужно удалить
     * @return T возвращает удалённый объект
     * В цикле Каждый элемент сдвигается на один шаг влево, чтобы заполнить место,
     * которое занимал удаляемый элемент.
     * Очищает последний элемент
     * Уменьшает размер списка
     * Возвращает удаленный
     */

    @Override
    public T deleteElementByIndex(int index) {
        if (index < 0 || index > array.length - 1) {
            throw new IndexOutOfBoundsException();
        }
        T removedElement = (T) array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return removedElement;
    }

    /**
     * Метод очищает весь список
     */

    @Override
    public void clearMyList() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Метод создает нвоый массив,скопировав заданынй в параметрах  увелчвающийся в два раза
     */

    private void expendArray() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /**
     * Метод для быстрой сортировки с использованием Компоратора
     *
     * @param comparator
     */
    public void quickSort(Comparator<T> comparator) {
        quickSort(0, size - 1, comparator);


    }

    /**
     * @param low        первый элемент массива
     * @param high       последний элемент массива
     * @param comparator сравне ние объектов
     *
     *                   Задаётся базис для выхода из вызова рекурсии
     *                   Определяется индекс опорного элемента
     *                   Проиисходит сортировка подмассивов для определения порядка
     */

    private void quickSort(int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Данныйы метод позволяет  разделить массив на подмассивы, значально определв опорный элемент
     * и сдвигая элементы меньше опорного в левую сторону и наоборот.
     * @param low первый элемент
     * @param high последний элемент
     * @param comparator сравнение двух элементов
     * @return воз-ся индекс опорного элемента
     */

    private int partition(int low, int high, Comparator<T> comparator) {
        T pivot = (T) array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare((T) array[j], pivot) <= 0) {
                i++;
                swap(i, j);

            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     *  Метод позволяет поменять положение элементов в массиве друг с другом
     * @param i элемент
     * @param j второй элемент
     */
    private void swap(int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {

    }
}
