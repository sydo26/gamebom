package skills;

import java.util.Map;

import players.Player;

public interface SkillRunnable {

  public boolean existCondition(Player player, Player target);

  public Map<String, Object> abilityTrigger(Player player, Player target);

}
