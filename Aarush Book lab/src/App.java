import java.nio.file.*;
import java.io.*;
import java.util.*;


class App {
  public static void main(String[] args) {
    Book book = new Book();

    Path cwd = Paths.get("").toAbsolutePath();
    Path in = findFileCaseInsensitive(cwd, "illiad.txt", 5);
    if (in == null) in = cwd.resolve("illiad.txt");
    Path out = in.getParent().resolve("illiad_piglatin.txt");

    long totalWords = 0;

    try (BufferedReader br = Files.newBufferedReader(in);
         BufferedWriter bw = Files.newBufferedWriter(out)) {
      String line;
      while ((line = br.readLine()) != null) {
        totalWords += book.countWords(line);
        bw.write(book.translateSentence(line));
        bw.newLine();
      }
      System.out.println("Done! Total words: " + totalWords);
      System.out.println("Output: " + out.toAbsolutePath());
    } catch (IOException e) {
      System.err.println("I/O error: " + e.getMessage());
    }
  }

  private static Path findFileCaseInsensitive(Path start, String name, int depth) {
    try (var s = Files.walk(start, depth)) {
      var want = name.toLowerCase(Locale.ROOT);
      return s.filter(Files::isRegularFile)
              .filter(p -> p.getFileName().toString().toLowerCase(Locale.ROOT).equals(want))
              .findFirst().orElse(null);
    } catch (IOException e) { return null; }
  }
}
