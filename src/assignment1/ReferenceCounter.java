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

	public ReferenceCounter() {
		parser = ASTParser.newParser(AST.JLS9);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);

	}

	public void setSource(char[] src) {
		parser.setSource(src);

		cu = (CompilationUnit) parser.createAST(null);
	}

	public int count() {
		int count = 0;
		cu.accept(new ASTVisitor() {
			@Override
			public boolean visit(TypeDeclaration node) {
				System.out.println(node.resolveBinding());
				System.out.println(node.getName().getFullyQualifiedName());
				return super.visit(node);
			}

			@Override
			public boolean visit(SimpleType node) {
				System.out.println(node.resolveBinding());
				System.out.println(node.getName().getFullyQualifiedName());
				return super.visit(node);
			}
		});
		return count;
	}

	public static void main(String[] args) {
		ReferenceCounter counter = new ReferenceCounter();
		counter.setSource("public class Bar{private String test;}".toCharArray());
		counter.count();

	}
}
