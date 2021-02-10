package players;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import skills.Passive;
import skills.SkillRunnable;

public class Kirito extends BasePlayer {

  public Kirito(String name) {
    super("👾", name, null, null);
    initSkills();
  }

  protected Passive criticalHit;

  protected void initSkills() {
    this.criticalHit = new Passive("ataque crítico",
        "fez com que o jogador {player} desse um crítico de {percent}% em {target}.", this, new SkillRunnable() {

          @Override
          public Map<String, Object> abilityTrigger(Player player, Player target) {
            Map<String, Object> map = new TreeMap<>();
            double percent = (new Random()).nextDouble() * 50 + 50;
            map.put("percent", percent + 100);
            map.put("player", player.getName());
            map.put("target", target.getName());

            target.receiveDamage(player, player.getDamage() + (player.getDamage() * percent));
            return map;
          }

          @Override
          public boolean existCondition(Player player, Player target) {
            return (new Random()).nextInt(101) < 50;
          }

        });
  }

  @Override
  public void attackTo(Player player) {
    if (this.criticalHit.existCondition(player)) {
      this.criticalHit.abilityTrigger(player);
      this.addLog(this.criticalHit.toString());
    } else
      super.attackTo(player);
  }

  @Override
  public void receiveDamage(Player attacker, double damage) {
    // TODO Auto-generated method stub
    super.receiveDamage(attacker, damage);
  }

}
