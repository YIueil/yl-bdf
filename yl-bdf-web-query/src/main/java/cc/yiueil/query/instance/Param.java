package cc.yiueil.query.instance;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Param {
    private String name;

    private String defaultValue;

    private String text;

    @Override
    public String toString() {
        return text;
    }
}
