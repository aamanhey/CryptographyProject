package MathOps.Representation;

public class NumberPair {
    public int value;
    public int multiplier;
    public int addition;
    public NumberPair pairAdder;

    public NumberPair(int value, int multiplier, int addition){
        this.value = value;
        this.multiplier = multiplier;
        this.addition = addition;
    }

    public NumberPair(int value, int multiplier, NumberPair pairAdder){
        this.value = value;
        this.multiplier = multiplier;
        this.pairAdder = pairAdder;
    }

    public String getPrint(){
        if(this.pairAdder != null) {
            return String.format("(%s + %d(%d))", this.pairAdder.getPrint(), this.value, this.multiplier);
        }
        return String.format("(%d + %d(%d))", this.addition, this.value, this.multiplier);
    }
}
