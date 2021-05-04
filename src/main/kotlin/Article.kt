data class Article(
    val id: Int,

    val regDate: String,
    var updateDate: String,

    var memberId: Int,
    var boardId: Int,

    var title: String,
    var body: String

)