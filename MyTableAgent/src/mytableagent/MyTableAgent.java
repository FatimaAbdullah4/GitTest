/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytableagent;

import java.util.Scanner;
import java.util.ArrayList;

public class MyTableAgent {

    private static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;

    private static int[][] directionTable;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rows, cols, dirt;
        ArrayList<Integer> rDirt = new ArrayList<Integer>();
        ArrayList<Integer> cDirt = new ArrayList<Integer>();

        System.out.println("Enter the number of Rows: ");
        rows = input.nextInt();
        System.out.println("Enter the number of Columns: ");
        cols = input.nextInt();
        System.out.println("Enter the number of Dirty cells: ");
        dirt = input.nextInt();

        Environment env = new Environment(rows, cols, dirt);

        setDirectionTable(rows, cols);

        //Scan for dirt
        //Scan for dirt
        for (int i = 0; i < rows; i++) {
            if (i != 0) {
                env.moveDown();
            }
            for (int j = 0; j < cols; j++) {
                boolean x = env.isDirty();
                if (x == true) {
                    rDirt.add(env.getRow());
                    cDirt.add(env.getColumn());
                }

                if (j != (cols - 1)) {

                    if (i == 0 || i % 2 == 0) {
                        env.moveRight();
                    } else {
                        env.moveLeft();
                    }
                }
            }
        }

        env.reset();
        System.out.println("\nEnvironment before cleaning:");
        System.out.println(env.toString());

        System.out.println("\nLocations of Dirt:");
        for (int i = 0; i < rDirt.size(); i++) {
            System.out.println("(" + rDirt.get(i) + "," + cDirt.get(i) + ")");
        }

        //Clean dirt
        int totalDirty = rDirt.size();
        while (totalDirty > 0) {
            if (env.isDirty()) {
                env.suck();
                totalDirty--;
                System.out.println("\nClean:");
                System.out.println(env.toString());
                if (totalDirty == 0) {
                    break;
                }
            }
            switch (directionTable[env.getRow()][env.getColumn()]) {
                case UP:
                    env.moveUp();
                    System.out.println("\nMove Up:");
                    System.out.println(env.toString());
                    break;
                case DOWN:
                    env.moveDown();
                    System.out.println("\nMove Down:");
                    System.out.println(env.toString());
                    break;
                case LEFT:
                    env.moveLeft();
                    System.out.println("\nMove Left:");
                    System.out.println(env.toString());
                    break;
                case RIGHT:
                    env.moveRight();
                    System.out.println("\nMove Right:");
                    System.out.println(env.toString());
                    break;
            }
        }

        System.out.println("\nEnvironment after cleaning:");
        System.out.println(env.toString());
        System.out.println("Score: " + env.getScore());
    }

    private static void setDirectionTable(int rows, int cols) {
        directionTable = new int[rows][cols];

        //Scan for dirt
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j != (cols - 1)) {
                    if (i == 0 || i % 2 == 0) {
                        directionTable[i][j] = RIGHT;
                    } else {
                        directionTable[i][j] = LEFT;
                    }
                }
                if (j == 0) {
                    if (i % 2 == 1) {
                        directionTable[i][j] = DOWN;
                    } else {
                        directionTable[i][j] = RIGHT;
                    }
                }
                if (j == cols - 1) {
                    if (i % 2 == 1) {
                        directionTable[i][j] = LEFT;
                    } else {
                        directionTable[i][j] = DOWN;
                    }
                }
            }
        }
    }
}
