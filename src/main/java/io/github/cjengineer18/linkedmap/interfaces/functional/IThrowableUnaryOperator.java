package io.github.cjengineer18.linkedmap.interfaces.functional;

@FunctionalInterface
public interface IThrowableUnaryOperator<T> extends IThrowableFunction<T, T> {

	static <A> IThrowableUnaryOperator<A> identity() {
		return a -> a;
	}

}
