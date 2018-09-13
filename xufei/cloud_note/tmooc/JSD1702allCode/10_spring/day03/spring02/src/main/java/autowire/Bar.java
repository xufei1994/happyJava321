package autowire;

public class Bar {
	private Foo foo;
	
	public Bar() {
		System.out.println("Bar()");
	}

	public void setFoo(Foo foo) {
		System.out.println("setFoo()");
		this.foo = foo;
	}

	@Override
	public String toString() {
		return "Bar [foo=" + foo + "]";
	}
	
	
	
}
