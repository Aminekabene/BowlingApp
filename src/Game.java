import java.util.*;

public class Game {

    // The index i in this linkedList represent the frame number and the nested Hash map in it stores the roll number as the key and the score of that roll as the value.
    //        Frame            roll     scoreOfRoll
    private LinkedList<HashMap<Integer, Integer>> board = new LinkedList<>();
    private boolean isGameOver = false;


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
            System.out.println(board.toString());
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
            System.out.println(board.toString());
            return;
        }
        else if(board.size() == 11 && board.get(currentframe).size() == 1){
            board.get(currentframe).put(2,pins);
            isGameOver = true;
            System.out.println("Game Over !");
        }
        else{
            board.add(new HashMap<Integer,Integer>(){ {put(1,pins);} });
        }
        System.out.println(board.toString());
    }

    //This method compute the score for all rolls performed
    public void score(){
        int totalScore = 0;

        if (board.isEmpty()){
            throw new RuntimeException("Can not calculate the score, game has not started yet.");
        }

        for (int i =0; i < board.size(); i++){

            if (i > 0){
                int previousFrame = i-1;

                // check if previous frame was a stike
                if(board.get(previousFrame).size() == 1){
                    totalScore += findNextTwoRolls(i);
                }
                // check if previous frame was a spare
                else if(board.get(previousFrame).size() == 2 && computeFrameScore(board.get(previousFrame)) == 10){
                    totalScore += board.get(i).get(1);
                }
            }

            if(i+1 != 11){
                totalScore += computeFrameScore(board.get(i));
            }
        }

        System.out.println("The Score is: " + totalScore);
    }

    /* Helper Methods */

    private int computeFrameScore(HashMap<Integer,Integer> map){
        int sum = 0;
        for (int rollScore : map.values()){
            sum += rollScore;
        }
        return sum;
    }

    private int findNextTwoRolls(int index){

        if (board.get(index).size() == 2)
            return computeFrameScore(board.get(index));

        int sum = 0;
        int count = 0;
        for(int i = index; i < board.size(); i++){
            if (count == 2) {
                break;
            }
            sum += board.get(i).get(1);
            count++;
        }

        return  sum;
    }

}
