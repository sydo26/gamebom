package players;

import java.util.Map;
import java.util.TreeMap;

import skills.Passive;
import skills.SkillRunnable;

/**
 * Isso é um exemplo de jogador. O mercenário da 50% menos dano no seu ataque
 * Porém, quando o mercenário é atacado, o dano do mercenário muda para o
 * próprio dano + o dano do atacante. Toda vez que ele ataca, se o jogador não
 * morrer ele ganha +1 de vida Se o dano do mercenário por acaso chegar a 0, ele
 * morre instanteneamente.
 */

public class Mercenary extends BasePlayer {

  protected Passive recoveryLife;
  protected Passive incrementDamage;

  public Mercenary(String name) {
    // name[String], initDamage[Double], initLife[Double]
    super("🧑", name, null, null);
    this.recoveryLife = new Passive("recuperar vida",
        "recuperou + {liferecovery} de vida após lutar e ambos sairem vivos.", this, new SkillRunnable() {
          @Override
          public Map<String, Object> abilityTrigger(Player player, Player target) {
            Map<String, Object> map = new TreeMap<>();
            map.put("liferecovery", 1.0); // crio isso apra poder passar para a descrição
            player.addLife(1.0);
            return map;
          }

          @Override
          public boolean existCondition(Player player, Player target) {
            return player.isAlive() && target.isAlive();
          }

        });
    this.incrementDamage = new Passive("aumenta dano", "aumentou o dano do jogador para {newdamage}.", this,
        new SkillRunnable() {
          @Override
          public Map<String, Object> abilityTrigger(Player player, Player target) {
            Map<String, Object> map = new TreeMap<>();
            double damage = player.getDamage() + target.getDamage();
            map.put("newdamage", damage); // crio isso apra poder passar para a descrição
            player.addDamage(target.getDamage());
            return map;
          }

          @Override
          public boolean existCondition(Player player, Player target) {
            return true;
          }

        });
  }

  @Override
  public void attackTo(Player player) {
    this.setDamage(this.getDamage() * 0.50);
    if (this.getDamage() <= 0) {
      this.setLife(0);
    }

    super.attackTo(player);
    if (this.recoveryLife.existCondition(player)) {
      this.recoveryLife.abilityTrigger(player);
      this.addLog(this.recoveryLife.toString());
    }

  }

  @Override
  public void receiveDamage(Player attacker, double damage) {
    if (this.incrementDamage.existCondition(attacker)) {
      this.incrementDamage.abilityTrigger(attacker);
      this.addLog(this.incrementDamage.toString());
    }
    super.receiveDamage(attacker, damage);
  }

}