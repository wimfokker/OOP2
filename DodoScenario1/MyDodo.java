import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
        
    }
    
    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead()){
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * The Dodo can move over the fence to climb around.
     */
    public void climbOverFence() {
        turnLeft();
        move();
        turnRight();
        move();
        move();
        turnRight();
        move();
        turnLeft();
    }
    /**
     * The Dodo lays egg in the nests on the way
     * The Dodo walks to the front of a fence
     * checks if there's a fence ahead.
     * climbes over the fence.
     * move and checks again.
     */
    public void walkToWorldEdgeClimbingOverFences() {
        while (!borderAhead()){
            if (onNest() && !onEgg()) {
            layEgg();
            }
            if  (fenceAhead() == true) {
            climbOverFence();
            } else{
            move();
            }
        }
        if (onNest() && !onEgg()) {
            layEgg();
        }
    }
    
    
    /**
     * The Dodo grab the grain that is infront of where the Dodo is facing.
     * This methode has a submethode stepOneCellBackwards() for the dodo to step back.
     */
    public boolean grainAhead() {
            move();
            boolean gotGrain = onGrain();
            stepOneCellBackwards();
            return gotGrain;
    }
    
    /**
     * This methode is for the Dodo to go a step back.
     */
    public void stepOneCellBackwards() {
            turn180();
            step();
            turn180();
    }

    /**
     * This methode is were the Dodo moves to the edge of the world
     * and prints the coordinates of the positions were the grains were.
     */
    public void pickUpGrainsAndPrintCoordinates() {
        while (!borderAhead()){
        if (grainAhead()) {
            move();
            pickUpGrain();
            System.out.println("Grain at" + getX() + "," + getY());
        } else{
            move();
        }
        }
    }    
    
    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
    
    /**
     * The Dodo can walk to the egg.
     * The Dodo stops if theres a border ahead.
     */
    public void gotoEgg() {
        while (!onEgg() && !borderAhead()){
        move();
        }
    }
    
    
    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
            System.out.println(nrStepsTaken + "moved");
        }
    }
    
    
    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdge(){
        while( ! borderAhead() ){
            System.out.println("Coordinates"+ super.getX() +"," + super.getY());// print coordinates
            move();   
        }
    }
    
    /**
     * The Dodo turns around and walks to the edge of the world and then turns around.
     */
    public void goBackToStartOfRowAndFaceBack(){
        turn180();
        walkToWorldEdge();
        turn180();
    }
    
    /**
     * The Eggs are counted in a row, and the Dodo returns to the start.
     * showCompliment prints how many eggs are counted.
     */
    public int countEggsInRow() {
        int eggCount = 0;
        while (!borderAhead()) {
            if (onEgg()) {
                eggCount++;
            }
            move();
        }
        if (onEgg()) {
            eggCount++;
        }
        goBackToStartOfRowAndFaceBack();
        showCompliment("Eieren:" + eggCount);
        return eggCount;
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
        if( onEgg() ){
            return false;
        }else{
            return true;
        }
    }  
    
    /**
     * The Dodo turns around 180 degrees.
     */
    public void turn180() {
        turnRight();
        turnRight();
    }
    
    /**
     * This methode makes the Dodo lay egg on the nests all to the row.
     * The last if is for the dodo to lay an egg on the nest on the last cell of the grid.
     */
    public void layEggOnEmptyNests() {
        while( ! borderAhead() ) {
            if ( onNest() && !onEgg()) {
            layEgg();
            }
            move();
        }
        if ( onNest() && !onEgg()) {
        layEgg();
        }
    }
    
    /**
     * This methode makes the Dodo go around a fenced area to get to a egg.
     * With the dodo stopping by the egg.
     */
    public void walkAroundFencedArea() {
        while (!onEgg()) {
            turnRight();
            while(fenceAhead()){
                turnLeft();
            }
            move();
        }
    }
    
    /**
     * The Dodo can follow with this methode a trail of eggs to the nest.
     */
    public void eggTrailToNest() {
        while (!onNest()) {
             if (eggAhead() || nestAhead() == true) {
            move();
            } else {
            turnRight();
            if (!eggAhead()) {         
            turn180(); }
            } 
        }
    }
    
    /**
     * This Methode makes the Dodo go through the route of a maze to the nest.
     */
    public void goFindNestInMaze() {
        while (!onNest()) {
            turnRight();
            while(fenceAhead()) {
                turnLeft();
            }
            move();
        }
    }
    
    /**
     * This methode makes the eggs changes values were the temporary egg
     * gets the value of the blue egg and the blue egg gets the value
     * of the golden egg,
     * while the golden egg gets the value of the temporary egg.
     */
    public void changeEggValue() {
        BlueEgg blueEgg = new BlueEgg(); // value 1
        GoldenEgg goldenEgg = new GoldenEgg(); //value 5
        
        int temporaryValueEgg = blueEgg.getValue(); // value 1
        
        blueEgg.setValue(goldenEgg.getValue()); // value 1
        goldenEgg.setValue(temporaryValueEgg);
        
        System.out.println(goldenEgg.getValue());
        System.out.println(blueEgg.getValue());
    }
    
    /**
     * This methode is for the Dodo that he can change direction of face.
     * Para direction is what the Dodo would be facing.
     */
    public void faceDirection(int directions) {
        if (directions >= 0 && directions <= 3) {
        while (getDirection() != directions){
            turnLeft();
        }
    }
    }
    
    /**
     * This methode can make the Dodo follow the cordinates in the grid.
     */
    public void goToLocation(int coordX, int coordY) {
        int moveX = coordX - getX();
        int moveY = coordY - getY();
        if (moveX > 0) {
            moveX = moveX +-1;
            setDirection(0);
            jump(moveX);
        } else {
            setDirection(0);
            turn180();
            jump(moveX);
        }
        if (moveY > 0) {
            moveY = moveY +-1;
            setDirection(0);
            jump(moveY);
        } else {
            setDirection(0);
            turn180();
            jump(moveY);
        }
    }
    
    /**
     * This methode checks if the coordinates are within or outside the grid.
     * It shows an error message if the coordinates is out of bounds.
     */
    public boolean validCoordinates(int x, int y) {
        if (x >= 0 && x < getWorld().getWidth() && y >= 0 && y < getWorld().getHeight()) {
            return true;
        } else {
            showError("Invalid coordinates");
            return false;
        }
    }
    
    /**
     * The Dodo wil walk while laying eggs and stop by the last egg.
     */
    public void layTrailOfEggs(int n) {
        int layEggTrail = 0;
        while (layEggTrail < n && !borderAhead()) {
            move();
            layEgg();
            layEggTrail++;
        }
    }
}