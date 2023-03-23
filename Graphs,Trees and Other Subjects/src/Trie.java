public class Trie {
    static final int ALPHABET_SIZE = 26;
    private TrieNode root;
    static class TrieNode{
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        boolean isEndOfWord;
        TrieNode(){
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
        }
    }
    public Trie(TrieNode root){
        this.root=root;
    }
    public  void insert(String key){
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++){
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        pCrawl.isEndOfWord = true;
    }

}
