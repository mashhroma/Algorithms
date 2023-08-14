/*
 * Необходимо реализовать метод разворота связного списка (двухсвязного или односвязного на выбор).
*/

package Homework03;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        DLList myDlList = new DLList();

        for (int i = 0; i < 13; i++) {
            myDlList.addFirst(new Random().nextInt(0, 100));
        }

        myDlList.print();

        myDlList.reverse();
        
        System.out.println();
        myDlList.print();
    }
}
