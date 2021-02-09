package skills;

import players.Player;

/**
 * A skill passiva acontece em todo round quando as condições específicas
 * acontecem.
 */

public class Passive extends Skill {

  public Passive(String name, String description, Player player, SkillRunnable skill) {
    super(name, description, player, skill);
  }

  @Override
  public String toString() {
    return String.format("[PASSIVA] A habilidade passiva [%s] %s", this.getName(),
        this.replaceAllValues(this.getDescription()));
  }
}
