package vstudionetutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rcuser
 */
public class RCGen {

    Connection conn;
    String databaseName;
    String[] tables;
    String tablePrefix;
    String filePath;
    String fileName;

    public RCGen(Connection conn, String databaseName, String[] tables, String tablePrefix, String path, String fileName) {
        this.conn = conn;
        this.databaseName = databaseName;
        this.tables = tables;
        this.tablePrefix = tablePrefix;

        //testing if path end correctly, if not fixing it
        if (path.length() > 0 && (path.charAt(path.length() - 1) == '/' || path.charAt(path.length() - 1) == '\\')) {
            this.filePath = path;
        } else {
            this.filePath = path + "/";
        }
        this.fileName = fileName;


        /* Setting the database*/
        try {
            conn.createStatement().execute("USE " + databaseName);
        } catch (SQLException ex) {
            Logger.getLogger(GenMNT_Procedures.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
