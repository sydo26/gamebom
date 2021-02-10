package skills;

import java.util.Map;
import java.util.TreeMap;

import players.Player;

public class Skill {

  private String name;
  private String description;
  private Player player;
  private SkillRunnable skillRunnable;
  private Map<String, Object> values;

  public Skill(String name, String description, Player player, SkillRunnable skillRunnable) {
    this.skillRunnable = skillRunnable;
    this.name = name;
    this.player = player;
    this.description = description;
    this.values = new TreeMap<>();
  }

  public Map<String, Object> getValues() {
    return values;
  }

  public void addValue(String key, Object value) {
    this.values.put(key, value);
  }

  public String getName() {
    return name.toUpperCase();
  }

  public String getDescription() {
    return description;
  }

  public Player getPlayer() {
    return this.player;
  }

  public boolean existCondition(Player target) {
    return skillRunnable.existCondition(this.player, target);
  }

  public String replaceAllValues(String string) {
    String randomHash = "125912809581298491289581";
    string = string.replaceAll("%", randomHash);
    for (Map.Entry<String, Object> value : this.getValues().entrySet()) {
      if (value.getValue() instanceof Double) {
        while (string.contains("{" + value.getKey() + "}")) {
          string = string.replace("{" + value.getKey() + "}", "%.2f");
          string = String.format(string, (double) value.getValue());
        }
      } else {
        while (string.contains("{" + value.getKey() + "}")) {
          string = string.replace("{" + value.getKey() + "}", "%s");
          string = String.format(string, String.valueOf(value.getValue()));
        }
      }
    }

    string = string.replaceAll(randomHash, "%");

    return string;
  }

  public void abilityTrigger(Player target) {
    try {
      for (Map.Entry<String, Object> obj : skillRunnable.abilityTrigger(this.player, target).entrySet()) {
        if (obj.getValue() instanceof String || obj.getValue() instanceof Integer || obj.getValue() instanceof Double
            || obj.getValue() instanceof Boolean) {
          this.addValue(obj.getKey(), obj.getValue());
        } else {
          throw new Exception(
              "Você deve passar para os parâmetros do addValue apenas um desses tipos de valores: Double, Integer, Boolean ou String");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
