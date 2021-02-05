import java.util.*;

public class VignereEncoding {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите символы ключа: ");
        String key = in.nextLine().toLowerCase();
        System.out.print("Введите текст для шифрования: ");
        String[] sentence = in.nextLine().toLowerCase().split(" ");
        StringBuilder text = new StringBuilder();
        for (String words:sentence) {
            text.append(words);
        }
        String strText = text.toString();

        char[] RussianAlph = new char[] {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н',
                'о', 'п', 'р','с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы','ь', 'э', 'ю', 'я'};

        char[][] VignereMatrix = new char[RussianAlph.length][RussianAlph.length];
        for (int i = 0, k; i < VignereMatrix.length; i++) { k = i;
            for (int j = 0; j < VignereMatrix[i].length; j++) {
                VignereMatrix[i][j] = RussianAlph[k]; k++;
                if (k == RussianAlph.length) { k = 0;}
            }
        }

        String preparedKey = key.repeat(strText.length() / key.length() + 1)
                .substring(0, strText.length());

        List<String> codedText = new ArrayList<>();
        for (int i = 0, j = 0; i < sentence.length; i++) { char[] word = sentence[i].toCharArray();
            char[] partKey = preparedKey.substring(j, j + word.length).toCharArray();

            StringBuilder encodedWord = new StringBuilder();
            for (int k = 0; k < word.length; k++) {
                encodedWord.append(VignereMatrix[Arrays.binarySearch(RussianAlph, partKey[k])]
                        [Arrays.binarySearch(RussianAlph, word[k])]); j++;
            }
            codedText.add(encodedWord.toString());
        }

        System.out.print("Шифрованый текст: ");
        for (String word:codedText) { System.out.print(word + " "); }
    }
}
