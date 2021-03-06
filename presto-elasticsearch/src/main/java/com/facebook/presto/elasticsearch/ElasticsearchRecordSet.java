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

import com.facebook.presto.spi.RecordCursor;
import com.facebook.presto.spi.RecordSet;
import com.facebook.presto.spi.type.Type;
import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ElasticsearchRecordSet
        implements RecordSet
{
    private final List<ElasticsearchColumnHandle> columnHandles;
    private final List<Type> columnTypes;
    //private final ByteSource byteSource;
    private final ElasticsearchTableSource tableSource;

    public ElasticsearchRecordSet(ElasticsearchSplit split, List<ElasticsearchColumnHandle> columnHandles)
    {
        checkNotNull(split, "split is null");

        this.columnHandles = checkNotNull(columnHandles, "column handles is null");
        ImmutableList.Builder<Type> types = ImmutableList.builder();
        for (ElasticsearchColumnHandle column : columnHandles) {
            types.add(column.getColumnType());
        }
        this.columnTypes = types.build();

        /*
        try {
            byteSource = Resources.asByteSource(split.getUri().toURL());
        }
        catch (MalformedURLException e) {
            throw Throwables.propagate(e);
        }
        */
        tableSource = split.getUri();
    }

    @Override
    public List<Type> getColumnTypes()
    {
        return columnTypes;
    }

    @Override
    public RecordCursor cursor()
    {
        return new ElasticsearchRecordCursor(columnHandles, tableSource);
    }
}
