

val articleRepository = ArticleRepository()
val memberRepository = MemberRepository()
val boardRepository = BoardRepository()

var loginedMember: Member? = null




fun main(){

    val systemController = SystemController()
    val articleController = ArticleController()
    val memberController = MemberController()
    val boardController = BoardController()


    // 테스트를 위한 데이터 추가 시작

    // board 출력 시, id로 찾기 때문에 아래 Member 데이터는 무의미함.
    loginedMember = Member(1, "jh", "jh", Util.getNowDateStr(), Util.getNowDateStr(), "김지후", "jh", "1234-1234", "jh@test.com")

    articleRepository.makeTestArticles()
    boardRepository.makeTestBoards()
    memberRepository.makeTestMembers()

    // 테스트를 위한 데이터 추가 끝



    while(true){


        var prompt = if(loginedMember == null){
            "명령어)"
        }else(
                "${loginedMember!!.nickname})"
        )



        print(prompt)

        val command = readLineTrim()
        val req = Req(command)

        when(req.actionPath){


            "/system/exit" ->{
                systemController.exit(req)
                break
            }
            "/member/join"->{
                memberController.join(req)
            }
            "/member/login"->{
                memberController.login(req)
            }
            "/member/logout"->{
                memberController.logout(req)
            }
            "/board/add"->{
                boardController.add(req)
            }
            "/board/delete"->{
                boardController.delete(req)
            }
            "/board/modify"->{
                boardController.modify(req)
            }
            "/board/detail"->{
                boardController.detail(req)
            }
            "/board/list"->{
                boardController.list(req)
            }
            "/article/write"->{
                articleController.write(req)
            }
            "/article/delete"->{
                articleController.delete(req)
            }
            "/article/modify"->{
                articleController.modify(req)
            }
            "/article/detail"->{
                articleController.detail(req)
            }
            "/article/list"->{
                articleController.list(req)
            }
            else -> {
                println("`${command}`는(은) 존재하지 않는 명령어입니다.")
            }


        }



    }


}