package io.github.cjengineer18.linkedmap.interfaces.functional;

@FunctionalInterface
public interface IThrowableFunction<T, U> {

	U apply(T input) throws Exception;

}
