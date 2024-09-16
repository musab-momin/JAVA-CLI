package com.taskify;

import com.taskify.enums.OperationsEnum;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Operations op = new Operations();
        OperationsEnum selectedOperation = op.run();

        Actions ac = new Actions(selectedOperation);
        ac.run();
    }
}
