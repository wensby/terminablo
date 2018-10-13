package com.wensby.terminablo.world;

import java.math.BigDecimal;

public class AgentStats {

  private BigDecimal life = new BigDecimal(5000);
  private BigDecimal maxLife = new BigDecimal(50000);

  public BigDecimal getMaxLife() {
    return maxLife;
  }

  public BigDecimal getLife() {
    return life;
  }

  public void setLife(BigDecimal bigDecimal) {
    life = bigDecimal;
  }
}
