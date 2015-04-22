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

import com.thoughtworks.xstream.XStream;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper that specifies which types are basic immutable types. Types that are marked as immutable will be written
 * multiple times in the serialization stream without using references.
 *
 * @author Joe Walnes
 */
public class ImmutableTypesMapper extends MapperWrapper {

    private final Map<Class, XStream.ReferencePathRetentionPolicy> pathRetentionByType = new HashMap<>();

    public ImmutableTypesMapper(Mapper wrapped) {
        super(wrapped);
    }

    public void addImmutableType(Class type, XStream.ReferencePathRetentionPolicy retentionPolicy) {
        if(type == null || retentionPolicy == null) { throw new IllegalArgumentException(); }
        pathRetentionByType.put(type, retentionPolicy);
    }

    public XStream.ReferencePathRetentionPolicy getPathRetentionPolicy(Class type) {
        return pathRetentionByType.containsKey(type)
                ? pathRetentionByType.get(type)
                : super.getPathRetentionPolicy(type);
    }

}
