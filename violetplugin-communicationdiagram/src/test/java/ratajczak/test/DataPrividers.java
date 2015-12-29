package ratajczak.test;

import org.testng.annotations.DataProvider;

public class DataPrividers {
	

	@DataProvider(name="NodesToolTipTest")
	public static Object[][] getToolTips(){
		return new Object[][]{
				{"Class",false},
				{"Interface",false},
				{"Object",true},
				{"Actor",true},
				{"Action",false},
		};
	}
	
	@DataProvider(name="EdgesToolTipTest")
	public static Object[][] getEgdeToolTips(){
		return new Object[][] {
			{"Depends on",false},
			{"Is associated with",false},
			{"Message",true},
			{"<<Use>>",false},
			{"Is composed of",false},
		};
	}
	
	@DataProvider(name="BoundsText")
	public static Object[][] getText(){
		return new Object[][]{
			{"przyklad"},{"zienna dlugosc"},{"nowe znaczenie"},{""},{"A co mi tam"}
		};
	}
	
	@DataProvider(name="MessageConstruction")
	public static Object[][] getMessages(){
		return new Object[][]{
			{new supportMessageTest("1.2", "[loop]", false, "", "1.2 * [loop] : ")},
			{new supportMessageTest("1.2", "[loop]", false, "yes", "1.2 * [loop] : yes")},
			{new supportMessageTest("5.2", "", true, "yes", "5.2 : yes")},
			{new supportMessageTest("2", "", false, "no", "2 : no")},
			{new supportMessageTest("13", "[loop]", true, "yes", "13 *|| [loop] : yes")},
			{new supportMessageTest("","",false,""," : ")},
			{new supportMessageTest("", "", false, "", " : ")},
			{new supportMessageTest("1.1", "", false, "", "1.1 : ")},
			{new supportMessageTest("1.1", "", true, "", "1.1 : ")},
			{new supportMessageTest("", "", true, "test", " : test")},
			
		};
	}
	
}


