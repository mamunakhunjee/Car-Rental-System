package com.carRental.main;

import java.util.Scanner;

import com.carRental.manager.MenuManager;

public class Main {
	public static void main(String args[]) {
		System.out.println("=====!	Welcome to Car Rental System	!=====\n\n\n");

		Scanner sc = new Scanner(System.in);

		MenuManager mm = new MenuManager();
		mm.menu(sc);
		sc.close();
	}
}
