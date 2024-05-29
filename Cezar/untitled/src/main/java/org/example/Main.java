package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner inputString = new Scanner(System.in);
        String string = inputString.nextLine();
        Scanner inputKeyIn = new Scanner(System.in);
        int key = inputKeyIn.nextInt();
        Scanner inputKeyWord = new Scanner(System.in);
        String keyWord = inputKeyWord.nextLine();
        String s = "abd";
        char cha = 'c';
        //System.out.println(сaesarsСipherWithoutWord(string, key));
        System.out.println(сaesarsСipherWithWord(string, key, keyWord));
        System.out.println(secondCaesarsCipherWithWord(string, key, keyWord));
    }

    public static String сaesarsСipherWithoutWord(String string, int key){
        String outputString = new String();
        for(int i=0; i<string.length(); i++){
            int tmpKey = key;
            char c = string.charAt(i);
            if(c=='я'){
                tmpKey--;
                c='а';
            }
            else if(c=='Я'){
                c='А';
                tmpKey--;
            }
            c= (char) (c+tmpKey);
            outputString += c;
        }
        return outputString;
    }
    public static String сaesarsСipherWithWord(String string, int key, String keyWord){
        String outputString = new String();
        keyWord = keyWord.chars().distinct().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        String startAplhabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        String alphabet = new String();
        int keyWordLen = keyWord.length();
        for(int i=0; i<key;i++){
            alphabet+=startAplhabet.charAt(i+startAplhabet.length()-key);
        }
        for(int i=0; i<keyWord.length(); i++){
            char tmp = keyWord.charAt(i);
            if(alphabet.indexOf(tmp)==-1){
                alphabet+= tmp;
            }
            else keyWordLen--;
        }
        int notChangedPart = startAplhabet.length()-alphabet.length()+keyWordLen;
        for(int i=0; i<notChangedPart; i++){
            char c = startAplhabet.charAt(i);
            if(alphabet.indexOf(c)==-1){
                alphabet+=c;
            }
        }
        System.out.println(alphabet);
        for(int i=0; i<string.length();i++){
            int t = startAplhabet.indexOf(string.charAt(i));
            outputString+=alphabet.charAt(t);
        }
        return outputString;
    }
    public static String secondCaesarsCipherWithWord(String word, int key, String keyword){
        String outputString = new String();
        String startAplhabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        String alphabet = new String();
        for(int i=0; i<key; i++){
            alphabet+=startAplhabet.charAt(i+startAplhabet.length()-key);
        }
        for(int i=0; i<keyword.length(); i++){
            alphabet+=keyword.charAt(i);
        }
        for(int i=0; i<startAplhabet.length(); i++){
            alphabet+=startAplhabet.charAt(i);
        }
        alphabet = alphabet.chars().distinct().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        for(int i=0; i<word.length();i++){
            int t = startAplhabet.indexOf(word.charAt(i));
            outputString+=alphabet.charAt(t);
        }
        return outputString;
    }
}