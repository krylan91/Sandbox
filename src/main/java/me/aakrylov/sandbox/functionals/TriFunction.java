package me.aakrylov.sandbox.functionals;

public interface TriFunction<F, S, T, R> {

    R apply(F firstArgument, S secondArgument, T thirdArgument);
}
