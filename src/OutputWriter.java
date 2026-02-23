import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputWriter {
    private final String outputFile;  // final benefits us safe code and final is constant

    public OutputWriter(String outputFile) {
        this.outputFile = outputFile;
    }

    public void writeOutput(List<String> outputLines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i = 0; i < outputLines.size(); i++) {
                bw.write(outputLines.get(i));
                if (i != outputLines.size() - 1) {
                    bw.newLine(); // insertion only after the last line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}