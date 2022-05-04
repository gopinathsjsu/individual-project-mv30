package com.cmpe_202.mv30.interfaces;

public abstract class FileIterator<E> {

    public abstract Boolean hasNext();

    public abstract E next() throws Exception;
}
