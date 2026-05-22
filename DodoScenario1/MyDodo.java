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
     * The Dodo walks to the front of a fence
     * checks if there's a fence ahead.
     * climbes over the fence.
     * move and checks again.
     */
    public void walkToWorldEdgeClimbingOverFences() {
        while (!borderAhead()){
        if  (fenceAhead() == true) {
        climbOverFence();
        } else{
        move();
        }
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
}
