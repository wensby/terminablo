package com.wensby.application;

import java.time.Duration;

public interface Benchmark {

  Duration getLastUpdateTime();

  Duration getLastRenderTime();

  Duration getLastTickTime();
}
