package dataGenerator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import model.ItemData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class StudentEntityGenerator {

    @Parameter(names = "-ic", description = "Item count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {
        StudentEntityGenerator generator = new StudentEntityGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ItemData> items = generateItems(count);
        if (format.equals("csv")) {
            saveAsCsv(items, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(items, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(items, new File(file));
        } else {
            System.out.println("Please check format for save file");
        }
    }

    private void saveAsJson(List<ItemData> items, File file) throws IOException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(items);
        try (Writer writer = new FileWriter(file);) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ItemData> items, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ItemData.class);
        String xml = xstream.toXML(items);
        try (Writer writer = new FileWriter(file);) {
            writer.write(xml);
        }

    }

    private void saveAsCsv(List<ItemData> items, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file);) {
            for (ItemData item : items) {
                writer.write(String.format("%s\n", item.getName()));
            }
        }
    }

    private List<ItemData> generateItems(int count) {
        List<ItemData> items = new ArrayList<ItemData>();
        for (int i = 0; i < count; i++) {
            items.add(new ItemData()
                    .withName(String.format("generated_Item %s", i)));
        }
        return items;
    }
}
