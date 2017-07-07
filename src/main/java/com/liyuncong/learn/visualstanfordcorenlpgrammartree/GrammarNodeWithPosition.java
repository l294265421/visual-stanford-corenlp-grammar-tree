package com.liyuncong.learn.visualstanfordcorenlpgrammartree;

public class GrammarNodeWithPosition {
	private GrammarNode grammarNode;
	private int x;
	private int y;
	public GrammarNode getGrammarNode() {
		return grammarNode;
	}
	public void setGrammarNode(GrammarNode grammarNode) {
		this.grammarNode = grammarNode;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "GrammarNodeWithPosition [grammarNode=" + grammarNode + ", x=" + x + ", y=" + y + "]";
	}
}
