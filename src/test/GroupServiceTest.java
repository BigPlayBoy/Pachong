package test;

import java.util.List;

import sina.bean.group.GroupCategory;
import sina.bean.info.LoginInfo;
import sina.dao.GroupDao;
import sina.service.GroupService;

/**
 * @author zc0604
 *
 */
public class GroupServiceTest {
	private static GroupService gs;
	private static GroupDao groupdao;
	public static void main(String[] args) throws Exception {
		saveGroupMemberByGroupID("837622",70);
	}
	public static void saveGroupMemberByGroupID(String groupID,Integer groupTotalMemberNumber){
		gs=new GroupService(LoginInfo.username1,LoginInfo.password);
		gs.saveGroupAdminMemberID(groupID);
		gs.saveGroupMemberID(groupID, groupTotalMemberNumber);
	}
	public static void saveAllGroupCatagory(){
		gs=new GroupService(LoginInfo.username,LoginInfo.password);
		gs.saveAllGroupCatagory();
	}
	public static void downLoadGroupInfo(){
		gs=new GroupService(LoginInfo.username,LoginInfo.password);
		groupdao=new GroupDao();
		
		List<GroupCategory> gc=groupdao.queryAllGroupCategroy();
		for(GroupCategory categroy:gc){
			if(-1!=categroy.getP_id()){
				gs.downLoadGroupInfoByCategroy(categroy);
			}
		}
	}
	public static void redownLoadGroupInfo(){
		gs=new GroupService(LoginInfo.username1,LoginInfo.password);
		groupdao=new GroupDao();
		
		List<GroupCategory> gc=groupdao.queryAllGroupCategroy();
		for(GroupCategory categroy:gc){
			if(-1!=categroy.getP_id()){
				gs.redownLoadGroupInfoByCategroy(categroy);
			}
		}
	}
	public static void saveGroupInfo(){
		gs=new GroupService();
		gs.saveDownLoadedGroupInfo();
	}
	
	public static void saveGroupInfoByGroupIDList(){
		gs.saveDownLoadedGroupTopicMessage();
	}
}
