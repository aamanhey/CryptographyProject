package Cryptography;

public class Alphabet {
    char[] letters;

    public Alphabet(){
        letters = new char[26];
        char letter = 'a';
        for (int i = 0; i < 26; i++) {
            letters[i] = (char)(letter + i);
        }
    }

    public int getLetter(char letter){
        for (int i = 0; i < 26; i++) {
            if(letters[i] == letter){
                return i;
            }
        }
        return -1;
    }
}
