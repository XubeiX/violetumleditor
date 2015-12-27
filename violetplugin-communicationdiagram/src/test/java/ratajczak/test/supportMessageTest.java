package ratajczak.test;

public class supportMessageTest{
	String sequenceNumber;
	String sequentialLoop;
	boolean ConcurrentLoop;
	String Message;
	String expected;
	
	public supportMessageTest(String sequenceNumber, String sequentialLoop, boolean ConcurrentLoop, String Message, String expected){
		this.sequenceNumber = sequenceNumber;
		this.sequentialLoop = sequentialLoop;
		this.ConcurrentLoop=ConcurrentLoop;
		this.Message=Message;
		this.expected=expected;
	}
}