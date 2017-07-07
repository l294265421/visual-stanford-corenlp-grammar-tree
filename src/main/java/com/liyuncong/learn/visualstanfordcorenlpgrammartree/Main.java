package com.liyuncong.learn.visualstanfordcorenlpgrammartree;

import com.liyuncong.learn.visualstanfordcorenlpgrammartree.GrammarTreeFrame.GrammarNode;

public class Main {
	private Main() {
	}
	public static void main(String[] args) {
		String stanfordParseString = "(ROOT (PP (P 由于) (IP (NP (CP (IP (NP (PN 我们)) (VP (MSP 所) (VP (VV 站)))) (DEC 的)) (NP (NN 立场))) (VP (VA 不同)))))";
		GrammarNode grammarNode = GrammarNode.parseStanfordTreeString(stanfordParseString);
		GrammarTreeFrame.showTree(grammarNode);
	}
}
