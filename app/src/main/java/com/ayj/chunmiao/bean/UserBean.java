package com.ayj.chunmiao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/22.
 */
public class UserBean implements Serializable{

    /**
     * err : 0
     * msg : 账号密码验证成功
     * total : 1
     * data : [{"name":"洋洋","level":null,"status":"USERSTATUS001","deptid":null,"roleid":null,
     * "userid":"10275","shopid":"001","mobile":null,"tel":"13676969840",
     * "imgurl":"Upload/2017-02-27/20170227113133039.png,Upload/2017-02-27/20170227113108776.png,
     * Upload/2017-02-27/20170227113025746.png","isRoot":null,"isParent":null,
     * "createuserid":null,"createdate":null,"statusshow":"在职","dept_type":null,"dept_pid":null,
     * "addr":"苏州市相城区澄阳路2999号朗悦湾","idcard":"401421197005281684","onlyNeedLeader":null,
     * "lead_type":null,"deptidList":null,"zw":"ZWLX017","modifyuserid":null,
     * "modifyuseridshow":"","modifydate":null,"email":null,"ipaddress":null,"userflag":null,
     * "deptcode":null,"needCurrentUser":null,"gender":null,"birthday":null,"phone":null,
     * "sex":"SEX001","zzlxList":null,"zzlx":null,"zzlxName":null,"deptidForRSTree":null,
     * "nativeplace":null,"education":null,"bankid":null,"bankcard":null,"sexshow":"男",
     * "zwshow":"春苗店长","educationshow":"","bankidshow":"","createuseridshow":"",
     * "deptidshow":"春苗","qq":null,"weixin":null,"experience":null,"photouploaddate":null,
     * "photoname":null,"photourl":"/","shopidshow":"朗悦湾小区服务点","pf":"4.52","pfcount":"38",
     * "isayjstore":"SFPD001","isayjstoreshow":"是","shopcomment":null,"isouremployee":null,
     * "photourlcompressshow":"http://test.21ga.cn:8000/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=/","headimgurlshow":"","headimgurl":null,
     * "isouremployeeshow":"","imgurlshow":"","imgurlcompressshow":""}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * name : 洋洋
     * level : null
     * status : USERSTATUS001
     * deptid : null
     * roleid : null
     * userid : 10275
     * shopid : 001
     * mobile : null
     * tel : 13676969840
     * imgurl : Upload/2017-02-27/20170227113133039.png,Upload/2017-02-27/20170227113108776.png,
     * Upload/2017-02-27/20170227113025746.png
     * isRoot : null
     * isParent : null
     * createuserid : null
     * createdate : null
     * statusshow : 在职
     * dept_type : null
     * dept_pid : null
     * addr : 苏州市相城区澄阳路2999号朗悦湾
     * idcard : 401421197005281684
     * onlyNeedLeader : null
     * lead_type : null
     * deptidList : null
     * zw : ZWLX017
     * modifyuserid : null
     * modifyuseridshow :
     * modifydate : null
     * email : null
     * ipaddress : null
     * userflag : null
     * deptcode : null
     * needCurrentUser : null
     * gender : null
     * birthday : null
     * phone : null
     * sex : SEX001
     * zzlxList : null
     * zzlx : null
     * zzlxName : null
     * deptidForRSTree : null
     * nativeplace : null
     * education : null
     * bankid : null
     * bankcard : null
     * sexshow : 男
     * zwshow : 春苗店长
     * educationshow :
     * bankidshow :
     * createuseridshow :
     * deptidshow : 春苗
     * qq : null
     * weixin : null
     * experience : null
     * photouploaddate : null
     * photoname : null
     * photourl : /
     * shopidshow : 朗悦湾小区服务点
     * pf : 4.52
     * pfcount : 38
     * isayjstore : SFPD001
     * isayjstoreshow : 是
     * shopcomment : null
     * isouremployee : null
     * photourlcompressshow : http://test.21ga.cn:8000/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=/
     * headimgurlshow :
     * headimgurl : null
     * isouremployeeshow :
     * imgurlshow :
     * imgurlcompressshow :
     */

    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String name;
        private Object level;
        private String status;
        private Object deptid;
        private Object roleid;
        private String userid;
        private String shopid;
        private Object mobile;
        private String tel;
        private String imgurl;
        private Object isRoot;
        private Object isParent;
        private Object createuserid;
        private Object createdate;
        private String statusshow;
        private Object dept_type;
        private Object dept_pid;
        private String addr;
        private String idcard;
        private Object onlyNeedLeader;
        private Object lead_type;
        private Object deptidList;
        private String zw;
        private Object modifyuserid;
        private String modifyuseridshow;
        private Object modifydate;
        private Object email;
        private Object ipaddress;
        private Object userflag;
        private Object deptcode;
        private Object needCurrentUser;
        private Object gender;
        private Object birthday;
        private Object phone;
        private String sex;
        private Object zzlxList;
        private Object zzlx;
        private Object zzlxName;
        private Object deptidForRSTree;
        private Object nativeplace;
        private Object education;
        private Object bankid;
        private Object bankcard;
        private String sexshow;
        private String zwshow;
        private String educationshow;
        private String bankidshow;
        private String createuseridshow;
        private String deptidshow;
        private Object qq;
        private Object weixin;
        private Object experience;
        private Object photouploaddate;
        private Object photoname;
        private String photourl;
        private String shopidshow;
        private String pf;
        private String pfcount;
        private String isayjstore;
        private String isayjstoreshow;
        private Object shopcomment;
        private Object isouremployee;
        private String photourlcompressshow;
        private String headimgurlshow;
        private Object headimgurl;
        private String isouremployeeshow;
        private String imgurlshow;
        private String imgurlcompressshow;
        private String passWord;

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getDeptid() {
            return deptid;
        }

