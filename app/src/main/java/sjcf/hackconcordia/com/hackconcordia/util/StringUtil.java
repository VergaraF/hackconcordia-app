package sjcf.hackconcordia.com.hackconcordia.util;


import java.util.List;

public class StringUtil {
    public static String toCommaString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i));
            if (i != list.size() - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

}
