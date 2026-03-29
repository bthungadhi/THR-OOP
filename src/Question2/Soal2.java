package Question2;

import java.util.*;

abstract class Stand {
    protected String owner;

    public Stand(String owner) {
        this.owner = owner;
    }

    public abstract boolean expose(String name);
}

class CrazyDiamond extends Stand {
    public CrazyDiamond(String owner) {
        super(owner);
    }

    @Override
    public boolean expose(String name) {
        return false;
    }
}

class StarPlatinum extends Stand {
    public StarPlatinum(String owner) {
        super(owner);
    }

    @Override
    public boolean expose(String name) {
        return name.length() <= 3;
    }
}

class TheHand extends Stand {
    public TheHand(String owner) {
        super(owner);
    }

    @Override
    public boolean expose(String name) {
        String lower = name.toLowerCase();
        for (int i = 0; i < lower.length() - 1; i++) {
            if (lower.charAt(i) == lower.charAt(i + 1)) return true;
        }
        return false;
    }
}

class Echoes extends Stand {
    public Echoes(String owner) {
        super(owner);
    }

    @Override
    public boolean expose(String name) {
        long vowels = name.toLowerCase().chars()
                .filter(c -> "aeiou".indexOf(c) >= 0)
                .count();
        return vowels >= 3;
    }
}

class HeavensDoor extends Stand {
    public HeavensDoor(String owner) {
        super(owner);
    }

    @Override
    public boolean expose(String name) {
        String lower = name.toLowerCase();
        String reversed = new StringBuilder(lower).reverse().toString();
        return lower.equals(reversed);
    }
}

class SheerHeartAttack extends Stand {
    public SheerHeartAttack(String owner) {
        super(owner);
    }

    @Override
    public boolean expose(String name) {
        return name.toLowerCase().startsWith("k");
    }
}

public class Soal2 {

    public static void investigate(String characterName, Stand stand, String inputLine) {
        String[] parts = inputLine.trim().split("\\s+");

        Queue<String> suspects = new LinkedList<>();
        for (String name : parts) {
            suspects.add(name.toLowerCase());
        }
        suspects.add("kira");

        Stack<String> arrested = new Stack<>();

        while (!suspects.isEmpty()) {
            String name = suspects.poll();
            if (stand.expose(name)) {
                arrested.push(name);
            }
        }

        if (arrested.isEmpty()) {
            System.out.println(characterName + " exposed no one. Arrested: []");
        } else {
            List<String> result = new ArrayList<>();
            while (!arrested.isEmpty()) {
                result.add(0, arrested.pop());
            }
            System.out.println(characterName + " exposed someone! Arrested: " + result);
        }
    }

    public static void main(String[] args) {
        Stand josuke  = new SheerHeartAttack("Josuke");
        Stand jotaro  = new StarPlatinum("Jotaro");
        Stand okuyasu = new TheHand("Okuyasu");
        Stand koichi  = new Echoes("Koichi");
        Stand rohan   = new HeavensDoor("Rohan");

        String input1 = "john paul mary ian leo samuel";
        String input2 = "john paul mary ian leo samuel Ian roy aaron";

        System.out.println("=== Test Cases ===");
        investigate("Josuke",  josuke,  input1);
        investigate("Jotaro",  jotaro,  input1);
        investigate("Okuyasu", okuyasu, input2);
        investigate("Koichi",  koichi,  input2);
        investigate("Rohan",   rohan,   input2);
    }
}