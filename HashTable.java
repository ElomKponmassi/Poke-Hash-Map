import java.util.*;

public class HashTable {

    int tableSize;
    Pair[] arr;
    Pair gravestone = new Pair("Gravestone", "Gravestone");
    double maxLF = 0.8;

    public HashTable(int size) {
        tableSize = size;
        arr = new Pair[tableSize];
    }

    /**
     * Add a new entry.
     *
     * @param pokemon The name will be the table key.
     * @param entry   The value that will be inserted.
     * @return true if insertion was successful
     */
    public boolean mapInsert(String pokemon, String... entry) {

        if (findd(pokemon).equals("Pokemon was not found")) {

            if (Double.compare(loadFactor(), maxLF) <= 0) {

                Pair newPair = new Pair(pokemon, entry);
                arr[hashFunction(pokemon)] = newPair;
                return true;

            } else if (Double.compare(loadFactor(), maxLF) > 0) {

                System.out.println(loadFactor() + " Insertion was unsuccessful because the hash table is too full.");

            }
        }
        return false;

    }

    /**
     * Search.
     *
     * @param pokemon The name of the Pokemon
     * @return The full entry associated with this Pokemon.
     */
    public String findd(String pokemon) {
        for (int i = 0; i <= tableSize - 1; i++) {
            if (arr[i] != null) {
                if (arr[i].key.equalsIgnoreCase(pokemon)) {
                    return arr[i].key + " : " + Arrays.toString(arr[i].value);
                }
            }
        }
        return "Pokemon was not found.";
    }

    /**
     * Compute the hash function
     *
     * @param x The name of the Pokemon
     * @return The result of the hash function.
     */
    public int hashFunction(String x) {
        int a = Math.abs(x.hashCode());

        while (arr[a % tableSize] != null) {
            a += 1;
        }
        return a % tableSize;
    }

    /**
     * Remove an entry.
     *
     * @param pokemon The key for the entry to remove.
     * @return true if this entry was deleted,
     * false if the pokemon was not found.
     */
    public boolean deletee(String pokemon) {
        for (int i = 0; i <= tableSize - 1; i++) {
            if (arr[i] != null) {
                if (arr[i].key.equalsIgnoreCase(pokemon)) {
                    arr[i] = gravestone;
                    return true;
                }
            }
        }
        System.out.println(findd(pokemon));
        return false;
    }

    /**
     * Calculate the load factor.
     *
     * @return the load factor.
     */
    public double loadFactor() {
        double n = 0;
        for (int i = 0; i <= tableSize - 1; i++) {
            if (arr[i] != null) {
                n += 1;
            }
        }
        return n / tableSize;
    }

}
