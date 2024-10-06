package io.github.cjengineer18.linkedmap.interfaces.functional;

@FunctionalInterface
public interface IIndexedBiConsumer<T, U> {

	void accept(T arg0, U arg1, int index) throws Exception;

}
