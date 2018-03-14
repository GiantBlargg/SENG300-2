public class Test<T> {
	public class NestedClass {
	}
}

interface Interface {
	Test.NestedClass t();
	Test<Test.NestedClass> g;
}