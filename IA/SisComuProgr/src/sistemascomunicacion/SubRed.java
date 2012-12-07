/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemascomunicacion;

/**
 *
 * @author rC
 */
class SubRed {

    NetType type;
    String subredName;
    String desde;
    String hasta;
    String broadcast;

    public SubRed(NetType type, String subredName, String desde, String hasta, String broadcast) {
        this.type = type;
        this.subredName = subredName;
        this.desde = desde;
        this.hasta = hasta;
        this.broadcast = broadcast;
    }

    @Override
    public String toString() {
        return subredName + " \t " + desde + " \t " + " \t " + hasta + " \t " + broadcast;
    }
}
