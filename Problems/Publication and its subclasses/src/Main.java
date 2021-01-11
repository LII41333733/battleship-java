
class Publication {

    protected String title;

    public Publication(String title) {
        this.title = title;
    }

    public final String getInfo() {
        // write your code here
        return String.format("%s%s%s", this.getType(), this.getDetails(), this.title);
    }

    protected String getType() {
        return "Publication";
    }

    protected String getDetails() {
        return ": ";
    }

}

class Newspaper extends Publication {

    private String source;

    public Newspaper(String title, String source) {
        super(title);
        this.source = source;
    }

    // write your code here
    protected String getType() {
        return "Newspaper";
    }

    protected String getDetails() {
        return String.format(" (source - %s): ", this.source);
    }

}

class Article extends Publication {

    private String author;

    public Article(String title, String author) {
        super(title);
        this.author = author;
    }

    // write your code here
    protected String getType() {
        return "Article";
    }

    protected String getDetails() {
        return String.format(" (author - %s): ", this.author);
    }

}

class Announcement extends Publication {

    private int daysToExpire;

    public Announcement(String title, int daysToExpire) {
        super(title);
        this.daysToExpire = daysToExpire;
    }

    // write your code here
    protected String getType() {
        return "Announcement";
    }

    protected String getDetails() {
        return String.format(" (days to expire - %d): ", this.daysToExpire);
    }

}