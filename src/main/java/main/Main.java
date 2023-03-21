package main;

import repository.QuestionRepositoryImp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want a random question?");
        while (scanner.next().equals("Yes")) {
            System.out.println(new QuestionRepositoryImp().getRandom());
            System.out.println("Do you wanna a next question?");
        }
        System.out.println("End!");
    }
}
