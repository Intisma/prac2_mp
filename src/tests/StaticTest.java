package tests;

import information.Date;

public class StaticTest {
    public static void main(String[] args) {
        Date date = new Date(1990, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
        Date date2 = new Date(1992, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
        Date dateProba = new Date(1992, (char) 1, (char) 3, (char) 1, (char) 1, (char) 1);
        if (dateProba.inRange(date2, date)) System.out.println("es la mas xuleta");
        else System.out.println("es la mas tonta");

    }
}
