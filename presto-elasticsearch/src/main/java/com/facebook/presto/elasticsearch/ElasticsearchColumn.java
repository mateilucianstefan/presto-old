/*
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
package com.facebook.presto.elasticsearch;

import com.facebook.presto.spi.type.Type;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

public final class ElasticsearchColumn
{
    private final String name;
    private final Type type;
    private final String jsonPath;
    private final String jsonType;

    @JsonCreator
    public ElasticsearchColumn(
            @JsonProperty("name") String name,
            @JsonProperty("type") Type type,
            @JsonProperty("jsonPath") String jsonPath,
            @JsonProperty("jsonType") String jsonType)
    {
        checkArgument(!isNullOrEmpty(name), "name is null or is empty");
        this.name = name;
        this.type = checkNotNull(type, "type is null");
        this.jsonPath = checkNotNull(jsonPath, "jsonPath is null");
        this.jsonType = checkNotNull(jsonType, "jsonType is null");
    }

    @JsonProperty
    public String getName()
    {
        return name;
    }

    @JsonProperty
    public Type getType()
    {
        return type;
    }

    @JsonProperty
    public String getJsonPath()
    {
        return jsonPath;
    }

    @JsonProperty
    public String getJsonType()
    {
        return jsonType;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, type, jsonPath, jsonType);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ElasticsearchColumn other = (ElasticsearchColumn) obj;
        return Objects.equals(this.name, other.name) &&
                Objects.equals(this.type, other.type) &&
                Objects.equals(this.jsonPath, other.jsonPath) &&
                Objects.equals(this.jsonType, other.jsonType);
    }

    @Override
    public String toString()
    {
        return name + ":" + type + ":" + jsonPath + ":" + jsonType;
    }
}
