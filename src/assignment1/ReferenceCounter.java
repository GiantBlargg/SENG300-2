package assignment1;

import org.eclipse.jdt.core.dom.*;

/**
 * This class can be passed a string to be parsed and a type to be counted.
 * 
 * @author daniel
 *
 */

public class ReferenceCounter {

	private ASTParser parser;
	private CompilationUnit cu;

	public ReferenceCounter(String path) {
		parser = ASTParser.newParser(AST.JLS9);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setEnvironment(new String[] { System.getProperty("java.home") + "/lib/rt.jar" }, new String[] { path },
				null, false);
		parser.setUnitName("");

	}

	public void setSource(char[] src) {
		parser.setSource(src);

		cu = (CompilationUnit) parser.createAST(null);
	}

	public counts count(String name) {
		counts c = new counts();
		cu.accept(new ASTVisitor() {
			@Override
			public boolean visit(TypeDeclaration node) {
				if (node.resolveBinding() != null) {
					if (node.resolveBinding().getBinaryName().equals(name)) {
						c.Declarations++;
					}
				}
				return super.visit(node);
			}

			@Override
			public boolean visit(SimpleType node) {
				if (node.resolveBinding() != null) {
					if (node.resolveBinding().getBinaryName().equals(name)) {
						c.References++;
					}
				}
				return super.visit(node);
			}
		});
		return c;
	}

	public class counts {
		public int Declarations;
		public int References;
	}

	public static void main(String[] args) {
		ReferenceCounter counter = new ReferenceCounter(".");
		counter.setSource("package foo; public class Bar{public String test;}".toCharArray());
		counts c = counter.count("java.lang.String");
		System.out.println(c.Declarations);
		System.out.println(c.References);
	}
}