        public void setDeptid(Object deptid) {
            this.deptid = deptid;
        }

        public Object getRoleid() {
            return roleid;
        }

        public void setRoleid(Object roleid) {
            this.roleid = roleid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public Object getIsRoot() {
            return isRoot;
        }

        public void setIsRoot(Object isRoot) {
            this.isRoot = isRoot;
        }

        public Object getIsParent() {
            return isParent;
        }

        public void setIsParent(Object isParent) {
            this.isParent = isParent;
        }

        public Object getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(Object createuserid) {
            this.createuserid = createuserid;
        }

        public Object getCreatedate() {
            return createdate;
        }

        public void setCreatedate(Object createdate) {
            this.createdate = createdate;
        }

        public String getStatusshow() {
            return statusshow;
        }

        public void setStatusshow(String statusshow) {
            this.statusshow = statusshow;
        }

        public Object getDept_type() {
            return dept_type;
        }

        public void setDept_type(Object dept_type) {
            this.dept_type = dept_type;
        }

        public Object getDept_pid() {
            return dept_pid;
        }

        public void setDept_pid(Object dept_pid) {
            this.dept_pid = dept_pid;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public Object getOnlyNeedLeader() {
            return onlyNeedLeader;
        }

        public void setOnlyNeedLeader(Object onlyNeedLeader) {
            this.onlyNeedLeader = onlyNeedLeader;
        }

        public Object getLead_type() {
            return lead_type;
        }

        public void setLead_type(Object lead_type) {
            this.lead_type = lead_type;
        }

        public Object getDeptidList() {
            return deptidList;
        }

        public void setDeptidList(Object deptidList) {
            this.deptidList = deptidList;
        }

        public String getZw() {
            return zw;
        }

        public void setZw(String zw) {
            this.zw = zw;
        }

        public Object getModifyuserid() {
            return modifyuserid;
        }

        public void setModifyuserid(Object modifyuserid) {
            this.modifyuserid = modifyuserid;
        }

        public String getModifyuseridshow() {
            return modifyuseridshow;
        }

        public void setModifyuseridshow(String modifyuseridshow) {
            this.modifyuseridshow = modifyuseridshow;
        }

        public Object getModifydate() {
            return modifydate;
        }

        public void setModifydate(Object modifydate) {
            this.modifydate = modifydate;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getIpaddress() {
            return ipaddress;
        }

        public void setIpaddress(Object ipaddress) {
            this.ipaddress = ipaddress;
        }

        public Object getUserflag() {
            return userflag;
        }

        public void setUserflag(Object userflag) {
            this.userflag = userflag;
        }

        public Object getDeptcode() {
            return deptcode;
        }

        public void setDeptcode(Object deptcode) {
            this.deptcode = deptcode;
        }

        public Object getNeedCurrentUser() {
            return needCurrentUser;
        }

        public void setNeedCurrentUser(Object needCurrentUser) {
            this.needCurrentUser = needCurrentUser;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getZzlxList() {
            return zzlxList;
        }

        public void setZzlxList(Object zzlxList) {
            this.zzlxList = zzlxList;
        }

        public Object getZzlx() {
            return zzlx;
        }

        public void setZzlx(Object zzlx) {
            this.zzlx = zzlx;
        }

        public Object getZzlxName() {
            return zzlxName;
        }

        public void setZzlxName(Object zzlxName) {
            this.zzlxName = zzlxName;
        }

        public Object getDeptidForRSTree() {
            return deptidForRSTree;
        }

        public void setDeptidForRSTree(Object deptidForRSTree) {
            this.deptidForRSTree = deptidForRSTree;
        }

        public Object getNativeplace() {
            return nativeplace;
        }

        public void setNativeplace(Object nativeplace) {
            this.nativeplace = nativeplace;
        }

        public Object getEducation() {
            return education;
        }

        public void setEducation(Object education) {
            this.education = education;
        }

        public Object getBankid() {
            return bankid;
        }

        public void setBankid(Object bankid) {
            this.bankid = bankid;
        }

        public Object getBankcard() {
            return bankcard;
        }

        public void setBankcard(Object bankcard) {
            this.bankcard = bankcard;
        }

        public String getSexshow() {
            return sexshow;
        }

        public void setSexshow(String sexshow) {
            this.sexshow = sexshow;
        }

        public String getZwshow() {
            return zwshow;
        }

        public void setZwshow(String zwshow) {
            this.zwshow = zwshow;
        }

        public String getEducationshow() {
            return educationshow;
        }

        public void setEducationshow(String educationshow) {
            this.educationshow = educationshow;
        }

        public String getBankidshow() {
            return bankidshow;
        }

        public void setBankidshow(String bankidshow) {
            this.bankidshow = bankidshow;
        }

        public String getCreateuseridshow() {
            return createuseridshow;
        }

        public void setCreateuseridshow(String createuseridshow) {
            this.createuseridshow = createuseridshow;
        }

        public String getDeptidshow() {
            return deptidshow;
        }

        public void setDeptidshow(String deptidshow) {
            this.deptidshow = deptidshow;
        }

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
            this.weixin = weixin;
        }

        public Object getExperience() {
            return experience;
        }

        public void setExperience(Object experience) {
            this.experience = experience;
        }

        public Object getPhotouploaddate() {
            return photouploaddate;
        }

        public void setPhotouploaddate(Object photouploaddate) {
            this.photouploaddate = photouploaddate;
        }

        public Object getPhotoname() {
            return photoname;
        }

        public void setPhotoname(Object photoname) {
            this.photoname = photoname;
        }

        public String getPhotourl() {
            return photourl;
        }

        public void setPhotourl(String photourl) {
            this.photourl = photourl;
        }

        public String getShopidshow() {
            return shopidshow;
        }

        public void setShopidshow(String shopidshow) {
            this.shopidshow = shopidshow;
        }

        public String getPf() {
            return pf;
        }

        public void setPf(String pf) {
            this.pf = pf;
        }

        public String getPfcount() {
            return pfcount;
        }

        public void setPfcount(String pfcount) {
            this.pfcount = pfcount;
        }

        public String getIsayjstore() {
            return isayjstore;
        }

        public void setIsayjstore(String isayjstore) {
            this.isayjstore = isayjstore;
        }

        public String getIsayjstoreshow() {
            return isayjstoreshow;
        }

        public void setIsayjstoreshow(String isayjstoreshow) {
            this.isayjstoreshow = isayjstoreshow;
        }

        public Object getShopcomment() {
            return shopcomment;
        }

        public void setShopcomment(Object shopcomment) {
            this.shopcomment = shopcomment;
        }

        public Object getIsouremployee() {
            return isouremployee;
        }

        public void setIsouremployee(Object isouremployee) {
            this.isouremployee = isouremployee;
        }

        public String getPhotourlcompressshow() {
            return photourlcompressshow;
        }

        public void setPhotourlcompressshow(String photourlcompressshow) {
            this.photourlcompressshow = photourlcompressshow;
        }

        public String getHeadimgurlshow() {
            return headimgurlshow;
        }

        public void setHeadimgurlshow(String headimgurlshow) {
            this.headimgurlshow = headimgurlshow;
        }

        public Object getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(Object headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getIsouremployeeshow() {
            return isouremployeeshow;
        }

        public void setIsouremployeeshow(String isouremployeeshow) {
            this.isouremployeeshow = isouremployeeshow;
        }

        public String getImgurlshow() {
            return imgurlshow;
        }

        public void setImgurlshow(String imgurlshow) {
            this.imgurlshow = imgurlshow;
        }

        public String getImgurlcompressshow() {
            return imgurlcompressshow;
        }

        public void setImgurlcompressshow(String imgurlcompressshow) {
            this.imgurlcompressshow = imgurlcompressshow;
        }
    }
}
