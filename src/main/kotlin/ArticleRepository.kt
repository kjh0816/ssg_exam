class ArticleRepository {

    fun makeTestArticles(){
        for(id in 1 .. 19){
            write(id, (id % 2 + 1), "제목_$id", "내용_$id")
        }
    }
    fun write(loginedMemberId: Int, boardId: Int, title: String, body: String): Article {

        val id = ++articleLastId
        val regDate = Util.getNowDateStr()
        val updateDate = Util.getNowDateStr()

        val article = Article(id, regDate, updateDate, loginedMemberId, boardId, title, body)
        articles.add(article)

        return article


    }

    fun getArticleById(id: Int): Article? {
        for(article in articles){
            if(article.id == id){
                return article
            }
        }
        return null

    }

    fun delete(article: Article) {
        articles.remove(article)

    }

    fun modify(article: Article, boardId: Int, title: String, body: String) {

        val updateDate = Util.getNowDateStr()

        article.updateDate = updateDate
        article.title = title
        article.body = body

    }

    fun getFilteredArticles(searchKeyword: String, page: Int, boardCode: String, itemCountInAPage: Int): List<Article> {
        val filtered1Articles = getSearchKeywordFilteredArticles(articles, searchKeyword, boardCode)
        val filtered2Articles = getPageFilteredArticles(filtered1Articles, page, itemCountInAPage)

        return filtered2Articles

    }

    private fun getSearchKeywordFilteredArticles(articles: MutableList<Article>, searchKeyword: String, boardCode: String): List<Article> {

        val filteredArticles = mutableListOf<Article>()

        val boardCode = if(boardCode.isEmpty()){
            0
        }else{
            boardRepository.getIdByCode(boardCode)
        }


        // boardCode가 0이면 의미 없는 데이터 -> boardCode는 필터링 요소에 넣지 않는다.
        if(boardCode == 0 && searchKeyword.isNotEmpty()){

            for(article in articles){
                if(article.title.contains(searchKeyword)){
                    filteredArticles.add(article)
                }
            }
            return filteredArticles

        }else if(boardCode != 0 && searchKeyword.isNotEmpty()){
            for(article in articles){
                val board = boardRepository.getBoardById(article.boardId)
                if(article.title.contains(searchKeyword) && board?.id == boardCode){
                    filteredArticles.add(article)
                }
            }
            return filteredArticles
        }
        else{
            return articles
        }

    }

    private fun getPageFilteredArticles(filtered1Articles: List<Article>, page: Int, itemCountInAPage: Int): List<Article> {

        val filteredArticles = mutableListOf<Article>()

        val offsetCount = (page - 1) * itemCountInAPage

        val startIndex = filtered1Articles.lastIndex - offsetCount
        var endIndex  = startIndex - (itemCountInAPage - 1)
        if(endIndex < 0){
            endIndex = 0
        }

        for(i in startIndex downTo endIndex){
            filteredArticles.add(filtered1Articles[i])
        }

        return filteredArticles


    }

    var articleLastId = 0
    var articles = mutableListOf<Article>()

}