package assignment1;

import java.util.Map;

import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.compiler.*;

/**
 * This class can be passed a string to be parsed and a type to be counted.
 * 
 * @author daniel
 *
 */
public class ReferenceCounter {

	private ASTParser parser;
	private CompilationUnit cu;

	public ReferenceCounter(String path, char[] src) {
		parser = ASTParser.newParser(AST.JLS9);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setEnvironment(null, new String[] { path }, null, true);
		parser.setUnitName("");
		parser.setSource(src);

		cu = (CompilationUnit) parser.createAST(null);
	}

	public Map<String, counts> count(Map<String, counts> classes) {
		cu.accept(new ASTVisitor() {
			@Override
			public boolean visit(TypeDeclaration node) {
				if (node.resolveBinding() != null) {
					String name = node.resolveBinding().getBinaryName();
					counts c = classes.get(name);
					if (c == null) {
						c = new counts();
						classes.put(name, c);
					}
					c.Declarations++;

				}
				return super.visit(node);
			}

			@Override
			public boolean visit(SimpleType node) {
				if (node.resolveBinding() != null) {
					String name = node.resolveBinding().getBinaryName();
					counts c = classes.get(name);
					if (c == null) {
						c = new counts();
						classes.put(name, c);
					}
					c.References++;
				}
				return super.visit(node);
			}
		});
		return classes;
	}

	public class counts {
		public int Declarations;
		public int References;
	}
}
