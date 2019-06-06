import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import static org.junit.Assert.assertTrue;

public class SpellCheckerTest {

    private List<String> readDictionary(String word) {
        List<String> spellCheck = new ArrayList<>();
        try {
            Optional<URL> url = Optional.ofNullable(getClass().getClassLoader().getResource("US.dic"));
            if (url.isPresent()) {
                File file = new File(url.get().getFile());
                if (file.exists()) {
                    List<String> spellingList = FileUtils.readLines(file, "UTF-8");
                    List<String> filteredList = spellingList.stream().filter(v -> v.contains(word)).collect(Collectors.toList());
                    if (filteredList.size() != 0) {
                        List<String> newFilteredListMatchedWords = filteredList.stream().filter(v -> v.equalsIgnoreCase(word)).collect(Collectors.toList());
                        if (newFilteredListMatchedWords.size() > 0) {
                            System.out.println("word exist in the dictionary: " + word);
                            spellCheck.add(word);
                        } else {
                            System.out.println("Do you mean any of the following words?");
                            System.out.println(filteredList.stream().collect(Collectors.joining(", ")));
                            spellCheck.addAll(filteredList.stream().collect(Collectors.toList()));
                        }
                    }
                }
            }

        } catch (NullPointerException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        return spellCheck;
    }

    @Test
    public void testCorrectCheckSpellingAndSuggestOptions() {
        assertTrue(readDictionary("tes").size() > 0);
    }

    @Test
    public void testCheckIfWordExistsInDictionary() {
        List<String> result=readDictionary("test");
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).equalsIgnoreCase("test"));
    }
}
