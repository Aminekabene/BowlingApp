import java.util.*;

public class Game {

    // The index i in this linkedList represent the frame number and the nested Hash map in it stores the roll number as the key and the score of that roll as the value.
    //        Frame            roll     scoreOfRoll
    public LinkedList<HashMap<Integer, Integer>> board = new LinkedList<>();
    private boolean isGameOver = false;
    private int totalScore = 0;


    public void roll(int pins){

        if(pins > 10){
            throw new RuntimeException("Error you can not knock down more than 10 pins.");
        }

        if(isGameOver){
            throw new RuntimeException("Can not roll anymore the Game is Over.");
        }

        // This is the First roll
        if (board.isEmpty()){
            board.add(new HashMap<Integer,Integer>(){ {put(1,pins);} });
            return;
        }

        int currentframe = board.size()-1;
        if(board.get(currentframe).size() == 1 && board.get(currentframe).get(1) != 10){

            if (pins + board.get(currentframe).get(1) > 10){
                throw new RuntimeException("Error you can not knock down "+pins+" pins when there are only "+ (10-board.get(currentframe).get(1))+" pins remaining.");
            }

            board.get(currentframe).put(2,pins);

            if( (board.size() == 10 && (board.get(currentframe).get(1) + board.get(currentframe).get(2)) < 10) || board.size()==11){
                isGameOver = true;
                System.out.println("Game Over !");
            }

            return;
        }
        else{
            board.add(new HashMap<Integer,Integer>(){ {put(1,pins);} });
            
            if (board.size()==11){
                int previousFrame = board.size()-2;
                if(board.get(previousFrame).size()==2){
                    isGameOver = true;
                    System.out.println("Game Over !");
                }
            }
        }


    }

    public void score(){

    }

    /* Helper Methods */

    private int computeFrameScore(HashMap<Integer,Integer> map){
        int sum =0;
        for (int rollScore : map.values()){
            sum += rollScore;
        }
        return sum;
    }

}
