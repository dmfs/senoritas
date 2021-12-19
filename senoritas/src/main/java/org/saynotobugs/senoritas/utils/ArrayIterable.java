package org.saynotobugs.senoritas.utils;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An Iterable to iterate any array.
 */
public final class ArrayIterable implements Iterable<Object>
{
    private final Object mArray;


    public ArrayIterable(Object array)
    {
        if (!array.getClass().isArray())
        {
            throw new RuntimeException(array + " is not an array");
        }
        mArray = array;
    }


    @Override
    public Iterator<Object> iterator()
    {
        return new Iterator<Object>()
        {
            private int mPos;


            @Override
            public boolean hasNext()
            {
                return mPos < Array.getLength(mArray);
            }


            @Override
            public Object next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                }
                return Array.get(mArray, mPos++);
            }
        };
    }
}
