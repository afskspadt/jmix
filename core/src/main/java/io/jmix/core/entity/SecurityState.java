/*
 * Copyright 2019 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jmix.core.entity;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Stores information about:
 * <ul>
 *   <li>data that has been filtered by row level security;
 * </ul>
 */
public class SecurityState implements Serializable {

    private static final long serialVersionUID = 6613320540189701505L;

    protected transient Multimap<String, Object> erasedData;

    @Nullable
    public Multimap<String, Object> getErasedData() {
        return erasedData;
    }

    public Collection<String> getErasedAttributes() {
        return erasedData == null ? Collections.emptyList() : erasedData.keySet();
    }

    public Collection<Object> getErasedIds(String attrName) {
        return erasedData == null ? Collections.emptyList() : erasedData.get(attrName);
    }

    public void addErasedIds(String attrName, Collection<Object> erasedIds) {
        if (erasedData == null) {
            erasedData = HashMultimap.create();
        }
        erasedData.putAll(attrName, erasedIds);
    }

    public void addErasedId(String attrName, Object erasedId) {
        if (erasedData == null) {
            erasedData = HashMultimap.create();
        }
        erasedData.put(attrName, erasedId);
    }
}
