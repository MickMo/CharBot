/**
 * 
 */
package org.wltea.analyzer.test;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.search.Query;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;
//import org.wltea.analyzer.help.CharacterHelper;
import org.wltea.analyzer.lucene.IKQueryParser;

import junit.framework.TestCase;

/**
 * @author Administrator
 *
 */
public class SegmentorTester extends TestCase{
	
	public void testLetter(){
		String t = "C++ S43-LC10 AT&T and I.B.M Corp mail : 1.12.34.33 -1-2003%123*111-11+12 2009A17B10 10:10:23wo!r+d.1{}0.16-8AAAA_B$BB@0.1.12.34.33.10.18ok?hello001.txt";
//		Token ruToken = new Token();
//		StandardTokenizer st = new StandardTokenizer(new StringReader(t));
//		try {
//			while((ruToken = st.next(ruToken)) != null){
//				System.out.println(ruToken);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(t);	
		IKSegmentation ikSeg = new IKSegmentation(new StringReader(t) ,false);
		try {
			Lexeme l = null;
			while( (l = ikSeg.next()) != null){
				System.out.println(l);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void testNumberCount(){
		List<String> testStr = new ArrayList<String>();
		testStr.add("20多人 20几万");
		testStr.add("一九九五年12月31日,");
		testStr.add("1/++ ￥+400 ");
		testStr.add("-2e-12 xxxx1E++300/++"); 
		testStr.add("1500名常用的数量和人名的匹配 超过22万个");
		testStr.add("据路透社报道，印度尼西亚社会事务部一官员星期二(29日)表示，" 
				+ "日惹市附近当地时间27日晨5时53分发生的里氏6.2级地震已经造成至少5427人死亡，" 
				+ "20000余人受伤，近20万人无家可归。");
		testStr.add("古田县城关六一四路四百零五号");
		testStr.add("欢迎使用阿江统计2.01版");
		testStr.add("51千克 五十一千克 五万一千克 两千克拉 2千克拉 五十一");
		testStr.add("十一点半下班十一点下班");
		testStr.add("福州第一中学福州一中福州第三十六中赐进士及第");
		testStr.add("0571-12345686");
		testStr.add("第21个人 第100位 第10个月");
		
		
		for(String t : testStr){
			System.out.println(t);	
			IKSegmentation ikSeg = new IKSegmentation(new StringReader(t) , false);
			try {
				Lexeme l = null;
				while( (l = ikSeg.next()) != null){
					System.out.println(l);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("***************");	
		}
		
	}
	
	public void testChinese(){
		List<String> testStr = new ArrayList<String>();
//		testStr.add("福州大学周边的必胜客");
//		testStr.add("中华人民共和国香港特别行政区");
//		testStr.add("据路透社报道，印度尼西亚社会事务部一官员星期二(29日)表示，" 
//		+ "日惹市附近当地时间27日晨5时53分发生的里氏6.2级地震已经造成至少5427人死亡，" 
//		+ "20000余人受伤，近20万人无家可归。");
//		
//		testStr.add("公路局正在治理解放大道路面积水问题");
//		testStr.add("福建省邮政储蓄局华林储蓄所");
//		testStr.add("一九九五年12月31日,我在夜色下舞蹈 细长的鞋跟踩着星星的碎片 旋转的黑色裙摆覆盖来时的眼泪 亲爱的恋人 献给你我最华丽的落幕");
//		testStr.add("直接从 Gmail 内部与您的朋友们聊天，再也无需单独加载程序或查找新地址。只需点击一下鼠标，即可与通过电子邮件来往的人以及 Google Talk 网络中的人聊天。现在您甚至可以保存和搜索 Gmail 帐户中的聊天记录。聊天还是不错的。");
//		testStr.add("江南水都II期,花好月圆3期针对分词器所需的字符切割/过滤算法进行可行性测试");
//		
//		testStr.add("1500名常用的数量和人名的匹配 超过22万个词的词库整理 实现jinshan正向最大匹配算法 支持分词粒度控制 ");
//		testStr.add("欢迎使用阿江统计2.01版！ 阿江(Ajiang) www.ajiang.net");
//		testStr.add("姚明是一个伟大的篮球运动员.");	
//		testStr.add("陈文平是开睿动力通讯科技有限公司董事长");
//		testStr.add("古田县城关六一四路四百零五号");
//		testStr.add("永和服装饰品有限公司");
//		testStr.add("当结合成分子时");
//		testStr.add("航班车行李票");		
//		testStr.add("长春市长春节致辞");		
////		
//		testStr.add("天福大酒店,陈文平是提拉米苏董事长,达利蛋黄派，家家都喜爱， 福州达利园食品有限公司.");
//		testStr.add("我对他有意见。总统有意见他");
//		
//		testStr.add("，联合国安理会16日(当地时间)对于涉及核武及导弹项目的5名朝鲜人士，确定了禁止旅行和冻结海外资产等制裁，并决定同时制裁5个相关企业和2种相关材料。由此，对朝制裁首次包括朝鲜政府人士。另外，继4月冻结朝鲜3家企业的海外资产之后，再加上上述5家企业，被安理会制裁的朝鲜企业合计共为8 家。"
//				+"在此之前的制裁名单里包括朝鲜外交官等共15人，但安理会最终只确定了5人。对此，联合国的某外交官表示:“对个人的制裁具有象征性的意义。”“制裁名单被缩减为5人，可以理解为与会国之间经过协调所达成的结果。"
//				+"安理会制裁的决定还包括5个企业，这些企业被怀疑与弹道导弹、核武器和其他大规模杀伤性武器项目有关。"
//				+"与此同时，可用于制作导弹的EDM碳素化合物和芳纶(aramid)纤维，也被列为被制裁对象。");
//		
//		testStr.add("我真不知道台湾的媒体人是哪只眼睛看蔡英文是「小龙女」。蔡英文怎么会是小龙女。如果说原著的文字无法形象化小龙女的形象，那么我们就从影视中来探究探究。" +
//				"许多影视剧中都有小龙女的扮演，台湾人比较熟悉早期陈玉莲，潘迎紫，新加坡有范文芳，" +
//				"大陆的有刘亦菲，而其中最为经典的还属香港的李若彤，即便以最低标准的吴倩莲来说，" +
//				"以上诸位蔡英文能比哪一个？千万不要亵渎了影迷们心目中的小龙女的光辉形象。");
//		
		testStr.add("IKAnalyzer是一个开源的，基于java语言开发的轻量级的中文分词工具包。从2006年12月推出1.0版开始， IKAnalyzer已经推出了3个大版本。");
//
		testStr.add("永和服装饰品");//		
		testStr.add("作者博客：linliangyi2007.javaeye.com  电子邮件地址：linliangyi2005@gmail.com");
		testStr.add("古田县城关六一四路四百零五号");
		testStr.add("广州市越秀区广州大道中131-133号信龙大厦");

//		
//		testStr.add("中国人民银行");

		testStr.add("法新社消息，日本自卫队直升机飞临福岛第一核电站3号和4号堆上方，每架倾倒了超过7吨水来给反应堆降温。行动从上午日本时间九点五十四分开始。");
		testStr.add("曙光天阔 I620r-G /A950r-F 夏普SH9020C");
//		testStr.add("神话电视连续剧  20092008년을 마무리 할까 합니다  右のテキストエリアに訳文が  にちほん");
//
//		testStr.add("向浙江省长兴县安监局等多部门反应");
//		testStr.add("浙江省长兴县城管在光天化日之下明抢东东");
		for(String t : testStr){
			System.out.println(t);	
			IKSegmentation ikSeg = new IKSegmentation(new StringReader(t) , false);
			try {
				Lexeme l = null;
				while( (l = ikSeg.next()) != null){
					System.out.println(l);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("***************");	
		}
//		System.out.println(CharacterHelper.isCJKCharacter('년'));

//		char[] jpStr = "".toCharArray();
//		for(char c : jpStr){
//			Character.UnicodeBlock jub = Character.UnicodeBlock.of(c);
//			System.out.println(jub.toString());
//		}
	}
	
	public static void main(String[] args){
		
	String testString = "";
		for (int i = 0; i <300; i++) {
			testString += "18岁那年，有个自称算命先生看了我的手相后说，此生你将注定与男人纠缠不清。我说怎么可能，我不漂亮，也无贪欲。我不想要太多，一生只想爱一次，只要一个爱我的丈夫，然后我是他的好妻子。为他做饭、洗衣带孩子。我要和他相伴到老。 " 
             +"算命先生还说我曾有快乐的童年，但这说明不了什么，未来每一天都在变，没有长久的苦难，当然也没有长久的幸福。听到这话的时候，我感觉自己从里到外开始发冷。" 
             +"那么多年，我一直企图摆脱这个咒语一样的预言，却总是徒劳无功。我碰到过很多男人，不是他爱我我不爱他就是我爱他而他不爱我，还有就是我们彼此相爱却因为有缘无份而不得不分开。他们都说我是好姑娘，结果是我至今仍孑然一身。" 
 +"23岁的时候，我经历了第一场爱情的失败。那是我的初恋，他叫钟建。我们分手的时候也是这样的春天。我还清晰地记得，我们坐在江边的茶园里。我们都不敢看对方的眼睛。我的眼睛四处逡巡。我看见柳树发芽了，鹅黄的叶子在阳光下快乐地疯长。河里有很多垃圾漂过，河堤上有情侣在接吻。茶叶一根根笔直地站立在水中，这是上好的绿茶。我想起刚认识钟建的那一年那个算命先生的话，我想这是不是就是一切纠缠和苦难的开始呢，我很害怕。" 
 +"和钟建分手一年后，我来到了现在的这座城市。我曾在这个城市读了四年大学，我像熟悉我的家乡一样熟悉这座城市。最重要的是我最好的朋友邓澜在这座城市。我做了电台DJ，一档深夜音乐节目。从此，我用声音和文字与世界交流。 "
 +"我居无定所，像无根的池萍一样在这个城市的四处飘荡。一年内我搬了五次家。从这个城市的南边搬到北边，再从北边搬到西边然后东边。我恨极了这种漂泊流浪没有尽头没有希望的日子。我每天晚上十点半出门上班，十二点下班。我像幽灵一样穿越这个城市的夜晚。我化很精致的妆，穿很漂亮的衣服。像人们清晨出门那样。" 
 +"有时候我会去酒吧坐一坐，更多的时候我下班就回家上网。我买了一台二手联想电脑，很破却已经足够我夜深人静的时候上线游荡。我每天准时凌晨一点上网，然后在各个BBS之间游荡，写写看看，停停走走。我很少仔细看贴子，走马观花逛完一圈的时候天就亮。" 
 +"我在节目里侃侃而谈，实际上我勤于思考却拙于表达，我总是不知道该用怎样的词语才能恰当地表达出自己的起初想法。我在网上认识人不多，其中有一个叫野鬼。他说他是孤魂野鬼，只在夜晚出没。碰到他的时候我叫幽冥。他说女孩子不应该取我这们的名字，我笑就，因为我也只在夜晚活动。我们从不问对方是干什么的，我只是滔滔不绝地对他诉说，说我的生活，说我喜欢自己自己的声音在这个城市的夜里四处散布，说我陷在绝望的爱情里找不到出路，" +
 		"还说我希望有一座房子，面朝大海，春暖花开。他总是安静地听我说话，无论我怎么思维混乱、言辞颠倒他也从不怀疑我是否在撒谎。我喜欢相互信任，即使是在网上。我叫他野鬼，可他从不叫我幽冥，他叫我丫头，我们的称呼常常会打动我的心，但那只限于夜晚。白天我是一个坚硬冷漠的人，我甚至从不在白天上网。那个叫野鬼的人从不问我为什么，只是对我说，我不该是一个缺少阳光的女子，我令他心疼。" 
 +"无论我对野鬼说过　只作为一个符号存储在我的电脑里。关掉电脑以后，他就像空气一样立刻从我的视野和脑海里消失，甚至于一场小小的病毒也会让他从此不再出现。放映＜花样年华＞后，我曾经对他说他是我的“树洞”，他沉默数秒后表示反对。他说“树洞”是没有感情和生命的，而他有。 "
 +"上大学的时候老师告诉我们所谓悲剧就是人类自己将美好的东西撕碎给人类看。我一直记得我句话，我想努力制造一个喜剧，却不小心把这个喜剧撕碎了，成了悲剧。 "
 +"邓澜说我把自己的生活搞得乱七八糟，该有个人来照顾我，还说如果我再不嫁就没人要了，那口气和我爸妈一样。我说好啊，那你给我介绍个好人吧，好要有跔的胸怀愿意收留我。其实，那时候我也就26岁，比起30岁的现在来说还算得上是花样年华。 "
 +"第一次见到江凯文是在培根路的1812酒吧，我和邓澜一进门就看见坐在吧台的江凯文，他一个人在喝闷酒。邓澜为我们作了介绍，虽是第一次见面，其实我们都早已在对方心里盘踞。有很多次，邓澜对我欲言又止。 "
 +"邓澜欲言又止的是江凯文是个离异的男人，有一个小孩，和她妈妈在一起。邓澜觉得这对我来说有些不公。我不在乎这些。我们相爱了。 "
 +"第一次感到和一个人心有灵犀的默契是这样幸福。我想，我会一辈子爱他，我不要再让忧郁溢满他的眼神。我也相信他爱我一如我爱他。然后，我开始小心翼翼地经营这份爱情。他不喜欢我做电台的工作，我听他的话换了一份朝九晚五的工作。每天做好晚饭等他回家。我戒了网，彻底忘记那个叫野鬼的人。我希望我的爱可以抚平他心里的伤口。" 
 +"一切原本都是好好的。如果我不说结婚的话，可能一切都不会发生。和江凯文相恋一年的时候，我想结婚了。我并不是想要一个所谓老婆的名分，我从来都觉得婚姻束缚不了两个不再相爱了的人，没了爱，婚姻又要来干什么。我只是想为他生个孩子，给他一个完整的家。 "
 +"我说出结婚的想法以后，江凯文就消失了。我去他上班的地方找他，他避而不见。我给他找电话，我说我错了，我不要你娶我，我只要我们在一起。他在电话里沉默不语。我想不明白凯文为什么这样惧怕婚姻。我每天神思恍惚，晚上回家总是胡思乱想，我迅速地憔悴下去。" 
 +"我到1812去喝酒，这是我和凯文第一次见面的地方。誓言还在耳边，一切却已经改变。我喝了很多酒。我看见有一个头盖骨在酒柜里，好像曾经被摔碎过，胶布像绷带一样缠满了整个头颅。我叫来服务生，我想知道为什么他们为什么会放一个头骨在这里，我还想知道这个头骨生前是男是女，他（她）是否也曾有过悲伤的爱情。服务生说这是老板从华西医院拿来的头骨，生前是一个非常漂亮的女孩，只在这个世上呆了20年。万圣节的时候，客人玩得太疯，" +
 		"摔到地上碎了，所以用胶布粘起来。我说她一定很疼了，你们把她放在这里，她会嫌吵的。服务生说，也许她就喜欢这种生活呢，夜夜笙歌多好啊。我说你们怎么可以这样啊，我拉住服务生一定要他说这个薄的女孩生前有没有过刻骨铭心的爱，我一杯接一杯地喝酒。这时，邓澜和凯文一起出现在我的面前。" 
 +"凯文冲过来抱住我，不停地说对不起对不起，他温热的泪滴在我的脸上和我的泪混在一起，我又感到了他的温度，这让我温暖。我哭着说我不能没有他。我可以不要婚姻，不要名分，什么都不要，只要他别离开我。他终于说出他的苦衷。他以前在部队的时候曾摔断过颈椎，虽然现在好了，但是随时有可能得发导致瘫痪，他不要我为了他受苦。我说生生死死我都要和他在一起，无论贫穷，疾病。除非他不再爱我了。他说傻丫头，我怎么可能不爱你呢，没有你我活着还有什么意思。 "
 +"失而复得的爱情让我觉得多年的坎坷其实算不了什么，幸福一定会属于我，只要我不放弃。凯文对我很好，他似乎也对这份有过波折的爱倍加珍惜。我以为日子可以就这样一直过下去，结婚对我来说已经不重要，重要的是我和凯文在一起。而且，我们彼此相爱。 "
 +"不知道是不是老天故意和我开玩笑。一个月后，凯文又消失了。我满世界找他，疯了一样。我相信他一定有什么苦衷，不然他决不会突然失踪。我求邓澜帮我找到他。我蜷缩在家里，不吃不喝等他的电话。很久以后，在我快要死去的时候，凯文从西藏打回电话说他不能给我一个家，他很穷，没有自己的房子他不会娶我。 "
 +"我立刻倾尽所有买了一套房子。我说我们有自己的房子了，你快回来啊。他回来了，却不愿意再回我身边。他瘦了很多，我相信这些日子里他也常常思念我，不然不会这么消瘦。我忽然发现我根本不懂凯文，在一起一年，一直以为我们了解对方就像了解自己一样。一直相信有了爱其他一切都不重要了。而此刻，我才发现我完全不知道凯文在想什么，他要的又是什么。我感到悲哀。" 
 +"我每天幽灵一样游荡在这个城市的大街小巷，我想我该忘记这个男人，这个多变的男人。我走在路上的时候就像梦游一样，好几次善战被车撞到。有好几次，我恍惚看见凯文在跟踪我，我想一定是我看花眼了。 "
 +"同事结婚，赶去祝福。坐在角落里翻看他们的婚纱照，心里悲凉无比。有个客人走过来对我说，“咦，怎么凯文不上来啊？我看他在楼下已经站了半个小时了，很焦躁的样子。我还以为他在等你呢。”我个人并不知道我和凯文之间后来发生的那些事。我站起来就往楼下冲，只看到楼下一地的烟蒂。我因此确信凯文依然是爱我的，只要他爱我无论发生过什么我都会原谅他。" 
 +"关于这段故事，我已经不想再叙述。凯文最终没有成我相位终身的爱人。他曾经是爱我的，这毋庸置疑。他第二次离开我的原因是他牛顿　他的初恋情人。她一直是他心里的痛，暗恋多年却不敢表达。后来女孩出国，他也结婚生子。现在女孩回来了，三十多岁的女人风采依然，而且一直单身。凯文自从在街上看到她的第一眼就认定她才是他生命里的天使，而此刻的他又是自由身，他相信这是上天安排的缘分，于是，他离开我，开始疯狂地追求她。这是他在一次醉酒后告诉邓澜的。" +
 		"他还说我是一个好女孩，他不能欺骗我。看见我一天天憔悴下去，他也很心疼，他怕我想不开所以才跟踪我。听到这番话的时候，我觉得自己真是滑稽。居然为了这么一个男人如此消瘦。他以为没了他，我会自杀，我不会这么蠢，为了一个不爱自己的男人而折磨自己。早知道他离开我的原因，我甚至不会有一丝一毫的难过，如果伤心，也是为自己的有眼而伤心。" 
 +"结局是，我又回到电台。而凯文依然没有圆他那个青春年少的梦，他曾回来求我说他发现他爱的还是我，没有我他将无法生活。我哑然失笑。我说这也是我曾经对你说过的话，不过，这是我说过的最愚蠢的话。我早已不爱你了，而我也做不了你的天使。这世界谁没谁，生活都会照样继续。没什么大不了的。 "
 +"后来，邓澜对我讲述了凯文的第一次婚姻。这些往事，凯文从没对我说过，我也从不。凯文以前在部队，出身贫寒，最大的梦想就是出人头地。而无论他怎么努力钻营，机会也一直没有垂青于他。为了改变命运，他娶了并不喜欢的团长的女儿。这次婚姻并没有带给他转机，骄横的爱人终于激怒了他。于是，他离婚了。他似乎不再追逐名利，只想找一个相爱的人好好生活。如果不钻营投机，他会是一个相当优秀的人，聪明而体贴。邓澜以为他已经醒悟了，所以才力撮我们。" +
 		"她说她对不起我，不该让我们相识。我笑笑，说一切都过去了。何况，这怨不得谁，是老天早就注定的。18岁的时候就注定了的。" 
 +"选择爱就是选择劫难。 "
 +"我换了城市。继续做DJ的工作。 "
 +"有听众曾说，我的声音透着绝望，那透明清凉的绝望。 "
 +"朋友说我越来越不正常。我说是，一个迷恋夜晚的女子怎么可能正常。 "
 +"有人在背后开始对我指指点点，说一个30岁的女人还不结婚一定是有问题。 "
 +"我不为所动。 但是我知道，总有一天，我会老去，且没有人会再听我说话。";
		}
		System.out.println("Length = " + testString.length() + "  | " +testString.getBytes().length);
		IKSegmentation ikSeg = new IKSegmentation(new StringReader(testString) , false);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		long begin = System.currentTimeMillis();   
		try {
			Lexeme l = null;
//			while( (l = ikSeg.next()) != null){
//				System.out.println(l);
//			}
			while( ikSeg.next()!= null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long end = System.currentTimeMillis();
        System.out.println("耗时 : " + (end - begin) + "ms");

		System.out.println("***************");	
	
	}
	
	public void testQueryParser(){
		Query query = null;
//			String[] fields = new String[]{"f1" , "f3" , "f5"};
//			query = IKQueryParser.parseMultiField(fields,  "title:评测");
			query = IKQueryParser.parse("t",  "和服装饰品");
		System.out.println(query);
	}

}
