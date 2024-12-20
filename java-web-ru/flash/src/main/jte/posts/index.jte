@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
    content = @`
        <div class="container mt-5">
            <h1 class="mb-4">Все посты</h1>
            <a href="/posts/build" class="btn btn-success mb-3">Добавить новый пост</a>
            @if(page.getFlash() != null)
                <div class="alert alert-success" role="alert">
                    ${page.getFlash()}
                </div>
            @endif
            @if(page.getPosts() != null && !page.getPosts().isEmpty())
                @for(var post : page.getPosts())
                    <div class="card mb-3">
                        <div class="card-body">
                            <h5 class="card-title">${post.getName()}</h5>
                            <p class="card-text">${post.getBody()}</p>
                            <a href="/posts/${post.getId()}" class="btn btn-primary">Перейти к посту</a>
                        </div>
                    </div>
                @endfor
            @else
                <p>Постов не найдено.</p>
            @endif
        </div>
    `,
)
