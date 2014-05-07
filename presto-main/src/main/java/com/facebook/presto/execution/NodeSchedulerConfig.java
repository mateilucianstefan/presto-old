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
package com.facebook.presto.execution;

import io.airlift.configuration.Config;

import javax.validation.constraints.Min;

public class NodeSchedulerConfig
{
    private int minCandidates = 10;
    private boolean locationAwareScheduling = true;

    @Min(1)
    public int getMinCandidates()
    {
        return minCandidates;
    }

    @Config("node-scheduler.min-candidates")
    public NodeSchedulerConfig setMinCandidates(int candidates)
    {
        this.minCandidates = candidates;
        return this;
    }

    public boolean isLocationAwareSchedulingEnabled()
    {
        return locationAwareScheduling;
    }

    @Config("node-scheduler.location-aware-scheduling-enabled")
    public NodeSchedulerConfig setLocationAwareSchedulingEnabled(boolean locationAwareScheduling)
    {
        this.locationAwareScheduling = locationAwareScheduling;
        return this;
    }
}