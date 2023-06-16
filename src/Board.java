import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    static int snakeSize = 3;
    static int x_food = -1;
    static int y_food = -1;
    private int size;
    private int x_pos;
    private int y_pos;
    private int[][] board;
    private String direction;
    public Board(int size, int x, int y, String direction) throws CustomError {
        setSize(size);
        Snake(x, y, direction, false);
    }
    public void Snake(int x, int y, String direction, boolean turning) throws CustomError {
        int old_X = getX_pos();
        int old_Y = getY_pos();
        String oldDirection = getDirection();
        setX_pos(x);
        setY_pos(y);
        setDirection(direction);
        int[][] game = new int[getSize()][getSize()];
        int x_distance = Math.abs(old_X - x);
        int y_distance = Math.abs(old_Y - y);
        int partSnake = x_distance!=0?x_distance:y_distance;
        int otherPart = getSnakeSize() - partSnake;
        int snake_size = turning?partSnake:getSnakeSize();
        for(int i=0;i<snake_size;i++) {
            if (direction.equals("T"))
                game[x + i][y] = 1;
            else if (direction.equals("B"))
                game[x - i][y] = 1;
            else if (direction.equals("L"))
                game[x][y + i] = 1;
            else if (direction.equals("R"))
                game[x][y - i] = 1;
            else
                throw new CustomError("Invalid inputs");
        }
        if(turning) {
            for (int i = 0; i < otherPart; i++) {
                if (oldDirection.equals("T"))
                    game[old_X + i][old_Y] = 1;
                else if (oldDirection.equals("B"))
                    game[old_X - i][old_Y] = 1;
                else if (oldDirection.equals("L"))
                    game[old_X][old_Y + i] = 1;
                else if (oldDirection.equals("R"))
                    game[old_X][old_Y - i] = 1;
                else
                    throw new CustomError("Invalid inputs");
            }
        }
        if(Board.x_food==getX_pos()&&Board.y_food==getY_pos()){
            setSnakeSize(getSnakeSize()+1);
            otherPart = getSnakeSize() - partSnake;
            snake_size = turning?partSnake:getSnakeSize();
            for(int i=0;i<snake_size;i++) {
                if (direction.equals("T"))
                    game[x + i][y] = 1;
                else if (direction.equals("B"))
                    game[x - i][y] = 1;
                else if (direction.equals("L"))
                    game[x][y + i] = 1;
                else if (direction.equals("R"))
                    game[x][y - i] = 1;
                else
                    throw new CustomError("Invalid inputs");
            }
            if(turning) {
                for (int i = 0; i < otherPart; i++) {
                    if (oldDirection.equals("T"))
                        game[old_X + i][old_Y] = 1;
                    else if (oldDirection.equals("B"))
                        game[old_X - i][old_Y] = 1;
                    else if (oldDirection.equals("L"))
                        game[old_X][old_Y + i] = 1;
                    else if (oldDirection.equals("R"))
                        game[old_X][old_Y - i] = 1;
                    else
                        throw new CustomError("Invalid inputs");
                }
            }
        }
        setBoard(game);
        if((Board.x_food==-1&&Board.y_food==-1)||(Board.x_food==getX_pos()&&Board.y_food==getY_pos())){
            generateFood();
        }
        game[Board.x_food][Board.y_food] = 7;
        setBoard(game);
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getX_pos() {
        return x_pos;
    }
    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }
    public int getY_pos() {
        return y_pos;
    }
    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }
    public int[][] getBoard() {
        return board;
    }
    public void setBoard(int[][] board) {
        this.board = board;
    }
    public static int getSnakeSize() {
        return snakeSize;
    }
    public static void setSnakeSize(int snakeSize) {
        Board.snakeSize = snakeSize;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public void turn(String direction, int n) throws CustomError {
        String currentDirection = getDirection();
        String leftRight = "LR";
        String topBottom = "TB";
        if((leftRight.contains(currentDirection)&&leftRight.contains(direction))||(topBottom.contains(currentDirection)&&topBottom.contains(direction)))
            throw new CustomError("Invalid Direction");
        int x = getX_pos(), y = getY_pos();
        if(direction.equals("T"))
            x-=n;
        else if (direction.equals("B"))
            x+=n;
        else if (direction.equals("L"))
            y-=n;
        else if (direction.equals("R"))
            y+=n;
        else
            throw new CustomError("Invalid Direction");
        if(n>=snakeSize-1)
            Snake(x, y, direction, false);
        else
            Snake(x, y, direction, true);
    }
    public void moveSt(int n) throws CustomError {
        int x = getX_pos(), y = getY_pos();
        if(getDirection().equals("T"))
           x-=n;
        else if (getDirection().equals("B"))
            x+=n;
        else if (getDirection().equals("L"))
            y-=n;
        else
            y+=n;
        if(x>getSize() || y>getSize() || x<0 || y<0)
            throw new CustomError("Invalid steps to move");
        Snake(x, y, getDirection(), false);
    }
    public void generateFood() {
        int[][] game = getBoard();
        Random r = new Random();
        while (true) {
            int n = r.nextInt(getSize()), m = r.nextInt(getSize());
            if (game[n][m] == 0) {
                Board.x_food = n;
                Board.y_food = m;
                break;
            }
        }
    }
    public void printBoard(){
        System.out.println("Board:");
        int[][] game_board = getBoard();
        for(int i = 0; i<game_board.length; i++){
            for(int j=0;j<game_board[i].length;j++){
                System.out.print(game_board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n***************");
    }
}
