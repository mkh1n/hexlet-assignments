package exercise;

// BEGIN
public class LabelTag implements TagInterface{
    private String value;
    private TagInterface child;

    public LabelTag(String value, TagInterface child) {
        this.value = value;
        this.child = child;
    }
    public String render() {
        return String.format("<label>%s%s</label>", value, child.render());
    }
}
// END
