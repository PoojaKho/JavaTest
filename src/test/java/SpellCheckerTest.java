import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class SpellCheckerTest {

    private void readDictionary(String word) {
        try {
            Optional<URL> url = Optional.ofNullable(getClass().getClassLoader().getResource("US.dic"));
            if (url.isPresent()) {
                File file = new File(url.get().getFile());
                if (file.exists()) {
                    List<String> spellingList = FileUtils.readLines(file, "UTF-8");
                    List<String> filteredList = spellingList.stream().filter(v -> v.contains(word)).collect(Collectors.toList());

                    if (filteredList.size() != 0) {
                        Optional<String> spelling1 = filteredList.stream().filter(v -> v.startsWith(word)).findFirst();

                        if (spelling1.isPresent()) {
                            if (spelling1.get().equalsIgnoreCase(word)) {
                                System.out.println("we found the word: " + word);
                            } else {
                                System.out.println("correct spelling is : " + spelling1.get());
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException | IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Test
    public void testCheckSpelling() {
        readDictionary("tes");
        readDictionary("test");
    }
}
