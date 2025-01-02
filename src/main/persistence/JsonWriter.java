package persistence;

import model.Event;
import model.EventLog;
import model.League;
import org.json.JSONObject;


import java.io.*;


// PrintWriter is a writer that will convert my league into JSON object
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // writer will write to given named destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: instantiates writer with a file of destination name, throws
    //          FileNotFoundException if there is problem with destination File
    public void openWriter() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: saves League to JSON object stored in a file
    public void write(League myLeague) {
        JSONObject leagueJson = myLeague.toJson();
        saveToFile(leagueJson.toString(TAB));
        EventLog.getInstance().logEvent(new Event("League Saved to File"));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void closeWriter() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes JSON Object as a string to the file
    public void saveToFile(String leagueJson) {
        writer.print(leagueJson);
    }
}
