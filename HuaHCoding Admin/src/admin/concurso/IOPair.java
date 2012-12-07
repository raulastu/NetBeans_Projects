package admin.concurso;

public class IOPair {

    private int number;
    private byte[] input;
    private byte[] output;

    public IOPair(int number, byte[] input, byte[] output) {
        this.number = number;
        this.input = input;
        this.output = output;
    }

    public int getNumber() {
        return number;
    }

    public byte[] getInput() {
        return input;
    }

    public byte[] getOutput() {
        return output;
    }
}
