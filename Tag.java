public class Tag implements Comparable<Tag>{
    private String tag;
    private Integer count;

    public Tag(String tag) {
        this.tag = tag;
        this.count = 1;
    }

    public String getTag() {
        return tag;
    }

    public void increment() {
        count++;
    }
    public Integer getCount() {
        return count;
    }

    public int compareTo(Tag other) {
        return this.count.compareTo(other.count);
    }

    public String toString() {
        return "(" + count + ", " + tag + ")";
    }
}
