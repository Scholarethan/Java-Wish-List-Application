//Referenced from TellerApp example

package persistence;

import java.io.PrintWriter;

// Class represents the data that can be saved to file
public interface Saveable {
    //MODIFIES: printWriter
    //EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
