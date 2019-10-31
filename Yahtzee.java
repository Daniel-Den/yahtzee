import java.util.*;

class Yahtzee{
    static final int NUMBER_OF_DICE = 5;
    static int[] dice = new int[NUMBER_OF_DICE];
    static final Hashtable<String, Integer> scoreCard = new Hashtable<String, Integer>();




    public static void main(String[] args){
        scoreCard.put("Yahtzee", -1);
        scoreCard.put("Large Straight", -1);
        scoreCard.put("Full House", -1);
        scoreCard.put("Three of a Kind", -1);
        scoreCard.put("Four of a Kind", -1);
        scoreCard.put("Small Straight", -1);
        scoreCard.put("Chance", -1);
        scoreCard.put("Ones", -1);
        scoreCard.put("Twos", -1);
        scoreCard.put("Threes", -1);
        scoreCard.put("Fours", -1);
        scoreCard.put("Fives", -1);
        scoreCard.put("Sixes", -1);
        scoreCard.put("Bonuses", 0);

        Scanner keyboard = new Scanner(System.in);
        informUser();
        for(int i = 0; i < 13; i++){
            printScoreCard();
            System.out.println("Rolling the dice...");
            rollDice();
            System.out.println(diceToString());
            if(reRoll(dice)){
                reRoll(dice);
            }
            String[] scores = possibleScores();
            if(noOptions(scores)){
                scores = possibleZeros();
                System.out.println("You have no possible points to score. Please select a score to be 0.");
                printScoringOptions(scores);
                String scoreChoice = keyboard.nextLine();
                while(validScore(scoreChoice) == false){
                    System.out.println(scoreChoice + " is not a valid scoring option. Please enter a valid option.");
                    scoreChoice = keyboard.nextLine();
                }
                if(scoreChoice.equals("Chance")){
                    scoreCard.put("Chance", 0);
                }
                if(scoreChoice.equals("Large Straight")){
                    scoreCard.put("Large Straight", 0);
                }
                if(scoreChoice.equals("Small Straight")){
                    scoreCard.put("Small Straight", 0);
                }
                if(scoreChoice.equals("Full House")){
                    scoreCard.put("Full House", 0);
                }
                if(scoreChoice.equals("Yahtzee")){
                    scoreCard.put("Yahtzee", 0);
                }
                if(scoreChoice.equals("Three of a Kind")){
                    scoreCard.put("Three of a Kind", 0);
                }
                if(scoreChoice.equals("Four of a Kind")){
                    scoreCard.put("Four of a Kind", 0);
                }
                if(scoreChoice.equals("Ones")){
                    scoreCard.put("Ones", 0);
                }
                if(scoreChoice.equals("Twos")){
                    scoreCard.put("Twos", 0);
                }
                if(scoreChoice.equals("Threes")){
                    scoreCard.put("Threes", 0);
                }
                if(scoreChoice.equals("Fours")){
                    scoreCard.put("Fours", 0);
                }
                if(scoreChoice.equals("Fives")){
                    scoreCard.put("Fives", 0);
                }
                if(scoreChoice.equals("Sixes")){
                    scoreCard.put("Sixes", 0);
                }
                System.out.println("You selected " + scoreChoice + " and scored " + scoreCard.get(scoreChoice) + " points.");
            }
            else{
                System.out.println("Please select a scoring option: ");
                printScoringOptions(scores);
                String scoreChoice = keyboard.nextLine();
                while(validScore(scoreChoice) == false){
                    System.out.println(scoreChoice + " is not a valid scoring option. Please enter a valid option.");
                    scoreChoice = keyboard.nextLine();
                }
                if(scoreChoice.equals("Chance")){
                    scoreCard.put("Chance", chance());
                }
                if(scoreChoice.equals("Large Straight")){
                    scoreCard.put("Large Straight", largeStraightScore());
                }
                if(scoreChoice.equals("Small Straight")){
                    scoreCard.put("Small Straight", smallStraightScore());
                }
                if(scoreChoice.equals("Full House")){
                    scoreCard.put("Full House", fullHouseScore());
                }
                if(scoreChoice.equals("Yahtzee")){
                    int initialScore = scoreCard.get("Yahtzee");
                    if(initialScore > 0){
                        initialScore += 100;
                        System.out.println(initialScore);
                        scoreCard.put("Yahtzee", initialScore);
                        i -= 1;
                    }
                    else{
                        scoreCard.put("Yahtzee", yahtzeeScore());
                    }
                }

                if(scoreChoice.equals("Three of a Kind")){
                    scoreCard.put("Three of a Kind", threeOfAKindScore());
                }
                if(scoreChoice.equals("Four of a Kind")){
                    scoreCard.put("Four of a Kind", fourOfAKindScore());
                }
                if(scoreChoice.equals("Ones")){
                    scoreCard.put("Ones", numbersScore(1));
                }
                if(scoreChoice.equals("Twos")){
                    scoreCard.put("Twos", numbersScore(2));
                }
                if(scoreChoice.equals("Threes")){
                    scoreCard.put("Threes", numbersScore(3));
                }
                if(scoreChoice.equals("Fours")){
                    scoreCard.put("Fours", numbersScore(4));
                }
                if(scoreChoice.equals("Fives")){
                    scoreCard.put("Fives", numbersScore(5));
                }
                if(scoreChoice.equals("Sixes")){
                    scoreCard.put("Sixes", numbersScore(6));
                }
                System.out.println("You selected " + scoreChoice + " and scored " + scoreCard.get(scoreChoice) + " points.");


            }

        }
        String result = "Your score is: ";
        int score_final = finalScore();
        if(bonus()){
            score_final += 35;
        }
        result += score_final;
        System.out.println(result);

    }

