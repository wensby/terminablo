package com.wensby.terminablo;

import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.AgentStats;

public class StandardAgent implements Agent {

    private AgentStats stats = new AgentStats();

    @Override
    public AgentStats getStats() {
        return stats;
    }
}
