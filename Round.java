
import java.util.List;

import players.Player;

public class Round {
  public Round(List<Player> players) {
    for (int p = 0; p < players.size(); p++) {
      for (int t = 0; t < players.size(); t++) {
        if (players.get(p).isAlive() && t != p) {
          System.out.println("[ " + players.get(p).getName() + " ⚔ " + players.get(t).getName() + " ]");
          double oldLifeTarget = players.get(t).getLife();
          double oldLifePlayer = players.get(p).getLife();
          players.get(p).attackTo(players.get(t));
          players.get(p).print(true, players.get(t), oldLifeTarget - players.get(t).getLife(),
              oldLifePlayer - players.get(p).getLife());
        }
      }
    }
  }
}