    public static boolean validScore(String usrChoice){
        if(scoreCard.containsKey(usrChoice) != true){
            return false;
        }
        if(usrChoice.equals("Yahtzee")){
            return true;
        }
        if(scoreCard.get(usrChoice) != -1){
            return false;
        }
        return true;
    }

    public static void printScoreCard(){

        System.out.println("\nYahtzee           " + scoreCard.get("Yahtzee"));
        System.out.println("Large Straight    " + scoreCard.get("Large Straight"));
        System.out.println("Full House        " + scoreCard.get("Full House"));
        System.out.println("Three of a Kind   " + scoreCard.get("Three of a Kind"));
        System.out.println("Four of a Kind    " + scoreCard.get("Four of a Kind"));
        System.out.println("Small Straight    " + scoreCard.get("Small Straight"));
        System.out.println("Chance            " + scoreCard.get("Chance"));
        System.out.println("Ones              " + scoreCard.get("Ones"));
        System.out.println("Twos              " + scoreCard.get("Twos"));
        System.out.println("Threes            " + scoreCard.get("Threes"));
        System.out.println("Fours             " + scoreCard.get("Fours"));
        System.out.println("Fives             " + scoreCard.get("Fives"));
        System.out.println("Sixes             " + scoreCard.get("Sixes"));
        System.out.println("Bonuses           " + scoreCard.get("Bonuses") + "\n");

    }

    public static String diceToString(){
        String result = "Your dice are: ";
        for(int die : dice){
            result += die + " ";
        }
        return result;
    }

    public static boolean isYahtzee(){
        for(int die : dice){
            if(die != dice[0]){
                return false;
            }
        }
        return true;
    }

    public static void rollDice(int[] diceToChange){
        for(int i = 0; i < diceToChange.length; i++){
            dice[diceToChange[i] - 1] = getRandomDieValue();
        }
    }

    public static void rollDice(){
        for(int i = 0; i < NUMBER_OF_DICE; i++){
            dice[i] = getRandomDieValue();
        }
    }

    public static int getRandomDieValue(){
        return (int) (Math.random() * 6 + 1);
    }

