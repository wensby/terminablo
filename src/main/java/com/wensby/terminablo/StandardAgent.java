package com.wensby.terminablo;

import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.AgentStats;
import com.wensby.terminablo.world.MapEntity;

public class StandardAgent implements Agent {

    private AgentStats stats = new AgentStats();

    @Override
    public AgentStats getStats() {
        return stats;
    }

    @Override
    public MapEntity getMapEntity() {
        return null;
    }
}
