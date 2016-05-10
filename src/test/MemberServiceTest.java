import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import sina.bean.info.LoginInfo;
import sina.bean.member.Member;
import sina.bean.member.Relation;
import sina.bean.topic.Topic;
import sina.dao.GroupDao;
import sina.dao.MemberDao;
import sina.service.MemberService;
import sina.utils.HttpUtils;
import sina.utils.Utils;

 
public class MemberServiceTest {
	private static MemberDao memberdao=new MemberDao();
	public static void main(String[] args) {
//		saveGroupMemberByGroupID("");
//		saveMemberHeadPicByGroupID("");
		saveMemberRelationIntoDB();
//		saveInnerMemberRelation();
//		saveMememberTopic("");
//		saveMemberFollowingSecondLevel("1762137711");
	}
	
	public static void saveGroupMemberByGroupID(String groupid){
		groupid ="837622";
		MemberService ms=new MemberService(LoginInfo.username1,LoginInfo.password2);
	 	GroupDao gd=new GroupDao();
	 	List<String> memberIDs=gd.queryGroupMemberByID(groupid);
	 	for(String memberID:memberIDs){
	 		String id=memberdao.queryMemberByID(memberID).getMemberID();
	 		if(id !=null && id.trim().length()>0){
	 			continue;
	 		}
	 		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	 		ms.saveMemberInfo(memberID);
	 	}
	}
	
	/*
	 * 某一个 微群中 用户关系 的 入库 
	 */
	public static void saveMemberRelationIntoDB(){
//		int count=0;
		MemberService ms = new MemberService(LoginInfo.username1,LoginInfo.password);;
		List<Member> members = memberdao.queryAllMember();
		List<String> alreadyHasMemberID = memberdao.queryAlreadyHasMember();
		for( Member member : members ){
			if(alreadyHasMemberID.contains(member.getMemberID()) ) {
				continue;
			}
//			if(count==0){
//				ms = new MemberService(LoginInfo.username1,LoginInfo.password);
//			}else if(count ==1){
//				ms = new MemberService(LoginInfo.username2,LoginInfo.password);
//			}else if(count ==2){
//				ms = new MemberService(LoginInfo.username,LoginInfo.password);
//			}
//			count=(count+1)%3;
			ms.saveMemberFollowing(member.getMemberID(), member.getFollowingNumber());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 挖掘 用户关系中内在 用户之间的关系 
	 * 保存单个群中的用户成员之间的关系
	 */
	public static void saveInnerMemberRelation(){
		String groupID="837622";
		List<String> memberIDS=new GroupDao().queryGroupMemberByID(groupID);
		List<Relation> tmp=new ArrayList<Relation>();
		for(String memberID:memberIDS){
			List<Relation> relations=memberdao.queryMemberRelationByMemberID(memberID);
			System.out.println(memberID+"--FOLLOWINTG-"+relations.size());
			for(Relation r:relations){
				if(memberIDS.contains(r.getReleationID())){
					tmp.add(r);
				}
			}
			memberdao.saveMemberInnerRelation(tmp,memberID);
			tmp.clear();
		}
	}
	
	public static void saveMemberTags(){
		MemberService ms = new MemberService(LoginInfo.username1,LoginInfo.password);
		List<Member> members=memberdao.queryAllMember();
		List<String> existTagMemberIDs=memberdao.queryExistTagMemberID();
		for(Member m:members){
			if(!existTagMemberIDs.contains(m.getMemberID())){
				ms.saveMemberInfo(m.getMemberID());
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void saveMemberHeadPicByGroupID(String groupid){
		List<String> members=new GroupDao().queryGroupMemberByID("837622");
		for(String memberID:members){
			Member member=memberdao.queryMemberByID(memberID);
			String headpicUrl=member.getHeadpicurl();
			savePic(headpicUrl,member.getMemberID());
		}
	}
	public static void savePic(String headpicUrl,String memberid){
		Map<String,String> headers=new HashMap<String,String>();
		headers.put("Accept", "text/html, application/xhtml+xml, */*");
		headers.put("Accept-Language", "zh-cn");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
		headers.put("Host", "tp1.sinaimg.cn");
		headers.put("Connection", "Keep-Alive");
		HttpResponse responsepic=HttpUtils.doGet(headpicUrl,headers);
		HttpEntity entitypic=responsepic.getEntity();
		InputStream inpic=null;
		try {
			inpic = entitypic.getContent();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
		Utils.writeFileFromStream("headpic\\"+memberid+".jpeg", inpic);
	}
	
	public static void saveMememberTopic(String memberid){
		memberid="1783571112";
		MemberService ms = new MemberService(LoginInfo.username1,LoginInfo.password);
		List<Topic> topics =ms.getMemberTopic(memberid, 535);
		int i=1;
		for(Topic topic:topics){
			System.out.println(i++ +"-----"+topic.getTopicContent()+"--");
		}
	}
	
	public static void saveMemberFollowingSecondLevel(String memberid){
		MemberService ms = new MemberService(LoginInfo.username1,LoginInfo.password);
		List<String> memberids=memberdao.queryPersonalMemberRelationIDs(memberid);
		List<String> existIDs=memberdao.queryTempMemberIDs();
		for(String id:memberids){
			if(existIDs.contains(id)) continue;
			Member member=ms.saveMemberInfo(id);
			if( member==null ) continue;
			System.out.println(member.getMemberID()+"---"+ member.getFollowingNumber());
			ms.saveMemberFollowing(member.getMemberID(), member.getFollowingNumber());
			memberdao.saveTempMemberIDs(member.getMemberID());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
