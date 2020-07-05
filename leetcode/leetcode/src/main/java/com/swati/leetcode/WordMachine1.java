package com.swati.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class WordMachine {
    private static final int MAX_INT = (int) (Math.pow(2, 20) - 1);
    private static final Stack<Integer> stack = new Stack<>();

    private static void applyCommands(String s) {
        String command = null;
        try {
            push(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            command = s;
        }

        if (command == null) return;
        switch (command) {
            case "POP":
                stack.pop();
                break;
            case "DUP":
                checkNumber(1);
                stack.push(stack.peek());
                break;
            case "+":
                checkNumber(2);
                stack.push(stack.pop() + stack.pop());
                break;
            case "-":
                checkNumber(2);
                push(stack.pop() - stack.pop());
                break;
        }
    }

    public static void main(String[] args) {
        String s = "13 DUP 4 POP 5 DUP + DUP + -";
        System.out.println(solution(s));
        s = "";
        System.out.println(solution(s));
        s = null;
        System.out.println(solution(s));
        s = "3 DUP 5 - -";
        System.out.println(solution(s));
        s = "5 6 + -";
        System.out.println(solution(s));
    }

    private static int solution(String S) {
        if (S == null || S.isEmpty()) return -1;
        try {
            Arrays.stream(S.split(" ")).forEach(WordMachine::applyCommands);
            return stack.pop();
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }

    private static void checkNumber(int i) {
        if (i < 0 || i > MAX_INT) {
            throw new IllegalArgumentException("Input under/overflow");
        }
    }

    private static void push(int i) {
        checkNumber(i);
        stack.push(i);
    }

}
