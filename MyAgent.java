import java.util.Random;
import java.util.ArrayList;

public class MyAgent extends Agent
{
    Random random;
    private int bottomRow = myGame.getRowCount()-1;
    private int topRow = myGame.getRowCount()-5;
    private int endColumn = myGame.getColumnCount()-1;
    private int begColumn = myGame.getColumnCount()-6;
   
   /**
    *counts how many slots on the bottom row have red tokens in order to figure out if it is the first turn
    *@return number of bottom row slots are filled with red
   */
   public int rowFiveRedTokens(){
       int redToken = 0;
       for (int i = 0; i < myGame.getColumnCount(); i++){
           if (myGame.getColumn(i).getSlot(5).getIsRed()){
               redToken = redToken + 1;
            }
       }
    return redToken;
   }
   
   /**
    * checks if specified slot is open
    * @param column to see if specific row is filled
    * @param row to see if specific column is filled
    * @return if slot is open
    */
   public boolean isOpen(int column, int row){
        boolean isOpen = false;
            if (myGame.getColumn(column).getSlot(row).getIsFilled() == false){
                isOpen = true;
            }
            else if (myGame.getColumn(column).getSlot(row).getIsFilled() == true){
                isOpen = false;
            }
        return isOpen;
    }
   
    
  
  /**
   * get distance from border in specified direction from specified slot position
   * @param column direction
   * @param row direction
   * @param column of slot 
   * @param row of slot
   * @return distance from border in specified direction from specified slot position
   */
  public int getDistanceFromBorder(int columnDir, int rowDir, int column, int row){
    int distanceFromBorder = 0;
      if(columnDir == -1 && rowDir == -1){distanceFromBorder = getTopLeftDistanceFromBorder(column, row);} //1 direction
      else if(columnDir == 1 && rowDir == -1){distanceFromBorder = getTopRightDistanceFromBorder(column, row);} //3 direction
      else if(columnDir == 1 && rowDir == 0){distanceFromBorder = endColumn - column;} //4 direction
      else if(columnDir == 1 && rowDir == 1){distanceFromBorder = getBottomRightDistanceFromBorder(column, row);} //5 direction
      else if(columnDir == 0 && rowDir == 1){distanceFromBorder = bottomRow - row;} //6
      else if(columnDir == -1 && rowDir == 1){distanceFromBorder = getBottomLeftDistanceFromBorder(column, row);} //7 direction
      else if(columnDir == -1 && rowDir == 0){distanceFromBorder = column;} //8 direction
      else if(columnDir == 0 && rowDir == 0){distanceFromBorder = 1;} //0 no direction
      return distanceFromBorder;
    }   
  
    /**
     * get the top left distance from border
     * @param column position of top left position
     * @param row position of top left position
     * @return top left distance from border
     */  
    public int getTopLeftDistanceFromBorder(int column, int row){
        int topLeftDistance = 0;
        if (row == 0 || column == 0){
            topLeftDistance = 0;
        }       
        else if (column == 1 && row > 0){
            topLeftDistance = column;
        }   
        else if (column == 2 && row > 0){
           if(row == 1){
               topLeftDistance = row;
            }
            else{topLeftDistance = column;}
        }   
        else if (column == 3){
            if (row > 0 && row <= 2){
                topLeftDistance = row;
            }
            else if (row > 2){
                topLeftDistance = column;
            }
        }
        else if (column == 4){
           if (row < 5 && row > 0){
               topLeftDistance = row;
            }
            else if(row == 5){
               topLeftDistance = column;
            }
        }
        else if (column == 5 || column == 6 && row > 0){
            topLeftDistance = row;
        }
        return topLeftDistance;
      }
      
     /**
     * get the top right distance from border
     * @param column position of top right position
     * @param row position of top right position
     * @return top right distance from border
     */  
      public int getTopRightDistanceFromBorder(int column, int row){
        int topRightDistance = 0;
        if (row == 0 || column == myGame.getColumnCount()-1){
            topRightDistance = 0;
        }       
        else if (column == (myGame.getColumnCount()-1) - 1 && row > 0){
            topRightDistance = 6 - column;
        }   
        else if (column == (myGame.getColumnCount()-1) - 2 && row > 0){
           if(row == 1){
               topRightDistance = row;
            }
            else{topRightDistance = 6 - column;}
        }   
        else if (column == (myGame.getColumnCount()-1) - 3){
            if (row > 0 && row <= 2){
                topRightDistance = row;
            }
            else if (row > 2){
                topRightDistance = 6 - column;
            }
        }
        else if (column == (myGame.getColumnCount()-1) - 4){
           if (row < 5 && row > 0){
               topRightDistance = row;
            }
            else if(row == (myGame.getRowCount()-1)){
               topRightDistance = 6 - column;
            }
        }
        else if (column == (myGame.getColumnCount()-1) - 5 || column == (myGame.getColumnCount()-1) - 6 && row > 0){
            topRightDistance = row;
        }
        return topRightDistance;
      }
      
