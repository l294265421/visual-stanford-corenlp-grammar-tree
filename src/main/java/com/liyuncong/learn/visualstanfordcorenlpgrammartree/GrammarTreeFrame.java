package com.liyuncong.learn.visualstanfordcorenlpgrammartree;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class GrammarTreeFrame extends JFrame {
	private static final long serialVersionUID = -3763028538806157006L;
	
	public GrammarTreeFrame() {
		setSize(1800, 800);
	}
	
	public static void showTree(final GrammarNode root) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				GrammarTreeFrame treeFrame = new GrammarTreeFrame();
				treeFrame.setTitle("语法树");
				treeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				treeFrame.add(new GrammarNodeComponent(root));
				treeFrame.setVisible(true);
			}
		});
	}
	
	public static class GrammarNodeComponent extends JComponent {
		private static final long serialVersionUID = -6615204568407054807L;
		private GrammarNode root;
		private Map<Integer, List<GrammarNodeWithPosition>> levelAndGrammarNode = new HashMap<>();
		
		public GrammarNodeComponent(GrammarNode root) {
			super();
			this.root = root;
			levelAndGrammarNode = hierarchicalTraversal(root);
		}

		/**
		 * 层次遍历语法树，并把每个节点包装为GrammarNodeWithPosition
		 * @param root
		 * @return 键为层好（从1开始）值为对应层的所有节点
		 */
		private static Map<Integer, List<GrammarNodeWithPosition>> hierarchicalTraversal(GrammarNode root) {
			Map<Integer, List<GrammarNodeWithPosition>> levelAndGrammarNode = new HashMap<>();
			GrammarNodeWithPosition sentinel = new GrammarNodeWithPosition();
			LinkedList<GrammarNodeWithPosition> queue = new LinkedList<>();
			GrammarNodeWithPosition rootWithPosition = new GrammarNodeWithPosition();
			rootWithPosition.setGrammarNode(root);
			queue.addLast(rootWithPosition);
			queue.addLast(sentinel);
			int level = 1;
			List<GrammarNodeWithPosition> thisLevelNode = new LinkedList<>();
			while (!queue.isEmpty()) {
				GrammarNodeWithPosition grammarNodeWithPosition = queue.removeFirst();
				thisLevelNode.add(grammarNodeWithPosition);
				if (grammarNodeWithPosition == sentinel) {
					levelAndGrammarNode.put(level, thisLevelNode);
					thisLevelNode = new LinkedList<>();
					if (!queue.isEmpty()) {
						queue.addLast(sentinel);
						level++;
					}
				} else {
					for(GrammarNode child : grammarNodeWithPosition.getGrammarNode().getChildren()) {
						GrammarNodeWithPosition childWithPosition = new GrammarNodeWithPosition();
						childWithPosition.setGrammarNode(child);
						queue.addLast(childWithPosition);
					}
				}
			}
			return levelAndGrammarNode;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D graphics2d = (Graphics2D) g;
			drawGrammarNode(graphics2d, root, 1000, 20, 1);
		}
		
		private void drawGrammarNode(Graphics2D graphics2d, GrammarNode grammarNode, int x, int y, int level) {
			graphics2d.drawString(grammarNode.getText(), x, y);
			List<GrammarNode> children = grammarNode.getChildren();
			int childrenNum = children.size();
			if (childrenNum == 0) {
				return;
			}
			int half = children.size() / 2;
			int newY = y + 50;
			for(int i = 0; i < childrenNum; i++) {
				GrammarNode child = children.get(i);
				int newX = generateX(level + 1, x, child, i, half);
				graphics2d.draw(new Line2D.Double(x, y + 12, newX, newY - 12));
				drawGrammarNode(graphics2d, child, newX, newY, level + 1);
			}
		}
		
		private int generateX(int level, int parentX, GrammarNode grammarNode, int childSerialNum, int half) {
			int x = parentX + (childSerialNum - half) * 40 * ((int) (level * 0.7));
			List<GrammarNodeWithPosition> thisLevelGrammarNodes = levelAndGrammarNode.get(level);
			GrammarNodeWithPosition previous = null; 
			GrammarNodeWithPosition now = null;
			for (GrammarNodeWithPosition grammarNodeWithPosition : thisLevelGrammarNodes) {
				previous = now;
				now = grammarNodeWithPosition;
				if (now.getGrammarNode() == grammarNode) {
					break;
				}
			}
			if (previous != null) {
				int previouX = previous.getX();
				int previousTextLen = previous.getGrammarNode().getText().length();
				if (x < previouX + previousTextLen * Constants.CHAR_WIDTH) {
					x = previouX + previousTextLen * Constants.CHAR_WIDTH + 10;
				}
			}
			now.setX(x);
			return x;
		}
		
	}
	
}
