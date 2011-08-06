package jbamboo.basetypes;

public interface Functor<Domain,Range> {
	public Range exectue(Domain x);
}
