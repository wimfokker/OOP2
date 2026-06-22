import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.1 -- 29-07-2017
 */
public class MyDodo extends Dodo
{

    public MyDodo() {
        super( EAST );
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
     * Test if Dodo can move forward, 
     * i.e. there are no obstructions or end of world in the cell in front of her.
     * 
     * <p> Initial:   Dodo is somewhere in the world
     * <p> Final:     Same as initial situation
     * 
     * @return  boolean true if Dodo can move (thus, no obstructions ahead)
     *                  false if Dodo can't move
     *                      there is an obstruction or end of world ahead
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
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
        }
    }

    /**
     * Places all the Egg objects in the world in a list.
     * 
     * @return List of Egg objects in the world
     */
    public List<Egg> getListOfEggsInWorld() {
        return getWorld().getObjects(Egg.class);
    }

    public List<Integer> createListOfNumbers() {
        return new ArrayList<> (Arrays.asList( 2, 43, 7, -5, 12, 7 ));
    }

    /**
     * Method for praciticing with lists.
     */
    public void practiceWithLists( ){
        List<Integer> listOfNumbers = createListOfNumbers();
        
        //the following is incorrect and is to be fixed in challenge 6.1c
        System.out.println("First element: " + listOfNumbers.get(1) ); 
    }
    
    public void practiceWithListsOfSurpriseEggs( ){
        List<SurpriseEgg>  listOfEgss = SurpriseEgg.generateListOfSurpriseEggs( 12, getWorld() );
    }
    
    public void moveRandomly() {
        for(int myNrStepsTaken = 0; myNrStepsTaken < Mauritius.MAXSTEPS;) {
            myNrStepsTaken++;
            faceDirection(randomDirection());
            if (canMove()) {
                move();
            }
        }
    }
    
    /**
     * This methode will place 10 suprise eggs on the grid.
     */
    public List<SurpriseEgg> makeListOfSupriseEggs() {
        return SurpriseEgg.generateListOfSurpriseEggs( 10, getWorld());
    }
    
    /**
     * Mimi will print the coordinates of an egg in the world.
     */
    public void printCoordinatesOfEgg(Egg egg) {
        System.out.println (egg.getX() + "." + egg.getY());
    }
    
    /**
     * The Dodo will make a list of suprise eggs and print the coordinates.
     */
    public void makeListOfSupriseEggsAndprintCoordinates() {
        for (Egg egg: makeListOfSupriseEggs()) {
            printCoordinatesOfEgg(egg);
        }
    }
    
    /**
     * In this methode Mimi will place 10 eggs and calculate the average value.
     */
    public void averageEggValue() {
        List<SurpriseEgg> eggs = makeListOfSupriseEggs();
        double total = 0;
        for (SurpriseEgg egg :eggs){
            total += egg.getValue();
        }
        double average = total / eggs.size();
        System.out.println(average);
    }
    
}
