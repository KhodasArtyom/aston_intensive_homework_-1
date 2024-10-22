package com.artemhodas.my_ArrayList_Realisation;

import java.util.Comparator;

public interface MyList<T>{

    public void addElement(T elem);

    public int showSize();

    public void addElementByIndex(int index,T elem);

    public T getElement(int index);

    public T deleteElementByIndex(int index);

    public void clearMyList();

    public void quickSort(Comparator<T> comparator);


}
