package ratajczak.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ratajczak.violet.product.diagram.classes.CommunicationDiagramPlugin;

public class DiagramPluginTest {
	
	private CommunicationDiagramPlugin plugin;
	
	@BeforeClass
	public void init(){
		plugin = new CommunicationDiagramPlugin();
	}
	
	@Test
	public void DiagramPropertyTest(){
		Assert.assertEquals(plugin.getName(), "4.Communication diagram");
		Assert.assertEquals(plugin.getCategory(), "2.Dynamic view");
		Assert.assertEquals(plugin.getFileExtension(), ".communication.violet.html");
		Assert.assertEquals(plugin.getFileExtensionName(),"Communication Diagram Files (.communication.violet.html)");
		Assert.assertEquals(plugin.getSampleFilePath(), "sample.communication.violet.html");
	}
	
}
