public class FindTheLongestSubstring {
  private class Node{
        Node[] children=new Node[26];
        boolean eow;
    }
    Node root;
    String max;
    private void insertWords(String[] words){
        for(String word:words) {
            if(root==null){
                root=new Node();
                root.eow=true;
            } 
            insertWord(word,root);
        }
    }
    private void insertWord(String w,Node node){
        Node nextNode=node.children[w.charAt(0)-'a'];
        nextNode=(nextNode==null)?new Node():nextNode;
        node.children[w.charAt(0)-'a']=nextNode;
        if(w.length()==1){
            nextNode.eow=true;
            return;
        }
        insertWord(w.substring(1),nextNode);
    }
    private void max(Node node,StringBuilder w){
       if(!node.eow) return;
       if(w!=null && w.length()>max.length()) max=w.toString();
       for(int i=0;i<26;i++) {
           if(node.children[i]==null) continue;
           char ch=(char)('a'+i);
           w.append(ch);
           max(node.children[i],w);
           w.deleteCharAt(w.length()-1);
       }
        
    }
    public String longestString(String[] words) {
       insertWords(words);
       max="";
       max(root,new StringBuilder());
       return max;
    }
}
