/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymodelbasedagent;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class MyModelBasedAgent {

    private static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;

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

        Random rand = new Random();
        //Clean dirt
        int totalDirty = rDirt.size();
        boolean hitWall = false;
        while (totalDirty > 0) {
            while (!hitWall) {
                if (env.isDirty()) {
                    env.suck();
                    totalDirty--;
                    System.out.println("\nClean:");
                    System.out.println(env.toString());
                    if (totalDirty == 0) {
                        break;
                    }
                }
                int success = 2;
                if (env.getRow() % 2 == 0) {
                    success = env.moveRight();
                    System.out.println("\nMove Right:");
                    System.out.println(env.toString());
                } else {
                    success = env.moveLeft();
                    System.out.println("\nMove Left:");
                    System.out.println(env.toString());
                }

                if (success == 1) {
                    hitWall = true;
                }
            }
            if (env.isDirty()) {
                env.suck();
                totalDirty--;
                System.out.println("\nClean:");
                System.out.println(env.toString());
            }
            if (totalDirty == 0) {
                break;
            }
            env.moveDown();
            System.out.println("\nMove Down:");
            System.out.println(env.toString());
            hitWall = false;

        }

        System.out.println(
                "\nEnvironment after cleaning:");
        System.out.println(env.toString());
        System.out.println(
                "Score: " + env.getScore());
    }

}
