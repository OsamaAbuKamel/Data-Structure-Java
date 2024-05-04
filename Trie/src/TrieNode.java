import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    // Map to store child nodes for each character
    private Map<Character, TrieNode> child;
    // Flag to indicate if the node represents the end of a word
    private boolean isEndOfWord;

    public TrieNode() {
        // Initialize the child map
        child = new HashMap<>();
        // Set isEndOfWord to false by default
        isEndOfWord = false;
    }

    // Getter for child map
    public Map<Character, TrieNode> getChild() {
        return child;
    }

    // Setter for child map
    public void setChild(Map<Character, TrieNode> child) {
        this.child = child;
    }

    // Getter for isEndOfWord flag
    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    // Setter for isEndOfWord flag
    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }
}