/*
 * Copyright (C) 2005 Joe Walnes.
 * Copyright (C) 2006, 2007, 2009 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 22. January 2005 by Joe Walnes
 */
package com.thoughtworks.xstream.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper that specifies which types are basic immutable types. Types that are marked as immutable will be written
 * multiple times in the serialization stream without using references.
 *
 * @author Joe Walnes
 */
public class ImmutableTypesMapper extends MapperWrapper {

    private static final int RETAIN_FOR_COMPATIBILITY = 1;
    private static final int RETAIN_NEVER = 2;

    private final Map<Class, Integer> pathRetentionByType = new HashMap<>();

    public ImmutableTypesMapper(Mapper wrapped) {
        super(wrapped);
    }

    public void addImmutableType(Class type, boolean retainPathsOnDeserialization) {
        if(type == null) { throw new IllegalArgumentException(); }
        pathRetentionByType.put(type, retainPathsOnDeserialization ? RETAIN_FOR_COMPATIBILITY : RETAIN_NEVER);
    }

    @Override
    public boolean isImmutableValueType(Class type) {
        return pathRetentionByType.containsKey(type)
                ? isImmutableType(type, RETAIN_NEVER) //use the most-strict test here, since the callers specify less strict ones if necessary
                : super.isImmutableValueType(type);
    }

    @Override
    public boolean isImmutableValueType(Class type, boolean includeBackwardsCompatibleTypes) {
        return pathRetentionByType.containsKey(type)
                ? isImmutableType(type, includeBackwardsCompatibleTypes ? RETAIN_FOR_COMPATIBILITY : RETAIN_NEVER)
                : super.isImmutableValueType(type, includeBackwardsCompatibleTypes);
    }

    private boolean isImmutableType(Class<?> type, int neededLevel) {
        int retentionLevel = pathRetentionByType.get(type);
        return retentionLevel >= neededLevel;
    }
}
