import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import players.Player;

public class Game {
  private boolean firstExecution = false;
  private List<Player> players;

  public Game() {
    this.players = new ArrayList<>();
  }

  public void addPlayer(Player player) {
    this.players.add(player);
  }

  public void showPlayers() {
    for (Player player : players)
      player.print(null, null, null, null);
  }

  public void start() {
    Scanner scanner = new Scanner(System.in);
    Collections.shuffle(this.players);
    if (!firstExecution) {
      firstExecution = true;
      System.out.println("\nJogadores:");
      showPlayers();
    }
    int alives = 0;
    int round = 0;
    while (true) {
      System.out.print("Prosseguir para a próxima rodada? (Y/n): ");
      String value;
      try {
        value = scanner.nextLine();
      } catch (NoSuchElementException e) {
        value = "N";
        System.out.println();
      }

      if (value.equalsIgnoreCase("Y")) {
        System.out.println("\n\nRound " + round++ + ":\n");
        new Round(this.players);
        alives = 0;
        for (Player player : this.players) {
          if (player.isAlive())
            alives++;
          if (alives > 1)
            break;
        }

        if (alives < 2) {
          break;
        }
        if (firstExecution) {
          System.out.println("\nResultado pós round " + round + ":");
          showPlayers();
        }
      } else if (value.equalsIgnoreCase("N"))
        break;

    }

    showPlayers();
    if (alives == 1) {
      Player winner = null;
      for (Player player : players) {
        if (player.isAlive()) {
          winner = player;
          break;
        }
      }
      if (winner != null) {
        System.out.println("🏅 O vencedor foi " + winner.toString());
      } else
        System.out.println("Não houve nenhum vencedor!");
    } else {
      System.out.println("Não houve nenhum vencedor!");
    }
    scanner.close();
  }
}
