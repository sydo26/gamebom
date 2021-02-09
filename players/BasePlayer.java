package players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BasePlayer implements Player {
  private String name;
  private double life;
  private double damage;
  protected double initLife;
  protected double initDamage;
  private Random random;
  private String unicode;
  private List<String> logs;

  BasePlayer(String unicode, String name, Double initDamage, Double initLife) {
    this.unicode = unicode;
    this.random = new Random();
    this.name = name;
    this.initDamage = initDamage != null ? initDamage : (getRandom().nextDouble() * 10) + 10;
    this.initLife = initDamage != null ? initLife : (getRandom().nextDouble() * 50) + 50;
    this.life = this.initLife;
    this.damage = this.initDamage;
    this.logs = new ArrayList<>();
  }

  public List<String> getLogs() {
    return logs;
  }

  public void addLog(String log) {
    this.logs.add(log);
  }

  public void resetLogs() {
    this.logs = new ArrayList<>();
  }

  @Override
  public void addLife(double value) {
    this.life += value;
  }

  @Override
  public void addDamage(double value) {
    this.damage += value;
  }

  @Override
  public void reduceDamage(double value) {
    this.damage -= value;
  }

  @Override
  public void reduceLife(double value) {
    this.life -= value;
  }

  protected Random getRandom() {
    return random;
  }

  protected void setLife(double life) {
    this.life = life;
  }

  protected void setDamage(double damage) {
    this.damage = damage;
  }

  @Override
  public double getLife() {
    return this.life;
  }

  @Override
  public double getDamage() {
    return this.damage;
  }

  @Override
  public String getUnicode() {
    return this.unicode;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void attackTo(Player player) {
    if (this.isAlive()) {
      player.receiveDamage(this, this.getDamage());
    }
  }

  @Override
  public boolean isAlive() {
    return this.life > 0.0;
  }

  @Override
  public void receiveDamage(Player attacker, double damage) {
    this.setLife(this.getLife() - damage);
    if (!this.isAlive())
      this.setLife(0);
  }

  @Override
  public void print(Boolean attacking, Player other, Double causedDamageInTarget, Double causedDamageInPlayer) {
    if (attacking == null) {
      System.out.println(this.getUnicode() + " " + toString() + " " + (!isAlive() ? "💀" : ""));
      return;
    }

    System.out.println("=====================================================");
    if (attacking) {
      System.out.println("[LUTA] " + this.getUnicode() + "🤺 " + toString()
          + String.format(" (causou %.2f de dano)", causedDamageInTarget) + " AO ATACAR -> " + this.getUnicode() + "🔰 "
          + other.toString());
    } else {
      System.out.println("[LUTA] " + this.getUnicode() + "🤺 " + other.toString()
          + String.format(" (causou %.2f de dano)", causedDamageInTarget) + " AO ATACAR -> " + this.getUnicode() + "🔰 "
          + toString());
    }

    System.out.println("\n[ Logs de batalha ]:");
    for (String log : getLogs()) {
      System.out.println(this.getUnicode() + " " + this.getName() + " - " + log);
    }
    for (String log : other.getLogs()) {
      System.out.println(other.getUnicode() + " " + other.getName() + " - " + log);
    }
    System.out.println();

    this.resetLogs();
    other.resetLogs();
    System.out.println("=====================================================\n");
  }

  @Override
  public String toString() {
    return String.format("%s [L:%.2f  D:%.2f]", this.getName(), this.getLife(), this.getDamage());
  }

}
