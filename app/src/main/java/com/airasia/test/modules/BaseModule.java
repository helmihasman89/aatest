package com.airasia.test.modules;

public interface BaseModule<T> {

    /**
     * The unique id.
     *
     * @return The unique id.
     */
    String getId();

    /**
     * The hash of the module.
     *
     * @return The hash of the module.
     */
    String getHash();


    /**
     * Custom check will be implement in each module.
     *
     * @return true if the current module empty.
     */
    boolean isEmpty();


    /**
     * Sort by.
     *
     * @return sort type.
     */
    T getSortBy();
}