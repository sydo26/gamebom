
import java.util.List;
import java.util.Random;

import players.Player;

public class Round {
  public Round(List<Player> players) {
    Random random = new Random();
    for (Player player : players) {
      if (player.isAlive()) {
        Player target;
        while (true) {
          target = players.get(random.nextInt(players.size()));
          if (!target.getName().equalsIgnoreCase(player.getName()))
            break;
        }
        System.out.println("[ " + target.getName() + " ⚔ " + player.getName() + " ]");
        double oldLifeTarget = target.getLife();
        double oldLifePlayer = player.getLife();
        player.attackTo(target);
        player.print(true, target, oldLifeTarget - target.getLife(), oldLifePlayer - player.getLife());
      }
    }
  }
}
