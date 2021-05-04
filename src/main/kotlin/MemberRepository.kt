class MemberRepository {
    fun isUsableLoginId(loginId: String): Boolean {
        for(member in members){
            if(member.loginId == loginId){
                return false
            }
        }
        return true
    }

    fun makeTestMembers(){
        for(i in 1..9){
            join("user$i", "user$i", "홍길동$i", "길동이$i", "1577-000$i","test${i}@test.com")
        }
    }

    fun join(loginId: String, loginPw: String, name: String, nickname: String, cellphoneNo: String, email: String): Member {


        val id = ++memberLastId
        val regDate = Util.getNowDateStr()
        val updateDate = Util.getNowDateStr()

        val member = Member(id, loginId, loginPw, regDate, updateDate, name, nickname, cellphoneNo, email)

        members.add(member)

        return member



    }

    fun getMemberByLoginId(loginId: String): Member? {
        for(member in members){
            if(member.loginId  == loginId){
                return member
            }
        }
        return null

    }

    fun getMemberById(memberId: Int): Member? {
        for(member in members){
            if(member.id == memberId){
                return member
            }
        }
        return null

    }

    var memberLastId = 0
    var members = mutableListOf<Member>()
}