     /**
     * get bottom left distance from border
     * @param column position of bottom left position
     * @param row position of bottom left position
     * @return bottom left distance from border
     */  
      public int getBottomLeftDistanceFromBorder(int column, int row){
        int bottomLeftDistance = 0;
        if (row == bottomRow || column == 0){
            bottomLeftDistance = 0;
        }       
        else if (column == 1 && row < bottomRow){
            bottomLeftDistance = column;
        }   
        else if (column == 2 && row < bottomRow){
           if(row == (bottomRow-1)){
               bottomLeftDistance = 5 - row;
            }
            else{bottomLeftDistance = column;}
        }   
        else if (column == 3){
            if (row < bottomRow && row >= bottomRow-2){
                bottomLeftDistance = 5 - row;
            }
            else if (row < (bottomRow)-2){
                bottomLeftDistance = column;
            }
        }
        else if (column == 4){
           if (row > (bottomRow)-5 && row < bottomRow){
               bottomLeftDistance = 5 - row;
            }
            else if(row == (bottomRow-5)){
               bottomLeftDistance = column;
            }
        }
        else if (column == 5 || column == 6 && row < bottomRow){
            bottomLeftDistance = 5 - row;
        }
        return bottomLeftDistance;
    }
      
     /**
     * get bottom right distance from border
     * @param column position of bottom right position
     * @param row position of bottom right position
     * @return bottom right distance from border
     */  
      public int getBottomRightDistanceFromBorder(int column, int row){
        int bottomRightDistance = 0;
        if (row == bottomRow || column == endColumn){
            bottomRightDistance = 0;
        }       
        else if (column == endColumn - 1 && row < bottomRow){
            bottomRightDistance = 6 - column;
        }   
        else if (column == endColumn - 2 && row < bottomRow){
           if(row == (bottomRow-1)){
               bottomRightDistance = 5 - row;
            }
            else{bottomRightDistance = 6 - column;}
        }   
        else if (column == endColumn - 3){
            if (row < bottomRow && row >= bottomRow-2){
                bottomRightDistance = 5 - row;
            }
            else if (row < (bottomRow)-2){
                bottomRightDistance = 6 - column;
            }
        }
        else if (column == endColumn - 4){
           if (row > (bottomRow)-5 && row < bottomRow){
               bottomRightDistance = 5 - row;
            }
            else if(row == (bottomRow-5)){
               bottomRightDistance = 6 - column;
            }
        }
        else if (column == endColumn - 5 || column == endColumn - 6 && row < bottomRow){
            bottomRightDistance = 5 - row;
        }
        return bottomRightDistance;
    }

   
   /**
    * checks if specified slot have a filled spot undernath of it in order to place a token in the current slot
    * @param column specified
    * @param column specified
    * @return if the specified slot has support unerneath of it in order to place a token in the current slot
    */
       public boolean hasSupport(int column, int row){
        boolean hasSupport = false;
        
        if (row == 5){
            hasSupport = true;
        }
        
        else{
            if(myGame.getColumn(column).getSlot(row + 1).getIsFilled() == true){
                hasSupport = true;
            }
        }
        return hasSupport;
        }
    /**
     * counts the number of red or yellow tokens in a row in a certain direction
     * @param the number of consecutive red or yellow tokens you are looking for
     * @param the color of consecutive tokens you are counting 
     * @param the column of slot you are counting consecutive tokens from
     * @param the row of slot you are counting consecutive tokens from
     * @param the column direction you are counting consecutive tokens
     * @param the row direction you are counting consecutive tokens
    */
    public int numInARow(int numberOfMatches, String color, int column, int row, int columnDir, int rowDir){
        int counter = 0;
        int redCounter = 0;
        int yellowCounter = 0;
        if (getDistanceFromBorder(columnDir, rowDir, column, row) >= numberOfMatches){
            for(int i = 1; i <= numberOfMatches; i++){
                if(color.equals("red") && myGame.getColumn(column + (columnDir * i)).getSlot(row + (rowDir * i)).getIsRed()){
                    redCounter = redCounter + 1;
                }
                else if(color.equals("yellow") && !myGame.getColumn(column + (columnDir * i)).getSlot(row + (rowDir * i)).getIsRed() && !isOpen(column + (columnDir*i), row + (rowDir*i))){
                    yellowCounter = yellowCounter + 1;
                    counter = yellowCounter;
                }
            }
            if (color.equals("red")){
                counter = redCounter;
            }
            else if (color.equals("yellow")) {counter = yellowCounter;}
        }
        return counter;
    }
    
