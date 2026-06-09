import java.util.ArrayList;
import java.util.List;

/**
 * Minimal JSON array parser for reading storedMessages.json.
 * Parses a JSON array of objects without external dependencies.
 */
class JSONArray {

    private final List<JSONObject> items = new ArrayList<>();

    JSONArray(String content) {
        if (content == null) return;
        String trimmed = content.trim();

        // Strip outer [ ]
        if (trimmed.startsWith("[")) trimmed = trimmed.substring(1);
        if (trimmed.endsWith("]")) trimmed = trimmed.substring(0, trimmed.length() - 1);

        // Split on object boundaries: find each { ... } block
        int depth = 0;
        int start = -1;
        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);
            if (c == '{') {
                if (depth == 0) start = i;
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0 && start != -1) {
                    String objStr = trimmed.substring(start, i + 1);
                    items.add(new JSONObject(objStr));
                    start = -1;
                }
            }
        }
    }

    int length() {
        return items.size();
    }

    JSONObject getJSONObject(int i) {
        return items.get(i);
    }
}
