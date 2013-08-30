package Messages;

import Messages.MessageGameProgress.MessageGameAction;

public class Message3Boom extends MessageGameProgress {

	private boolean isBoom;
	private float number;
	private boolean isCorrect;
	private boolean isNext;
	
	public Message3Boom(boolean isBoom,boolean isNext, float number) {
		super(MessageGameAction.TURN_3BOOM);
		this.setBoom(isBoom);
		this.setNumber(number);
		this.setNext(isNext);
	}
	
	public Message3Boom() {
		
		super(MessageGameAction.TURN_3BOOM);
	};

	public void setBoom(boolean isBoom) {
		this.isBoom = isBoom;
	}

	public boolean isBoom() {
		return isBoom;
	}

	public void setNumber(float number) {
		this.number = number;
	}

	public float getNumber() {
		return number;
	}

	public void setIsCorrect(boolean isCorrect) {
		
		this.isCorrect=isCorrect;
	}


	public boolean isCorrect() {
		return isCorrect;
	}

	public void setNext(boolean isNext) {
		this.isNext = isNext;
	}

	public boolean isNext() {
		return isNext;
	}

}
