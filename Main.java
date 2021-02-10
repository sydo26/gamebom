import players.Kirito;
import players.Mercenary;

public class Main {
  public static void main(String[] args) {
    Game game = new Game();

    game.addPlayer(new Kirito("Kirito"));
    game.addPlayer(new Mercenary("João"));

    game.start();
  }
}