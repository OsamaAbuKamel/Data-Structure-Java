import java.util.Map;

public class Trie {
    // Root node of the Trie
    private TrieNode root;

    public Trie() {
        // Initialize the root node
        root = new TrieNode();
    }

    // Inserts a word into the Trie
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode node = curr.getChild().get(c);
            if (node == null) {
                node = new TrieNode();
                curr.getChild().put(c, node);
            }
            curr = node;
        }
        curr.setEndOfWord(true);
    }

    // Searches for a word in the Trie
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode node = curr.getChild().get(c);
            if (node == null) {
                return false;
            }
            curr = node;
        }
        return curr.isEndOfWord();
    }

    // Checks if a given prefix exists in the Trie
    public boolean prefixSearch(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            if (!curr.getChild().containsKey(c)) {
                return false;
            }
            curr = curr.getChild().get(c);
        }
        return true;
    }

    // Removes a word from the Trie
    public void remove(String word) {
        if (search(word) == true) {
            remove(root, word, 0);
        }
    }

    // Recursive helper function for word removal
    private boolean remove(TrieNode curr, String word, int index) {
        if (index == word.length()) {
            if (!curr.isEndOfWord()) {
                return false; // Word not found in the Trie
            }
            curr.setEndOfWord(false);
            return curr.getChild().isEmpty(); // Remove empty child nodes
        }
        char c = word.charAt(index);
        TrieNode node = curr.getChild().get(c);
        if (node == null) {
            return false; // Character not found in the Trie
        }
        boolean shouldRemove = remove(node, word, index + 1);
        if (shouldRemove) {
            curr.getChild().remove(c); // Remove the child node if it's empty
            return curr.getChild().isEmpty(); // Remove parent node if all children are empty
        }
        return false;
    }

    // Print all words in the Trie (assuming a depth-first traversal)
    public void printTrie() {
        printTrie(root, "");
    }

    private void printTrie(TrieNode curr, String currentWord) {
        if (curr.isEndOfWord()) {
            System.out.println(currentWord);
        }
        for (Map.Entry<Character, TrieNode> entry : curr.getChild().entrySet()) {
            printTrie(entry.getValue(), currentWord + entry.getKey());
        }
    }
}
