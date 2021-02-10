package players;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import skills.Passive;
import skills.SkillRunnable;

public class Kirito extends BasePlayer {

  protected Passive critical_hit;

  public Kirito(String name) {
    super("🧔", name, null, null);
    initSkills();
  }

  protected void initSkills() {
    this.critical_hit = new Passive("ataque crítico",
        "fez com que o jogador {player} desse um crítico de {percent}% em {target}.", this, new SkillRunnable() {

          @Override
          public Map<String, Object> abilityTrigger(Player player, Player target) {
            Map<String, Object> map = new TreeMap<>();
            double percent = (new Random()).nextDouble() * 100;
            map.put("percent", percent);
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
    if (this.critical_hit.existCondition(player)) {
      this.critical_hit.abilityTrigger(player);
      this.addLog(this.critical_hit.toString());
    } else
      super.attackTo(player);

  }

}
