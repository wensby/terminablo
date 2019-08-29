package com.wensby.terminablo.world;

import java.math.BigDecimal;
import java.util.List;

public class AgentStats {

  private BigDecimal life = new BigDecimal(5000);
  private BigDecimal maxLife = new BigDecimal(50000);
  private BigDecimal mana = new BigDecimal(5000);
  private BigDecimal manaMax = new BigDecimal(50000);

  public BigDecimal getMaxLife() {
    return maxLife;
  }

  public BigDecimal getLife() {
    return life;
  }

  public void setLife(BigDecimal bigDecimal) {
    life = bigDecimal;
  }

  public BigDecimal getMana() {
    return mana;
  }

  public void setMana(BigDecimal bigDecimal) {
    mana = bigDecimal;
  }

  public BigDecimal getMaxMana() {
    return manaMax;
  }
}
