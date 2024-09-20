package exercise;

import java.util.stream.IntStream;

// BEGIN
public class ReversedSequence implements java.lang.CharSequence {
    private  String string;
    public ReversedSequence(String string) {
        this.string = string;
    }
    public int length() {
        return string.length();
    }

    @Override
    public String toString() {
        return null;    }

    @Override
    public IntStream chars() {
        return CharSequence.super.chars();
    }

    @Override
    public IntStream codePoints() {
        return CharSequence.super.codePoints();
    }

    public char charAt(int index) {
        return string.charAt(index);

    }

    @Override
    public boolean isEmpty() {
        return CharSequence.super.isEmpty();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

}
// END
