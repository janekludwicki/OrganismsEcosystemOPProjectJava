package ProjectOP;

public class Position {
   private int x;
   private int y;

    public  Position() {}

    public  Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public  int getY() {
        return y;
    }

   public  void setPosition(Position position) {
        x = position.x;
        y = position.y;
    }

    public  void move(int xMove, int yMove) {
        x += xMove;
        y += yMove;
    }

    public boolean IsCorrect() {
        if (x >= 0 && x < 20 && y >= 0 && y < 20)
            return true;
        return false;
    }
}
