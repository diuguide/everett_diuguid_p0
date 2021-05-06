package com.revature.project_0.util;

public interface List<T> {

    void add(T data);
    T get(int index);
    boolean contains(T data);
    int size();

}
