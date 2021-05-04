class BoardRepository {
    fun isUsableName(name: String): Boolean {
        for(board in boards){
            if(board.name == name){
                return false
            }
        }
        return true

    }

    fun isUsableCode(code: String): Boolean {

        for(board in boards){
            if(board.code == code){
                return false
            }
        }
        return true

    }

    fun makeTestBoards(){
        // board 출력 시, id로 찾기 때문에 아래 Member 데이터는 무의미함.
        add(Member(99,"user1", "user1","지금","지금", "홍길동", "길동이", "1577-0000","test$@test.com"), "공지", "notice")
        add(Member(99,"user1", "user1","지금","지금", "홍길동", "길동이", "1577-0000","test$@test.com"), "자유", "free")
    }

    fun add(loginedMember: Member, name: String, code: String): Board {

        val id = ++boardLastId
        val regDate = Util.getNowDateStr()
        val updateDate = Util.getNowDateStr()

        val board = Board(id, regDate, updateDate, loginedMember.id, name, code)
        boards.add(board)
        return board

    }

    fun getBoardById(id: Int): Board? {

        for(board in boards){
            if(board.id == id){
                return board
            }
        }
        return null

    }

    fun delete(board: Board) {

        boards.remove(board)

    }

    fun modify(board: Board, name: String, code: String) {

        val updateDate = Util.getNowDateStr()

        board.updateDate = updateDate
        board.name = name
        board.code = code

    }

    fun getList() {
        for(board in boards){
            val member = memberRepository.getMemberById(board.memberId)
            println("${board.id} / ${board.name} / ${board.code} / ${member?.nickname} / ${board.updateDate} / ${board.regDate}")
        }

    }

    fun getIdByCode(boardCode: String): Int {
        for(board in boards){
            if(board.code == boardCode){
                return board.id
            }
        }
        return 0
    }


    var boardLastId = 0
    var boards = mutableListOf<Board>()
}