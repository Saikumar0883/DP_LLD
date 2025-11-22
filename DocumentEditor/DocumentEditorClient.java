import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface DocumentElement {
    public abstract String render();
}

class TextElement implements DocumentElement {
    private String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return text;
    }
}

class ImageElement implements DocumentElement {
    private String imagePath;

    public ImageElement(String imagepath) {
        this.imagePath = imagepath;
    }

    @Override
    public String render() {
        return "[Image :" + imagePath + "]";
    }
}

class Document {
    List<DocumentElement> elements = new ArrayList<>();

    void addDocumentElement(DocumentElement ele) {
        elements.add(ele);
    }

    String render() {
        String ans = "";
        for (DocumentElement ele : elements) {
            ans += ele.render();
        }
        return ans;
    }
}

interface Persistance {
    void save(String data);
}

class SavetoFile implements Persistance {
    public void save(String data) {
        try {
            FileWriter writer = new FileWriter("document.txt");
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class DocumentEditor {
    private Document doc;
    private Persistance db;
    private String renderDoucment = "";

    public DocumentEditor(Document document, Persistance persistance) {
        this.doc = document;
        this.db = persistance;
    }

    void addText(String text) {
        doc.addDocumentElement(new TextElement(text));
    }

    void addImage(String path) {
        doc.addDocumentElement(new ImageElement(path));
    }

    String render() {
        if (renderDoucment.isEmpty())
            renderDoucment = doc.render();
        return renderDoucment;
    }

    void save() {
        db.save(renderDoucment);
    }
}

public class DocumentEditorClient {
    public static void main(String[] args) {
        Document doc = new Document();
        Persistance db = new SavetoFile();

        DocumentEditor editor = new DocumentEditor(doc, db);

        editor.addText("Hello world");
        editor.addText("This is my first Low level Design");
        editor.addImage("picture.png");
        System.out.println(editor.render());
    }
}