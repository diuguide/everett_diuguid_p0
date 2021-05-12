package com.revature.project_0.util;

/**
 * List interface.  Defines required methods and fields for all classes implementing the List interface
 *
 * @author Everett Diuguid
 * @author Wezley Singleton
 *
 * @param <T>
 */
public interface List<T> {

    void add(T data);
    T get(int index);
    boolean contains(T data);
    int size();
    int removeByIndex(int index);

}