    /**
     * Constructs a new agent, giving it the game and telling it whether it is Red or Yellow.
     * 
     * @param game The game the agent will be playing.
     * @param iAmRed True if the agent is Red, False if the agent is Yellow.
     */
    public MyAgent(Connect4Game game, boolean iAmRed)
    {
        super(game, iAmRed);
        random = new Random();
    }

    /**
     * The move method is run every time it is this agent's turn in the game. You may assume that
     * when move() is called, the game has at least one open slot for a token, and the game has not
     * already been won.
     * 
     * By the end of the move method, the agent should have placed one token into the game at some
     * point.
     * 
     * After the move() method is called, the game engine will check to make sure the move was
     * valid. A move might be invalid if:
     * - No token was place into the game.
     * - More than one token was placed into the game.
     * - A previous token was removed from the game.
     * - The color of a previous token was changed.
     * - There are empty spaces below where the token was placed.
     * 
     * If an invalid move is made, the game engine will announce it and the game will be ended.
     * 
     */
    
    private ArrayList<Integer> theyCanWin = xInRow(3, "yellow");
    private ArrayList<Integer> iCanWin = xInRow(3, "red");
    private ArrayList<Integer> twoSlotOptions = xInRow(2, "red");
    private ArrayList<Integer> oneSlotOptions = xInRow(1, "red");
    public void move()
    {
        Random generator = new Random();
        int LeftOrRight = generator.nextInt(2);
        int directionTriggered = 0;
        if (rowFiveRedTokens() < 1){
           if (!myGame.getColumn(3).getSlot(5).getIsFilled()){ 
               moveOnColumn(3);
               theyCanWin = xInRow(3, "yellow");
               iCanWin = xInRow(3, "red");
               twoSlotOptions = xInRow(2, "red");
               oneSlotOptions = xInRow(1, "red");
           }
           else if (myGame.getColumn(3).getSlot(5).getIsFilled()){
               //move left 
               if (LeftOrRight == 0){
                   moveOnColumn(2);
                   theyCanWin = xInRow(3, "yellow");
                   iCanWin = xInRow(3, "red");
                   twoSlotOptions = xInRow(2, "red");
                   oneSlotOptions = xInRow(1, "red"); 
                }
                //move right
                else if (LeftOrRight == 1){
                    moveOnColumn(4);
                    theyCanWin = xInRow(3, "yellow");
                    iCanWin = xInRow(3, "red");
                    twoSlotOptions = xInRow(2, "red");
                    oneSlotOptions = xInRow(1, "red");
                }
           }
        }
        else{ 
            int columnMove = 0;
            theyCanWin = xInRow(3, "yellow");
            iCanWin = xInRow(3, "red");
            twoSlotOptions = xInRow(2, "red");
            oneSlotOptions = xInRow(1, "red");
            
            if (theyCanWin.size()>0){
                if (theyCanWin.size() == 1) {
                    columnMove = theyCanWin.get(0);
                } else {
                    int option = generator.nextInt(theyCanWin.size()-1);
                    columnMove = theyCanWin.get(option);
                }
                moveOnColumn(columnMove);
                theyCanWin = xInRow(3, "yellow");
                iCanWin = xInRow(3, "red");
                twoSlotOptions = xInRow(2, "red");
                oneSlotOptions = xInRow(1, "red");
            }
            else if (iCanWin.size()>0){
                if (iCanWin.size() == 1) {
                    columnMove = iCanWin.get(0);
                } else {
                    int option = generator.nextInt(iCanWin.size()-1);
                    columnMove = iCanWin.get(option);
                }
                moveOnColumn(columnMove);
                theyCanWin = xInRow(3, "yellow");
                iCanWin = xInRow(3, "red");
                twoSlotOptions = xInRow(2, "red");
                oneSlotOptions = xInRow(1, "red");
            }
            else if (twoSlotOptions.size()>0){
                if (twoSlotOptions.size() == 1) {
                    columnMove = twoSlotOptions.get(0);
                } else {
                    int option = generator.nextInt(twoSlotOptions.size()-1);
                    columnMove = twoSlotOptions.get(option);
                }
                moveOnColumn(columnMove);
                theyCanWin = xInRow(3, "yellow");
                iCanWin = xInRow(3, "red");
                twoSlotOptions = xInRow(2, "red");
                oneSlotOptions = xInRow(1, "red");
            }
            else if (oneSlotOptions.size()>0){
                if (oneSlotOptions.size() == 1) {
                    columnMove = oneSlotOptions.get(0);
                } else {
                    int option = generator.nextInt(oneSlotOptions.size()-1);
                    columnMove = oneSlotOptions.get(option);
                }
                moveOnColumn(columnMove);
                theyCanWin = xInRow(3, "yellow");
                iCanWin = xInRow(3, "red");
                twoSlotOptions = xInRow(2, "red");
                oneSlotOptions = xInRow(1, "red");
            }
        }
    }
       

