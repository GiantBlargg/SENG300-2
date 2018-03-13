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
		parser.setEnvironment(new String[] { "/usr/lib/jvm/java-8-jdk" }, new String[] { "/usr/lib/jvm/java-8-jdk" },
				null, false);
		parser.setUnitName("test");

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
				if (node.resolveBinding().getBinaryName().equals(name)) {
					c.Declarations++;
				}
				return super.visit(node);
			}

			@Override
			public boolean visit(SimpleType node) {
				System.out.println(node.resolveBinding());
				System.out.println(node.getName().getFullyQualifiedName());
				return super.visit(node);
			}
		});
		return c;
	}

	public class counts {
		public int Declarations;
		public int References;
	}
}
