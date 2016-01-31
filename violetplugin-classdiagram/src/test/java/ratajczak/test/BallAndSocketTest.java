package ratajczak.test;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.horstmann.violet.product.diagram.classes.nodes.BallNode;
import com.horstmann.violet.product.diagram.classes.nodes.SocketNode;

public class BallAndSocketTest {
	private BallNode ball;
	private SocketNode socket;

	@BeforeMethod
	public void init() {
		ball = new BallNode();
		socket = new SocketNode();
	}

	@Test
	public void copyBallTest() {
		ball.setName("Compied");
		ball.setLocation(new Point2D.Double(10, 10));

		BallNode copy = ball.clone();

		Assert.assertEquals(copy.getName(), ball.getName());
		Assert.assertEquals(copy.getLocation(), ball.getLocation());
	}

	@Test
	public void copySocketTest() {
		socket.setLocation(new Point2D.Double(50, 35));
		socket.setBackgroundColor(Color.RED);
		socket.setName("socket");
		SocketNode copy = socket.clone();

		Assert.assertEquals(copy.getLocation(), socket.getLocation());
		Assert.assertEquals(copy.getBackgroundColor(), socket.getBackgroundColor());
		Assert.assertEquals(copy.getName(), socket.getName());
	}

	@Test
	public void testBallBounds() {
		Rectangle2D expected = new Rectangle2D.Double(20, 30, 20, 20);
		ball.setLocation(new Point2D.Double(20, 30));
		Rectangle2D ballBounds = ball.getBounds();

		Assert.assertEquals(ballBounds, expected);

	}

	@Test
	public void testSocketBounds() {
		Rectangle2D expected = new Rectangle2D.Double(20, 30, 20, 20);
		socket.setLocation(new Point2D.Double(20, 30));
		Rectangle2D socketBounds = socket.getBounds();
		Assert.assertEquals(socketBounds, expected);
	}

}
