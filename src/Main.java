public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        game.roll(8);
        game.roll(2);
        game.roll(10);
        game.roll(4);
        game.roll(4);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(2);
        game.roll(3);

        System.out.println(game.board.toString());
    }
}