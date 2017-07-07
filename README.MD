# 项目解决的问题
给stanford-corenlp下面这个句子：
<br />由于我们所站的立场不同
<br />解析的出的语法树是这样的：
<br />(ROOT (PP (P 由于) (IP (NP (CP (IP (NP (PN 我们)) (VP (MSP 所) (VP (VV 站)))) (DEC 的)) (NP (NN 立场))) (VP (VA 不同)))))
<br />这个解释的结果并不易于我们分析句子。
<br />这个项目的目的就是把这个语法树以图形化、易于阅读的方式呈现

# 使用方法
现在只支持在代码中使用，具体使用方法见类：
<br />com.liyuncong.learn.visualstanfordcorenlpgrammartree.Main
<br />示例句子的显示结果已经存储为文件：
<br />com.liyuncong.learn.visualstanfordcorenlpgrammartree.grammar_tree.jpg