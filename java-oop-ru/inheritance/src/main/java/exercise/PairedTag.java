package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        String attributesString = attributes.entrySet().stream()
                .map(entry -> String.format("%s=\"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(" "));

        String childrenString = children.stream()
                .map(Tag::toString)
                .collect(Collectors.joining(""));

        return String.format("<%s%s>%s%s</%s>", name, attributesString.isEmpty() ? "" : " " + attributesString, body, childrenString, name);
    }
}