    /**
     * Drops a token into a particular column so that it will fall to the bottom of the column.
     * If the column is already full, nothing will change.
     * 
     * @param columnNumber The column into which to drop the token.
     */
    public void moveOnColumn(int columnNumber){
        int lowestEmptySlotIndex = getLowestEmptyIndex(myGame.getColumn(columnNumber));   // Find the top empty slot in the column
                                                                                                  // If the column is full, lowestEmptySlot will be -1
        if (lowestEmptySlotIndex > -1)  // if the column is not full
        {
            Connect4Slot lowestEmptySlot = myGame.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);  // get the slot in this column at this index
            if (iAmRed) // If the current agent is the Red player...
            {
                lowestEmptySlot.addRed(); // Place a red token into the empty slot
            }
            else // If the current agent is the Yellow player (not the Red player)...
            {
                lowestEmptySlot.addYellow(); // Place a yellow token into the empty slot
            }
        }
    }

    /**
     * Returns the index of the top empty slot in a particular column.
     * 
     * @param column The column to check.
     * @return the index of the top empty slot in a particular column; -1 if the column is already full.
     */
    public int getLowestEmptyIndex(Connect4Column column) {
        int lowestEmptySlot = -1;
        for  (int i = 0; i < column.getRowCount(); i++)
        {
            if (!column.getSlot(i).getIsFilled())
            {
                lowestEmptySlot = i;
            }
        }
        return lowestEmptySlot;
    }

    /**
     * Returns a random valid move. If your agent doesn't know what to do, making a random move
     * can allow the game to go on anyway.
     * 
     * @return a random valid move.
     */
    public int randomMove()
    {
        int i = random.nextInt(myGame.getColumnCount());
        while (getLowestEmptyIndex(myGame.getColumn(i)) == -1)
        {
            i = random.nextInt(myGame.getColumnCount());
        }
        return i;
    }
    
  /**
   *collects a list of all of the columns that meet the criteria of a certain number of a color's tokens in a row
   *@param number of tokens in a row searching for
   *@param color of tokens in a row searching for
   *@return all options of columns that have a certain number of red or yellow tokens next to that position
   */
  public ArrayList<Integer> xInRow(int numberInRow, String color){
    ArrayList<Integer> options = new ArrayList<Integer>();
       int row = 0;
       int column = 0;
       for(int r = 0; r <= myGame.getRowCount()-1; r++){
           row = r;
           //System.out.print("R" + row +"| ");
           for(int c = 0; c <= myGame.getColumnCount()-1; c++){
               column = c;
               //System.out.print("  " + column + "  ");
               /*
                * for (int t = 1; t<=8;t++){
                  if(t !=2){
                      //1
                      if (t==1){
                          //System.out.print("D"+t+":"+getDistanceFromBorder(-1, -1, column, row)+" ");
                          System.out.print("IR:" + numInARow(numberInRow, color, column, row, -1, -1)+" ");
                      }
                      //3
                      else if (t==3){
                          //System.out.print("D"+t+":"+getDistanceFromBorder(1, -1, column, row)+" ");
                          System.out.print("IR:" + numInARow(numberInRow, color, column, row, 1, -1)+" ");
                      }
                      //4
                      else if (t==4){
                          //System.out.print("D"+t+":"+getDistanceFromBorder(1, 0, column, row)+" ");
                          System.out.print("IR:" + numInARow(numberInRow, color, column, row, 1, 0)+" ");
                      }
                      //5
                      else if (t==5){
                          //System.out.print("D"+t+":"+getDistanceFromBorder(1, 1, column, row)+" ");
                          System.out.print("IR:" + numInARow(numberInRow, color, column, row, 1, 1)+" ");
                      }
                      else if (t==6){
                          //System.out.print("D"+t+":"+getDistanceFromBorder(0, 1, column, row)+" ");
                           System.out.print("IR:"+numInARow(numberInRow, color, column, row, 0, 1)+" ");
                      }
                      else if (t==7){
                          //System.out.print("D"+t+":"+getDistanceFromBorder(-1, 1, column, row)+" ");
                          System.out.print("IR" + numInARow(numberInRow, color, column, row, -1, 1)+" ");
                      }
                      else if (t==8){
                          //System.out.print("D"+t+":"+getDistanceFromBorder(-1, 0, column, row)+" ");
                          System.out.print("IR:" + numInARow(numberInRow, color, column, row, -1, 0)+" ");
                      }                      
                  }
               }
               System.out.print("[O:"+isOpen(column, row)+" ");
               System.out.print("S:"+hasSupport(column, row)+"] ");
               */
               for (int i = 1 ; i <= 8; i++){
                   //check top left for distance from border greater than 1
                   //adds top left to arrayList of potential moves
                   //System.out.println("OMEGA: " + TLDFB + " " + TLO + " " + TLHS);
                   if (i == 1 
                       && getDistanceFromBorder(-1, -1, column, row) >=1
                       && isOpen(column, row) == true 
                       && hasSupport(column, row) == true 
                       //numInARow(int numberOfMatches, String color, int column, int row, int columnDir, int rowDir)
                       && numInARow(numberInRow, color, column, row, -1, -1) == numberInRow){ 
                           options.add(column);
                   }
                   else if (i == 3 
                       && getDistanceFromBorder(1, -1, column, row) >= 1 
                       && isOpen(column, row) == true 
                       && hasSupport(column, row) == true
                       && numInARow(numberInRow, color, column, row, 1, -1) == numberInRow){ 
                           options.add(column);
                   }
                   else if (i == 4 
                       && getDistanceFromBorder(1, 0, column, row) >= 1 
                       && isOpen(column, row) == true 
                       && hasSupport(column, row) == true
                       && numInARow(numberInRow, color, column, row, 1, 0) == numberInRow){ 
                           options.add(column);
                   }
                   else if (i == 5
                       && getDistanceFromBorder(1, 1, column, row) >= 1 
                       && isOpen(column, row) == true 
                       && hasSupport(column, row) == true
                       && numInARow(numberInRow, color, column, row, 1, 1) == numberInRow){ 
                           options.add(column);
                   }
                   else if (i == 6 
                       && getDistanceFromBorder(0, 1, column, row) >= 1 
                       && isOpen(column, row) == true 
                       && hasSupport(column, row) == true
                       && numInARow(numberInRow, color, column, row, 0, 1) == numberInRow){ 
                           options.add(column);
                   }
                   else if (i == 7 
                       && getDistanceFromBorder(-1, 1, column, row) >= 1 
                       && isOpen(column, row) == true 
                       && hasSupport(column, row) == true
                       && numInARow(numberInRow, color, column, row, -1, 1) == numberInRow){ 
                           options.add(column);
                   }
                   else if (i == 8 
                       && getDistanceFromBorder(-1, 0, column, row) >= 1 
                       && isOpen(column, row) == true 
                       && hasSupport(column, row) == true
                       && numInARow(numberInRow, color, column, row, -1, 0) == numberInRow){ 
                           options.add(column);
                   }
               } 
            }
            //System.out.println(" ");
       }
        /*
        System.out.println(color + " has " + numberInRow + " in a row: " + options);
        System.out.println(" ");
        */
        return options;
  }

    /**
     * Returns the name of this agent.
     *
     * @return the agent's name
     */
    public String getName()
    {
        return "My Agent";
    }
    
}

   