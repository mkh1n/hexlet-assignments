@import exercise.dto.articles.BuildArticlePage
@import exercise.App

@param BuildArticlePage page

@template.layout.page(
    content = @`
    <h2>Создание статьи</h2>
    <form action="/articles" method="post" class="m-2">
        @if(page.getErrors() != null)
            <div class="alert alert-danger rounded mb-1" role="alert">
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <div>${error.getMessage()}</div>
                    @endfor
                @endfor
            </div>
        @endif
        <div class="form-group mb-1">
            <label for="title">Название статьи</label>
            <input type="text" class="form-control" id="title" required name="title" placeholder="Введите название статьи" value="${page.getTitle()}">
        </div>
        <div class="form-group mb-1">
            <label for="content">Статья</label>
            <textarea class="form-control" id="content" required name="content" rows="10" placeholder="Введите текст статьи">${page.getContent()}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">Отправить</button>
    </form>
    `
)
