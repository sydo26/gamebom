import players.Mercenary;

public class Main {
  public static void main(String[] args) {
    Game game = new Game();

    game.addPlayer(new Mercenary("Vini"));
    game.addPlayer(new Mercenary("João"));

    game.start();
  }
}