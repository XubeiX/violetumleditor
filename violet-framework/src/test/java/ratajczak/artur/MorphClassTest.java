package ratajczak.artur;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.abstracts.AbstractGraph;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.abstracts.property.Morph;
import com.horstmann.violet.product.diagram.abstracts.property.MultiLineString;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.AbstractNodeMorph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.ClassNodeMorph;
import com.horstmann.violet.product.diagram.abstracts.property.morphClass.InterfaceNodeMorph;

public class MorphClassTest {
	private AbstractGraph graph;
	private Morph morph;

	@BeforeMethod
	public void init() {
		graph = new ClassDiagramGraph();
		morph = new Morph();
		Morph.setGraph(graph);
	}
	
	
	@Test
	public void redrawTest(){
		Class morphClass = Morph.class;
		INode node = new AbstractNodeMorph();
		INode node1 = new AbstractNodeMorph();
		INode node2 = new AbstractNodeMorph();
		
		graph.addNode(node, node.getLocation());
		graph.addNode(node1, node1.getLocation());
		graph.addNode(node2, node2.getLocation());
		
		try {
			
			Method privMethod = morphClass.getMethod("redrawNodes",  new Class[]{INode.class, INode.class});
			privMethod.setAccessible(true);
			
			ClassNodeMorph c =new ClassNodeMorph();
			AbstractNodeMorph a = new AbstractNodeMorph();
			InterfaceNodeMorph i = new InterfaceNodeMorph();
			
			privMethod.invoke(morphClass, node,c);
			privMethod.invoke(morphClass, node1,a);
			privMethod.invoke(morphClass, node2,i);
			ArrayList<INode> nodes = new ArrayList<INode>(graph.getAllNodes());
			
			Assert.assertEquals(nodes.get(0), c);
			Assert.assertEquals(nodes.get(1), a);
			Assert.assertEquals(nodes.get(2), i);
			
			
		}catch (NoSuchMethodException e) {} catch (SecurityException e) {} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		}
	}
	
	@Test(dataProvider="Class", dataProviderClass=DataProviders.class)
	public void abstractToClass(SupportToCreateClass s){
		AbstractNodeMorph abst = prepareDefaultAbstract(s);
		graph.addNode(abst, abst.getLocation());
		
		ClassNodeMorph c = prepareDefaultClass(s);
		
		abst.setMorph(Morph.TOCLASS);
		
		ArrayList<INode> nodes = new ArrayList<INode>(graph.getAllNodes());

		ClassNodeMorph afterMorph = (ClassNodeMorph) nodes.get(0);
		
		abstractoToClassAssert(afterMorph, c);
		
	}
	
	
	
	

	@Test(dataProvider="Class", dataProviderClass=DataProviders.class)
	public void abstractToINterface(SupportToCreateClass s){
		AbstractNodeMorph abst = prepareDefaultAbstract(s);
		graph.addNode(abst, abst.getLocation());
		InterfaceNodeMorph shouldBe = prepareDefaultInterface(s);

		abst.setMorph(Morph.TOINTERFACE);

		ArrayList<INode> nodes = new ArrayList<INode>(graph.getAllNodes());

		InterfaceNodeMorph afterMorph = (InterfaceNodeMorph) nodes.get(0);
		
		abstToInterfaceAssert(abst, shouldBe);
	}

	

	@Test(dataProvider="Class", dataProviderClass=DataProviders.class)
	public void interfaceToAbstract(SupportToCreateClass support){
		InterfaceNodeMorph inter = prepareDefaultInterface(support);
		graph.addNode(inter, inter.getLocation());
		
		AbstractNodeMorph shouldBe = prepareDefaultAbstract(support);
		MultiLineString att = new MultiLineString();
		att.setText("");
		shouldBe.setAttributes(att);
		
		inter.setMorph(Morph.ABSTRACT);

		ArrayList<INode> nodes = new ArrayList<INode>(graph.getAllNodes());

		AbstractNodeMorph afterMorph = (AbstractNodeMorph) nodes.get(0);
		
		interToAbstractAssertions(afterMorph, shouldBe);
	}
	


	@Test(dataProvider="Class", dataProviderClass=DataProviders.class)
	public void interfaceToClass(SupportToCreateClass support) {
		InterfaceNodeMorph inter = prepareDefaultInterface(support);
		graph.addNode(inter, inter.getLocation());

		ClassNodeMorph shouldBe = prepareDefaultClass(support);
		MultiLineString att = new MultiLineString();
		att.setText("");
		shouldBe.setAttributes(att);

		inter.setMorph(Morph.TOCLASS);

		ArrayList<INode> nodes = new ArrayList<INode>(graph.getAllNodes());

		ClassNodeMorph afterMorph = (ClassNodeMorph) nodes.get(0);
		
		interToClassAssertions(afterMorph, shouldBe);
	}



	@Test(dataProvider = "Class", dataProviderClass = DataProviders.class)
	public void classToAbstract(SupportToCreateClass support) {
		ClassNodeMorph klasa = prepareDefaultClass(support);
		graph.addNode(klasa, klasa.getLocation());

		AbstractNodeMorph shouldBe = prepareDefaultAbstract(support);

		klasa.setMorph(Morph.ABSTRACT);

		ArrayList<INode> nodes = new ArrayList<INode>(graph.getAllNodes());

		AbstractNodeMorph afterMorph = (AbstractNodeMorph) nodes.get(0);
		chceckAbstractToClassAssertions(afterMorph, shouldBe);
	}

	@Test(dataProvider = "Class", dataProviderClass = DataProviders.class)
	public void classToInterfaceTest(SupportToCreateClass support) {
		ClassNodeMorph klasa = prepareDefaultClass(support);
		graph.addNode(klasa, klasa.getLocation());

		InterfaceNodeMorph shouldBe = prepareDefaultInterface(support);

		// morph.morph(klasa);
		klasa.setMorph(Morph.TOINTERFACE);

		ArrayList<INode> nodes = new ArrayList<INode>(graph.getAllNodes());

		InterfaceNodeMorph afterMorph = (InterfaceNodeMorph) nodes.get(0);

		checkClassToInterfaceAssertion(afterMorph, shouldBe);
	}


	private ClassNodeMorph prepareDefaultClass(SupportToCreateClass support) {
		ClassNodeMorph clazz = new ClassNodeMorph();
		MultiLineString name = new MultiLineString();
		MultiLineString attr = new MultiLineString();
		MultiLineString meth = new MultiLineString();
		name.setText(support.name);
		attr.setText(support.attributes);
		meth.setText(support.methods);
		clazz.setName(name);
		clazz.setAttributes(attr);
		clazz.setMethods(meth);
		clazz.setLocation(support.Location);

		return clazz;
	}

	private InterfaceNodeMorph prepareDefaultInterface(SupportToCreateClass support) {
		InterfaceNodeMorph inter = new InterfaceNodeMorph();
		MultiLineString name = new MultiLineString();
		MultiLineString meth = new MultiLineString();
		name.setText("<<interface>>\n" + support.name);
		meth.setText(support.methods);
		inter.setName(name);
		inter.setMethods(meth);
		inter.setLocation(support.Location);
		return inter;

	}
	
	private AbstractNodeMorph prepareDefaultAbstract(SupportToCreateClass support) {
		AbstractNodeMorph abst = new AbstractNodeMorph();
		MultiLineString name = new MultiLineString();
		MultiLineString attr = new MultiLineString();
		MultiLineString meth = new MultiLineString();
		name.setText("<<abstract>>\n" + support.name);
		attr.setText(support.attributes);
		meth.setText(support.methods);
		abst.setName(name);
		abst.setAttributes(attr);
		abst.setMethods(meth);
		abst.setLocation(support.Location);
		return abst;
	}

	private void chceckAbstractToClassAssertions(AbstractNodeMorph afterMorph, AbstractNodeMorph shouldBe) {
		Assert.assertEquals(afterMorph.getName().getText(), shouldBe.getName().getText());
		Assert.assertEquals(afterMorph.getMethods().getText(), shouldBe.getMethods().getText());
		Assert.assertEquals(afterMorph.getAttributes().getText(), shouldBe.getAttributes().getText());
		Assert.assertEquals(afterMorph.getLocation(), shouldBe.getLocation());
		Assert.assertEquals(afterMorph.getMorph(), Morph.ABSTRACT);
	}
	
	private void abstractoToClassAssert(ClassNodeMorph afterMorph, ClassNodeMorph c) {
		Assert.assertEquals(afterMorph.getName().getText(), c.getName().getText());
		Assert.assertEquals(afterMorph.getMethods().getText(), c.getMethods().getText());
		Assert.assertEquals(afterMorph.getAttributes().getText(), c.getAttributes().getText());
		Assert.assertEquals(afterMorph.getLocation(), c.getLocation());
		Assert.assertEquals(afterMorph.getMorph(), Morph.TOCLASS);
		
	}
	
	private void interToAbstractAssertions(AbstractNodeMorph afterMorph, AbstractNodeMorph shouldBe) {
		Assert.assertEquals(afterMorph.getName().getText(), shouldBe.getName().getText());
		Assert.assertEquals(afterMorph.getMethods().getText(), shouldBe.getMethods().getText());
		Assert.assertEquals(afterMorph.getAttributes().getText(), shouldBe.getAttributes().getText());
		Assert.assertEquals(afterMorph.getLocation(), shouldBe.getLocation());
		Assert.assertEquals(afterMorph.getMorph(), Morph.ABSTRACT);
	}
	
	private void checkClassToInterfaceAssertion(InterfaceNodeMorph afterMorph, InterfaceNodeMorph shouldBe) {
		Assert.assertEquals(afterMorph.getName().getText(), shouldBe.getName().getText());
		Assert.assertEquals(afterMorph.getMethods().getText(), shouldBe.getMethods().getText());
		Assert.assertEquals(afterMorph.getLocation(), shouldBe.getLocation());
		Assert.assertEquals(afterMorph.getMorph(), Morph.TOINTERFACE);

	}
	
	private void abstToInterfaceAssert(AbstractNodeMorph abst, InterfaceNodeMorph shouldBe) {
		Assert.assertEquals(abst.getName().getText(), shouldBe.getName().getText());
		Assert.assertEquals(abst.getMethods().getText(), shouldBe.getMethods().getText());
		Assert.assertEquals(abst.getLocation(), shouldBe.getLocation());
		Assert.assertEquals(abst.getMorph(), Morph.TOINTERFACE);
		
	}
	
	private void interToClassAssertions(ClassNodeMorph afterMorph, ClassNodeMorph shouldBe) {
		Assert.assertEquals(afterMorph.getName().getText(), shouldBe.getName().getText());
		Assert.assertEquals(afterMorph.getMethods().getText(), shouldBe.getMethods().getText());
		Assert.assertEquals(afterMorph.getAttributes().getText(), shouldBe.getAttributes().getText());
		Assert.assertEquals(afterMorph.getLocation(), shouldBe.getLocation());
		Assert.assertEquals(afterMorph.getMorph(), Morph.TOCLASS);
	}

	
}
