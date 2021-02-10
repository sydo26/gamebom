package players;

import java.util.List;

public interface Player {

  public String getName();

  public double getLife();

  public double getDamage();

  public List<String> getLogs();

  public double getInitLife();

  public double getInitDamage();

  public String getUnicode();

  public boolean isAlive();

  public void attackTo(Player player);

  public void receiveDamage(Player attacker, double damage);

  public void print(Boolean attacking, Player other, Double causedDamageInTarget, Double causedDamageInPlayer);

  public void addLife(double value);

  public void addDamage(double value);

  public void reduceDamage(double value);

  public void reduceLife(double value);

  public void resetLogs();

}
