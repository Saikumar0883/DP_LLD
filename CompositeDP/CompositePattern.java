package CompositeDP;

import java.util.ArrayList;
import java.util.List;

interface FileSystem {
    void ls(int indent);

    void openAll(int indent);

    int getSize();

    FileSystem cd(String name);

    String getName();

    boolean isFolder();
}

class File implements FileSystem {
    private String name;
    private int size;

    public File(int size, String name) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void ls(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name);
    }

    @Override
    public void openAll(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public FileSystem cd(String name) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isFolder() {
        return false;
    }
}

class Folder implements FileSystem {
    private String name;
    private List<FileSystem> children;

    public Folder(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public void add(FileSystem item) {
        children.add(item);
    }

    @Override
    public void ls(int indent) {
        String indentSpaces = " ".repeat(indent);
        for (FileSystem item : children) {
            if (item.isFolder()) {
                System.out.println(indentSpaces + "+" + item.getName());
            } else System.out.println(indentSpaces + item.getName());
        }
    }

    @Override
    public void openAll(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name);
        for (FileSystem item : children) {
            item.openAll(indent + 4);
        }
    }

    @Override
    public int getSize() {
        int size = 0;
        for (FileSystem item : children) {
            size += item.getSize();
        }
        return size;
    }

    @Override
    public FileSystem cd(String name) {
        for (FileSystem item : children) {
            if (item.isFolder() && item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isFolder() {
        return true;
    }
}

public class CompositePattern {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.add(new File(1, "file1.txt"));
        root.add(new File(1, "file2.txt"));

        Folder docs = new Folder("docs");
        docs.add(new File(1, "docs1.pdf"));
        docs.add(new File(1, "resume.doc"));

        root.add(docs);

        Folder images = new Folder("images");
        images.add(new File(1, "image1.jpeg"));
        root.add(images);

        root.ls(0);
        FileSystem cwd = root.cd("docs");
        if (cwd != null) {
            cwd.ls(0);
        } else {
            System.out.println("\nCould not cd into docs\n");
        }
        System.out.println(root.getSize());
        root.openAll(0);
    }
}
