import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class EdKpHash implements Pokedex {

    HashTable ht = new HashTable(1039);

    public static void main(String[] args) {
        EdKpHash n = new EdKpHash();

        n.readAndPut();
        n.run();

    }

    /**
     * Read input file and add pokemon k,v objects to the Hash Table.
     */
    public void readAndPut() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("pokemon_pokedex_alt.csv"));
            br.readLine(); // Read (skip) first line
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                ht.mapInsert(values[5], values);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read user input and run program accordingly.
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome, Please enter a command:");
        String userInput = scan.nextLine();

        if (userInput.toLowerCase().contains("find")) {
            String[] a = userInput.split(" +");
            System.out.println(find(a[1]));
            run();
        } else if (userInput.toLowerCase().contains("add")) {
            String[] a = userInput.split(" +");
            String[] subarray = Arrays.copyOfRange(a, 2, a.length);
            add(a[1], Arrays.toString(subarray));
            run();
        } else if (userInput.toLowerCase().contains("delete")) {
            String[] a = userInput.split(" +");
            delete(a[1]);
            run();
        } else if (userInput.toLowerCase().contains("printht")) {
            printHT();
            run();
        } else if (userInput.toLowerCase().contains("who")) {
            who();
            run();
        } else if (userInput.toLowerCase().contains("help")) {
            help();
            run();
        } else if (userInput.toLowerCase().contains("exit")) {
            exit();
        } else {
            System.out.println("Please enter a valid command");
            run();
        }
    }

    @Override
    public String find(String pokemon) {
        return ht.findd(pokemon);
    }

    @Override
    public boolean add(String pokemon, String entry) {
        return ht.mapInsert(pokemon, entry);
    }

    @Override
    public boolean delete(String pokemon) {
        return ht.deletee(pokemon);
    }

    @Override
    public void printHT() {
        for (int i = 0; i <= ht.tableSize - 1; i++) {
            if (ht.arr[i] != null) {
                System.out.println(ht.arr[i].key + " : " + Arrays.toString(ht.arr[i].value));
            }
        }
    }

    @Override
    public double getLoadFactor() {
        return ht.loadFactor();
    }

    @Override
    public double getMaxLoadFactor() {
        return ht.maxLF;
    }

    @Override
    public int count() {
        return (int) (ht.loadFactor() * ht.tableSize);
    }

    @Override
    public void who() {
        System.out.println("Elom Kponmassi");
    }

    @Override
    public void help() {
        System.out.println("add – insert an entry into the table\n" +
                "delete – remove an entry\n" +
                "find – If the user enters a pokemon name, print out its entry\n" +
                "printHT – Print the entire table\n" +
                "who – Print author's name\n" +
                "help – Print these commands\n" +
                "exit – Quit the program");
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
