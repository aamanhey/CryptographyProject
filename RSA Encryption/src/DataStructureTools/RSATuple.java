package DataStructureTools;

public class RSATuple {
    private Integer[] encode;
    private Integer[] decode;

    public RSATuple(int e, int d, int n){
        this.encode = new Integer[2];
        this.encode[0] = e;
        this.encode[1] = n;
        this.decode = new Integer[2];
        this.decode[0] = d;
        this.decode[1] = n;
    }

    public String get_string(){
        String format = String.format("Public: (e,n) = (%d, %d)\n", encode[0], encode[1]);
        format += String.format("Private: (d,n) = (%d, %d)\n", decode[0], decode[1]);
        return format;
    }

    public Integer[] getDecode() {
        return decode;
    }

    public Integer[] getEncode() {
        return encode;
    }
}
