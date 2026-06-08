//written by teagan donnelly

import java.util.Scanner; 

// importing java.lang package
import java.lang.Math;

public class hw00_spring {
    public static void main(String[] args) {
        System.err.println("Welcome to the Vector Operations Program!");
        Scanner keyboard = new Scanner(System.in); //creates keyboard for uses to input values 
        int choice = 0;
        choices(choice,keyboard);

        
        keyboard.close(); //closes keyboard
    }

    public static void choices(int choice, Scanner keyboard){
        System.out.println("Enter 1. To Add 2 Vectors \n Enter 2. To Subtract 2 Vectors \n Enter 3. To Find the Magnitude of a Vector \n Enter 9. To Quit");
        choice = keyboard.nextInt();

        while(choice != 9){
            if(choice == 1){
                vectorAddition(choice, keyboard);
            } else if(choice == 2){
                vectorSubtraction(choice, keyboard);
            } else if(choice == 3){
                magnitude(choice, keyboard);
            } else{
                System.out.println("Invalid number inputted, please try again.");
            }
        }

        System.out.println("Thank you for using the Vector Operations Program! Bye now");
    }


    public static double[] createVector(Scanner keyboard, int size){

        double[] vector = new double[size];

        for(int i = 0; i < size; i++){
            System.out.println("Please add value number " + (i+1) + " of your vector");
            vector[i] = keyboard.nextDouble();
        }

        return vector;

    }    

    public static void vectorAddition(int choice, Scanner keyboard){
        System.out.println("Enter the size of the Vectors you would like to add");
        int size = keyboard.nextInt(); //makes a varible to hold the size the user chose

        double[] vector1 = createVector(keyboard, size); //makes the first vector
        double[] vector2 = createVector(keyboard, size); //makes the second vector

        //makes a new vector that is the addition of both
        double[] newVector = new double[size];
        for(int i = 0; i < size; i++){
            newVector[i] = vector1[i] + vector2[i];
        }

        //prints out the vector and the math equation
        printVectors(vector1, size);
        System.out.println("+");
        printVectors(vector2, size);
        System.out.println("=");
        printVectors(newVector, size);

        choice = 0;
        choices(choice,keyboard);

    }

    public static void vectorSubtraction(int choice, Scanner keyboard){
        System.out.println("Enter the size of the Vectors you would like to subtract");
        int size = keyboard.nextInt(); //makes a varible to hold the size the user chose

        double[] vector1 = createVector(keyboard, size); //makes the first vector
        double[] vector2 = createVector(keyboard, size); //makes the second vector

        //makes a new vector that is the subtraction of both
        double[] newVector = new double[size];
        for(int i = 0; i < size; i++){
            newVector[i] = vector1[i] - vector2[i];
        }

        //prints out the vector and the math equation
        printVectors(vector1, size);
        System.out.println("-");
        printVectors(vector2, size);
        System.out.println("=");
        printVectors(newVector, size);

        choice = 0;
        choices(choice,keyboard);
    }

    public static void magnitude(int choice, Scanner keyboard){
        System.out.println("Enter the size of the Vector");
        int size = keyboard.nextInt(); //makes a varible to hold the size the user chose
        if(!validate(size)){
            System.out.println("Invalid size");
        }

        double[] vector = createVector(keyboard, size); //creates a vector the size that the user inputted

        double val = 0.0; //creates a varible to hold the addtion of all the squares in the vector
        for(int i = 0; i < size; i++){
            val += (Math.pow(vector[i], 2));
        }

        val = (Math.pow(val, .5));

        System.out.println("The magnitude of the vector is " + val);

        choice = 0;
        choices(choice,keyboard);
    }

    public static void printVectors(double[] vector, int size){
        for(int i = 0; i < size; i++){
            System.out.println(vector[i]);
        }
    }

    public static boolean validate(int size){
        boolean valid = true;
        if(size <= 0){
            valid = false;
        }

        return valid;
    }
}
