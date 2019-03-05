/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myreflexagent;

public interface EnvironmentInterface {

    public int moveLeft();

    public int moveRight();

    public int moveUp();

    public int moveDown();

    public void suck();          // clean current cell

    public boolean isDirty();    // is current cell dirty?

    public int getScore();       // returns penalty score
}
