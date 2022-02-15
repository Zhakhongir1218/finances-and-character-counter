package character_counter;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Character, Integer> alphabet = new HashMap<>();
        char a = 'a';
        char z = 'z';

        while (a <= z) {
            System.out.println(a);
            alphabet.put(a,0);
            a++;

        }
        System.out.println(alphabet.keySet());

        String text = "Dear\n" +
                "Harry, Ron wrote to me and told me about his phone call to your Uncle \n" +
                "Vernon. I do hope you’re all right. I’m on holiday in France at the \n" +
                "moment and I didn’t know how I was going to send this to you —what if \n" +
                "they’d opened it at customs? —but then Hedwig turned up! I think she \n" +
                "wanted to make sure you got something for your birthday for a change. I \n" +
                "bought your present by owl-order; there wasan advertisement in the Daily\n" +
                "Prophet (I’ve been getting it delivered; it’s so good to keep up with \n" +
                "what’s going on in the wizarding world). Did you see that picture of Ron\n" +
                "and his family a week ago? I bet he’s learning loads. I’m really \n" +
                "jealous —the ancient Egyptian wizards were fascinating. There’s some \n" +
                "interesting local history of witchcraft here, too. I’ve rewritten my \n" +
                "whole History of Magic essay to include some of the things I’ve found \n" +
                "out, I hope it’s not too long —it’s two rolls of parchment more than \n" +
                "Professor Binns asked for. Ron says he’s going to be in London in the \n" +
                "last week of the holidays. Can you make it? Will your aunt and uncle let\n" +
                "you come? I really hope you can. If not, I’ll see you on the Hogwarts \n" +
                "Express on September first! Love from Hermione P.S. Ron says Percy’s \n" +
                "Head Boy. I’ll bet Percy’s really pleased. Ron doesn’t seem too happy \n" +
                "about it ";

        String toLow = text.toLowerCase();
        char[] textChar = toLow.toCharArray();


        for(int i = 0; i < textChar.length; i++){
            if(alphabet.containsKey(textChar[i])){
               int value = alphabet.get(textChar[i]);
               alphabet.put(textChar[i],value+1);
            }
        }

        System.out.println(alphabet);
    }
}