    public static int[] convert(String s){
        StringTokenizer st = new StringTokenizer(s);
        int[] a = new int[st.countTokens()];
        int i = 0;

        while(st.hasMoreTokens()){
            a[i++] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    public static int chance(){
        int sum = 0;
        for(int i = 0; i < dice.length; i ++){
            sum += dice[i];
        }
        return sum;
    }

    public static boolean largeStraight(){
        Arrays.sort(dice);
        if(scoreCard.get("Large Straight") != -1){
            return false;
        }
        for(int i = 0; i < dice.length - 1; i++){
            if(dice[i] + 1 != dice[i + 1]){
                return false;
            }
        }
        return true;
    }

    public static boolean fullHouse(){
        Arrays.sort(dice);
        if(scoreCard.get("Full House") != -1){
            return false;
        }
        if (dice[0] == dice[1] && dice[1] == dice[2] && dice[3] == dice[4]){
            return true;
        }
        if (dice[0] == dice[1] && dice[2] == dice[3] && dice[3] == dice[4]){
            return true;
        }
        return false;
    }

    public static boolean threeOfAKind(){
        Arrays.sort(dice);
        if(scoreCard.get("Three of a Kind") != -1){
            return false;
        }
        if (dice[0] == dice[1] && dice[1] == dice[2]){
            return true;
        }
        if (dice[1] == dice[2] && dice[2] == dice[3]){
            return true;
        }
        if (dice[2] == dice[3] && dice[3] == dice[4]){
            return true;
        }
        return false;
    }

    public static boolean fourOfAKind(){
        Arrays.sort(dice);
        if(scoreCard.get("Four of a Kind") != -1){
            return false;
        }
        if (dice[0] == dice[1] && dice[1] == dice[2] && dice[2] == dice[3]){
            return true;
        }
        if (dice[1] == dice[2] && dice[2] == dice[3] && dice[3] == dice[4]){
            return true;
        }
        if(dice[0] == dice[1] && dice[1] == dice[3] && dice[3] == dice[4]){
            return true;
        }
        return false;
    }

    public static boolean smallStraight(){
        Arrays.sort(dice);
        if(scoreCard.get("Small Straight") != -1){
            return false;
        }
        int counter = 0;
        for(int i = 0; i < NUMBER_OF_DICE - 1; i++){
            if(dice[i] + 1 == dice[i+1]){
                counter = counter + 1;
            }
        } if(counter >= 3){
            return true;
        } else{
            return false;
        }
    }
    public static boolean bonus(){
        int value=scoreCard.get("Ones")+scoreCard.get("Twos")+scoreCard.get("Threes")+scoreCard.get("Fours")+scoreCard.get("Fives")+scoreCard.get("Sixes");
        if (value>=63){
            return true;
        }
        return false;
    }
    public static boolean Ones(){
      int counter = 0;
      for(int i = 0; i < NUMBER_OF_DICE - 1; i++){
        if(dice[i] == 1){
          counter = counter + 1;
        }
      }
      if(counter > 0){
        return true;
      }
      return false;

    }
    public static boolean Twos(){
      int counter = 0;
      for(int i = 0; i < NUMBER_OF_DICE - 1; i++){
        if(dice[i] == 2){
          counter = counter + 1;
        }
      }
      if(counter > 0){
        return true;
      }
      return false;

    }
    public static boolean Threes(){
      int counter = 0;
      for(int i = 0; i < NUMBER_OF_DICE - 1; i++){
        if(dice[i] == 3){
          counter = counter + 1;
        }
      }
      if(counter > 0){
        return true;
      }
      return false;

    }
    public static boolean Fours(){
      int counter = 0;
      for(int i = 0; i < NUMBER_OF_DICE - 1; i++){
        if(dice[i] == 4){
          counter = counter + 1;
        }
      }
      if(counter > 0){
        return true;
      }
      return false;

    }
    public static boolean Fives(){
      int counter = 0;
      for(int i = 0; i < NUMBER_OF_DICE - 1; i++){
        if(dice[i] == 5){
          counter = counter + 1;
        }
      }
      if(counter > 0){
        return true;
      }
      return false;

    }
    public static boolean Sixes(){
      int counter = 0;
      for(int i = 0; i < NUMBER_OF_DICE - 1; i++){
        if(dice[i] == 6){
          counter = counter + 1;
        }
      }
      if(counter > 0){
        return true;
      }
      return false;

    }



    public static String[] possibleScores(){
        String[] list = new String[13];
        if(scoreCard.get("Chance") == -1){
            list[0] = "Chance";
        }
        if(Ones() && scoreCard.get("Ones") == -1){
            list[1] = "Ones";
        }
        if(Twos() && scoreCard.get("Twos") == -1){
            list[2] = "Twos";
        }
        if(Threes() && scoreCard.get("Threes") == -1){
            list[3] = "Threes";
        }
        if(Fours() && scoreCard.get("Fours") == -1){
            list[4] = "Fours";
        }
        if(Fives() && scoreCard.get("Fives") == -1){
            list[5] = "Fives";
        }
        if(Sixes() && scoreCard.get("Sixes") == -1){
            list[6] = "Sixes";
        }
        if (largeStraight() && scoreCard.get("Large Straight") == -1){
            list[7] = "Large Straight";
        }
        if (fullHouse() && scoreCard.get("Full House") == -1){
            list[8] = "Full House";
        }
        if (threeOfAKind() && scoreCard.get("Three of a Kind") == -1){
            list[9] = "Three of a Kind";
        }
        if (fourOfAKind() && scoreCard.get("Four of a Kind") == -1){
            list[10] = "Four of a Kind";
        }
        if (smallStraight() && scoreCard.get("Small Straight") == -1){
            list[11] = "Small Straight";
        }
        if (isYahtzee() && scoreCard.get("Yahtzee") != 0){
            list[12] = "Yahtzee";
        }
        return list;
    }

    public static int yahtzeeScore(){
        int score = 0;
        if(isYahtzee()){
            score = 50;
        }
        return score;
    }

    public static int largeStraightScore(){
        int score = 0;
        if(largeStraight()){
            score = 40;
        }
        return score;
    }

    public static int smallStraightScore(){
        int score = 0;
        if(smallStraight()){
            score = 30;
        }
        return score;
    }


    public static int fullHouseScore(){
        int score = 0;
        if(fullHouse()){
            score = 25;
        }
        return score;
    }

    public static int threeOfAKindScore(){
        int score = 0;
        if(threeOfAKind()){
            score = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
        }
        return score;
    }

    public static int fourOfAKindScore(){
        int score = 0;
        if(fourOfAKind()){
            score = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
        }
        return score;
    }

    public static int numbersScore(int number){
        int score = 0;
        for(int die : dice){
            if(die == number){
                score += number;
            }
        }
        return score;
    }

    public static int finalScore(){
        Enumeration names;
        String key;
        names = scoreCard.keys();
        int score = 0;
        while(names.hasMoreElements()){
            key = (String) names.nextElement();
            int value = scoreCard.get(key);
            if(value >= 0){
                score = score + value;
            }
        }
      return score;

    }

    public static boolean reRoll(int[] dice){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Would you like to reroll?");
        String choice = keyboard.nextLine();
        if(choice.equals("yes")){
            System.out.println("Which dice do you want to reroll?");
            choice = keyboard.nextLine();
            int[] reroll = convert(choice);
            rollDice(reroll);
            System.out.println(diceToString());
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean noOptions(String[] scores){
        for(int x = 0; x < scores.length; x ++){
            if (scores[x] != null){
                return false;
            }
        }
        return true;
    }

    public static void printScoringOptions(String[] scores){
        System.out.println("Your options are: ");
        for(int x = 0; x < scores.length; x ++){
            if (scores[x] != null){
                System.out.print(scores[x] + "\n");
            }
        }
    }

    public static String[] possibleZeros(){
        String[] list = new String[13];
        if(scoreCard.get("Chance") == -1){
            list[0] = "Chance";
        }
        if(scoreCard.get("Ones") == -1){
            list[1] = "Ones";
        }
        if(scoreCard.get("Twos") == -1){
            list[2] = "Twos";
        }
        if(scoreCard.get("Threes") == -1){
            list[3] = "Threes";
        }
        if(scoreCard.get("Fours") == -1){
            list[4] = "Fours";
        }
        if(scoreCard.get("Fives") == -1){
            list[5] = "Fives";
        }
        if(scoreCard.get("Sixes") == -1){
            list[6] = "Sixes";
        }
        if (scoreCard.get("Large Straight") == -1){
            list[7] = "Large Straight";
        }
        if (scoreCard.get("Full House") == -1){
            list[8] = "Full House";
        }
        if (scoreCard.get("Three of a Kind") == -1){
            list[9] = "Three of a Kind";
        }
        if (scoreCard.get("Four of a Kind") == -1){
            list[10] = "Four of a Kind";
        }
        if (scoreCard.get("Small Straight") == -1){
            list[11] = "Small Straight";
        }
        if (scoreCard.get("Yahtzee") == -1){
            list[12] = "Yahtzee";
        }
        return list;
    }

    public static void informUser(){
        System.out.println(" ");
        System.out.println("Here are some helpers for effective gameplay:");
        System.out.println(" ");
        System.out.println("When you reroll dice, enter a number from one to five, this number should correspond with the place in the sequence of the die you want to role.");
        System.out.println("THE ORDER IN WHICH YOU ENTER NUMBERS DOES NOT MATTER");
        System.out.println("YOU MUST PLACE SPACES BETWEEN NUMBERS!");
        System.out.println("Invalid inputs will be discarded, and with no valid inputs no dice will be rerolled and you will proceed to the next action");


    }

}
