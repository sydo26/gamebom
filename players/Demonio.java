package players;

import java.util.Map;
import java.util.TreeMap;

import skills.Passive;
import skills.SkillRunnable;

public class Demonio extends BasePlayer {

  protected Passive eatSouls;

  public Demonio(String name) {
    super("👹", name, null, null);
    initSkills();
  }

  protected void initSkills() {
    this.eatSouls = new Passive("Devorador de almas",
        "fez com que o jogador {player} roubasse {percent}% de vida em {target}.", this, new SkillRunnable() {

          @Override
          public Map<String, Object> abilityTrigger(Player player, Player target) {
            Map<String, Object> map = new TreeMap<>();
            double percent = 0.5 * target.getLife();
            map.put("percent", percent);
            map.put("player", player.getName());
            map.put("target", target.getName());

            player.addLife(percent);
            target.receiveDamage(player, player.getDamage() + percent);
            return map;
          }

          @Override
          public boolean existCondition(Player player, Player target) {
            return target.getLife() <= 0.3 * target.getInitLife();
          }

        });
  }

  @Override
  public void attackTo(Player player) {
    if (this.eatSouls.existCondition(player)) {
      this.eatSouls.abilityTrigger(player);
      this.addLog(this.eatSouls.toString());
    } else
      super.attackTo(player);

  }

}
