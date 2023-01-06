package cc.yiueil.query.instance;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filter {
    private String name;

    private String left;

    private String right;

    private String text;

    @Override
    public String toString() {
        return text;
    }
}
