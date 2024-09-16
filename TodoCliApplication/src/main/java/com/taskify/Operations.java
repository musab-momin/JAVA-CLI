package com.taskify;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.taskify.Exceptions.InvalidOperationException;
import com.taskify.enums.OperationsEnum;

public class Operations {
    private final static Map<OperationsEnum, String> operations = new LinkedHashMap<>();

    static {
        operations.put(OperationsEnum.VIEW_TASK, "1: View Task list");
        operations.put(OperationsEnum.ADD_NEW_TASK, "2: Add New Task");
        operations.put(OperationsEnum.UPDATE_TASK, "3: Change Status of Task");
        operations.put(OperationsEnum.QUIT, "4: Quit");
    }

    public OperationsEnum takeInput() {

        OperationsEnum ae = null;
        Scanner sc = new Scanner(System.in);

        while (ae == null) {
            System.out.println();
            System.out.println("select action: ");
            try {
                int selectedOperationNum = sc.nextInt();

                if (selectedOperationNum <= 0 || selectedOperationNum > 4) {
                    throw new InvalidOperationException("Please select from given options");
                }

                Object[] arr = operations.keySet().toArray();
                ae = (OperationsEnum) arr[selectedOperationNum - 1];

            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Please select from given options");
                sc.next();
            }
        }
        return ae;
    }

    public void printOperations() {
        System.out.println();
        System.out.println(">>> WELCOME TO TASKIFY <<<");
        System.out.println();
        for (String option : operations.values()) {
            System.out.println(option);
        }
        System.out.println();
    }

    public OperationsEnum run() {
        printOperations();
        OperationsEnum selectedOperation = takeInput();
        return selectedOperation;
    }

}
