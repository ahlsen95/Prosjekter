package inf101.games.test;
 
import static org.junit.Assert.*;
 
import org.junit.Test;
import inf101.games.invaders.Position;
import inf101.games.invaders.IPosition;
import inf101.games.invaders.Rectangle;
 
public class test {
   
	private int width = 15;
    private int height = 15;
    private int x = 4;
    private int y = 5;
    private Rectangle recTest = new Rectangle(x, y, width, height);
   
    @Test
    public void getHeightTest() {
        assertEquals(this.height, recTest.getHeight());
    }
   
    @Test
    public void getWidthTest() {
        assertEquals(this.width, recTest.getWidth());
    }
   
    @Test
    public void getYTest() {
        assertEquals(this.y, recTest.getY());
    }
   
    @Test
    public void getXTest() {
        assertEquals(this.x, recTest.getX());
    }
   
    @Test
    public void moveTest(){
        IPosition pos = recTest.getPosition();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                recTest.move(i, j);
               
                assertEquals(pos, recTest.getPosition());  
            }
        }
    }
   
    @Test
    public void moveToTest(){
        IPosition pos = recTest.getPosition();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                recTest.moveTo(i, j);
               
                assertEquals(pos, recTest.getPosition());  
            }
        }
    }  
    
    @Test
    public void containsEqualRectTest(){
    	Rectangle rect1 = new Rectangle(0, 0, 6, 6);
    	Rectangle rect2 = new Rectangle(0, 0, 6, 6);
    	
    	assertTrue(rect1.contains(rect2));
    }
   
    @Test
    public void containsInsideRectTest(){
        Rectangle rect1 = new Rectangle(0, 0, 4, 4);
        Rectangle rect2 = new Rectangle(0, 0, 1, 1);
       
        assertTrue(rect1.contains(rect2));     
    }
   
    @Test
    public void containsPosOutsideTest(){
    	Rectangle rect1 = new Rectangle(0, 0, 2, 2);
    	Rectangle rect2 = new Rectangle(3, 3, 6, 6);
    	
    	assertFalse(rect1.contains(rect2.getPosition()));
    }
    
    @Test
    public void containsPosInsideRectTest(){
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(3, 3, 1, 1);
       
        assertTrue(rect1.contains(rect2.getPosition()));
    }
   
    @Test
    public void overlapsTest() {
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(3, 3, 1, 1);
       
        assertTrue(rect1.overlaps(rect2));
    }
 
}