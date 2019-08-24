package com.wensby.terminalapp;

import java.time.Duration;

public interface Benchmark {

  Duration getLastUpdateTime();

  Duration getLastRenderTime();

  Duration getLastTickTime();
}
