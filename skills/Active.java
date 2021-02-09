package skills;

import players.Player;

/**
 * A skill passiva acontece em todo round quando as condições específicas
 * acontecem.
 */

public class Active extends Skill {

  protected int timeDefaultFight;
  private int everyFight;

  public Active(String passiveName, String description, Player player, int everyFight, SkillRunnable skill) {
    super(passiveName, description, player, skill);
    this.timeDefaultFight = everyFight;
    this.everyFight = everyFight;
  }

  public int getEveryFight() {
    return everyFight;
  }

  @Override
  public boolean existCondition(Player target) {
    if (this.everyFight-- > 0) {
      return false;
    }
    return super.existCondition(target);
  }

  @Override
  public String toString() {
    return String.format("[ATIVA] A habilidade ativa [%s] %s", this.getName(),
        this.replaceAllValues(this.getDescription()));
  }

}
