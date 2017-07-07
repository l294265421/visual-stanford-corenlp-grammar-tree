package com.liyuncong.learn.visualstanfordcorenlpgrammartree;

import java.util.LinkedList;
import java.util.List;

public class GrammarNode {
	private String text;
	private List<GrammarNode> children = new LinkedList<>();
	public void addChild(GrammarNode child) {
		assert child != null;
		children.add(child);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<GrammarNode> getChildren() {
		return children;
	}
	public void setChildren(List<GrammarNode> children) {
		this.children = children;
	}
	/**
	 * 
	 * @param StanfordTreeString 
	 * @return
	 */
	public static GrammarNode parseStanfordTreeString(String stanfordTreeString) {
		int end = stanfordTreeString.indexOf('(', 1);
		GrammarNode grammarNode = new GrammarNode();
		if (end == -1) {
			grammarNode.setText(stanfordTreeString.substring(1, stanfordTreeString.length() - 1));
		} else {
			grammarNode.setText(stanfordTreeString.substring(1, end).trim());
			String childrenText = stanfordTreeString.substring(end, stanfordTreeString.length() -1);
			LinkedList<Integer> stack = new LinkedList<>();
			for(int i = 0; i < childrenText.length(); i++) {
				if (childrenText.charAt(i) == '(') {
					stack.push(i);
				} else if (childrenText.charAt(i) == ')') {
					Integer start = stack.pop();
					if (stack.isEmpty()) {
						grammarNode.addChild(parseStanfordTreeString(childrenText.substring(start, i + 1)));
					}
				}
			}
		}
		return grammarNode;
	}
	@Override
	public String toString() {
		return "GrammarNode [text=" + text + ", children=" + children + "]";
	}
}
