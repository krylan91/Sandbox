package me.aakrylov.sandbox.components;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomPair<F, S> {

    private F first;
    private S second;

    private CustomPair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public static <F, S> CustomPair<F, S> of(F first, S second) {
        return new CustomPair<>(first, second);
    }

    @Override
    public boolean equals(Object comparable) {
        if (this == comparable) {
            return true;
        }
        if (comparable instanceof CustomPair<?,?> pair) {
            return this.first.equals(pair.getFirst()) &&
                    this.second.equals(pair.getSecond());
        }
        return false;
    }
}
