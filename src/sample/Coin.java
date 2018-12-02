package sample;

import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Class for coins
 */
public class Coin {
	protected ImageView photo;
	private double y;
	private int value;
	private Text coinText;
	
	public Coin(String p) {
		setPhoto(p);
		Random R=new Random();
        value=R.nextInt(20)+1;
        coinText=new Text(String.valueOf(value));
        coinText.setX(photo.getX()+20);
        coinText.setY(photo.getY()+30);
	}
	
	public void setPhoto(String location) {
    	Image image = new Image(location);
    	photo=new ImageView(image);
    	Random rand=new Random();
    	photo.setX(rand.nextInt(500));
    	
    	photo.setY(-100);
    	photo.setFitWidth(50);
    	photo.setFitHeight(50);
    }
	
	public ImageView getPhoto() {
    	return photo;
    }
	
	public int getValue() {
		return value;
	}
	
	  public boolean isOverlapping(List<Block> blocks, Token T[], Wall W, Coin C[]) {
	    	for(int i=0;i<blocks.size();i++) {
	    		if(blocks.get(i).getBoundsInParent().intersects(this.getPhoto().getBoundsInParent())){
	    			return true;
	    		}
	    	}
	    	for(int i=0;i<2;i++) {
	    		if(T[i]!=null && T[i].getPhoto().getBoundsInParent().intersects(this.getPhoto().getBoundsInParent())) {
	    			return true;
	    		}
	    	}
	    	
	    	for(int i=0;i<2;i++) {
	    		if(C[i]!=null && C[i].getPhoto().getBoundsInParent().intersects(this.getPhoto().getBoundsInParent())){
	    			return true;
	    		}
	    	}
	    	if(W!=null && W.getLine().getBoundsInParent().intersects(this.getPhoto().getBoundsInParent())) {
	    		return true;
	    	}
	    	return false;
	    }
	  void moveDown(double speed) {
	    	setManualY(photo.getTranslateY()+1+speed);
	    	photo.setTranslateY(photo.getTranslateY() + 1 + speed);
	    }
	  
	  void moveCoinText(double speed) {
	       	coinText.setTranslateY(coinText.getTranslateY() + 1 + speed);
	    }
	  
	  public Text getCoinText() {
	    	return coinText;
	    }
	  
	  public double getManualY() {
			return y;
	    }
	  
	  public void setManualY(double relativeY) {
	    	this.y=relativeY;
	    }

}
