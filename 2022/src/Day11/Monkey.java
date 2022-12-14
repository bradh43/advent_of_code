package src.Day11;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.math.BigInteger;

public class Monkey {

    List<BigInteger> items;
    Function<BigInteger, BigInteger> operation;
    int worryTest;
    int trueMonkey;
    int falseMonkey;
    BigInteger inspectionCount;
    Function<BigInteger, BigInteger> coolWorryLevel;

    public Monkey(List<BigInteger> items, Function<BigInteger, BigInteger> operation, int worryTest, int trueMonkey, int falseMonkey) {
        super();
        this.items = items;
        this.operation = operation;
        this.worryTest = worryTest;
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
        this.inspectionCount = BigInteger.ZERO;
    }

    
    public BigInteger inspectItem(BigInteger item) {
        this.inspectionCount = this.inspectionCount.add(BigInteger.ONE);
        BigInteger itemToPass = this.operation.apply(item);
        BigInteger newItem = this.coolWorryLevel.apply(itemToPass);
        return newItem;
    }

    
    public void setCoolWorryLevel(Function<BigInteger, BigInteger> coolWorryLevel) {
            this.coolWorryLevel = coolWorryLevel;
    }

    public int throwItem(BigInteger item) {
        if (item.mod(BigInteger.valueOf(this.worryTest)).equals(BigInteger.ZERO)) {
            return this.trueMonkey;
        }
        return this.falseMonkey;
    }

    public void catchItem(BigInteger item) {
        this.items.add(item);
    }

    public void clearItems() {
        this.items.clear();
    }

    public Monkey clone() {
        List<BigInteger> copyItems = new LinkedList<>();
        for (BigInteger item : this.items) {
            copyItems.add(item);

        }
        return new Monkey(copyItems, this.operation, this.worryTest, trueMonkey, falseMonkey);
    }
}
