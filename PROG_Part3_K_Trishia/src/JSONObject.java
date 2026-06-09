import java.util.HashMap;
import java.util.Map;

/**
 * Minimal JSON object parser for reading storedMessages.json.
 * Handles simple flat objects with string values only.
 */
class JSONObject {

    private final Map<String, String> fields = new HashMap<>();

    JSONObject(String content) {
        if (content == null) return;
        String trimmed = content.trim();

        // Strip outer { }
        if (trimmed.startsWith("{")) trimmed = trimmed.substring(1);
        if (trimmed.endsWith("}")) trimmed = trimmed.substring(0, trimmed.length() - 1);

        // Parse "key": "value" pairs
        // Simple state-machine approach
        int i = 0;
        while (i < trimmed.length()) {
            // Skip whitespace and commas
            while (i < trimmed.length() && (trimmed.charAt(i) == ',' ||
                   Character.isWhitespace(trimmed.charAt(i)))) i++;
            if (i >= trimmed.length()) break;

            // Read key (quoted string)
            if (trimmed.charAt(i) != '"') { i++; continue; }
            int keyStart = i + 1;
            int keyEnd = trimmed.indexOf('"', keyStart);
            if (keyEnd < 0) break;
            String key = trimmed.substring(keyStart, keyEnd);
            i = keyEnd + 1;

            // Skip whitespace and colon
            while (i < trimmed.length() && (trimmed.charAt(i) == ':' ||
                   Character.isWhitespace(trimmed.charAt(i)))) i++;

            // Read value (quoted string)
            if (i >= trimmed.length() || trimmed.charAt(i) != '"') { i++; continue; }
            int valStart = i + 1;
            // Find closing quote, respecting escape sequences
            int valEnd = valStart;
            OUTER:
            while (valEnd < trimmed.length()) {
                switch (trimmed.charAt(valEnd)) {
                    case '\\' -> valEnd += 2; // skip escaped character
                    case '"' -> {
                        break OUTER;
                    }
                    default -> valEnd++;
                }
            }
            String value = trimmed.substring(valStart, valEnd);
            i = valEnd + 1;

            fields.put(key, value);
        }
    }

    String getString(String key) {
        String value = fields.get(key);
        if (value == null) throw new IllegalArgumentException("Key not found: " + key);
        return value;
    }
}
