package persistence;

import org.json.JSONObject;

// Interface for model
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
