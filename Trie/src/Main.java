public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");

        System.out.println(trie.search("apple"));
        System.out.println(trie.prefixSearch(""));
        trie.printTrie();

        System.out.println("=======================");
        System.out.println(trie.longestWord());
    }
}