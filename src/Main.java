import java.util.*;
public class Main {
    public static void main(String[] args) throws CustomError {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter board size >= 10, snake head position in (x, y) and direction as T, R, B or L");
        try {
            int size = in.nextInt();
            if(size<10)
                throw new CustomError("Invalid Board size");
            int X = in.nextInt();
            int Y = in.nextInt();
            if(X<1||X>size||Y<1||Y>size)
                throw new CustomError("Invalid head position");
            String direction = in.next().toUpperCase().charAt(0)+"";
            if(!"TBLR".contains(direction))
                throw new CustomError("Invalid Directions");
            Board b = new Board(size, X - 1, Y - 1, direction);
            if((direction.equals("T")&&b.getX_pos()>(b.getSize()-Board.getSnakeSize())) ||
                    (direction.equals("B")&&b.getX_pos()<Board.getSnakeSize()+1) ||
                    (direction.equals("L")&&b.getY_pos()>(b.getSize()-Board.getSnakeSize())) ||
                    (direction.equals("R")&&b.getX_pos()>Board.getSnakeSize()+1)){
                throw new CustomError("Invalid Head position");
            }
            b.printBoard();
            while (true) {
                System.out.println("Enter 1. to move straight / 2. to turn:");
                String option = in.next();
                if (option.equals("1")) {
                    System.out.println("Enter no of steps to move straight:");
                    int steps = in.nextInt();
                    b.moveSt(steps);
                } else if (option.equals("2")) {
                    System.out.println("Enter the direction to turn as 'T','B','L' or 'R':");
                    String turn_direction = in.next();
                    System.out.println("Enter no steps to move:");
                    int turn_steps = in.nextInt();
                    b.turn(turn_direction, turn_steps);
                } else
                    break;
                b.printBoard();
            }
        }
        catch(CustomError e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println("Snake Dead");
        }
    }
}