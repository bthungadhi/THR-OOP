package Question1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Soal1 {

    public static List<String> processWords(String input) {
        String[] words = input.trim().split("\\s+");

        HashMap<String, String> map = new HashMap<>();
        Stack<String> stack1 = new Stack<>();

        for (int i = 0; i < words.length - 1; i += 2) {
            String key = words[i];
            String value = words[i + 1];

            map.put(key, value);
            stack1.push(key);
        }

        Stack<String> stack2 = new Stack<>();

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        List<String> result = new ArrayList<>();

        while (!stack2.isEmpty()) {
            String key = stack2.pop();
            String value = map.get(key);

            result.add(key);
            result.add(value);
        }

        return result;
    }

    public static void main(String[] args) {
        String input = "holly may interesting MARCH corey November junior january paul december";

        List<String> output = processWords(input);

        System.out.println("Input : " + input);
        System.out.println("Output: " + output);
    }
}